package com.ch018.library.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Person.Role;
import com.ch018.library.form.Password;
import com.ch018.library.form.Registration;
import com.ch018.library.form.ResetPassword;
import com.ch018.library.util.CalculateRating;
import com.ch018.library.util.VerificationKey;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private MailService mailService;
	
	private static final int MULTIBOOK_DEFAULT = 10;
	
	@Override
	@Transactional
	public void save(Person person) {
		// TODO Auto-generated method stub
		personDao.save(person);
	}
	
	@Override
	@Transactional
	public void registrate(Registration registration, 
			HttpServletRequest request) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Person person = new Person();

		person.setEmail(registration.getEmail().trim());
		person.setPassword(passwordEncoder.encode(registration.getPassword()));
		person.setVerificationKey(VerificationKey.generate(registration
				.getEmail()));
		person.setRole(Role.ROLE_USER.toString());
		person.setConfirm(false);
		person.setEmailConfirmed(false);
		person.setRating(CalculateRating.getRating());
		person.setMultibookAllowed(MULTIBOOK_DEFAULT);
		String url = request.getRequestURL().toString();
		String message = "Thank you for joining our JLibrary."
				+ " Please confirm your email by clicking next link: "
				+ url + "/confirm?key="                
				+ person.getVerificationKey();
		mailService.sendMail(registration.getEmail(),
				"Library email confirmation", message);
		personDao.save(person);
	}
	
	@Override
	@Transactional
	public void remindPasswoed(Person person, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String message = " Change your password by clicking next link: "
				+ url + "/pass?key="                
				+ person.getVerificationKey();
		mailService.sendMail(person.getEmail(),
				"Library password recovery", message);
	}
	
	@Override
	@Transactional
	public void restorePassword(Person person, 
			ResetPassword password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		person.setPassword(passwordEncoder.encode(password.getPassword()));
		personDao.update(person);
	}

	@Override
	@Transactional
	public int delete(int id) {
		// TODO Auto-generated method stub
		return personDao.delete(id);
	}

	@Override
	@Transactional
	public void update(Person person) {
		// TODO Auto-generated method stub
		personDao.update(person);
	}

	@Override
	@Transactional
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return personDao.getAll();
	}

	@Override
	@Transactional
	public Person getById(int id) {
		// TODO Auto-generated method stub
		return personDao.getById(id);
	}

	@Override
	@Transactional
	public Person getByEmail(String email) {
		// TODO Auto-generated method stub
		return personDao.getByEmail(email);
	}
	
	@Override
	@Transactional
	public Person getByKey(String key) {
		// TODO Auto-generated method stub
		return personDao.getByKey(key);
	}

	@Override
	@Transactional
	public List<Person> getByName(String name) {
		// TODO Auto-generated method stub
		return personDao.getByName(name);
	}

	@Override
	@Transactional
	public List<Person> getBySurname(String surname) {
		// TODO Auto-generated method stub
		return personDao.getBySurname(surname);
	}

	@Override
	@Transactional
	public Person getByCellPhone(String cellphone) {
		// TODO Auto-generated method stub
		return personDao.getByCellPhone(cellphone);
	}

	@Override
	@Transactional
	public List<Person> getByRole(String role) {
		// TODO Auto-generated method stub
		return personDao.getByRole(role);
	}

	@Override
	@Transactional
	public List<Person> getConfirmed() {
		// TODO Auto-generated method stub
		return personDao.getConfirmed();
	}

	@Override
	@Transactional
	public List<Person> getSmsEnabled() {
		// TODO Auto-generated method stub
		return personDao.getSmsEnabled();
	}

	@Override
	public Person updateAccProperties(Person person, Person updatedPerson) {
		if (!updatedPerson.getName().isEmpty())
			if ((person.getName() == null)
					|| (!person.getName().equals(updatedPerson.getName()))) {
				person.setName(updatedPerson.getName());
			}
		if (!updatedPerson.getSurname().isEmpty())
			if ((person.getSurname() == null)
					|| (!person.getSurname().equals(updatedPerson.getSurname()))) {
				person.setSurname(updatedPerson.getSurname());
			}
		if (!updatedPerson.getCellphone().isEmpty())
			if ((person.getCellphone() == null)
					|| (!person.getCellphone().equals(
							updatedPerson.getCellphone()))) {
				person.setCellphone(updatedPerson.getCellphone());
			}
		if (!updatedPerson.getEmail().isEmpty())
			if (!person.getEmail().equals(updatedPerson.getEmail())) {
				person.setEmail(updatedPerson.getEmail());
			}
		if (person.getSms() != updatedPerson.getSms()) {
			person.setSms(updatedPerson.getSms());
		}
		return person;
	}
    
    @Override
    @Transactional
    public long isExist(String email) {
    	// TODO Auto-generated method stub
    	return personDao.isExist(email);
    }

    @Override
    @Transactional
    public void librarianUpdatePerson(Person person, Person person2) {
		person.setPassword(person2.getPassword());
		person.setRole(person2.getRole());
		person.setRating(person2.getRating());
		person.setEmailConfirmed(person2.getEmailConfirmed());
		person.setVerificationKey(person2.getVerificationKey());
		personDao.update(person);
    }

}
