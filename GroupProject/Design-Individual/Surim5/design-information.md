Design-information

1. User can interact with the UserControl or interface in order to input information to access the app or website. In this case the user can create a list/rename a list/delete a list. And the user itself has a lists of grocery lists. 

2. Interacting with the Grocery List brings up a UI in where the user can delete items check off items on the list or add items. 

3. Grocery list itself auto updates, and is dependent on the UserControl while they are interacting with the Grocery List; addItem(),deleteItem(),interactWithItem(),changeNameOfGroceryList().

4. You can change the name of the grocery list by editing the grocery list. Or you can add/delete items from the list, or delete the list itself.

5. Adding items can be done by searching through the Hierarchical list and click add item, where then user is requested for input or the User can type to search the data base Which will search and update database for item if not found, that way supermarket can possibly decide to order item if popular in demand.

6. Set checkbox requires an index for the item, but this is displayed via the interface, and you can uncheck the item off the list. Or you can use the check all off feature which empties out the list.

7. Store Server checks database queries, and sends user whether the item is in the database. If it's not it will request for user to send in info so that it can store it. 

8. Hierarchical List is based off of the DataBase info and is a aggregation of the database.

9. Grocery List is comprised of a list of items and the User class is comprised of grocery lists. So the UserControl class has a composition of GroceryLists and GroceryList has a composition of Items

10. Instead of making a class for grocery list item (groceryListItem()) assume that this is a generic store with  no multiple brands such that there's no need for a specific data structure for the grocery item itself and can be distinguished via string.

    

    

