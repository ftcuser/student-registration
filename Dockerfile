FROM tomcat:8.5-alpine
MAINTAINER "Rupesh Kumar"
ADD target/usermanager.war /usr/local/tomcat/webapps
ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh", "run"]
