package twitter;


import java.util.Date;

public class Tweet {

    private int id;
    private String message;
    private Date createdAt;

    public Tweet(int id, String message, Date createAt) {
        this.id = id;
         this.message = message;
        this.createdAt = createAt;
    }
    public Tweet(String message, Date createAt) {
        this.message = message;
        this.createdAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                "message='" + message + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

}