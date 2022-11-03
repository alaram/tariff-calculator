This is a spring boot application with very simple endpoints, so in order to test it the project has to build 
and run with maven. The following endpoints are available with example calls:

POST call with the following JSON format
{
 "timeInterval" : "The time interval for this tariff"
 "city": "The city to create for this tariff"
 "amount" : "How much is going to be charged for the time interval"
}
/api/tariff/create
http://localhost:8080/api/tariff/create

GET call to obtain tariff for the vehicle
/api/tariff/{vehicleType}/{city}/{date}
http://localhost:8080/api/tariff/foreign/goteborg/2013%2D01%2D14%2021%3A00%3A00

The assumption that date will be send as part of the call required to remove dashes and spaces and use the 
ASCII representation of these characters.

The test package has test for the packages that are considered important, thus business, domain and rest are 
tested to validate that they provide entities, calculations and call results.

The application can be enhanced with database creating tables for Tariff, this is prepared but not implemented, 
the tariffs work for the city of Gothemburg and can be implemented for other cities if creating the Tariff entity

A simple front-end can be created and also swagger could be introduced to apply the calls in a GUI friendly 
way. Consider to use postman for testing the REST Api.


The most important design principle for RESTful Services is
Think about consumer of the API.

How does the consumer know

What is format of the request?
What content types your API supports?
What is the structure of the response?
Do you use HATEOAS?
How to test your API?
What kind of security mechanism you use?

REST does not specify a documentation standard or a contract like SOAP (WSDL). REST gives you the flexibility to choose your documentation format and approach. But that does not mean “No documentation”