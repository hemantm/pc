## Personal Financial Coding Exercises

#### Microservice to get plans from Elastic Search 

The code for the Lambda based Elastic Search service is in the subdirectory eswebservice.

The service is deployed at [ElasticSearch Microservice](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise).

The service provides a HTTP GET API and allows searching the plans by passing in a "field" and a "value" or simply a "scrollId".

The following field values are special values for simplicity field=plan, field=sponsor and field=state. But you can search by any field if you know the field name.

A value parameter will provide the string to search for the field.

Here are some example queries 
[http://<host>/pro/pfexercise?field=plan&value=HARVEY](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise?field=plan&value=HARVEY)
[http://<host>/pro/pfexercise?field=sponsor&value=BETTERWAY](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise?field=sponsor&value=BETTERWAY)
[http://<host>/pro/pfexercise?field=state&value=CALIFORNIA](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise?field=state&value=CALIFORNIA)
[http://<host>/pro/pfexercise?field=ADMIN_NAME&value=611000](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise?field=ADMIN_NAME&value=611000)
