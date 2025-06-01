# Проект Hibernate для магазина видеопроката

## Описание
Проект демонстрирует работу с базой данных MySQL через Hibernate.
**Цель:** Маппинг сложной схемы БД, реализация транзакционных операций и анализ структуры данных.
Так же необходимо выявить критичные секции и предложить способ решения.



### 🧰 **Технологический стек**
- ORM: Hibernate 5.6 + Jakarta
- БД: MySQL 8.0 с драйвером P6Spy
- Логирование: SLF4J + Logback 
- Сборка: Maven


### 🔍 **Проблемные места в структуре БД**
❌ Отсутствие foreign key в таблице film_text → риск нарушения целостности данных  
⚠️ Избыточность данных в таблице address (дублирование городов)  
🔄 Некорректные индексы для частых запросов аренды  
📅 Поле last_update не обновляется триггерами  
🆔 Отсутствие SEQUENCE для автоинкрементных полей


### 🛠 **Рекомендации по улучшению**
```plaintext
-- Добавление недостающих ограничений
ALTER TABLE film_text
ADD CONSTRAINT fk_film_text FOREIGN KEY (film_id) REFERENCES film(film_id);

-- Создание составного индекса
CREATE INDEX idx_rental_dates ON rental (rental_date, return_date);

-- Нормализация таблиц
CREATE TABLE postal_code (
postal_code_id INT PRIMARY KEY,
code VARCHAR(10) NOT NULL
);
```
