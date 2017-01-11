package ru.letnes.switter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.letnes.switter.entity.Comment;


import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAll();

    Comment find(Long id);

    boolean add(Comment comment);

    boolean remove(Long id);

    List<Comment> findByPostId(Long postId);

}