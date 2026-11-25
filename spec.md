# Kryteria akceptacji

Ta sekcja jest ściśle powiązana z testowaniem. Kryteria akceptacji pozwalają na zweryfikowanie, czy system realizuje to, co zostało opisane w wymaganiach.

| AC-01 | Poprawne utworzenie nowej rezerwacji |
|-------|--------------------------------------|
| G     | w systemie istnieje pomieszczenie `Space`, a czas rozpoczęcia `startTime` jest późniejszy niż 2 godziny od bieżącego czasu w strefie `Europe/Stockholm`, natomiast czas zakończenia `endTime` jest późniejszy niż `startTime + 1 godzina` |
| W     | użytkownik wysyła żądanie `POST /rsvs` z poprawnymi danymi rezerwacji: `startTime`, `endTime`, `space` |
| T     | system zapisuje nową encję `Reservation` w bazie danych, nadaje jej unikalne `id` i zwraca utworzony obiekt |

| AC-02 | Odrzucenie rezerwacji ze zbyt wczesnym czasem rozpoczęcia |
|-------|-----------------------------------------------------------|
| G     | podany czas rozpoczęcia rezerwacji `startTime` jest wcześniejszy lub równy `aktualny czas + 2 godziny` (według strefy `Europe/Stockholm`) |
| W     | użytkownik wysyła żądanie utworzenia `POST /rsvs` lub aktualizacji `PUT /rsvs` rezerwacji |
| T     | system zgłasza błąd walidacji typu `IllegalArgumentException` z komunikatem o wymaganym 2-godzinnym wyprzedzeniu i nie zapisuje zmian w bazie danych |

| AC-03 | Odrzucenie rezerwacji o zbyt krótkim czasie trwania |
|-------|----------------------------------------------------|
| G     | podany czas zakończenia rezerwacji `endTime` musi następować co najmniej o 1 godzinę później niż czas rozpoczęcia rezerwacji |
| W     | użytkownik wysyła żądanie utworzenia `POST /rsvs` lub aktualizacji `PUT /rsvs` rezerwacji |
| T     | system zgłasza błąd walidacji typu `IllegalArgumentException` z komunikatem o minimalnym czasie trwania rezerwacji i odrzuca operację |

| AC-04 | Aktualizacja istniejącej rezerwacji |
|-------|-------------------------------------|
| G     | w bazie danych istnieje rezerwacja o podanym `id`, a nowe wartości `startTime`, `endTime` oraz `space` spełniają reguły walidacji czasowej |
| W     | użytkownik wysyła żądanie `PUT /rsvs` z identyfikatorem istniejącej rezerwacji oraz zaktualizowanymi danymi |
| T     | system aktualizuje pola `startTime`, `endTime` oraz przypisaną salę `space`, zapisuje zmiany i zwraca zaktualizowany obiekt `Reservation` |

| AC-05 | Usunięcie rezerwacji oraz obsługa nieistniejącego identyfikatora |
|-------|------------------------------------------------------------------|
| G     | w bazie danych istnieje rezerwacja o określonym `id` (scenariusz pozytywny) lub nie istnieje rekord o podanym `id` (scenariusz negatywny) |
| W     | użytkownik wysyła żądanie `GET /rsvs/{id}` z danym identyfikatorem |
| T     | dla istniejącego rekordu system usuwa rezerwację z bazy i zwraca jej dane; w przypadku braku rekordu o podanym `id` system rzuca wyjątek |
