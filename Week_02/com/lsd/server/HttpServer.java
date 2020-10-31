package Week_02.com.lsd.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-28 20:12
 * @Modified By：
 */
public class HttpServer {

    private static ExecutorService  fixedThreadPool = Executors. newFixedThreadPool(40);

    public static void main(String args[]){

        try {


            ServerSocket serverSocket = new ServerSocket(8081);

            System.out.println("服务端已启动，端口号:8080");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("服务端收到请求");
                fixedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            service(socket);
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }
                });
            }

        }catch (IOException e){

            e.printStackTrace();
        }


    }

    private static void service( Socket socket) throws IOException{

        PrintWriter printWriter = null;
        try {

            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html");
            printWriter.println();
            printWriter.write("Hello Big Ban");
            printWriter.flush();

        }catch (IOException e){
            throw e;
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
