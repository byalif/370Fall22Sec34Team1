# Project Plan

**Author**: Team 1

## 1 Introduction

The Grocery List Manager map allows users to create, save, update and delete multiple shopping lists to make grocery shopping a quick and easy experience.

## 2 Process Description

- MainActivity - This will be the welcome page to the app. From this page, there will be buttons that will allow you to interact with other activities via intents.
- CreateListActivity - This activity will hold the main methods involving creating and updating a list. From this page, the user will be able to name their list, add items to their list, search for other items, and delete items. The Create List Activity will have onCreate buttons that will trigger operations for modifying the list/quantities of the items on the list. There will also be a text field that will allow the user to search for items within the list they are currently viewing. When selecting a saved list from the SavedListActivity, the user will be redirected to the CreateListActivity so they can edit and update a previously saved list. The user will also be able to check items off the list.
- SavedListsActivity - This activity will hold all of the user's saved lists. The user can select a previously saved list to view, edit or update it. The user can also click a button that will allow them to add a new list. When a user creates a list, it will be given a default name and the list will be saved by this name until the user decides to edit the name. Users will also be able to select lists for deletion from this page.
- SearchActivity - This activity include a search bar which will search the catalog. The user can search by typing the name or selecting the product type then the product name. If the item doesn't exist in the catalog, the user will be prompted to add that item.
- AddItemActivity - The user will be redirected to this activity when they need to add an item that doesn't exist in the catalog. The user will be able to add the items type and name and it will be stored in the database accordingly. Afterwards, it will be easy for the customer to find the item through the search function.

## 3 Team

_Describe the team and their roles (there may be more roles than there are team members)_

- | Team member | Role                 | Tasks                                         |
  | ----------- | -------------------- | --------------------------------------------- |
  | Alif        | Database Developer   | working with the server on the backend        |
  | Sophia      | Tester/Documentation | testing activities and updating documentation |
  | Michael     | User Interface       | creating the HierarchicalAddItemActivity and AddDataBaseItemActivity |
  | Pranti      | User Interface       | creating SavedList Activity                   |
  | Parminder   | Tester/QA            | testing all methods and activities            |
