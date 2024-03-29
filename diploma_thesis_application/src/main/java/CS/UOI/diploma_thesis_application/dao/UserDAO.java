package CS.UOI.diploma_thesis_application.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import CS.UOI.diploma_thesis_application.model.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
	
}
