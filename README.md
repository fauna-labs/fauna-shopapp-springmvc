# fauna-java
This is a sample application that demonstrates basics operations we can do perform in FQL
by using a Fauna client for Java

### Running the application locally
The application uses fauna managed database service, 
you'll need to export 2 environment variables to access it:
```
export FAUNA_DB_HOST=https://your-fauna-db-host
export FAUNA_SECRET_KEY=your-secret-key
```
This is a spring boot application, and you can start a server by running:
```
./gradlew bootRun
```
After that server should be available at http://localhost:8080/