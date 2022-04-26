package FeedbackAnalyzer.dto;

public class WordDto extends AbstractDto {

    private static final long serialVersionUID = -8404038822005680731L;

    private String word;

    private Double weight;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("id: %s, word: %s, weight: %s", this.id.toString(), this.word, this.weight.toString());
    }

}
