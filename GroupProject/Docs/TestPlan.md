# Test Plan



## 1 Testing Strategy

### 1.1 Overall strategy

We will be using regression testing, system testing and unit testing to verify if we meet all the requirements we set for the app. Parminder will be our tester for the app so he will be doing the unit testing. We will all take part in regression testing if any of us make any changes or updates to the app and we will test the entire app as a group.

### 1.2 Test Selection

First, we will refine our requirements analysis of our software and we will develop some test scenarios and test cases based on those requirements. The test cases will mimic what a user may do when they are using the app and we will apply the test cases with different inputs.

### 1.3 Adequacy Criterion

We will map out different simulations of a user interacting with the application and if the software performs as expected for every scenario and test case, we will consider the testing to be sufficient. Once we refine our test cases, we expect a certain number of failures in our unit tests that will represent the results of testing invalid inputs.

### 1.4 Bug Tracking

 We will use excel for all test management. For example if we want to see  whether or not our login field is working with  valid credentials, we will create some test cases, log them in excel and execute them manually. If we find any bug we will log the bug in excel and then collectively try to fix it.

### 1.5 Technology

We will be using J-Unit for running tests and excel for keeping track of our test cases and results.

## 2 Test Cases



| Test Case               | Purpose                                                      | Steps                                                        | Expected Result                                              | Actual Result                                                | Pass/Fail |
| ----------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | :-------: |
| Create a new List       | to ensure that the user can create lists                     | - click the create list button <br />- pass in a name for the list | user has a new saved list with the name they provided        | a new list was created                                       |   pass    |
| Check off an Item       | to allow the user to select items for deletion               | click a button next to an item on the list and               | the check box next to the item name should be filled in      | the check box is filled in                                   |   pass    |
| Remove checks           | the user needs to be able to easily uncheck an item          | click the check button next to the item                      | the check box should be empty                                | the check box was empty                                      |   pass    |
| Delete Items            | the user needs to be able to delete items from the list if they want to change it | select the item then click the "delete selected" button      | the item should no longer appear on the list                 | the item was removed from the list                           |   pass    |
| Delete List             | in the case that the user no longer wants a list or wants to create space, they need to be able to delete lists | click the remove button that will be next to the name of a list on the saved lists activity | that list should no longer appear in the saved lists activity | the list was removed from saved lists                        |   pass    |
| Rename a list           | the user may want to rename a list                           | click the edit  button that will be next to the list name when a list is opened up and pass a new name | the list name should be replaced with the new list name      | you get redirected to a page where you can input a new name for the list |   pass    |
| Add Item                | if an item is not already in the database,  the user will be able to add it | type in a name of an item that doesn't exist in the database, you should be prompted to add that item and then should be able to enter the type and name | a message stating that the item has been added and that item should be on your list. Also if you need to add it in the future it will  be in the database | the item was added to the list and shows up if you search for it again |   pass    |
| Add Item to list        | the user needs to be able to add items to their list         | click the add item button that is at the bottom of the page when you're inside a list | you should be able to input the name of the item and enter a quantity | redirected to a page to enter the item name then prompted to enter the quantity |   pass    |
| Register for an account | When a user uses the app for the first time, they should be able to make a user name and password  so they have an account to store all their lists | enter in a valid username and password then click register   | you get redirected to the main page of the app               | you have to click on the sign in button underneath, sign in and then you get redirected to the main page |   pass    |

