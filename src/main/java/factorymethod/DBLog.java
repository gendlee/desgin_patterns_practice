package factorymethod;

public class DBLog extends Log {
    @Override
    public void writeLog() {
        System.out.println("DB Log is writing...");
    }
}
