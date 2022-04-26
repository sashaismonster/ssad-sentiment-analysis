package FeedbackAnalyzer.service.impl.entities;

import FeedbackAnalyzer.constants.CommonConstants;
import FeedbackAnalyzer.dto.AbstractDto;
import FeedbackAnalyzer.dto.WordDto;
import FeedbackAnalyzer.repository.Table;
import FeedbackAnalyzer.service.ApiService;
import FeedbackAnalyzer.service.impl.JsonReaderServiceImpl;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service to interact with Table.
 * Can save or get Word objects
 */
public class WordService implements ApiService {

    private static WordService wordService;

    protected final Table<WordDto> wordTable;

    private WordService() {
        this.wordTable = new Table<>();
    }

    public static WordService getInstance() {
        if (wordService == null) {
            wordService = new WordService();
        }
        return wordService;
    }

    /**
     * Find a Word in table by word
     *
     * @param word - String word to find Word object in Table of words
     * @return Word data transfer object
     */
    public WordDto getByWord(String word) {
        for (WordDto wordDto : this.wordTable.getList()) {
            if (wordDto.getWord().equalsIgnoreCase(word)) {
                return wordDto;
            }
        }
        return null;
    }

    /**
     * Get a weight for specific word in parameters
     *
     * @param wordString - Word to find WordDto
     * @return weight of given word
     */
    public static double getWeightByWord(String wordString) {
        WordDto word = wordService.getByWord(wordString);
        return word != null ? word.getWeight() : 0;
    }

    /**
     * Create WordDto object and save to Table
     *
     * @param dto - Word dto
     * @return identifier of created object
     */
    @Override
    public UUID create(AbstractDto dto) {
        this.wordTable.add((WordDto) dto);
        return dto.getId();
    }

    /**
     * Get an WordDto from Table
     *
     * @param id - identifier of object
     * @return - return WordDto by identifier
     */
    @Override
    public AbstractDto get(UUID id) {
        return this.wordTable.get(id);
    }

    /**
     * Get a list of all WordDto
     *
     * @return list of Words
     */
    public List<WordDto> getList() {
        return this.wordTable.getList();
    }

    /**
     * Initialize Table with objects from json file
     * path to json should be setted in constants.CommonConstants.WORD_PATH
     */
    @Override
    public void initialize() {
        Gson gson = new Gson();
        List<Object> source = JsonReaderServiceImpl.readObjectFromFile(CommonConstants.WORD_PATH);
        List<WordDto> data = source.stream().map(object -> gson.fromJson(gson.toJson(object), WordDto.class))
                .collect(Collectors.toList());
        this.wordTable.initialize(data);
    }
}
