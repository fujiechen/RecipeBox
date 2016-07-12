package comp3350.recipebox.tests.business;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import comp3350.recipebox.application.Services;
import comp3350.recipebox.business.AccessRecipe;
import comp3350.recipebox.objects.Recipe;
import comp3350.recipebox.objects.User;
import comp3350.recipebox.persistence.DataAccess;
import comp3350.recipebox.tests.persistence.DataAccessStub;

public class AccessRecipeTest
{
	public AccessRecipe accessRecipe;

	public AccessRecipeTest()
	{
		String testing = "testing";
		DataAccess dataAccess = new DataAccessStub(testing);
		Services.createDataAccess(dataAccess);
		accessRecipe = new AccessRecipe(dataAccess);
	}

	@Test
	public void testRecipeAccessor()
	{
		String title, instruction;
		ArrayList<String> ingredients;
		int id;

		title = "  Testing Title  ";
		instruction = "  Testing instruction  ";
		ingredients = new ArrayList<>();
		ingredients.add("test1");
		ingredients.add("test2");
		accessRecipe.addRecipe(title, ingredients, instruction, new User(1, "test", "password"));


		ArrayList<Recipe> recipes = accessRecipe.getRecipes();
		Assert.assertNotNull(recipes);

		id = recipes.size() - 1;
		Recipe recipe = accessRecipe.getRecipe(id);
		Assert.assertEquals(recipe.getTitle(), "Testing Title");
		Assert.assertEquals(recipe.getInstruction(), "Testing instruction");
		Assert.assertEquals(recipe.getIngredients().get(0).getContent(), ingredients.get(0));

		recipe = accessRecipe.getRecipe(Integer.MAX_VALUE);
		Assert.assertNull(recipe);

		recipes = accessRecipe.getRecipesByKeyword("Testing");
		Assert.assertNotNull(recipes);


	}
}
