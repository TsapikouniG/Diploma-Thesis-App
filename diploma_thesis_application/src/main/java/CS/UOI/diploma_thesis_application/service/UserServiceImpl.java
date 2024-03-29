package CS.UOI.diploma_thesis_application.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import CS.UOI.diploma_thesis_application.dao.UserDAO;
import CS.UOI.diploma_thesis_application.model.User;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Role;
import CS.UOI.diploma_thesis_application.model.Professor;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ProfessorService professorService;
	
	
	@Override
	public void saveUser(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDAO.save(user);
        ifStudentCreate(user);
        ifProfessorCreate(user);
    }


	@Override
	public boolean isUserPresent(User user) {
		Optional<User> storedUser = userDAO.findByUsername(user.getUsername());
		return storedUser.isPresent();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		 return userDAO.findByUsername(username).orElseThrow(
	                ()-> new UsernameNotFoundException(
	                        String.format("USER_NOT_FOUND", username)
	                ));
	}
	
	
	@Override
	public User loadTheUserByUsername(String username) throws UsernameNotFoundException 
	{
		 return userDAO.findByUsername(username).orElseThrow(
	                ()-> new UsernameNotFoundException(
	                        String.format("USER_NOT_FOUND", username)
	                ));
	}
	
	
	
	
	
	public void ifStudentCreate(User user) {
		if (user.getRole() == Role.STUDENT) 
		{
			Student student = new Student();
			student.setUser(user);
			this.studentService.createProfile(student);
		}
	}
	public void ifProfessorCreate(User user) {
		if (user.getRole() == Role.PROFESSOR) {
			Professor professor = new Professor();
			professor.setUser(user);
			this.professorService.createProfile(professor);
		}
	}
	
	public UserDAO getDAO () {
		return userDAO;
	}
	
}
