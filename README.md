# Digital
The application is a basic standalone web application which reads data from an API and have exposed the below functionalities throgh an API

  - Find users who stays with X(default 50 miles) miles from London
  - Find distance between two cities
  - Find distance between two co-ordinates

# API's(Alternate documentation through swagger)
| API | Inputs |  Description |
| ------ | ------ | ------ |
| /api/v1/location/{location}/distance/{distance} | Location(Location enums), Distance(Optional fefault 50) in miles | This API takes the city and returns the users who stay within the radius of "distance" miles|
| /api/v1/distance?sourceCity=&destinationCity= | Source city and destination city parameters| Returns the distance between source and destination cities with pre configured coordinates |
| api/v1/distance/coordinates?lat1=51.50853&lon1=-0.12574&lat2=53.4808&lon2=2.2426 | Takes a pair of latitudes and longitudes and returns the distance between them |


  - Validations on latitude should be between 90 and -90
  - Validations on longitude should be -180 and 180

### Tech

All open source framework and libraries used

* Spring boot 2
* Java 11
* Apache commons
* Junit for testing

### Running instructions

Run the following on the same level as pom.xml

```sh
mvn clean install
```

```sh
java -jar DWPDigital-1.0-SNAPSHOT.jar
```

Swagger can be access through

```sh
http(s)://[server]:[port]/swagger-ui.html
```
