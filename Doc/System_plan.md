# System Design

## Task Completion
### Roles
- Product owner: Zoltán Sipos
- Scrum master: Bence Papp
### Developers
- Backend: Bence Papp, Balázs Holléry
  - Handling and caching API calls
  - Preparing the data for the frontend
  - Business logic implementation
- Frontend: Zoltán Sipos
  - Handling the received data on the UI
  - Maximize user experience 
- Backend tests: Bence Papp, Balázs Holléry
  - Postman scheduled integration tests
  - Unit tests
- Frontend tests: Zoltán Sipos
  - Cypress E2E tests
### Schedule
The tasks outlined in the requirements specification will be completed in multiple phases. Tasks can be checked on [Trello](https://trello.com/b/AXOJeYCF/raincheck).

## Use case diagram
Check use case graph [here](https://www.plantuml.com/plantuml/png/NOx1IiD048RlynHpJ0yvb2Gj596AkE1DB7s0SJCnYvDKTYUAuCERwQu8dlRVRpv-_cDIYhfC0eVpwU7vW9Jmd3X2vIwHFINT679RgEwU7j6JI0OjL2SMzef34J7Col6316dkqGVzocO3rJtvaR4dfLMwlY-KE9-xjNdHoqGQF7w6TqPVx22bORSsZEpVC0oeemaY658M--pimMPZ7SFhgFMjBVu7cWA41_kN-LOuyGR0jNZpNTVMvFObDk2s9RRWkj_uHxihjRI38y_zCiaF)

## Requirements
### Functional requirements
- Data caching
- Logs
- Easily operable system
- Responsive design
### Non-functional requirements
The system's functions can be used by anyone without login.
### Glossary
GDPR: The GDPR (General Data Protection Regulation) is the European Union's General Data Protection Regulation.

## Web Interface
The system will have a web interface.

## Purpose
To inform users about weather forecast.

## User Accessible Functions
- Filtering by settlement
- Daily, weekly, and 2-week forecasts

## Requirements for the Interface
- Web: Must match the developed prototype.
- Easily operable system.

## Frontend
- Angular

## Backend
- Java 21
- Spring project
- Maven
- Redis

## Framework
- Spring Boot
- Angular Service

## Architectural Plan
The layers of the application, main components, and their relationships:
- Backend: Entities, Controllers, Services, Redis Server - Database, Utilities
  - Database keys: weather:{duration}:{location}
- Frontend

The two layers communicate with each other through REST API.
