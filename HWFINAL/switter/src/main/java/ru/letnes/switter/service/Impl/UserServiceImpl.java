package ru.letnes.switter.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.letnes.switter.entity.Role;
import ru.letnes.switter.entity.User;
import ru.letnes.switter.repository.RoleRepository;
import ru.letnes.switter.repository.UserRepository;
import ru.letnes.switter.service.UserService;
import ru.letnes.switter.utils.form.UserForm;
import ru.letnes.switter.utils.transformers.UserFormTransformer;

import javax.annotation.PostConstruct;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_ROLE_NAME = "ROLE_USER";
    private Role defaultRole;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserFormTransformer transformer;

    @PostConstruct
    private void initialize() {
        defaultRole = roleRepository.findByName(DEFAULT_ROLE_NAME);
    }

    @Transactional
    @Override
    public void saveUser(UserForm form) {
        User user = transformer.toUser(form);
        user.getRoles().add(defaultRole);
        userRepository.save(user);
    }
}