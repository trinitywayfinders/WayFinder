import java.io.IOException;

public class Test {
    public static void main(String [] args) throws IOException {
        
        String url = "https://postman-echo.com/get?foo1=bar1&foo2=bar2";
        String response = Library.GET(url, null);
        
        System.out.println("!!!\n"+response);
    }
}
