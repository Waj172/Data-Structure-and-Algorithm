package love.aojie.稀疏数组;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 稀疏数组：
 *      1.应用场景：压缩二维数组，只记录二维数中有效元素
 *      2、结构：三列n行：一二两列记录二位数组的行和列，第三列记二维数组的元素个数，
 *              下面每一行记录二维数组具体元素坐标和值。
 */
public class SparseArray {

    public static void main(String[] args) {

        SparseArray sparseArray = new SparseArray();

        // 1、首创建一个原始的二维数组，11*11（五子棋存盘）
        // 其中 0 表示没有棋子， 1 表示黑子 ， 2 表示白子 。

        int[][] chessArr1 = new int[11][11];

        // 2、在棋盘上，黑子与白子各自下了一个棋
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // 3、输出原始数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.print(data+"\t"); // %d 表示输出int类型
            }
            System.out.println();
        }

        // 4、获取原始数组中的有效元素个数
        int sum = sparseArray.getDocumentCount(chessArr1);
        // 5、把原始数组转换为稀疏数组
        int[][] sparseArray1 = sparseArray.toSparseArray(chessArr1, sum);
        // 6、写入硬盘
        sparseArray.write(sparseArray1);
        // 7、从磁盘中读取
        int[][] sparseArray2 = sparseArray.reader();
        // 8、将稀疏数组转换为原始数组
        sparseArray.toOriginalArray(sparseArray2);
    }

    /**
     *  获取原数组的元素个数
     * @param arr
     * @return
     */
    public int getDocumentCount(int[][] arr){
        // 4、遍历原始的数组，得到非零个数，即有效元素个数，即棋子数
        // 记录有效个数
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0){
                    sum++;
                }
            }
        }

        return sum;
    }

    /**
     *  将原始数组转换为稀疏数组
     * @param arr
     * @param sum
     */
    public int[][] toSparseArray(int[][] arr ,int sum){
        int[][] sparseArr1 = new int[sum+1][3];
        sparseArr1[0][0] = arr.length;
        sparseArr1[0][1] = arr[0].length;
        sparseArr1[0][2] = sum;

        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0){
                    sparseArr1[count][0] = i;
                    sparseArr1[count][1] = j;
                    sparseArr1[count][2] = arr[i][j];
                    count++;
                }
            }
        }
        System.out.println("稀疏数组，转换成功，如下");
        for (int[] row : sparseArr1) {
            for (int data : row) {
                System.out.print(data+"\t"); // %d 表示输出int类型
            }
            System.out.println();
        }

        return sparseArr1;
    }

    /**
     *  将稀疏数组写到硬盘中
     * @param arr
     */
    public void write(int[][] arr){
        OutputStream os = null;
        OutputStreamWriter write = null;
        try {
            os = new FileOutputStream(new File("sparse.txt"));

            write = new OutputStreamWriter(os, StandardCharsets.UTF_8);

            for (int i = 0; i < arr.length; i++) {
                write.append(arr[i][0]+"\t"+arr[i][1]+"\t"+arr[i][2]+"\t");
            }
            System.out.println("写入成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (write != null) {
                    write.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  将稀释数组从硬盘中读取到内存中
     */
    public int[][] reader(){

        FileInputStream fis = null;
        InputStreamReader reader = null;
        int[][] arr = null;
        try {
            fis = new FileInputStream("sparse.txt");

            reader = new InputStreamReader(fis, StandardCharsets.UTF_8);

            StringBuffer sb = new StringBuffer();

            while (reader.ready()){
                sb.append((char) reader.read());
            }

            String[] str = sb.toString().split("\t");

            arr = new int[str.length/3][3];

            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = Integer.parseInt(str[count]);
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("读取成功！！！");
            return arr;
        }
    }

    /**
     *  将稀疏数组转换为原始数组
     * @param arr
     */
    public void toOriginalArray(int[][] arr){
        int[][] chessArray2 = new int[arr[0][0]][arr[0][1]];

        for (int i = 1; i < arr.length; i++) {
            chessArray2[arr[i][0]][arr[i][1]] = arr[i][2];
        }

        System.out.println("原始数组，转换成功，如下");
        for (int[] row : chessArray2) {
            for (int data : row) {
                System.out.print(data+"\t"); // %d 表示输出int类型
            }
            System.out.println();
        }
    }
}
