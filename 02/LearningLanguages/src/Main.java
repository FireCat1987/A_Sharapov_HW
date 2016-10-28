import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.util.*;
import java.util.function.UnaryOperator;

public class Main {
    private static final String PATH_TO_lANGUAGE = "src/langfile/";
    private static final String PATH_TO_PROPERTIES = "src/";

    private static String[] sStat = {"0", "0"};
    private static File sFile;
    private static String sId;

    private static Properties sProp = new Properties();
    private static Scanner sIn = new Scanner(System.in);
    private static Random sRandom = new Random();

    public static void main(String[] args) {
        inValue("Хотите продолжить с предидущими результатами? (да/нет): ", Main::getId);
        loadLangFile("Введите название языка который желаете изучать или введите exit для выхода: ");
    }

    private static String getId(String s) {
        int k = new Integer(s);
        if (k == 1) {
            loadFile("stat");
            sId = inValue("Введите свой номер: ", null);
            while (true) {
                if (!getRuProp(String.valueOf(sId)).equals("")) {
                    sStat = getRuProp(String.valueOf(sId)).split(";");
                    System.out.println("Ваши предидущие результаты: правильных ответов - " + sStat[0] + ", неправильных - " + sStat[1]);
                    break;
                } else {
                    sId = inValue("Такой номер не найден, попробуйте ещё или введите 0 для создания нового номера: ", null);
                }
            }
        } else {
            sId = String.valueOf(sRandom.nextInt(100));
            System.out.println("Ваш id = " + sId);

        }
        return sId;
    }

    private static String getRuProp(String s) {
        try {
            return new String(sProp.getProperty(s).getBytes("ISO8859-1"));
        } catch (IOException e) {
            e.getMessage();
            System.err.println("ОШИБКА: неудалось получить свойство " + s + "!");
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return "";
    }

    private static String inValue(String text, UnaryOperator<String> callback) {
        System.out.println(text);
        String a = sIn.next();
        switch (a.toLowerCase()) {
            case "да":
                if (callback != null) {
                    callback.apply("1");
                    break;
                } else {
                    return a;
                }
            case "нет":
                if (callback != null) {
                    callback.apply("0");
                    break;
                } else {
                    return a;
                }
            case "exit":
                exit();
                break;
            case "выход":
                exit();
                break;
            default:
                if (callback != null) {
                    inValue(text, callback);
                    break;
                } else {
                    return a;
                }
        }
        return "0";
    }

    @NotNull
    private static String saveStat(String s) {
        if (!sId.isEmpty() && s.equalsIgnoreCase("1")) {

            loadFile("stat");
            sProp.clear();
            sProp.setProperty(sId, sStat[0] + ";" + sStat[1]);
            saveFile("stat");
            return "Результаты сохранены.";
        } else {
            return "Нет id для сохранения результата.";
        }
    }

    private static void exit() {
        if (!sStat[0].equalsIgnoreCase("0") || !sStat[1].equalsIgnoreCase("0")) {
            System.out.println("Ваши результаты: правильных ответов - " + sStat[0] + ", неправильных - " + sStat[1]);
            inValue("Сохранить результат? (да/нет): ", Main::saveStat);
        }
        System.out.println("пока!");
        System.exit(0);
    }

    private static void loadLangFile(String str) {
        try {
            String s = inValue(str, null);
            while (true) {
                sFile = new File(PATH_TO_lANGUAGE + s + ".properties");
                if (sFile.exists() && sFile.isFile()) {
                    FileInputStream fis = new FileInputStream(sFile);
                    sProp.clear();
                    sProp.load(fis);
                    break;
                } else {
                    s = inValue("Данный языковой файл не найден, попробуйте ещё раз: ", null);
                }
            }
            if (!sProp.isEmpty()) {
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
        List<Object> listOfKeys = new ArrayList<>(sProp.keySet());
        Collections.shuffle(listOfKeys);
        for (Object o : listOfKeys) {
            String sin = inValue(o.toString() + " = ?", null);
            if (sin.equalsIgnoreCase(getRuProp(o.toString()))) {
                sStat[0] = String.valueOf(new Integer(sStat[0]) + 1);
                System.out.println("Ответ верный (" + getRuProp(o.toString()) + ")");
            } else {
                sStat[1] = String.valueOf(new Integer(sStat[1]) + 1);
                System.out.println("Ответ неверный (" + getRuProp(o.toString()) + ")");
            }
        }
        System.out.println("Больше заданий к сожалению нет");
        loadLangFile("Введите название языка который желаете изучать: ");
    }

    private static void loadFile(String s) {
        sFile = new File(PATH_TO_PROPERTIES + s + ".properties");
        try {

            if (sFile.exists() && sFile.isFile()) {
                FileInputStream fis = new FileInputStream(sFile);
                sProp.load(fis);
            } else {
                System.err.println("Данный файл не найден, или его невозможно прочитать! Создаю новый файл...");
            }
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private static void saveFile(String s) {
        sFile = new File(PATH_TO_PROPERTIES + s + ".properties");
        try {
            FileOutputStream fos = new FileOutputStream(sFile);
            sProp.store(fos, "");
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println("Результаты сохранены!");
    }
}
