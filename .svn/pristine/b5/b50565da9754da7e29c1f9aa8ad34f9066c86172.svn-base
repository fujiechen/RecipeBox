package comp3350.recipebox.tests.objects;

import org.junit.Assert;
import org.junit.Test;

import comp3350.recipebox.objects.Ingredient;

public class IngredientTest
{

	@Test
	public void testIngredient1()
	{

		String content;
		int IngID;
		int RecipeID;

		content = "This is the content";
		IngID = 5;
		RecipeID = 10;

		Ingredient ingredient1;
		Ingredient ingredient2;
		Ingredient ingredient3;

		ingredient1 = new Ingredient(IngID, RecipeID, content);
		Assert.assertNotNull(ingredient1);
		Assert.assertEquals(content, ingredient1.getContent());
		Assert.assertEquals(IngID, ingredient1.getIngredientID());
		Assert.assertEquals(RecipeID, ingredient1.getRecipeID());

		content = "Here is some new content";

		ingredient1.setContent(content);
		Assert.assertEquals(content, ingredient1.getContent());
		Assert.assertEquals(content, ingredient1.toString());

		ingredient2 = new Ingredient(IngID, RecipeID, content);
		Assert.assertEquals(ingredient1, ingredient2);

		content = "This is the content";
		ingredient3 = new Ingredient(IngID, RecipeID, content);
		Assert.assertEquals(ingredient1, ingredient2);
	}
}
