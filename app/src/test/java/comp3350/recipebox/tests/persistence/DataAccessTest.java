package comp3350.recipebox.tests.persistence;

import android.provider.ContactsContract;

import java.util.List;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.*;

import comp3350.recipebox.persistence.DataAccess;
import comp3350.recipebox.objects.*;

public class DataAccessTest {


    @Test
    public void dataAccessTest()
    {
        String dbName = "testing";
        String dbPath = "test/RB";
        DataAccess dataAccess = new DataAccessStub(dbName);

        String title;
        String instruction;
        String content;
        User user;
        ArrayList<String> ingredients;
        Recipe recipe;
        int rate;
        String userName;
        String userPassword;
        List<Recipe> recipes;
        List<Review> reviews;
        int reviewID;
        int recipeID;
        int userID;
        String result;
        String success;
        Review review;

        dataAccess.open(dbPath);
        ingredients = new ArrayList<String>();
        ingredients.add("test ingredient 1");
        ingredients.add("test ingredient 2");
        title = "test recipe";
        instruction = "test recipe instruction";
        content = "test review content";
        userName = "testUser";
        userPassword = "testPassword";
        success = "Success";
        recipes = new ArrayList<Recipe>();
        reviews = new ArrayList<Review>();
        rate = 5;

        result = dataAccess.addNewUser(userName, userPassword);
        Assert.assertTrue(result.equals(success));

        user = dataAccess.getUserByName(userName);
        Assert.assertNotNull(user);

        Assert.assertTrue(userName.equals(user.getName()));
        Assert.assertTrue(userPassword.equals(user.getPassword()));

        userID = user.getID();
        Assert.assertTrue(userID != -1);

        user = dataAccess.findUserByID(userID);
        Assert.assertNotNull(user);

        result = dataAccess.addNewRecipe(title, ingredients, instruction, user);
        Assert.assertTrue(result.equals(success));

        result = dataAccess.getRecipeList(recipes);
        Assert.assertTrue(result.equals(success));
        Assert.assertTrue(recipes.size() != 0);

        user = dataAccess.getUserByName(userName);
        Assert.assertNotNull(user);

        result = dataAccess.getRecipeListByUser(recipes,user);
        Assert.assertTrue(result.equals(success));
        Assert.assertTrue(recipes.size() != 0);

        recipe = recipes.get(0);
        Assert.assertNotNull(recipe);
        recipeID = recipe.getRecipeID();
        Assert.assertTrue(0 == recipeID);
        recipe = dataAccess.findRecipeByID(recipeID);
        Assert.assertNotNull(recipe);
        Assert.assertTrue(recipe.equals(recipes.get(0)));

        result = dataAccess.addNewUser(userName, userPassword);
        Assert.assertTrue(result.equals(success));

        result = dataAccess.addNewReview(recipe, rate, content, user);
        Assert.assertTrue(result.equals(success));

        result = dataAccess.getReviewList(reviews);
        Assert.assertTrue(result.equals(success));
        Assert.assertTrue(reviews.size() != 0);

        result = dataAccess.getReviewsByRecipe(reviews, recipe);
        Assert.assertTrue(result.equals(success));
        Assert.assertTrue(reviews.size() != 0);

        result = dataAccess.getReviewListByUser(reviews, user);
        Assert.assertTrue(result.equals(success));
        Assert.assertTrue(reviews.size() != 0);

        review = reviews.get(0);
        Assert.assertNotNull(review);
        reviewID = review.getReviewID();
        review = dataAccess.findReviewByID(reviewID);
        Assert.assertNotNull(review);
        Assert.assertTrue(review.equals(reviews.get(0)));

        result = dataAccess.updateRecipe(recipeID, "update title", ingredients, "update instruction");
        Assert.assertTrue(result.equals(success));
        recipe = dataAccess.findRecipeByID(recipeID);
        Assert.assertNotNull(recipe);
        result = recipe.getTitle();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.equals("update title"));
        result = recipe.getInstruction();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.equals("update instruction"));

        result = dataAccess.deleteRecipeByID(recipeID);
        Assert.assertTrue(result.equals(success));
        recipe = dataAccess.findRecipeByID(recipeID);
        Assert.assertNull(recipe);

        result = dataAccess.deleteReviewByID(reviewID);
        Assert.assertTrue(result.equals(success));
        review = dataAccess.findReviewByID(reviewID);
        Assert.assertNull(review);

        result = dataAccess.deleteUserProfile(userID);
        Assert.assertTrue(result.equals(success));
        user = dataAccess.findUserByID(userID);
        Assert.assertNull(user);

        result = dataAccess.getRecipeByKeyword(recipes, "egg");
        Assert.assertTrue(result.equals(success));
        Assert.assertNotNull(recipes);

        dataAccess.close();
    }
}
