package inno.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@SequenceGenerator(sequenceName = "users_seq", name = "usersSequence")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersSequence")
    @Column(name = "id")
    private Integer id;

    @Column(name = "login", unique = true, nullable = false)
    @NotEmpty(message = "Логин не может быть пустым")
    private String login;
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    @Column(name = "subject")
    @Enumerated(EnumType.ORDINAL)
    private SubjectType subject;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Student> students;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id",
                    nullable = false)})
    private Set<Role> roles;

    public User() {
    }

    public User(String login, String password) {
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Set<Role> getRoles() {
        if (roles == null) {
            roles = new HashSet<>();
        }
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public SubjectType getSubject() {
        return subject;
    }

    public void setSubject(SubjectType subject) {
        this.subject = subject;
    }
}
