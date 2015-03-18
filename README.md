# InventoryApp
Inventory application

General configuration:

1. To make available and save pictures, add a context element inside a host element in the server.xml config file of the tomcat server.

  <Context docBase="c:/items/pictures" path="/uploads" reloadable="true" crossContext="true" />
  
The docBase path is configured in the application.properties file of the web application project so the images are saved in this path.
