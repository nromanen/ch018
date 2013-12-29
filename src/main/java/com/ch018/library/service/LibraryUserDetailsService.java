package com.ch018.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;

@Service
public class LibraryUserDetailsService implements UserDetailsService {

	@Autowired
	PersonDao personDao;
	
	@Transactional
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		Person user = personDao.getByEmail(login);
		
		return new User(user.getEmail(), user.getPassword(), user.getConfirm(), true, true, true, null);
	}

}
