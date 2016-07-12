package comp3350.recipebox.objects;

import java.util.ArrayList;

public class Recipe
{
    private int recipeID;
    private int userID;
    private String title;
    private String instruction;
    private ArrayList<Ingredient> ingredients;
    private double avgRating;

    public Recipe(int recipeID, int userID)
    {
        this.recipeID = recipeID;
        this.userID = userID;
        this.title = null;
        this.instruction = null;
        this.ingredients = null;
        this.avgRating = 0.0;
    }

    public Recipe(int recipeID, int userID, String title, String instruction, ArrayList<Ingredient> ingredients, double avgRating)
    {
        this.recipeID = recipeID;
        this.userID = userID;
        this.title = title;
        this.instruction = instruction;
        this.ingredients = ingredients;
        this.avgRating = avgRating;
    }

    public Recipe(Recipe recipe){
        this.recipeID = recipe.getRecipeID();
        this.userID = recipe.getUserID();
        this.title = recipe.getTitle();
        this.instruction = recipe.getInstruction();
        this.ingredients = recipe.getIngredients();
        this.avgRating = recipe.getAvgRating();
    }

    public int getRecipeID() { return recipeID; }

    public int getUserID() { return userID; }

    public String getTitle() { return title;}

    public String getInstruction() { return instruction; }

    public ArrayList<Ingredient> getIngredients() { return ingredients; }

    public double getAvgRating() { return avgRating;}

    public void setTitle(String title) { this.title = title; }

    public void setInstruction(String instruction) { this.instruction = instruction; }

    public void setIngredients(ArrayList<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }

    public void setAvgRating(double avgRating)
    {
        this.avgRating = avgRating;
    }

    public String toString()
    {
        return title;
    }

    public boolean equals(Object object)
    {
        boolean result;
        Recipe recipe;

        result = false;
        if(object instanceof Recipe)
        {
            recipe = (Recipe) object;
            if(recipe.getRecipeID() == recipeID)
            {
                result = true;
            }
        }

        return result;
    }
}