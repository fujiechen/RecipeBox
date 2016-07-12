package comp3350.recipebox.presentation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import comp3350.recipebox.R;
import comp3350.recipebox.business.AccessRecipe;
import comp3350.recipebox.business.AccessUser;
import comp3350.recipebox.objects.Ingredient;
import comp3350.recipebox.objects.Recipe;
import comp3350.recipebox.objects.User;

public class ViewRecipeActivity extends Activity
{

    private Recipe recipeView;
    private User user;
    private AccessRecipe accessRecipe;
    private AccessUser accessUser;
    private TextView titleText;
    private TextView contentText;
    private ListView ingredientsList;
    private ArrayAdapter<Ingredient> adapter;
    private Button addReviewButton;
    private Button editRecipeButton;
    private Button deleteRecipeButton;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        setContentView(R.layout.layout_viewrecipe);

        accessRecipe = new AccessRecipe();
        accessUser = new AccessUser();

        titleText = (TextView) findViewById(R.id.recipeTitle);
        ingredientsList = (ListView) findViewById(R.id.listView);
        contentText = (TextView) findViewById(R.id.textView4);
        addReviewButton = (Button) findViewById(R.id.addReviewButton);
        editRecipeButton = (Button) findViewById(R.id.editRecipeButton);
        editRecipeButton.setVisibility(View.GONE);
        deleteRecipeButton = (Button) findViewById(R.id.deleteRecipeButton);
        deleteRecipeButton.setVisibility(View.GONE);

        int recipeID = -1;


        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            recipeID = extras.getInt("recipeID");
        }

        if(recipeID != -1)
        {
            recipeView = accessRecipe.getRecipe(recipeID);

            populateRecipe();

            if (accessUser.getCurrentAccount() == null)
                addReviewButton.setVisibility(View.GONE);

            if(recipeView.getUserID() != -1)
            {
                user = accessUser.getUserByID(recipeView.getUserID());

                if (user.equals(accessUser.getCurrentAccount())) {
                    addReviewButton.setVisibility(View.GONE);
                    editRecipeButton.setVisibility(View.VISIBLE);
                    deleteRecipeButton.setVisibility(View.VISIBLE);
                }

                ((TextView) findViewById(R.id.authorName)).setText("Written by: " + user.getName());

                findViewById(R.id.authorButton).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(ViewRecipeActivity.this, ViewUserActivity.class);
                        intent.putExtra("userID", recipeView.getUserID());
                        startActivity(intent);
                    }
                });

                editRecipeButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(ViewRecipeActivity.this, EditRecipeActivity.class);
                        intent.putExtra("recipeID", recipeView.getRecipeID());
                        startActivity(intent);
                    }
                });

                deleteRecipeButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        deleteRecipeDialog();
                    }
                });
            }
        }
    }

    private void populateRecipe() {
        contentText.setText(recipeView.getInstruction());
        titleText.setText(recipeView.getTitle());

        adapter = new ArrayAdapter<Ingredient>(
                this, android.R.layout.simple_list_item_1, recipeView.getIngredients());
        ingredientsList.setAdapter(adapter);
    }

    public void showReviews(View view)
    {
        Intent intent = new Intent(ViewRecipeActivity.this, ViewReviewListActivity.class);
        intent.putExtra("recipeID", recipeView.getRecipeID());
        startActivity(intent);
    }

    public void addReview(View view)
    {
        Intent intent = new Intent(ViewRecipeActivity.this, AddReviewActivity.class);
        intent.putExtra("recipeID", recipeView.getRecipeID());
        startActivity(intent);
    }

    private void deleteRecipeDialog()
    {
        AlertDialog.Builder builder;
        AlertDialog dialog;

        builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this recipe?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                accessRecipe.deleteRecipe(recipeView.getRecipeID());

                dialog.dismiss();

                Intent intent = new Intent(ViewRecipeActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        recipeView = accessRecipe.getRecipe(recipeView.getRecipeID());
        populateRecipe();
    }

}