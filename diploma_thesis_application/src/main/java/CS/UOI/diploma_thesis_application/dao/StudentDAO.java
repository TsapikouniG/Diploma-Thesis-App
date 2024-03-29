package CS.UOI.diploma_thesis_application.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import CS.UOI.diploma_thesis_application.model.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
	public Student findById(int theId);
	
	
	public Student findByUserId(@Param("userId")int userId);
	
	
	@Modifying
	@Query("update Student s set s.fullName = :fullName, s.yearOfStudies = :yearOfStudies, s.currentAverageGrade = :currentAverageGrade, s.numberOfRemainingCourses = :numberOfRemainingCourses where s.id = :id")
	void saveProfile(@Param("id") int id, @Param("fullName") String fullName, @Param("yearOfStudies") int yearOfStudies, @Param("currentAverageGrade") double currentAverageGrade, @Param("numberOfRemainingCourses") int numberOfRemainingCourses);

	
	@Query("SELECT s FROM Student s WHERE s.id IN :studentIds")
	public List<Student> findStudentsByIds(List<Integer> studentIds);
	
}
