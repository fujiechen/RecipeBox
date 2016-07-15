package comp3350.recipebox.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

import comp3350.recipebox.objects.*;

public class DataAccessObject implements DataAccess
{
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    //Constructor
    public DataAccessObject(String dbName)
    {
        this.dbName = dbName;
    }
    
    //Open HSQLDB connection
    public void open(String dbPath)
    {
        String url;
        try {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath;
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    //Close HSQLDB connection
    public void close()
    {
        try
        {
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    //Add a new recipe
    public String addNewRecipe(String title, ArrayList<String> ingredients, String instructions, User user)
    {
        String values;
        int recipeID;

        recipeID = getNextRecipeID();
        result = null;
        try
        {
            values = recipeID + "," + user.getID() + ",'"
                    + title + "','" + instructions
                    + "'," + 0;
            cmdString = "Insert into Recipe " + " Values(" + values + ")";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);

            addNewIngredient(recipeID, ingredients);
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    //Add a new review
    public String addNewReview(Recipe recipe, int rate, String content, User user)
    {
        String values;
        int reviewID;

        reviewID = getNextReviewID();
        result = null;
        try
        {
            values = reviewID + "," + recipe.getRecipeID() + "," + user.getID()
                    + "," + rate + ",'" + content + "'";
            cmdString = "Insert into Review " + " Values(" + values + ")";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);

            reCalRate(recipe.getRecipeID());
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }
    
    //Add a new user
    public String addNewUser(String userName, String userPassword)
    {
        String values;
        int userID;

        userID = getNextUserID();
        result = null;
        try
        {
            values = userID + ",'" + userName + "','"
                    + userPassword + "'";
            cmdString = "Insert into User " + " Values(" + values + ")";
            updateCount = st3.executeUpdate(cmdString);
            result = checkWarning(st3, updateCount);
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }
    
    //Get a User object by given user name
    public User getUserByName(String userName)
    {
        User user;
        int userID;
        String userPassword;

        user = null;
        userID = 0;
        userPassword = EOF;

        result = null;
        try
        {
            cmdString  = "Select * from User Where userName='" + userName+"'";

            rs2 = st1.executeQuery(cmdString);

            if(rs2.next()) {
                userID = rs2.getInt("userID");
                userPassword = rs2.getString("userPassword");

                user = new User(userID, userName, userPassword);
            }
            rs2.close();
        }
        catch(Exception e)
        {
            processSQLError(e);
        }

        return user;
    }

    //Get whole recipe list
    public String getRecipeList(List<Recipe> recipeList)
    {
        Recipe recipe;
        int recipeID, userID;
        String title, instruction;
        double avgRating;
        ArrayList<Ingredient> ingredients;

        recipeID = 0;
        userID = 0;
        title = EOF;
        instruction = EOF;
        avgRating = 0.0;
        ingredients = null;

        result = null;
        try
        {
            cmdString  = "Select * from Recipe";
            rs2 = st1.executeQuery(cmdString);
            while(rs2.next())
            {
                recipeID = rs2.getInt("recipeID");
                userID = rs2.getInt("userID");
                title = rs2.getString("title");
                instruction = rs2.getString("instruction");
                avgRating = rs2.getDouble("avgRating");

                ingredients = new ArrayList<Ingredient>();
                findIngredientByRecipeID(ingredients, recipeID);
                recipe = new Recipe(recipeID, userID, title, instruction, ingredients, avgRating);
                recipeList.add(recipe);
            }
            rs2.close();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }
    
    //Get whole review list
    public String getReviewList(List<Review> reviewList)
    {
        Review review;
        int reviewID, recipeID, userID, rate;
        String content;

        reviewID = 0;
        recipeID = 0;
        userID = 0;
        rate = 0;
        content = EOF;

        result = null;
        try
        {
            cmdString  = "Select * from Review";
            rs2 = st1.executeQuery(cmdString);
            while(rs2.next())
            {
                reviewID = rs2.getInt("reviewID");
                recipeID = rs2.getInt("recipeID");
                userID = rs2.getInt("userID");
                rate = rs2.getInt("rate");
                content = rs2.getString("content");

                review = new Review(reviewID, recipeID, userID, rate, content);
                reviewList.add(review);
            }
            rs2.close();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }
    
    //Get review list related to the given recipe
    public String getReviewsByRecipe(List<Review> reviewList, Recipe recipe)
    {
        Review review;
        int reviewID, recipeID, userID, rate;
        String content;

        reviewID = 0;
        recipeID = 0;
        userID = 0;
        rate = 0;
        content = EOF;

        result = null;
        try
        {
            cmdString  = "Select * from Review Where recipeID=" + recipe.getRecipeID();
            rs2 = st1.executeQuery(cmdString);
            while(rs2.next())
            {
                reviewID = rs2.getInt("reviewID");
                recipeID = rs2.getInt("recipeID");
                userID = rs2.getInt("userID");
                rate = rs2.getInt("rate");
                content = rs2.getString("content");

                review = new Review(reviewID, recipeID, userID, rate, content);
                reviewList.add(review);
            }
            rs2.close();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    //Get recipe list related to the given user
    public String getRecipeListByUser(List<Recipe> recipeList, User user)
    {
        Recipe recipe;
        int recipeID, userID;
        String title, instruction;
        double avgRating;
        ArrayList<Ingredient> ingredients;

        recipeID = 0;
        userID = 0;
        title = EOF;
        instruction = EOF;
        avgRating = 0.0;
        ingredients = null;

        result = null;
        try
        {
            cmdString  = "Select * from Recipe Where userID=" + user.getID();
            rs4 = st3.executeQuery(cmdString);
            while(rs4.next())
            {
                recipeID = rs4.getInt("recipeID");
                userID = rs4.getInt("userID");
                title = rs4.getString("title");
                instruction = rs4.getString("instruction");
                avgRating = rs4.getDouble("avgRating");

                ingredients = new ArrayList<Ingredient>();
                findIngredientByRecipeID(ingredients, recipeID);
                recipe = new Recipe(recipeID, userID, title, instruction, ingredients, avgRating);
                recipeList.add(recipe);
            }
            rs4.close();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    //Get review list related to the given user
    public String getReviewListByUser(List<Review> reviewList, User user)
    {
        Review review;
        int reviewID, recipeID, userID, rate;
        String content;

        reviewID = 0;
        recipeID = 0;
        userID = 0;
        rate = 0;
        content = EOF;

        result = null;
        try
        {
            cmdString  = "Select * from Review Where userID=" + user.getID();
            rs2 = st1.executeQuery(cmdString);
            while(rs2.next())
            {
                reviewID = rs2.getInt("reviewID");
                recipeID = rs2.getInt("recipeID");
                userID = rs2.getInt("userID");
                rate = rs2.getInt("rate");
                content = rs2.getString("content");

                review = new Review(reviewID, recipeID, userID, rate, content);
                reviewList.add(review);
            }
            rs2.close();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    //Get recipe object by recipe ID
    public Recipe findRecipeByID(int recipeID)
    {
        Recipe recipe;
        int userID;
        String title, instruction;
        double avgRating;
        ArrayList<Ingredient> ingredients;

        recipe = null;
        userID = 0;
        title = EOF;
        instruction = EOF;
        avgRating = 0.0;
        ingredients = null;

        try
        {
            cmdString  = "Select * from Recipe where recipeID=" + recipeID;
            rs4 = st1.executeQuery(cmdString);
            rs4.next();
            recipeID = rs4.getInt("recipeID");
            userID = rs4.getInt("userID");
            title = rs4.getString("title");
            instruction = rs4.getString("instruction");
            avgRating = rs4.getDouble("avgRating");

            ingredients = new ArrayList<Ingredient>();
            findIngredientByRecipeID(ingredients, recipeID);
            recipe = new Recipe(recipeID, userID, title, instruction, ingredients, avgRating);
            rs4.close();
        }
        catch(Exception e)
        {
           processSQLError(e);
        }

        return recipe;
    }

    //Get review object by review ID
    public Review findReviewByID(int reviewID)
    {
        Review review;
        int recipeID, userID, rate;
        String content;

        review = null;
        recipeID = 0;
        userID = 0;
        rate = 0;
        content = EOF;

        try
        {
            cmdString  = "Select * from Review Where reviewID=" + reviewID;
            rs2 = st1.executeQuery(cmdString);

            rs2.next();
            reviewID = rs2.getInt("reviewID");
            recipeID = rs2.getInt("recipeID");
            userID = rs2.getInt("userID");
            rate = rs2.getInt("rate");
            content = rs2.getString("content");

            review = new Review(reviewID, recipeID, userID, rate, content);
            rs2.close();
        }
        catch(Exception e)
        {
            processSQLError(e);
        }

        return review;
    }

    //Get ingredient list related to the given recipe by recipe ID
    public String findIngredientByRecipeID(List<Ingredient> ingredients, int recipeID)
    {
        int ingredientID;
        String content;
        Ingredient ingredient;

        ingredientID = 0;
        content = EOF;
        result = null;

        try
        {
            cmdString = "Select * from Ingredient where recipeID=" + recipeID;
            rs3 = st2.executeQuery(cmdString);
            while(rs3.next())
            {

                ingredientID = rs3.getInt("ingredientID");
                content = rs3.getString("content");
                ingredient = new Ingredient(ingredientID, recipeID, content);
                ingredients.add(ingredient);
            }
            rs3.close();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    //Get user object by the given user ID
    public User findUserByID(int userID)
    {
        User user;
        String userName, userPassword;

        user = null;
        userName = EOF;
        userPassword = EOF;

        try
        {
            cmdString  = "Select * from User Where userID=" + userID;
            rs2 = st1.executeQuery(cmdString);

            rs2.next();
            userName = rs2.getString("userName");
            userPassword = rs2.getString("userPassword");

            user = new User(userID, userName, userPassword);
            rs2.close();
        }
        catch(Exception e)
        {
            processSQLError(e);
        }

        return user;
    }

    //Delete the user and all profiles realted to him by the given user ID
    public String deleteUserProfile(int userID)
    {
        result = null;
        try
        {
            cmdString = "Delete from User " + " Where userID=" + userID;
            updateCount = st3.executeUpdate(cmdString);
            result = checkWarning(st3, updateCount);
            reCalRateAll();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteReviewByID(int reviewID)
    {
        result = null;
        try
        {
            cmdString = "Delete from Review " + " Where reviewID=" + reviewID;
            updateCount = st3.executeUpdate(cmdString);
            result = checkWarning(st3, updateCount);
            reCalRateAll();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteRecipeByID(int recipeID)
    {
        result = null;
        try
        {
            cmdString = "Delete from Recipe " + " Where recipeID=" + recipeID;
            updateCount = st3.executeUpdate(cmdString);
            result = checkWarning(st3, updateCount);
            reCalRateAll();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String updateRecipe(int recipeID, String title, ArrayList<String> ingredients, String instructions) {
        String values;
        String where;

        result = null;
        try {
            updateIngredientByList(recipeID, ingredients);
            values = "TITLE='" + title
                    + "', INSTRUCTION='" + instructions
                    + "'";
            where = "where recipeID =" + recipeID;
            cmdString = "Update Recipe " + " Set " + values + " " + where;
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return result;
    }

    public String getRecipeByKeyword(List<Recipe> recipeList, String keyword)
    {
        Recipe recipe;
        int recipeID, userID;
        String title, instruction;
        double avgRating;
        ArrayList<Ingredient> ingredients;

        recipeID = 0;
        userID = 0;
        title = EOF;
        instruction = EOF;
        avgRating = 0.0;
        ingredients = null;

        result = null;
        try
        {
            cmdString  = "Select * from Recipe Where LOWER(Title) LIKE '%" + keyword.toLowerCase() + "%' OR LOWER(Instruction) LIKE '%" + keyword.toLowerCase() + "%'";
            rs2 = st1.executeQuery(cmdString);
            while(rs2.next())
            {
                recipeID = rs2.getInt("recipeID");
                userID = rs2.getInt("userID");
                title = rs2.getString("title");
                instruction = rs2.getString("instruction");
                avgRating = rs2.getDouble("avgRating");

                ingredients = new ArrayList<Ingredient>();
                findIngredientByRecipeID(ingredients, recipeID);
                recipe = new Recipe(recipeID, userID, title, instruction, ingredients, avgRating);
                recipeList.add(recipe);
            }
            rs2.close();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        e.printStackTrace();

        return result;
    }

    private String addNewIngredient(int recipeID, ArrayList<String> ingredients)
    {
        String values;
        int ingredientID;

        result = null;
        try
        {
            for(int i = 0; i < ingredients.size(); i++) {
                ingredientID = getNextIngredientID();
                values = ingredientID + "," + recipeID + ",'"
                        + ingredients.get(i) + "'";
                cmdString = "Insert into Ingredient " + " Values(" + values + ")";
                updateCount = st3.executeUpdate(cmdString);
                result = checkWarning(st3, updateCount);
            }
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    private void updateIngredientByList(int recipeID, ArrayList<String> ingredients)
    {
        deleteIngredientByRecipeID(recipeID);
        addNewIngredient(recipeID, ingredients);
    }

    private String deleteIngredientByRecipeID(int recipeID)
    {
        result = null;
        try
        {
            cmdString = "Delete from Ingredient " + " Where recipeID=" + recipeID;
            updateCount = st3.executeUpdate(cmdString);
            result = checkWarning(st3, updateCount);
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    private String reCalRateAll()
    {
        result = null;

        try
        {
            cmdString = "SELECT RECIPEID FROM REVIEW";
            rs4 = st2.executeQuery(cmdString);

            while (rs4.next()) {
                reCalRate( rs4.getInt("RECIPEID"));
            }
            rs4.close();
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    private String reCalRate(int recipeID)
    {
        int sum = 0;
        int count = 0;
        double average = 0.0;
        result = null;
        try
        {
            cmdString = "SELECT RATE FROM REVIEW WHERE RECIPEID=" + recipeID;
            rs5 = st1.executeQuery(cmdString);

            while(rs5.next())
            {
                sum += rs5.getInt("RATE");
                count++;
            }
            rs5.close();

            if(count > 0)
            {
                average = (double)sum/count;
                updateRecipeAvgRate(recipeID, average);
            }
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    private String updateRecipeAvgRate(int recipeID, double average)
    {
        result = null;
        try
        {
            cmdString = "UPDATE RECIPE SET AVGRATING= " + average + " WHERE RECIPEID=" + recipeID;
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch(Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    private int getNextRecipeID()
    {
        int nextID = 0;

        try
        {
            cmdString  = "Select max(recipeID) As recipeID from Recipe";

            rs5 = st3.executeQuery(cmdString);

            if(rs5.next())
                nextID = rs5.getInt("recipeID") + 1;

            rs5.close();
        }
        catch(Exception e)
        {
            processSQLError(e);
        }

        return nextID;
    }

    private int getNextReviewID()
    {
        int nextID = 0;

        try
        {
            cmdString  = "Select max(reviewID) as reviewID from Review";
            rs4 = st1.executeQuery(cmdString);
            if(rs4.next())
            {
                nextID = rs4.getInt("reviewID") + 1;
            }
            rs4.close();
        }
        catch(Exception e)
        {
            processSQLError(e);
        }
        return nextID;
    }

    private int getNextIngredientID()
    {
        int nextID = 0;

        try
        {
            cmdString  = "Select max(ingredientID) as ingredientID from Ingredient";

            rs5 = st3.executeQuery(cmdString);

            if(rs5.next())
                nextID = rs5.getInt("ingredientID") + 1;

            rs5.close();
        }
        catch(Exception e)
        {
            processSQLError(e);
        }

        return nextID;
    }

    private int getNextUserID()
    {
        int nextID = 0;

        try
        {
            cmdString  = "Select max(userID) as userID from User";

            rs5 = st3.executeQuery(cmdString);

            if(rs5.next())
                nextID = rs5.getInt("userID") + 1;

            rs5.close();
        }
        catch(Exception e)
        {
            processSQLError(e);
        }

        return nextID;
    }
}
