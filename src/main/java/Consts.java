/**
 * The Constants.
 */
public class Consts {

    /**
     * The constant FILE_SEP, which removes ambiguity whether to type slash or
     * backslash.
     */
    public static final String FILE_SEP = "/";
    /**
     * The constant USER_HOME, which Java uses to tell us the project root.
     */
    public static final String USER_HOME = System.getProperty("user.home");
    /**
     * The constant RESOURCES_FOLDER, which is relative path to resources
     * folder.
     */
    public static final String RESOURCES_FOLDER =
            "src" + FILE_SEP + "main" + FILE_SEP + "resources";
    /**
     * The constant INTERNET_MEME_JSON_FILE, which is the JSON file to parse
     * in this exercise.
     */
    public static final String INTERNET_MEME_JSON_FILE =
            "internetmemes.json";
    /**
     * The constant INTERNET_MEME_JSON_BACKUP_FILE, which is the backup JSON
     * file used to restore the JSON file (to be parsed).
     */
    public static final String INTERNET_MEME_JSON_BACKUP_FILE =
            "internetmemes_backup.json";
    /**
     * The constant FILEPATH, which is the path to the backup or original file.
     */
    public static final String FILEPATH = RESOURCES_FOLDER + FILE_SEP;
}
