import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * The Class InternetMeme, which does the following.
 * <br>
 * <br> <br>Exercise 2 - Meme sorter and scorer
 * <br>
 * <br>I. Read in a list of internet memes from a json file on the classpath.
 * <br> II. Calls a method, which takes the list of memes and sorts them by name.
 * <br>III. Calls a second method, which:
 * <ol>
 * <li> associates a "lulz" score (from 1-10) with each meme
 * <li> writes the updated values to the same json file.
 * </ol>
 */
public class InternetMeme {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ParseException the parse exception
     */
    public static void main(String[] args) throws ParseException {
        String backup = args[0];
        String input = args[1];
        String output = args[2];


        String jsonListName = "memes";
        restoreJsonFileFromBackup(backup, input);
        JSONArray sortedMemes = sortMemeList(
                Consts.FILEPATH + input,
                jsonListName);
        addLulzAndUpdate(Consts.FILEPATH + input, sortedMemes);
    }

    /**
     * Restore json file from backup.
     *
     * @param backupFileName the backup file name
     * @param inputFileName the current (one to be used) file
     */
    private static void restoreJsonFileFromBackup(
            final String backupFileName,
            final String inputFileName) {

        Path source = Paths.get(Consts.FILEPATH + backupFileName);
        Path target = Paths.get(Consts.FILEPATH + inputFileName);

        //copy backup to start file, which will be changed
        try {
            System.out.println("Setup. Copy backup to be original file: "
                    + Files.copy(source, target, StandardCopyOption
                    .REPLACE_EXISTING));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sort meme list.
     *
     * @param fullFileName the full file name that is to be parsed into the
     *                     JSON Object
     * @param jsonListName the json list name that is converted to a JSON Array
     * @return the jSON array: the sorted JSON Array
     * @throws ParseException the parse exception that may occur if the file
     *                        (to be made into a JSON Object) is not properly formed
     */
    private static JSONArray sortMemeList(
            final String fullFileName,
            final String jsonListName)
            throws ParseException {

        JSONArray memes = null;
        JSONArray sortedMemes = new JSONArray();

        try {
            FileReader reader = new FileReader(fullFileName);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            memes = (JSONArray) jsonObject.get(jsonListName);

            ArrayList<String> list = new ArrayList<String>();

            for (String meme : (Iterable<String>) memes) {
                list.add(meme.toString());
            }

            Collections.sort(list);

            for (String element : list) {
                sortedMemes.add(element);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sortedMemes;
    }

    /**
     * Add lulz and update.
     *
     * @param fileFullName the file full name that is to be parsed into the
     *                     JSON Object
     * @param sortedMemes  the sorted memes - the sorted JSON Array, returned by
     *                     the <code>sortMeme</code> method
     */
    private static void addLulzAndUpdate(
            final String fileFullName,
            final JSONArray sortedMemes) {

        //lulz is just rand int from 1 to 10 inclusive
        int upperbound = 10;
        int lowerbound = 1;
        int lulz = 5;

        JSONArray arr = new JSONArray();
        HashMap<String, JSONObject> map = new HashMap<String, JSONObject>();
        for (int i = 0; i < 10; i++) {
            lulz = (int) (Math.random() * ((upperbound - lowerbound) + 1)
                    + lowerbound);
            JSONObject json = new JSONObject();
            json.put("meme", sortedMemes.get(i));
            json.put("lulz", lulz);
            map.put("json" + i, json);
            arr.add(map.get("json" + i));
        }
        System.out.println("The json string is now " + arr.toString());

        FileWriter file = null;

        try {
            file = new FileWriter(fileFullName);
            file.write(arr.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
