slow-crud-app
=============

slow-crud-app is a 'typical' Web CRUD App using Spring, Hibernate and other 'typical' enterprise Java technologies. 
It's key purpose is to provide configurable bad performance characteristics through JMX, such as Deadlocks, heavy 
Disk I/O, heavy Network I/O, CPU trashing etc.

slow-crud-app can be used for testing performance theories, teaching, performance tuning workshops etc.

slow-crud-app is a fake application which hosts job listings for people who only had one job (onlyhadonejob.com).

Disclaimer
==========

This project is not truly a 'real world' application. It is an experimental project with the goal of providing a 
pluggable framework in which to add different performance scenarios such as Deadlocks, waiting on 3rd party resources, 
Garbage Collection pauses, Disk I/O and much, much more.

There is deliberately badly performing code in slow-crud-app, please do ask on the Friends of jClarity mailing list first!

How to help
===========

Development of slow-crud-app is currently discussed through the "Friends of jClarity" community mailing list. You can sign-up 
via http://www.jclarity.com/.

Bad Performance Characteristics
===============================

The following performance characteristics have been implemented:

* Waiting on 3rd party resource (RESTFul call)
* CPU Bound code
* Too much logging and Disk I/O
* Too much context switching
* Lock contention problems
* Deadlock

Building slow-crud-app
=====================

slow-crud-app is a Maven 3 project

```mvn clean install```

Running slow-crud-app
=====================
   
slow-crud-app is a jetty plugin enabled Maven 3 project and will start on localhost:8080/had_one_dismissal

```
cd had-one-dismisaal
mvn jetty:run
```

Also, you'll want to bring up the 3rd party service

```
cd auth-service
mvn jetty:run
```

Configuring slow-crud-app
=========================

TODO
