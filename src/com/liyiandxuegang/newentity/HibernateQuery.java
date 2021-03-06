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
	
	@Test
	public void testHQLCond() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			// query all customers: from Entity class name
			Query query = session.createQuery("from Customer where cid=? and custName=?");
			
			// set condition value: int: position; args: value
			query.setParameter(0, 2);
			query.setParameter(1, "SGI CANADA");
			
			// 
			query = session.createQuery("from Customer where custName like %CANA%");
			// 
			// GET THE VAULE
			List<Customer> customers = query.list();
			for(Customer customer : customers) {
				System.out.println("Customer: " + customer.getCustName());
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
	public void testHQLOrder() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("from Customer order by cid desc");
			List<Customer> customers = query.list();
			
			for(Customer customer : customers) {
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
	
	@Test
	public void testHQLPagination() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("from Customer");
			query.setFirstResult(0); // start 
			query.setMaxResults(1); // end
			List<Customer> customers = query.list();
			for(Customer customer : customers) {
				System.out.println("Customer: " + customer.getCustName());
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
	public void testHQLProjection() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("select custName from Customer");
			List<Object> customers = query.list();
			for(Object customer : customers) {
				System.out.println("Customer: " + customer);
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
	public void testHQLFunctions() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("select count(*) from Customer");
			Object obj = query.uniqueResult();
			System.out.println("Total customer: " + obj.toString());
			
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}

























