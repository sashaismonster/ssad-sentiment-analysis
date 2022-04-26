package FeedbackAnalyzer.service.impl.entities;

import FeedbackAnalyzer.constants.CommonConstants;
import FeedbackAnalyzer.dto.AbstractDto;
import FeedbackAnalyzer.dto.UserDto;
import FeedbackAnalyzer.repository.Table;
import FeedbackAnalyzer.service.ApiService;
import FeedbackAnalyzer.service.impl.JsonReaderServiceImpl;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service to interact with Table.
 * Can save or get User objects
 */
public class UserService implements ApiService {

    private static UserService userService;

    protected final Table<UserDto> userTable;

    private UserService() {
        this.userTable = new Table<>();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    /**
     * Create UserDto object and save to Table
     *
     * @param dto - User dto
     * @return identifier of created object
     */
    @Override
    public UUID create(AbstractDto dto) {
        this.userTable.add((UserDto) dto);
        return dto.getId();
    }

    /**
     * Get an UserDto from Table
     *
     * @param id - identifier of object
     * @return - return UserDto by identifier
     */
    @Override
    public AbstractDto get(UUID id) {
        return this.userTable.get(id);
    }

    /**
     * Get a list of all UserDto
     *
     * @return list of Users
     */
    public List<UserDto> getList() {
        return this.userTable.getList();
    }

    /**
     * Initialize Table with objects from json file
     * path to json should be setted in constants.CommonConstants.USER_PATH
     */
    @Override
    public void initialize() {
        Gson gson = new Gson();
        List<Object> source = JsonReaderServiceImpl.readObjectFromFile(CommonConstants.USER_PATH);
        List<UserDto> data = source.stream().map(object -> gson.fromJson(gson.toJson(object), UserDto.class))
                .collect(Collectors.toList());
        this.userTable.initialize(data);
    }
}
