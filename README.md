<center>
     <h2>Cyber Security Venture - Stealth</h2> 
     <h3>Software Engineer Challenge - Backend</h3>
</center>
 
**Assignment:**

Design and implement a web service that receive input via WebSockets, process the logic and 
stream it via REST.

1. Rules:

* You have up to 6 hours to complete the tasks, be careful with your time, make sure you understand the requirements correctly.
* Try not to over-optimize. An MVP with good documentation counts more than a single polished component.
* Think about edge cases. What if you have not enough memory, do you need to persist the state, what if you need to scale? We will discuss it afterwards!


2. User stories:

* Your WebServer should get messages on `127.0.0.1` port `9011`
* Your Sever should proceed the messages and filter between `Greets() && Coordinates()`
* Your WebServer should provide 3 Streaming REST endpoints.
    * /greets/even -> streams Greets with an even number inside.
    * /greets/odd -> streams Greets with an odd number inside.
    * /coordinates -> streams Coordinates.

3. Specifications:

*  **Messages examples**
  ```
  | Coordinates(-149.4331873,60.12892832,Starbucks - AK - Seward  00025) |
                   
  | Greets(Privet: {815548195}) |
  ```
  ```
  Coordinates(long: Double, lat: Double, name: String)
  Greets(greet: String)
  ```
  The messages are divided by a CRLF. 
  
* **REST Endpoints** should use [streaming](https://www.w3.org/TR/2009/WD-eventsource-20091029/) for providing the results to the clients.
  
4. How to run?

    * Start your application listening.
    * Start socketpublisher.jar (`java -jar socketpublisher.jar`)
    * The `demo.csv` should be in the same root as the jar. 
    * Java 8 is required.


5. Deliverable:

* You can use any language/technology/framework of your choice. But we will ask for the rationale of your decision. 
Since we heavily using Scala - using Scala is preferable but not mandatory! 
* The solution should include a comprehensive documentation and how to run the app. Keep it simple!
* You can provide a github link to the solution or send us a tar ball with the sourcecode. 

6. Bonus Points when:

* Dockerizing your application.
* Writing tests for the critical parts of the app.
* What are the critical structures of the application, what are the possible bottlenecks, what you had did when you had more time. We are curious of your thoughts! 
* Visualize your data! Stream the coordinates into a maps widget! 

_The Bonus points don't have to be delivered on time - you can submit them later!_ 

Looking forward for your result, have fun with this task and good luck! 


  








