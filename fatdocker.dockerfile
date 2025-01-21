FROM alpine:latest
WORKDIR /app
RUN apk add --no-cache \
openjdk17 \
git \
bash \
&& rm -rf /var/cache/apk/*

# Establecer JAVA_HOME para Java 17

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk
ENV PATH="$JAVA_HOME/bin:$PATH"

# Comprobar la instalación de Java y Git

RUN java -version && git --version
RUN	git clone https://github.com/josechocobar/Constraints /fat
RUN	ls -l /app
RUN chmod +x /app/fat.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/fat.jar"]
CMD ["--server.port=8080"]
