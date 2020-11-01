package Week_03.com.lsd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-01 13:10
 * @Modified By：
 */
public class MutiplexerTimerServer implements Runnable {

    private Selector selector;
    private ServerSocketChannel servChannel;

    public MutiplexerTimerServer() {


        try {

            selector = Selector.open();

            servChannel = ServerSocketChannel.open();
            servChannel.configureBlocking(false);
            servChannel.socket().bind(new InetSocketAddress(8088), 1024);
            servChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("timer server is started in port 8088");

        }catch (Exception e){

            e.printStackTrace();



        }

    }

    @Override
    public void run() {
        while (true){

            try {

                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator it =  selectionKeys.iterator();

                while (it.hasNext()) {

                    SelectionKey selectionKey = (SelectionKey)it.next();

                    it.remove();

                    service(selectionKey);


                }

            }catch (IOException e){

                e.printStackTrace();

            }


        }
    }

    private void service( SelectionKey selectionKey ){

        try {

            if ( selectionKey.isValid() ) {

                if ( selectionKey.isAcceptable() ) {

                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();

                    SocketChannel socketChannel = serverSocketChannel.accept();

                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ );
                }

                if(selectionKey.isReadable()){


                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();


                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                    int readBytes = socketChannel.read(readBuffer);
                    if (readBytes > 0) {

                        readBuffer.flip();

                        byte[] bytes = new byte[readBuffer.remaining()];

                        readBuffer.get(bytes);

                        String body = new String(bytes, "UTF-8");

                        System.out.println("timer server received body : " + body);

                        String message = new String("Hello Big Bang");
                        int length = message.getBytes().length;

                        StringBuffer sb = new StringBuffer();
                        sb.append("HTTP/1.1 200 OK\r\n");
                        sb.append("Content-Type:text/html;charset=UTF-8\r\n");
                        sb.append("Content-length:" + length + "\r\n\r\n");
                        sb.append(message);

                        ByteBuffer buf = ByteBuffer.wrap(sb.toString().getBytes(StandardCharsets.UTF_8));


                        socketChannel.write(buf);

                        System.out.println("socket id:"+ socketChannel.hashCode()+" done");

                        socketChannel.close();

                    }else if (readBytes < 0 ) {

                        selectionKey.cancel();
                        socketChannel.close();
                    }else{
                        //continue
                        System.out.println("忽略");
                    }

                }

            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
