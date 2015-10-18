import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Class that does unit testing for the "meme exercise".
 */
public class TestMemeExercise {

    /**
     * The test that validates the modified/rewritten JSON output file
     * (overwrites the original file).<br><br>The <code>doAssertions</code>
     * method
     * actually performs the unit validations and returns true or false,
     * which is then used by this method to assert if the test passes or not.
     *
     * @param backup   the backup file, used to restore the original file
     *                 (which will be overwritten).
     * @param original the original file, the list of internet memes.
     * @param output   the output file, the expected file (alphabetizes the
     *                 memes and adds a lulz factor from 1 to 10 to each
     *                 meme) that is to be compared to the newly overwritten
     *                 original file ... to ensure the code worked correctly.
     */
    @Test(
            dataProvider = "memeExercise",
            dataProviderClass = DataProviderMemeExercise.class
    )
    public void checkOutput(String backup, String original, String output) {
        String[] argz = {backup, original, output};

        try {
            InternetMeme.main(argz);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        boolean ok = false;
        try {
            ok = doAssertions(original, output);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(ok);

    }

    /**
     * This performs the unit validations and returns true or false,
     * which is then used by the <code>checkOutput</code> method
     * assert if the test passes or not.<br><br>Additionally, any invalid
     * entry (due to alphabetization errors or missing memes OR having a lulz
     * value that is not within the defined range) is noted in a message
     * written to standard out (console).
    *
     * @param original the original file, the list of internet memes.
     * @param output   the output file, the expected file (alphabetizes the
     *                 memes and adds a lulz factor from 1 to 10 to each
     *                 meme) that is to be compared to the newly overwritten
     *                 original file ... to ensure the code worked correctly.
     *
     * @return isOK -- If comparisons are as expected, then return is true --
     * otherwise false.
     * @throws IOException Occurs if file is not found, etc.
     * @throws ParseException Occurs if JSON file is malformed and cannot be
     * parsed.
     */
    private boolean doAssertions(
            String original,
            String output)
            throws IOException, ParseException {

        boolean isOK = true;
        ArrayList<String> msgs = new ArrayList<String>();
        msgs.clear();

        FileReader modifiedOriginalReader = new FileReader(Consts.FILEPATH
                + original);
        JSONParser jsonParser0 = new JSONParser();
        JSONArray parse0 = (JSONArray) jsonParser0.parse
                (modifiedOriginalReader);

        FileReader expectedReader = new FileReader(Consts.FILEPATH + output);
        JSONParser jsonParser1 = new JSONParser();
        JSONArray parse1 = (JSONArray) jsonParser1.parse(expectedReader);

        int expectedSize = parse1.size();

        if (parse0.size() != expectedSize) {
            msgs.add("Expected & actual JSON files are of different size.");
            isOK = false;
        } else {
            //iterate through both and figure it out
            for (int i = 0; i <= expectedSize - 1; i++) {
                String s0 = parse0.get(i).toString();
                String s1 = parse1.get(i).toString();

                String modifiedOrigMeme =
                        s0.split(",")[0].replace("\"", "").replace("{meme:", "");

                int modifiedOrigLulz = Integer.parseInt(
                        s0.split(",")[1].replace("\"", "").replace("lulz:", "")
                                .replace("}", ""));

                String expectedMeme =
                        s1.split(",")[0].replace("\"", "").replace("{meme:", "");

                int expectedLulz = Integer.parseInt(
                        s1.split(",")[1].replace("\"", "").replace("lulz:", "")
                                .replace("}", ""));

                if (!modifiedOrigMeme.equals(expectedMeme)) {
                    msgs.add(modifiedOrigMeme + " is not the same as "
                            + expectedMeme + ", for index " + i);
                    isOK = false;
                }

                if ((modifiedOrigLulz > 10) || (modifiedOrigLulz < 0)) {
                    msgs.add("The modified/added Lulz value is out of range: '"
                            + modifiedOrigLulz + "' for index: " + i);
                    isOK = false;
                }
            }
        }

        for (String message : msgs) {
            System.out.println(message);
        }

        return isOK;
    }
}
