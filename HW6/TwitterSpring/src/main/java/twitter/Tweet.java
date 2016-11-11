package twitter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "tweets")
public class Tweet implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;
    @Column(name = "message")
    private String message;
    @Column(name = "createat")
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

