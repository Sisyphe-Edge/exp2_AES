package src;

public class Main{
      public static void main(String[] args){
          char[] a={'1','1','0','1','0','1','1','0','0','0'};
          char[] p=new char[]{'1','1','0','1','0','1','1','0'};
          keyScheduler key = new keyScheduler(a);
          String[] b =new String[2];
          b=key.getKeys();
          S_DES sDes = new S_DES(p, b);

      }

} 
