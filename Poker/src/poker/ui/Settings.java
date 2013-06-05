

package poker.ui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Reads/writes an integer from/to a file.
 * 
 */
public class Settings 
{
    /**
     * The file where the settings are stored.
     */
    File file;

    /**
     * Class constructor.
     * @throws IOException if an I/O error occurs.
     */
    public Settings() throws Exception
    {
        this.file = new File("settings.dat");
        if (!this.file.exists())
        {
            this.file.createNewFile();
        }        
    }    
    
    /**
     * Writes an int to file.
     * @param i the int to be written
     * @throws IOException if an I/O error occurs. 
     */
    public void storeInt(int i) throws Exception
    {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file)))
        {
            out.writeInt(i);
        }
    }
    
    /**
     * Reads an int from file.
     * @return the stored int
     * @throws EOFException if the file is empty.
     * @throws IOException if the stream has been closed or another I/O error occurs.
     */
    public int readInt() throws Exception
    {
        int i;
        try (DataInputStream in = new DataInputStream(new FileInputStream(file)))
        {
            i = in.readInt();
        }
        return i;
    }

}
