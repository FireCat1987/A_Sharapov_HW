package twitter;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("TweetService")
@Transactional
class TweetService {
    private static Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private final String name = "padmin";
    private final String password = "123456";
    private final String path = "jdbc:postgresql://localhost:5432/twitter";
    private Connection connection = null;
    private List<Tweet> tweets = new ArrayList<>();

    TweetService() {
        ConnectBD();
    }

    private void ConnectBD() {
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

    List<Tweet> getAllTweets() {
        logger.debug("Retrieving all tweets");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM tweets");
        return query.list();
        /*try {
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
        }*/
    }


    void addTweet(String message) {
        Tweet tweet = new Tweet(message, new Date());
        logger.debug("Adding new tweet");
        Session session = sessionFactory.getCurrentSession();
        session.save(tweet);

       /* try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO  tweets (message, createat) VALUES (" + "'" + tweet.getMessage() + "'" + "," + "'" + tweet.getCreatedAt() + "'" + ") ");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    void editTweet(int id, String message) {
        logger.debug("Editing existing tweet");
        Session session = sessionFactory.getCurrentSession();
        Tweet existingTweet = (Tweet) session.get(Tweet.class, id);

        existingTweet.setMessage(message);
        existingTweet.setCreatedAt(existingTweet.getCreatedAt());
        existingTweet.setId(existingTweet.getId());

        session.save(existingTweet);

        /*
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE tweets SET  message = '" + message + "' WHERE id = '" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    void delTweet(int id) {
        logger.debug("Deleting existing tweet");
        Session session = sessionFactory.getCurrentSession();
        Tweet tweet = (Tweet) session.get(Tweet.class, id);
        session.delete(tweet);
        /*try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM tweets WHERE id= " + "'" + id + "'");
            statement.execute("DELETE FROM comments WHERE tweetat= " + "'" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    Tweet getTweetById(int id) {
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

    List<Comment> getCommentsById(int tweetat) {
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