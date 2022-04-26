package FeedbackAnalyzer.service.impl.analyzer;

import java.util.UUID;

public class AnalyzerServiceImpl {
    private static AnalyzerServiceImpl analyzerServiceImpl;

    private AbstractAnalyzerService analyzerService;

    private AnalyzerServiceImpl() {
        this.analyzerService = ComplexAnalyzerService.getInstance();
    }

    public static AnalyzerServiceImpl getInstance() {
        if (analyzerServiceImpl == null) {
            analyzerServiceImpl = new AnalyzerServiceImpl();
        }
        return analyzerServiceImpl;
    }

    /**
     * Set different Analyzer strategies
     *
     * @param abstractAnalyzerService - analyzer of type AnalyzerService
     */
    public void setAnalyzerService(AbstractAnalyzerService abstractAnalyzerService) {
        this.analyzerService = abstractAnalyzerService;
    }

    /**
     * Execute calculating rating for some product
     *
     * @param productID - Some product's ID of type UUID
     * @return calculated rating of String type
     */
    public String calculateRating(UUID productID) {
        return this.analyzerService.calculateRating(productID);
    }
}
