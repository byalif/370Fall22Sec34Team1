1. The user needs to be able to add, delete, and change the quantitiy of the items on their list. To implement this, there is the class "List",used to represent the list itself, that has add and delete operations. There is also a class ,ItemName, that has an quanitity attribute
2. There must be a database of item types. This was not considered because it does not affect the design but there is a class, ItemType, that is used to represent the item types that would be in the database.
3. Users can add items by picking them from a hierarchical list. This was not considered because it does not affect the design but the hierarchy is represented by two classes, ItemType and ItemName, and the relationship between the two.
4. Users must be able to sepcify an item by its name. A search function was not considered but users can enter the ItemName into the add function.
5. Lists will be saved automatically but this is not relevant to the design,
6. Users must be able to check off items in a list. To represent this, there is a boolean variable called "selected" that is initialized to false and an operation called "select" that can change the value of the "selected" variable.
7. Users must be able to deselect all items on the list. Within the List class, there is a "deselectAll" operation.
8. Check off marks are persistent must be saved immediately but this is not relevant to the design and is not represented in the diagram.
9. Items in the list must be grouped by type however this feature is not represented in the diagram because it is not relevant to the design.
10. The application will be able to support multiple lists. There is a name attribute in the List class. The User class also has functions for adding and deleting lists by the list's name and setting a name for the list.
11. The User Interface must be intuitive and responsive. This was not considered in the diagram because it does not affect the design.