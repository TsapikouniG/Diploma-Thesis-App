package CS.UOI.diploma_thesis_application.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
@Table(name="subject")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="objectives")
	private String objectives;
	
	@ManyToOne
    @JoinColumn(name="professor_id")
	private Professor professor;
	
	@OneToMany(mappedBy="subject",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Application> applications;
	
	public Subject() 
	{
	
	}

	public Subject(int id, String title, String objectives ) 
	{
		this.id = id;
		this.title = title;
		this.objectives=objectives;

	}

	public Subject(String title, String objectives ) 
	{
		this.title = title;
		this.objectives=objectives;

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor theProfessor) {
		this.professor=theProfessor;
	}

	public void setApplications(List<Application> applications2) {
		// TODO Auto-generated method stub
		
	}

	public List<Application> getApplications() {
		// TODO Auto-generated method stub
		return null;
	}
}
