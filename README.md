# LucaResponse Backend

AI-powered REST API built with Spring Boot that processes user queries and generates intelligent responses using LLMs (Groq / Llama 3).

## Features

* Integration with gemini-3.1-flash-lite
* Clean architecture (Controller → Service → DTO)
* Global exception handling
* CORS configuration for frontend integration
* Dockerized for deployment (Render)
* No database required (lightweight and fast)

---

## API Endpoint

### POST `/ai/query`

**Request:**

```json
{
  "query": "Who is Alan Turing?"
}
```

**Response:**

```json
{
  "response": "Alan turing  was an English mathematician, computer scientist,..."
}
```

---

## Health Check

```bash
GET /ai/health
```

---

## Configuration

Environment variables:

```env
OPENAI_API_KEY=your_gemini_api_key
```

Application properties:

```properties
server.port=${PORT:8080}
spring.ai.openai.api-key=${GEMINI_API_KEY}
```

---

## Docker

Build and run:

```bash
docker build -t lucaresponse-backend .
docker run -p 8080:8080 lucaresponse-backend
```

---

## Deployment

* Backend: Render
* Containerized with Docker
* Uses environment variables for secure configuration

---

## Tech Stack

* Java 21
* Spring Boot
* Maven
* gemini-3.1-flash-lite
* Docker

---

## Notes

This project is part of a full-stack application with a React frontend deployed on Vercel.

---

## Author

David – Backend Developer (Java / Spring Boot)
