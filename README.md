# RecipeBox

Basic useful feature list:

 * Ctrl+S / Cmd+S to save the file
 * Ctrl+Shift+S / Cmd+Shift+S to choose to save as Markdown or HTML
 * Drag and drop a file into here to load it
 * File contents are saved in the URL so you can share files
 * 
## Feature

The major features implemented at this point are the recipes themselves, the reviews, and the user accounts. The log in screen is accessible from a link in the main screen. Users can view recipes and reviews while not logged in, but cannot add any. From the main screen, users can add a recipe, browse recipes, or search recipes by keyword. Selecting a recipe will show details (such as the
 instructions and ingredients list) and will display a link to the list of reviews, and a link to the author. The list of reviews again displays a list of abbreviated reviews, which can in turn be clicked to read the full text. New to iteration 3 is a search feature and the ability to modify posted content. Searching is possible through a button in the main screen. When a logged in user views their own recipe, they can edit or delete it; when they view their own review, they can delete it.
  

## Structure

This project uses a 3 tier architecture. There are five packages, found in Project/app/src/main/java/comp3350.
Four represent major elements of the architecture - the business and objects packages represent the business logic, the persistence and presentation packages represent their equivalient tiers. The application package simply consists of some code needed to initialize the data. All of the xml files can be found in Project/app/src/main/res.

## Developer Tasks

### Iteration 1


###### View a Recipe:
- Create a stub database: 2 hours
- Create GUI for viewing a recipe: 4 hours.
- Build a "recipe" object: 4 hours.

###### Add a Recipe:
- Create GUI for adding a recipe : 4 hours.
- Retrieve recipe from stub database: 2 hours.

###### View a Review:
- Create GUI for viewing reviews: 4 hours
- Retrieve GUI from database: 2 hours

### Iteration 2

###### Add a Review (bumped back from iteration 1)
- Build "review" object: 2 hours.
- Create a GUI for adding a review: 4 hours.
- Store review in database: 2 hours.

###### Big User Story: User accounts

###### Detailed user stories:

###### Add a user:
- Build the user object: 0.5 hours
- Create the table in the database for the user - 0.5 hours
- Add user property to review / recipe tables - 2 hours
- Sign up page in GUI - 5 hours
- Modify the persistence class to be able to store users - 3 hours
- Modify logic class to be able to get users from the database - 1 hour

###### View a user profile
- Logic + persistence - retrieve user + information - 1 hour
- GUI: make user profile display - 3 hours
- Modify recipe and review to show the user that submitted them - 2 hours
- Modify recipe and review displays to show users - 2 hours

###### Delete a User
- Persistance - Delete User method in interface - 2 hours
- GUI: add delete functionality - 2 hours


### Iteration 3

###### Edit a Recipe:
- Implement in GUI - 5 hours
- Include functionality in business logic to delete a recipe - 2 hours
- Update an recipe in the database - 2 hours

###### Delete a Recipe:
- Implement in GUI - 5 hours
- Create "delete recipe" method in business logic - 2 hours
- Delete a recipe in the database - 2 hours

###### Delete a Review:
- Implement in GUI - 3
- Create "delete review" method in business logic - 2 hours
- Delete a review from the database - 2 hours.

###### Search Recipes:
- Modify GUI to allow searching functionality. - 3 hours
- Add search by keyword logic to business logic - 4 hours
- Add search capability to database - 4 hours


## Notes

Sometimes the project cannot be run when the project checks out first time.
You should fix the local configure file, and restart Android Studio.

For running the app with the stub database, please change line 13 in application.Services.java:
    dataAccessService = new DataAccessStub(dbName);

For running the app with real database (HSQLDB), please change the line 13 in application.Services.java:
    dataAccessService = new DataAccessObject(dbName);

The file app/database/RB.script is used in the integration tests. After running, replace the contents
 with RB.script.orig to run integration tests again.
