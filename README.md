# A Simple Circuit Breaker with Hystrix
## Introduction

Im creating a simple service which relying on 3rd party api, we will simulate what our service will do when when it cannot access that 3rd party api.

### Configuration
```
request volume threshold        = 2
sleep window in milliseconds    = 5000
error threshold percentage      = 50
```  

It means that in order to circuit breaker work, all things must happen in that 5 second window. When in 5 seconds you will receive at least 2 requests (volume threshold) and the certain percentage of them will fail (error threshold) circuit will be opened for consecutive 5 seconds. After that time, the first request will be served to a downstream resource, and in case of success, the circuit will be closed again.