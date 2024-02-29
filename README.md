# movie-metadata-service

### Introduction
The Movie API is a Spring Boot application that can be used to search for movies and add new movies to a MongoDB. Users can put in HTTP requests for the data they want and can get all the movies but also can search for specific movies by both year and title. Users can send a body of data through the client (POSTMAN) to create a new movie. The client should return a HTTP response and any data associated with the HTTP request.

### Setup
 1. Download the project
 2.	Modify application.yaml with the provided MongoDB credentials
 3.	Open IntelliJ and on the right side of the screen open the “Gradle” tab
 4.	Execute the “Gradle bootRun” Command

### Run Tests
1.	Open up the project and navigate to this folder
2.	You can right click and hit “Run” on the file above to run the Junit tests

### Api Endpoints
 1. The API endpoints are these:
	2. /movies/all – returns a list of all movies in the database
	3. /movies/title/{title} – returns all movies with the title: {title}
	4. /movies/year/{year} – returns all movies released during the year: {year}
	5. /movies/decade/{decadeLowerBound} – returns all movies in a 10-year period starting with {decadeLowerBound)
	6. /movies/member/{member} – returns all movies that have a specific cast member 
