package Week_03.com.lsd.like_asyc_bio;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-31 15:58
 * @Modified By：
 */
public class TimerServerHandler implements Runnable {

    private Socket socket;

    public TimerServerHandler(Socket socket) {

        if(socket == null){
            new NullPointerException();
        }
        this.socket = socket;
    }

    @Override
    public void run() {


        service(socket);

    }

    private  void service( Socket socket){

        PrintWriter printWriter = null;
        try {

            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));


            String message = new String("Hello Big Bang");
            int length = message.getBytes().length;

            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html");
            printWriter.println("Content-length:" + length);
            printWriter.println();
            printWriter.write(message);
            printWriter.flush();

        }catch (IOException e){
           e.printStackTrace();
        }finally {
            try {
                if(printWriter != null){
                    printWriter.close();
                }

                if(socket != null && !socket.isClosed()){
                    socket.close();
                }
            }catch (Exception e){
                e.printStackTrace();

            }

        }
    }
}
