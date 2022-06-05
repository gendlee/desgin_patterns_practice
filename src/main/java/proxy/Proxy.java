package proxy;

public class Proxy extends Subject {
    @Override
    public void request() {
        preRequest();
        super.request();
        afterRequest();
    }

    private void preRequest() {
        System.out.println("preRequest：发送请求前先处理一下");
    }

    private void afterRequest() {
        System.out.println("afterRequest: 请求完再处理一下");
    }
}
