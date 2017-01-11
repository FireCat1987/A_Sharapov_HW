package ru.letnes.switter.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.letnes.switter.entity.Post;
import ru.letnes.switter.entity.User;
import ru.letnes.switter.repository.PostRepository;
import ru.letnes.switter.security.SecurityUtils;
import ru.letnes.switter.service.PostService;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository repository;

    @Transactional
    @Override
    public void savePost(Post post) {
        User user = SecurityUtils.getCurrentUser();
        post.setUser(user);
        // TODO использовать PostForm
        repository.save(post);
    }
}