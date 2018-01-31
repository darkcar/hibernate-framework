package com.liyiandxuegang.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import com.liyiandxuegang.utils.HibernateUtils;

public class HibernateManytoMany {
	
	@Test
	public void test() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			// Add two users and add two roles to each user
			User user1 = new User();
			user1.setUser_name("Liyi&Tian");
			user1.setUser_password("123456");
			
			User user2 = new User();
			user2.setUser_name("Frank");
			user2.setUser_password("1111111");
			
			Role role1 = new Role();
			role1.setRole_name("Manager");
			role1.setRole_memo("Manager makes more money.");
			
			Role role2 = new Role();
			role2.setRole_name("Director");
			role2.setRole_memo("Director is the boss in the department.");
			
			Role role3 = new Role();
			role3.setRole_name("VP");
			role3.setRole_memo("VP is big boss.");
			
			// 2 Create connections
			// user1 -- r1/r2
			user1.getSetRoles().add(role1);
			user1.getSetRoles().add(role2);
			
			// user2 -- r2/r3
			user2.getSetRoles().add(role2);
			user2.getSetRoles().add(role3);
			
			// 3. Save user
			session.save(user1);
			session.save(user2);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	
	@Test
	public void test1() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
		
			// Add one role to one user
			User user = session.get(User.class, 1);
			Role role = session.get(Role.class, 3);
			
			user.getSetRoles().add(role);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	@Test
	public void test2() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
		
			// Add one role to one user
			User user = session.get(User.class, 2);
			Role role = session.get(Role.class, 3);
			
			user.getSetRoles().remove(role);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
