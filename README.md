## Personal Financial Coding Exercises

#### Microservice to get plans from Elastic Search 

The code for the Lambda based Elastic Search service is in the subdirectory eswebservice.

The service is deployed here [ElasticSearch Microservice](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise).

The service provides a HTTP GET API and allows searching the plans by passing in a "field" and a "value" or simply a "scrollId".

The following field values are special values for simplicity field=plan, field=sponsor and field=state. But you can search by any field if you know the field name.

A value parameter will provide the string to search for the field.

Here are some example queries 
- [prod/pfexercise?field=plan&value=HARVEY](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise?field=plan&value=HARVEY)
- [prod/pfexercise?field=sponsor&value=BETTERWAY](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise?field=sponsor&value=BETTERWAY)
- [prod/pfexercise?field=state&value=CALIFORNIA](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise?field=state&value=CALIFORNIA)
- [prod/pfexercise?field=ADMIN_NAME&value=611000](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise?field=ADMIN_NAME&value=611000)

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
- [prod/pfexercise?scrollId=DnF1ZXAAAEBwxYyUEtpaEw1U1R2eTRzdFQ3OXJwMDdR](https://3dz2sv9fk6.execute-api.us-west-1.amazonaws.com/prod/pfexercise?scrollId=DnF1ZXAAAEBwxYyUEtpaEw1U1R2eTRzdFQ3OXJwMDdR)

The client gets the total number of hits in the first request and can use that to scroll through the result 10 records at a time.
