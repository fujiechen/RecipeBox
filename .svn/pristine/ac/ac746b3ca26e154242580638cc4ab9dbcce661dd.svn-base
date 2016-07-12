package comp3350.recipebox.objects;

public class Ingredient
{
    private int ingredientID;
    private int recipeID;
    private String content;

    public Ingredient(int ingredientID, int recipeID, String content)
    {
        this.ingredientID = ingredientID;
        this.recipeID = recipeID;
        this.content = content;
    }

    public int getIngredientID()
    {
        return ingredientID;
    }

    public int getRecipeID()
    {
        return recipeID;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String toString()
    {
        return content;
    }

    public boolean equals(Object object)
    {
        boolean result;
        Ingredient i;

        result = false;
        if(object instanceof Ingredient)
        {
            i = (Ingredient) object;
            if(i.getIngredientID() == ingredientID)
            {
                result = true;
            }
        }

        return result;
    }
}