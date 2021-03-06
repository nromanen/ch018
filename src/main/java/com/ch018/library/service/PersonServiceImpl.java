package com.ch018.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BooksInUseDAO;
import com.ch018.library.DAO.HistoryDAO;
import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.DAO.WishListDAO;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.History;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import com.ch018.library.entity.Person.Role;
import com.ch018.library.form.Password;
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
	
	@Autowired 
	private MessageSource messageSource;
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private WishListDAO wishListDAO;
	
	@Autowired
	private BooksInUseDAO booksInUseDAO;
	
	@Autowired
	private HistoryDAO historyDAO;
	
	private VerificationKey verifyKey;
	
	private static final int MULTIBOOK_DEFAULT = 10;
	
	@Override
	@Transactional
	public void save(Person person) {
		personDao.save(person);
	}
	
	@Override
	@Transactional
	public void registrate(Registration registration, String message,
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
		String url2 = request.getServletPath();
		url.replaceAll(url2, "/");
		message = message + url + "/confirm?key=" + person.getVerificationKey();
		mailService.sendMail(registration.getEmail(),
				"Library email confirmation", message);
		personDao.save(person);
	}
	
	@Override
	@Transactional
	public void remindPasswoed(Person person, String message, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		message += url + "/pass?key=" + person.getVerificationKey();
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
	public int deletePersonCascade(Integer id) {
		Person person = personDao.getByIdWithAll(id);
		List<WishList> wishList = new ArrayList<>(person.getWishList());
		for (WishList wish : wishList) {
			wishListDAO.deleteWish(wish);
		}
		List<Orders> orders = new ArrayList<>(person.getOrders());
		for (Orders order : orders) {
			ordersDAO.deleteOrder(order);
		}
		List<BooksInUse> booksInUses = new ArrayList<>(person.getBooksinuses());
		for (BooksInUse booksInUse : booksInUses) {
			booksInUseDAO.removeBooksInUse(booksInUse.getBuid());
		}
		List<History> histories = new ArrayList<>(person.getHistories());
		for (History history : histories) {
			historyDAO.removeHistory(history);
		}
		return personDao.delete(id);
	}

	@Override
	@Transactional
	public void update(Person person) {
		personDao.update(person);
	}
	
	@Override
	@Transactional
	public List<Person> getAll(int currentPos, int pageSize, String field, boolean isAsc) {
		return personDao.getAll(currentPos, pageSize, field, isAsc);
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
	public List<Person> getSmsEnabled() {
		return personDao.getSmsEnabled();
	}

	@Override
	public Person updateAccProperties(Person person, Person updatedPerson, HttpServletRequest request) {
		person.setName(updatedPerson.getName());
		person.setSurname(updatedPerson.getSurname());
		person.setCellphone(updatedPerson.getCellphone());
		person.setSms(updatedPerson.getSms());
		return person;
	}
    
    @Override
    @Transactional
    public long isExist(String email) {
    	return personDao.isExist(email);
    }

    @Override
    @Transactional
    public void librarianUpdatePerson(Person updatedPerson, Person person) {
    	updatedPerson.setPassword(person.getPassword());
    	updatedPerson.setRole(person.getRole());
    	updatedPerson.setRating(person.getRating());
    	updatedPerson.setEmailConfirmed(person.getEmailConfirmed());
    	updatedPerson.setVerificationKey(person.getVerificationKey());
		personDao.update(updatedPerson);
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
		String url = request.getRequestURL().toString();
		String url2 = request.getServletPath();
		url = url.replaceAll(url2, "/remind");
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
		person.setEmailConfirmed(person2.getEmailConfirmed());
		person.setVerificationKey(person2.getVerificationKey());
		personDao.update(person);
    }
    
    @Override
    @Transactional
    public void adminSavePerson(Person person, HttpServletRequest request) {
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		person.setPassword(passwordEncoder.encode(PasswordGen.generateString(6)));
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

	@Override
	@Transactional
	public void updateEmail(String message, Person pers, Person pers2, HttpServletRequest request) {
		pers.setEmailConfirmed(false);
		pers.setVerificationKey(verifyKey.generate(pers2.getEmail()));
		personDao.update(pers);
		String url = request.getRequestURL().toString();
		//String message = "You have change your e-mail address on account in J Library"
			//	+ " Please confirm your new email by clicking next link: "
				//+ url + "/confirm?key=" + verifyKey.generate(pers2.getEmail()) + "&email=" +pers2.getEmail();
		message+= url + "/confirm?key=" + verifyKey.generate(pers2.getEmail()) + "&email=" +pers2.getEmail();
		mailService.sendMail(pers2.getEmail(), "Library email confirmation", message);
	}

	@Override
	@Transactional
	public boolean updatePassword(Password password, Person person) {
		boolean success = false;
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (BCrypt.checkpw(password.getPassword(), person.getPassword()))
			if (password.getNewPassword().equals(
					password.getConfirmPassword())) {
				person.setPassword(passwordEncoder.encode(password
						.getNewPassword()));
				personDao.update(person);
				success = true;
			}
		return success;
	}

}
