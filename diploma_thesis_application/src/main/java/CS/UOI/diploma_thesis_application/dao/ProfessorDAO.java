package CS.UOI.diploma_thesis_application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import CS.UOI.diploma_thesis_application.model.Professor;


@Repository
public interface ProfessorDAO extends JpaRepository<Professor, Integer> {
	
	Professor findById(int theId);
	
	public Professor findByUserId(@Param("userId")int userId);
	
	@Modifying
	@Query("update Professor p set p.fullName = :fullName, p.specialty = :specialty where p.id = :id")
	 void saveProfile(@Param("id")int id,@Param("fullName") String fullName,@Param("specialty") String specialty);
}
