package CS.UOI.diploma_thesis_application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import CS.UOI.diploma_thesis_application.controller.StudentController;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;
import CS.UOI.diploma_thesis_application.model.User;
import CS.UOI.diploma_thesis_application.service.StudentService;
import CS.UOI.diploma_thesis_application.service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

class TestStudentController {

    @Mock
    private StudentService studentService;

    @Mock
    private UserService userService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStudentProfile() {
        // Mock authentication
        User user = new User();
        user.setId(1);

        // Mock studentService.findByUserId() to return a student
        Student student = new Student();
        when(userService.loadTheUserByUsername(anyString())).thenReturn(user);
        when(studentService.findByUserId(eq(user.getId()))).thenReturn(student);

        // Create a model object to pass as an argument
        Model model = mock(Model.class);

        // Call the method to test
        Student result = studentController.studentProfile(model);

        // Verify that the studentService.findByUserId() is called once
        verify(studentService, times(1)).findByUserId(eq(user.getId()));

        // Verify that the model.addAttribute() is called once with the correct attributes
        verify(model, times(1)).addAttribute(eq("student"), eq(student));

        // Perform assertions on the result
        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    void testSaveStudent() {
        // Create a student object
        Student student = new Student();

        // Call the method to test
        String result = studentController.saveStudent(student);

        // Verify that the studentService.saveProfile() is called once with the correct student object
        verify(studentService, times(1)).saveProfile(eq(student));

        // Perform assertions on the result
        assertEquals("student/dashboard", result);
    }

    @Test
    void testGetSubjectList() {
        // Mock studentService.getSubjectList() to return a list of subjects
        List<Subject> subjects = new ArrayList<>();
        when(studentService.getSubjectList()).thenReturn(subjects);

        // Create a model object to pass as an argument
        Model model = mock(Model.class);

        // Call the method to test
        String result = studentController.getSubjectList(model);

        // Verify that the studentService.getSubjectList() is called once
        verify(studentService, times(1)).getSubjectList();

        // Verify that the model.addAttribute() is called once with the correct attributes
        verify(model, times(1)).addAttribute(eq("subjects"), eq(subjects));

        // Perform assertions on the result
        assertEquals("student/subjects", result);
    }

    @Test
    void testShowSubjectInfo() {
        // Mock studentService.findSubjectById() to return a subject
        int subjectId = 1;
        Subject subject = new Subject();
        when(studentService.findSubjectById(eq(subjectId))).thenReturn(subject);

        // Mock authentication
        User user = new User();
        user.setId(1);
        when(userService.loadTheUserByUsername(anyString())).thenReturn(user);

        // Mock studentService.findByUserId() to return a student
        Student student = new Student();
        when(studentService.findByUserId(eq(user.getId()))).thenReturn(student);

        // Create a model object to pass as an argument
        Model model = mock(Model.class);

        // Call the method to test
        String result = studentController.showSubjectInfo(subjectId, model);

        // Verify that the studentService.findSubjectById() is called once with the correct subjectId
        verify(studentService, times(1)).findSubjectById(eq(subjectId));

        // Verify that the userService.loadTheUserByUsername() is called once with the correct username
        verify(userService, times(1)).loadTheUserByUsername(anyString());

        // Verify that the studentService.findByUserId() is called once with the correct userId
        verify(studentService, times(1)).findByUserId(eq(user.getId()));

        // Verify that the model.addAttribute() is called twice with the correct attributes
        verify(model, times(1)).addAttribute(eq("student"), eq(student));
        verify(model, times(1)).addAttribute(eq("subject"), eq(subject));

        // Perform assertions on the result
        assertEquals("student/subject-details", result);
    }

    @Test
    void testApplyForSubject() {
        // Mock the parameters
        int studentId = 1;
        int subjectId = 1;

        // Mock studentService.hasApplied() to return false
        when(studentService.hasApplied(eq(studentId), eq(subjectId))).thenReturn(false);

        // Create a redirectAttributes object
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        // Call the method to test
        String result = studentController.applyForSubject(studentId, subjectId, redirectAttributes);

        // Verify that the studentService.hasApplied() is called once with the correct parameters
        verify(studentService, times(1)).hasApplied(eq(studentId), eq(subjectId));

        // Verify that the studentService.applyForSubject() is called once with the correct parameters
        verify(studentService, times(1)).applyForSubject(eq(studentId), eq(subjectId));

        // Verify that the redirectAttributes.addFlashAttribute() is called once with the correct message
        verify(redirectAttributes, times(1)).addFlashAttribute(eq("message"), eq("Your application has been submitted."));

        // Perform assertions on the result
        assertEquals("redirect:/student/dashboard", result);
    }
}
