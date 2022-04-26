package FeedbackAnalyzer.application;

import FeedbackAnalyzer.dto.FeedbackDto;
import FeedbackAnalyzer.dto.ProductDto;
import FeedbackAnalyzer.dto.UserDto;
import FeedbackAnalyzer.dto.WordDto;
import FeedbackAnalyzer.service.impl.analyzer.AnalyzerServiceImpl;
import FeedbackAnalyzer.service.impl.analyzer.ComplexAnalyzerService;
import FeedbackAnalyzer.service.impl.analyzer.SimpleAnalyzerService;
import FeedbackAnalyzer.service.impl.entities.FeedbackService;
import FeedbackAnalyzer.service.impl.entities.ProductService;
import FeedbackAnalyzer.service.impl.entities.UserService;
import FeedbackAnalyzer.service.impl.entities.WordService;

import java.util.List;
import java.util.UUID;

public class AnalyzerAppImpl implements AnalyzerApp {

    private static AnalyzerAppImpl analyzerApp;

    private final UserService userService;

    private final WordService wordService;

    private final ProductService productService;

    private final FeedbackService feedbackService;

    private final ComplexAnalyzerService complexAnalyzerService;
    private final SimpleAnalyzerService simpleAnalyzerService;

    private final AnalyzerServiceImpl analyzerServiceImpl;

    private AnalyzerAppImpl() {
        this.userService = UserService.getInstance();
        this.wordService = WordService.getInstance();
        this.productService = ProductService.getInstance();
        this.feedbackService = FeedbackService.getInstance();
        this.complexAnalyzerService = ComplexAnalyzerService.getInstance();
        this.simpleAnalyzerService = SimpleAnalyzerService.getInstance();
        this.analyzerServiceImpl = AnalyzerServiceImpl.getInstance();
    }

    public static AnalyzerAppImpl getInstance() {
        if (analyzerApp == null) {
            analyzerApp = new AnalyzerAppImpl();
        }
        return analyzerApp;
    }

    @Override
    public void initializeDtoCache() {
        this.userService.initialize();
        this.wordService.initialize();
        this.productService.initialize();
        this.feedbackService.initialize();
    }

    @Override
    public void printAllUsers() {
        List<UserDto> dtoList = this.userService.getList();
        System.out.printf("Total user count: %d%n", dtoList.size());
        dtoList.forEach(dto -> System.out.println(dto.toString()));

    }

    @Override
    public void printUserDetails(UUID userId) {
        UserDto userDto = (UserDto) this.userService.get(userId);
        System.out.println(userDto.toString());
    }

    @Override
    public void printAllFeedbacks(UUID productId) {
        List<FeedbackDto> dtoList = this.feedbackService.getList();
        System.out.printf("Total feedback count: %d%n", dtoList.size());
        dtoList.forEach(dto -> System.out.println(dto.toString()));
    }

    @Override
    public void printAllProducts() {
        List<ProductDto> dtoList = this.productService.getList();
        System.out.printf("Total product count: %d%n", dtoList.size());
        dtoList.forEach(dto -> System.out.println(dto.toString()));
    }

    @Override
    public void printProductDetails(UUID productId) {
        ProductDto productDto = (ProductDto) this.productService.get(productId);
        System.out.println(productDto.toString());
    }

    @Override
    public void calculateRating() {
        for (ProductDto product : this.productService.getList()) {
            if (this.feedbackService.getByProductID(product.getId()).size() > 3) {
                analyzerServiceImpl.setAnalyzerService(this.complexAnalyzerService);
            } else {
                analyzerServiceImpl.setAnalyzerService(this.simpleAnalyzerService);
            }
            String rating = this.analyzerServiceImpl.calculateRating(product.getId());
            this.productService.updateRating(product.getId(), rating);
        }
    }

    @Override
    public void printAllWords() {
        List<WordDto> dtoList = this.wordService.getList();
        System.out.printf("Total word count: %d%n", dtoList.size());
        dtoList.forEach(dto -> System.out.println(dto.toString()));
    }

}
