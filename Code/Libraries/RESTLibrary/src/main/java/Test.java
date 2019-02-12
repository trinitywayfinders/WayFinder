import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String [] args) throws IOException {
        
        String url = "https://postman-echo.com/get?foo1=bar1&foo2=bar2";
 
        List<List<String>> headers = new ArrayList<List<String>>();
        List<String> header1 = new ArrayList<>(Arrays.asList("header1", "val1"));
        List<String> header2 = new ArrayList<>(Arrays.asList("header2", "val2"));
        List<String> header3 = new ArrayList<>(Arrays.asList("header3", "val3"));

        headers.add(header1);
        headers.add(header2);
        headers.add(header3);
        
        String response = Library.GET(url, headers);
        
        System.out.println("!!!\n"+response);
    }
}
