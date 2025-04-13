# Finance Tracker

## Description
Описание проекта
сервис для анализа трат



## Stack
### Основные технологии
- Spring Boot Web: `3.3.2`
- Spring Boot Data Jpa: `3.3.2`
- Springdoc Openapi Starter Webmvc (Swagger): `2.3.0`

### Базы данных и миграции
- PostgreSQL Driver: `42.7.3`
- Flyway: `10.10.0`

### Библиотеки для упрощения работы
- Lombok: `1.18.34`
- MapStruct: `1.5.5.Final`
- Lombok MapStruct Binding: `0.2.0`

### Тестирование
- Spring Boot Test: `3.3.2`
- JUnit: `1.10.2`



## PreDeployment requirements
Для успешного развертывания и запуска сервиса убедитесь, что на вашей
машине установлены следующие компоненты:
- Java: `17` и выше
- Gradle
- Docker
- Docker compose



## Fast Uses
1. Клонируйте репозиторий:
```shell
git clone https://github.com/nuto17/tracker-of-finance
```
2. Заполните переменные среды в файле `.env.docker` своими значениями


3. Соберите `.jar` файл
```shell
./gradlew clean bootJar
```

4. Запустите образ с автоматической сборкой
```shell
docker-compose --env-file .env.docker up -d

```

## System endpoints
Swagger endpoint
```
http://localhost:8887/swagger-ui.html
```

## Contribution
Для локального запуска потребуется:

1. Создайте переменные окружения в новом файле `.env`, по
   примеру `.env.example` и загрузить переменные в систему.


2. Запустите проект с автоматической сборкой:
```shell
docker-compose --env-file .env.docker up -d --build
```

3. Запустите приложение через UI или командой:
```shell
./gradlew bootRun
```
