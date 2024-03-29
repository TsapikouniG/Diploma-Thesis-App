package CS.UOI.diploma_thesis_application.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CS.UOI.diploma_thesis_application.dao.ApplicationDAO;
import CS.UOI.diploma_thesis_application.dao.ProfessorDAO;
import CS.UOI.diploma_thesis_application.dao.StudentDAO;
import CS.UOI.diploma_thesis_application.dao.SubjectDAO;
import CS.UOI.diploma_thesis_application.dao.ThesisDAO;
import CS.UOI.diploma_thesis_application.model.Application;
import CS.UOI.diploma_thesis_application.model.Professor;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;
import CS.UOI.diploma_thesis_application.model.Thesis;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorDAO ProfessorRepository;
	
	@Autowired
	private SubjectDAO subjectRepository;
	
	@Autowired
	private ApplicationDAO applicationRepository;
	
	@Autowired
	private StudentDAO studentRepository;
	
	@Autowired
	private ThesisDAO thesisRepository;
	
	@Autowired
	public ProfessorServiceImpl(ProfessorDAO theProfessorRepository, SubjectDAO theSubjectRepository,ApplicationDAO theApplicationRepository,StudentDAO theStudentRepository,ThesisDAO theThesisRepository) {
		ProfessorRepository = theProfessorRepository;
		subjectRepository= theSubjectRepository;
		applicationRepository = theApplicationRepository;
		studentRepository = theStudentRepository;
		thesisRepository = theThesisRepository;
		
	}
	
	
	
	
	
	//CRUD function when wanting to create a new professor entity
	@Override
	@Transactional
	public void createProfile(Professor theProfessor) {
		ProfessorRepository.save(theProfessor);
	}

	
	@Override
	@Transactional
	public void deleteById(int theId) {
		ProfessorRepository.deleteById(theId);
	}
	
	//Custom query that returns a professor entity through the userId field
	@Override
	@Transactional
	public Professor findByUserId(int userId) 
	{
		return ProfessorRepository.findByUserId(userId);
	}
	
	//custom modifying query that only affects certain fields of a professor entity
	@Override
	@Transactional
	public void saveProfile(Professor theProfessor) 
	{
		ProfessorRepository.saveProfile(theProfessor.getId(), theProfessor.getFullName(), theProfessor.getSpecialty());
	}

	
	//Returns a List of Subject entites that have a the given professor ID in their row
	@Override
	@Transactional
	public List<Subject> getSubjectsByProfessorId(int professorId) {
		return subjectRepository.findByProfessorId(professorId);
	}
	
	//CRUD function when a professor wants to delete a subject
	@Override
	@Transactional
	public void deleteSubject(int subjectId) {
		subjectRepository.deleteById(subjectId);
	}
	
	//CRUD operation when a professor wants to create a subject entity
	@Override
	@Transactional
	public void addSubject(Subject theSubject) {
		subjectRepository.save(theSubject);
	
	}
	
	//CRUD operation when a professor creates a subject, it also creates  a Thesis entity with assigned subject ID , professor ID, but with a temporary NULL student ID field
	@Override
	@Transactional
	public void createThesis(Professor professor ,Subject theSubject) {
		Thesis thesis = new Thesis();
		thesis.setProfessor(professor);
		thesis.setSubject(theSubject);
		thesisRepository.save(thesis);
	}

	//a method that returns a list of student entities through the Application entity  Repository
	@Override
	@Transactional
	public List<Student> getStudentsBySubjectId(int subjectId) {
		
		List<Integer> studentIds = applicationRepository.findStudentIdsBySubjectId(subjectId);

		return studentRepository.findStudentsByIds(studentIds);
	
	}
	
	//custom modifying query that modifies the appropriate Thesis entity with a studentId (Student)
	@Override
	@Transactional
	public void assignThesis(int studentId,int professorId,int subjectId)  {
	        
	      thesisRepository.assignStudentId(professorId, subjectId, studentId);
	    
	}
}