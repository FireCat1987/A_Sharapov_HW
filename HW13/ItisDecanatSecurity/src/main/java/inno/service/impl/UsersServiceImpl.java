package inno.service.impl;

import inno.model.Role;
import inno.model.SubjectType;
import inno.model.User;
import inno.repository.RoleRepository;
import inno.repository.UsersRepository;
import inno.service.UsersService;
import inno.utils.form.UserForm;
import inno.utils.transformers.UserFormTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Transactional(readOnly = true)
public class UsersServiceImpl implements UsersService {

    private static final String DEFAULT_ROLE_NAME = "ROLE_USER";
    private Role defaultRole;
    private SubjectType subject;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserFormTransformer transformer;

    @PostConstruct
    private void initialize() {
        defaultRole = roleRepository.findByName(DEFAULT_ROLE_NAME);
        subject = SubjectType.OTHER;
    }

    @Transactional
    @Override
    public void saveUser(UserForm form) {
        User user = transformer.toUser(form);
        user.getRoles().add(defaultRole);
        user.setSubject(subject);
        usersRepository.save(user);
    }
}
