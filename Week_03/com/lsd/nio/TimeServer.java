package Week_03.com.lsd.nio;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-31 15:57
 * @Modified By：
 */
public class TimeServer {

    public static void main (String args[]) {

        MutiplexerTimerServer server = new MutiplexerTimerServer();

        new Thread(server).start();

    }
}
