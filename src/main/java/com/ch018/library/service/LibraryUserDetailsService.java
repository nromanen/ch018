package com.ch018.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@Service
public class LibraryUserDetailsService implements UserDetailsService {

	@Autowired
	private PersonDao personDao;
	
	@Transactional
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		Person user = personDao.getByEmail(login);
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(user.getRole().toString()));
		User u = new User(user.getEmail(), user.getPassword(), user.getEmailConfirmed(), true,
				true, true, roles); 
		return u;
	}

}
