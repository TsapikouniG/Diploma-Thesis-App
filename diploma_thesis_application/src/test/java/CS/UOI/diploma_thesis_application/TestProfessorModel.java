package CS.UOI.diploma_thesis_application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import CS.UOI.diploma_thesis_application.dao.UserDAO;
import CS.UOI.diploma_thesis_application.model.Professor;
import CS.UOI.diploma_thesis_application.model.Subject;
import CS.UOI.diploma_thesis_application.model.User;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class TestProfessorModel {

    @Mock
    private UserDAO userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        int expectedId = 1;
        Professor professor = new Professor();
        professor.setId(expectedId);
        int actualId = professor.getId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    void testGetFullName() {
        String expectedFullName = "John Doe";
        Professor professor = new Professor();
        professor.setFullName(expectedFullName);
        String actualFullName = professor.getFullName();
        Assertions.assertEquals(expectedFullName, actualFullName);
    }

    @Test
    void testGetSpecialty() {
        String expectedSpecialty = "Computer Science";
        Professor professor = new Professor();
        professor.setSpecialty(expectedSpecialty);
        String actualSpecialty = professor.getSpecialty();
        Assertions.assertEquals(expectedSpecialty, actualSpecialty);
    }

    @Test
    void testGetAndSetUser() {
        User expectedUser = new User();
        Professor professor = new Professor();
        professor.setUser(expectedUser);
        User actualUser = professor.getUser();
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void testConstructorWithArguments() {
        int expectedId = 1;
        String expectedFullName = "John Doe";
        String expectedSpecialty = "Computer Science";

        Professor professor = new Professor(expectedId, expectedFullName, expectedSpecialty);

        Assertions.assertEquals(expectedId, professor.getId());
        Assertions.assertEquals(expectedFullName, professor.getFullName());
        Assertions.assertEquals(expectedSpecialty, professor.getSpecialty());
    }

    @Test
    void testSubjectsList() {
        Subject subject1 = new Subject();
        Subject subject2 = new Subject();

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);

        Professor professor = new Professor();
        professor.setSubjects(subjects);

        List<Subject> actualSubjects = professor.getSubjects();

        Assertions.assertEquals(subjects, actualSubjects);
    }
}
