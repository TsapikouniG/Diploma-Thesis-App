package CS.UOI.diploma_thesis_application.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import CS.UOI.diploma_thesis_application.model.Professor;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;
import CS.UOI.diploma_thesis_application.model.User;
import CS.UOI.diploma_thesis_application.service.StudentService;
import CS.UOI.diploma_thesis_application.service.UserService;

@Controller
@RequestMapping("/student")
public class StudentController 
{
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public StudentController(StudentService theStudentService,UserService theUserService) {
		studentService=theStudentService;
		userService=theUserService;
	}
	
	//method that returns the dashboard html file
	@RequestMapping("/dashboard")
	public String studentDashboard() {
		return ("student/dashboard");
	}
   
	//method for retrieving the student's credentials from the database
	@RequestMapping("/profile")
	public Student studentProfile(Model theModel) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User thisUser = userService.loadTheUserByUsername(authentication.getName());
		int userId = thisUser.getId();
		Student student = studentService.findByUserId(userId);
		return student;
	}
	//method for parsing the updated student's credentials to the database
	@PostMapping("/profile/save")
	public String saveStudent(@ModelAttribute("student") Student theStudent) 
	{
		studentService.saveProfile(theStudent);
		return "student/dashboard";
	}
	
	//method for viewing all the subjects from the database
	@RequestMapping("/subjects")
	public String getSubjectList(Model theModel){
		List<Subject> theSubjects = studentService.getSubjectList();
		theModel.addAttribute("subjects",theSubjects);
		return "student/subjects";
	}
	
	//method when a student wants to look more details regarding a subject using dynamic mapping
	@RequestMapping("/subject-details/{id}")
	public String showSubjectInfo(@PathVariable("id") int theId,Model theModel) {
		Subject theSubject = studentService.findSubjectById(theId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User thisUser = userService.loadTheUserByUsername(authentication.getName());
		int userId = thisUser.getId();
		Student student = studentService.findByUserId(userId);
		theModel.addAttribute("student", student);
		theModel.addAttribute("subject",theSubject);
		return "student/subject-details";
	}
	
	// POST method called when a student wants to apply for a specific subject
	@PostMapping("/apply")
	public String applyForSubject(@RequestParam("studentId") int studentId, @RequestParam("subjectId") int subjectId,RedirectAttributes redirectAttributes) {
		boolean hasApplied = studentService.hasApplied(studentId, subjectId);
		if (hasApplied) 
		{
	        redirectAttributes.addFlashAttribute("message", "You have already applied for this subject.");
		}
		else 
		{
			studentService.applyForSubject(studentId, subjectId);
	        redirectAttributes.addFlashAttribute("message", "Your application has been submitted.");

		}
		System.out.println("Message: " + redirectAttributes.getFlashAttributes().get("message"));
		return "redirect:/student/dashboard";
	}
	
}
