package FeedbackAnalyzer.dto;

public class ProductDto extends AbstractDto {

    private static final long serialVersionUID = 8445967992707061150L;

    private String productName;

    private String rating;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("id: %s, text: %s, rating: %s", this.id.toString(), this.productName, this.rating);
    }
}
