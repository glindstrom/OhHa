

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
    File file;

    public Settings() throws Exception
    {
        this.file = new File("settings.dat");
        if (!this.file.exists())
        {
            this.file.createNewFile();
        }        
    }    
    
    public void storeInt(int i) throws Exception
    {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file)))
        {
            out.writeInt(i);
        }
    }
    
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
