package comp3350.recipebox.tests.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.recipebox.application.Main;
import comp3350.recipebox.objects.*;
import comp3350.recipebox.persistence.DataAccess;

public class DataAccessStub implements DataAccess
{
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Recipe> recipes;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Review> reviews;
    private ArrayList<User> users;

    public DataAccessStub(String dbName) { this.dbName = dbName; }

    public DataAccessStub() { this(Main.dbName); }

    public void open(String dbPath)
    {
        recipes = new ArrayList<Recipe>();
        ingredients = new ArrayList<Ingredient>();
        users = new ArrayList<User>();
        reviews = new ArrayList<Review>();
        addContent();
        System.out.println("Open" + dbType + " database " + dbPath);
    }

    private void addContent()
    {
        User user;
        Recipe recipe;
        Review review;
        Ingredient ingredient;
        ArrayList<Ingredient> currIngList;
        String instruction;

        user = new User(0, "TEST", "test");
        users.add(user);
        user = new User(1, "Stu", "dent");
        users.add(user);
        user = new User(2, "Mike", "123");
        users.add(user);
        user = new User(3, "John", "456");
        users.add(user);

        currIngList = new ArrayList<Ingredient>();
        ingredient = new Ingredient(0,0, "Sticks string cheese");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(1,0, "Eggs");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(2,0, "Italian seasoned bread crumbs");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(3,0, "Vegetable oil");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        instruction = "Remove string cheese sticks from plastic wrappings and cut the strings in half crosswise.\n" +
                "Line a baking sheet with parchment paper. Place eggs in a bowl and bread crumbs into a large shallow bowl.\n" +
                "Dip cheese stick halves into egg and roll in bread crumbs to completely coat the sticks; place breaded cheese sticks onto the prepared baking sheet. Allow cheese sticks to stand at room temperature for about 5 minutes to set, and repeat dipping in egg and bread crumbs to form a thick coating. Freeze breaded cheese sticks for at least 2 hours.\n" +
                "Heat oil in a deep-fryer or large saucepan to 350 degrees F (175 degrees C).\n" +
                "Deep fry cheese sticks, a few at a time, until the coating is golden brown and crisp and the cheese is soft, 5 to 8 minutes. Let sticks drain on paper towels; cool slightly and serve warm.\n";
        recipe = new Recipe(0, 0, "Home-Fried Cheese Sticks", instruction, currIngList, 0);
        recipes.add(recipe);

        currIngList = new ArrayList<Ingredient>();
        ingredient = new Ingredient(4,1, "Kale");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(5,1, "Olive oil");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(6,1, "Seasoned salt");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        instruction = "Preheat an oven to 350 degrees F (175 degrees C). Line a non insulated cookie sheet with parchment paper.\n" +
                "With a knife or kitchen shears carefully remove the leaves from the thick stems and tear into bite size pieces. \nWash and thoroughly dry kale with a salad spinner. Drizzle kale with olive oil and sprinkle with seasoning salt.\n" +
                "Bake until the edges brown but are not burnt, 10 to 15 minutes.";
        recipe = new Recipe(1, 0,  "Baked Kale Chips", instruction, currIngList, 0);
        recipes.add(recipe);

        currIngList = new ArrayList<Ingredient>();
        ingredient = new Ingredient(7,2, "Raw whole pumpkin seeds");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(8,2, "Melted butter");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(9,2, "Salt");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        instruction = "Preheat oven to 300 degrees F (150 degrees C)." +
                "Toss seeds in a bowl with the melted butter and salt.\n Spread the seeds in a single layer on a baking sheet and bake for about 45 minutes or until golden brown; stir occasionally.\n";
        recipe = new Recipe(2, 1, "Roasted Pumpkin Seeds", instruction, currIngList, 0);
        recipes.add(recipe);

        currIngList = new ArrayList<Ingredient>();
        ingredient = new Ingredient(10,3, "Vegetable oil");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(11,3, "Sliced paper thin potato");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(12,3, "Salt or taste");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        instruction = "Pour the vegetable oil into a plastic bag (a produce bag works well). \nAdd the potato slices, and shake to coat.\n" +
                "Coat a large dinner plate lightly with oil or cooking spray. \nArrange potato slices in a single layer on the dish.\n" +
                "Cook in the microwave for 3 to 5 minutes, or until lightly browned (if not browned, they will not become crisp).\n Times will vary depending on the power of your microwave. Remove chips from plate, and toss with salt (or other seasonings).\n Let cool. Repeat process with the remaining potato slices.\n You will not need to keep oiling the plate.";
        recipe = new Recipe(3, 2, "Potato Chips", instruction, currIngList, 0);
        recipes.add(recipe);

        currIngList = new ArrayList<Ingredient>();
        ingredient = new Ingredient(13,4, "Egg");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(14,4, "Water");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(15,4, "All-purpose flour");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        ingredient = new Ingredient(16,4, "Salt");
        currIngList.add(ingredient);
        ingredients.add(ingredient);
        instruction = "In a medium bowl, beat the egg. Mix in the water." +
                "In a large bowl, combine the flour and salt.\n Create a well in the center of the mixture and slowly pour in the egg and water.\n Mix well. If the mixture is too dry, increase the amount of water one teaspoon at a time until a pliable dough has formed.\n" +
                "On a lightly floured surface, knead the dough until elastic. \nCut dough into two separate balls.\n Cover the balls with a damp cloth for a minimum of 10 minutes.\n" +
                "Cut each ball into four equal pieces.\n Roll the pieces into 10 1/2 by 10 1/2 inch squares. Cut each into nine 3 1/2 by 3 1/2 inch squares. \nUse in any recipe that calls for wonton wrappers.";
        recipe = new Recipe(4, 0, "Wonton Wrappers", instruction, currIngList, 0);
        recipes.add(recipe);



        review = new Review(0, 0, 1, 5, "I made a double batch, sticking half in freezer. First batch disappeared in record time.\n" +
                " I was told I was seen sticking extra in freezer but couldn't I go ahead and cook those too? 24 for 6 people was not enough.\n Went remarkably well with a big pitcher of lemonade!\n" +
                " Kids and adults raved over them. BIG keeper!\n Thanks Melissa. Everyone is looking forward to a repeat!");
        reCalRate(0);
        reviews.add(review);

        review = new Review(1, 0, 2,  5, "Excellent!");
        reCalRate(0);
        reviews.add(review);

        review = new Review(2, 0, 3, 2, "Not very good!");
        reCalRate(0);
        reviews.add(review);

        review = new Review(3, 1, 0, 4, "I want to try it!");
        reCalRate(1);
        reviews.add(review);

        review = new Review(4, 1, 1, 3, "Not bad!");
        reCalRate(1);
        reviews.add(review);

        review = new Review(5, 2, 0, 1, "Poor!");
        reCalRate(2);
        reviews.add(review);

        review = new Review(6, 2, 1, 4, "Sounds good!");
        reCalRate(2);
        reviews.add(review);

        review = new Review(7, 4, 2, 1, "Bad!");
        reCalRate(4);
        reviews.add(review);

        review = new Review(8, 4, 3, 4, "Great!");
        reCalRate(4);
        reviews.add(review);

        review = new Review(9, 4, 0,  5, "Perfect!");
        reCalRate(4);
        reviews.add(review);

        review = new Review(10, 0, 3,  5, "Good!");
        reCalRate(0);
        reviews.add(review);

        review = new Review(11, 0, 1, 4, "This is a very good recipe. For those that are health conscious or are avoid fried foods.\n" +
                "Prepare the cheese in the same manner but wrap with a wonton wrapper, brush with egg white wash and place in oven at 400 for about 10 min.\n" +
                "Keep an eye on them so they don't over brown or explode.\n Very good and kid friendly.");
        reCalRate(0);
        reviews.add(review);
    }

    public void close() { System.out.println("Closed " + dbType + " database" + dbName); }

    public String addNewRecipe(String title, ArrayList<String> ingredients, String instructions, User user)
    {
        int recipeID;
        int ingredientID;
        ArrayList<Ingredient> ingredientList;
        Ingredient ingredient;
        Recipe newRecipe;
        if(recipes.size()!=0)
            recipeID = (recipes.get( recipes.size() - 1) ).getRecipeID() + 1;
        else
            recipeID = 0;
        if(this.ingredients.size()!=0)
            ingredientID = (this.ingredients.get( this.ingredients.size() - 1) ).getIngredientID() + 1;
        else
            ingredientID = 0;
        ingredientList = new ArrayList<Ingredient>();

        for(int i = 0; i < ingredients.size(); i++)
        {
            ingredient = new Ingredient(ingredientID, recipeID, ingredients.get(i));
            ingredientID++;
            this.ingredients.add(ingredient);
            ingredientList.add(ingredient);
        }

        newRecipe = new Recipe(recipeID, user.getID(), title, instructions, ingredientList, 0);
        recipes.add(newRecipe);
        return "Success";
    }

    public String addNewReview(Recipe recipe, int rate, String content, User user)
    {
        int recipeID;
        int reviewID;

        recipeID = recipe.getRecipeID();
        if(reviews.size()!=0)
            reviewID = (reviews.get(reviews.size() - 1)).getReviewID() + 1;
        else
            reviewID = 0;
        Review newReview = new Review(reviewID, recipeID, user.getID(), rate, content);
        reCalRate(recipeID);
        reviews.add(newReview);

        return "Success";
    }

    public String addNewUser(String userName, String userPassword)
    {
        int userID;
        if(users.size()!=0)
            userID = (users.get(users.size()-1)).getID() + 1;
        else
            userID = 0;


        User newUser = new User(userID, userName, userPassword);
        users.add(newUser);
        return "Success";
    }

    public Recipe findRecipeByID(int recipeID)
    {
        boolean found = false;
        Recipe recipe = null;

        for(int i = 0; (i < recipes.size()) && !found ; i++) {
            Recipe curRecipe = recipes.get(i);
            if(curRecipe.getRecipeID() == recipeID)
            {
                found = true;
                recipe = curRecipe;
            }
        }

        return recipe;
    }

    public String getRecipeList(List<Recipe> recipeList) {
        recipeList.addAll(recipes);
        return "Success";
    }

    public String getReviewList(List<Review> reviewList) {
        reviewList.addAll(reviews);
        return "Success";
    }

    public String getReviewsByRecipe(List<Review> reviewList, Recipe recipe)
    {
        int curRecipeID;
        int recipeID;
        Review curReview;

        recipeID = recipe.getRecipeID();

        for(int i = 0; i < reviews.size(); i++)
        {
            curReview = reviews.get(i);
            curRecipeID =  curReview.getRecipeID();

            if(curRecipeID == recipeID)
                reviewList.add(curReview);
        }

        return "Success";
    }

    public String getRecipeListByUser(List<Recipe> recipeList, User user)
    {
        int userID;
        int curUserID;
        Recipe curRecipe;

        userID = user.getID();

        for(int i = 0; i < recipes.size(); i++)
        {
            curRecipe = recipes.get(i);
            curUserID = curRecipe.getUserID();
            if(curUserID == userID)
            {
                recipeList.add(curRecipe);
            }
        }

        return "Success";
    }

    public String getReviewListByUser(List<Review> reviewList, User user)
    {
        int userID;
        int curUserID;
        Review curReview;

        userID = user.getID();

        for(int i = 0; i < reviews.size(); i++)
        {
            curReview = reviews.get(i);
            curUserID = curReview.getUserID();
            if(curUserID == userID)
            {
                reviewList.add(curReview);
            }
        }

        return "Success";
    }

    public User getUserByName(String userName)
    {
        User curUser = null;

        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getName().equals(userName))
            {
                curUser = users.get(i);
            }
        }
        return curUser;
    }

    public Review findReviewByID(int reviewID) {
        boolean found = false;
        Review review = null;

        for (int i = 0; (i < reviews.size()) && !found; i++) {
            Review currReview = reviews.get(i);
            if (currReview.getReviewID() == reviewID) {
                found = true;
                review = currReview;
            }
        }

        return review;
    }

    public User findUserByID(int userID)
    {
        User curUser = null;

        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getID() == userID)
            {
                curUser = users.get(i);
            }
        }
        return curUser;
    }

    public String deleteUserProfile(int userID)
    {
        for(int i = 0; i < recipes.size(); i++)
        {
            if(recipes.get(i).getUserID() == userID)
            {
                int recipeID = recipes.get(i).getRecipeID();
                for(int j = 0; j < reviews.size(); j++)
                {
                    if(reviews.get(j).getRecipeID() == recipeID)
                    {
                        reviews.remove(j);
                        j = -1;
                    }
                }

                for(int j = 0; j < ingredients.size(); j++)
                {
                    if(ingredients.get(j).getRecipeID() == recipeID)
                    {
                        ingredients.remove(j);
                        j = -1;
                    }
                }
                recipes.remove(i);
                i = -1;
            }
        }

        for(int i = 0; i < reviews.size(); i++)
        {
            if(reviews.get(i).getUserID() == userID)
            {
                reviews.remove(i);
                i = -1;
            }
        }

        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getID() == userID)
            {
                users.remove(i);
                break;
            }
        }
        return "Success";
    }

    public String deleteReviewByID(int reviewID)
    {
        boolean found = false;

        for(int i = 0; i < reviews.size() && !found; i++)
        {
            if(reviews.get(i).getReviewID() == reviewID)
            {
                reviews.remove(i);
                found = true;
            }
        }
        return "Success";
    }

    public String deleteRecipeByID(int recipeID)
    {
        boolean found = false;

        for(int i = 0; i < recipes.size() && !found; i++)
        {
            if(recipes.get(i).getRecipeID() == recipeID)
            {
                for(int j = 0; j < reviews.size(); j++)
                {
                    if(reviews.get(j).getRecipeID() == recipeID)
                    {
                        reviews.remove(j);
                        j = -1;
                    }
                }

                for(int j = 0; j < ingredients.size(); j++)
                {
                    if(ingredients.get(j).getRecipeID() == recipeID)
                    {
                        ingredients.remove(j);
                        j = -1;
                    }
                }
                recipes.remove(i);
                found = true;
            }
        }
        return "Success";
    }

    public String updateRecipe(int recipeID, String title, ArrayList<String> ingredients, String instructions)
    {
        boolean found = false;
        Recipe recipe;
        int ingredientID;
        ArrayList<Ingredient> ingredientList;
        Ingredient ingredient;

        for(int i = 0; i < recipes.size() && !found; i++)
        {
            if(recipes.get(i).getRecipeID() == recipeID)
            {
                recipe = recipes.get(i);
                recipe.setTitle(title);
                recipe.setInstruction(instructions);
                for(int j = 0; j < this.ingredients.size(); j++)
                {
                    if(this.ingredients.get(j).getRecipeID() == recipeID)
                    {
                        this.ingredients.remove(j);
                        j = -1;
                    }
                }

                if(this.ingredients.size() != 0)
                    ingredientID = (this.ingredients.get( this.ingredients.size() - 1) ).getIngredientID() + 1;
                else
                    ingredientID = 0;
                ingredientList = new ArrayList<Ingredient>();

                for(int j = 0; j < ingredients.size(); j++)
                {
                    ingredient = new Ingredient(ingredientID, recipeID, ingredients.get(j));
                    ingredientID++;
                    this.ingredients.add(ingredient);
                    ingredientList.add(ingredient);
                }
                recipe.setIngredients(ingredientList);

                found = true;
            }
        }
        return "Success";
    }

    public String getRecipeByKeyword(List<Recipe> recipeList, String keyword)
    {
        for(int i = 0; i < recipes.size(); i++)
        {
            Recipe recipe;
            String title;
            String instruction;

            recipe = recipes.get(i);
            title = recipe.getTitle();
            instruction = recipe.getInstruction();

            if(title.toLowerCase().contains(keyword.toLowerCase()) || instruction.toLowerCase().contains(keyword.toLowerCase()))
            {
                recipeList.add(recipe);
            }
        }
        return "Success";
    }

    private void reCalRate(int recipeID)
    {
        Recipe curRecipe;

        curRecipe = findRecipeByID(recipeID);
        if(curRecipe != null)
        {
            int sum = 0;
            int count = 0;
            double average;

            for(int i = 0; i < reviews.size(); i++)
            {
                Review curReview = reviews.get(i);
                if(curReview.getRecipeID() == recipeID)
                {
                    sum += curReview.getRating();
                    count++;
                }
            }

            if(count > 0)
            {
                average = (double)sum/count;
                curRecipe.setAvgRating(average);
            }
        }

    }
}