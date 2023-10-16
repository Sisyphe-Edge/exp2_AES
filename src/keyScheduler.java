package src;

import java.util.Arrays;

public class keyScheduler {

    private String plainKey;//主密钥16-bit
    public String[] keys = new String[6];//密钥串 6 * 8-bit


    //构造函数 获得初始密钥，并且调度函数计算密钥串
    public keyScheduler(String a){
        plainKey = a;//8-bit
        keys[0] = plainKey.substring(0, 8);
        keys[1] = plainKey.substring(8, 16);
    }

    //返回密钥串
    public String[] getKeys(){
        return keys;
    }

    String RCON1 = new String("10000000");
    String RCON2 = new String("00110000");
    private String G(String str, int n){ //n指示rcon的数
        //RotWord
        StringBuffer sb = new StringBuffer();
        sb.append(str.substring(4,8));
        sb.append(str.substring(0,4));
        String s = new String(str);

        return str;
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

          String a = new String("1111000011110000");
          keyScheduler b = new keyScheduler(a);
          String[] s = new String[]{};
          s = b.getKeys();
          for (int i = 0; i < 2; i++) {
              System.out.println(s[i]);
          }
      }
    //移位操作
}
