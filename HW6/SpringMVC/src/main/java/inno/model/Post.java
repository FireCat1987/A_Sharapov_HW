package inno.model;

import java.util.Date;

public class Post {

    private String title;
    private String text;
    private Date date;
    private long id;

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
