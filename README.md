# AsellionAssignment
Spring-boot REST API + MySQL 

Testing the application

Step 1: Set up mySql, insert data using data.sql

Step 2: Generate an access token
Use the following command to generate an access token for user priya.jain: $ curl assignmentclientid:XY7kmzoNzl100@localhost:8080/oauth/token -d grant_type=password -d username=priya.jain -d password=password

Step 3: Use the token to access RESTful API
Use the generated token as the value of the Bearer in the Authorization header as follows: 
curl http://localhost:8080/api/products -H "Authorization: Bearer {token}

And will get the response 200(ok).
