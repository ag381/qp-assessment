# qp-assessment
1. If you are running the application using Docker image, then you need to first run Mysql docker image and create a network between mysql image and grocery application image. 
2. After running the application, First step is to run the Admin post APIs to insert some data in DB, sothat all other Get APIS and placeOrder api can work properly.
3. Admin api(s) are:<br>
   a. localhost:8080/api/admin/addGroceryItem<br>
   b. localhost:8080/api/admin/findGroceryItem<br>
   c. localhost:8080/api/admin/updateGroceryItem<br>
   d. localhost:8080/api/admin/deleteGroceryItemById/{id}<br>
   e. localhost:8080/api/admin/updateGroceryInventory<br>
4. User api(s) are:<br>
   a. localhost:8080/api/viewAllGroceries<br>
   b. localhost:8080/api/placeOrder<br>

