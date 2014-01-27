package com.ch018.library.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Person.Role;
import com.ch018.library.form.Registration;
import com.ch018.library.form.ResetPassword;
import com.ch018.library.util.CalculateRating;
import com.ch018.library.util.PasswordGen;
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
		person.setEmailConfirmed(true);
		personDao.update(person);
	}

	@Override
	@Transactional
	public int delete(int id) {
		return personDao.delete(id);
	}

	@Override
	@Transactional
	public void update(Person person) {
		personDao.update(person);
	}

	@Override
	@Transactional
	public List<Person> getAll() {
		return personDao.getAll();
	}
	
	@Override
	@Transactional
	public List<Person> getAll(int currentPos, int pageSize, String field) {
		
		if (currentPos > -1) {
			return personDao.getAll(currentPos, pageSize, field);
		} else {
			return personDao.getAll();
		}
	}

	@Override
	@Transactional
	public Person getById(int id) {
		return personDao.getById(id);
	}
	
	@Override
	@Transactional
	public Person getByIdWithBooks(int id) {
		return personDao.getByIdWithBooks(id);
	}
	
	@Override
	@Transactional
	public Person getByIdWithOrders(int id) {
		return personDao.getByIdWithOrders(id);
	}

	@Override
	@Transactional
	public Person getByEmail(String email) {
		return personDao.getByEmail(email);
	}
	
	@Override
	@Transactional
	public Person getByKey(String key) {
		return personDao.getByKey(key);
	}

	@Override
	@Transactional
	public List<Person> getByName(String name) {
		return personDao.getByName(name);
	}

	@Override
	@Transactional
	public List<Person> getBySurname(String surname) {
		return personDao.getBySurname(surname);
	}

	@Override
	@Transactional
	public Person getByCellPhone(String cellphone) {
		return personDao.getByCellPhone(cellphone);
	}

	@Override
	@Transactional
	public List<Person> getByRole(String role) {
		return personDao.getByRole(role);
	}

	@Override
	@Transactional
	public List<Person> getConfirmed() {
		return personDao.getConfirmed();
	}

	@Override
	@Transactional
	public List<Person> getSmsEnabled() {
		return personDao.getSmsEnabled();
	}

	@Override
	public Person updateAccProperties(Person person, Person updatedPerson, HttpServletRequest request) {
		/*if (!updatedPerson.getName().isEmpty())
			if ((person.getName() == null)
					|| (!person.getName().equals(updatedPerson.getName()))) {  */
				person.setName(updatedPerson.getName());
			//}
		/*if (!updatedPerson.getSurname().isEmpty())
			if ((person.getSurname() == null)
					|| (!person.getSurname().equals(updatedPerson.getSurname()))) { */
				person.setSurname(updatedPerson.getSurname());
			//}
	/*	if (!updatedPerson.getCellphone().isEmpty())
			if ((person.getCellphone() == null)
					|| (!person.getCellphone().equals(
							updatedPerson.getCellphone()))) {*/
				person.setCellphone(updatedPerson.getCellphone());
			//}
		//if (!updatedPerson.getEmail().isEmpty())
			if (!person.getEmail().equals(updatedPerson.getEmail())) {
				person.setEmail(updatedPerson.getEmail());
                                String url = request.getRequestURL().toString();
		                String message = "You have change your e-mail address on account in J Library"
				+ " Please confirm your new email by clicking next link: "
				+ url + "/confirm?key="                
				+ person.getVerificationKey();
		                mailService.sendMail(person.getEmail(),
				"Library email confirmation", message);
			}
		//if (person.getSms() != updatedPerson.getSms()) {
			person.setSms(updatedPerson.getSms());
            	//	}
		return person;
	}
    
    @Override
    @Transactional
    public long isExist(String email) {
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
    
    @Override
    @Transactional
    public void librarianSavePerson(Person person, HttpServletRequest request) {
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		person.setPassword(passwordEncoder.encode(PasswordGen.generateString(6)));
		person.setRole(Person.Role.ROLE_USER.toString());
		person.setEmailConfirmed(false);
		person.setVerificationKey(VerificationKey.generate(person.getEmail()));
		person.setConfirm(false);
		person.setRating(CalculateRating.getRating());
		person.setMultibookAllowed(MULTIBOOK_DEFAULT);
		String url = "http://localhost:8080/library/remind";
		String message = "Thank you for joining our JLibrary. Change your password by clicking next link: "
		+ url + "/pass?key="                
		+ person.getVerificationKey();
		
		mailService.sendMail(person.getEmail(),
				"Library email confirmation", message);

		personDao.save(person);
    }
    
    @Override
    @Transactional
    public void adminUpdatePerson(Person person, Person person2) {
    	person.setPassword(person2.getPassword());
		//person.setRole(person2.getRole());
		//person.setRating(person2.getRating());
		person.setEmailConfirmed(person2.getEmailConfirmed());
		person.setVerificationKey(person2.getVerificationKey());
		personDao.update(person);
    }
    
    @Override
    @Transactional
    public void adminSavePerson(Person person, HttpServletRequest request) {
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		person.setPassword(passwordEncoder.encode(PasswordGen.generateString(6)));
		//person.setRole(Person.Role.ROLE_USER.toString());
		person.setEmailConfirmed(false);
		person.setVerificationKey(VerificationKey.generate(person.getEmail()));
		person.setConfirm(false);
		person.setRating(CalculateRating.getRating());
		person.setMultibookAllowed(MULTIBOOK_DEFAULT);
		String url = "http://localhost:8080/library/remind";
		String message = "Thank you for joining our JLibrary. Change your password by clicking next link: "
		+ url + "/pass?key="                
		+ person.getVerificationKey();
		
		mailService.sendMail(person.getEmail(),
				"Library email confirmation", message);

		personDao.save(person);
    }
    
    @Override
    @Transactional
    public long getCount() {
    	return personDao.count();
    }

}
