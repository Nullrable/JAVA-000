Classfile /Users/lusudong/Documents/github-work/JAVA-000/classes/production/JAVA-000/Week_01/学习总结/字节码技术/HelloTest.class
  Last modified 2020-10-17; size 430 bytes
  MD5 checksum 5fde159c1631bab989b98ff91b3032d7
  Compiled from "HelloTest.java"
public class Week_01.学习总结.字节码技术.HelloAdd
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #5.#14         // java/lang/Object."<init>":()V
   #2 = Methodref          #15.#16        // java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
   #3 = Methodref          #15.#17        // java/lang/Integer.intValue:()I
   #4 = Class              #18            // Week_01/学习总结/字节码技术/HelloTest
   #5 = Class              #19            // java/lang/Object
   #6 = Utf8               <init>
   #7 = Utf8               ()V
   #8 = Utf8               Code
   #9 = Utf8               LineNumberTable
  #10 = Utf8               main
  #11 = Utf8               ([Ljava/lang/String;)V
  #12 = Utf8               SourceFile
  #13 = Utf8               HelloTest.java
  #14 = NameAndType        #6:#7          // "<init>":()V
  #15 = Class              #20            // java/lang/Integer
  #16 = NameAndType        #21:#22        // valueOf:(I)Ljava/lang/Integer;
  #17 = NameAndType        #23:#24        // intValue:()I
  #18 = Utf8               Week_01/学习总结/字节码技术/HelloTest
  #19 = Utf8               java/lang/Object
  #20 = Utf8               java/lang/Integer
  #21 = Utf8               valueOf
  #22 = Utf8               (I)Ljava/lang/Integer;
  #23 = Utf8               intValue
  #24 = Utf8               ()I
{
  public Week_01.学习总结.字节码技术.HelloAdd();
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
      stack=2, locals=4, args_size=1
        
         //常量值1，放入栈顶
         0: iconst_1
         
         // 调用Integer.valueOf方法，返回 Integer 对象，原常量1出栈，Integer 1 放入栈顶
         1: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
         
         // Integer 1 出栈， 存入本地变量表 第1个 slot
         4: astore_1
         
         //常量值2，放入栈顶
         5: iconst_2
        
        // 调用Integer.valueOf方法，返回 Integer 对象，原常量2出栈，Integer 2 放入栈顶
         6: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       
       // Integer 2 出栈， 存入本地变量表 第2个 slot
         9: astore_2
        
        // slot1中 Integer 1 放入栈顶
        10: aload_1
       
       // 调用Integer.intValue方法，返回基本数据类型 1，原对象 Integer 1 出栈，基本数据类型 1 放入栈顶
        11: invokevirtual #3                  // Method java/lang/Integer.intValue:()I
        
        // slot2中 Integer 2 放入栈顶，所以stack栈深为2
        14: aload_2
      
        // 调用Integer.intValue方法，返回基本数据类型 2，原对象 Integer 2 出栈，基本数据类型 2 放入栈顶
        15: invokevirtual #3                  // Method java/lang/Integer.intValue:()I
        
        // 将栈顶中的，1，2进行相加 返回数据 3。1，2 出栈，3放入栈顶
        18: iadd
       
        // 调用Integer.valueOf方法，返回 Integer 对象，原int 3 出栈，Integer 3 放入栈顶
        19: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        
        //将栈顶 Integer 3 出栈，存到 第3个solt，所以locals值为4，slot从0开始计数
        22: astore_3
        
        //方法结束
        23: return
      LineNumberTable:
        line 12: 0
        line 13: 5
        line 14: 10
        line 15: 23
}
SourceFile: "HelloTest.java"
