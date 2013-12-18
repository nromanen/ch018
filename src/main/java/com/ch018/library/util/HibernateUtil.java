package com.ch018.library.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class HibernateUtil {
	static Logger log = LogManager.getLogger(HibernateUtil.class);
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); 
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			log.info("SessionFactory created.");
		} catch (Throwable ex) {
			log.error("Initial SessionFactory creation failed. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
	      return sessionFactory;
	    }

}
