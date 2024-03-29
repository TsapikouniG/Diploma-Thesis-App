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
import javax.transaction.Transactional;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




@Entity
@Table(name="student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="year_of_studies")
	private int yearOfStudies;
	
	@Column(name="current_average_grade")
	private double currentAverageGrade;
	
	@Column(name="number_of_remaining_courses")
	private int numberOfRemainingCourses;
	
	@Transient
	@OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
	
	
	@OneToMany(mappedBy="student")
	private List<Application> applications;
	
	// constructor
	public Student() {
		
	}
	
	public Student(int id, String fullName, int yearOfStudies,double currentAverageGrade,int numberOfRemainingCourses ) {
		this.id = id;
		this.fullName = fullName;
		this.yearOfStudies=yearOfStudies;
		this.currentAverageGrade=currentAverageGrade;
		this.numberOfRemainingCourses=numberOfRemainingCourses;

	}

	public Student(String fullName,int yearOfStudies,double currentAverageGrade,int numberOfRemainingCourses) {
		this.fullName = fullName;
		this.yearOfStudies=yearOfStudies;
		this.currentAverageGrade=currentAverageGrade;
		this.numberOfRemainingCourses=numberOfRemainingCourses;
	}
	// getters/setters
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


	public int getYearOfStudies() {
		return yearOfStudies;
	}

	public void setYearOfStudies(int yearOfStudies) {
		this.yearOfStudies = yearOfStudies;
	}
	
	public double getCurrentAverageGrade() {
		return currentAverageGrade;
	}

	public void setCurrentAverageGrade(double currentAverageGrade) {
		this.currentAverageGrade = currentAverageGrade;
	}
	
	public int getNumberOfRemainingCourses() {
		return numberOfRemainingCourses;
	}

	public void setNumberOfRemainingCourses(int numberOfRemainingCourses) {
		this.numberOfRemainingCourses = numberOfRemainingCourses;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public void setApplications(List<Application> applications2) {
		// TODO Auto-generated method stub
		
	}

	public List<Application> getApplications() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
