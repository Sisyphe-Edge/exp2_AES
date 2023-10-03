package src;

import java.util.Arrays;

public class keyScheduler {
    private static int[] P10 = new int[] {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
    private static int[] P8 = new int[] {6, 3, 7, 4, 8, 5, 10, 9};

    private static int[] leftShirt1 = new int[] {2, 3, 4, 5, 1};

    private static int[] leftShift2 = new int[] {3, 4, 5, 1, 2};

    private String plainKey;//主密钥
    public String[] keys = new String[16];//密钥串

    //构造函数 获得初始密钥，并且调度函数计算密钥串
    public keyScheduler(char[] a){
        plainKey = String.valueOf(a);
        plainKey = subtitueE(plainKey,P10);
        //左右两部分分别进行移位操作的存储值
        String left = new String();
        String right = new String();
        String result = new String();
    //初始值变换
        left = plainKey.substring(0,5);
        left = subtitueE(left, leftShirt1);
        right = plainKey.substring(5,10);
        right = subtitueE(right, leftShift2);
        result = left+right;
        keys[0]=subtitueE(result,P8);
        for(int i=1;i<16;i++){
            left = subtitueE(left, leftShirt1);
//            System.out.println(left); //测试代码
            right = subtitueE(right, leftShift2);
            result = left+right;
            keys[i]=subtitueE(result,P8);
        }
    }

    //返回密钥串
    private String[] getKeys(){
        return keys;
    }

    private static String xor(String str, String key){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==key.charAt(i))
                sb.append(0);
            else sb.append(1);
        }
        return new String(sb);
    }

    //按照各个box进置换 加密
    private static String subtitueE(String str, int[] P){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<P.length;i++){
            sb.append(str.charAt((P[i])-1));
        }
        return new String(sb);
    }

    //按照各个box进行置换 解密
    private static String subtitueD(String str, int[] P){
        char[] c=new char[str.length()];
        int j=0;
        for(int i=0;i<P.length;i++){
            c[P[i]-1]=str.charAt(j);
            j++;
        }
        return new String(c);
    }

    //测试代码
/*      public static void main(String[] args) {

          char[] a={'1','1','0','1','0','1','1','0','0','0'};
          keyScheduler b=new keyScheduler(a);
          System.out.print(a);
          String[] s= new String[]{};
          s=b.getKeys();
          for(int i=0;i<16;i++){
              System.out.println(s[i]);
          }*/

    //移位操作
}
