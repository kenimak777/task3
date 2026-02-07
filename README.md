# Тестовое задание 3

## Запуск
```
docker compose up --build
```
Порт приложения: `http://localhost:8080`.
Логин/пароль лежат в `.env`.

## API
### 1) Приёмка поставки
`POST /api/supplies`

Пример:
```json
{
  "supplierId": "11111111-1111-1111-1111-111111111111",
  "supplyDate": "2026-02-01",
  "items": [
    { "productId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1", "weight": 100.5 },
    { "productId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3", "weight": 50.0 }
  ]
}
```

### 2) Отчёт за период
`GET /api/reports?start=2026-02-01&end=2026-02-28`

### 3) Справочники
`GET /api/suppliers`

`GET /api/products`

## Примечания
- Цены берутся из таблицы `price` по дате поставки.
- Миграции находятся в `src/main/resources/db/changelog`.
