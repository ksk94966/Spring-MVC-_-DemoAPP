package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;   //this import is important
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public List<Customer> getCustomers(){
		// TODO Auto-generated method stub
		
		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query .... sort by lastName
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
														Customer.class);
		
		//execute the query and get the result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		
		return customers;
		
	}



	@Override
	public void saveCustomer(Customer theCustomer) {
		// TODO Auto-generated method stub
		
		//get the current Hibernate session
	    Session currentSession = sessionFactory.getCurrentSession();
	    
	    //save the customer
	    currentSession.saveOrUpdate(theCustomer);
	    
	}



	@Override
	public Customer getCustomer(int theId) {
		// TODO Auto-generated method stub
		
		//get the current Hibernate session
	    Session currentSession = sessionFactory.getCurrentSession();
	    
	    //now retrive the customer with that id
	  	Customer theCustomer = currentSession.get(Customer.class, theId);
	    
		return theCustomer;
	}



	@Override
	public void deleteCustomer(int theId) {
		// TODO Auto-generated method stub
		
		//get the current Hibernate session
	    Session currentSession = sessionFactory.getCurrentSession();
	    
	    //now delete the customer with that id
	  	Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
	    
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}
	

}
