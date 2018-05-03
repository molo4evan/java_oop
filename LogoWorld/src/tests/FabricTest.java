package tests.Fabric;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.nsu.ccfit.molochev.task1.Fabric;
import ru.nsu.ccfit.molochev.task1.MyExcepts.ConfigException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.InitExistExcept.InitConfException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.MainException;

public class FabricTest extends Assert {
    Fabric f = Fabric.getInstance();

    @Before
    public void setUPp() throws ConfigException, InitConfException{
        f.configure("testConfig.properties");
    }

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCorrectConfig() throws ConfigException, InitConfException{
        f.configure("testConfig.properties");
    }

    @Test
    public void testNoFile() throws ConfigException, InitConfException{
        ex.expect(ConfigException.class);
        f.configure("ololo");
    }

    @Test
    public void testIncConf() throws ConfigException, InitConfException{
        ex.expect(ConfigException.class);
        f.configure("incConf.properties");
    }

    @Test
    public void testConfWithoutInit() throws ConfigException, InitConfException{
        ex.expect(InitConfException.class);
        f.configure("cwi.properties");
    }

    @Test
    public void correctGetWorker() throws MainException{
        Object w = f.getWorker("TEST");
        try {
            assertEquals(Class.forName("java.util.ArrayList"), w.getClass());
        }
        catch (ClassNotFoundException e){}
    }
}
