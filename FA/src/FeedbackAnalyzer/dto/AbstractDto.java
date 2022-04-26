package FeedbackAnalyzer.dto;

import java.io.Serializable;
import java.util.UUID;

public class AbstractDto implements Serializable {

    private static final long serialVersionUID = 1101555259845272143L;

    protected UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String toString() {
        return String.format("id: %s", this.id.toString());
    }
}
