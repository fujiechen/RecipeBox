package comp3350.recipebox.presentation;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comp3350.recipebox.business.*;
import comp3350.recipebox.objects.*;

public class CLI
{
    public static BufferedReader console;
    public static String inputLine;
    public static String[] inputTokens;

    public static void run()
    {
        try
        {
            console = new BufferedReader(new InputStreamReader(System.in));
            process();
            console.close();
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public static void process()
    {
        readLine();
        while ((inputLine != null) && (!inputLine.equalsIgnoreCase("exit"))
                && (!inputLine.equalsIgnoreCase("quit"))
                && (!inputLine.equalsIgnoreCase("q"))
                && (!inputLine.equalsIgnoreCase("bye")))
        {
            inputTokens = inputLine.split("\\s+");
            parse();
            readLine();
        }
    }

    public static void readLine()
    {
        try
        {
            System.out.print(">");
            inputLine = console.readLine();
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public static void parse()
    {
        if(inputTokens[0].equalsIgnoreCase("add"))
        {
            processAdd();
        }
        else if(inputTokens[0].equalsIgnoreCase("list"))
        {
            processRecipeList();
        }
        else
        {
            System.out.println("Invalid command.");
        }
    }


    public static void processAdd()
    {
        if (inputTokens[1].equalsIgnoreCase("Recipe"))
        {
            processAddRecipe();
        }
        else
        {
            System.out.println("Invalid data type");
        }
    }

    public static void processRecipeList()
    {
        List<Recipe> list;
        AccessRecipe accessRecipe = new AccessRecipe();

        list = accessRecipe.getRecipes();

        for(int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }

    public static void processAddRecipe()
    {
        String title;
        String instruction;
        String currIngredient;
        ArrayList<String> ingredient = new ArrayList<String>();
        AccessRecipe accessRecipe = new AccessRecipe();
        AccessUser accessUser = new AccessUser();

        System.out.println("Please enter the title");
        readLine();
        title = inputLine;

        System.out.println("Please enter the ingredients separated by line\nWhen you finished, enter \"end\" ");
        do{
            readLine();
            currIngredient = inputLine;
            ingredient.add(currIngredient);
        }while(inputLine.equalsIgnoreCase("end"));

        System.out.println("Please enter the instruction");
        readLine();
        instruction = inputLine;

        accessRecipe.addRecipe(title, ingredient, instruction, accessUser.getCurrentAccount());
    }

}
