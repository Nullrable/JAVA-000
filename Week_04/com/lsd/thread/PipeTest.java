package Week_04.com.lsd.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-15 15:01
 * @Modified By：
 */
public class PipeTest {

    public static void main (String[] args)throws Exception {


        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();

        writer.connect(reader);

        Thread t = new Thread(new Print(reader));

        t.start();


        int receive = 0;


        InputStream inputStream =  System.in;


        try {

            while ((receive = inputStream.read()) != -1) {
                writer.write(receive);
            }

        } catch (IOException ex) {

            ex.printStackTrace();

        }




    }

}

class Print implements Runnable {


    private PipedReader pipedReader;

    public Print(PipedReader pipedReader) {
        this.pipedReader = pipedReader;
    }

    @Override
    public void run() {

        int receive = 0;
        try {

                while ((receive = pipedReader.read()) != -1) {
                    System.out.print((char) receive);
                }

            } catch (IOException ex) {

                ex.printStackTrace();

        }
    }
}
