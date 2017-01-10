package inno.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "scores")
@SequenceGenerator(sequenceName = "scores_seq", name = "scoresSequence")
public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoresSequence")
    @Column(name = "id")
    private Integer id;
    @Column(name = "score")
    @Range(min = 1, max = 5, message = "Оценка должна входить в диапазон от 1 до 5!")
    @NotNull(message = "Нужно выставить оценку")
    private Integer score;

    @Column(name = "subject_type")
    @Enumerated(EnumType.ORDINAL)
    private SubjectType subjectType;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    public Score() {
    }

    public Score(Integer score, SubjectType subjectType) {
        this.score = score;
        this.subjectType = subjectType;
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
