package inno.repository.impl;

import inno.model.Post;
import inno.model.PostMapper;
import inno.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimplePostRepository implements PostRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Post> findAll() {
        String SQL = "select * from Post";
        return jdbcTemplate.query(SQL,
                new PostMapper());
    }

    @Override
    public Post find(Long id) {
        String SQL = "select * from post where id = ?";
        return jdbcTemplate.queryForObject(SQL,
                new Object[]{id}, new PostMapper());

    }

    @Override
    public void add(Post post) {
        String SQL = "insert into post (title, text, date) values (?, ?, ?)";
        jdbcTemplate.update( SQL, post.getTitle(), post.getText(), post.getDate());
        System.out.println("Created Record Title = " + post.getTitle() + " text = " + post.getText() + " date = " + post.getDate());
    }

    @Override
    public void delete(Long id) {
        String SQL = "delete from post where id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
    }
}
