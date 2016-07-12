package comp3350.recipebox.tests.objects;

import org.junit.Assert;
import org.junit.Test;

import comp3350.recipebox.objects.Review;

public class ReviewTest
{
	@Test
	public void testRecipe1()
	{
		int reviewID;
		int recipeID;
		int rate;
		String content;

		reviewID = 0;
		recipeID = 0;
		rate = 5;
		content = "This is a test";

		Review review1;
		Review review2;
		review1 = new Review(reviewID, 0, recipeID, rate, content);

		Assert.assertNotNull(review1);
		Assert.assertTrue(reviewID == review1.getReviewID());
		Assert.assertTrue(recipeID == review1.getRecipeID());
		Assert.assertTrue(rate == review1.getRating());
		Assert.assertTrue(content.equals(review1.getContent()));

		rate = 4;
		content = "Here is another test";

		review1.setRating(rate);
		review1.setContent(content);

		Assert.assertTrue(rate == review1.getRating());
		Assert.assertTrue(content.equals(review1.getContent()));

		review2 = new Review(reviewID, 0, recipeID, rate, content);
		Assert.assertTrue(review2.equals(review1));
		Assert.assertTrue(review2.toString().equals(review1.toString()));
	}

}
