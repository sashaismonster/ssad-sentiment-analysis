package FeedbackAnalyzer.service.impl.analyzer;

import FeedbackAnalyzer.dto.FeedbackDto;
import FeedbackAnalyzer.service.impl.entities.WordService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ComplexAnalyzerService extends AbstractAnalyzerService {
    private static ComplexAnalyzerService analyzerService;

    private ComplexAnalyzerService() {
        super();
    }

    public static ComplexAnalyzerService getInstance() {
        if (analyzerService == null) {
            analyzerService = new ComplexAnalyzerService();
        }
        return analyzerService;
    }

    /**
     * Collect weights from every product's feedback, normalize it and transform it into string format
     *
     * @param productID - Some product's ID of type UUID
     * @return calculated rating of String type
     */
    @Override
    public String calculateRating(UUID productID) {
        List<String> comments = this.getProductComments(productID);
        double totalRating = 0;
        for (String comment : comments) {
            totalRating += this.analyse(comment);
        }
        int n = comments.size();
        double ratingBorder = this.getRatingBorder(productID);
        // totalRating -> [-n*rb; n*rb]
        totalRating += n * ratingBorder; // -> [0; 2*n*rb]
        totalRating *= 5 / (n * ratingBorder); // -> [0; 10]
        return transformRating(totalRating);
    }

    /**
     * Calculate weight of some feedback
     *
     * @param comment - Some feedback's text of type String
     * @return double weight of some feedback
     */
    @Override
    public double analyse(String comment) {
        double rating = 0;
        for (String word : this.tokenizeComment(comment)) {
            rating += WordService.getWeightByWord(word);
        }

        return rating;
    }

    /**
     * Provide rating border of some product's feedbacks for future normalization
     *
     * @param productID - Some product's ID of type UUID
     * @return double type rating border
     */
    private double getRatingBorder(UUID productID) {
        double curRating;
        List<String> allComments = this.feedbackService
                .getByProductID(productID)
                .stream()
                .map(dto -> ((FeedbackDto) dto).getText())
                .collect(Collectors.toList());
        double ratingBorder = 0;
        for (String comment : allComments) {
            curRating = 0;
            for (String word : this.tokenizeComment(comment)) {
                curRating += WordService.getWeightByWord(word);
            }
            if (ratingBorder < Math.abs(curRating)) {
                ratingBorder = Math.abs(curRating);
            }
        }
        return ratingBorder;
    }
}

