package inno.repository.impl;

import inno.model.Post;
import inno.repository.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Repository
public class SimplePostRepository implements PostRepository {

    private List<Post> posts = new ArrayList<>();

    {
        posts.add(new Post("Новость 1", " текст первой новости"));
        posts.add(new Post("Новость 2", " текст первой пока новости"));
        posts.add(new Post("Новость 3", " текст привет первой новости"));
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Post find(Long id) {
        return posts.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst().orElse(null);
    }

    @Override
    public boolean add(Post post) {
        return posts.add(post);
    }

    @Override
    public boolean delete(Long id) {
        return posts.removeIf(p -> Objects.equals(p.getId(), id));
    }
}
