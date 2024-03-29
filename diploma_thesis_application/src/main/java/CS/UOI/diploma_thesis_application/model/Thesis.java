package CS.UOI.diploma_thesis_application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "thesis", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "subject_id"}))
public class Thesis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="professor_id")
	private Professor professor;
	
	@OneToOne
    @JoinColumn(name="subject_id")
	private Subject subject;
	
	@OneToOne
    @JoinColumn(name="student_id")
	private Student student;


	public Thesis() 
	{
	
	}

	public Thesis(int id ) 
	{
		this.id = id;
	}
	public Thesis(int id,Professor professor,Subject subject) 
	{
		this.id = id;
		this.professor = professor;
		this.subject = subject;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setSubject(Subject subject) {
		this.subject=subject;
	}
	
	public void setStudent(Student student) {
		this.student=student;
	}
	public void setProfessor(Professor theProfessor) {
		this.professor=theProfessor;
	}
}
