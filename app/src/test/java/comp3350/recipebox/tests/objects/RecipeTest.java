package comp3350.recipebox.tests.objects;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import comp3350.recipebox.objects.Ingredient;
import comp3350.recipebox.objects.Recipe;

public class RecipeTest
{
    private int recipeID;
    private String title;
    private String instruction;
    private ArrayList<Ingredient> ingredients1;
    private ArrayList<Ingredient> ingredients2;
    private double avgRating;

    @Before
    public void setUpTest()
    {
		recipeID = 5;
		title = "Recipe Test";
		instruction = "Test the recipe";
		ingredients1 = new ArrayList<Ingredient>();
		ingredients2 = new ArrayList<Ingredient>();

		Ingredient ingredient1 = new Ingredient(0, 1, "Ingredient 1");
		Ingredient ingredient2 = new Ingredient(1, 1, "Ingredient 2");
		ingredients1.add(ingredient1);
		ingredients1.add(ingredient2);
		ingredient1 = new Ingredient(2, 1, "Ingredient 3");
		ingredient2 = new Ingredient(3, 1, "Ingredient 4");
		ingredients2.add(ingredient1);
		ingredients2.add(ingredient2);

		avgRating = 3.3;
    }

    @Test
    public void testRecipe()
    {
        Recipe recipe1;
        Recipe recipe2;
        recipe1 = new Recipe(recipeID, 0, title, instruction, ingredients1, avgRating);

        Assert.assertNotNull(recipe1);
        Assert.assertTrue(recipeID == recipe1.getRecipeID());
        Assert.assertTrue(title.equals(recipe1.getTitle()));
        Assert.assertTrue(instruction.equals(recipe1.getInstruction()));
        Assert.assertEquals(avgRating, recipe1.getAvgRating(), 0.0);
        Assert.assertTrue(ingredients1 == recipe1.getIngredients());

        title = "Another test";
        instruction = "Do some more stuff";
        avgRating = 4.0;

        recipe1.setTitle(title);
        recipe1.setInstruction(instruction);
        recipe1.setAvgRating(avgRating);
        recipe1.setIngredients(ingredients2);

        Assert.assertTrue(title.equals(recipe1.getTitle()));
        Assert.assertTrue(instruction.equals(recipe1.getInstruction()));
        Assert.assertEquals(avgRating, recipe1.getAvgRating(), 0.0);
        Assert.assertTrue(ingredients2 == recipe1.getIngredients());

        recipe2 = new Recipe(recipeID, 0, title, instruction, ingredients1, avgRating);
        Assert.assertTrue(recipe1.equals(recipe2));
        Assert.assertTrue(recipe1.toString().equals(recipe2.toString()));
    }

}
