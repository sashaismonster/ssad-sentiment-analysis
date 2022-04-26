package FeedbackAnalyzer.service.impl.analyzer;

import FeedbackAnalyzer.dto.FeedbackDto;
import FeedbackAnalyzer.service.impl.entities.FeedbackService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

abstract class AbstractAnalyzerService implements Analyzer {

    protected final FeedbackService feedbackService;

    protected AbstractAnalyzerService() {
        this.feedbackService = FeedbackService.getInstance();
    }


    /**
     * Calculate weight of some feedback
     *
     * @param comment - Some feedback's text of type String
     * @return double weight of some feedback
     */
    @Override
    public String transformRating(double rating) {
        String stringRating;
        if (rating < 10.0 / 3.0) { // 0 - 3.(3)
            stringRating = "very bad";
        } else if (rating < 20.0 / 3.0) { // 3.(3) - 6.(6)
            stringRating = "bad";
        } else { // 6.(6) - 10.0
            stringRating = "good";
        }
        return stringRating;
    }

    /**
     * Provide all product's feedbacks
     *
     * @param productID - Some product's ID of type UUID
     * @return list of String type comments
     */
    @Override
    public List<String> getProductComments(UUID productID) {
        List<FeedbackDto> feedbackList = this.feedbackService.getByProductID(productID);
        return feedbackList
                .stream()
                .map(FeedbackDto::getText)
                .collect(Collectors.toList());
    }

    /**
     * Tokenize given comment
     *
     * @param comment - Some String type comment
     * @return list of String type words
     */
    @Override
    public String[] tokenizeComment(String comment) {
        return comment.replaceAll("[^A-Za-z ]", "")
                .replaceAll(" +", " ")
                .split(" ");
    }
}

