1. Микросервисы можно запускать как в докере, так и локально
2. Локальный запуск:
- запустить rabbitmq (docker run -d -p 5672:5672 rabbitmq:3 или rabbitmq-server start)
- запустить zipkin (docker run -d -p 9411:9411 openzipkin/zipkin:latest)
- выставить SERVICE_URL_DEFAULT_ZONE в идее в running configurations
- указать профиль в Running Configurations, например qa
3. Запуск в docker:
- уже всё настроено в docker-compose.yml, надо просто запустить
- sudo docker-compose build
- sudo docker-compose up
4. Запуск в kubernetes:
- создать namespace=micro
- создать configmap из папки currency-conversion-service
- выполнить apply -f всех deployment скриптов из папки currency-exchange-service
5. Описание сервисов и проверка работоспособности:
- выполнить http://35.241.3.52/currency-exchange/USD/to/INR - просто статический вызов, убедиться что работает
- выполнить http://35.241.3.52/currency-converter/discovery/from/USD/to/INR/quantity/1111 - проверка работы кубер дискавери
- выполнить http://35.241.3.52/currency-converter/uri/from/USD/to/INR/quantity/1111 - демонстрация работы с env переменными через configmap, deployment.yml, application.properties. Можно раскомментировать в ExchangeProxyUri разные варианты
- выполнить http://35.241.3.52/currency-converter/zuul/from/USD/to/INR/quantity/1111 - проверка вызова через zuul gateway (опционально)
- выполнить http://35.241.3.52/currency-converter/from/USD/to/INR/quantity/1111 - проверка и демонстрация подстановки env переменных, заинжекченных кубером
- открыть логи подов currency-conversion-service и currency-exchange-service, не должно быть ошибок и connection refused
- опционально: открыть веб морду zipkin, удостовериться что всё трассируется 
- если использован autoscaling то посмотреть количество подов