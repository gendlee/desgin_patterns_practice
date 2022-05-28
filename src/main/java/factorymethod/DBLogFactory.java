package factorymethod;

public class DBLogFactory extends LogFactory{
    @Override
    public Log createLog() {
        System.out.println("create DB Log Factory");
        return new DBLog();
    }
}
