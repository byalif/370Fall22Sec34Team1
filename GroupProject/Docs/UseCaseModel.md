# Use Case Model

_This is the template for your use case model. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document._

**Author**: `Team 1`

## 1 Use Case Diagram

![img](<https://july22proj.s3.amazonaws.com/Blank+diagram+(4).png>)

## 2 Use Case Descriptions

_For each use case in the use case diagram, this section should contain a description, with the following elements:_

### `Enter Name`:

- `Requirements`: Once the User logs in, there will be an asyncronous request to an API using the Volley library to fetch the users data. The data consists of lists and items that belong to the particular user.
- `Pre-conditions`: User must open the app and be on the MainActivity.
- `Post-conditions`: Once the user logs in, he will be redirected to the home page.
- `Scenarios`: If the user has invalid log in details, they will have to keep re trying. [User not found error].

### `View lists`:

- `Requirements`: This use case will allow the user to go back to their list whenever they want. There will be a button that is present on the bottom corner of the app at all times.
- `Pre-conditions`: User must be logged in for this use case to run.
- `Post-conditions`: If the user wants to view all lists, a get request to the API will retrieve all lists that belong to the logged in user.
- `Scenarios`: If the user has no lists, they will have a blank page when they click the "All lists" button.

### `Modify List`:

- `Requirements`: This use case will be responsible for any updates to a users list. Whether that be to rename a list, delete a list, change quanity of item or to delete an item completely from a list.
- `Pre-conditions`: User must be logged on.
- `Post-conditions`: After modifying the list, the databse will automatically save the new updated rows that belong to the user using /POST requests through the Volley API.
- `Scenarios`: If there is duplicate list names, the database will automatically add an incremental number onto the duplicated list name to ensure unique lists.

### `Search List`:

- `Requirements`: This use case is responsible for responding to user inputs by sending /GET request as the user types into the filter box. In the backend, there is going to be APIs that will send back arrays of Items from a users list that match the filter requirements given by the user.
- `Pre-conditions`: User must be logged on.
- `Post-conditions`: Searching in the filter will trigger a request that will take the input of the search into the URL of the search request as follows;
  /api/{UserInput}
- `Scenarios`: If the user enters a type or name in the filter that does not associate with any items, they will recieve an error as a response stating "there was no match found".
