package inno.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "score")
@SequenceGenerator(sequenceName = "score_seq", name = "scoreSequence")
public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoreSequence")
    @Column(name = "id")
    private Integer id;
    @Column(name = "score")
    @Range(min = 1, max = 5, message = "Оценка должна входить в диапазон от 1 до 5!")
    private Integer score;

    @Column(name = "subject_type")
    @Enumerated(EnumType.ORDINAL)
    private SubjectType subjectType;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    public Score() {
    }

    public Score(Integer score, SubjectType subjectType, Student student) {
        this.score = score;
        this.subjectType = subjectType;
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
