package FeedbackAnalyzer.service.impl.entities;

import FeedbackAnalyzer.constants.CommonConstants;
import FeedbackAnalyzer.dto.AbstractDto;
import FeedbackAnalyzer.dto.ProductDto;
import FeedbackAnalyzer.repository.Table;
import FeedbackAnalyzer.service.ApiService;
import FeedbackAnalyzer.service.impl.JsonReaderServiceImpl;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service to interact with Table.
 * Can save or get Product objects
 */
public class ProductService implements ApiService {

    private static ProductService productService;

    protected final Table<ProductDto> productTable;


    private ProductService() {
        this.productTable = new Table<>();
    }

    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    /**
     * Update rating for product by identifier
     *
     * @param productId - identifier of product
     * @param rating    - rating for product
     */
    public void updateRating(UUID productId, String rating) {
        ProductDto dto = (ProductDto) get(productId);
        dto.setRating(rating);
    }

    /**
     * Create ProductDto object and save to Table
     *
     * @param dto - Product dto
     * @return identifier of created object
     */
    @Override
    public UUID create(AbstractDto dto) {
        this.productTable.add((ProductDto) dto);
        return dto.getId();
    }

    /**
     * Get an ProductDto from Table
     *
     * @param id - identifier of object
     * @return - return ProductDto by identifier
     */
    @Override
    public AbstractDto get(UUID id) {
        return this.productTable.get(id);
    }

    /**
     * Get a list of all ProductDto
     *
     * @return list of Product
     */
    public List<ProductDto> getList() {
        return this.productTable.getList();
    }

    /**
     * Initialize Table with objects from json file
     * path to json should be setted in constants.CommonConstants.PRODUCT_PATH
     */
    @Override
    public void initialize() {
        Gson gson = new Gson();
        List<Object> source = JsonReaderServiceImpl.readObjectFromFile(CommonConstants.PRODUCT_PATH);
        List<ProductDto> data = source.stream().map(object -> gson.fromJson(gson.toJson(object), ProductDto.class))
                .collect(Collectors.toList());
        this.productTable.initialize(data);
    }
}
