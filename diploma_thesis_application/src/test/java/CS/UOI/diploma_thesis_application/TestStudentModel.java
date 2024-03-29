package CS.UOI.diploma_thesis_application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import CS.UOI.diploma_thesis_application.dao.UserDAO;
import CS.UOI.diploma_thesis_application.model.Application;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.User;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class TestStudentModel {

    @Mock
    private UserDAO userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        int expectedId = 1;
        Student student = new Student();
        student.setId(expectedId);
        int actualId = student.getId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    void testGetFullName() {
        String expectedFullName = "John Doe";
        Student student = new Student();
        student.setFullName(expectedFullName);
        String actualFullName = student.getFullName();
        Assertions.assertEquals(expectedFullName, actualFullName);
    }

    @Test
    void testGetYearOfStudies() {
        int expectedYearOfStudies = 3;
        Student student = new Student();
        student.setYearOfStudies(expectedYearOfStudies);
        int actualYearOfStudies = student.getYearOfStudies();
        Assertions.assertEquals(expectedYearOfStudies, actualYearOfStudies);
    }

    @Test
    void testGetCurrentAverageGrade() {
        double expectedCurrentAverageGrade = 85.5;
        Student student = new Student();
        student.setCurrentAverageGrade(expectedCurrentAverageGrade);
        double actualCurrentAverageGrade = student.getCurrentAverageGrade();
        Assertions.assertEquals(expectedCurrentAverageGrade, actualCurrentAverageGrade, 0.001);
    }

    @Test
    void testGetNumberOfRemainingCourses() {
        int expectedNumberOfRemainingCourses = 5;
        Student student = new Student();
        student.setNumberOfRemainingCourses(expectedNumberOfRemainingCourses);
        int actualNumberOfRemainingCourses = student.getNumberOfRemainingCourses();
        Assertions.assertEquals(expectedNumberOfRemainingCourses, actualNumberOfRemainingCourses);
    }

    @Test
    void testGetAndSetUser() {
        User expectedUser = new User();
        Student student = new Student();
        student.setUser(expectedUser);
        User actualUser = student.getUser();
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void testConstructorWithArguments() {
        int expectedId = 1;
        String expectedFullName = "John Doe";
        int expectedYearOfStudies = 3;
        double expectedCurrentAverageGrade = 85.5;
        int expectedNumberOfRemainingCourses = 5;

        Student student = new Student(expectedId, expectedFullName, expectedYearOfStudies,
                expectedCurrentAverageGrade, expectedNumberOfRemainingCourses);

        Assertions.assertEquals(expectedId, student.getId());
        Assertions.assertEquals(expectedFullName, student.getFullName());
        Assertions.assertEquals(expectedYearOfStudies, student.getYearOfStudies());
        Assertions.assertEquals(expectedCurrentAverageGrade, student.getCurrentAverageGrade(), 0.001);
        Assertions.assertEquals(expectedNumberOfRemainingCourses, student.getNumberOfRemainingCourses());
    }

    @Test
    void testApplicationsList() {
        Application application1 = new Application();
        Application application2 = new Application();

        List<Application> applications = new ArrayList<>();
        applications.add(application1);
        applications.add(application2);

        Student student = new Student();
        student.setApplications(applications);

        List<Application> actualApplications = student.getApplications();

        Assertions.assertEquals(applications, actualApplications);
    }
}
