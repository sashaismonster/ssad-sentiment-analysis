package FeedbackAnalyzer.dto;

import java.util.UUID;

public class FeedbackDto extends AbstractDto {

    private static final long serialVersionUID = 2788842856965823803L;

    private UUID userId;

    private UUID productId;

    private String text;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("id: %s, productId: %s. text: %s", this.id.toString(), productId.toString(), this.text);
    }

}
