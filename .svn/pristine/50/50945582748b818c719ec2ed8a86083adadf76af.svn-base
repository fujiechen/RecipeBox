package comp3350.recipebox.business;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import comp3350.recipebox.objects.Recipe;
import comp3350.recipebox.objects.Review;

public class ListSorter
{
	public ListSorter() { }

	public void sortRecipeByRating(List<Recipe> recipes)
	{
		assert recipes != null;
		if (!recipes.isEmpty())
		{
			Collections.sort(recipes, new Comparator<Recipe>()
			{
				@Override
				public int compare(Recipe lhs, Recipe rhs)
				{
					Double lhsAvgRating = lhs.getAvgRating();
					Double rhsAvgRating = rhs.getAvgRating();
					if (lhsAvgRating.equals(rhsAvgRating) && (lhs.getTitle() != null && rhs.getTitle() != null))
					{
						String lhsTitle = lhs.getTitle().toUpperCase();
						String rhsTitle = rhs.getTitle().toUpperCase();
						return lhsTitle.compareTo(rhsTitle);
					}
					return rhsAvgRating.compareTo(lhsAvgRating);
				}
			});
		}
	}

	public void sortReviewByRating(List<Review> reviews)
	{
		if (reviews != null && !reviews.isEmpty())
		{
			Collections.sort(reviews, new Comparator<Review>()
			{
				@Override
				public int compare(Review lhs, Review rhs)
				{
					assert lhs.getRecipeID() == rhs.getRecipeID();
					Integer lhsRating = lhs.getRating();
					Integer rhsRating = rhs.getRating();
					if (lhsRating.equals(rhsRating))
						return new Integer(lhs.getReviewID()).compareTo(rhs.getReviewID());
					return rhsRating.compareTo(lhsRating);
				}
			});
		}
	}
}
