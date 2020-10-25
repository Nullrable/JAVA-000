package Week_02.com.lsd.gc;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-25 14:23
 * @Modified By：
 */
public class TestDirectToOldEden {

    private static final int _1MB = 1024 * 1024;

    /*** VM参数：-verbose:gc -Xms512M -Xmx512M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 */

    public static void main(String[] args) {

        byte[] allocation = new byte[7 * _1MB]; //直接分配在老年代中

        for (MemoryPoolMXBean memoryPoolMXBean : ManagementFactory.getMemoryPoolMXBeans()) {

            System.out.println(memoryPoolMXBean.getName() + "  总量:" + memoryPoolMXBean.getUsage().getCommitted()  / 1024 / 1024 + "M" + "   使用的内存:" + memoryPoolMXBean.getUsage().getUsed() / 1024 / 1024 + "M");
        }


    }
}
