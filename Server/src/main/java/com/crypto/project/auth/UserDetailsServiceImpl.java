package com.crypto.project.auth;


import org.dom4j.util.UserDataAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crypto.project.controller.UsersController;
import com.crypto.project.model.ApplicationUser;
import com.crypto.project.model.Users;
import com.crypto.project.service.ApplicationUserRepository;
import com.crypto.project.service.UsersRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private ApplicationUserRepository applicationUserRepository;
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private UsersController userCtrl;
    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
    	Optional<ApplicationUser> userOptionall =  applicationUserRepository.findByUsername(username);
        ApplicationUser applicationUser = userOptionall.get();
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        Users userData=null;
        try
        {
        userData=userRepository.findByUserId(username).get();
        }catch (Exception e) {
			// TODO: handle exception
		}
        ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority(applicationUser.getRole()));
        return new CustomSpringUser(applicationUser.getUsername(), applicationUser.getPassword(), authList,userData.getUserNumId());
    }
    
   void store(String token,Long userNumId){
    	userCtrl.store(token, userNumId);
    }
   
   void logout(String token,Long userNumId){
   		userCtrl.logout(userNumId);
   }
}