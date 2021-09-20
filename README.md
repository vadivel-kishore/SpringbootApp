# SpringbootApp
Sample Springboot Application



**Tools Used:**
1) STS : 4.12
2) H2 Database (InMemory)

**Introduction:**
This repository is sample Springboot Application on Customer Information
	
	- GET 	 : Retrieve customer information from database
	- POST 	 : Add customer data
	- DELETE : Delete customer data based on ID.

**URLs**

- H2 Console : http://localhost:8085/h2-console/
- ***Get all Customers*** => GET : http://localhost:8085/api/customer    
- ***Add Customer*** => POST : 	http://localhost:8085/api/customer
	
	```json
	#Request Body
	{
 	  "id": 1,
   	  "firstName": "FirstNameValue",
   	  "lastName": "LastNameValue",
     	  "emailID": "EmailValue"
	}
	```
- ***Delete Customer based on ID*** => DELETE : http://localhost:8085/api/customer/<id>
- ***Get Customers List based on First Name*** => GET : http://localhost:8085/api/customer<firstName>

	
******************************************************************************************************************************************************
**How to Create Springboot Application template using Spring starter Web:**

1) Open "Create new Spring Starter Project" window on STS
	- Service URL : https://start.spring.io
	- Name : CustomerInfo
	
	
	- Type : Maven Project
	- Packaging : Jar
	- Java Version : 11
	
	- Group : com.example.application
	- Artifact : customer
	- Version : 0.0.1-SNAPSHOT
	- Description : Demo project for Spring Boot
	- Package : com.example.application

Click "Next"

2) Select Project dependencies
		- Spring boot version : 2.5.4
		- Dependencies:
			- Spring Web
			- H2 Database
			- Spring Data JPA
			- Lombok
			
Click "Next" and "Finish"
