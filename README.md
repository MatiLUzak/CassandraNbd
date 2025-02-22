# Java-Cassandra-Docker-Integration

## 📌 Opis projektu

**Java-Cassandra-Docker-Integration** to aplikacja napisana w języku **Java**, która integruje się z bazą danych **Apache Cassandra**. Projekt wykorzystuje **Docker** do uruchamiania instancji Cassandry, co ułatwia zarządzanie środowiskiem bazy danych i zapewnia spójność konfiguracji.

## 🛠 Wymagania

Aby uruchomić projekt, potrzebujesz:

- **Java Development Kit (JDK) 11** lub nowszy
- **Maven** do zarządzania zależnościami i budowania projektu
- **Docker** do uruchamiania instancji Apache Cassandry

## 🚀 Instalacja i uruchomienie

1. **Klonowanie repozytorium:**

   ```bash
   git clone https://github.com/MatiLUzak/CassandraNbd.git
   cd CassandraNbd
   ```

2. **Uruchomienie Apache Cassandry za pomocą Dockera:**

   - Przejdź do katalogu `dockerCassandra`:

     ```bash
     cd dockerCassandra
     ```

   - Uruchom kontener Dockera z Cassandrą:

     ```bash
     docker-compose up -d
     ```

   *Upewnij się, że Docker jest zainstalowany i działa na Twoim systemie.*

3. **Konfiguracja połączenia z Cassandrą:**

   - Dostosuj ustawienia połączenia z bazą danych w pliku `src/main/resources/application.properties` lub innym pliku konfiguracyjnym, jeśli jest używany.

4. **Budowanie projektu za pomocą Mavena:**

   ```bash
   mvn clean install
   ```

5. **Uruchomienie aplikacji:**

   - Uruchom główną klasę aplikacji znajdującą się w `src/main/java`.
   - Aplikacja połączy się z uruchomioną instancją Cassandry i wykona zaplanowane operacje.

## 📂 Struktura projektu

```
CassandraNbd/
├── .idea/
├── diagram/
│   └── [pliki diagramów]
├── dockerCassandra/
│   ├── docker-compose.yml
│   └── [inne pliki konfiguracyjne]
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── [pakiet z kodem źródłowym]
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
├── .gitignore
├── pom.xml
└── README.md
```

- **`.idea/`** – katalog konfiguracyjny środowiska IDE.
- **`diagram/`** – zawiera diagramy związane z projektem.
- **`dockerCassandra/`** – zawiera pliki konfiguracyjne Dockera do uruchamiania Apache Cassandry.
- **`src/`** – katalog z kodem źródłowym aplikacji i zasobami.
- **`pom.xml`** – plik konfiguracyjny Mavena, definiujący zależności i pluginy.

## ✍️ Autor

- **MatiLUzak** – [Profil GitHub](https://github.com/MatiLUzak)

## 📜 Licencja

Ten projekt jest licencjonowany na podstawie licencji MIT. Szczegóły znajdują się w pliku `LICENSE`.
