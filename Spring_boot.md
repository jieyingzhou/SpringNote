* Quickly develop Spring Boost applicaations
* Develop a REST API using Spring Boot
* Create a Spring MVC app with Spring Boot 
* Connect Spring Boost apps to a Database for CRUD development
* Leverage all Java configuration(no xml) and Maven

# Benfits
* Make it easier to get started with Spring development
* Minimized the amount of manual configuration
  * Perform auto-configuration bases on props files and JAR class path
* Help to resolve dependency conflicts(Maven or Gradle)
* Provide an embedded HTTP server so you can get started quickly
  * Tomcat, Jetty ,Undertow,…

Directory        Description

Src/main/java         Your Java source code

Src/main/resources         Properties/ config files used by your app

Src/test/java                   Unit testing source code

# Spring-boot-starter-web
A collection of Maven dependencies: spring-mvc, hibernate-validator,json,tomcat…

# Spring-boot-devtools 
* Automatically restarts your application when code is updates
* Simply add the dependency to your POM file
* No need to write additional code

# Spring Boot Actuator
*Problem*
* How can I monitor and manage my application?
* How can I check the application health?
* How can I access application metric?

—Solution: Spring Boot Actuator
* Exposes endpoints to monitor and manage your application
* You easilt get DevOps fuunctionality out-of-the-box
* Simply add the dependency to your POM file
* REST endpoints are qutomatically added to your application

*Health Endpoint*
* /health checks the stauts of your application
* Normally used by monitoring apps to see if your app is up or down

*Info Endpoint*
* /info gives information about your application
* Default is empty

# REST API with Spring Boot
REST API with Spring Boot that connects to a database

Create a REST API for the Employee Directory

REST clients should be able to

* Get a list of employees
* Get a single employee by id
* Add a new employee 
* Update an employee 
* Delete an employee 


*Step by Step*
1. Set up Database Dev Environment
2. Create Spring Boot project using Spring Initializer
3. Get list of employees
4. Get single employee by ID
5. Add a new employee
6. Updaate an existing employee
7. Delete an existing employee


# What is JPA?
* Java Persistence API
  * Standard API for Object-to-Relational-Mapping(ORM)
* Only a specification
  * Define a set of interfaces
  * Requires an implementation to be usable

*Auto Data Source Configuration*

* In springboot, hibernate is default implementation of JPA
* EntityManager is similar to hibernate SessionFactory
* EntityManager can serve as a wrapper for a Hibernate Session object
* We can inject the RntityManger into our DAO

# Various DAO Techniques
* Version 1: Use EntityManager but leverage native Hibernate API
* Version 2: Use EntityManager and standaard JPA API
* Version 3: Spring Data JPA


*Step By Step*
1. Update db configs in application.proerties
2. Create Employee entity
3. Create DAO interface
4. Create DAO implementation
5. Create REST contoller to use DAO

# Sending JSON to Spring REST Controller
* When sending JSON data to Spring REST Controller
* For controller to process JSON data, need to set a HTTP request
  * Content-type: applocation/json
* Need to configure REST client to send the correct HTTP request header

# Inject custom application properties

```java
@RestController
public class FunRestController {
	
	// inject properties for coach.name and team name
	@Value("${coach.name}")
	private String coachName;
	
	@Value("${team.name}")
	private String teamName;
```

# Spring Boot REST API 
*Step by Step*
1. Set up Database Dev Environment
2. Create spring boot project using spring initializer
3. get list of employee
4. Get single employee by ID
5. Add a new employee
6. Update an existing employee
7. Delete an existing employee

# Build a REST CRUD API with Hibernate
```java
package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public List<Employee> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery =
				currentSession.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results		
		return employees;
	}


	@Override
	public Employee findById(int theId) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		Employee theEmployee =
				currentSession.get(Employee.class, theId);
		
		// return the employee
		return theEmployee;
	}


	@Override
	public void save(Employee theEmployee) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save employee
		currentSession.saveOrUpdate(theEmployee);
	}


	@Override
	public void deleteById(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery(
						"delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
	}

}

```

# Build a REST CRUD API with JPA
```java
package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager thEntityManager) {
		entityManager = thEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		// create a query
		Query theQuery = entityManager.createQuery("from Employee");
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		// get employee
		Employee theEmployee = entityManager.find(Employee.class, theId);
		
		// return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		// save or update the employee
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		// update with id from db ... so we can get generated id for save/insert
		theEmployee.setId(dbEmployee.getId());
		
	}

	@Override
	public void deleteById(int theId) {
		
		// delete object with promary key
		Query theQuery = entityManager.createQuery("delete from Employee where id =: employeIde");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
	}

}

```


# Spring Data JPA in Spring Boot

Various DAO Techniques

Version 1: Use EntityManager but leverage native Hibernate API
Version 2: Use EntityManager and standard JPA API
Version 3: Spring Data JPA

# The problem

To create a DAO for Employee: create EmployeeDAO, EmployeeDAO Implimentation,

```java
package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
		
}
```
* What if we need to create a DAO for another entity?
  * Customer, Student, Product, Book…
* Do we have to repeat all of the same code again?

# Spring Data JPA - Solution

* Spring Data JPA is the solution!
* Create a DAP and just plug in your entity type and primary key
* Spring will give you a CRUD implementation for FREE
  * Helps to minimize boiler-plate DAO code

# JpaRepository
* Spring Data JPA provides the interface: JpaRepository
* Exposes methods (some by inheritance from parents)

*Step by Step*
1. Extend JpaRepository interface
2. Use your Repository in your app (No need for implementation class)

```java
package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
```

# Advanced Features
* Advanced features available for
  * Extending and adding custom queries with JPQL
  * Query Domain Specific Language(Query DSL)
  * Defining custom methods(low-level coding)




