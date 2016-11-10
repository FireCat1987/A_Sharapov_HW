package twitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TweetService {

    private static int idst = 0;
    private static int idsc = 0;

    private static List<Tweet> tweets = new ArrayList<Tweet>();
    private static List<Comment> comments = new ArrayList<Comment>();

    public static List<Tweet> getAllTweet() {
        return tweets;
    }

    public static List<Comment> getAllComments() {
        return comments;
    }

    public static boolean addTweet(String message) {
        Tweet tweet = new Tweet();
        tweet.setMessage(message);
        tweet.setCreatedAt(new Date());
        tweet.setId(++idst);
        return tweets.add(tweet);
    }

    public static boolean addComment(long tweetId, String message) {
        Comment comment = new Comment();
        comment.setMessage(message);
        comment.setCreatedAt(new Date());
        comment.setTweetAt(tweetId);
        for (Tweet tweet : tweets) {
            if (tweet.getId() == tweetId) {
                tweet.setCommentKol(tweet.getCommentKol()+1);
            }
        }
        comment.setId(++idsc);
        return comments.add(comment);
    }

    public static Tweet getTweetById(long id) {
        for (Tweet tweet : tweets) {
            if (tweet.getId() == id) {
                return tweet;
            }
        }
        return null;
    }
    public static List<Comment> getCommentById(long tweetId) {
        List<Comment> commentList = new ArrayList<Comment>();
        for (Comment comment : comments) {
            if (comment.getTweetAt() == tweetId) {
                commentList.add(comment);
            }
        }
        return commentList;
    }
}