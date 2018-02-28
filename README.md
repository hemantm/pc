## Personal Financial Coding Exercises

#### MonteCarlo Simulations
The code for the monte carlo simulations is in the montecarlo sub directory.
A Simulator class houses the main method which acts as a driver to run the simulation.


#### Microservice to get plans from Elastic Search 

The code for the Lambda based Elastic Search service is in the subdirectory eswebservice.

The service is deployed here [ElasticSearch Microservice](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pcexercise).

The service provides a HTTP GET API and allows searching the plans by passing in a "field" and a "value" or simply a "scrollId".

The following field values are special values for simplicity field=plan, field=sponsor and field=state. But you can search by any field if you know the field name. These field parameters map to the following fields in the schema
plan = PLAN_NAME, sponsor = SPONS_DFE_PN and state = SPONS_DFE_MAIL_US_STATE

A value parameter will provide the string to search for the field.

Here are some example queries 
- [prod/pcexercise?field=plan&value=HARVEY](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pcexercise?field=plan&value=HARVEY)
- [prod/pcexercise?field=sponsor&value=BETTERWAY](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pcexercise?field=sponsor&value=BETTERWAY)
- [prod/pcexercise?field=state&value=CALIFORNIA](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pcexercise?field=state&value=CALIFORNIA)
- [prod/pcexercise?field=ADMIN_NAME&value=611000](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pcexercise?field=ADMIN_NAME&value=611000)

The Reponse is a JSON object of the form
```
{
  "hits":[{"SPONS_DFE_MAIL_US_ADDRESS2":"10131 MILLS ROAD",...}, {"SPONS_DFE_MAIL_US_ADDRESS2":"XYZ...",...}],
  "totalHits":500,
  "count":10,
  "scrollId":"DnF1ZXAAAEBwxYyUEtpaEw1U1R2eTRzdFQ3OXJwMDdR"
}
```
The number of records is limited to 10. When the total number of hits are larger than 10 then a separate scroll API allows you to scroll through the hits 10 records at a time.

To scroll through the records simply query using the scrollId as follows
- [prod/pcexercise?scrollId=<scrollId>](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pcexercise?scrollId=enterscrollid)

The client gets the total number of hits in the first request and can use that to scroll through the result 10 records at a time.
