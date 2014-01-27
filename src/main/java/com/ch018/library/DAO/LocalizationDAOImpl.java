package com.ch018.library.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;
import com.ch018.library.entity.Person;

@Component
public class LocalizationDAOImpl implements LocalizationDAO {
	
	private static Logger log = LogManager.getLogger(LocalizationDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String getName(int id, String language) {
		Localization loc = new Localization();
		String name = "";
		try {
			/*name = (String) sessionFactory.getCurrentSession().createQuery("select localizedName from Lokalization where Genre.id=:id and language=:language")
					.setString("language", language).setInteger("id", id).list().get(0);
			*/
			loc = (Localization) sessionFactory.getCurrentSession()
					.createCriteria(Localization.class)
					.add(Restrictions.eq("genre.id", id))
					.add(Restrictions.eq("language", language)).list().get(0);
			name = loc.getLocalizedName();
		} catch (Exception e) {
			log.error(e);
		}
		return name;
	}

}
