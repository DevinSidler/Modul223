# M223 Punchclock

Dieses Projekt wurde zur Leistungsbeurteilung Teil C erstellt. Sie basiert auf JavaScript
und HTML im Frontend und kommuniziert mit dem Backend über eine REST-Schnittstelle. Das 
Backend wurde mit Java umgesetzt und die Datenbank ist eine H2 Datenbank.

Beim Aufrufen der Seite kann man sich Registrieren und anschliessen einloggen.
Wenn man sich angemeldet hat kann man User erstellen, bearbeiten und löschen sowie auch 
Aktivitäten erstellen, bearbeiten und löschen.

Die Applikation kann man starten, indem man Folgendes in der Konsole ausführt:
```shell script
./mvnw compile quarkus:dev
```
Anschliessend muss man die Seite http://localhost:8080 aufrufen um zum Frontend der 
Applikation zu gelangen.




H2 Console: http://localhost:8080/h2/
Datenquelle: jdbc:h2:mem:punchclock
Benutzername: zli
Passwort: zli

