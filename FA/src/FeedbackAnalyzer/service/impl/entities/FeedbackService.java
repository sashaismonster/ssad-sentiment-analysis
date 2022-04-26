package FeedbackAnalyzer.service.impl.entities;

import FeedbackAnalyzer.constants.CommonConstants;
import FeedbackAnalyzer.dto.AbstractDto;
import FeedbackAnalyzer.dto.FeedbackDto;
import FeedbackAnalyzer.repository.Table;
import FeedbackAnalyzer.service.ApiService;
import FeedbackAnalyzer.service.impl.JsonReaderServiceImpl;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service to interact with Table.
 * Can save or get Feedback objects
 */
public class FeedbackService implements ApiService {

    private static FeedbackService feedbackService;

    protected final Table<FeedbackDto> feedbackTable;

    private FeedbackService() {
        this.feedbackTable = new Table<>();
    }

    public static FeedbackService getInstance() {
        if (feedbackService == null) {
            feedbackService = new FeedbackService();
        }
        return feedbackService;
    }

    /**
     * Get feedback list from Table by user identifier
     *
     * @param userID - identifier of user
     * @return list of FeedbackDto
     */
    public List<FeedbackDto> getByUserID(UUID userID) {
        return this.feedbackTable.getList().stream().filter(feedbackDto ->
                feedbackDto.getUserId().equals(userID)).collect(Collectors.toList());
    }

    /**
     * Get feedback list from Table by product identifier
     *
     * @param productID - identifier of product
     * @return list of FeedbackDto
     */
    public List<FeedbackDto> getByProductID(UUID productID) {
        return this.feedbackTable.getList().stream().filter(feedbackDto ->
                feedbackDto.getProductId().equals(productID)).collect(Collectors.toList());
    }

    /**
     * Create FeedbackDto object and save to Table
     *
     * @param dto - Feedback dto
     * @return identifier of created object
     */
    @Override
    public UUID create(AbstractDto dto) {
        this.feedbackTable.add((FeedbackDto) dto);
        return dto.getId();
    }

    /**
     * Get an FeedbackDto from Table
     *
     * @param id - identifier of object
     * @return - return FeedbackDto by identifier
     */
    @Override
    public AbstractDto get(UUID id) {
        return this.feedbackTable.get(id);
    }

    /**
     * Get a list of all FeedbackDto
     *
     * @return list of Feedbacks
     */
    public List<FeedbackDto> getList() {
        return this.feedbackTable.getList();
    }

    /**
     * Initialize Table with objects from json file
     * path to json should be setted in constants.CommonConstants.FEEDBACK_PATH
     */
    @Override
    public void initialize() {
        Gson gson = new Gson();
        List<Object> source = JsonReaderServiceImpl.readObjectFromFile(CommonConstants.FEEDBACK_PATH);
        List<FeedbackDto> data = source.stream().map(object -> gson.fromJson(gson.toJson(object), FeedbackDto.class))
                .collect(Collectors.toList());
        this.feedbackTable.initialize(data);
    }

}
