# Cab Data Researcher API
__Description__

This application provides the REST API end-points to analyze NY cab trips.<br>

The application provides following two end-points:<br>
 1. /search?useCache={useCache}<br>
 	- url: ``http://localhost:8080/ny_trips/search?useCache=true``<br>
 	- Sample request JSON:<br>
 ```json
 {"tripBookings":[
    {
        "medallion": "",
        "pickUpDate": "2013-12-06"
    }
]}
```
 2. /admin/flushCache
 <p></p>

__Build__
<br>To build the executable Jar, run the following maven command in the project root directory:<br>
``$ mvn clean package``

It will create a jar cab_data_client.jar in <project root>/target directory.<br>

__Run__
<br>To run the program:<br>
``$ mvn spring-boot:run``
