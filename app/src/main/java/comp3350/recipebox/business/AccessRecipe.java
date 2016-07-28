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

	//Constructor
	public AccessRecipe()
	{
		dataAccess = Services.getDataAccess(Main.dbName);
		recipes = null;
	}

	//Constructor
	public AccessRecipe(DataAccess dataAccess)
	{
		this.dataAccess = dataAccess;
		this.dataAccess = Services.getDataAccess(Main.dbName);
	}

	//Add new recipe
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

	//Get the whole recipe list
	public ArrayList<Recipe> getRecipes()
	{
		recipes = new ArrayList<Recipe>();
		dataAccess.getRecipeList(recipes);

		return recipes;
	}

	//Get recipes by the given keyword
	public ArrayList<Recipe> getRecipesByKeyword(String keyword)
	{

		keyword = keyword.trim();
		recipes = new ArrayList<Recipe>();
		dataAccess.getRecipeByKeyword(recipes, keyword);
		return recipes;
	}

	//Delete recipe by its id
	public void deleteRecipe(int id)
	{
		dataAccess.deleteRecipeByID(id);
	}

	//Update recipe by its id
	public void updateRecipe(int id, String title, ArrayList<String> ingredients, String instructions)
	{
		dataAccess.updateRecipe(id, title, ingredients, instructions);
	}

	//Get recipe by its id
	public Recipe getRecipe(int id)
	{
		return dataAccess.findRecipeByID(id);
	}

	//Get recipe list by the user object
	public ArrayList<Recipe> getRecipeListByUser(User user)
	{
		recipes = new ArrayList<Recipe>();
		dataAccess.getRecipeListByUser(recipes, user);
		return recipes;
	}
}
