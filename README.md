---Инструкция по развертыванию---
1) Установить MongoDB:
    Загрузите MongoDB с официального сайта MongoDB и установите.
    Убедитесь, что MongoDB запущен и слушает порт 27017.
2) Настроить переменные для OAuth2:
    В файле конфигурации application.yml укажите
    значения для переменных issuer-uri и jwk-set-uri. Эти параметры необходимы для
    работы с OAuth2, они определяют URL-адреса вашего провайдера OAuth2:
      issuer-uri — базовый URI сервера авторизации.
      jwk-set-uri — URI для получения набора ключей JWT (используется для проверки токенов).
3) Запустить проект:
    Выполните команду ниже, чтобы запустить проект с помощью Maven:
      mvn spring-boot:run
4) API тестирование:
    В файле конфигурации application.yml укажите port сервера.
    После запуска сервера приложение будет доступно на http://localhost:port.
    Используйте cURL или инструменты API-клиентов (например, Postman) для тестирования эндпоинтов,
    передавая access_token в запросах для защищённых маршрутов.
   
---Примеры cURL запросов---
Замени ACCESS_TOKEN на действительный токен доступа, который был получен от сервера авторизации. 

Получить список студентов по ID (POST запрос): 
curl -X POST http://localhost:8080/api/v1/students/search-students \
     -H "Authorization: Bearer ACCESS_TOKEN" \
     -H "Content-Type: application/json" \
     -d '[1, 2, 3]' 

Получить информацию о студенте по ID (GET запрос)
curl -X GET http://localhost:8080/api/v1/students/1 \
     -H "Authorization: Bearer ACCESS_TOKEN"

Создать нового студента (PUT запрос)
curl -X PUT http://localhost:8080/api/v1/students \
     -H "Authorization: Bearer ACCESS_TOKEN" \
     -H "Content-Type: application/json" \
     -d '{
           "lastName": "Doe",
           "firstName": "John",
           "middleName": "A",
           "group": "A1",
           "averageScore": 4.5
         }'

Обновить данные студента (POST запрос)
curl -X POST http://localhost:8080/api/v1/students/1 \
     -H "Authorization: Bearer ACCESS_TOKEN" \
     -H "Content-Type: application/json" \
     -d '{
           "lastName": "Smith",
           "firstName": "John",
           "middleName": "B",
           "group": "B2",
           "averageScore": 3.8
         }'

Удалить студента (DELETE запрос)
curl -X DELETE http://localhost:8080/api/v1/students/1 \
     -H "Authorization: Bearer ACCESS_TOKEN"
