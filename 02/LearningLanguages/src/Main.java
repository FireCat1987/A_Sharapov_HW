import java.io.*;
import java.util.*;
import java.util.function.UnaryOperator;

public class Main {
    private static final String PATH_TO_PROPERTIES = "src/langfile/";


    private static String[] Stat = {"0","0"};
    private static File file;
    private static String id;

    private static Properties prop = new Properties();
    private static Scanner in = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        inValue("Хотите продолжить с предидущими результатами? (да/нет): ", Main::getId);
        loadLangFile("Введите название языка который желаете изучать или введите exit для выхода: ");
    }

    private static String getId(String s) {
        int k = new Integer(s);
        if (k == 1) {
            loadFile("stat");
            id = inValue("Введите свой номер: ", null);
            int i = 0;
            while (i == 0) {
                if (!getRuProp(String.valueOf(id)).equals("")) {
                    Stat = getRuProp(String.valueOf(id)).split(";");
                    System.out.println("Ваши предидущие результаты: правильных ответов - " + Stat[0] + ", неправильных - " + Stat[1]);
                    i = 1;
                } else {
                    id = inValue("Такой номер не найден, попробуйте ещё или введите 0 для создания нового номера: ", null);
                }
            }
        } else {
            id = String.valueOf(random.nextInt(100));
            System.out.println("Ваш id = " + id);

        }
        return id;
    }

    private static String getRuProp(String s) {
        try {
            return new String(prop.getProperty(s).getBytes("ISO8859-1"));
        } catch (IOException e) {
            e.getMessage();
            System.err.println("ОШИБКА: неудалось получить свойство " + s + "!");
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return "";
    }

    private static String inValue(String text , UnaryOperator<String> callback) {
        System.out.println(text);
        String a = in.next();
        switch (a.toLowerCase()) {
            case "да":
                if(callback != null) {
                    callback.apply("1");
                    break;
                } else {
                    return a;
                }
            case "нет":
                if(callback != null) {
                    callback.apply("0");
                    break;
                } else {
                    return a;
                }
            case "exit":
                statexit();
                break;
            case "выход":
                statexit();
                break;
            default:
                if(callback != null) {
                    inValue(text,callback);
                    break;
                } else {
                    return a;
                }
        }
        return "0";
    }
    private static String savestat(String s){
        if (!id.isEmpty() && s.equalsIgnoreCase("1")) {
            loadFile("stat");
            prop.setProperty(id, Stat[0] + ";" + Stat[1]);
            saveFile("stat");
            return "Результаты сохранены.";
        } else {
            return "Нет id для сохранения результата.";
        }

    }
    private static void statexit() {
        if (!Stat[0].equalsIgnoreCase("0") || !Stat[1].equalsIgnoreCase("0")) {
            System.out.println("Ваши результаты: правильных ответов - " + Stat[0] + ", неправильных - " + Stat[1]);
            inValue("Сохранить результат? (да/нет): ", Main::savestat);
        }
        System.out.println("пока!");
        System.exit(0);
    }

    private static void loadLangFile(String str) {
        try {
            String s = inValue(str, null);
            int i = 0;
            while (i == 0) {
                file = new File(PATH_TO_PROPERTIES + s + ".properties");
                if (file.exists() && file.isFile()) {
                    FileInputStream fis = new FileInputStream(file);
                    prop.clear();
                    prop.load(fis);
                    i = 1;
                    break;
                } else {
                    s = inValue("Данный языковой файл не найден, попробуйте ещё раз: ", null);
                }
            }
            if(!prop.isEmpty()) {
                System.out.println("Поехали...");
                getQuestion();
            } else {
                System.out.println("В данном языке пока заданий нет");
                loadLangFile("Введите название языка который желаете изучать: ");
            }

        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private static void getQuestion() {
        for (Object o : prop.keySet()) {
            String sin = inValue(o.toString() + " = ?", null);
            if (sin.equalsIgnoreCase(getRuProp(o.toString()))) {
                Stat[0] = String.valueOf(new Integer(Stat[0]) + 1);
                System.out.println("Ответ верный (" +getRuProp(o.toString())+ ")");
            } else {
                Stat[1] = String.valueOf(new Integer(Stat[1]) + 1);
                System.out.println("Ответ неверный (" +getRuProp(o.toString())+ ")");
            }
        }
        System.out.println("Больше заданий к сожалению нет");
        loadLangFile("Введите название языка который желаете изучать: ");
    }

    private static void loadFile(String s) {
        file = new File(PATH_TO_PROPERTIES + s + ".properties");

        try {

            if (file.exists() && file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                prop.clear();
                prop.load(fis);
            } else {
                System.err.println("Данный файл не найден, или его невозможно прочитать!");

            }
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private static void saveFile(String s) {
        file = new File(PATH_TO_PROPERTIES + s + ".properties");
        try {

            if (file.exists() && file.isFile()) {
                FileOutputStream fos = new FileOutputStream(file);
                prop.store(fos, "");
            } else {
                System.err.println("Данный файл не найден, или его невозможно прочитать!!!");

            }
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
