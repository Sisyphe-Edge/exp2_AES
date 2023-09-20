package src;

public class S_DES {
    // public int plainText;
    private char[] plainText;//初始明文
    private char[][] keys;//密钥串

    //初始化函数时，为函数所需要的明文以及密钥赋值
    public S_DES(char[] pText, char[][] keyT){
        plainText = new char[8];
        keys = new char[16][8];
        plainText=pText;
        keys=keyT;

/*        for(int i=0;i<16;i++){
            System.out.print(keys[i]);
        }
        System.out.print(plainText);*/
    }

/*    public static void main(String[] args) {
        System.out.println("Hello, World!");
        char[] a=new char[] {'1','0','1','1','1','0','1','1'};
        char[][] keyT=new char[16][8];
        for(int i=0;i<16;i++){
            keyT[i]=new char[] {'1','0','1','1','1','0','1','1'};
        }
        S_DES desScheduler=new S_DES(a,keyT);
    }*/

    private void IPBox(char[] a){

    }

    private void nonIPBox(char[] a){

    }

    private void EPBox(){

    }

    private void SPBox(){

    }

    private  void SBox1(){

    }

    private void SBox2(){

    }

    private void Xor(){

    }



}
