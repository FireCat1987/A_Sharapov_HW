package twitter;


import java.util.Date;

public class Tweet {

    private long id;

    private String message;

    private Date createdAt;

    private int commentKol = 0;

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

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "message='" + message + '\'' +
                ", createdAt=" + createdAt + '\'' +
                ", commentKol=" + commentKol +
                '}';
    }

    public int getCommentKol() {
        return commentKol;
    }

    public void setCommentKol(int commentKol) {
        this.commentKol = commentKol;
    }
}