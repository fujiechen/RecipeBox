package comp3350.recipebox.objects;

public class Review
{
    private int reviewID;
    private int recipeID;
    private int userID;
    private int rating;
    private String content;

    public Review(int reviewID, int recipeID, int userID)
    {
        this.reviewID = reviewID;
        this.recipeID = recipeID;
        this.userID = userID;
    }

    public Review(int reviewID, int recipeID, int userID, int rating, String content)
    {
        this.reviewID = reviewID;
        this.recipeID = recipeID;
        this.userID = userID;
        this.rating = rating;
        this.content = content;
    }

    public int getReviewID()
    {
        return reviewID;
    }

    public int getRecipeID()
    {
        return recipeID;
    }

    public int getUserID()
    {
        return userID;
    }

    public int getRating()
    {
        return rating;
    }

    public String getContent()
    {
        return content;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String toString()
    {
        return "RecipeID: " + recipeID + " ReviewID: " + reviewID +
                " Rating: " + rating + "Content: " + content;
    }

    public boolean equals(Object object)
    {
        boolean result;
        Review review;

        result = false;
        if(object instanceof Review)
        {
            review = (Review) object;
            if(review.reviewID == reviewID)
            {
                result = true;
            }
        }

        return result;
    }
}