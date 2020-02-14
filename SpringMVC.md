# What is Spring MVC?

* Framework for building web application in Java
* Based on Model-View-Controller design pattern
* Leverages features of the Core Spring Framework(Ioc, DI)

* Controller *
Code created by developer
* Contains your business logic
  * Handle the request
  * Store/retrieve data(db, web, service…)
  * Place data in model
* Send to appropriate view template

* Model *
Model: contains your data
* Store/ retrive data via backend systems
  * database, web service, etc…
  * Use a Spring bean if you like
* Place your data in the model
  * Data can be any Java object.collection

* View Template *
* Spring MVC is flexible
  * Supports many view templates
* Most common is JSP(Java server pages)+JSTL(JSP standard tag library)

* Developer creates a page
  *  Displays data
  
  # Spring MVC Configuratuin Process 
- Part 1

Add configurations to file: WEB-INF/web.xml
1. Configure Spring MVC Dispatcher Servlet
2. Set up URL mappings to Spring MVC Dispatcher Servlet

-Part 2

Add configuration to file: WEB-INF/spring-mvc-demo-servlet.xml
3. Add support for Spring component scanning
4. Add support for conversion, formatting and validation
5. Configure Spring MVC View Resolver

# Creating a Spring Home Controller and View
* Step by Step *
1. Create Controller class
@Controller inherits from @Component…supports scanning
2. Define Controller method
3. Add Request Mapping to Controller method
4. Return View Name
5. Develop View Page

* Code Example *
* We want to create a new method to process form data
* Read the dorm data: student’s name
* Convert the name to upper case
* Add the uppercase version to the model


# What is Hibernate?

A framework for persisting/ saving Java objects in a database

*Benefits of Hibernate*
* Hibernate handles all of the low-level SQL
* Minimizeds the amount of JDBC code you have to develop
* Hibernate provides the Object-to-Relational Mapping(ORM)


To Do List

1. Create Eclipse Project
2. Download Hibernate Files
3. Download MySQL JDBC Driver
4. Add JAR files to Eclipse Project … Build Path

# Hibernate annotation
*Java Annotations*
1. Map class to database table
2. Map fields to database columns

# FAQ: Why we are using JPA Annotation instead of Hibernate ?



QUESTION:
Why we are using JPA Annotation instead of Hibernate ?

For example, why we are not using this org.hibernate.annotations.Entity?

ANSWER:
JPA is a standard specification. Hibernate is an implementation of the JPA specification.

Hibernate implements all of the JPA annotations.

The Hibernate team recommends the use of JPA annotations as a best practice.

# Hibernate Dev Process - To Do List
1. Add Hibernate Configuration file
2. Annotate Java Class
3. Develop Java Code to perform database operations

*SAVE & READ*
``` java
                        // create a student object
			System.out.println("Creating a new student object...");
			Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			// MY NEW CODE
			
			// find out the student's if: primary key
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
				
			System.out.println("Done!");

```

*Update & Delete*

```java
int studentId = 1;
			
		
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstname("Scooby");
			
			// commit the transaction
			session.getTransaction().commit();
			
			/*-------------------------------------------*/
			
			// NEW CODE
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update for all students
			System.out.println("Update email for all students");
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			
			session.getTransaction().commit();
				
			System.out.println("Done!”);
```

```java
// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			// delete the student
			//System.out.println("Deleting student: " + myStudent);
			//session.delete(myStudent);
			
			// delete student id=2
			System.out.println("Deleting student id=2");
			
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
				
			System.out.println("Done!”);
```

# Primary key and Foreign key
* Primary key: identify a unique row in a table
* Foreign key:
  * Link tables together
  * A field in one table that refers to primary key in another table
  
  # Development Process: One to One
1. Prep Work- Define database tables
2. Create InstructorDetail class
3. Create Instructor class
4. Create Main App

*More on Foreign Key*
* Main purpose is to preserve relationship between tables
  * Referential Integrity
* Prevents operations that would destriy relationship

* Ensures only valid data is inserted into the foreign key column
  * Can only contain valid reference to primary key in other table

# One to One - Uni
*Create*
```java
// create the objects
			Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www/youtube.com", "Guitar");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
			//
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!”);
```

*Delete*
``java
// start a transaction
session.beginTransaction();

// get instructor by primary key /id
int theId = 1;
Instructor tempInstructor = session.get(Instructor.class, theId);

System.out.println("Found instructor: " + tempInstructor);

// delete the instructors
if (tempInstructor != null) {

	System.out.println("Deleting: " + tempInstructor);

	// Note: will ALSO delete accociated "details" object
	// because of CascadeType.ALL
	//
	session.delete(tempInstructor);
}


// commit transaction
session.getTransaction().commit();

System.out.println("Done!");
```

# One-to-One - Bi
1. Make updates to InstructorDetail class:
  1. Add new field to reference Instructor
  2. Add getter/setter methods for Instructor
  3. Add @OneToOne annotation
2. Create Main App

```java
@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "youtube_channel")
	private String youtubeChannel;
	
	@Column(name = "hobby")
	private String hobby;
	
	// add new field for instructor(also add getter/setter)
	// add @OneToOne annotation
	
	// refers to "instructorDetail" property in "Instructor" class
	
	@OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
	private Instructor instructor;	
	
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public InstructorDetail() {
		
	}

	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youtubeChannel=" + youtubeChannel + ", hobby=" + hobby + "]";
	}
	
```

```java
// start a transaction
session.beginTransaction();

// get the instructor detail object
int theId = 2;
InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

// print the instructor detail
System.out.println("tempInstructorDetail: " + tempInstructorDetail);

// print the associated instructor
System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

// commit transaction
session.getTransaction().commit();

System.out.println("Done!”);
```

*Delete*
```java
// start a transaction
session.beginTransaction();

// get instructor by primary key /id
int theId = 2;
InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

System.out.println("tempInstructorDetail: " + tempInstructorDetail);

System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

// delete the instructor detail
System.out.println("Deleting tempInstructorDetail:" + tempInstructorDetail);
session.delete(tempInstructorDetail);


// commit transaction
session.getTransaction().commit();

System.out.println("Done!”);
```

*Only delete InstructDetail*
```java
// start a transaction
session.beginTransaction();

// get instructor by primary key /id
int theId = 3;
InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

System.out.println("tempInstructorDetail: " + tempInstructorDetail);

System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

// delete the instructor detail
System.out.println("Deleting tempInstructorDetail:" + tempInstructorDetail);

// remove the associated object reference
// break bi-directional link
tempInstructorDetail.getInstructor().setInstructorDetail(null);

session.delete(tempInstructorDetail);


// commit transaction
session.getTransaction().commit();

System.out.println("Done!”);
```

# One to Many-Bi
Do not applying cascade delete

*Development Process: One-to-Many
1. Prep Work - Define database tables
2. Create Course class
3. Update Instructor class
4. Create Main App

