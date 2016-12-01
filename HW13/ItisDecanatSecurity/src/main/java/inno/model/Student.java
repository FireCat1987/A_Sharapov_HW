package inno.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static org.hibernate.annotations.OnDeleteAction.CASCADE;

@Entity
@Table(name = "student")
@SequenceGenerator(sequenceName = "student_seq", name = "studentSequence")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSequence")
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
    private String studgroup;
    @OneToMany(targetEntity = Score.class, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Score> scores;

    public Student() {
    }

    public Student(String firstname, String surname, String lastname, String studgroup) {
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.studgroup = studgroup;
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

    public String getStudgroup() {
        return studgroup;
    }

    public void setStudgroup(String studgroup) {
        this.studgroup = studgroup;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
