package src;

public class AES {
    private String plainText = new String();//初始明文 16-bit
    private String cipherText = new String();//初始密文

    private String[] keys = new String[3];//密钥串
    private int[][] SBox = new int[][]{{9,4,10,11},{13,1,8,5},{6,2,0,3},{12,14,15,7}};
    private int[][] nonSBox = new int[][]{{10,5,9,11},{1,7,8,15},{6,0,2,3},{12,4,13,14}};
    private int[][] cipher = new int[2][2];//明文分解为2*2的十进制位数

    //

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
    // 将int[][]转换为一个二进制的String
    public String intToBinaryString(){
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++)
                 sb.append(Integer.toBinaryString(cipher[j][i]));
        }
        return new String(sb);
    }

    //将String转换为十进制的int[][]
    public int[][] StringToDecimalInt(String str){
        int k=0;
        String s = new String();
        int [][] a = new int[2][2];
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++)
            {
                s = str.substring(k, k+4);
                a[j][i] = Integer.parseInt(s, 2);
                k += 4;
            }
        }
        return a;
    }

    // n = ? 代表调用哪一个密钥
    private void AKn(int n){
        int[][] a = StringToDecimalInt(keys[n]);
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++)
            {
                cipher[i][j] ^= a[i][j];
            }
        }
    }

    // 半字节替代
    private void NS(){
        String s = new String();
        int left, right;
        for(int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                s = Integer.toBinaryString(cipher[i][j]); //十进制数变换为String，需要补全为4bit
                while (s.length()<4){
                    s = "0" + s;
                }
                left = two_bit_binaryToDec(s.substring(0,2));
                right = two_bit_binaryToDec(s.substring(2,4));
                cipher[i][j] = SBox[left][right];
            }
        }
    }

    private void SR(){
        int n;
        n = cipher[1][1];
        cipher[1][1] = cipher[1][0];
        cipher[1][0] = n;
    }


    String resultCipher = new String();

    private void Encrypt(){
        // S
        cipher = StringToDecimalInt(plainText);
        //AK0
        AKn(0);

        //NS
        NS();

        //SR
        SR();

        // MC

        //AK1
        AKn(1);

        // NS
        NS();

        // SR
        SR();

        // Ak2
        AKn(2);

        // S
    }

    public String getResultPlain() {
        return resultPlain;
    }

    String resultPlain = new String();

    private void Decrypt(){

    }


    // 二进制数转换为十进制
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