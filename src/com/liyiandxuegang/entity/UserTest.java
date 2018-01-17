package com.liyiandxuegang.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.liyiandxuegang.utils.HibernateUtils;

public class UserTest {

	@Test
	public void test() {
		/*
		// 1. Load core xml file, since name and loaction is fixed, so no params needed. 
		Configuration cfg = new Configuration();
		cfg.configure();
		
		// 2. Create SessionFactory, according to mapping relation, create table in DB.
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		// 3. Create session object, like connections in JDBC
		Session session = sessionFactory.openSession();
		*/
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		// 4. Create transaction
		Transaction transaction = session.beginTransaction();
		
		// 5. CRUD
		// Insert
		User user = new User();
		user.setUsername("Liyi");
		user.setPassword("123456");
		user.setAddress("Canada");
		// Call session save
		session.save(user);
		
		// 6. Commit transactions
		transaction.commit();
		
		// 7. Close resources
		session.close();
		sessionFactory.close();
	}
}
