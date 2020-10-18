package Week_01.学习总结.字节码技术;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-17 15:06
 * @Modified By：
 */
public class HelloForAddIF {

    public static void main(String[] args){
        Integer a = 100;
        Integer b = 200;
        Integer c = a + b;

        Integer d = 0;

        for (int i = 0 ; i < c; i++) {
            if(i == 2){
                return;
            }
            d = (c * i);
            System.out.println(d);
        }
    }
}
