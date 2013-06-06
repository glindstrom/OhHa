package poker.ui;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

/**
 * Stores/retrieves the number of trials to/from file.
 *
 */
public class Settings
{

    /**
     * The file where the settings are stored.
     */
    private File file;
    /**
     * The file handler which performs I/O operations.
     */
    private FileHandler fileHandler;

    /**
     * Class constructor.
     *
     * @throws IOException if an I/O error occurs.
     */
    public Settings() throws Exception
    {
        this.file = new File("settings.dat");
        if (!this.file.exists())
        {
            this.file.createNewFile();
        }
        this.fileHandler = new FileHandler();
    }

    /**
     * Writes an int to file.
     *
     * @param i the int to be written
     * @throws IOException if an I/O error occurs.
     */
    public void storeTrials(int i) throws Exception
    {
        fileHandler.storeInt(file, i);
    }

    /**
     * Reads an int from file.
     *
     * @return the stored int
     * @throws EOFException if the file is empty.
     * @throws IOException if the stream has been closed or another I/O error
     * occurs.
     */
    public int readTrials() throws Exception
    {
        return fileHandler.readInt(file);
    }
}
