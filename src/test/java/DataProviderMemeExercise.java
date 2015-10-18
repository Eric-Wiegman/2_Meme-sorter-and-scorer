import org.testng.annotations.DataProvider;

/**
 * The DataProvider, which passes a list of list of Objects to the @Test with
 * the corresponding dataprovider attribute to be used as parameters to that
 * test method. Essentially a form of parameterization.
 */
public class DataProviderMemeExercise {

    /**
     * Meme exercise object [ ] [ ] -- the DataProvider that is used in the
     * "Meme Exercise" test to perform the parameterization.
     *
     * @return the object [ ] [ ] The data (passed via TestNG to the test).
     */
    @DataProvider

    public static Object[][] memeExercise() {
        return new Object[][]{
                {"internetmemes_backup.json",
                        "internetmemes.json",
                        "internetmemes_output.json"}
        };
    }
}
