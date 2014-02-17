package com.ch018.library.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Localization;

@Component
public class LocalizationDAOImpl implements LocalizationDAO {
	
	private static Logger log = LogManager.getLogger(LocalizationDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addGenreLocalization(Localization localization) {
		try {
			sessionFactory.getCurrentSession().save(localization);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	@Override
	public String getName(int id, String language) {
		Localization loc = new Localization();
		String name = "";
		try {
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
