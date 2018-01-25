package com.liyiandxuegang.entity;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.liyiandxuegang.utils.HibernateUtils;

public class UserQueryTest {

	@Test
	public void test1() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			// 1. create query obj
			Query query = session.createQuery("from User");

			// 2. get result
			List<User> resultList = query.list();
			for (User user : resultList) {
				System.out.println(user);
			}

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

			// 1. create Crteria
			Criteria criteria = session.createCriteria(User.class);
			// 2. Call the method to get the result
			List<User> userList = criteria.list();
			for (User user : userList) {
				System.out.println(user);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	@Test
	public void test3() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			// 1. create sqlQuery
			SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM t_user");
			sqlQuery = sqlQuery.addEntity(User.class);
			List<User> userList = sqlQuery.list();
			for(User user : userList) {
				System.out.println(user);
			}
			// List<Object[]> userList = sqlQuery.list();
			// for(Object[] objects : userList) {
			// System.out.println(Arrays.toString(objects));
			// }
			
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
