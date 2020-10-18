Classfile /Users/lusudong/Documents/github-work/JAVA-000/classes/production/JAVA-000/Week_01/学习总结/字节码技术/HelloForAddIF.class
  Last modified 2020-10-17; size 587 bytes
  MD5 checksum 0feb8971a2b5586bcffcd5f644cfe7b5
  Compiled from "HelloForAddIF.java"
public class Week_01.学习总结.字节码技术.HelloForAddIF
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #5.#17         // java/lang/Object."<init>":()V
   #2 = Methodref          #18.#19        // java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
   #3 = Methodref          #18.#20        // java/lang/Integer.intValue:()I
   #4 = Class              #21            // Week_01/学习总结/字节码技术/HelloForAddIF
   #5 = Class              #22            // java/lang/Object
   #6 = Utf8               <init>
   #7 = Utf8               ()V
   #8 = Utf8               Code
   #9 = Utf8               LineNumberTable
  #10 = Utf8               main
  #11 = Utf8               ([Ljava/lang/String;)V
  #12 = Utf8               StackMapTable
  #13 = Class              #23            // "[Ljava/lang/String;"
  #14 = Class              #24            // java/lang/Integer
  #15 = Utf8               SourceFile
  #16 = Utf8               HelloForAddIF.java
  #17 = NameAndType        #6:#7          // "<init>":()V
  #18 = Class              #24            // java/lang/Integer
  #19 = NameAndType        #25:#26        // valueOf:(I)Ljava/lang/Integer;
  #20 = NameAndType        #27:#28        // intValue:()I
  #21 = Utf8               Week_01/学习总结/字节码技术/HelloForAddIF
  #22 = Utf8               java/lang/Object
  #23 = Utf8               [Ljava/lang/String;
  #24 = Utf8               java/lang/Integer
  #25 = Utf8               valueOf
  #26 = Utf8               (I)Ljava/lang/Integer;
  #27 = Utf8               intValue
  #28 = Utf8               ()I
{
  public Week_01.学习总结.字节码技术.HelloForAddIF();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 9: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=6, args_size=1
      
         // byte型常量 100 放入栈顶
         0: bipush        100
         
         // 调用Integer.valueOf 转成Integer类型
         2: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        
         // Integer 100 存入本地变量表 slot1
         5: astore_1
        
         // short型常量 200 放入栈顶
         6: sipush        200
         
         // 调用Integer.valueOf 转成Integer类型
         9: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        
         // Integer 200 存入本地变量表 slot2
        12: astore_2
        
        // slot1对象放入栈顶
        13: aload_1
        
        // 调用 Integer.intValue 转为 int 100
        14: invokevirtual #3                  // Method java/lang/Integer.intValue:()I
        
        // slot2对象放入栈顶
        17: aload_2
        
         // 调用 Integer.intValue 转为 int 200
        18: invokevirtual #3                  // Method java/lang/Integer.intValue:()I
       
        //  相加返回300，int 100，200销毁， int 300 放入栈顶
        21: iadd
        
        // 调用Integer.valueOf 转成Integer类型 Integer 300
        22: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       
        // Integer 300 放入slot3
        25: astore_3
       
        // 常量 int 0，放入栈顶（Integer d = 0;）
        26: iconst_0
       
        // 调用Integer.valueOf 转成Integer类型 Integer 0
        27: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        
        //  Integer 0 放入本地变量表 slot4
        30: astore        4
        
        // 常量 int 0，放入栈顶（（for循环的i值）
        32: iconst_0
       
        //  int 0 放入本地变量表 slot5 （for循环的i值）
        33: istore        5
        
        // slot5数据 int 0，放到栈顶
        35: iload         5
       
        // slot3数据 Integer 300，放到栈顶
        37: aload_3
       
        // 栈顶 Integer 300 执行 Integer.intValue，变为 int 300
        38: invokevirtual #3                  // Method java/lang/Integer.intValue:()I
        
        // 比较栈顶两int型数值大小（300， 0），当结果大于等于0时跳转，(0 - 300) < 0 不成立，执行下一条；注意 if_icmpge 是 第二个值 跟 第一个值 比较，这里是 第二个值 >= 第一个值时，跳转，否则执行下一行
        41: if_icmpge     69
       
        //加载 slot5， int 0 放入栈顶if_icmpge
        44: iload         5
        
        // 常量 int 2，放入栈顶
        46: iconst_2
        
        // 比较栈顶两int型数值大小，当结果不等于0时跳转，这里 (0-2) != 0 成立跳转到 51序号继续执行
        47: if_icmpne     51
       
        // 方法结束
        50: return
        
        // 加载slot3，Integer 300 放入栈顶
        51: aload_3
       
       // 栈顶数据 执行 Integer.intValue，int 300
        52: invokevirtual #3                  // Method java/lang/Integer.intValue:()I
      
        // 加载slot5，int 0 放入栈顶
        55: iload         5
       
        // 栈顶2项数据相乘，结果为0，入栈
        57: imul
        
        // Integer.valueOf 方法 int 0
        58: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        
        // int 0 存入到 slot4 （d的值）
        61: astore        4
        
        // slot5（for循环的i值）进行加 1
        63: iinc          5, 1
        
        // 重新执行35序号的运算
        66: goto          35
        
        //方法结束
        69: return
      LineNumberTable:
        line 12: 0
        line 13: 6
        line 14: 13
        line 16: 26
        line 18: 32
        line 19: 44
        line 20: 50
        line 22: 51
        line 18: 63
        line 24: 69
      StackMapTable: number_of_entries = 3
        frame_type = 255 /* full_frame */
          offset_delta = 35
          locals = [ class "[Ljava/lang/String;", class java/lang/Integer, class java/lang/Integer, class java/lang/Integer, class java/lang/Integer, int ]
          stack = []
        frame_type = 15 /* same */
        frame_type = 250 /* chop */
          offset_delta = 17
}
SourceFile: "HelloForAddIF.java"