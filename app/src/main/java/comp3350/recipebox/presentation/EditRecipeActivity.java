package comp3350.recipebox.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import comp3350.recipebox.R;
import comp3350.recipebox.business.AccessRecipe;
import comp3350.recipebox.objects.Ingredient;
import comp3350.recipebox.objects.Recipe;

public class EditRecipeActivity extends Activity
{

    private EditText titleField;
    private ListView ingredientsList;
    private EditText quantityField;
    private EditText ingredientNameField;
    private Button addIngredientButton;
    private Button removeIngredientButton;
    private EditText instructionsField;
    private Button submitButton;

    private ArrayList<String> ingredients;


    private String title;
    private String quantity;
    private String ingredientName;
    private String instructions;

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.layout_addrecipe);

        quantity = "";
        ingredientName = "";

        titleField = (EditText) findViewById(R.id.titleField);
        ingredientsList = (ListView) findViewById(R.id.ingredientsList);
        addIngredientButton = (Button) findViewById(R.id.addButton);
        removeIngredientButton = (Button) findViewById(R.id.removeButton);
        quantityField = (EditText) findViewById(R.id.quantityField);
        ingredientNameField = (EditText) findViewById(R.id.ingredientNameField);
        instructionsField = (EditText) findViewById(R.id.instructionsField);
        submitButton = (Button)findViewById(R.id.submitButton);

        int recipeID = -1;

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            recipeID = extras.getInt("recipeID");
        }

        if(recipeID != -1) {
            recipe = new AccessRecipe().getRecipe(recipeID);

            title = recipe.getTitle();
            titleField.setText(title);

            instructions = recipe.getInstruction();
            instructionsField.setText(instructions);

            ingredients = new ArrayList<String>();

            for(Ingredient ing : recipe.getIngredients())
            {
                ingredients.add(ing.getContent());
            }

            ingredientsList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ingredients));
        }

        titleField.addTextChangedListener(new TextWatcher()
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
                title = titleField.getText().toString();
                refresh();
            }
        });

        quantityField.addTextChangedListener(new TextWatcher()
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
                quantity = quantityField.getText().toString();
                refresh();
            }
        });

        ingredientNameField.addTextChangedListener(new TextWatcher()
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
                ingredientName = ingredientNameField.getText().toString();
                refresh();
            }
        });

        instructionsField.addTextChangedListener(new TextWatcher()
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
                instructions = instructionsField.getText().toString();
                refresh();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AccessRecipe().updateRecipe(recipe.getRecipeID(), title, ingredients, instructions);
                finish();
            }
        });

        refresh();
    }

    public void addIngredient(View view)
    {
        quantity = quantity.trim();
        if (quantity.length() > 0)
            quantity += " ";
        ingredients.add(quantity + ingredientName.trim());
        ((ArrayAdapter<String>) ingredientsList.getAdapter()).notifyDataSetChanged();
        ingredientsList.smoothScrollToPosition(ingredients.size() - 1);
        quantityField.setText("");
        ingredientNameField.setText("");

        refresh();
    }

    public void removeIngredient(View view)
    {
        if(ingredients.size() != 0)
        {
            ingredients.remove(ingredients.size() - 1);
            ((ArrayAdapter<String>) ingredientsList.getAdapter()).notifyDataSetChanged();
        }
        refresh();
    }

    private void refresh()
    {
        if(title != null && title.trim().length() > 0
                && ingredients != null && ingredients.size() > 0
                && instructions != null && instructions.trim().length() > 0)
        {
            submitButton.setEnabled(true);
        }
        else
        {
            submitButton.setEnabled(false);
            submitButton.setClickable(true);
        }

        addIngredientButton.setEnabled(quantity != null && quantity.length() > 0
                && ingredientName != null && ingredientName.length() > 0);
        removeIngredientButton.setEnabled(ingredients.size() > 0);
    }

}
