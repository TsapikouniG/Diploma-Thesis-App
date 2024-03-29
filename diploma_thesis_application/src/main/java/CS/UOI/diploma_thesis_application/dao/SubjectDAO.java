package CS.UOI.diploma_thesis_application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import CS.UOI.diploma_thesis_application.model.Professor;
import CS.UOI.diploma_thesis_application.model.Subject;


@Repository
public interface SubjectDAO extends JpaRepository<Subject, Integer> {

	public Subject findById(int theId);
	public List<Subject> findByProfessorId(int professorId);
}