package src;

public class AES {
    private String plainText = new String();//初始明文 16-bit
    private String cipherText = new String();//初始密文
    private String[] keys = new String[3];//密钥串
    private int[][] SBox = new int[][]{{9,4,10,11},{13,1,8,5},{6,2,0,3},{12,14,15,7}};
    private int[][] nonSBox = new int[][]{{10,5,9,11},{1,7,8,15},{6,0,2,3},{12,4,13,14}};
    private int[][] cipher = new int[2][2];//明文分解为2*2的十进制位数
    private int[][] plain = new int[2][2];//明文分解为2*2的十进制位数

    public static int[][] mc = new int[][]{{1,4}, {4,1}};
    private static int[][] nonmc = new int[][]{{9,2}, {2,9}};

    private GF2_4 gf24 = new GF2_4();

    //

    //初始化函数时，为函数所需要的明文以及密钥赋值
    //是的 在两个文件当中传递keys和明文 需要一部分牺牲换取模块的相对独立性 int n代表加密还是解密
    public AES(String Text, String[] keyT, int n){
        keys = keyT;
        for(int i=0;i<keys.length;i++){
            //System.out.println("key"+i+" = "+keys[i]);
        }
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
    private void nonAKn(int n){
        int[][] a = StringToDecimalInt(keys[n]);
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++)
            {
                plain[i][j] ^= a[i][j];
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

    private void INS(){
        String s = new String();
        int left, right;
        for(int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                s = Integer.toBinaryString(plain[i][j]); //十进制数变换为String，需要补全为4bit
                while (s.length()<4){
                    s = "0" + s;
                }
                left = two_bit_binaryToDec(s.substring(0,2));
                right = two_bit_binaryToDec(s.substring(2,4));
                plain[i][j] = nonSBox[left][right];
            }
        }
    }

    // 行移位
    private void SR(){
        int n;
        n = cipher[1][1];
        cipher[1][1] = cipher[1][0];
        cipher[1][0] = n;
    }

    private void ISR(){
        int n;
        n = plain[1][1];
        plain[1][1] = plain[1][0];
        plain[1][0] = n;
    }

    public void MC(){
        int[][] res = new int[][]{{0,0},{0,0}};
        int a = 0;
        int b=0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                a = gf24.multiplyGF2_4(mc[i][0], cipher[0][j]);
                b = gf24.multiplyGF2_4(mc[i][1], cipher[1][j]);
                res[i][j] = a ^ b;
            }
        }
        cipher = res;
    }

    public void IMC(){
        int[][] res = new int[][]{{0,0},{0,0}};
        int a = 0;
        int b=0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                a = gf24.multiplyGF2_4(nonmc[i][0], plain[0][j]);
                b = gf24.multiplyGF2_4(nonmc[i][1], plain[1][j]);
                res[i][j] = a ^ b;
            }
        }
        plain = res;
    }

    String resultCipher = new String();

    private void Encrypt(){
        // S
        cipher = StringToDecimalInt(plainText);
        //AK0 轮密钥加
        AKn(0);
        resultCipher = intToBinaryString();
        //System.out.println("AK0: "+resultCipher);
        //NS 半字节替代
        NS();
        resultCipher = intToBinaryString();
        //System.out.println("NS: "+resultCipher);
        //SR 行移位
        SR();
        resultCipher = intToBinaryString();
        //System.out.println("SR: "+resultCipher);
        // MC 列混淆
        MC();
        resultCipher = intToBinaryString();
        //System.out.println("MC: "+resultCipher);
        //AK1
        AKn(1);
        resultCipher = intToBinaryString();
        //System.out.println("AK1："+resultCipher);
        // NS
        NS();
        resultCipher = intToBinaryString();
        //System.out.println("NS: "+resultCipher);
        // SR
        SR();
        resultCipher = intToBinaryString();
        //System.out.println("SR: "+resultCipher);
        // Ak2
        AKn(2);
        resultCipher = intToBinaryString();
        //System.out.println("AK2: "+resultCipher);
        // S 输出16位密文
        resultCipher = intToBinaryString();
        //System.out.println("s: "+resultCipher);
    }

    public String getResultPlain() {
        return resultPlain;
    }

    String resultPlain = new String();

    private void Decrypt(){
        // S
        plain = StringToDecimalInt(cipherText);

        //AK2
        nonAKn(2);
        resultPlain = non_intToBinaryString();
        //System.out.println("nonAK2: "+resultPlain);

        // ISR 逆行移位
        ISR();
        resultPlain = non_intToBinaryString();
        //System.out.println("ISR: "+resultPlain);

        // INS 逆半字节替代
        INS();
        resultPlain = non_intToBinaryString();
        //System.out.println("INS: "+resultPlain);
        // AK1
        nonAKn(1);
        resultPlain = non_intToBinaryString();
        //System.out.println("nonAK1: "+resultPlain);
        // IMC 逆列混淆
        IMC();
        resultPlain = non_intToBinaryString();
        //System.out.println("IMC: "+resultPlain);
        // ISR 逆行移位
        ISR();
        resultPlain = non_intToBinaryString();
        //System.out.println("ISR: "+resultPlain);
        // INS 逆半字节替代
        INS();
        resultPlain = non_intToBinaryString();
        //System.out.println("INS: "+resultPlain);
        // AK0
        nonAKn(0);
        resultPlain = non_intToBinaryString();
        //System.out.println("nonAK0: "+resultPlain);
        // S
        resultPlain = non_intToBinaryString();
        //System.out.println(resultPlain);
    }

    // 将int[][]转换为一个二进制的String
    public String intToBinaryString(){
        StringBuffer sb = new StringBuffer();
        String s = new String();
        int l=0;
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++) {
                l=0;
                s = Integer.toBinaryString(cipher[j][i]);
                l = s.length();
                while (l<4){
                    sb.append("0");
                    l++;
                }
                sb.append(s);
            }
        }
        return new String(sb);
    }

    public String non_intToBinaryString(){
        StringBuffer sb = new StringBuffer();
        String s = new String();
        int l=0;
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++) {
                l=0;
                s = Integer.toBinaryString(plain[j][i]);
                l = s.length();
                while (l<4){
                    sb.append("0");
                    l++;
                }
                sb.append(s);
            }
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


//    public static void main(String[] args) {
//        String[] k = new String[]{"0010110101010101", "1011110011101001", "1010001101001010"};
//        String s = new String();
//        s = "1010"+"0111"+"0100"+"1001";
//        AES aes = new AES(s,k,1);
//        //System.out.println(aes.getResultCipher());
//    }

}
