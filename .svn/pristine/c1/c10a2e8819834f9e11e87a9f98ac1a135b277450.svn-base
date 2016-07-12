package comp3350.recipebox.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.recipebox.objects.*;

public interface DataAccess
{
    void open(String dbPath);

    void close();

    String addNewRecipe(String title, ArrayList<String> ingredients, String instructions, User user);

    String addNewReview(Recipe recipe, int rate, String content, User user);

    String addNewUser(String userName, String userPassword);

    User getUserByName(String userName);

    String getRecipeList(List<Recipe> recipeList);

    String getReviewList(List<Review> reviewList);

    String getReviewsByRecipe(List<Review> reviewList, Recipe recipe);

    String getRecipeListByUser(List<Recipe> recipeList, User user);

    String getReviewListByUser(List<Review> reviewList, User user);

    Recipe findRecipeByID(int recipeID);

    Review findReviewByID(int reviewID);

    User findUserByID(int userID);

    String deleteUserProfile(int userID);

    String deleteReviewByID(int reviewID);

    String deleteRecipeByID(int recipeID);

    String updateRecipe(int recipeID, String title, ArrayList<String> ingredients, String instructions);

    String getRecipeByKeyword(List<Recipe> recipeList, String keyword);
}
