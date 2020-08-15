# Cab Data Researcher API
__Description__

This application provides the REST API end-points to analyze NY cab trips.<br>

The application provides following two end-points:<br>
 > 1. /search?useCache={useCache}<br>
 	- url: ``http://localhost:8080/ny_trips/search?useCache=true``<br>
 	- Sample request JSON:<br>
 	
```
{
 "tripBookings":[
    {
        "medallion": "08B8CD4A3E1A0804F67F9B4328411987",
        "pickUpDate": "2013-12-06"
    }
]}

```
 	- Sample response JSON:<br>
	
```
{
"trips": [
    {
        "medallion": "08B8CD4A3E1A0804F67F9B4328411987",
        "numTrips": 1
    }
]
}
```
	
 > 2. /admin/flushCache<br>
 	- url: ``http://localhost:8080/ny_trips/admin/flushCache``
 	<p>
 	This API requires HTTP Basic Authentication<br>
 	The username is: admin<br>
 	The password is: password
 	</p>

__Build__
<br>To build the executable Jar, run the following maven command in the project root directory:<br>
``$ mvn clean package``

It will create a jar cab_data_client.jar in <project root>/target directory.<br>

__Run__
<br>To run the program:<br>
``$ mvn spring-boot:run``
