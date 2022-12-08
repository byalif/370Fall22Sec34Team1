# User Manual

### How the data is stored and manipulated

`DATABASE`

- We used a backend node.js server implementing mysql and a REST API. Since we had some prior knowledge on creating servers, We thought this would be the fastest way to manipulate data in this project. All the Create, read, update and delete operations were done with HTTP /GET, /POST and /DELETE requests. The server is attached in the /GroupProject/server directory. It includes all the endpoints we used. The server was hosted on heroku.
- Here are some examples of endpoints we used:
  - Types of items by listId: https://project-370.herokuapp.com/api/lists/getItemTypes/74
  - All lists by userId: https://project-370.herokuapp.com/api/lists/144
  - Items by listId: https://project-370.herokuapp.com/api/lists/items/74

`Registration and Login:`

- When you open the app for the first time, click "Register" to make yourself an account. You will be prompted to create a username and password.

- After your account is successfully created, you will be able to create and store lists

- The next time you open up the app, use the same username and password you created previously to log in.

  

`Creating Saving and Updating Lists:`

- The Saved Lists page will be empty when you first create an account. Click the Create List button on the bottom of the page to create a new list
- You will be prompted to enter a name for the list. Once you enter a name, the list will be saved in your saved lists.
- From the saved lists page, select the list you need to add items to. You can add items directly to that list by selecting the add Item button. You can also delete items on the list by selecting the delete button.


`Updating/Deleting Lists:`

- To rename a list, open up the saved lists page and click the "edit" button next to the list you want to edit. Then type the name you want to rename the list to and press the "rename list" button.
- To delete a list click the "remove" button to remove the list.
`Updating/Delete items from a list:`
- When a list is selected you can delete a specific item by clicking on the "remove" button on the item.
- Alternatively you can click on select all, which checks every single item or you can click on the specific items check box to select or deselect items. Once you have all the selected items you want to delete you can press the delete selected button above the list to delete them.
- Changing the quantity, you can change the quantity by typing in a new non 0 number for the "quantity:" field. Alternatively if you add more of the same item to a list it will add the quantities together. 

`Adding a new item`

- You can add a new item by selecting a list and clicking add items, where you will have two options of searching.
- The first option is to click the "search" button which will search by previosuly stored item types. You can search for items by items type, once you click on the specific item type that you want, it will update the list to all items of that type. From there you can click on the item and a popup box will apear asking for the user to input the quantity, once specified the item is added to the list. You are able to add items of the same type if you wish or you can go back to the list of item types by clicking the "select type" button.(note the "all list" button will take you back to your lists."
- The second option of searching is by typing in the item name and then clicking the "add item" button. It will then search the data base by name for any items which may be similar to the searched item. If the item is of the correct one you can click the "add to list" button which will pop up box asking for the user to input the quantity and adding it to the list once entered. If there is no item that matches your search expectations you can manually add the item by clicking the "no matches found" and this will take you to another screen where you can input the item type to add it to the data base.

