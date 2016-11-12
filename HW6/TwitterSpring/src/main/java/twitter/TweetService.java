package twitter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
class TweetService {

    @Autowired
    DataSource dataSource; //look to application-context.xml bean id='dataSource' definition

    private JdbcTemplate jdbcTemplate;
    @PostConstruct
    public void init() {
        System.out.println("JDBCExample postConstruct is called. datasource = " + dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private List<Tweet> tweets = new ArrayList<>();



    List<Tweet> getAllTweets() {

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
        this.JdbcTemplate

       /* try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO  tweets (message, createat) VALUES (" + "'" + tweet.getMessage() + "'" + "," + "'" + tweet.getCreatedAt() + "'" + ") ");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    void editTweet(int id, String message) {

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