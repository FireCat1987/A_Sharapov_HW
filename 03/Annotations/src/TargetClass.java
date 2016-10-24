
public class TargetClass {
    @DelayAnnotation(delay=5)
    public static void method1() {
        System.out.println("method1");
    }
    @SimpleAnnotation
    public static void method2() {
        System.out.println("method2");
    }
    @SimpleAnnotation
    public static void method3() {
        System.out.println("method3");
    }
    @DelayAnnotation(delay=10)
    public static void method4() {
        System.out.println("method4");
    }
    public static void method5() {
        System.out.println("method5");
    }
    public static void method6() {
        System.out.println("method6");
    }
    public static void method7() {
        System.out.println("method7");
    }
    public static void method8() {
        System.out.println("method8");
    }
    public static void method9() {
        System.out.println("method9");
    }
    public static void method10() {
        System.out.println("method10");
    }
}
