package FeedbackAnalyzer.service.impl.analyzer;

import java.util.List;
import java.util.UUID;

interface Analyzer {

    /**
     * Collect weights from every product's feedback, normalize it and transform it into string format
     *
     * @param productID - Some product's ID of type UUID
     * @return calculated rating of String type
     */
    String calculateRating(UUID productID);

    /**
     * Transform double type rating into String format
     *
     * @param rating - Rating of type double
     * @return transformed into String format rating
     */
    String transformRating(double rating);

    /**
     * Calculate weight of some feedback
     *
     * @param comment - Some feedback's text of type String
     * @return double weight of some feedback
     */
    double analyse(String comment);

    /**
     * Provide all product's feedbacks
     *
     * @param productID - Some product's ID of type UUID
     * @return list of String type comments
     */
    List<String> getProductComments(UUID productID);

    /**
     * Tokenize given comment
     *
     * @param comment - Some String type comment
     * @return list of String type words
     */
    String[] tokenizeComment(String comment);
}
