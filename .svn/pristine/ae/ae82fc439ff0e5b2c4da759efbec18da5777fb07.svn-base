package comp3350.recipebox.tests.business;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import comp3350.recipebox.business.ListSorter;
import comp3350.recipebox.objects.Recipe;
import comp3350.recipebox.objects.Review;

public class ListSorterTest
{
	public List<Recipe> recipes;
	public List<Review> reviews;
	public ListSorter sort;

	@Before
	public void setRecipes()
	{
		sort = new ListSorter();
		recipes = new ArrayList<>();
		reviews = new ArrayList<>();
	}

	@Test
	public void testSingleElement()
	{
		Recipe recipe = new Recipe(0, 0);
		recipe.setAvgRating(5.0);
		recipes.add(recipe);

		Review review = new Review(1, 0, 0, 5, "A");
		reviews.add(review);

		sort.sortRecipeByRating(recipes);
		sort.sortReviewByRating(reviews);

		Assert.assertEquals(recipes.get(0), recipe);
		Assert.assertEquals(reviews.get(0), review);

		try {
			recipes.get(1);
			Assert.fail();
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			reviews.get(1);
			Assert.fail();
		} catch (IndexOutOfBoundsException e) {

		}
	}

	@Test
	public void testMixRecipe()
	{
		Recipe recipe = new Recipe(0, 0);
		Recipe recipe1 = new Recipe(1, 0);
		Recipe recipe2 = new Recipe(2, 0);
		Recipe recipe3 = new Recipe(3, 0);
		Recipe recipe4 = new Recipe(4, 0);

		recipe.setAvgRating(4.93574);
		recipe1.setAvgRating(0.0);
		recipe2.setAvgRating(5.0);
		recipe3.setAvgRating(1.0);
		recipe4.setAvgRating(4.93573);

		recipes.add(recipe);
		recipes.add(recipe1);
		recipes.add(recipe2);
		recipes.add(recipe3);
		recipes.add(recipe4);

		List<Recipe> recipesCopy = new ArrayList<>();
		recipesCopy.addAll(recipes);

		sort.sortRecipeByRating(recipes);

		Assert.assertFalse(recipesCopy.equals(recipes));

		Assert.assertEquals(recipes.get(4), recipe1);
		Assert.assertEquals(recipes.get(3), recipe3);
		Assert.assertEquals(recipes.get(2), recipe4);
		Assert.assertEquals(recipes.get(1), recipe);
		Assert.assertEquals(recipes.get(0), recipe2);

	}

	@Test
	public void testMixReview()
	{
		int recipeId = 0;
		Review review = new Review(1, recipeId, 0, 5, "A");
		Review review1 = new Review(0, recipeId, 0, 3, "C");
		Review review2 = new Review(3, recipeId, 0, 4, "d");
		Review review3 = new Review(6, recipeId, 0, 2, "b");
		reviews.add(review);
		reviews.add(review1);
		reviews.add(review2);
		reviews.add(review3);

		sort.sortReviewByRating(reviews);

		Assert.assertTrue(reviews.get(0).getRating() > reviews.get(1).getRating());
		Assert.assertTrue(reviews.get(0).getRating() > reviews.get(2).getRating());

		Assert.assertEquals(reviews.get(3), review3);
		Assert.assertEquals(reviews.get(2), review1);
		Assert.assertEquals(reviews.get(1), review2);
		Assert.assertEquals(reviews.get(0), review);
	}

	@Test
	public void testSameReviewRating()
	{
		int recipeId = 0;
		Review review = new Review(1, recipeId, 0, 5, "A");
		Review review1 = new Review(0, recipeId, 0, 5, "C");
		Review review2 = new Review(9, recipeId, 0, 5, "B");
		Review review3 = new Review(5, recipeId, 0, 5, "d");
		Review review4 = new Review(3, recipeId, 0, 5, "E");

		reviews.add(review);
		reviews.add(review1);
		reviews.add(review2);
		reviews.add(review3);
		reviews.add(review4);

		sort.sortReviewByRating(reviews);

		Assert.assertTrue(reviews.get(0).getReviewID() < reviews.get(1).getReviewID());
		Assert.assertTrue(reviews.get(1).getReviewID() < reviews.get(2).getReviewID());

		Assert.assertEquals(reviews.get(0), review1);
		Assert.assertEquals(reviews.get(1), review);
		Assert.assertEquals(reviews.get(2), review4);
		Assert.assertEquals(reviews.get(3), review3);
		Assert.assertEquals(reviews.get(4), review2);
	}

	@Test
	public void testSameRecipeRating()
	{
		Recipe recipe = new Recipe(0, 0);
		Recipe recipe1 = new Recipe(1, 0);
		Recipe recipe2 = new Recipe(2, 0);
		Recipe recipe3 = new Recipe(3, 0);

		recipe.setTitle("jjj");
		recipe1.setTitle("ZXCVB");
		recipe2.setTitle("AaAa");
		recipe3.setTitle("zxcvb");

		recipes.add(recipe);
		recipes.add(recipe1);
		recipes.add(recipe2);
		recipes.add(recipe3);

		sort.sortRecipeByRating(recipes);

		Assert.assertEquals(recipes.get(0).getTitle(), "AaAa");
		Assert.assertEquals(recipes.get(1).getTitle(), "jjj");
		Assert.assertEquals(recipes.get(2).getTitle(), "ZXCVB");
		Assert.assertEquals(recipes.get(3).getTitle(), "zxcvb");
	}

	@Test
	public void testNullList()
	{
		recipes = null;
		reviews = null;
		try {
			sort.sortRecipeByRating(recipes);
			Assert.fail();
		} catch (AssertionError e) {
		}
		try {
			sort.sortReviewByRating(reviews);
			Assert.fail();
		} catch (AssertionError e) {
		}
	}

	@Test
	public void testEmptyList()
	{
		List<Recipe> recipesCopy = new ArrayList<>();
		recipesCopy.addAll(recipes);
		sort.sortRecipeByRating(recipes);
		Assert.assertTrue(recipes.size() == 0);
		Assert.assertTrue(recipes.equals(recipesCopy));

		List<Review> reviewsCopy = new ArrayList<>();
		reviewsCopy.addAll(reviews);
		sort.sortReviewByRating(reviews);
		Assert.assertTrue(recipes.size() == 0);
		Assert.assertTrue(reviews.equals(reviewsCopy));
	}

	@Test(expected = AssertionError.class)
	public void testInconsistentRecipeId()
	{
		reviews.add(new Review(1, 0, 0, 5, "A"));
		reviews.add(new Review(0, 1, 0, 3, "C"));
		reviews.add(new Review(3, 2, 0, 4, "B"));
		sort.sortReviewByRating(reviews);
	}
}
