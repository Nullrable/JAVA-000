**作业见** *作业目录*

## 一. 字节码

### 1. 什么是字节码

> Javabytecode由单字节（byte）的指令组成，理论上最多支持256个操作码（opcode）。实际上Java只使用了200左右的操作码，还有一些操作码则保留给调试操作。

根据指令的性质，主要分为四个大类：
1.栈操作指令，包括与局部变量交互的指令2.程序流程控制指令
3.对象操作指令，包括方法调用指令
4.算术运算以及类型转换指令

### 2. 查看字节码

> javap -c -v demo.jvm0104.HelloByteCode

### 3. JVM运行时结构

> JVM是一台基于栈的计算机器。
每个线程都有一个独属于自己的线程栈（JVMStack），用于存储栈帧（Frame）。
每一次方法调用，JVM都会自动创建一个栈帧。
栈帧由操作数栈，局部变量数组以及一个Class引用组成。
Class引用指向当前方法在运行时常量池中对应的Class。

3.1 栈帧(Stack Frame)的局部变量表：存放方法参数和局部变量的内存区域。对于实例方法，slot0存储this，对于静态方法存储参数
>​ java数据类型包括：byte boolean char short int long float double reference(对象引用数据类型，是指向堆内存的引用)

3.2 栈帧(Stack Frame)的局部变量表中的最小单位为slot
> jvm规范中没有特定指定slot的大小，通常在32位操作系统中，slot占32位，此时long/double占两个slot。再64位操作系统中，slot占64位。所有数据类型都占1个slot。

### 4. JVM数据类型

Java是静态类型的，它会影响字节码指令的设计，这样指令就会期望自己对特定类型的值进行操作。例如，就会有好几个add指令用于两个数字相加：iadd、ladd、fadd、dadd。他们期望类型的操作数分别是int、long、float和double。大多数字节码都有这样的特性，它具有不同形式的相同功能，这取决于操作数类型。
JVM定义的数据类型包括:

1.  基本类型:
    数值类型: byte (8位), short (16位), int (32位), long (64-bit位), char (16位无符号Unicode), float(32-bit IEEE 754 单精度浮点型), double (64-bit IEEE 754 双精度浮点型)
    布尔类型
    指针类型: 指令指针。
2.  引用类型:
    类
    数组
    接口
在字节码中布尔类型的支持是受限的。举例来说，没有结构能直接操作布尔值。布尔值被替换转换成 int 是通过编译器来进行的，并且最终还是被转换成 int 结构。Java 开发者应该熟悉所有上面的类型，除了 returnAddress，它没有等价的编程语言类型。类数组接口在字节码中布尔类型的支持是受限的。举例来说，没有结构能直接操作布尔值。布尔值被替换转换成 int 是通过编译器来进行的，并且最终还是被转换成 int 结构。

Java 开发者应该熟悉所有上面的类型，除了 returnAddress，它没有等价的编程语言类型


### 5. 字节码助记符

https://blog.csdn.net/PacosonSWJTU/article/details/50600128

### 6. 参考文章

https://blog.csdn.net/a15089415104/article/details/83215598（一，二，三）

https://zhuanlan.zhihu.com/p/81965927?from_voters_page=true

## 二. JVM加载器

### 1. 类的生命周期

1.加载（Loading）：找Class文件
2.验证（Verification）：验证格式、依赖
3.准备（Preparation）：静态字段、方法表
4.解析（Resolution）：符号解析为引用
5.初始化（Initialization）：构造器、静态变量赋值、静态代码块6.使用（Using）
7.卸载（Unloading）