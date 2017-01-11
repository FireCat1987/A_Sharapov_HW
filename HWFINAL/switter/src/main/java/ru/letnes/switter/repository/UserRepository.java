package ru.letnes.switter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.letnes.switter.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}