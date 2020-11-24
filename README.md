<!-- TABLE OF CONTENTS -->
<details open="open">
    <summary>Table of Contents</summary>
    <ol>
        <li>
            <a href="#about-the-project">About The Project</a>
            <ul>
                <li><a href="#built-with">Built With</a></li>
            </ul>
        </li>
        <li>
            <a href="#getting-started">Getting Started</a>
            <ul>
                <li><a href="#prerequisites">Prerequisites</a></li>
                <li><a href="#installation">Installation</a></li>
            </ul>
        </li>
    </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About the project

This project done as a practical for my Distributed Systems module. The aim of this project was to adapt code provided to use RESTful Web Services for interaction between each of the 3 quotation services and the broker and between the broker and client. Each of these components can be deployed as seperate docker images. All of the images can be deployed using the docker-compose file.

### Built With

* [Spring Boot](https://spring.io)
* [maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/)

<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

Have docker installed in your local environment

### Installation

*   ```sh
    mvn install
    ```
*   ```sh
    docker-compose build
    ```
*   ```sh
    docker-compose up
    ```
*   ```sh
    cd ./clients/
*   ```sh
    mvn spring-boot:run