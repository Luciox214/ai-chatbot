#  AI Text Reviewer - Spring Boot + Ollama

Este es un proyecto simple que integra **Spring Boot**, **Spring AI** y **Ollama** para revisar textos en español con inteligencia artificial de forma local.

## ¿Qué hace?
-  Recibe un comentario, un PDF o ambos
-  Procesa el contenido con IA local (LLaMA 3)
-  Devuelve sugerencias o correcciones
-  Todo funciona sin conexión a servicios externos

##  Tecnologías

- Java 17 + Spring Boot
- Spring AI
- Ollama (llama3)
- HTML, CSS y JS para el frontend

## Cómo ejecutar

### 1. Clonar y compilar
- git clone https://github.com/tu-usuario/ai-text-reviewer.git
- cd ai-text-reviewer
- mvn clean install

### 2. Iniciar Ollama 
- ollama run llama3

### 3. Ejecutar el backend
- ./mvnw spring-boot:run

### 4. Probar con Postman
- POST http://localhost:8080/api/v1/revisar
