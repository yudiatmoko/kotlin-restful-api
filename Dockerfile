# Gunakan image OpenJDK sebagai base image
FROM openjdk:22

# Tetapkan direktori kerja
WORKDIR /app

# Salin semua file dari direktori lokal ke direktori kerja di container
COPY . .

# Pastikan gradlew dapat dieksekusi dan build project
RUN chmod +x ./gradlew && ./gradlew build

# Salin hasil build ke direktori yang sesuai
RUN cp build/libs/kotlin-restful-api-0.0.1-SNAPSHOT.jar /app/application.jar

# Tentukan perintah yang akan dijalankan saat container dimulai
CMD ["java", "-jar", "/app/application.jar"]
