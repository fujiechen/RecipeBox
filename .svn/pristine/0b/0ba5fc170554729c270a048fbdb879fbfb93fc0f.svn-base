package comp3350.recipebox.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import comp3350.recipebox.R;
import comp3350.recipebox.business.AccessRecipe;
import comp3350.recipebox.objects.Recipe;

public class SearchActivity extends Activity
{

    private ListView recipesList;
    private Button searchButton;
    private ArrayAdapter<Recipe> adapter;
    private List<Recipe> recipes;
    private String keyword;
    private EditText searchField;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        setContentView(R.layout.activity_search);

        searchButton = (Button) findViewById(R.id.searchButton);
        recipesList = (ListView) findViewById(R.id.recipeList);
        searchField = (EditText) findViewById(R.id.searchField);

        refresh();

        displayPostedRecipes();

        recipesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(SearchActivity.this, ViewRecipeActivity.class);
                Recipe selectedRecipe = adapter.getItem(position);
                intent.putExtra("recipeID", selectedRecipe.getRecipeID());
                startActivity(intent);
            }
        });

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                keyword = searchField.getText().toString();
                refresh();
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                keyword = searchField.getText().toString();
                refresh();
            }
        });

    }

    private void displayPostedRecipes()
    {
        recipes = new AccessRecipe().getRecipes();
        adapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_1, recipes)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parents)
            {
                LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
                View recipeWithRating = inflater.inflate(R.layout.layout_table, null, true);
                TextView recipeName = (TextView)  recipeWithRating.findViewById(R.id.text);
                RatingBar rating = (RatingBar)  recipeWithRating.findViewById(R.id.ratingBar);

                recipeName.setText(recipes.get(position).getTitle());
                rating.setRating((float)recipes.get(position).getAvgRating());

                return recipeWithRating;
            }
        };

        recipesList.setAdapter(adapter);

    }

    private void displaySearchResult()
    {
        recipes = new AccessRecipe().getRecipesByKeyword(keyword);
        adapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_1, recipes)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parents)
            {
                LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
                View recipeWithRating = inflater.inflate(R.layout.layout_table, null, true);
                TextView recipeName = (TextView)  recipeWithRating.findViewById(R.id.text);
                RatingBar rating = (RatingBar)  recipeWithRating.findViewById(R.id.ratingBar);

                recipeName.setText(recipes.get(position).getTitle());
                rating.setRating((float)recipes.get(position).getAvgRating());

                return recipeWithRating;
            }
        };

        recipesList.setAdapter(adapter);
    }

    private void refresh()
    {

        if(keyword != null && keyword.trim().length() > 0)
        {
            searchButton.setEnabled(true);
            searchButton.setClickable(true);
        }
        else
        {
            searchButton.setEnabled(false);
            searchButton.setClickable(false);
            displayPostedRecipes();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        displayPostedRecipes();

        if (keyword == null || keyword.equals(""))
            displayPostedRecipes();
        else
            displaySearchResult();
    }

    public void searchByKeyword(View view)
    {
        if(keyword != null || keyword.trim().length() > 0)
            displaySearchResult();
        else
            displayPostedRecipes();
    }

}