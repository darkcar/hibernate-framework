package com.liyiandxuegang.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static final Configuration cfg;
	private static final SessionFactory sessionFactory;
	// Static, only run once.
	static {
		cfg = new Configuration();
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
	}
	// return session factory 
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	// return local session 
	public static Session getSessionObject() {
		return sessionFactory.getCurrentSession();
	}
	
	
	public static void main(String[] args) {
		
	}
}
