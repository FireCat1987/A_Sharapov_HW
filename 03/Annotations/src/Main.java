import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class cl = Class.forName("TargetClass");
        Method[] method = cl.getMethods();
        Object action = null;
        for(Method md: method){
            if(md.isAnnotationPresent(SimpleAnnotation.class)) {
                ;
                md.invoke(cl.getClass());
            }

        }

    }
}
