The Database must contain a class User with 4 attributes:
1.UserId(String)
2.UserLastName(String).
3.UserFirstName(String) and 
4.UserAddress(String)
And including operations:
1.AddUser(UserName) :to add new user
2.ModifyUser():If the user needs to change any information.
3.UpdateUser():To update all the changed information of the user.

The database must contain a class name Item with 4 attributes:
1.ItemId(String).
2.ItemName(String).
3.ItemDescription(String).
4.ItemPrice(Number)
and including operations:
1.AddItem(item) User can add items
2.DeleteItem(item) User can delete items if they do not want it
3.ChangeItem(item) Change an item 
4.UpdateItem(item)update the item

The database must contains a class name ItemType which is a subclass of the class item with 3 attributes:
1.ItemTypeId(String).
2.ItemTypeName(String).
3.ItemTypeDescription(String).
and the operations are in that class:
1.AddItem(item):wheen user find the item by searching the database,it will add the item.
2.UpdateQuantity(number):User will update the quantity by specifying it.

The database must contain a class name List with 3 attributes:
1ListId(String) .
2.ListItemType(String).
3.ListItemQuantity(Number).
and the operations are:
1.AddListItem() :Add items to the list
2.DeleteListItem():Delete items from the list 
3.ModifyListItem() :Modify if they want to change it.
4.SaveListItem():saave the updated list.

The database must contains a class called CheckOffItem with 3 attributes:
1.ItemId(String). 
2.ItemName(String). 
3.ItmQuantity(String).
and the operations are:
1.AddCheckOffItem() :user can check off items from a list and add
2.ClearCheckOffItem() :user can clear all the check off marks.
3.SavedCheckOffItem():dataabase will save the check off items.


The daatabase must contains a class name MultiList with 1 attributes :
MultiListName(String).
and the operations are:
1.CreateMultiList() :User Create a list (for specific occasion)
RenameMultiList():Database will allow user to rename list .
SelectMultiList() User can select from the exixting list.
DeleteMultiList():User can delete the list.

The UI interface does need to include in our design as it will not affect our design.

Relationships between the class:
The user can have multiple items and multiple list.
The item can have multiple user
itemType is a subclass of the item.
itemList can have checkoffitem
Multilist caan have many user and vice versa.
