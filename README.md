# fauna-java
This is a sample application that demonstrates basics operations we can perform in FQL
by using a Fauna client for Java

### Prerequisites
The only prerequisite you need to run this application is JDK:  
https://openjdk.java.net/install/index.html

You can do all operations on the database from the fauna dashboard:  
https://dashboard.fauna.com/  
Alternatively, you can use a fauna-shell app:  
https://github.com/fauna/fauna-shell

### Fauna database structure
You'll need to create 2 collections:
- Categories (name: string)
- Products (name: string, price: float, description: string, category_id: ref_Categories, quantity: int)

Follow the next steps to create collections and documents:  
 * [Sign up for free](https://dashboard.fauna.com/accounts/register) or [log in](https://dashboard.fauna.com/accounts/login) at [dashboard.fauna.com](https://dashboard.fauna.com/accounts/register).
 * Click [CREATE DATABASE], name it "shopapp", select a region group (e.g., "Classic"), and click [CREATE]
 * Click the [SECURITY] tab at the bottom of the left sidebar, and [NEW KEY].
 * Create a Key with the default Role of "Admin" selected, you'll need to add this key to one of the environment variable to start the app, you'll see the name of that variable in the following section
 * Navigate to your "shopapp" by just clicking on it on the main page of the fauna dashboard
 * Click "New Collection", and provide a name for it, i.e. "Categories", and click "Save"
 * Do the same and create the "Products" collection
 * Navigate to the "Collections" menu and choose "Categories" collection
 * Click "New Document", in the opened page add some document, for example:
```
{
    name: "Books"
}
```
 * Create several documents
 * Do the same for "Products" collection (notice that we've also added a reference to "Categories" collection):
```
{
    name: "Some product name",
    price": 152.11,
    description: "Some description",
    category_id: Ref(Collection("Categories"), "287626591762121219"),
    quantity: 5,
}
```
Make sure to add your own "category_id" reference, to retrieve it you can click "Collections" in the fauna dashboard, and choose one of the documents in "Categories" collection.
 * You can populate more data for those 2 collections if you want

### Running the application locally
The application uses fauna managed database service, 
you'll need to export 2 environment variables to access it (getting a secret key is described in the previous section):
```
export FAUNA_DB_HOST=https://db.fauna.com
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
