package CS.UOI.diploma_thesis_application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import CS.UOI.diploma_thesis_application.model.Application;

@Repository
public interface ApplicationDAO extends JpaRepository<Application, Integer> {
    
	Application findByStudentIdAndSubjectId(int studentId, int subjectId);

	@Query ("SELECT a.student.id FROM Application a WHERE a.subject.id = :subjectId")
	List<Integer> findStudentIdsBySubjectId(int subjectId);
	
}
