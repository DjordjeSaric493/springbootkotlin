**izgenerisao cgpt da se ne bi drkao sa papirom koji bi izgubio u roku od odmah kao uvek

# 🎵 Muzički Kviz - Backend Baza i Logika

Ovaj dokument opisuje model baze podataka i osnovnu logiku za API 
koji će služiti Android TV aplikaciji za muzički kviz.

## 📚 Pregled sistema

Sistem omogućava sledeće funkcionalnosti:
- Upravljanje pesmama koje se koriste u kvizu.
- Čuvanje isečaka i slika albuma.
- Generisanje pitanja sa ponuđenim odgovorima.
- Praćenje rezultata igrača.

## 🗄️ Modeli i tabele

### 1️⃣ **Tabela: songs**

Informacije o svakoj pesmi koja se koristi u kvizu.

| Polje          | Tip       | Opis                                    |
|----------------|-----------|-----------------------------------------|
| id             | Long      | Primarni ključ                          |
| title          | String    | Naziv pesme                             |
| artist         | String    | Izvođač                                 |
| album          | String    | Album na kojem je pesma                 |
| release_year   | Int       | Godina izdanja                          |
| trivia         | String    | Kratka zanimljivost o pesmi             |
| cover_url      | String    | URL slike albuma (blurovane)            |
| audio_clip_url | String    | URL isečka za puštanje                  |
| created_at     | Timestamp | Datum unosa                             |

---

### 2️⃣ **Tabela: users**

Podaci o korisnicima koji igraju kviz.

| Polje      | Tip       | Opis                         |
|------------|-----------|------------------------------|
| id         | Long      | Primarni ključ               |
| username   | String    | Jedinstveno korisničko ime   |
| created_at | Timestamp | Datum registracije          |

---

### 3️⃣ **Tabela: game_sessions**

Jedna sesija igre koju je korisnik igrao.

| Polje        | Tip       | Opis                                     |
|--------------|-----------|------------------------------------------|
| id           | Long      | Primarni ključ                           |
| user_id      | Long      | Strani ključ ka `users`                  |
| score        | Int       | Konačan broj poena u sesiji              |
| started_at   | Timestamp | Vreme početka igre                       |
| finished_at  | Timestamp | Vreme završetka igre                     |

---

### 4️⃣ **Tabela: game_answers**

Odgovori koje je korisnik dao tokom igre.

| Polje         | Tip       | Opis                                   |
|---------------|-----------|----------------------------------------|
| id            | Long      | Primarni ključ                         |
| session_id    | Long      | Strani ključ ka `game_sessions`        |
| song_id       | Long      | Strani ključ ka `songs`                |
| selected      | String    | Tekst odgovora koji je korisnik izabrao|
| correct       | Boolean   | Da li je odgovor bio tačan             |
| answered_at   | Timestamp | Vreme odgovora                         |

---

## 🧩 Primer toka igre

1. Klijent traži listu pesama za novu igru.
2. Backend šalje `n` random pesama sa podacima (bez trivia).
3. Korisnik odabere odgovor.
4. Backend proverava i pamti rezultat u `game_answers`.
5. Na kraju igre rezultat se upisuje u `game_sessions`.
6. Po želji, klijent može zatražiti `trivia` za prikaz nakon svakog pitanja.

---

## 🛠️ Dalja proširenja


- Čuvanje statistike po pesmi (koliko puta je pogođena).
- Kategorije i filteri po žanru.

---**

