package comp3350.recipebox.application;

public class Main
{
    public static final String dbName="RB";
    private static String dbPathName = "database/RB";

    //Start the app
    public static void main(String[] args)
    {
        startUp();

        shutDown();

        System.out.println("All done");
    }

    //Create Data Access
    public static void startUp()
    {
        Services.createDataAccess(dbName);
    }

    //Close Data Access
    public static void shutDown()
    {
        Services.closeDataAccess();
    }

    //Get the Database Path Name
    public static String getDBPathName()
    {
        if(dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    //Set the Database Path Name
    public static void setDBPathName(String pathName)
    {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }
}
