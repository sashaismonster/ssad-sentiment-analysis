package FeedbackAnalyzer.service;

import FeedbackAnalyzer.dto.AbstractDto;

import java.util.UUID;

/**
 * Common interface for interact with low level objects storing.
 */
public interface ApiService {

    /**
     * Create object and save to Table
     *
     * @param dto - Data transfer object
     * @return identifier of created object
     */
    UUID create(AbstractDto dto);

    /**
     * Get an object from Table
     *
     * @param id - identifier of object
     * @return - return object by identifier
     */
    AbstractDto get(UUID id);

    /**
     * Initialize Table with objects from json files
     * path to json should be setted in constants.CommonConstants
     */
    void initialize();
}
