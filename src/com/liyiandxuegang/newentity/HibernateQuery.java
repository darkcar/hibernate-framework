package com.liyiandxuegang.newentity;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.liyiandxuegang.utils.HibernateUtils;

public class HibernateQuery {
	@Test
	public void test() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Customer customer = session.get(Customer.class, 1);
			Set<LinkMan> linkMans = customer.getSetLinkMans();
			System.out.println(linkMans.size());
			
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	@Test
	public void testHQL() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			// query all customers: from Entity class name
			Query query = session.createQuery("from Customer");
			// get result
			List<Customer> resultCustomers = query.list();
			for(Customer customer : resultCustomers) {
				System.out.println(customer.getCid() + ", " + customer.getCustName());
			}
			
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
