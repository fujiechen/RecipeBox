package comp3350.recipebox.business;

import java.util.ArrayList;

import comp3350.recipebox.application.Main;
import comp3350.recipebox.application.Services;
import comp3350.recipebox.objects.Recipe;
import comp3350.recipebox.objects.Review;
import comp3350.recipebox.objects.User;
import comp3350.recipebox.persistence.DataAccess;

public class AccessReview
{
	private DataAccess dataAccess;
	private ArrayList<Review> reviews;

	//Constructor
	public AccessReview()
	{
		dataAccess = Services.getDataAccess(Main.dbName);
		reviews = null;
	}
	
	//Constructor
	public AccessReview(DataAccess dataAccess)
	{
		this.dataAccess = dataAccess;
		this.dataAccess = Services.getDataAccess(Main.dbName);
	}

	//Add a new review
	public boolean addReview(Recipe recipe, int rating, String text, User user)
	{
		text = text.trim();
		if (dataAccess.findRecipeByID(recipe.getRecipeID()) == null)
			return false;

		if (rating > 5 || rating < 0)
			return false;

		dataAccess.addNewReview(recipe, rating, text, user);
		return true;
	}

	//delete review by review id
	public void deleteReview(int id)
	{
		dataAccess.deleteReviewByID(id);
	}

	//Get review list by the recipe object
	public ArrayList<Review> getReviewsByRecipe(Recipe recipe)
	{
		reviews = new ArrayList<Review>();
		dataAccess.getReviewsByRecipe(reviews, recipe);
		return reviews;
	}

	//Get the review By its id
	public Review getReview(int id)
	{
		return dataAccess.findReviewByID(id);
	}

	//Get the review List by the given user object
	public ArrayList<Review> getReviewListByUser(User user)
	{
		reviews = new ArrayList<Review>();
		dataAccess.getReviewListByUser(reviews, user);
		return reviews;
	}
}
