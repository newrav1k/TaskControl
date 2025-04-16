# Используем официальный образ OpenJDK
FROM eclipse-temurin:17-jdk-jammy

# Рабочая директория в контейнере
WORKDIR /app

# Копируем JAR-файл
COPY build/libs/*.jar manager-app.jar

# Открываем порт
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "manager-app.jar"]