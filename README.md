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

### API Endpoints
 1. The API endpoints are these:
 2. /movies/all – returns a list of all movies in the database
 3. /movies/title/{title} – returns all movies with the title: {title}
 4. /movies/year/{year} – returns all movies released during the year: {year}
 5. /movies/decade/{decadeLowerBound} – returns all movies in a 10-year period starting with {decadeLowerBound)
 6. /movies/member/{member} – returns all movies that have a specific cast member 

### Sample API Requests
 1. All good requests will return a 200 OK
 2. Bad requests will show 400 and give a descriptive message

### Runtime
Happy Path

Example: Get all movies
This represents the happy path for the project given a HTTP request. First the client will send the request to the Spring Boot application. Then that request will be processed, in this case the request would be a GET and would have /movies/all. This request is mapped to the getAllMovies() method. The controller will request all documents from the service. This MovieService object will connect to the MongoDB and get the data back as a list of movie objects. This is then seen in the HTTP response where the user can see all the objects returned from the database. 

Negative Path
 
Example: Get a movie by a year 
This represents the negative path for the project given a HTTP request. First the client sends the bad request to the Spring Boot application. That request will be recognized as a bad request since no movies were made in the year 9999. The MovieController will call the method getMoviesByYear(9999), then the MovieService object will call its method getAllMoviesByYear(9999) and throw an ApiException recognizing the bad request. Then in the response the user can see that they got a bad request HTTP code and must change their request to work. 



