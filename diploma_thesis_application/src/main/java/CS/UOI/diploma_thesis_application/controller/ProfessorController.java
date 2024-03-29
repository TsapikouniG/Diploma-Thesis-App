package CS.UOI.diploma_thesis_application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CS.UOI.diploma_thesis_application.model.Application;
import CS.UOI.diploma_thesis_application.model.Professor;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;
import CS.UOI.diploma_thesis_application.model.Thesis;
import CS.UOI.diploma_thesis_application.model.User;
import CS.UOI.diploma_thesis_application.service.ProfessorService;
import CS.UOI.diploma_thesis_application.service.StudentService;
import CS.UOI.diploma_thesis_application.service.UserService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public ProfessorController(ProfessorService theProfessorService,UserService theUserService) {
		professorService=theProfessorService;
		userService=theUserService;
	
	}
	
	
	//method that returns the dashboard html file
    @RequestMapping("/dashboard")
    public String getProfessorHome()
    {
    	return "professor/dashboard";
    }
    
	//method for retrieving the professor's credentials from the database
    @RequestMapping("/profile")
	public Professor professorProfile(Model theModel) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User thisUser = userService.loadTheUserByUsername(authentication.getName());
		int userId = thisUser.getId();
		Professor professor = professorService.findByUserId(userId);
		return professor;
	}
    
  //method for updating the professor's credentials from the database
    @PostMapping("/profile/save")
	public String saveProfessor(@ModelAttribute("professor") Professor theProfessor) 
	{
		professorService.saveProfile(theProfessor);
		return "professor/dashboard";
	}
    
    //method that will list the professor's own subjects
    @RequestMapping("/professor-subjects")
    public String listProfessorSubjects(Model theModel) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User thisUser = userService.loadTheUserByUsername(authentication.getName());
		int userId = thisUser.getId();
		Professor professor = professorService.findByUserId(userId);
    	List<Subject> theSubjects = professorService.getSubjectsByProfessorId(professor.getId());
		theModel.addAttribute("subjectList",theSubjects);
    	return "professor/professor-subjects";
    	
    }
    //method that deletes a subject
    @RequestMapping("/delete")
	public String deleteSubject(@RequestParam("subjectId") int theId) {
		
		// delete the subject
		professorService.deleteSubject(theId);
		// redirect to /employees/list ACTION
		return "redirect:/professor/professor-subjects";
    }
	
    //method that returns the subject-addForm html file
    @RequestMapping("/showAddForm")
    public String showFormForAddSubject(Model theModel) {
    	Subject newSubject = new Subject();
    	
    	theModel.addAttribute("subject", newSubject);
    	
    	return "professor/subject-addForm";
    }
    
    
    //POST method that saves the subject to the database after filling the appropriate fields
    @PostMapping("/save")
    public String saveSubject(@ModelAttribute("subject")Subject theSubject)
    {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User thisUser = userService.loadTheUserByUsername(authentication.getName());
		int userId = thisUser.getId();
		Professor professor = professorService.findByUserId(userId);
    	theSubject.setProfessor(professor);
    	professorService.addSubject(theSubject);
    	professorService.createThesis(professor, theSubject);
   
    	
    	return "redirect:/professor/professor-subjects";
    }
    
    // GET method for viewing applications made by students for a specific subject
    @GetMapping("/applications")
    public String viewApplications(@RequestParam("subjectId") int subjectId, Model model) {
        // Logic to fetch applications for the given subjectId
        List<Student> students = professorService.getStudentsBySubjectId(subjectId);
        model.addAttribute("applications", students);
        model.addAttribute("subjectId", subjectId);
        return "professor/applicants"; 
    }
    
    // POST method that a professor will assign a student (studentId) to a specific Thesis subject
    @PostMapping("/assign")
    public String assignThesis(@RequestParam("studentId") int studentId, @RequestParam("subjectId") int subjectId)
      {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User thisUser = userService.loadTheUserByUsername(authentication.getName());
		int userId = thisUser.getId();
		Professor professor = professorService.findByUserId(userId);
		int professorId = professor.getId();
		professorService.assignThesis(studentId, professorId, subjectId);
        
        // Redirect to the appropriate page or return a success message
        return "redirect:/professor/dashboard"; // Replace with the desired redirect URL
    }

    
    
    
}
