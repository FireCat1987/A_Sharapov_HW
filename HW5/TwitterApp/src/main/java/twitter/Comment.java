package twitter;


public class Comment {

    private long id;

    private String comment;

    private long tweetAt;

    public Comment(long id, String comment, long tweetAt) {
        this.id = id;
        this.comment = comment;
        this.tweetAt = tweetAt;
    }

    public Comment(String comment, long tweetAt) {
        this.comment = comment;
        this.tweetAt = tweetAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getTweetAt() {
        return tweetAt;
    }

    public void setTweetAt(long tweetAt) {
        this.tweetAt = tweetAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "message='" + comment + '\'' +
                ", tweetAt=" + tweetAt +
                '}';
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}