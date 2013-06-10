package poker.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * An instance of FileHandler reads/writes from/to a file.
 *
 */
public class FileHandler
{

    /**
     * Writes an int to the specified file.
     *
     * @param file the file which to write to
     * @param i the int to store
     * @throws IOException if an I/O error occurs.
     */
    public void storeInt(File file, int i) throws Exception
    {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file)))
        {
            out.writeInt(i);
        }
    }

    /**
     * Reads an int from the specified file.
     * @param file the file to read from
     * @return an int
     * @throws EOFException if the file is empty.
     * @throws IOException if the stream has been closed or another I/O error occurs.
     */
    public int readInt(File file) throws Exception
    {
        int i;
        try (DataInputStream in = new DataInputStream(new FileInputStream(file)))
        {
            i = in.readInt();
        }
        return i;
    }
}
