package comp3350.recipebox.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import comp3350.recipebox.R;
import comp3350.recipebox.application.Main;
import comp3350.recipebox.business.AccessRecipe;
import comp3350.recipebox.business.AccessUser;
import comp3350.recipebox.objects.Recipe;
import comp3350.recipebox.business.ListSorter;

public class MainActivity extends Activity
{

    private ListView recipesList;
    private Button loginButton;
    private Button viewProfileButton;
    private Button addRecipeButton;
    private AccessRecipe accessRecipe;
    private AccessUser accessUser;
    private ArrayAdapter<Recipe> adapter;
    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        copyDatabaseToDevice();

        Main.startUp();

        setContentView(R.layout.activity_main);
        accessUser = new AccessUser();

        accessRecipe = new AccessRecipe();

        addRecipeButton = (Button) findViewById(R.id.showAddRecipeMenuButton);
        addRecipeButton.setVisibility(View.GONE);
        recipesList = (ListView) findViewById(R.id.recipeList);

        displayPostedRecipes();

        recipesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(MainActivity.this, ViewRecipeActivity.class);
                Recipe selectedRecipe = adapter.getItem(position);
                intent.putExtra("recipeID", selectedRecipe.getRecipeID());
                startActivity(intent);
            }
        });
    }

    private void displayPostedRecipes()
    {
        recipes = accessRecipe.getRecipes();
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

        loginButton = (Button) findViewById(R.id.loginButton);
        viewProfileButton = (Button) findViewById(R.id.viewProfileButton);
    }

    public void sortByRating(View view)
    {
        ListSorter sorter = new ListSorter();
        sorter.sortRecipeByRating(recipes);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        displayPostedRecipes();

        if (accessUser.getCurrentAccount() != null)
            viewAsUser();

        else
            viewAsGuest();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Main.shutDown();
    }

    private void copyDatabaseToDevice()
    {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try
        {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++)
            {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        }
        catch (IOException ioe)
        {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException
    {
        AssetManager assetManager = getAssets();

        for (String asset : assets)
        {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists())
            {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1)
                {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    public void showAddRecipeMenu(View view)
    {
        Intent addRecipe = new Intent(MainActivity.this, AddRecipeActivity.class);
        startActivity(addRecipe);
    }


    public void login(final View view)
    {
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
    }

    public void showSearchMenu(View view)
    {
        Intent search = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(search);
    }

    public void logout()
    {
        accessUser.logout();
        viewAsGuest();
    }

    private void viewAsUser()
    {
        addRecipeButton.setVisibility(View.VISIBLE);
        viewProfileButton.setVisibility(View.VISIBLE);
        loginButton.setText("LOG OUT");
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                logout();
            }
        });
    }

    private void viewAsGuest()
    {
        addRecipeButton.setVisibility(View.GONE);
        loginButton.setText("LOG IN");
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                login(v);
            }
        });
    }

    public void viewProfile(View view)
    {
        Intent intent = new Intent(MainActivity.this, ViewUserActivity.class);
        intent.putExtra("userID", new AccessUser().getCurrentUserID());
        startActivity(intent);
    }
}