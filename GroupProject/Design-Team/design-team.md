## Design 1 (Pranti):

![Pranti's Design](https://github.com/qc-se-fall2022/370Fall22Sec34Team1/blob/eb46e506641862073e1574d2a5891a2c6c36a8ba/GroupProject/reference-pics/Pranti%20Design%201.png)

Pros:

1. The design lays out the structure for the User, Item and List entities and describes necessary attributes and methods for each.

Cons:

1. There are duplicate methods and attributes
2. CheckOffItem is included as a separate class when it can just be included as an attribute of the Item class

## Design 2 (Michael):

![Michael's Design](https://github.com/qc-se-fall2022/370Fall22Sec34Team1/blob/eb46e506641862073e1574d2a5891a2c6c36a8ba/GroupProject/reference-pics/Michael%20Design%202.png)

Pros:

1.  The relationships are very detailed

Cons:

1. The user input doesn't need to be included as a class
2. This is more of a database design

## Design 3 (Alif):

![Alif's Design](https://github.com/qc-se-fall2022/370Fall22Sec34Team1/blob/eb46e506641862073e1574d2a5891a2c6c36a8ba/GroupProject/reference-pics/Alif%20Design%203.png)

Pros:

1. Covers all necessary requirements and associations are accurate

Cons:

1. The user class has no methods

## Design 4 (Sophia):

![Sophia's Design](https://github.com/qc-se-fall2022/370Fall22Sec34Team1/blob/eb46e506641862073e1574d2a5891a2c6c36a8ba/GroupProject/reference-pics/Sophia%20Design%204.png)

Pros:

1. The methods and attributes are clean and match the requirements
2. Design is straightforward

Cons:

1. The relationships aren't accurately portrayed

## Design 5 (Parminder):

![Parminder's Design](https://github.com/qc-se-fall2022/370Fall22Sec34Team1/blob/eb46e506641862073e1574d2a5891a2c6c36a8ba/GroupProject/reference-pics/Parminder%20Design%205.png)

Pros:

1. Adds an Item List functionality which interacts with the data base
2. Most methods and classes are well thought out

Cons:

1. The relationships can be fleshed out more

## Team Design

![](https://370cs.s3.amazonaws.com/design-team+(2).jpeg)

All of our designs took similar approaches to the the structure of the classes. There was a lot of overlap with the methods and attributes and we came to an agreement on how to rearrange them. We took the associations and multiplicities from Design 3 and combined attributes and methods from Design 4. We felt Design 1 and 2 had extra bloat that was not needed for the design. We decided to represent Items as one class with a name and type attribute because the hierarchal aspect will be represented through the database. We used an array of Lists to represent the users saved lists. Each list has a name attribute so the user can add and change the name of each of their lists. The lists themselves are represented as and array list of items which is a concept we got from both Design 2 and Design 5. Design 5 also helped our group discuss whether we should use some sort of inheritance for item type, and although we we unanimously agreed that there may be benefits to inheritance we decided to leave it out until further project details are revealed. The List class also has an autoSave function that runs after any change to a list. Design 1 inspired a discussion on how we would represent checking items off the list. This discussion had us further go into data type design detail where we ultimately concluded that checking should be represented with a boolean variable.

Summary:

We wanted to take a simplistic approach to the design of the app. We decided to narrow it down to three tables that express all the necessary functionality. We felt it was better to not overcomplicate the design with miniscule details and that they will be implemented later on with more information on the project design.
