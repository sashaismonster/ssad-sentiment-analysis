package FeedbackAnalyzer.dto;

public class UserDto extends AbstractDto {
    private static final long serialVersionUID = 576573056207603431L;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format("id: %s, username: %s", this.id.toString(), this.username);
    }
}
