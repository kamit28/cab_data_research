# Cab Data Researcher API
__Description__

This application provides the REST API end-points to analyze NY cab trips.<br>
<p>
The application uses MySql database and Redis cache and the data used in the application can be found
in the file data/ny_cab_data_cab_trip_data_full.sql
</p>
The application provides following four end-points:<br>
 > 1. /search?useCache={useCache}<br>
 	- url: ``http://localhost:8080/ny_trips/search?useCache=true``<br>
 	or
 		``http://localhost:8080/ny_trips/search?useCache=false``<br>
 	The request parameter 'usecache' determines that the data will be pulled from cache (useCache=true) or the database (useCache=false)<br>
 	- Method: POST <br>
 	- Description: Pulls the number of trips for each medallion and pickupDate in the request. <br>
 	- Sample request JSON:<br>
 	
```
{
 "tripBookings":[
    {
        "medallion": "C8A5E5002322D46E2D6CB3477B4FC465",
        "pickUpDate": "2013-12-06"
    }
]}

```
 	- Sample response JSON:<br>
	
```
{
    "trips": [
        {
            "medallion": "C8A5E5002322D46E2D6CB3477B4FC465",
            "pickUpDate": "2013-12-06",
            "numTrips": 2,
            "_links": {
                "self": {
                    "href": "http://localhost:8080/ny_trips/C8A5E5002322D46E2D6CB3477B4FC465/trips?tripDate=2013-12-06&useCache=true"
                }
            }
        }
    ]
}
```

 > 2. /{medallion}/trips?tripDate={tripDate}&useCache={useCache}<br>
 	- url: ``http://localhost:8080/ny_trips/{medallion}/trips?tripsDate={tripDate}&useCache={useCache}``<br>
 	- Method: GET<br>
 	- Description: Pulls the details of the booking for the requested medallion and pickupDate. <br>
 	- Sample Request: ``http://localhost:8080/ny_trips/C8A5E5002322D46E2D6CB3477B4FC465/trips?tripDate=2013-12-06&useCache=true``<br>
 	- Sample Response JSON: 
 	
```{
    "trips": [
        {
            "medallion": "C8A5E5002322D46E2D6CB3477B4FC465",
            "pickUpDate": "2013-12-06",
            "numTrips": 0,
            "_links": {
                "self": {
                    "href": "http://localhost:8080/ny_trips/trip/70497?useCache=true"
                }
            }
        },
        {
            "medallion": "C8A5E5002322D46E2D6CB3477B4FC465",
            "pickUpDate": "2013-12-06",
            "numTrips": 0,
            "_links": {
                "self": {
                    "href": "http://localhost:8080/ny_trips/trip/73458?useCache=true"
                }
            }
        }
    ]
}
```
 	
 > 3. /trip/{id}?useCache={useCache}<br>
 	- url: ``http://localhost:8080/ny_trips/trip/{id}?&useCache={useCache}``<br>
 	- Method: GET <br>
 	- Description: Pulls the trip details for the given trip ID. <br>
 	- Sample Request: ``http://localhost:8080/ny_trips/trip/70497?useCache=true``<br>
 	- Sample Response JSON: 
 	
```{
    "id": 73458,
    "medallion": "C8A5E5002322D46E2D6CB3477B4FC465",
    "hackLicence": "-73.97245",
    "vendorId": "VTS",
    "rateCode": 1,
    "storeAndForwardFlag": "-73.97435",
    "pickupDateTime": "2013-12-06T08:23:00.000+00:00",
    "dropoffDateTime": "2013-12-06T08:36:00.000+00:00",
    "passengerCount": 6,
    "tripTimeInSeconds": 780,
    "tripDistance": 1.19
}
```
 	
 > 4. /admin/flushCache<br>
 	- url: ``http://localhost:8080/ny_trips/admin/flushCache`` <br>
 	<p>
 	This API requires HTTP Basic Authentication<br>
 	The username is: admin<br>
 	The password is: password
 	</p>

__Build__
<br>To build the executable Jar, run the following maven command in the project root directory:<br>
``$ mvn clean package``

It will create a jar cab_data_research.jar in <project root>/target directory.<br>

__Run__
Make sure that:
> 1. MySql Server is up and running. Also make sure that the database ny_cabs is created on the DB server and table cab_trip_data has been loaded from the SQL dump file.
> 2. Redis Server is up and running

<br>To run the program:<br>

``$ mvn spring-boot:run``
