package src;

import java.util.Arrays;

public class keyScheduler {

    private String plainKey;//主密钥16-bit
    public String[] keys = new String[6];//密钥串 6 * 8-bit

    private int[][] SBox = new int[][]{{9,4,10,11},{13,1,8,5},{6,2,0,3},{12,14,15,7}};
    private int[][] nonSBox = new int[][]{{10,5,9,11},{1,7,8,15},{6,0,2,3},{12,4,13,714}};


    //构造函数 获得初始密钥，并且调度函数计算密钥串
    public keyScheduler(String a){
        plainKey = a;//8-bit
        keys[0] = plainKey.substring(0, 8);
        keys[1] = plainKey.substring(8, 16);
        int n=1;
        for(int i=2;i<6;i++){
            if(i%2 == 0){
                keys[i] = G(keys[i-2],n);
            }
            else {
                keys[i] = xor(keys[i-2],keys[i-1]);
            }
            n+=0.5;
        }
    }

    //返回密钥串
    public String[] getKeys(){
        return keys;
    }

//    String RCON1 = new String("10000000");
//    String RCON2 = new String("00110000");

    int RCON1 = 128;
    int RCON2 = 48;
    private String G(String str, int n){ //n指示rcon的数
        //RotWord
        StringBuffer sb = new StringBuffer();
        String s = new String();
        String left = str.substring(4,8);
        String right = str.substring(0,4);
        //S盒+异或
        int a;int b;int c;
        a = Integer.parseInt(left.substring(0,2));
        b = Integer.parseInt(left.substring(2,4));

        s=Integer.toBinaryString(c);
        if(s.length()<4){
            for(int i=0;i< 4-sb.length();i++){
                sb.append(0);
            }
            sb.append(s);
        }else{
        sb.append(s);
        }

        a = Integer.parseInt(right.substring(0,2));
        b = Integer.parseInt(right.substring(2,4));
        if(n==1)
            c=RCON1^SBox[a][b];
        else c = RCON2^SBox[a][b];
        s=Integer.toBinaryString(c);
        if(s.length()<4){
            for(int i=0;i< 4-sb.length();i++){
                sb.append(0);
            }
            sb.append(s);
        }else{
            sb.append(s);
        }

        return new String(sb);
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


    //测试代码
      public static void main(String[] args) {

          String a = new String("0010110101010101");
          keyScheduler b = new keyScheduler(a);
          String[] s = new String[]{};
          s = b.getKeys();
          for (int i = 0; i < 6; i++) {
              System.out.println(s[i]);
          }
      }
    //移位操作
}
