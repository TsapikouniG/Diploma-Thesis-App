package CS.UOI.diploma_thesis_application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import CS.UOI.diploma_thesis_application.dao.ProfessorDAO;
import CS.UOI.diploma_thesis_application.model.Application;
import CS.UOI.diploma_thesis_application.model.Professor;
import CS.UOI.diploma_thesis_application.model.Subject;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class TestSubjectModel {

    @Mock
    private ProfessorDAO professorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        int expectedId = 1;
        Subject subject = new Subject();
        subject.setId(expectedId);
        int actualId = subject.getId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    void testGetTitle() {
        String expectedTitle = "Psifiaki";
        Subject subject = new Subject();
        subject.setTitle(expectedTitle);
        String actualTitle = subject.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testGetObjectives() {
        String expectedObjectives = "Flipflops,Vhdl";
        Subject subject = new Subject();
        subject.setObjectives(expectedObjectives);
        String actualObjectives = subject.getObjectives();
        Assertions.assertEquals(expectedObjectives, actualObjectives);
    }

    @Test
    void testGetAndSetProfessor() {
        Professor expectedProfessor = new Professor();
        Subject subject = new Subject();
        subject.setProfessor(expectedProfessor);
        Professor actualProfessor = subject.getProfessor();
        Assertions.assertEquals(expectedProfessor, actualProfessor);
    }

    @Test
    void testConstructorWithArguments() {
        int expectedId = 1;
        String expectedTitle = "Psifiaki";
        String expectedObjectives = "Flipflops,Vhdl";

        Subject subject = new Subject(expectedId, expectedTitle, expectedObjectives);

        Assertions.assertEquals(expectedId, subject.getId());
        Assertions.assertEquals(expectedTitle, subject.getTitle());
        Assertions.assertEquals(expectedObjectives, subject.getObjectives());
    }

    @Test
    void testApplicationsList() {
        Application application1 = new Application();
        Application application2 = new Application();

        List<Application> applications = new ArrayList<>();
        applications.add(application1);
        applications.add(application2);

        Subject subject = new Subject();
        subject.setApplications(applications);

        List<Application> actualApplications = subject.getApplications();

        Assertions.assertEquals(applications, actualApplications);
    }
}
