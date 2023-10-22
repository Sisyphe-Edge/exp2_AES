package src;

public class AES {
    private String plainText = new String();//初始明文
    private String cipherText = new String();//初始密文
    private String[] keys = new String[2];//密钥串
    private int[][] SBox = new int[][]{{9,4,10,11},{13,1,8,5},{6,2,0,3},{12,14,15,7}};
    private int[][] nonSBox = new int[][]{{10,5,9,11},{1,7,8,15},{6,0,2,3},{12,4,13,14}};

    //初始化函数时，为函数所需要的明文以及密钥赋值
    //是的 在两个文件当中传递keys和明文 需要一部分牺牲换取模块的相对独立性 int n代表加密还是解密
    public AES(String Text, String[] keyT, int n){

        keys = keyT;
        if(n==1){
            plainText = Text;
            Encrypt();
        }
        else {
            cipherText = Text;
            Decrypt();
        }

    }


    public String getResultCipher() {
        return resultCipher;
    }

    String resultCipher = new String();

    private void Encrypt(){

    }

    public String getResultPlain() {
        return resultPlain;
    }

    String resultPlain = new String();

    private void Decrypt(){

    }




    //异或操作
    private static String xor(String str, String key){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==key.charAt(i))
                sb.append("0");
            else sb.append("1");
        }
        return new String(sb);
    }


}
