*Step by Step*

* Set up Database Dev Environment
* List Customers
* Add a new Customer
* Update a Customer
* Delete a Customer

# Customer Data Access Object (DAO)
* Responsible for interfacing with the database
* This is a common design pattern: Data Access Object(DAO)

*List Customers*
1. Create Customer.java
2. Create CustomerDAO.java & CustomerDAOImpl.java
3. Create CustomerController.java
4. Create JSP page: list-customers.jsp

Hibernate Terminology-Refresh

Entity Class: Java class that is mapped to a database table

*Create hibernate entity*
Step1: Map class to database table
Step2: Map fields to database columns

# Define DAO
*For hibernate, DAO needs a hibernate SessionFactory
* Hibernate SessionFactory needs a Data Source
  * The data source defines databse connection info
*These are all dependencies! We will wire them together with Dependency Injection (DI)*

*Customer DAO*
1. Define Dao interface
2.Define DAO implementation
  * Inject the session factory

*@Transactional*

* Automagically begin and end a transaction for your Hibernate code
  * No need for you to explicitly do this in your code
* This Spring magic happends behind the scenes

*@Repository*
* Applied to DAO implementations
* Spring will automatically register the DAO implementation
  * thanks to component-scanning
* Spring also provides translation of anny JDBC related exceptions


*Inject DAO into Controller*


*@RequestMapping*
This mapping handles ALL HTTP methods: GET,POST,etc…

*@GetMapping*
This maapping ONLY handles GET method, other HTTP REQUEST method will get rejected
*@PostMapping*

GET&POST which one?
*Get*:
* Good for debugging
* Bookmark or email URL
* Limitation on data length
*POST*
* Can’t bookmark or email URL
* No limitations on data length
* Can also senf binary data


# Define Services with @Service
* Service Facade design pattern
* Intermediate layer for custom business logic
* Integrate data from multiple sources(DAO/repositories)

@Service applied to Service implementations

Spring will automatically register the Service implementation

 ## Customer Service
*Step by Step*
1. Define Service interface
2. Define Service implementation
  * Inject the CustomerDAO

*CustomerService*
```java
public interface CustomerService {
	
	public List<Customer> getCustomers();
	

}
```

*CustomerServiceImpl*
```java
@Service
public class CustomerServiceImpl implements CustomerService {

	// new to inject customer dao
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@Override
	public List<Customer> getCustomers() {
				
		return customerDAO.getCustomers();
	}
```

# Add Customer
1. Update list-customer.jsp
  * New “Add customer” button
2. Create HTML form for new customer
3. Process Form Data
  * Controller -> Service-> DAO
  
  # Update Customer
1. Update list-customers.jsp
  * New ”Update” link
2. Create customer-form.jsp
  * Prepopulate the form
3. Process form data
  * Controller > Service > DAO

*SAVE VS UPDATE*
* Save: INSERT new record
* Update: UPDATE existing record

saveOrUpdate(…)

if(primaryKey/ id) empty

Then INSERT new customer

else UPDATE exiting customer


# Delete Customer
1. Add “Delete” link on JSP
2. Add code for “Delete”
  * Controller > Service > DAO

# Add Search features

Overview of Development Process

1. Create the HTML form
2. Add mapping to the controller
3. Add methods in the service layer to delegate to DAO
4. Add method in the DAO to perfom search
