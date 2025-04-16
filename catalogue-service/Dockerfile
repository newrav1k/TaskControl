# Используем официальный образ OpenJDK
FROM eclipse-temurin:17-jdk-jammy

# Рабочая директория в контейнере
WORKDIR /app

# Копируем JAR-файл
COPY build/libs/*.jar catalogue-app.jar

# Открываем порт
EXPOSE 8081

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "catalogue-app.jar"]