A microservice which handles login, authentication, questions, quiz, etc each as a separate service.


1.each service has it's own database.
2.one service communicates with other using feign client to fetch data from other service
3.feign client is depended on eureka.Here eureka means a server registry which has all registered instances(services running on diff ports).
4.so with help of eureka, feign client fetch data.
5.instead of using diff ports for fetching data from services, we can use api gateway,which
means it is a reverse proxy server which forwards the incoming req from client to the appropriate service
with help of eureka server.