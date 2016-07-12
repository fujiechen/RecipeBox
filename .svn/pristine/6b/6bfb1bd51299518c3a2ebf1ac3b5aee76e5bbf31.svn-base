package comp3350.recipebox.presentation;

import android.app.Activity;
import java.util.List;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import comp3350.recipebox.R;
import comp3350.recipebox.business.AccessRecipe;
import comp3350.recipebox.business.AccessReview;
import comp3350.recipebox.business.ListSorter;
import comp3350.recipebox.objects.Review;
import comp3350.recipebox.objects.Recipe;

public class ViewReviewListActivity extends Activity
{
    private TextView recipeName;
    private ListView reviewDisplay;
    private Recipe recipe;
    private ArrayAdapter<Review> adapter;
    private List<Review> reviews;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.layout_viewreviewlist);

        recipeName = (TextView) findViewById(R.id.viewReviewsHeader);
        reviewDisplay = (ListView) findViewById(R.id.reviewList);

        AccessRecipe accessRecipe = new AccessRecipe();
        AccessReview accessReview = new AccessReview();
        int recipeID = -1;

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            recipeID = extras.getInt("recipeID");
        }

        if(recipeID != -1)
        {
            recipe = accessRecipe.getRecipe(recipeID);

            recipeName.setText("Reviews for " + recipe.getTitle());

            reviews = accessReview.getReviewsByRecipe(recipe);

            adapter = new ArrayAdapter<Review>(this, android.R.layout.simple_list_item_1, reviews)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parents)
                {
                    LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
                    View reviewPreview = inflater.inflate(R.layout.layout_table, null, true);
                    TextView reviewText = (TextView)  reviewPreview.findViewById(R.id.text);
                    RatingBar rating = (RatingBar)  reviewPreview.findViewById(R.id.ratingBar);

                    reviewText.setSingleLine();
                    reviewText.setEllipsize(TextUtils.TruncateAt.END);
                    reviewText.setText(reviews.get(position).getContent());
                    rating.setRating((float)reviews.get(position).getRating());

                    return reviewPreview;
                }
            };

            reviewDisplay.setAdapter(adapter);
            reviewDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewReviewListActivity.this, ViewReviewActivity.class);
                    Review selectedReview = adapter.getItem(position);
                    intent.putExtra("reviewID", selectedReview.getReviewID());
                    System.out.println(selectedReview.getReviewID() + " " + selectedReview.getContent());
                    startActivity(intent);
                }
            });
        }
    }

    public void sortByRecipeByRating(View view)
    {
        ListSorter sorter = new ListSorter();
        sorter.sortReviewByRating(reviews);
        adapter.notifyDataSetChanged();
    }
}
