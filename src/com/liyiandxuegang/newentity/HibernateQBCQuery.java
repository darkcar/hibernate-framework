package com.liyiandxuegang.newentity;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.liyiandxuegang.utils.HibernateUtils;

public class HibernateQBCQuery {

	@Test
	public void testQBC() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Customer.class);
			List<Customer> customers = criteria.list();
			for(Customer customer : customers) {
				System.out.println("Customer: " + customer.getCid() + ", " + customer.getCustName());
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	@Test
	public void testQBCCondition() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.like("custName", "%CANA%"));
			List<Customer> customers = criteria.list();
			for(Customer customer : customers) {
				System.out.println("Customer: " + customer.getCid() + ", " + customer.getCustName());
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	@Test
	public void testQBCQuery() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.addOrder(Order.desc("cid"));
			List<Customer> customers = criteria.list();
			for(Customer customer : customers) {
				System.out.println("Customer: " + customer.getCid() + ", " + customer.getCustName());
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	
	@Test
	public void testQBCPagination() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.setFirstResult(1);
			criteria.setMaxResults(1);
			List<Customer> customers = criteria.list();
			for(Customer customer : customers) {
				System.out.println("Customer: " + customer.getCid() + ", " + customer.getCustName());
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	@Test
	public void testQBCStats() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.setProjection(Projections.rowCount());
			Object count  = criteria.uniqueResult();
			System.out.println(((Long)count).intValue());
//			List<Customer> customers = criteria.list();
//			for(Customer customer : customers) {
//				System.out.println("Customer: " + customer.getCid() + ", " + customer.getCustName());
//			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	
	@Test
	public void testQBCOffline() {
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			// Create detachedCriteria class
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
			// executable session
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			
			List<Customer> list = criteria.list();
			for(Customer customer : list) {
				System.out.println("Customer: " + customer.getCid() + ", " + customer.getCustName());
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











