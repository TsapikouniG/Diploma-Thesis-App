package CS.UOI.diploma_thesis_application.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import CS.UOI.diploma_thesis_application.model.Thesis;

@Repository
public interface ThesisDAO extends JpaRepository<Thesis, Integer> {
    @Modifying
    @Query("UPDATE Thesis t SET t.student.id = :studentId WHERE t.professor.id = :professorId AND t.subject.id = :subjectId")
    void assignStudentId(@Param("professorId") int professorId, @Param("subjectId") int subjectId, @Param("studentId") int studentId);
}
