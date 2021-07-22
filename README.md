# Fauna Labs

This repository contains unofficial patterns, sample code, or tools to help developers build more effectively with [Fauna][fauna]. All [Fauna Labs][fauna-labs] repositories are provided “as-is” and without support. By using this repository or its contents, you agree that this repository may never be officially supported and moved to the [Fauna organization][fauna-organization].

[fauna]: https://www.fauna.com/
[fauna-labs]: https://github.com/fauna-labs
[fauna-organization]: https://github.com/fauna

# Sample shop API using Fauna and Java

#### Table of Contents
* [Overview](#overview)
* [Prerequisites](#prerequisites)
* [Set up your Fauna database](#set-up-your-fauna-database)
* [Run the app locally](#run-the-app-locally)

## Overview
This is a sample Spring Boot API for an e-commerce application that uses [Fauna](https://docs.fauna.com/) as a database, and Fauna's [JVM driver](https://github.com/fauna/faunadb-jvm).

## Prerequisites
The only prerequisite you need to run this application is JDK:  
https://openjdk.java.net/install/index.html

Supported Java versions:
- 11 and higher

The next step uses the [Fauna Dashboard](https://dashboard.fauna.com) to set up your database. Alternatively, you could use the [Fauna Shell/CLI tool](https://github.com/fauna/fauna-shell).  

## Set up your Fauna database

 1. [Sign up for free](https://dashboard.fauna.com/accounts/register) or [log in](https://dashboard.fauna.com/accounts/login) at [dashboard.fauna.com](https://dashboard.fauna.com/accounts/register).
 2. Click [CREATE DATABASE], name it "shopapp", select a region group (e.g., "Classic"), and click [CREATE].
 3. Click the [SECURITY] tab at the bottom of the left sidebar, and [NEW KEY].
 4. Create a Key with the default Role of "Admin" selected, and copy/paste the secret somewhere safe. It will not be displayed again, and you'll need this secret to start the app in the next section.
 5. Navigate to your "shopapp" database by clicking on it from the main page of your [Fauna Dashboard](https://dashboard.fauna.com) 
 6. Click [New Collection], name it "Categories", and click [Save].
 7. Do the same to create a "Products" collection.
 8. To quickly create some categories, navigate to the [Shell] tab in the left sidebar, paste the following query into the editor's bottom panel, and click [Run]:
```
Map(
    [
        ["101", { name: "books" }],
        ["102", { name: "food" }],
        ["103", { name: "tools" }]
    ],
    Lambda(
        ["id", "data"],
        Create(Ref(Collection("Categories"), Var("id")), { data: Var("data") })
    )
)
```
 9. To quickly create some products, run the following query:
```
Map(
    [
        ["201", {
            name: "Catch-22",
            price: 12.22,
            description: "lorem ipsum",
            category_id: Ref(Collection("Categories"), "101"),
            quantity: 5,
        }],
        ["202", {
            name: "The Little Prince", price: 10.49, description: "lorem ipsum", category_id: Ref(Collection("Categories"), "101"), quantity: 5,
        }],
        ["203", {
            name: "Frozen Pizza", price: 7.99, description: "lorem ipsum", category_id: Ref(Collection("Categories"), "102"), quantity: 5,
        }],
        ["204", {
            name: "Fresh Salad", price: 5.99, description: "lorem ipsum", category_id: Ref(Collection("Categories"), "102"), quantity: 5,
        }],
    ],
    Lambda(
        ["id", "data"],
        Create(Ref(Collection("Products"), Var("id")), { data: Var("data") })
    )
)
```

## Run the app locally
1. To access your Fauna database, you'll need to export two environment variables. Get `your-secret-key` from the secret you copied in the [Set up your Fauna database](#set-up-your-fauna-database) section, and run the following in your terminal:
```
export FAUNA_DB_HOST=https://db.fauna.com
export FAUNA_SECRET_KEY=your-secret-key
```
2. Start a server by running:
```
./gradlew bootRun
```
3. When you start your server, a Swagger UI should be available at http://localhost:8080/swagger-ui/ where you can test the following REST endpoints:
    - List of categories: http://localhost:8080/categories/
    - Category names: http://localhost:8080/categories/names/
    - List of products: http://localhost:8080/products/
