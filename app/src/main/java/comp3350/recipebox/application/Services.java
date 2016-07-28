package comp3350.recipebox.application;

import comp3350.recipebox.persistence.*;

public class Services
{
    private static DataAccess dataAccessService = null;

    //Create Data Access
    public static DataAccess createDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            dataAccessService = new DataAccessObject(dbName);
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    //Create Data Access
    public static DataAccess createDataAccess(DataAccess alternateDataAccessService)
    {
        if (dataAccessService == null)
        {
            dataAccessService = alternateDataAccessService;
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    //Get Data Access
    public static DataAccess getDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }
    
    //Close Data Access
    public static void closeDataAccess()
    {
        if (dataAccessService != null)
        {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
