package inno.repository;

import inno.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByLogin(String login);
}
