package Week_01.work.lsd.bytecode;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-20 21:32
 * @Modified By：
 */
public class Hello {

    private static final String HELLO_CONTANT = "HELLO";

    private Byte b;
    private Short aShort;
    private Integer i;
    private Float f;
    private Double d;
    private Long l;
    private Boolean bool;



    public Hello() {
    }

    public Hello(Byte b, Short aShort, Integer i, Float f, Double d, Long l, Boolean bool) {
        this.b = b;
        this.aShort = aShort;
        this.i = i;
        this.f = f;
        this.d = d;
        this.l = l;
        this.bool = bool;
    }

    public void initAndCal(){
        b = 127;
        aShort = 128;
        i = 129;
        f = 130f;
        d = 131d;
        l = 132L;
        bool = true;

        double a = b + aShort + f + d + l;
    }
}
