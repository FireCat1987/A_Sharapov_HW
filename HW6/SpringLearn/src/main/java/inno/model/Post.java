package inno.model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="post")
@SequenceGenerator(sequenceName = "post_id_seq", name = "postSequence")
public class Post {
    @Column
    private String title;
    @Column
    private String text;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSequence")
    @Column(name="id")
    private long id;

    @Transient
    @Enumerated(EnumType.STRING)
    private PostType type;


    public Post() {
        this.date = new Date();
    }

    public Post(String title, String text) {
        this.date = new Date();
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
