package inno.repository;

import inno.model.Users;

public interface UsersRepository{

    Users findById(Integer id);

    Users findByLogin(String login);

    boolean add(Users users);

    void update(Users users);

    boolean remove(Integer id);

}
