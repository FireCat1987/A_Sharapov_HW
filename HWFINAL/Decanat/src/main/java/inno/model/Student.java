package inno.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "students")
@SequenceGenerator(sequenceName = "students_seq", name = "studentsSequence")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentsSequence")
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    @NotEmpty
    private String firstname;
    @Column(name = "surname")
    @NotEmpty
    private String surname;
    @Column(name = "lastname")
    @NotEmpty
    private String lastname;
    @Column(name = "studgroup")
    @NotEmpty
    private String studGroup;


    @OneToMany(targetEntity = Score.class, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Score> scores;

    @ManyToOne
    @JoinColumn(name = "curator_id")
    private User user;
    public Student() {
    }

    public Student(String firstname, String surname, String lastname, String studGroup) {
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.studGroup = studGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStudGroup() {
        return studGroup;
    }

    public void setStudGroup(String studGroup) {
        this.studGroup = studGroup;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
