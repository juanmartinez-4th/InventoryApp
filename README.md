# InventoryApp
Inventory application

General configuration:

1. To make available and save pictures, add a context element inside a host element in the server.xml config file of the tomcat server.

  <Context docBase="c:/items/pictures" path="/uploads" reloadable="true" crossContext="true" />
  
The docBase path is configured in the application.properties file of the web application project so the images are saved in this path.



Import correctly catalogs

mysql -uroot -p --default-character-set=utf8 database
mysql> source sqlfile.sql


Configure tomcat
in ${tomcat-folder}\bin\setenv.bat file

set JAVA_OPTS=-Dfile.encoding=UTF-8 -Xms128m -Xmx1024m -XX:PermSize=64m -XX:MaxPermSize=256m