public class Main {

    public static void main(String[] args) {

        int[][] a = new int[4][5];
        int counter = 0;
        for (int[] ai:a) {
            for (int i = 0; i < ai.length; i++) {
                ai[i] = counter++;
            }
        }
        printMatr(a);
        int[][] tA = transMatr(a);
        printMatr(tA);
        int[][] mA = multMatr(a,tA);
        printMatr(mA);
    }

    private static int[][] multMatr(int[][] m1, int[][] m2) throws IllegalArgumentException{
        if(m1.length == 0 && m2.length == 0) {
            throw new IllegalArgumentException("Одна или обе матрицы пустые");
        }
        if(m1[0].length != m2.length) {
            throw new IllegalArgumentException("Матрицы не согласованы");
        }
        int[][] res = new int[m1.length][m2[0].length];
        System.out.println("Умножаем матрицы...");
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;

    }

    private static int[][] transMatr(int[][] m) throws IllegalArgumentException{

        if(m.length == 0) {
            throw new IllegalArgumentException("Матрица пустая");
        }
        int rowLength = m[0].length;
        for (int[] ai:m) {
            if (rowLength != ai.length) {
                throw new IllegalArgumentException("Матрица не симметрична");
            }
        }

        System.out.println("Транспонируем матрицу...");
        int [][] tM = new int[rowLength][];
        for (int i = 0; i < rowLength; i++) {
            tM[i] = new int[m.length];
        }
        for (int i = 0; i < m.length; i++) {
            int[] tArr = m[i];
            for (int j = 0; j < rowLength; j++) {
                tM[j][i] = tArr[j];
            }
        }

        return tM;

    }

    private static void printMatr(int[][] m) {
        for (int[] i:m) {
            String v = "";
            for (int j:i) {
                v += j + " ";
            }
            System.out.println(v);
        }
    }
}
