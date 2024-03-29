package CS.UOI.diploma_thesis_application.service;

import java.util.List;

import CS.UOI.diploma_thesis_application.model.Application;
import CS.UOI.diploma_thesis_application.model.Professor;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;


public interface ProfessorService {
	

	public void saveProfile(Professor theStudent);
	public void deleteById(int theId);
	public Professor findByUserId(int userId);
	public void createProfile(Professor theProfessor);
	List<Subject> getSubjectsByProfessorId(int professorId);
	public void deleteSubject(int subjectId);
	public void addSubject(Subject theSubject);
	public List<Student> getStudentsBySubjectId(int subjectId);
	public void createThesis(Professor professor, Subject theSubject);
	public void assignThesis(int studentId, int professorId, int subjectId);
	
		
		
	
	}


