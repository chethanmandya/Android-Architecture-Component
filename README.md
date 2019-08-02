# Android-Architecture-Component
A sample project to demonstrate Android Architecture Component libraries (Room, ViewModel, LiveData and LifeCycle) 

It is a note taking app, built using the Android Architecture Component libraries (Room, ViewModel, LiveData and LifeCycle), App data will be stored in an SQLite database and supports for insert, read, update and delete operations. For this I have followed the official recommendations from the "Guide to App Architecture" (link below).

https://developer.android.com/topic/libraries/architecture ,

https://developer.android.com/jetpack/docs/guide

https://github.com/googlesamples/android-architecture-components


This sample will help you to learn what the Architecture Components are, how they work and why we need them. With help of ViewModel and LiveData, you will learn how to overcome some of the common problems that arise from the Activity and Fragment lifecycle, configuration changes and bloated, tightly coupled classes. 


ViewModels store and manage UI related data, they survive configuration changes and can be used seemlessly by the newly created activity.
Here's why that's actually useful. Rotating phone is considered to be configuration changes, configuration changes causes your whole activity to torn down and then recreated, if you don't store and restore data from the destroyed activity, you may lose that data and end up with weired UI bags or even crashes. So enter the View model which, of course survives configuration changes, instead of storing all of your UI data in your activity, put it in the view model. now, this helps with configuration changes but it's also just general good software design, one common pit fall when developing for android is putting a lot of variables, logic and data into your activities and fragments, this create large unmaintainable mess of a class and violates the single responsiblity principle. 

You can use view models to easily divide out that responsiblity. The view models will be responsilbe for holding all of the data that you're going to show in your UI and then the activity is only responsible for knowing how to draw that data to the screen and receiving user interactions, but not for processing them .

Make sure your view model doesn't become bloted with too many responsibilities. To avoid this, you can create a presenter class or implement a more fully fledgeed clean architechture.



LiveData is an observable dataholder and it is life-cycle aware, which means it automatically starts and stops updating the UI-controller at the right times in it's lifecycle.

For the backend of our app we will use the "Room Persistence Library", which works as a wrapper around SQLite and helps us reduce boilerplate code by making extensive use of Annotations. Instead of creating an SQLiteOpenHelper, we simply turn Java classes into "entities" to create tables, and use "Data Access Objects" (DAO) to query these tables and make operations on them. Room also provides compile time verification for SQL statements, so we run into fewer runtime exceptions, caused by typos and invalid queries. We will also use a "Repository" class that works as another abstraction layer between the ViewModel and the underlying data model.

Together, this whole structure constitues an "MVVM" (Model-View-ViewModel) architecture, which follows the single responsibility and separation of concerns principles.

![alt text](https://github.com/chethu/Android-architecture-Component/blob/master/app/src/main/res/drawable/AndroidArchitecturalComponent.png)

ViewModel works with Room and LiveData to replace the loader. The ViewModel ensures that the data survives a device configuration change. Room informs your LiveData when the database changes, and the LiveData, in turn, updates your UI with the revised data.

Room : it is wrapper around sqlite that takes care most of complicated stuff that we previously had to do ourselves, we will write much less boilerplate code to create tables and make database operations. Room provides compile time verifications for our SQL lite. Example like we are trying to create column that doesn’t exist Or if we do type error in SQL statement , we can’t even compile our code, it is obviously much better than having the app crash at run time. 

DAO Data access object : which is used to communicate to SQLite . 

View Model : its job is to hold and preparing all the data which is require for user interface. We don’t have to put any of those code directly into fragment and activity, instead fragment and activity connects to view model and get all the necessary data from there. UI keeps job of drawing data into screen and reporting user interaction back to view model. view model receive this data and pass on to under layers of the app either to load new data and changes to data. In simple view model act as gateway between UI controller 

Rotating change : When there is configuration changes , I mean rotating screen from portrait to landscape , entire activity will recreate , activity will be reinitiate in order to reload different layout. In the past, what ever the data which is there in the screen were saving in onSavedInstance call back, restore back using onRestore call back. with help of view model we no need to worry any more for configuration changes. It will provide existing data instance to reload data.

Repository : it is one more abstraction layer , it mediate between different data source like our local database OR webservice. View model doesn’t have to care about where the data comes from , how it is fetch. 

Live Data : live data is wrapper again , it hold any kind of data including list and it can be observe by UI controller which means that when ever live data changes , observer automatically get notify with new data and can get refresh UI. 

LiveData is an observable data holder class, LiveData only updates app component observers that are in an active lifecycle state.
