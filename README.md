<p align="center">
  API for managing delivery, kitchens and restaurants.
</p>

<p align="center" >
  <img align="center" src="https://img.shields.io/github/last-commit/elielcena/cenafood">
  <img align="center" src="https://img.shields.io/github/languages/top/elielcena/cenafood" />
</p>

<div id="tabela-de-conteudo" />

## :point_right: Summary
- [💬 About](#about)
- [:rocket: Technologies](#tech)
- [📦️ Prerequisites](#️req)
- [:octocat: Running API](#run)
- [🎨 ER diagram](https://github.com/elielcena/cenafood/blob/main/.github/images/cenafood.png)
- [:memo: License](#license)

<div id="about" />

## 💬 About
This project was built through the ESR course taught by [AlgaWorks](https://www.algaworks.com/).

<div id="tech" />

## :rocket: Tecnologias
- :heavy_check_mark: Language **[Java 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)**;
- :heavy_check_mark: Frameworks **[Spring Boot](https://spring.io/projects/spring-boot)**, **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)** and **[Spring Security OAuth](https://spring.io/projects/spring-security-oauth)**;
- :heavy_check_mark: Reports **[Jaspersoft® Studio](https://community.jaspersoft.com/project/jaspersoft-studio)**;
- :heavy_check_mark: Documentation **[Swagger](https://swagger.io/)**;
- :heavy_check_mark: Database **[PostgreSQL](https://www.postgresql.org/)**;
- :heavy_check_mark: Deploy **[Docker](https://www.docker.com/)**;

<div id="req" />

## 📦️ Prerequisites
Before starting, you will need to have the tools [Git] (https://git-scm.com/downloads), [JDK 11] (https://www.oracle.com/us/java/technologies/javase-jdk11 -downloads.html), [Docker](https://www.docker.com/),
[PostgreSQL](https://www.postgresql.org/) installed on your machine.


<div id="run" />

### Running API

## :octocat: Clone the Project
```git
  # Cloning
  ❯ git clone https://github.com/elielcena/cenafood.git
 ```

```bash
 # Access the cenafood folder on the terminal
 $ cd cenafood

 # Build the project
 $ ./mvnw clean install -Pdocker

 # Running docker-compose
 $ docker-compose up
 
 # Enjoy!
```

<div id="license" />

## :memo: Licença
This project is under the MIT license. See the [LICENSE](LICENSE.md) file for more details.

