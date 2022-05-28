package factorymethod;

import org.junit.Test;

public class TestEntrance {

    @Test
    public void testFileLog() {
        LogFactory logFactory = new FileLogFactory();
        Log log = logFactory.createLog();

        log.writeLog();

    }

    @Test
    public void testDBLog() {
        LogFactory logFactory = new DBLogFactory();
        Log log = logFactory.createLog();

        log.writeLog();
    }
}
