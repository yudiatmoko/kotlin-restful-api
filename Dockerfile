# Gunakan image OpenJDK sebagai dasar
FROM openjdk:22

# Set direktori kerja di dalam container
WORKDIR /app

# Salin semua file proyek ke dalam container
COPY . /app

# Jalankan build menggunakan Gradle
RUN ./gradlew build

# Salin hasil build ke lokasi yang diinginkan dalam container
RUN cp build/libs/kotlin-restful-api-0.0.1-SNAPSHOT.jar /app/application.jar

# Tentukan command untuk menjalankan aplikasi
CMD ["java", "-jar", "/app/application.jar"]
