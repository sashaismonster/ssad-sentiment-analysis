package FeedbackAnalyzer;

import FeedbackAnalyzer.application.AnalyzerApp;
import FeedbackAnalyzer.application.AnalyzerAppImpl;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        AnalyzerApp analyzerApp = AnalyzerAppImpl.getInstance();
        analyzerApp.initializeDtoCache(); // initialization cache (imitation of real database)
        analyzerApp.calculateRating();
        analyzerApp.printAllUsers();
        analyzerApp.printAllProducts();
        System.out.println("Hello World!");
        analyzerApp.printProductDetails(UUID.fromString("0d65066a-e6ed-4391-ba4a-0c2b442ce936"));
        analyzerApp.printUserDetails(UUID.fromString("752160e2-cd14-4e8b-bc85-5ab6af8a30f5"));
    }
}
