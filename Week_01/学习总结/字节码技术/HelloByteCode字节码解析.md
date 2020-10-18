```
Classfile /Users/lusudong/Documents/github-work/JAVA-000/classes/production/JAVA-000/Week_01/HelloByteCode.class
  Last modified 2020-10-17; size 296 bytes      //最后修改时间； 该字节码文件大小
  MD5 checksum 6795a8635dded859b496922a19b61440 //该文件的md5
  Compiled from "HelloByteCode.java"            //表示该字节码文件是由“HelloByteCode.java”编译而来
public class Week_01.学习总结.HelloByteCode
  minor version: 0                              //表示可以支持最小的jdk版本，java的jdk都是向下兼容的，所以该值一般都为0
  major version: 52                             //编译HelloByteCode.java的jdk版本，我使用的是jdk1.8,所以52代表的是jdk8
  flags: ACC_PUBLIC, ACC_SUPER                  //表示该类的访问属性 ACC_PUBLIC：是否为Public类型 ACC_SUPER：是否允许使用invokespecial字节码指令的新语义．
Constant pool:                                  //常量池，可以理解为java资源的仓库，JVM运行方法时需要用到的数据都会在这里拿

   // #1 表示常量池值的序号，该数字表示为第一个；
   // Methodref 表示第一个值是一个方法的引用
   // #4.#13 =》 #4表示指向 第4个（#4 = Class #15） ，后有指向低15个（java/lang/Object）java/lang/Object; 然后是个 . 符号； 然后 #13 同理指向"<init>":()V； 三个合并起来就是java/lang/Object."<init>":()V
   #1 = Methodref          #4.#13         // java/lang/Object."<init>":()V

   #2 = Class              #14            // Week_01/HelloByteCode
   #3 = Methodref          #2.#13         // Week_01/HelloByteCode."<init>":()V
   #4 = Class              #15            // java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Utf8               LineNumberTable
   #9 = Utf8               main
  #10 = Utf8               ([Ljava/lang/String;)V
  #11 = Utf8               SourceFile
  #12 = Utf8               HelloByteCode.java
  #13 = NameAndType        #5:#6          // "<init>":()V
  #14 = Utf8               Week_01/HelloByteCode
  #15 = Utf8               java/lang/Object
{
  public Week_01.学习总结.HelloByteCode();

    //descriptor· 表示该方法的描述；()这里表示的是该方法的参数为空; V表示方法返回值为void
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1

         //加在本地变量表第一个slot，也是是this
         0: aload_0

         //调用构造函数，这里没有集成，就是调用 Object.init方法； this指向这个对象
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V

         //方法结束
         4: return
      LineNumberTable:
        line 9: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:

      //stack：操作数栈，深度为2； locals：本地变量表；两个slot，args_size：参数长度是1
      stack=2, locals=2, args_size=1

         // 实例化HelloByteCode，压入操作数栈 栈顶
         0: new           #2                  // class Week_01/HelloByteCode

          // 复制HelloByteCode对象，压入操作数栈 栈顶 （本质是会复制栈顶数据）
         3: dup

         // 栈顶数据执行构造方法，完成后销毁（注意这里看执行的方法有无返回值，有就压入栈顶，没有就只执行了销毁操作数动作），这样只留下啦最初的对象
         4: invokespecial #3                  // Method "<init>":()V

         // 将最初的对象存到本地变量表第一个slot
         7: astore_1

         //方法结束
         8: return
      LineNumberTable:
        line 13: 0
        line 15: 8
}
SourceFile: "HelloByteCode.java"
```

