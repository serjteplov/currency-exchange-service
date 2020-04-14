1. Микросервисы можно запускать как в докере, так и локально
2. При этом, если запускаем локально, то должны быть запущены:
- rabbitmq (rabbitmq-server start)
- zipkin (docker run -d -p 9411:9411 openzipkin/zipkin:latest)
3. При локальном запуске в идее, должен быть указан профиль, 
например qa
4. Запуск через докер:
sudo docker-compose build
sudo docker-compose up