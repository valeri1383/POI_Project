Project Deployment Guide
This guide provides step-by-step instructions for deploying the project using NetBeans and XAMPP. It includes details on installing the necessary software, setting up the database, configuring the server, and running the project. Additionally, login details for system testing and exploration are provided.

Prerequisites
Install NetBeans and XAMPP if they are not already installed on your machine. Follow the setup wizards for each software to complete the installation.

Setup Instructions
Starting XAMPP
Open the XAMPP Control Panel.
After installing XAMPP, launch the XAMPP Control Panel.
Start the Apache and MySQL modules by clicking the 'Start' button next to each module.

Importing the Database
Access PHPMyAdmin:
Click on the 'Admin' button next to MySQL in the XAMPP Control Panel to open PHPMyAdmin in your web browser. The database is set up on port 3306.
Create a New Database:
In PHPMyAdmin, select the 'Databases' tab and create a new database named 'poi'.
Import Database Structure:
Click on the newly created database in the left-hand pane, then select the 'Import' tab in the top menu.
Choose the SQL file you received and click the 'Go' button at the bottom of the page to import the database structure and data.

Setting Up NetBeans
Open Project:
Launch the NetBeans IDE and navigate to 'File' > 'Open Project'. Locate the project files you received and select 'Open Project'.
Configure Tomcat Connection:
In NetBeans, expand the 'Services' tab, right-click on 'Servers', and select 'Add Server...'.
Choose 'Apache Tomcat or TomEE' from the menu, click 'Next', then specify the server location (default is C:\xampp\tomcat).
Enter the required credentials and click 'Finish'.

Running the Project
Build the Project:
In the 'Projects' tab, right-click on the project name and select 'Clean and Build'. NetBeans will compile the project, displaying any issues in the 'Output' window.
Run the Project:
After a successful build, right-click the project name again and select 'Run'. NetBeans will start the built-in Tomcat server and open the web application in your default browser.
Login Details
The system includes different roles for users, including administrators who can manage products and accounts, and regular users who can search, add items to a shopping cart, and make purchases.
Administrators
Administrators can manage POI and user accounts.
•	Username: admin | Password: pass
•	Username: valeri | Password: pass
Regular Users
Regular users can search, add items comment to poi, and like them.
•	Username: ico | Password: pass
•	Username: dani | Password: pass
•	Username: venci | Password: pass

This guide should assist you in deploying the project smoothly. For additional assistance, refer to the respective software documentation or contact support.
