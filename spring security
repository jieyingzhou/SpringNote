* Secure Spring MVC Web Apps
* Develop login pages(default annd custom)
* Define users and roles with simple authentication
* Protect URLs based on role
* Use JSP tags to hide / show content based on role
* Store users, passwords and roles in DB(plain-text-encrypted)

# Spring Security Model
* Spring Security defines a framework for security
* Implemented using Servlet filters in the background
* Two methods of securing a Web app: declarative and programmatic

# Spring Security with Servlet Filters
* Servlet Filters are used to pre-process/post-process web requests
* Servlet Filters can rout web requests based on security logic
* Spring provides a bulk of security functionality with servlet filters

# Security Concepts
* Authentication
  * Check user id and password with credentials stored in app/db
* Authorization
  * Check to see if user has an authorized role

# Declarative Security
* Define application’s security constrints in configuration 
  * All Java config(@Configuration, no xml)
  * or Spring XML config
* Provides separation of concerns between application code and security

# Different Login Methods
* HTTP Basic Authentication
* Default login form
  * Spring Security provides a default login form

* Custom login form
  * your own look-and-feel, HTML + CSS

# Authentication and Authorization
* In-memory
* JDBC
* LDAP
* Custom / Pluggable
* others …

# Spring MVC App - Java Config

*Step by Step*
1. Add Maven dependencies for Spring MVC Web App
2. Create Spring App Configuration(@Configuration)
3. Create Spring Dispatcher Servlet Initializer
4. Develop our Spring controller
5. Develop our JSP view page

# Enabling the MVC Java Config

@EnableWebMvc

* Provides similar support to<mvc:annotation-driven /> in XML
* Add conversion, fomatting and validation support
* Processing of @Controller classes and @RequestMapping etc… methods

# Web App Initializer
* Spring MVC provides support for web app initialization
* Makes sure your code is automatically detected
* Your code is used to initialize the serlet container

*AbstractAnnoatationConfigDispatcherServletInitializer*
* Extend this abstract base class
* Override required methods
* Specify servlet mapping and location of your app config
