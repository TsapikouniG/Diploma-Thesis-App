package CS.UOI.diploma_thesis_application.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import CS.UOI.diploma_thesis_application.model.User;

@Service
public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
    public UserDetails loadUserByUsername(String username);
	User loadTheUserByUsername(String username) throws UsernameNotFoundException;
}
