
Android Chat Application 

**Before Running :**
Please note you're  running the application on Android studio


**Running Program:** 
Once you start running you will see the main page in  which is Login page.

You need to type on the following text box user name and password, for sign in properly 
Use the following user: 
User name : Karen
Password : 1111

If you won't type any character, the app will show you an error message.
For addition there is a validation system for checking if the user is signed up before.

All the users are stored in firebase database.


You can move to the SigUp Page by clicking the button in Login (click here to register)

For the Register page, there is a validation system also for confirm password and
a proper password type (8 characters)
 
The chat page is made by two pages: users where you will choose your contact to chat with
Chat room where you will send messages.

REST API:

As we learned about API controller, we can run it in this way https://localhost:7182/api/contacts 
contacts: is the name of our API controller.
Once running the link it should gives you GetAll users method 

[![Screenshot-API.png](https://i.postimg.cc/cCZ7xVsz/Screenshot-API.png)](https://postimg.cc/njR9TS9K)

For example try GET METHOD by pasting this link https://localhost:7182/api/contacts/JimHal  

it should give you this JSON : {"id":"JimHal","name":"Jimothy","password":"12345","server":"localhost:7265","last":"video","lastDate":"17:28"}

*GET METHOD used like this: https://localhost:7182/api/contacts/id
