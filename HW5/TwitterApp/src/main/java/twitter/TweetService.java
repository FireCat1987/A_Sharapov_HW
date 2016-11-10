package twitter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TweetService {

    private final String name = "padmin";
    private final String password = "123456";
    private final String path = "jdbc:postgresql://localhost:5432/twitter";
    private Connection connection = null;
    private List<Tweet> tweets = new ArrayList<>();

    TweetService() {
        ConnectBD();
    }

    public void ConnectBD() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(path, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Success");
        }
    }

    public void connectClose() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tweet> getAllTweets() {
        try {
            List<Tweet> finalResult = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tweets");
            while (result.next()) {
                finalResult.add(new Tweet(result.getInt("id"), result.getString("message"), result.getDate("createAt")));
            }
            return finalResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void addTweet(String message) {
        Tweet tweet = new Tweet(message, new Date());
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO  tweets (message, createat) VALUES (" + "'" + tweet.getMessage() + "'" + "," + "'" + tweet.getCreatedAt() + "'" + ") ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delTweet(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM tweets WHERE id= " + "'" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Tweet getTweetById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tweets WHERE id='" + id + "'");
            result.next();
            return new Tweet(result.getInt("id"), result.getString("message"), result.getDate("createAt"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Comment> getCommentsById(int tweetat) {
        try {
            List<Comment> finalResult = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM comments WHERE tweetat='" + tweetat + "'");
            while (result.next()) {
                finalResult.add(new Comment(result.getInt("id"), result.getString("comment"), result.getInt("tweetat")));
            }
            return finalResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void addComment(String message, int tweetAt) {
        Comment comment = new Comment(message, tweetAt);
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO  comments (comment, tweetat) VALUES (" + "'" + comment.getComment() + "'" + "," + "'" + comment.getTweetAt() + "'" + ") ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delComment(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM comments WHERE id= " + "'" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}