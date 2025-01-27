# qp-assessment
1. If you are running the application using Docker image, then you need to first run Mysql docker image and create a network between mysql image and grocery application image. 
2. After running the application, First step is to run the Admin post APIs to insert some data in DB, sothat all other Get APIS and placeOrder api can work properly.
3. Admin api(s) are:
   a. localhost:8080/api/admin/addGroceryItem
   b. localhost:8080/api/admin/findGroceryItem
   c. localhost:8080/api/admin/updateGroceryItem
   d. localhost:8080/api/admin/deleteGroceryItemById/{id}
   e. localhost:8080/api/admin/updateGroceryInventory
4. User api(s) are:
   a. localhost:8080/api/viewAllGroceries
   b. localhost:8080/api/placeOrder

