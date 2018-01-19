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
		 * // 1. Load core xml file, since name and loaction is fixed, so no
		 * params needed. Configuration cfg = new Configuration();
		 * cfg.configure();
		 * 
		 * // 2. Create SessionFactory, according to mapping relation, create
		 * table in DB. SessionFactory sessionFactory =
		 * cfg.buildSessionFactory();
		 * 
		 * // 3. Create session object, like connections in JDBC Session session
		 * = sessionFactory.openSession();
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

	@Test
	public void test1() {
		// Call sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// Open transactions
		Transaction transaction = session.beginTransaction();
		// Query by id
		User user = session.get(User.class, 1);
		System.out.println(user);
		// Commit
		transaction.commit();
		// Close
		session.close();
		sessionFactory.close();
	}

	@Test
	public void test2() {
		// Call sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// Open transactions
		Transaction transaction = session.beginTransaction();
		// update 
		User user = session.get(User.class, 2);
		user.setPassword("1112222");
		session.update(user);
		
		// Commit
		transaction.commit();
		// Close
		session.close();
		sessionFactory.close();
	}
	
	
	@Test
	public void test3() {
		// Call sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// Open transactions
		Transaction transaction = session.beginTransaction();
		// delete
		// 1. According id query the object, and then delete it from db.
		//User user = session.get(User.class, 1);
		//session.delete(user);
		// 2.  Create User class
		User userCreate = new User();
		userCreate.setUid(2);
		session.delete(userCreate);
		
		
		// Commit
		transaction.commit();
		// Close
		session.close();
		sessionFactory.close();
	}
	
	
	@Test
	public void test4() {
		// Call sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// Open transactions
		Transaction transaction = session.beginTransaction();
		
		User user = new User();
		user.setUid(5);
		user.setUsername("Lisds");
		user.setPassword("12sdf3");
		user.setAddress("Vethsdfanan");
		session.save(user);

		// Commit
		transaction.commit();
		// Close
		session.close();
		sessionFactory.close();
	}
}
