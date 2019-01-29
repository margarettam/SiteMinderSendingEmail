## Sending Email
Prerequisite
Eclipse Version: 2018-09 (4.9.0)
JavaSE-1.8

repo: https://github.com/margarettam/SiteMinderSendingEmail.git
site: http://13.237.196.250:8080/SpringBootMail/

The configure file of email servers is at following path, change accordingly
classpath/src/main/resources/application.properties

Build steps
1. git clone https://github.com/margarettam/SiteMinderSendingEmail.git
2. Eclipse->Open Projects From File System->Import Source from local directory
3. Make sure Spring Tools 4 and Java™ 8 m2e 1.4 is installed for the Eclipse
3a. Otherwise go to Eclipse->Help->Eclipse Marketplace->Search and install Spring Tools 4 and Java™ 8 m2e 1.4
3b. After install restart Eclipse
4. Right click on the project->Run as->Spring Boot App
5. Open link http://localhost:8080/

Deploy steps @ ubuntu
1. Right click on the project->Run as->Maven Build
2. After build successful, a war file should be under classpath>target
3. Rename the war file to SpringBootMail.war
4a. Use tomcat-manager for deployment or
4b. Copy the war to web server and put it under tomcat webapps folder
	If the service is running, stop it by running command at terminal : sudo service tomcat stop
	extract the war file: jar -xvf WorkForceManagement.war
	start the server: sudo service tomcat start
	
	
	
	