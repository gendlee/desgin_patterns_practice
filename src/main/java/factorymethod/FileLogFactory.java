package factorymethod;

public class FileLogFactory extends LogFactory{
    @Override
    public Log createLog() {
        System.out.println("create File Log Factory");
        return new FileLog();
    }
}
