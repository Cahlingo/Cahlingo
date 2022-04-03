# CAHLINGO's grocerystore Final Exam Javacourse

### Project Description:

* A CLI based program that allows the user to interact with a database containing groceries,transactions and customers
* Create fictional orders using a fixed menu of products that are queried from the database
* Returning customers can place a new order with their already existing customerID
* Users can also query the history of previously placed orders using the CLI menu


### Dependencies

* Java 17
* MySQL Connector 
* Maven

### Testing Dependencies

* H2 (Used to simulate a database with database in memory)
* JUnit 5 and Hamcrest for testing
* Mockito





### Setup Program

</br>

* Program uses a MySQL server running on localhost port 3306 
* My suggestions is to run it via a docker container using the command:

    `docker run --name test-mysql -e MYSQL_ROOT_PASSWORD=dempa123 -p 3306:3306 -d mysql`

* `mvn install` to install all the required dependencies 
* Everything runs from file Main.java 
* Login credentials are configured with the docker command above. 
  username **"root"** password **"password"**


