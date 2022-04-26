package FeedbackAnalyzer.application;

import java.util.UUID;

/**
 * Main interface to use application
 */
public interface AnalyzerApp {

    /**
     * Initialize application with data
     */
    void initializeDtoCache();

    /**
     * Print to console information about users in application
     */
    void printAllUsers();

    /**
     * Print to console information about user by identifier
     * @param userId - User identifier
     */
    void printUserDetails(UUID userId);

    /**
     * Print to console information about feedbacks in application
     */
    void printAllFeedbacks(UUID productId);

    /**
     * Print to console information about products in application
     */
    void printAllProducts();

    /**
     * Print to console information about user by identifier
     * @param productId - product identifier
     */
    void printProductDetails(UUID productId);

    /**
     * Calculate rating for all products in application
     */
    void calculateRating();

    /**
     * Print to console information about all words in application
     */
    void printAllWords();
}
