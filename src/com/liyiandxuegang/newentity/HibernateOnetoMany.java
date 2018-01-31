package com.liyiandxuegang.newentity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.liyiandxuegang.utils.HibernateUtils;

public class HibernateOnetoMany {

	@Test
	public void testAdd() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			// Add one customer and one link man
			// 1. Create customer and linkman
			Customer customer = new Customer();
			customer.setCustName("SGI");
			customer.setCustLevel("vip");
			customer.setCustMobile("306-581-8888");
			customer.setCustPhone("911");
			customer.setCustSource("Internet");
			
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("Frank");
			linkMan.setLkm_gender("Male");
			linkMan.setLkm_phone("8384");
			
			// 2. Create the connection between customer and linkman
			// 2.1 Customer and linkman
			customer.getSetLinkMans().add(linkMan);
			// 2.2 Linkman and customer
			linkMan.setCustomer(customer);
			
			// 3. Save to db
			session.save(customer);
			session.save(linkMan);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
		
		
	}
	
	@Test
	public void testAdd2() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			// Add one customer and one link man
			// 1. Create customer and linkman
			Customer customer = new Customer();
			customer.setCustName("SGI CANADA");
			customer.setCustLevel("Normal");
			customer.setCustMobile("306-581-8080");
			customer.setCustPhone("911");
			customer.setCustSource("Book");
			
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("Lee");
			linkMan.setLkm_gender("FeMale");
			linkMan.setLkm_phone("88888888");
			
			// 2. Create the connection between customer and linkman
			customer.getSetLinkMans().add(linkMan);
			
			// 3. Save to db
			session.save(customer);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	@Test
	public void testdelete() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			// delete the customer
			Customer customer = session.get(Customer.class, 3);
			session.delete(customer);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	@Test
	public void testupdate() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			// get linkman,
			Customer customer = session.get(Customer.class, 1);
			LinkMan linkMan = session.get(LinkMan.class, 2);
			// Set persistence value 
			customer.getSetLinkMans().add(linkMan);
			linkMan.setCustomer(customer);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}

	