package Week_02.com.lsd.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-28 20:35
 * @Modified By：
 */
public class WebClient {

    public static void main(String args[]){
        InputStreamReader reader = null;
        try {

            URL url = new URL("http://localhost:8081");

            URLConnection urlConnection = url.openConnection();

            reader = new InputStreamReader(urlConnection.getInputStream());

            char []cha = new char[1024];
            int len = reader.read(cha);
            System.out.println(new String(cha,0, len));
            reader.close();

        }catch (IOException e){

        }finally {
            try {
                if (reader != null) {
                    reader.close();
                }

            }catch (Exception e){

            }

        }


    }
}
