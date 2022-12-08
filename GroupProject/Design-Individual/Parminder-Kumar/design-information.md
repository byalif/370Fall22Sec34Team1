# UserInterface:
- User Inteface displays all existing grocery lists created by the user.
- It has the functionality to create, delete, and rename grocery lists.
- It allows the user to search for Items by ItemName(string) and displays a list of all items with the searched item name.

# GroceryList:
- This class has two attributes: List name, which should be unique and an array consisting of items(strings) that were added by the user.
    ## fucntionalities:
    - allows user to add, remove, change quantity, checkoff, and remove checkoff mark for all items in the list.
    - when adding items, all items in the Item list will be displayed
    
# ItemLIst:
-   contains a 2d array where first index represents ItemType and second index represents ItemName. It is used to display all items separated by category ItemType.
    ## fuctionalities:
    -  Permits user to add more items to the existing items and remove and add items to a grocery list

# ItemType:
-   A class for an item category which includes category name and allows adding a new category.

# Item:
-   This class inherits from ItemType class. It has various attributes which specifies an item. This class has a function used by ItemList class to add the current instance of Item item to GroceryList on which this function is called.
