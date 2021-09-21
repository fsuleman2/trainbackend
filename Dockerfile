FROM openjdk:8

ADD target/revature-railways-backend.jar revature-railways-backend.jar



ENTRYPOINT ["java","-jar","revature-railways-backend.jar"]