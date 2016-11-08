import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    private static Scanner mScanner = new Scanner(System.in);

    public static void main(String[] args) {
        Class mClass;
        String mReadLine;
        while (true) {
            System.out.println("Введите название класса:");
            mReadLine = mScanner.nextLine();
            try {
                mClass = Class.forName(mReadLine);
                break;
            } catch (ClassNotFoundException e) {
                System.out.println("Класс не найден!");

            }
        }
        while (true) {
            System.out.println("Введите название аннотации или наберите exit для выхода:");
            Method[] mMethods = mClass.getDeclaredMethods();
            mReadLine = mScanner.nextLine();
            if (mReadLine.equals("exit")) break;
            for (Method mMethod : mMethods) {
                Annotation[] mAnnotations = mMethod.getAnnotations();
                for (Annotation mAnnotation : mAnnotations) {
                    if (mReadLine.equals(mAnnotation.annotationType().getName())) {
                        Method[] mParametrs = mAnnotation.getClass().getDeclaredMethods();
                        Object mObjectParametr = null;
                        try {
                            for (Method mParametr : mParametrs) {
                                if (mParametr.getName().equals("delay"))
                                    mObjectParametr = mParametr.invoke(mAnnotation);
                            }
                            if (mObjectParametr != null) {
                                System.out.println("Задержка составит " + mObjectParametr + " секунд");
                                sleep((int) mObjectParametr * 1000);
                                mMethod.invoke(mClass.getClass());
                            } else {
                                mMethod.invoke(mClass.getClass());
                            }
                        } catch (IllegalAccessException | InterruptedException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
