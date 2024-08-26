FROM openjdk:22

WORKDIR /app

COPY . /app

# Pastikan `gradlew` memiliki izin eksekusi
RUN chmod +x ./gradlew

# Jalankan build dan tambahkan debugging log
RUN ./gradlew build

RUN cp build/libs/kotlin-restful-api-0.0.1-SNAPSHOT.jar /app/application.jar

CMD ["java", "-jar", "/app/application.jar"]
