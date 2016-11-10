package twitter;


import java.util.Date;

public class Comment {

    private long id;

    private String message;

    private Date createdAt;

    private long tweetAt;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "message='" + message + '\'' +
                ", createdAt=" + createdAt + '\'' +
                ", tweetAt=" + tweetAt +
                '}';
    }

    public long getTweetAt() {
        return tweetAt;
    }

    public void setTweetAt(long tweetAt) {
        this.tweetAt = tweetAt;
    }
}