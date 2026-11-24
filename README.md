# test-book-app

API REST pentru gestionarea cărților și a editurilor, făcut cu Java 17, Spring Boot, Spring Data JPA și H2 (sau PostgreSQL).

## Pornire

```bash
./mvnw spring-boot:run
```

Aplicația pornește pe `http://localhost:8080` cu o bază de date H2 în memorie, populată din `data.sql` (5 edituri și 5 cărți). Consola H2 e la `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:bookdb`, user `sa`, fără parolă).

### Cu PostgreSQL

Creează o bază de date `bookdb`, apoi:

```bash
DB_URL=jdbc:postgresql://localhost:5432/bookdb DB_USER=postgres DB_PASSWORD=parola \
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```

Tabelele se creează automat. Cu PostgreSQL nu se încarcă datele inițiale.

## Endpoint-uri

| Metodă | URL | Descriere |
|--------|-----|-----------|
| GET | `/books` | lista cărților |
| GET | `/books/{id}` | o carte (404 dacă nu există) |
| POST | `/books` | adaugă o carte (201) |
| PUT | `/books/{id}` | modifică o carte |
| DELETE | `/books/{id}` | șterge o carte (204) |
| GET, POST, PUT, DELETE | `/publishers`, `/publishers/{id}` | la fel, pentru edituri (ștergerea e refuzată cu 409 dacă editura are cărți) |

Exemple:

```bash
curl http://localhost:8080/books

curl -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{"title": "Carte nouă", "isbn": "123456", "publisherId": 1}'
```

Datele invalide primesc răspuns 400 cu detalii pe câmp, iar resursele inexistente primesc 404, în format JSON.

## Teste

```bash
./mvnw test
```

Testele (MockMvc) acoperă listarea, adăugarea, modificarea, ștergerea, validările și erorile 400, 404 și 409.

## Structura proiectului

- `controller` – endpoint-urile REST
- `service` – logica aplicației
- `repository` – accesul la baza de date (Spring Data JPA)
- `model` – entitățile `Book` și `Publisher`
- `dto` – obiectele primite în cereri, cu validări
- `exception` – excepții proprii și tratarea centralizată a erorilor
