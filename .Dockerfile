FROM jboss/wildfly
RUN mkdir -p wildfly/modules/system/layers/base/com/mysql/driver/main
ADD config/mysql.module.xml
wildfly/modules/system/layers/base/com/mysql/driver/main/module.xml
ADD config/mysql-connentor-java-5.1.4.2-bin.jar
wildfly/modules/system/layers/base/com/mysql/driver/main
RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent
ADD target/jAvalanche.war wildfly/standalone/deployments/
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
