package CS.UOI.diploma_thesis_application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import CS.UOI.diploma_thesis_application.dao.ProfessorDAO;
import CS.UOI.diploma_thesis_application.dao.StudentDAO;
import CS.UOI.diploma_thesis_application.dao.SubjectDAO;
import CS.UOI.diploma_thesis_application.model.Professor;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;
import CS.UOI.diploma_thesis_application.model.Thesis;


@SpringBootTest
class TestThesisModel {

    @Mock
    private ProfessorDAO professorRepository;

    @Mock
    private SubjectDAO subjectRepository;

    @Mock
    private StudentDAO studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        int expectedId = 1;
        Thesis thesis = new Thesis();
        thesis.setId(expectedId);
        int actualId = thesis.getId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    void testGetAndSetProfessor() {
        Professor expectedProfessor = new Professor();
        Thesis thesis = new Thesis();
        thesis.setProfessor(expectedProfessor);
        Professor actualProfessor = thesis.getProfessor();
        Assertions.assertEquals(expectedProfessor, actualProfessor);
    }

    @Test
    void testGetAndSetSubject() {
        Subject expectedSubject = new Subject();
        Thesis thesis = new Thesis();
        thesis.setSubject(expectedSubject);
        Subject actualSubject = thesis.getSubject();
        Assertions.assertEquals(expectedSubject, actualSubject);
    }

    @Test
    void testGetAndSetStudent() {
        Student expectedStudent = new Student();
        Thesis thesis = new Thesis();
        thesis.setStudent(expectedStudent);
        Student actualStudent = thesis.getStudent();
        Assertions.assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void testConstructorWithId() {
        int expectedId = 1;
        Thesis thesis = new Thesis(expectedId);
        Assertions.assertEquals(expectedId, thesis.getId());
    }

    @Test
    void testConstructorWithIdProfessorAndSubject() {
        int expectedId = 1;
        Professor expectedProfessor = new Professor();
        Subject expectedSubject = new Subject();
        Thesis thesis = new Thesis(expectedId, expectedProfessor, expectedSubject);
        Assertions.assertEquals(expectedId, thesis.getId());
        Assertions.assertEquals(expectedProfessor, thesis.getProfessor());
        Assertions.assertEquals(expectedSubject, thesis.getSubject());
    }
}
