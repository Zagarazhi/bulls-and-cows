# Описание игры.
   Компьютер загадывает 4-х значное число. Цифры загаданного числа не повторяются. Задача пользователя угадать загаданное число. У пользователя неограниченное число попыток. В каждую попытку пользователь дает компьютеру свой вариант числа. Компьютер сообщает сколько цифр точно угадано (бык) и сколько цифр угадано без учета позиции (корова). По ответу компьютера пользователь должен за несколько ходов угадать число.
   # Пример:
   - 7328 -- загаданное число
   - 0819 -- 0Б1К
   - 4073 -- 0Б2К
   - 5820 -- 0Б1К
   - 3429 -- 1Б1К
   - 5960 -- 0Б0К
   - 7238 -- 2Б2К
   - 7328 -- 4Б0К (число угадано)
# Реализовано:
- регистрация, авторизация, функционал игры, отображение истории попыток текущего игрока, информация об игроке и его играх

# Доступ:
- Сайт: http://localhost:8080/
- БД: http://localhost:8080/h2-console
- При каждом запуске БД создается заново

# Используемые технологии:
- 1)
 - - Java 17
 - - Spring Boot 2.7.0
 - - Javax Validation 2.0.1.Final
 - - Hibernate Validator 7.0.4.Final
 - - Jackson 2.13.3
- 2) БД H2
- 3) Обмен между фронтом и бэком ведется через JSON формат. 
- 4) Фронт: HTML + JS + bootstrap.
