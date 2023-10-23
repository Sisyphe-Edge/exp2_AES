package src;

import java.util.Arrays;

public class keyScheduler {

    private String plainKey;//主密钥16-bit
    private String[] keys = new String[6];//密钥串 6 * 8-bit

    private String[] resultKey = new String[3];
    private int[][] SBox = new int[][]{{9,4,10,11},{13,1,8,5},{6,2,0,3},{12,14,15,7}};

    private GF2_4 gf24 = new GF2_4();

    //构造函数 获得初始密钥，并且调度函数计算密钥串
    public keyScheduler(String a){
        plainKey = a;//8-bit
        keys[0] = plainKey.substring(0, 8);
        keys[1] = plainKey.substring(8, 16);
        int n=1;
        for(int i=2;i<4;i++){
            if(i%2 == 0){
                System.out.println(keys[i-1]);
                keys[i] = xor(G(keys[i-1],n),keys[i-2]);
                System.out.println("xor str1:"+G(keys[i-1],n)+"str2:"+keys[i-2]);
            }
            else {
                keys[i] = xor(keys[i-2],keys[i-1]);
                System.out.println("xor str1:"+keys[i-2]+"str2:"+keys[i-1]);

            }
        }
        n++;
        for(int i=4;i<6;i++){
            if(i%2 == 0){
                System.out.println(keys[i-1]);
                keys[i] = xor(G(keys[i-1],n),keys[i-2]);
                System.out.println("xor str1:"+G(keys[i-1],n)+"str2:"+keys[i-2]);
            }
            else {
                keys[i] = xor(keys[i-2],keys[i-1]);
                System.out.println("xor str1:"+keys[i-2]+"str2:"+keys[i-1]);

            }
        }
    }

    //返回密钥串
    public String[] getKeys(){
        for(int i=0;i<3;i++){
            resultKey[i]=keys[2*i]+keys[2*i+1];
        }
        return resultKey;

    }

    String RCON1 = new String("10000000");
    String RCON2 = new String("00110000");

//    int RCON1 = 128;
//    int RCON2 = 48;
    private String G(String str, int n){ //n指示rcon的数
        //RotWord
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        String s = new String();
        String left = str.substring(4,8);
        String right = str.substring(0,4);
        //S盒+异或
        int a;int b;int c;
        a = two_bit_binaryToDec(left.substring(0,2));
//        System.out.println(left.substring(0,2));
        b = two_bit_binaryToDec(left.substring(2,4));
//        System.out.println("g1函数left:"+a+" g1 function right:"+b);
        c = SBox[a][b];
//        System.out.println("g1函数"+c);
        s=Integer.toBinaryString(c);
        int e=s.length();
        while((4-e)>0){
            sb1.append("0");
            e++;
        }
        sb.append(sb1);
        sb.append(s);

        //s盒异或
        a = two_bit_binaryToDec(right.substring(0,2));
        b = two_bit_binaryToDec(right.substring(2,4));
//        System.out.println("g2函数left:"+a+" g2 function right:"+b);
        c = SBox[a][b];
//        System.out.println("g2函数"+c);
        s=Integer.toBinaryString(c);
        int d=s.length();
        while((4-d)>0){
            sb2.append("0");
            d++;
        }
        sb.append(sb2);
        sb.append(s);

        if(n==1)
            return xor(new String(sb),RCON1);
        return xor(new String(sb), RCON2);
    }

    private static String xor(String str1, String str2){
        StringBuffer sb=new StringBuffer();

        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i)==str2.charAt(i))
                sb.append(0);
            else sb.append(1);
        }
        return new String(sb);
    }

    public int two_bit_binaryToDec(String str){
        int n=0;

        if(Integer.parseInt(str.substring(0,1))==1){
            n += 2;
            if(Integer.parseInt(str.substring(1,2))==1)
                n += 1;
        }
        else{
            if(Integer.parseInt(str.substring(1,2))==1)
                n += 1;
        }
        return n;
    }

    //测试代码
/*      public static void main(String[] args) {

          String a = new String("0010110101010101");
          keyScheduler b = new keyScheduler(a);
          String[] s = new String[]{};
          s = b.getKeys();
          for (int i = 0; i < 6; i++) {
              System.out.println("w"+i+":"+s[i]);
          }
      }*/
    //移位操作
}
