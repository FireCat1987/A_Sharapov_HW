import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InterruptedException {
        Class cl = Class.forName("TargetClass");
        Method[] method = cl.getDeclaredMethods();
        Object action = null;
        for(Method md: method){

            if(md.isAnnotationPresent(SimpleAnnotation.class)) {
                System.out.println("Запускаем методы которые имеют аннотацию SimpleAnnotation");
                md.invoke(cl.getClass());
            }
        }
        for(Method md: method){

            if(md.isAnnotationPresent(DelayAnnotation.class)) {
                System.out.println("Запускаем методы которые имеют аннотацию DelayAnnotation с задержкой");
                int i = md.getAnnotation(DelayAnnotation.class).delay();
                System.out.println("Задержка составит " + i + " секунд");
                sleep(i * 1000);
                md.invoke(cl.getClass());
            }
        }

    }
}
