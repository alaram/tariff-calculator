This is a spring boot application with very simple endpoints, so in order to test it
the project has to build and run with maven. The following endpoints are available with
example calls:

/api/tariff/{vehicleType}
http://localhost:8080/api/tariff/bus

/api/tariff/{vehicleType}/{date}
http://localhost:8080/api/tariff/bus/2013%2D01%2D14%2021%3A00%3A00

/api/tariff/{vehicleType}/{city}/{date}
http://localhost:8080/api/tariff/foreign/goteborg/2013%2D01%2D14%2021%3A00%3A00

The assumption that date will be send as part of the call required to remove dashes and spaces and use the 
ASCII representation of these characters

The test package has test for the packages that are considered important, thus business, domain and rest are 
tested to validate that they provide entities, calculations and call results.

The application can be enhanced with database creating tables for Tariff and Car entities, this is prepared 
but not implemented, at the same time boilerplate can be improved with the use of Lombok project. 
The tariffs work for the city of Gothemburg and can be implemented for other cities.

A simple front-end can be created and also swagger could be introduced to apply the calls in a GUI friendly 
way. Consider to use postman for testing the REST Api