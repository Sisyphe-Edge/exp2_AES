package src;

public class test {
    public static int[][] mc = new int[][]{{1,4}, {4,1}};
    public static int[][] cipher = new int[][]{{6,4},{12,0}};//明文分解为2*2的十进制位数


    /*public static void main(String[] args) {
        int[][] res = new int[][]{{0, 0}, {0, 0}};
        GF2_4 gf24 = new GF2_4();
//        res = cipher;
        //为什么出错 因为 res和cipher指向同一个常量
        int a = 0;
        int b=0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                a = gf24.multiplyGF2_4(mc[i][0], cipher[0][j]);
                b = gf24.multiplyGF2_4(mc[i][1], cipher[1][j]);
                res[i][j] = a ^ b;
//               System.out.println("mc[i][0]:"+mc[i][0]+" res[0][j]:"+res[0][j]+"  a="+a);
//                System.out.println("mc[i][1]:"+mc[i][1]+" res[1][j]:"+res[1][j]+"  b="+b);
                System.out.println("i:"+i+" j:"+j+" "+res[i][j]);
            }
        }
        cipher = res;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println("cipher  i:"+i+" j:"+j+" "+cipher[i][j]);
            }
        }
    }*/
}