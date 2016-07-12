package comp3350.recipebox.business;

import java.util.ArrayList;

import comp3350.recipebox.application.Main;
import comp3350.recipebox.application.Services;
import comp3350.recipebox.objects.Recipe;
import comp3350.recipebox.objects.User;
import comp3350.recipebox.persistence.DataAccess;

public class AccessRecipe
{
	private DataAccess dataAccess;
	private ArrayList<Recipe> recipes;

	public AccessRecipe()
	{
		dataAccess = Services.getDataAccess(Main.dbName);
		recipes = null;
	}

	public AccessRecipe(DataAccess dataAccess)
	{
		this.dataAccess = dataAccess;
		this.dataAccess = Services.getDataAccess(Main.dbName);
	}

	public void addRecipe(String title, ArrayList<String> ingredients, String instruction, User user)
	{
		if (user == null || title == null)
		{
			throw new Error("NULL TITLE OR USER");
		}
		title = title.trim();
		instruction = instruction.trim();

		dataAccess.addNewRecipe(title, ingredients, instruction, user);
	}

	public ArrayList<Recipe> getRecipes()
	{
		recipes = new ArrayList<Recipe>();
		dataAccess.getRecipeList(recipes);

		return recipes;
	}

	public ArrayList<Recipe> getRecipesByKeyword(String keyword)
	{

		keyword = keyword.trim();
		recipes = new ArrayList<Recipe>();
		dataAccess.getRecipeByKeyword(recipes, keyword);
		return recipes;
	}

	public void deleteRecipe(int id)
	{
		dataAccess.deleteRecipeByID(id);
	}

	public void updateRecipe(int id, String title, ArrayList<String> ingredients, String instructions)
	{
		dataAccess.updateRecipe(id, title, ingredients, instructions);
	}

	public Recipe getRecipe(int id)
	{
		return dataAccess.findRecipeByID(id);
	}

	public ArrayList<Recipe> getRecipeListByUser(User user)
	{
		recipes = new ArrayList<Recipe>();
		dataAccess.getRecipeListByUser(recipes, user);
		return recipes;
	}
}
