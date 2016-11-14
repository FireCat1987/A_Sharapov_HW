package inno.repository;

import inno.model.Post;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


public interface PostRepository {

    List<Post> findAll();

    Post find(Long id);

    void add(Post post);

    void delete(Long id);
}
