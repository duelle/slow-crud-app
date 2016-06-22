#!/bin/bash

echo "NOTE: This will only work if you are running Java 7."
echo ""
echo "You are running:"
java -version
echo ""
read -p "Press <ENTER> to continue.." 

mvn clean install || echo "mvn clean install failed." && exit 1


unset MAVEN_OPTS

cd had_one_dismissal
mvn jetty:run > had_one_dismissal.log &
sleep 20
cd ..

cd auth-service 
export MAVEN_OPTS="-Dcom.sun.management.jmxremote.port=1100 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
mvn -Djetty.port=9000 jetty:run > auth-service.log &
sleep 20
cd ..

unset MAVEN_OPTS

cd sca-test-harness
mvn -Djclarity.hod.host=localhost -DJMX_AUTH_SERVER_HOST=localhost -DJMX_AUTH_SERVER_PORT=1100 exec:java
