package Week_01.work.lsd.cassloader;

import java.io.*;
import java.lang.reflect.Method;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-17 16:35
 * @Modified By：
 */
public class HelloClassLoader extends ClassLoader {

    private static final String XLASS_PATH = "Week_01/work/lsd/cassloader/resouce/";


    public static void main(String args[]){

        try {

            Class clazz = new HelloClassLoader().findClass("Hello");

            Object object = clazz.newInstance();

            Method method = clazz.getMethod("hello");

            method.invoke(object);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        String xlassFile =  XLASS_PATH + name + ".xlass";

        byte[] encodeByte = getBytesByFile(xlassFile);

        byte[] decodeByte = decode(encodeByte);

        return defineClass(name, decodeByte, 0, decodeByte.length);
    }


    private byte[] decode(byte[] source) {

        for (int i = 0; i < source.length; i++) {
            source[i] = intToByte(255 - byteToInt(source[i]));
        }

        return source;
    }

    //将文件转换成Byte数组
    private byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * byte转int类型
     * 如果byte是负数，则转出的int型是正数
     *
     * @param b
     * @return
     */
    private int byteToInt(byte b) {
        int x = b & 0xff;
        return x;
    }

    /**
     * int 类型转换为byte 类型
     * 截取int类型的最后8位,与 0xff
     *
     * @param x
     * @return
     */
    private byte intToByte(int x) {
        byte b = (byte) (x & 0xff);
        return b;

    }
}
