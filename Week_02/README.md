# 各类GC分析
## 1. ParallelGC
### 分析1：带上参数 -XX:SurvivorRatio=8
`java -Xmx512m -Xms512m -Xmn100m -XX:SurvivorRatio=8 -XX:+PrintGCDetails Week_02.com.lsd.gc.GCLogAnalysis`

```
[GC (Allocation Failure) [PSYoungGen: 81920K->10229K(92160K)] 81920K->29752K(514048K), 0.0111182 secs] [Times: user=0.02 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92149K->10222K(92160K)] 111672K->61042K(514048K), 0.0185135 secs] [Times: user=0.03 sys=0.09, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92142K->10235K(92160K)] 142962K->88897K(514048K), 0.0149950 secs] [Times: user=0.03 sys=0.06, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92155K->10224K(92160K)] 170817K->118116K(514048K), 0.0158554 secs] [Times: user=0.03 sys=0.06, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92144K->10237K(92160K)] 200036K->142439K(514048K), 0.0124433 secs] [Times: user=0.02 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92127K->10228K(92160K)] 224328K->167041K(514048K), 0.0126891 secs] [Times: user=0.02 sys=0.05, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92148K->10239K(92160K)] 248961K->191757K(514048K), 0.0130306 secs] [Times: user=0.02 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 91952K->10230K(92160K)] 273470K->218685K(514048K), 0.0140734 secs] [Times: user=0.02 sys=0.06, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 91970K->10238K(92160K)] 300425K->244947K(514048K), 0.0140505 secs] [Times: user=0.02 sys=0.05, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92029K->10229K(92160K)] 326739K->269810K(514048K), 0.0110722 secs] [Times: user=0.02 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92149K->10239K(92160K)] 351730K->297708K(514048K), 0.0136865 secs] [Times: user=0.03 sys=0.06, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92159K->10212K(92160K)] 379628K->323460K(514048K), 0.0130273 secs] [Times: user=0.02 sys=0.05, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92132K->10237K(92160K)] 405380K->352190K(514048K), 0.0146812 secs] [Times: user=0.02 sys=0.06, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92157K->10223K(92160K)] 434110K->382114K(514048K), 0.0140696 secs] [Times: user=0.03 sys=0.06, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92143K->10238K(92160K)] 464034K->412180K(514048K), 0.0146160 secs] [Times: user=0.03 sys=0.06, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 10238K->0K(92160K)] [ParOldGen: 401942K->260715K(421888K)] 412180K->260715K(514048K), [Metaspace: 2695K->2695K(1056768K)], 0.0440377 secs] [Times: user=0.21 sys=0.01, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 81920K->10225K(92160K)] 342635K->291620K(514048K), 0.0043150 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92145K->10235K(92160K)] 373540K->319474K(514048K), 0.0056590 secs] [Times: user=0.04 sys=0.01, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92002K->10233K(92160K)] 401242K->348758K(514048K), 0.0055409 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92153K->10225K(92160K)] 430678K->375135K(514048K), 0.0057221 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92145K->10228K(92160K)] 457055K->401389K(514048K), 0.0075694 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 10228K->0K(92160K)] [ParOldGen: 391160K->300067K(421888K)] 401389K->300067K(514048K), [Metaspace: 2695K->2695K(1056768K)], 0.0410978 secs] [Times: user=0.21 sys=0.00, real=0.04 secs]
```
可以看出年轻代可用内存是 92160K = 90m，而我们设置的是100m，为什么会少10m内，因为年轻代是由Eden区，S0，S1构成，比例是8:1:1（SurvivorRatio参数决定），其中S0和S1其中一个必为空，所以年轻代可用内存只有实际的90% 即 100m * 0.9 = 90m

`[GC (Allocation Failure) [PSYoungGen: 81920K->10229K(92160K)] 81920K->29752K(514048K), 0.0111182 secs] [Times: user=0.02 sys=0.05, real=0.01 secs]`

我们来分析这一条数据来验证下跟参数设置是否一致：
* PSYoungGen: 81920K->10229K(92160K) => 整个Young区GC内存变化， Young区可用总内存为 92160K / 1024 = 90m； 其中参数 SurvivorRatio = 8，则空闲内存为10m，所以Young区总内存为100m，跟我们启动参数设置 -Xmn100m 保持一致
* 81920K->29752K(514048K) => 整个堆GC内存变化， 这段比较有意思，可以看到这两个GC前数值是一样的，原因也很好理解，原因是第一次GC还没有对象晋升到Old区，所以所有对象都在Young区，所以GC前是一样的。现在来看一下整个堆可用内存是 514048K / 1024 = 502m，加上S0或S1空闲的10m，整个堆的分配内存为502m + 10m = 512m，跟设置的 -Xmx512m -Xms512m 是一致的

### 分析2: 接下来我们看下将 -Xms 去除，使用默认值看下GC变化情况

`
java -Xmx512m  -Xmn100m -XX:SurvivorRatio=8 -XX:+PrintGCDetails Week_02.com.lsd.gc.GCLogAnalysis
`

```
[GC (Allocation Failure) [PSYoungGen: 81920K->10236K(92160K)] 81920K->30472K(251904K), 0.0119625 secs] [Times: user=0.01 sys=0.06, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 91984K->10228K(92160K)] 112221K->58766K(251904K), 0.0167593 secs] [Times: user=0.02 sys=0.09, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92015K->10238K(92160K)] 140553K->88152K(251904K), 0.0159168 secs] [Times: user=0.03 sys=0.07, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92146K->10234K(92160K)] 170060K->115328K(251904K), 0.0127168 secs] [Times: user=0.02 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92133K->10235K(92160K)] 197226K->141589K(251904K), 0.0135753 secs] [Times: user=0.02 sys=0.06, real=0.01 secs]
第一次GC [Full GC (Ergonomics) [PSYoungGen: 10235K->0K(92160K)] [ParOldGen: 131354K->124898K(249856K)] 141589K->124898K(342016K), [Metaspace: 2695K->2695K(1056768K)], 0.0165800 secs] [Times: user=0.10 sys=0.01, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 81832K->10223K(92160K)] 206730K->155449K(342016K), 0.0080981 secs] [Times: user=0.03 sys=0.03, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92143K->10239K(92160K)] 237369K->186485K(342016K), 0.0158559 secs] [Times: user=0.03 sys=0.07, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92159K->10238K(92160K)] 268405K->213390K(342016K), 0.0150244 secs] [Times: user=0.03 sys=0.06, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92032K->10228K(92160K)] 295184K->242551K(342016K), 0.0149924 secs] [Times: user=0.03 sys=0.06, real=0.02 secs]
第二次GC [Full GC (Ergonomics) [PSYoungGen: 10228K->0K(92160K)] [ParOldGen: 232323K->189039K(338432K)] 242551K->189039K(430592K), [Metaspace: 2695K->2695K(1056768K)], 0.0300501 secs] [Times: user=0.17 sys=0.01, real=0.03 secs]
[GC (Allocation Failure) [PSYoungGen: 81920K->10233K(92160K)] 270959K->218477K(430592K), 0.0030289 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 92153K->10235K(92160K)] 300397K->244519K(430592K), 0.0048928 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 91936K->10232K(92160K)] 326220K->274256K(430592K), 0.0143153 secs] [Times: user=0.02 sys=0.07, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92152K->10218K(92160K)] 356176K->299492K(430592K), 0.0135519 secs] [Times: user=0.02 sys=0.06, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92138K->10237K(92160K)] 381412K->326353K(430592K), 0.0157368 secs] [Times: user=0.03 sys=0.07, real=0.02 secs]
第三次GC [Full GC (Ergonomics) [PSYoungGen: 10237K->0K(92160K)] [ParOldGen: 316116K->239308K(407040K)] 326353K->239308K(499200K), [Metaspace: 2695K->2695K(1056768K)], 0.0376212 secs] [Times: user=0.21 sys=0.00, real=0.03 secs]
[GC (Allocation Failure) [PSYoungGen: 81491K->10234K(92160K)] 320799K->267491K(499200K), 0.0028306 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 92118K->10233K(92160K)] 349375K->297820K(499200K), 0.0065531 secs] [Times: user=0.04 sys=0.01, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92153K->10236K(92160K)] 379740K->326315K(499200K), 0.0080601 secs] [Times: user=0.05 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 91780K->10238K(92160K)] 407858K->353603K(499200K), 0.0152258 secs] [Times: user=0.02 sys=0.07, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92158K->10235K(92160K)] 435523K->381626K(499200K), 0.0149495 secs] [Times: user=0.03 sys=0.06, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 92155K->10228K(92160K)] 463546K->411239K(499200K), 0.0149816 secs] [Times: user=0.02 sys=0.06, real=0.02 secs]
第四次GC [Full GC (Ergonomics) [PSYoungGen: 10228K->0K(92160K)] [ParOldGen: 401011K->295307K(421888K)] 411239K->295307K(514048K), [Metaspace: 2695K->2695K(1056768K)], 0.0421605 secs] [Times: user=0.25 sys=0.01, real=0.04 secs]
[GC (Allocation Failure) [PSYoungGen: 81415K->10234K(92160K)] 376723K->319995K(514048K), 0.0033173 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 92154K->10233K(92160K)] 401915K->348312K(514048K), 0.0057337 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 92153K->10227K(92160K)] 430232K->374071K(514048K), 0.0056641 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 91826K->10237K(92160K)] 455670K->396599K(514048K), 0.0060441 secs] [Times: user=0.04 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 92008K->10227K(92160K)] 478370K->422809K(514048K), 0.0083071 secs] [Times: user=0.03 sys=0.02, real=0.00 secs]
第五次GC [Full GC (Ergonomics) [PSYoungGen: 10227K->0K(92160K)] [ParOldGen: 412581K->314182K(421888K)] 422809K->314182K(514048K), [Metaspace: 2695K->2695K(1056768K)], 0.0473151 secs] [Times: user=0.26 sys=0.01, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 81920K->10236K(92160K)] 396102K->341602K(514048K), 0.0031351 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 91965K->10227K(92160K)] 423331K->371879K(514048K), 0.0048881 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92147K->10238K(92160K)] 453799K->402495K(514048K), 0.0052338 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 10238K->0K(92160K)] [ParOldGen: 392256K->331578K(421888K)] 402495K->331578K(514048K), [Metaspace: 2695K->2695K(1056768K)], 0.0477838 secs] [Times: user=0.24 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 81920K->10234K(92160K)] 413498K->364905K(514048K), 0.0034806 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92154K->10232K(92160K)] 446825K->393744K(514048K), 0.0050071 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 92152K->10237K(92160K)] 475664K->421704K(514048K), 0.0046613 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
第六次GC [Full GC (Ergonomics) [PSYoungGen: 10237K->0K(92160K)] [ParOldGen: 411467K->340852K(421888K)] 421704K->340852K(514048K), [Metaspace: 2695K->2695K(1056768K)], 0.0494386 secs] [Times: user=0.27 sys=0.01, real=0.05 secs]

```
* 首先可以看出初始的年轻代大小还是 92160K / 1024 = 90m，跟参数设置是一样的，不一样的地方在于整个堆的初始化大小，我们看第一条 251904K / 1024 = 246m + 10m =  256m，是设置的 -Xmx512m的50%；
* 另外比较有意思的是，整个堆扩容是每次发生在Full GC 之后，第一次GC，增加88m，第二次增加86.5m，读三次67m，第四次14.5m 总共增加 88m + 86.5m + 67m + 14.5m = 256m，跟初始化的 256m 累加就是512m，跟-Xmx512m一致
* 总结：如果不设置-Xms后，JVM会初始化堆的大小为 -Xmx的一半，然后Full GC时间点会较—Xms = -Xmx 时，提前，然后Full GC后就会进行扩容，直到等于-Xmx。

### 分析2: 接下来我们看下将 -XX:SurvivorRatio=8 去除，使用默认值看下GC变化情况

```
lusudongdeMacBook-Pro:JAVA-000 lusudong$ java -Xmx512m -Xms512m  -Xmn100m  -XX:+PrintGCDetails Week_02.com.lsd.gc.GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 76496K->12795K(89600K)] 76496K->26525K(511488K), 0.0098847 secs] [Times: user=0.01 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 89340K->12789K(89600K)] 103069K->51850K(511488K), 0.0167109 secs] [Times: user=0.03 sys=0.08, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 89492K->12788K(89600K)] 128554K->77505K(511488K), 0.0143438 secs] [Times: user=0.03 sys=0.06, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 89268K->12796K(89600K)] 153984K->100131K(511488K), 0.0110586 secs] [Times: user=0.03 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 89596K->12795K(89600K)] 176931K->125103K(511488K), 0.0121923 secs] [Times: user=0.02 sys=0.05, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 89595K->12799K(47616K)] 201903K->152278K(469504K), 0.0128468 secs] [Times: user=0.02 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 47466K->21617K(68608K)] 186945K->163960K(490496K), 0.0027347 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 55783K->27464K(68608K)] 198126K->173596K(490496K), 0.0057774 secs] [Times: user=0.03 sys=0.01, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 62168K->33791K(68608K)] 208300K->186971K(490496K), 0.0055230 secs] [Times: user=0.03 sys=0.01, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 68528K->27097K(68608K)] 221707K->198628K(490496K), 0.0093966 secs] [Times: user=0.02 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 61400K->12014K(68608K)] 232931K->210194K(490496K), 0.0114973 secs] [Times: user=0.01 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 46830K->13301K(68608K)] 245010K->223423K(490496K), 0.0056722 secs] [Times: user=0.01 sys=0.03, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 48117K->14958K(68608K)] 258239K->238084K(490496K), 0.0060290 secs] [Times: user=0.02 sys=0.02, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 49774K->11969K(68608K)] 272900K->249691K(490496K), 0.0070668 secs] [Times: user=0.01 sys=0.04, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 46652K->10577K(68608K)] 284374K->260030K(490496K), 0.0068368 secs] [Times: user=0.01 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 45154K->10898K(68608K)] 294608K->270065K(490496K), 0.0047139 secs] [Times: user=0.01 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 45616K->9887K(68608K)] 304783K->278889K(490496K), 0.0039454 secs] [Times: user=0.01 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 44447K->11428K(68608K)] 313450K->289075K(490496K), 0.0045038 secs] [Times: user=0.01 sys=0.02, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 46151K->12090K(68608K)] 323798K->300549K(490496K), 0.0053063 secs] [Times: user=0.01 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 46832K->10066K(68608K)] 335291K->310103K(490496K), 0.0056262 secs] [Times: user=0.01 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 44588K->11957K(68608K)] 344624K->321377K(490496K), 0.0047734 secs] [Times: user=0.01 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 46773K->13550K(68608K)] 356193K->334145K(490496K), 0.0062458 secs] [Times: user=0.02 sys=0.03, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 48159K->11872K(68608K)] 368753K->345673K(490496K), 0.0062650 secs] [Times: user=0.01 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 46576K->11000K(68608K)] 380378K->356461K(490496K), 0.0057135 secs] [Times: user=0.01 sys=0.03, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 45552K->13409K(68608K)] 391013K->369471K(490496K), 0.0063148 secs] [Times: user=0.02 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 47613K->15213K(68608K)] 403676K->384632K(490496K), 0.0061540 secs] [Times: user=0.02 sys=0.03, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 49890K->13420K(68608K)] 419308K->397383K(490496K), 0.0063921 secs] [Times: user=0.01 sys=0.03, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 48113K->13479K(69120K)] 432077K->410395K(491008K), 0.0067142 secs] [Times: user=0.01 sys=0.03, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 48807K->9926K(68608K)] 445723K->419495K(490496K), 0.0057763 secs] [Times: user=0.01 sys=0.02, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 9926K->0K(68608K)] [ParOldGen: 409569K->257063K(421888K)] 419495K->257063K(490496K), [Metaspace: 2695K->2695K(1056768K)], 0.0433246 secs] [Times: user=0.23 sys=0.01, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 35199K->14042K(71680K)] 292263K->271105K(493568K), 0.0014222 secs] [Times: user=0.01 sys=0.01, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 53462K->14515K(70144K)] 310526K->284945K(492032K), 0.0022669 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 53895K->14336K(74240K)] 324326K->298318K(496128K), 0.0022047 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 59392K->13191K(73216K)] 343374K->311315K(495104K), 0.0030281 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 58247K->12549K(76288K)] 356371K->323400K(498176K), 0.0021485 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 61701K->18892K(75264K)] 372552K->342021K(497152K), 0.0024913 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 67893K->14256K(76800K)] 391022K->354851K(498688K), 0.0063968 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 64432K->17181K(75776K)] 405027K->371975K(497664K), 0.0027647 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 67357K->15852K(77824K)] 422151K->386632K(499712K), 0.0028507 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 68076K->19485K(76800K)] 438856K->405418K(498688K), 0.0064938 secs] [Times: user=0.04 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 71521K->16490K(77824K)] 457454K->419913K(499712K), 0.0031642 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 16490K->0K(77824K)] [ParOldGen: 403423K->285638K(421888K)] 419913K->285638K(499712K), [Metaspace: 2695K->2695K(1056768K)], 0.0408511 secs] [Times: user=0.20 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 52224K->18974K(76800K)] 337862K->304613K(498688K), 0.0022602 secs] [Times: user=0.01 sys=0.01, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 71171K->17885K(77312K)] 356809K->320995K(499200K), 0.0039010 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 69564K->21083K(73728K)] 372674K->341327K(495616K), 0.0039200 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 73127K->15022K(77312K)] 393372K->353836K(499200K), 0.0035468 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 65708K->16436K(76288K)] 404522K->370078K(498176K), 0.0038264 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 67456K->18046K(78336K)] 421098K->387468K(500224K), 0.0030896 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 71806K->19496K(77824K)] 441228K->406166K(499712K), 0.0036589 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 73256K->16485K(78336K)] 459926K->420717K(500224K), 0.0065138 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 16485K->0K(78336K)] [ParOldGen: 404232K->314033K(421888K)] 420717K->314033K(500224K), [Metaspace: 2695K->2695K(1056768K)], 0.0450608 secs] [Times: user=0.22 sys=0.00, real=0.04 secs]
[GC (Allocation Failure) [PSYoungGen: 53503K->18498K(77824K)] 367536K->332531K(499712K), 0.0031525 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 72258K->21294K(76800K)] 386291K->352947K(498688K), 0.0048695 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 74542K->20817K(74240K)] 406195K->372502K(496128K), 0.0088693 secs] [Times: user=0.04 sys=0.01, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 74065K->18982K(77312K)] 425750K->390688K(499200K), 0.0040677 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 71202K->16478K(77312K)] 442908K->405535K(499200K), 0.0035053 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 68486K->21355K(76800K)] 457543K->425859K(498688K), 0.0032351 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 21355K->0K(76800K)] [ParOldGen: 404503K->333019K(421888K)] 425859K->333019K(498688K), [Metaspace: 2695K->2695K(1056768K)], 0.0463775 secs] [Times: user=0.24 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 51629K->17680K(77312K)] 384648K->350700K(499200K), 0.0018127 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 69904K->17815K(78336K)] 402924K->368082K(500224K), 0.0038378 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 71549K->19427K(77824K)] 421817K->387194K(499712K), 0.0076770 secs] [Times: user=0.04 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 73187K->20889K(77312K)] 440954K->407301K(499200K), 0.0059807 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 74108K->18721K(77824K)] 460520K->423392K(499712K), 0.0049137 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 18721K->0K(77824K)] [ParOldGen: 404670K->337289K(421888K)] 423392K->337289K(499712K), [Metaspace: 2695K->2695K(1056768K)], 0.0471528 secs] [Times: user=0.24 sys=0.01, real=0.04 secs]
执行结束!共生成对象次数:10377
```
* 可以看出Young区可用内存是一直在变化，原因是如果不指定SurvivorRatio = 8 这个参数的情况下，JVM对于Young区中的 Eden区，S0，S1 不是严格按照8:1:1来的，是有微小的变化的。所以Young在GC过程中有变化；

`[GC (Allocation Failure) [PSYoungGen: 76496K->12795K(89600K)] 76496K->26525K(511488K), 0.0098847 secs] [Times: user=0.01 sys=0.05, real=0.01 secs]`

* 分析这段可以看出，总的堆内存计算还是符合公式的 总的堆可用内存 511488K / 1024 = 499.5m；SO或S1占用无效内存为 100m - 89600K/1024 = 12.5m；堆总的分配内存为 499.5m + 12.5 = 512m

### 分析3: -Xmx128m -Xms128m

```
lusudongdeMacBook-Pro:JAVA-000 lusudong$ java -Xmx128m -Xms128m  -XX:SurvivorRatio=8 -XX:+PrintGCDetails Week_02.com.lsd.gc.GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 34853K->4080K(39424K)] 34853K->9883K(126976K), 0.0037418 secs] [Times: user=0.01 sys=0.02, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 39408K->4080K(39424K)] 45211K->20463K(126976K), 0.0057548 secs] [Times: user=0.02 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 39408K->4077K(39424K)] 55791K->34066K(126976K), 0.0060383 secs] [Times: user=0.01 sys=0.03, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 39405K->4095K(39424K)] 69394K->45999K(126976K), 0.0054924 secs] [Times: user=0.02 sys=0.02, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 39008K->4077K(39424K)] 80912K->56653K(126976K), 0.0055275 secs] [Times: user=0.01 sys=0.02, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 39380K->4081K(39424K)] 91955K->70262K(126976K), 0.0067520 secs] [Times: user=0.02 sys=0.03, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 39409K->4094K(39424K)] 105590K->82284K(126976K), 0.0052586 secs] [Times: user=0.01 sys=0.02, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 4094K->0K(39424K)] [ParOldGen: 78190K->73462K(87552K)] 82284K->73462K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0125590 secs] [Times: user=0.06 sys=0.01, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 34724K->0K(39424K)] [ParOldGen: 73462K->82248K(87552K)] 108187K->82248K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0154271 secs] [Times: user=0.08 sys=0.01, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 35278K->6036K(39424K)] [ParOldGen: 82248K->87206K(87552K)] 117526K->93242K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0095751 secs] [Times: user=0.04 sys=0.01, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 35157K->13623K(39424K)] [ParOldGen: 87206K->87277K(87552K)] 122364K->100901K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0134106 secs] [Times: user=0.09 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 35328K->16546K(39424K)] [ParOldGen: 87277K->86883K(87552K)] 122605K->103430K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0133879 secs] [Times: user=0.07 sys=0.01, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 35181K->22093K(39424K)] [ParOldGen: 86883K->87086K(87552K)] 122065K->109179K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0184037 secs] [Times: user=0.10 sys=0.00, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 35142K->24446K(39424K)] [ParOldGen: 87086K->86884K(87552K)] 122229K->111330K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0118403 secs] [Times: user=0.07 sys=0.00, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 35223K->26230K(39424K)] [ParOldGen: 86884K->87060K(87552K)] 122107K->113290K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0112443 secs] [Times: user=0.06 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 35328K->28420K(39424K)] [ParOldGen: 87060K->87378K(87552K)] 122388K->115798K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0121392 secs] [Times: user=0.07 sys=0.01, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 35089K->30208K(39424K)] [ParOldGen: 87378K->87093K(87552K)] 122467K->117301K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0136110 secs] [Times: user=0.08 sys=0.00, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 35162K->30646K(39424K)] [ParOldGen: 87093K->87093K(87552K)] 122255K->117740K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0018662 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35277K->30715K(39424K)] [ParOldGen: 87093K->87494K(87552K)] 122370K->118210K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0184834 secs] [Times: user=0.10 sys=0.00, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 35005K->30641K(39424K)] [ParOldGen: 87494K->87454K(87552K)] 122499K->118096K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0166683 secs] [Times: user=0.09 sys=0.01, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 35073K->32372K(39424K)] [ParOldGen: 87454K->87295K(87552K)] 122527K->119668K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0042064 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35245K->33755K(39424K)] [ParOldGen: 87295K->87216K(87552K)] 122541K->120971K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0145059 secs] [Times: user=0.06 sys=0.00, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 35189K->34326K(39424K)] [ParOldGen: 87216K->87216K(87552K)] 122405K->121542K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0020820 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35253K->34254K(39424K)] [ParOldGen: 87216K->87216K(87552K)] 122470K->121470K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0019531 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35308K->34585K(39424K)] [ParOldGen: 87216K->86884K(87552K)] 122524K->121469K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0063013 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 35226K->34621K(39424K)] [ParOldGen: 86884K->86884K(87552K)] 122110K->121505K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0018277 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35290K->34693K(39424K)] [ParOldGen: 86884K->86884K(87552K)] 122174K->121577K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0014572 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35158K->34700K(39424K)] [ParOldGen: 86884K->86695K(87552K)] 122043K->121396K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0018823 secs] [Times: user=0.00 sys=0.01, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35286K->34911K(39424K)] [ParOldGen: 86695K->86649K(87552K)] 121981K->121560K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0110245 secs] [Times: user=0.05 sys=0.00, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 35215K->35055K(39424K)] [ParOldGen: 86649K->86649K(87552K)] 121865K->121704K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0019595 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35320K->34800K(39424K)] [ParOldGen: 86649K->86649K(87552K)] 121969K->121449K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0017969 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35233K->34847K(39424K)] [ParOldGen: 86649K->86580K(87552K)] 121882K->121428K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0089921 secs] [Times: user=0.06 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 34995K->34867K(39424K)] [ParOldGen: 87219K->86974K(87552K)] 122215K->121841K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0029370 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35262K->35262K(39424K)] [ParOldGen: 86974K->86974K(87552K)] 122236K->122236K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0016074 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 35300K->35262K(39424K)] [ParOldGen: 87457K->87421K(87552K)] 122758K->122683K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0013939 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[Full GC (Allocation Failure) [PSYoungGen: 35262K->35262K(39424K)] [ParOldGen: 87421K->87402K(87552K)] 122683K->122664K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0092609 secs] [Times: user=0.06 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 35262K->35262K(39424K)] [ParOldGen: 87546K->87474K(87552K)] 122808K->122736K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0020098 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Allocation Failure) [PSYoungGen: 35262K->35262K(39424K)] [ParOldGen: 87474K->87474K(87552K)] 122736K->122736K(126976K), [Metaspace: 2695K->2695K(1056768K)], 0.0020640 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3332)
	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:674)
	at java.lang.StringBuilder.append(StringBuilder.java:208)
	at Week_02.com.lsd.gc.GCLogAnalysis.generateGarbage(GCLogAnalysis.java:57)
	at Week_02.com.lsd.gc.GCLogAnalysis.main(GCLogAnalysis.java:26)
```
* 可以看出当设置的堆内存为128m时，经过7次Young GC后，后面一直在Full GC，因为堆内存过下，导致Old区内存过小，每次都出触发了Full GC 而且Full GC次数特别多，因为JVM产生的对象过快，清除过慢，JVM一直在想尽快腾出空间，但是分配的内存又不够，所以导致最后整个堆都溢出啦

### 分析4: 相同配置的CMS和Parallel GC有何区别
```
java -Xmx4g -Xms4g  -XX:SurvivorRatio=8 -XX:ParallelGCThreads=8 -XX:+PrintGCDetails Week_02.com.lsd.gc.GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 1118208K->139771K(1257984K)] 1118208K->250107K(4054528K), 0.1089789 secs] [Times: user=0.11 sys=0.52, real=0.11 secs]
[GC (Allocation Failure) [PSYoungGen: 1257979K->139766K(1257984K)] 1368315K->410295K(4054528K), 0.1746560 secs] [Times: user=0.13 sys=0.46, real=0.18 secs]
执行结束!共生成对象次数:8258
```


```
java -XX:+UseConcMarkSweepGC -Xmx4g -Xms4g  -XX:SurvivorRatio=8 -XX:ParallelGCThreads=8 -XX:+PrintGCDetails Week_02.com.lsd.gc.GCLogAnalysis

正在执行...
[GC (Allocation Failure) [ParNew: 545344K->68096K(613440K), 0.0636990 secs] 545344K->143064K(4126208K), 0.0637711 secs] [Times: user=0.12 sys=0.27, real=0.07 secs]
[GC (Allocation Failure) [ParNew: 613440K->68096K(613440K), 0.0705432 secs] 688408K->259072K(4126208K), 0.0705699 secs] [Times: user=0.13 sys=0.32, real=0.07 secs]
[GC (Allocation Failure) [ParNew: 613440K->68094K(613440K), 0.0851898 secs] 804416K->371547K(4126208K), 0.0852270 secs] [Times: user=0.56 sys=0.06, real=0.09 secs]
[GC (Allocation Failure) [ParNew: 613438K->68094K(613440K), 0.0884883 secs] 916891K->496253K(4126208K), 0.0885171 secs] [Times: user=0.58 sys=0.06, real=0.08 secs]
[GC (Allocation Failure) [ParNew: 613438K->68096K(613440K), 0.0874568 secs] 1041597K->612874K(4126208K), 0.0874850 secs] [Times: user=0.53 sys=0.06, real=0.09 secs]
执行结束!共生成对象次数:10264
```
* 可以看出Parallel GC GC次数比CMS GC 少，试验下来基本1～2次，比较符合适用于吞吐量的场景，GC次数少但比较耗时；而CMS GC次数多，但是每次都比较短，所以适合快速响应的场景

### 分析5: 相同配置的G1 GC和Parallel GC有何区别

```
lusudongdeMacBook-Pro:JAVA-000 lusudong$ java -XX:+UseG1GC  -Xmx4g -Xms4g  -XX:SurvivorRatio=8 -XX:ParallelGCThreads=8 -XX:+PrintGC Week_02.com.lsd.gc.GCLogAnalysis
正在执行...
[GC pause (G1 Evacuation Pause) (young) 204M->68M(4096M), 0.0249616 secs]
[GC pause (G1 Evacuation Pause) (young) 246M->126M(4096M), 0.0233337 secs]
[GC pause (G1 Evacuation Pause) (young) 304M->182M(4096M), 0.0223882 secs]
[GC pause (G1 Evacuation Pause) (young) 360M->234M(4096M), 0.0212540 secs]
[GC pause (G1 Evacuation Pause) (young) 412M->286M(4096M), 0.0217654 secs]
[GC pause (G1 Evacuation Pause) (young) 464M->340M(4096M), 0.0239874 secs]
[GC pause (G1 Evacuation Pause) (young) 518M->396M(4096M), 0.0246131 secs]
[GC pause (G1 Evacuation Pause) (young) 596M->462M(4096M), 0.0288608 secs]
[GC pause (G1 Evacuation Pause) (young) 722M->533M(4096M), 0.0303240 secs]
[GC pause (G1 Evacuation Pause) (young) 797M->616M(4096M), 0.0332157 secs]
执行结束!共生成对象次数:10389
```
跟CMS GC 非常类似，其GC次数多，但GC每次暂停业务时间都比较短


## 2. CMS GC

```
lusudongdeMacBook-Pro:JAVA-000 lusudong$ java -XX:+UseConcMarkSweepGC -Xmx4g -Xms4g  -XX:SurvivorRatio=8 -XX:ParallelGCThreads=8 -XX:+PrintGCDetails Week_02.com.lsd.gc.GCLogAnalysis
正在执行...
[GC (Allocation Failure) [ParNew: 545344K->68096K(613440K), 0.0606005 secs] 545344K->140179K(4126208K), 0.0606690 secs] [Times: user=0.11 sys=0.27, real=0.06 secs]
[GC (Allocation Failure) [ParNew: 613440K->68096K(613440K), 0.0666659 secs] 685523K->257851K(4126208K), 0.0667665 secs] [Times: user=0.13 sys=0.28, real=0.07 secs]
[GC (Allocation Failure) [ParNew: 613440K->68096K(613440K), 0.0877119 secs] 803195K->380290K(4126208K), 0.0877389 secs] [Times: user=0.55 sys=0.06, real=0.08 secs]
[GC (Allocation Failure) [ParNew: 613440K->68096K(613440K), 0.0837328 secs] 925634K->495950K(4126208K), 0.0837668 secs] [Times: user=0.56 sys=0.05, real=0.09 secs]
[GC (Allocation Failure) [ParNew: 613440K->68095K(613440K), 0.0837246 secs] 1041294K->610977K(4126208K), 0.0837543 secs] [Times: user=0.53 sys=0.06, real=0.08 secs]
执行结束!共生成对象次数:10360
```

在内存充足的情况下，对于Parallel GC，G1 GC 对于GCLogAnalysis，在1sec内，其实性能差不多，这里可以看出CMS GC 对于Young区使用的是ParNew，由于内存充足，并未看到Full GC，等下将堆内存设置的小一点看下


```
lusudongdeMacBook-Pro:JAVA-000 lusudong$ java -XX:+UseConcMarkSweepGC -Xmx512m -Xms512m  -XX:SurvivorRatio=8 -XX:ParallelGCThreads=8 -XX:+PrintGCDetails Week_02.com.lsd.gc.GCLogAnalysis
正在执行...
[GC (Allocation Failure) [ParNew: 139748K->17471K(157248K), 0.0200976 secs] 139748K->48180K(506816K), 0.0201432 secs] [Times: user=0.04 sys=0.09, real=0.02 secs]
[GC (Allocation Failure) [ParNew: 157217K->17470K(157248K), 0.0188541 secs] 187927K->86180K(506816K), 0.0188858 secs] [Times: user=0.03 sys=0.08, real=0.01 secs]
[GC (Allocation Failure) [ParNew: 157246K->17472K(157248K), 0.0317779 secs] 225956K->131564K(506816K), 0.0318041 secs] [Times: user=0.20 sys=0.02, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 157248K->17471K(157248K), 0.0348786 secs] 271340K->177554K(506816K), 0.0349153 secs] [Times: user=0.20 sys=0.03, real=0.04 secs]
[GC (Allocation Failure) [ParNew: 157247K->17471K(157248K), 0.0380395 secs] 317330K->228252K(506816K), 0.0380773 secs] [Times: user=0.21 sys=0.03, real=0.04 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 210781K(349568K)] 229049K(506816K), 0.0001813 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 157247K->17470K(157248K), 0.0299948 secs] 368028K->269903K(506816K), 0.0300196 secs] [Times: user=0.20 sys=0.02, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 157246K->17470K(157248K), 0.0347221 secs] 409679K->314321K(506816K), 0.0347565 secs] [Times: user=0.18 sys=0.03, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 157246K->17471K(157248K), 0.0338862 secs] 454097K->359528K(506816K), 0.0339136 secs] [Times: user=0.21 sys=0.03, real=0.04 secs]
[CMS-concurrent-abortable-preclean: 0.003/0.159 secs] [Times: user=0.65 sys=0.08, real=0.16 secs]
[GC (CMS Final Remark) [YG occupancy: 18264 K (157248 K)][Rescan (parallel) , 0.0002458 secs][weak refs processing, 0.0000180 secs][class unloading, 0.0002186 secs][scrub symbol table, 0.0003543 secs][scrub string table, 0.0001477 secs][1 CMS-remark: 342056K(349568K)] 360320K(506816K), 0.0010453 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 157247K->17471K(157248K), 0.0132774 secs] 457827K->362557K(506816K), 0.0133237 secs] [Times: user=0.09 sys=0.00, real=0.01 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 345085K(349568K)] 363324K(506816K), 0.0001509 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (CMS Final Remark) [YG occupancy: 31841 K (157248 K)][Rescan (parallel) , 0.0002537 secs][weak refs processing, 0.0000053 secs][class unloading, 0.0001976 secs][scrub symbol table, 0.0003660 secs][scrub string table, 0.0001466 secs][1 CMS-remark: 345085K(349568K)] 376927K(506816K), 0.0010177 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 157247K->17470K(157248K), 0.0112524 secs] 400821K->309223K(506816K), 0.0112788 secs] [Times: user=0.08 sys=0.00, real=0.01 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 291753K(349568K)] 309552K(506816K), 0.0001708 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 157246K->17468K(157248K), 0.0105937 secs] 448999K->355635K(506816K), 0.0106227 secs] [Times: user=0.08 sys=0.00, real=0.01 secs]
[CMS-concurrent-abortable-preclean: 0.001/0.033 secs] [Times: user=0.10 sys=0.00, real=0.04 secs]
[GC (CMS Final Remark) [YG occupancy: 17705 K (157248 K)][Rescan (parallel) , 0.0004177 secs][weak refs processing, 0.0000063 secs][class unloading, 0.0002641 secs][scrub symbol table, 0.0005037 secs][scrub string table, 0.0001218 secs][1 CMS-remark: 338166K(349568K)] 355872K(506816K), 0.0013608 secs] [Times: user=0.00 sys=0.01, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 157244K->157244K(157248K), 0.0000137 secs][CMS: 307656K->302999K(349568K), 0.0555569 secs] 464900K->302999K(506816K), [Metaspace: 2695K->2695K(1056768K)], 0.0556072 secs] [Times: user=0.06 sys=0.00, real=0.06 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 302999K(349568K)] 303287K(506816K), 0.0001300 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 139776K->17471K(157248K), 0.0074748 secs] 442775K->348344K(506816K), 0.0074995 secs] [Times: user=0.06 sys=0.00, real=0.01 secs]
[CMS-concurrent-abortable-preclean: 0.001/0.029 secs] [Times: user=0.08 sys=0.00, real=0.03 secs]
[GC (CMS Final Remark) [YG occupancy: 24654 K (157248 K)][Rescan (parallel) , 0.0002150 secs][weak refs processing, 0.0000053 secs][class unloading, 0.0001930 secs][scrub symbol table, 0.0003741 secs][scrub string table, 0.0001311 secs][1 CMS-remark: 330872K(349568K)] 355526K(506816K), 0.0009575 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 157247K->157247K(157248K), 0.0000151 secs][CMS: 330234K->316157K(349568K), 0.0548012 secs] 487481K->316157K(506816K), [Metaspace: 2695K->2695K(1056768K)], 0.0548554 secs] [Times: user=0.06 sys=0.00, real=0.05 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 316157K(349568K)] 316466K(506816K), 0.0001402 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 139776K->17470K(157248K), 0.0112781 secs] 455933K->362962K(506816K), 0.0113130 secs] [Times: user=0.07 sys=0.00, real=0.01 secs]
[CMS-concurrent-abortable-preclean: 0.000/0.031 secs] [Times: user=0.09 sys=0.00, real=0.03 secs]
[GC (CMS Final Remark) [YG occupancy: 17600 K (157248 K)][Rescan (parallel) , 0.0002135 secs][weak refs processing, 0.0000051 secs][class unloading, 0.0002396 secs][scrub symbol table, 0.0004774 secs][scrub string table, 0.0001343 secs][1 CMS-remark: 345492K(349568K)] 363092K(506816K), 0.0011441 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 157246K->157246K(157248K), 0.0000149 secs][CMS: 345244K->332282K(349568K), 0.0620573 secs] 502491K->332282K(506816K), [Metaspace: 2695K->2695K(1056768K)], 0.0621266 secs] [Times: user=0.06 sys=0.00, real=0.06 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 332282K(349568K)] 332411K(506816K), 0.0001498 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 139585K->139585K(157248K), 0.0000241 secs][CMS[CMS-concurrent-abortable-preclean: 0.000/0.021 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
 (concurrent mode failure): 332282K->336533K(349568K), 0.0538053 secs] 471868K->336533K(506816K), [Metaspace: 2695K->2695K(1056768K)], 0.0538778 secs] [Times: user=0.05 sys=0.00, real=0.06 secs]
执行结束!共生成对象次数:9515
```
可以看出，当堆内存设置到512m时，GC次数明显变多，还发生了好几次Old GC

```
[GC (CMS Initial Mark) [1 CMS-initial-mark: 316157K(349568K)] 316466K(506816K), 0.0001402 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 139776K->17470K(157248K), 0.0112781 secs] 455933K->362962K(506816K), 0.0113130 secs] [Times: user=0.07 sys=0.00, real=0.01 secs]
[CMS-concurrent-abortable-preclean: 0.000/0.031 secs] [Times: user=0.09 sys=0.00, real=0.03 secs]
[GC (CMS Final Remark) [YG occupancy: 17600 K (157248 K)][Rescan (parallel) , 0.0002135 secs][weak refs processing, 0.0000051 secs][class unloading, 0.0002396 secs][scrub symbol table, 0.0004774 secs][scrub string table, 0.0001343 secs][1 CMS-remark: 345492K(349568K)] 363092K(506816K), 0.0011441 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
```

* 阶段 1：Initial Mark（初始标记）
* 阶段 2：Concurrent Mark（并发标记）
* 阶段 3：Concurrent Preclean（并发预清理）
* 阶段 4：Final Remark（最终标记）
* 阶段 5：Concurrent Sweep（并发清除）
* 阶段 6：Concurrent Reset（并发重置）

可以看出带有Concurrent的是并发执行，并不影响业务时间，Concurrent操作的时候，默认占用系统线程数的1/4

主要影响业务时间的是两个阶段 *Initial Mark（初始标记）* 和 *Final Remark（最终标记）* 这两个阶段会产生SWT，即所有线程数都在干这一件事情，没有多余的线程处理业务


## 3. G1 GC
```
lusudongdeMacBook-Pro:JAVA-000 lusudong$ java -XX:+UseG1GC  -Xmx256m -Xms256m  -XX:SurvivorRatio=8 -XX:ParallelGCThreads=8 -XX:+PrintGC Week_02.com.lsd.gc.GCLogAnalysis
正在执行...
[GC pause (G1 Evacuation Pause) (young) 14M->4996K(256M), 0.0023627 secs]
[GC pause (G1 Evacuation Pause) (young) 32M->15M(256M), 0.0035665 secs]
[GC pause (G1 Evacuation Pause) (young) 69M->33M(256M), 0.0069668 secs]
[GC pause (G1 Evacuation Pause) (young) 100M->59M(256M), 0.0106766 secs]
[GC pause (G1 Evacuation Pause) (young) 132M->82M(256M), 0.0125033 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 185M->115M(256M), 0.0113007 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001345 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0010344 secs]
[GC remark, 0.0010796 secs]
[GC cleanup 120M->120M(256M), 0.0005324 secs]


[GC pause (G1 Evacuation Pause) (young)-- 206M->205M(256M), 0.0006601 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark)-- 206M->206M(256M), 0.0006348 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0000046 secs]
[GC concurrent-mark-start]
[GC pause (G1 Humongous Allocation) (young) 206M->206M(256M), 0.0005160 secs]
[Full GC (Allocation Failure)  206M->204M(256M), 0.0064840 secs]
```
过程如下
* Evacuation Pause: young（纯年轻代模式转移暂停）
* Concurrent Marking（并发标记）
* 阶段 1: Initial Mark（初始标记）
* 阶段 2: Root Region Scan（Root区扫描）
* 阶段 3: Concurrent Mark（并发标记）
* 阶段 4: Remark（再次标记）
* 阶段 5: Cleanup（清理）
* Evacuation Pause (mixed)（转移暂停: 混合模式）
* Full GC (Allocation Failure)

可以看出，当堆内存比较小时，都会发生频繁的GC，所以不管使用哪类GC，需要合适的配置堆内存大小和GC线程数，再结合实际发生的情况，使用上述数据分析推理程序哪里应该优化或者参数配置的有问题

PS：G1分析的比较简单，在实际业务场景中并未使用过，平时也未系统学习过这个GC，课后还是要系统学习下