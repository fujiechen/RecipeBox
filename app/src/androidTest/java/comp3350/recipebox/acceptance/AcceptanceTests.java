package comp3350.recipebox.acceptance;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AcceptanceTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance Tests");
        suite.addTestSuite(RecipeTest.class);
        suite.addTestSuite(ReviewTest.class);
        suite.addTestSuite(UserTest.class);

        return suite;
    }
}