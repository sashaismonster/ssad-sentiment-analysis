package FeedbackAnalyzer.service.impl.analyzer;

import FeedbackAnalyzer.service.impl.entities.WordService;

import java.util.List;
import java.util.UUID;

public class SimpleAnalyzerService extends AbstractAnalyzerService {
    private static SimpleAnalyzerService analyzerService;

    private SimpleAnalyzerService() {
        super();
    }

    public static SimpleAnalyzerService getInstance() {
        if (analyzerService == null) {
            analyzerService = new SimpleAnalyzerService();
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
        // totalRating -> [-n; n]
        totalRating += n; // -> [0; 2n]
        totalRating = totalRating * 5 / n; // -> [0; 10]
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
        double curWeight;
        for (String word : this.tokenizeComment(comment)) {
            curWeight = WordService.getWeightByWord(word);
            if (curWeight > 0) {
                rating += 1;
            } else if (curWeight < 0) {
                rating -= 1;
            }

        }
        return rating;
    }
}

