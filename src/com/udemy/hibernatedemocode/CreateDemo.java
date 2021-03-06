package com.udemy.hibernatedemocode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.udemy.hibernatedemo.entity.Instructor;
import com.udemy.hibernatedemo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// create the objects
			Instructor tempInstructor = 
					new Instructor("edu","reka","edureka@gmail.com");
			
			InstructorDetail tempInstructorDetail 
			= new InstructorDetail("www.edureka.com/youtube","coding");
			// associate the objects
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction			
			session.beginTransaction();
			
			// save the instructor
			// Note : this will also save the details object because of CascadeType.ALL
			System.out.println("Saving Instructor: " + tempInstructor);
			session.save(tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}
	}

}
