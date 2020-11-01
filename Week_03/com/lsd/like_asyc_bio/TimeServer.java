package Week_03.com.lsd.like_asyc_bio;

import Week_03.com.lsd.bio.TimerServerHandler;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-31 15:57
 * @Modified By：
 */
public class TimeServer {

    public static void main (String args[]) {


        ServerSocket serverSocket = null;
        try {

            serverSocket = new ServerSocket(8088);

            TimerServerHandlerThreadPool threadPool = new TimerServerHandlerThreadPool();

            System.out.println("Timer Server Started");

            while (true){

                Socket socket = serverSocket.accept();

                System.out.println("Timer Server Accepted");

                TimerServerHandler timerServerHandler = new TimerServerHandler(socket);
                threadPool.execute(timerServerHandler);
            }



        }catch (Exception e){

            e.printStackTrace();

        }finally {

            try {
                if(serverSocket != null && !serverSocket.isClosed()){
                    serverSocket.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }




    }
}
