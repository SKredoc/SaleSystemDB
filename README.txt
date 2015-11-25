1)	Group number: 20
2)	Name: Wong Kam Shing (1155047854)
	Name: Wong Ka Lam    (1155038459)
	Name: Lam Chi Kit    (1155038234)
3)	Here is the branch structure of the SaleSystem file
	SaleSystem
	|-----README.txt
	|-----sample_data
	|	|-----category.txt
	|	|-----manufacturer.txt
	|	|-----part.txt
	|	|-----salesperson.txt
	|	|-----transaction.txt
	|-----src
		|-----CSCI3170Proj.java (contains main function, compile this)
		|-----ojdbc6.jar
		|-----com
			|-----connection
			|	|-----ConnectionManager.java
			|-----constants
			|	|-----Constants.java
			|-----entity
			|	|-----Category.java
			|	|-----Manufacturer.java
			|	|-----Part.java
			|	|-----SalesPerson
			|	|-----Transaction.java
			|-----menu
			|	|-----Menu.java
			|-----menuImpl
			|	|-----AdministratorMenu.java
			|	|-----MainMenu.java
			|	|-----ManagerMenu.java
			|	|-----SalesPersonMenu.java
			|-----operation
			|	|-----AdministrationOperation.java
			|	|-----ManagerOperation.java
			|	|-----SalesPersonOperation.java
			|-----utility
				|-----util.java
4) 	Compilation method
	1.	Go into the directory SaleSystem/src
	2.	Type "javac CSCI3170.java"
	3.	Type "java -classpath ./ojdbc6.jar:./ CSCI3170Proj"



