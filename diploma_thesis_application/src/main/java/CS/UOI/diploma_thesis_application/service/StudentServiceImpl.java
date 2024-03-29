package CS.UOI.diploma_thesis_application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CS.UOI.diploma_thesis_application.dao.ApplicationDAO;
import CS.UOI.diploma_thesis_application.dao.StudentDAO;
import CS.UOI.diploma_thesis_application.dao.SubjectDAO;
import CS.UOI.diploma_thesis_application.model.Application;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO StudentRepository;
	
	@Autowired
	private SubjectDAO subjectRepository;
	
	@Autowired ApplicationDAO applicationRepository;
	
	@Autowired
	public StudentServiceImpl(StudentDAO theStudentRepository, SubjectDAO theSubjectRepository, ApplicationDAO theApplicationRepository) {
		StudentRepository = theStudentRepository;
		subjectRepository = theSubjectRepository;
		applicationRepository = theApplicationRepository;
	}
	
	
	
	//method for retrieving a specific subject's
	@Override
	@Transactional
	public Subject findSubjectById(int theId) {
		
		Subject result = subjectRepository.findById(theId);
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find student id - " + theId);
		}
	}
	
	//Custom method using a  modifying query for editing specific information about a given student entity
	@Override
	@Transactional
	public void saveProfile(Student theStudent) {
		StudentRepository.saveProfile(theStudent.getId(), theStudent.getFullName(), theStudent.getYearOfStudies(), theStudent.getCurrentAverageGrade(), theStudent.getNumberOfRemainingCourses());
	}
	
	
	//CRUD operation responsible for creating a Student Entity
	@Override
	@Transactional
	public void createProfile(Student theStudent) {
		StudentRepository.save(theStudent);
	}
	
	@Override
	@Transactional
	public void deleteById(int theId) {
		StudentRepository.deleteById(theId);
	}
	
	//retrieving a student entity via his userId field
	@Override
	@Transactional
	public Student findByUserId(int userId) {
		return StudentRepository.findByUserId(userId);
		
	}
	
	//default JPA function for retrieving all the subjects entities
	@Override
	@Transactional
	public List<Subject> getSubjectList(){
		return subjectRepository.findAll();
	}
	
	//method for creating a new application entity and filling it with the appropriate information (studentId) and (subjectId)
	@Override
	@Transactional
	public void applyForSubject(int studentId, int subjectId) 
	{
		Application newApplication = new Application();
		newApplication.setStudent(StudentRepository.findById(studentId));
		newApplication.setSubject(subjectRepository.findById(subjectId));
		applicationRepository.save(newApplication);
	}
	
	//a method used for checking if a student entity (is already associated with a specific subject) has made an application for a specific subject
	@Override
	@Transactional
	public boolean hasApplied(int studentId,int subjectId) 
	{
		Application existingApplication = applicationRepository.findByStudentIdAndSubjectId(studentId, subjectId);
		if(existingApplication != null) {
			return true;
		}
		return false;
	}
	

	
}