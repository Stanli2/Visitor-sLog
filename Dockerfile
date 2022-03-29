FROM openjdk:17
ADD target/VisitorsLog.jar VisitorsLog.jar
ENTRYPOINT ["java", "-jar", "VisitorsLog.jar"]