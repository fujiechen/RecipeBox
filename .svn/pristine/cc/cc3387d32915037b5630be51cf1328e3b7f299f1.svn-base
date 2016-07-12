package comp3350.recipebox.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.view.View;

import comp3350.recipebox.R;
import comp3350.recipebox.business.AccessRecipe;
import comp3350.recipebox.business.AccessReview;
import comp3350.recipebox.business.AccessUser;

public class AddReviewActivity extends AppCompatActivity
{
    private String reviewText;
    private int rating;
    private EditText textField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addreview);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.addReviewRatingBar);
        textField = (EditText) findViewById(R.id.addReviewTextField);
        submitButton = (Button) findViewById(R.id.submitReviewButton);
        rating = -1;

        textField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                reviewText = textField.getText().toString();
                refreshSubmitButton();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
            {
                AddReviewActivity.this.rating = Math.round(ratingBar.getRating());
                refreshSubmitButton();
            }
        });

        submitButton.setEnabled(false);
    }

    private void refreshSubmitButton()
    {
        submitButton.setEnabled(rating >= 0 && reviewText != null && reviewText.length() > 0);
    }

    public void addReview(View view)
    {
        AccessReview accessReview = new AccessReview();
        AccessRecipe accessRecipe = new AccessRecipe();
        AccessUser accessUser = new AccessUser();
        Bundle extras = getIntent().getExtras();
        int recipeID;

        if (extras != null)
        {
            recipeID = extras.getInt("recipeID");
            accessReview.addReview(accessRecipe.getRecipe(recipeID), rating, textField.getText().toString(), accessUser.getCurrentAccount());
            finish();
        }
    }
}