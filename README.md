# AutoHeroSearch
Task 2 – Create Automation test for search functionality
Framework follows Page Object Model design pattern and framework is designed in a generic way so that there should not be any rework on adding additional testcases .
Framework is developed using Java, Selenium Web driver, Maven, TestNG and Extent report
How To Execute:- 
Run as TestNG Test from testng.xml or From WebTest.java
Check Report:- 
After execution of the tests extent report gets generated in “Reports” folder, along with logging and screenshots attached for the failed steps.
Browser Support: -
IE, Chrome, Firefox browsers are supported, Browser type can be selected from config.properties
Get test data from external file:-
Reading values from properties files: - application.properties and config.properties
Folder Structure: -
a.	AutoHero.Testcases -
WebTest.java consists of test case. Search.Java consists of all the locaters of search page needed and calls all the webdriver actions need to be performed the scenario. It also uses AutoUtil.java methods to verify the search results.
b.	Dataprovider
The Classes In this package are used to read data from properties file
c.	Factory
This package Consists of Browser Factory
d.	Utils
This package consists reusable Selenium Web driver actions and reusable utils needed to verify results.
