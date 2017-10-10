# Food Truck Radar

Food Truck Radar project consists of a user interface and a web service, where users can query the food trucks in the San Fransisco by their status or location. The results are shown with markers on Google Map.

## Getting Started

The whole project is deployed to Heroku, therefore you can check and play around with the service by clicking [here](https://foodtruckradar.herokuapp.com/). 

Developers can [clone](https://github.com/alperkaracelik/uber-coding-challange-spring-boot-repo.git) or [download](https://github.com/alperkaracelik/uber-coding-challange-spring-boot-repo/archive/master.zip) the project for examining the source code. In order to run the project in your local machine, you can apply the following steps:

### Prerequisites

Before continuing, please be sure that the following installations are ready:

1) JDK 1.8 (or above)
2) Maven 3.5.0
3) Git 2.8.1 (or above)

### Installing

For *Windows* operating systems, open a command shell and direct to the root folder of the project. Please check that *pom.xml* file is in the directory. Execute the following commands for cleaning, compiling and packaging:

```
mvn clean compile
mvn package
```

You should see a *.jar* file in the target directory. This *.jar* file includes an embedded *Tomcat* (v8.5). Run this file with the following command:

```
java -jar gs-rest-service-0.1.0.jar
```

A Spring Boot application with an embedded Tomcat server should be started. You can try sending *GET* requests to check if the web server is up and running. I have used a *Chrome* extension name [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop) during my tests.
Sample API URIs are available below in the **API** section.

## System Architecture

Food Truck Radar is a *full-stack* project that consists of a *front-end* and a *back-end* component. Front-end is a simple index.html file that accepts the user inputs, calls the API provided by the back-end and shows the results on Google Maps by using the *Google Maps API*. Back-end is a Java web service that provides a RESTful API, accepts queries with REST URIs, connects to [DataSF](https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat) by calling the DataSF API and indexes the received *JSON* objects.

![System Overview](http://url/to/img.png)

### Front-end

User interface is a single html file. User input validations, Google Maps API callings and *GET* requests to the server side are handled inside the embedded javascript block.

If user clicks to Show Food Trucks without specifiying any query inputs, then all the available food tracks are shown with a green marker on the map. A user can specify the status of food truck and / or specify a circle by entering latitude and longitude of the center of the circle and the radius of the circle. Food trucks that have the given status and / or reside in the given circle are shown on the map.
![System Overview](http://url/to/img.png)

### Back-end

Back-end is basically a Spring Boot application - a Java web application that resolves the maven dependencies automatically and uses an embedded Tomcat server. System overview is shown below.

![Web Service Overview](http://url/to/img.png)

*Rest Controller* class is a servlet class that accepts GET requests and forwards the user inputs to the *Query Handler*. Query Handler class uses the *Geodesic Distance Calculator* for queriying food trucks inside a specific circle. *Client* class calls the Data SF API, converts the received JSON string to a list of *Food Truck* objects and pushes the object to the *Accessor*. Accessor class can be interpretted as a database stub. It allows CRUD operations and maintains a map for storing all food trucks and another map for indexing the food trucks by their status.

## API 

## Future Work

Possible future works for the projects are:

1) New queries can be added (for example a query on working hours)
2) API for food truck data of other states or countries can be researched and if available, added to the web service.

## Authors

* [**Alper Karaçelik**](https://github.com/alperkaracelik)

## License

No licence is specified/required for this project.
