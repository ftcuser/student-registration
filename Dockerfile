FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
   JHIPSTER_SLEEP=0 \
   JAVA_OPTS=""

COPY target/mongo-0.0.1-SNAPSHOT.war .

CMD echo "The application will start in ${JHIPSTER_SLEEP}s..." && \
    sleep ${JHIPSTER_SLEEP} && \
   java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar mongo-0.0.1-SNAPSHOT.war
    
 











