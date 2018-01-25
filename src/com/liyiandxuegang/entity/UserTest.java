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
		Session session = HibernateUtils.getSessionObject();
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
		// session.close();
		// sessionFactory.close();
	}
	
	/* Verify First Level Cache */
	@Test
	public void testCache() {
		
		// Call sessionFactory
				SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
				Session session = sessionFactory.openSession();
				// Open transactions
				Transaction transaction = session.beginTransaction();
				
				// query 
				User user1 = session.get(User.class, 6);
				System.out.println(user1);
				
				User user2 = session.get(User.class, 6);
				System.out.println(user2);
				
				// Commit
				transaction.commit();
				// Close
				session.close();
				sessionFactory.close();
		
	}
	
	/* First-level cache feature */
	@Test
	public void testCacheFeature() {
		
		// Call sessionFactory
				SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
				Session session = sessionFactory.openSession();
				// Open transactions
				Transaction transaction = session.beginTransaction();
				
				// query ： 持久态会自动更新数据
				User user = session.get(User.class, 6);
				user.setUsername("LiyiandFrank");
				//	session.saveOrUpdate(user);
				
				
				// Commit
				transaction.commit();
				// Close
				session.close();
				sessionFactory.close();
		
	}
	
	@Test
	public void testStandard() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			// Add new user
			User user = new User();
			user.setUsername("Add new user");
			user.setPassword("123");
			user.setAddress("CANADA");
			
			session.save(user);
			int i = 10 / 0;
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	// hql query
}










