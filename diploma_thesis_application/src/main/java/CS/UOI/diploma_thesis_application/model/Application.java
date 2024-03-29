package CS.UOI.diploma_thesis_application.model;

import java.util.Collection;
import java.util.Collections;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="application")
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
    @JoinColumn(name="subject_id")
	private Subject subject;
	
	@ManyToOne
    @JoinColumn(name="student_id")
	private Student student;


	public Application() 
	{
	
	}

	public Application(int id ) 
	{
		this.id = id;
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
	
	public void setSubject(Subject subject) {
		this.subject=subject;
	}
	
	public void setStudent(Student student) {
		this.student=student;
	}
}
