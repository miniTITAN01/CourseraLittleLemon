ntroduction

By now you should have an app with three screens: the Onboarding, Home and Profile screens. The Onboarding screen should allow users to enter their first name, last name and email address and use a register button. The users should be able to access the Profile screen from the Home screen and log out from the Profile screen. When a user clicks on the Log out button the user's details should be removed from the device.  You should also have a Home screen with a button in the header linking to the Profile screen.

In the exercises in this lesson you will focus on developing the Home screen including the hero and the menu sections.  To do this, you need to fetch and store data such as menu items. In this reading, you will review some key concepts explained previously in the Android Developer program related to retrieving data from the remote server and storing the data in the app.

Fetching data

The first thing that must be recapped is that most mobile apps communicate with the back-end to retrieve data. Many mobile apps depend on data that is hosted on a remote server. To load that data in your applications, you will have to make an HTTP GET request to fetch it from the server. This can be achieved by using one of the popular networking libraries such as Ktor. The Ktor library can make network requests and handle network responses, meaning you can retrieve the data from a remote server. For example, your Little Lemon food ordering app will download the restaurant menu from an external endpoint.

Once the data is retrieved, it can then be parsed and processed by the app. Ktor can parse JSON data into Kotlin objects. To parse the received JSON data the kotlinx serialization needs to be configured. A data class will need to have a @Serializable annotation as well as the @SerialName annotation for each field.  Storing data

Data retrieved from a remote server can be stored in a local SQLite database. SQLite is a popular choice for storing data in Android applications because it is a lightweight, self-contained database engine that does not require a separate server process. It is also widely supported, with native bindings available, including Room.

Using Room in an Android app allows you to store and retrieve structured data in your device, such as user information, application settings and other types of data that need to be persisted between sessions. It also provides an easy way to query and manipulate data using SQL, which is a powerful and widely-used language for working with databases. The data in an app is stored as a database entity. One of the main advantages of using Room as persistent storage is that it allows you to store data locally on the device. This can be useful in cases where an internet connection is not available or when you want to reduce the amount of data that gets transferred between the device and a remote server. To start using Room 3 you need to:

Create a Database abstract class to define the database

Create an Entity data class to represent the menu entity

Setup a Dao interface to access and manipulate database data

Overall, using Room is a convenient and efficient way to store and manage data in an Android application.  Overview

Previously, you recapped the procedure for retrieving data to be used in an Android app. In this exercise, you will retrieve and parse data from a remote server and store it in an SQLite database. You will implement fetching and storing the data with the help of Ktor and Room.

Scenario

To create the food menu for your Little Lemon ordering app you need to fetch data from a remote server, store and display it.

Now that your pages are set up you need to connect to the API to fetch the menu items. Below is a diagram that shows how your app needs to connect to the API via HTTP as part of the application flow.  

In the case of this Little Lemon food ordering app, you need to retrieve a food menu from a remote server formatted in JSON, in the following format:

```{
  "menu": [
    {
      "id": 1,
      "title": "Greek Salad",
      "description": "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
      "price": "10",
      "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
      "category": "starters"
    },
    {
      "id": 2,
      "title": "Lemon Desert",
      "description": "Traditional homemade Italian Lemon Ricotta Cake.",
      "price": "10",
      "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true",
      "category": "desserts"
    },
    {
      "id": 3,
      "title": "Grilled Fish",
      "description": "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.",
      "price": "10",
      "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true",
      "category": "mains"
    },
    {
      "id": 4,
      "title": "Pasta",
      "description": "Penne with fried aubergines, cherry tomatoes, tomato sauce, fresh chili, garlic, basil & salted ricotta cheese.",
      "price": "10",
      "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
      "category": "mains"
    },
    {
      "id": 5,
      "title": "Bruschetta",
      "description": "Oven-baked bruschetta stuffed with tomatoes and herbs.",
      "price": "10",
      "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/bruschetta.jpg?raw=true",
      "category": "starters"
    }
  ]
} 
```
Prerequisites

To complete this exercise, you should have already created the application onboarding and defined all of the application screens together with setting up the navigation.

Instructions

Use this URL to fetch menu items from the remote server inside your application:

https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json


Step 1: Configure dependencies

Step 2: Fetch the menu

As soon as you get the JSON data from the server, you must convert it to a suitable Kotlin format. To do so, complete the following steps:

Under the application package name, create a new file called Network.kt.

In this file, create the MenuNetworkdata class and MenuItemNetwork data class with @Serializable and @SerialName annotations. These classes contain data classes that are used to decode the object received from the server. In the MainActivityclass, create the instance of the Ktor httpClient and install ContentNegotiation with JSON.

Use the httpClient instance to make a network call and decode the MenuNetwork instance representing menu items from the server.

Tip: The instance of the MenuNetwork class represents the entire menu. To retrieve individual menu items use the menu property of the MenuNetwork. Each item has id, title description, price and image attributes.

Step 3: Store data in a Room database

After retrieving menu items from the network, store the menu in the local database.

Under the application package name, create a new file called Database.kt.

In this file, create the Database and Dao.  Also create an entity data class representing the menu item with the same attributes as the menu item decoded from the JSON. In the MainActivity class, map the network data models to Room entities and save the data to the database.

Step 4: Display restaurant details

Before displaying the menu items, you need to update the Home Composable with the hero section. Implement the UI for the hero banner below the header.

Include the information about the restaurant provided below:

Restaurant name: Little Lemon

City: Chicago

Short description: We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist

Also add the hero image that was provided to you in the app assets zip folder.
The hero section should also include a search bar but you will add this in a later exercise.
Step 5: Display menu items

Define the MenuItems Composable with a single parameter representing a list of menu items. Display it below the hero section.

Retrieve items from the database as a state and assign data to the menu items variable.
Inside the MenuItems Composable use a Column layout to position items below each other.
Define the MenuItems Composable representing a single menu item. Add title, price, description and image.
In the dependencies block of the app/build.gradle file add the glide compose dependency:
implementation "com.github.bumptech.glide:compose:1.0.0-alpha.1"
Use the GlideImage library to load images using the URL present in the menu item image attribute.
Tip: To retrieve data from the database, use the observeAsState method.



