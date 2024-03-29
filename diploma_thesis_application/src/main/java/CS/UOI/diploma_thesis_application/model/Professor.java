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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="professor")
public class Professor 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="specialty")
	private String specialty;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
	
	@OneToMany(mappedBy="professor")
	private List<Subject> subjects;


	public Professor() 
	{
	
	}

	public Professor(int id, String fullName, String specialty ) 
	{
		this.id = id;
		this.fullName = fullName;
		this.specialty=specialty;
	}

	public Professor(String fullName, String specialty ) 
	{
		this.fullName = fullName;
		this.specialty=specialty;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public void setSubjects(List<Subject> subjects2) {
		// TODO Auto-generated method stub
		
	}

	public List<Subject> getSubjects() {
		// TODO Auto-generated method stub
		return null;
	}





}
