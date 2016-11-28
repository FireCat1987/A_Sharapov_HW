package inno.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@SequenceGenerator(sequenceName = "users_seq", name = "usersSequence")
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersSequence")
    @Column(name = "id")
    private Integer id;

    @Column(name = "login",unique = true)
    @NotEmpty(message = "Логин не может быть пустым")
    private String login;
    @Column(name = "password")
    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    public Users() {
    }

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
