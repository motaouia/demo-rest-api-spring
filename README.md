https://spring.io/guides/tutorials/rest/


Building REST services with Spring
REST has quickly become the de-facto standard for building web services on the web because they’re easy to build and easy to consume.

There’s a much larger discussion to be had about how REST fits in the world of microservices, but — for this tutorial — let’s just look at building RESTful services.

Why REST? REST embraces the precepts of the web, including its architecture, benefits, and everything else. This is no surprise given its author, Roy Fielding, was involved in probably a dozen specs which govern how the web operates.

What benefits? The web and its core protocol, HTTP, provide a stack of features:

Suitable actions (GET, POST, PUT, DELETE, …​)

Caching

Redirection and forwarding

Security (encryption and authentication)

These are all critical factors on building resilient services. But that is not all. The web is built out of lots of tiny specs, hence it’s been able to evolve easily, without getting bogged down in "standards wars".

Developers are able to draw upon 3rd party toolkits that implement these diverse specs and instantly have both client and server technology at their fingertips.

By building on top of HTTP, REST APIs provide the means to build:

Backwards compatible APIs

Evolvable APIs

Scaleable services

Securable services

A spectrum of stateless to stateful services

What’s important to realize is that REST, however ubiquitous, is not a standard, per se, but an approach, a style, a set of constraints on your architecture that can help you build web-scale systems. In this tutorial we will use the Spring portfolio to build a RESTful service while leveraging the stackless features of REST.