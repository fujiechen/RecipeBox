package comp3350.recipebox.tests.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import comp3350.recipebox.application.Services;
import comp3350.recipebox.application.Main;

import comp3350.recipebox.objects.Recipe;
import comp3350.recipebox.objects.Review;
import comp3350.recipebox.objects.User;
import comp3350.recipebox.persistence.DataAccess;

public class DataAccessHSQLDBTest
{
    private static String dbName = Main.dbName;
    private DataAccess dataAccess;

    private String title;
    private String instruction;
    private String content;
    private User user;
    private ArrayList<String> ingredients;
    private Recipe recipe;
    private int rate;
    private String userName;
    private String userPassword;
    private List<Recipe> recipes;
    private List<Review> reviews;
    private int reviewID;
    private int recipeID;
    private int userID;
    private String result;
    private Review review;

    public DataAccessHSQLDBTest()
    {
    }

    @Before
    public void setUpTest()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting Integration test DataAccess (using default DB)");

        Services.createDataAccess(dbName);
        dataAccess = Services.getDataAccess(dbName);

        ingredients = new ArrayList<String>();
        ingredients.add("test ingredient 1");
        ingredients.add("test ingredient 2");
        title = "test recipe";
        instruction = "test recipe instruction";
        content = "test review content";
        userName = "testUser";
        userPassword = "testPassword";
        recipes = new ArrayList<Recipe>();
        reviews = new ArrayList<Review>();
        rate = 5;
    }

    @Test
    public void dataAccessTest()
    {
        result = dataAccess.addNewUser(userName, userPassword);
        Assert.assertNull(result);

        user = dataAccess.getUserByName(userName);
        Assert.assertNotNull(user);

        Assert.assertTrue(userName.equals(user.getName()));
        Assert.assertTrue(userPassword.equals(user.getPassword()));

        userID = user.getID();
        Assert.assertTrue(userID != -1);

        user = dataAccess.findUserByID(userID);
        Assert.assertNotNull(user);

        result = dataAccess.addNewRecipe(title, ingredients, instruction, user);
        Assert.assertNull(result);

        result = dataAccess.getRecipeList(recipes);
        Assert.assertNull(result);
        Assert.assertTrue(recipes.size() != 0);

        user = dataAccess.getUserByName(userName);
        Assert.assertNotNull(user);

        result = dataAccess.getRecipeListByUser(recipes,user);
        Assert.assertNull(result);
        Assert.assertTrue(recipes.size() != 0);

        recipe = dataAccess.findRecipeByID(0);
        Assert.assertNotNull(recipe);
        recipeID = recipe.getRecipeID();
        Assert.assertTrue(0 == recipeID);
        recipe = dataAccess.findRecipeByID(recipeID);
        Assert.assertNotNull(recipe);
        Assert.assertTrue(recipe.equals(recipes.get(0)));

        result = dataAccess.addNewUser(userName, userPassword);
        Assert.assertNull(result);

        result = dataAccess.addNewReview(recipe, rate, content, user);
        Assert.assertNull(result);

        result = dataAccess.getReviewList(reviews);
        Assert.assertNull(result);
        Assert.assertTrue(reviews.size() != 0);

        result = dataAccess.getReviewsByRecipe(reviews, recipe);
        Assert.assertNull(result);
        Assert.assertTrue(reviews.size() != 0);

        result = dataAccess.getReviewListByUser(reviews, user);
        Assert.assertNull(result);
        Assert.assertTrue(reviews.size() != 0);

        review = reviews.get(0);
        Assert.assertNotNull(review);
        reviewID = review.getReviewID();
        review = dataAccess.findReviewByID(reviewID);
        Assert.assertNotNull(review);
        Assert.assertTrue(review.equals(reviews.get(0)));

        result = dataAccess.updateRecipe(recipeID, "update title", ingredients, "update instruction");
        Assert.assertNull(result);
        recipe = dataAccess.findRecipeByID(recipeID);
        Assert.assertNotNull(recipe);
        result = recipe.getTitle();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.equals("update title"));
        result = recipe.getInstruction();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.equals("update instruction"));

        result = dataAccess.deleteReviewByID(reviewID);
        Assert.assertNull(result);

        result = dataAccess.deleteRecipeByID(recipeID);
        Assert.assertNull(result);

        result = dataAccess.deleteUserProfile(userID);
        Assert.assertNull(result);

        result = dataAccess.getRecipeByKeyword(recipes, "egg");
        Assert.assertNull(result);
        Assert.assertNotNull(recipes);
    }


    @After
    public void tearDown()
    {
        Services.closeDataAccess();
        System.out.println("Finished Integration test DataAccess (using default DB)");
    }
}