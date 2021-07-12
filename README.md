# fauna-java
This is a sample application that demonstrates basics operations we can do perform in FQL
by using a Fauna client for Java

### Fauna database structure
You'll need to create 2 collections:
- Categories (name: string)
- Products (name: string, price: float, description: string, category_id: ref_Categories, quantity: int)  

You can also populate some data for those 2 collections

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

### Rest endpoints
- List of categories: http://localhost:8080/categories/
- Category names: http://localhost:8080/categories/names/
- List of products: http://localhost:8080/products/

### Swagger UI
Swagger UI is automatically started as well, so you can check all
the available endpoints here:  
http://localhost:8080/swagger-ui/
