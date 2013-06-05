
package poker.ui;

import java.io.File;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class SettingsTest
{
    private Settings settings;
    
    @Before
    public void setUp() throws Exception
    {
        this.settings = new Settings();
    }
   
    @Test 
    public void fileContentCorrectAfterWritingToIt() throws Exception
    {
        settings.storeInt(4000);
        assertEquals(4000, settings.readInt());
    }
    
    @Test
    public void fileContentCorrectAfterWritingToItTwice() throws Exception
    {
        settings.storeInt(4000);
        settings.storeInt(10000);
        assertEquals(10000, settings.readInt());
    }    
    
    @After
    public void deleteFile()
    {
        File file = new File("settings.dat");
        file.delete();
    }
}
