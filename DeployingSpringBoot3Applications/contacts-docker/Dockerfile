FROM public.ecr.aws/amazoncorretto/amazoncorretto:21.0.1-al2023-headful

RUN mkdir /app
WORKDIR /app
ADD ./contacts-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENV DB_USERNAME=admin 
ENV DB_PASSWORD=pass1234
CMD ["java", "-jar", "/app/contacts-0.0.1-SNAPSHOT.jar", "&"]