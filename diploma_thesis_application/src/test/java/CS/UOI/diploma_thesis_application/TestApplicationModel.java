package CS.UOI.diploma_thesis_application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import CS.UOI.diploma_thesis_application.dao.StudentDAO;
import CS.UOI.diploma_thesis_application.dao.SubjectDAO;
import CS.UOI.diploma_thesis_application.model.Application;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;


@SpringBootTest
class TestApplicationModel {

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
        Application application = new Application();
        application.setId(expectedId);
        int actualId = application.getId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    void testGetAndSetSubject() {
        Subject expectedSubject = new Subject();
        Application application = new Application();
        application.setSubject(expectedSubject);
        Subject actualSubject = application.getSubject();
        Assertions.assertEquals(expectedSubject, actualSubject);
    }

    @Test
    void testGetAndSetStudent() {
        Student expectedStudent = new Student();
        Application application = new Application();
        application.setStudent(expectedStudent);
        Student actualStudent = application.getStudent();
        Assertions.assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void testConstructorWithId() {
        int expectedId = 1;
        Application application = new Application(expectedId);
        Assertions.assertEquals(expectedId, application.getId());
    }
}
