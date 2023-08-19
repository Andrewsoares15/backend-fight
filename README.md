# backend-fight

Github: [@Andrewsoares](https://github.com/Andrewsoares15) | Twitter/X: [@andrewwbxd](https://twitter.com/andrewwbxd)

Project Url [repository](https://github.com/Andrewsoares15/backend-fight)
## Vasco
![Vasco](https://github.com/Andrewsoares15/backend-fight/blob/master/download.png) <!-- Substitua "link_da_sua_imagem_do_vasco.jpg" pelo caminho correto da sua imagem no repositório -->

## ⚠️ Playful Reminder
This project is playfully experimental and designed solely for the purposes of a developer challenge and fun engagement on social media. While it's optimized for performance exploration, it's not equipped with the necessary considerations and robustness required for real-world production environments. If you're seeking a battle-tested solution for actual production deployment, we strongly recommend looking into more suitable options. 
#### Let's have fun with us, in the next edition. Follow to more informations https://twitter.com/rinhadebackend

### Technologies
  - Java 17
  - Spring Boot
  - Docker / Docker compose (container orchestrator)
  - Redis (Distributed Cache)
  - Nginx (Load balancer)
  - Postgres (Database)

### Requirements
  - [Docker](https://docs.docker.com/engine/install/)

### How to run
  - execute `docker-compose up`
  - **API** will be available in the URL `http://localhost:9999`
  - **Swagger** will be available in the  URL `http://localhost:9999/swagger`
  - API Endpoints available
     - `GET /ping` health check, return `pong` if the API is up.
     - `POST /pessoas` for creating a record in the table `People` using the format message below
        ```json
        {
            "apelido" : "josé 12",
            "nome" : "José Roberto",
            "nascimento" : "2012-03-08T00:00:00",
            "stack" : ["C#", "Node", "Oracle"]
        }
        ``` 
     - `GET /pessoas/[id]` for retrieving a record of a person created using replacing `[id]` with the `person id`.
     - `GET /pessoas/t=[your search]` for retrieving records that contain the words informed, replace `[your search]` with your actual search.
     - `GET /contagem-pessoas` for retrieving the number of people created.
