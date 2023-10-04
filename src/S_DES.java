package src;

//加密调用encrypt(); 解密调用Decrypt();
public class S_DES {
    private String plainText = new String();//初始明文
    private String cipherText = new String();//初始密文
    private String[] keys = new String[2];//密钥串
    private static int[] IP = new int[] {2, 6, 3, 1, 4, 8, 5, 7};
    private static int[] nonIP = new int[] {4, 1, 3, 5, 7, 2, 8, 6};
    private static int[] EPBox = new int[] {4, 1, 2, 3, 2, 3, 4, 1};
    private static int[] SPBox = new int[] {2, 4, 3, 1};

    private static String[][] SBox1 = new String[][] {
            {"01", "00", "11", "10"},{"11", "10", "01", "00"},
            {"00", "10", "01", "11"},{"11", "01", "00", "10"}};

    private static String[][] SBox2 = new String[][] {
            {"00", "01", "10", "11"},{"10", "11", "01", "00"},
            {"11", "00", "01", "10"},{"10", "01", "00", "11"}};

    //初始化函数时，为函数所需要的明文以及密钥赋值
    //是的 在两个文件当中传递keys和明文 需要一部分牺牲换取模块的相对独立性 int n代表加密还是解密
    public S_DES(String Text, String[] keyT, int n){

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
        String f1 = new String();
        String f2 = new String();
        String left = new String();
        String right = new String();
        String sw = new String();
        //Step.1 IP
        plainText = subtitueE(plainText, IP);
        left = plainText.substring(0,4);
        right = plainText.substring(4,8);
        //Step.2 fk
        f1 = Fk(right,1);
        left = xor(f1,left);
        //Step.3 SW
        sw = right;
        right = left;
        left = sw;
        //Step.4 fk
        f2 = Fk(right, 2);
        left = xor(left, f2);
        plainText = left + right;
        //Step.5 non-IP
        plainText = subtitueE(plainText, nonIP);//此处可以替换为=subtitueE(plainText, nonIP)
        resultCipher=plainText;
        System.out.println("加密后的密文是:"+resultCipher);
    }

    public String getResultPlain() {
        return resultPlain;
    }

    String resultPlain = new String();

    private void Decrypt(){
        String f1 = new String();
        String f2 = new String();
        String left = new String();
        String right = new String();
        String sw = new String();
        //Step.1 IP
        cipherText = subtitueE(cipherText, IP);
        left = cipherText.substring(0,4);
        right = cipherText.substring(4,8);
        //Step.2 fk2
        f1 = DFk(right, 2);
        left = xor(left,f1);
        //Step.3 SW
        sw = right;
        right = left;
        left = sw;
        //Step.4 fk
        f2 = DFk(right, 1);
        left = xor(left, f2);
        resultPlain = left + right;
        //Step.5 non-IP
        resultPlain = subtitueE(resultPlain, nonIP);//此处可以替换为=subtitueE(plainText, nonIP)
        System.out.println("解密后的明文是:"+resultPlain);
    }


    private String Fk(String str, int n){
 /*       String ret=subtitueE(str, EPBox);
        ret = xor(ret, key);
        String ret1 = SBox(ret, 1);
        String ret2 = SBox(ret, 2);
        ret1 = subtitueE(ret1, SPBox);
        return ret1;
       //错误原因：SBox理解错误
        */
        str = subtitueE(str,EPBox);
        if(n==1)
            str = xor(str,keys[0]);//8-bit
        else str = xor(str,keys[1]);//8-bit
//        System.out.println(str);
        String left = new String();
        left=str.substring(0,4);
        String right = new String();
        right=str.substring(4,8);
        String ret = SBox(left,1)+SBox(right,2);
        ret = subtitueE(ret,SPBox);
        return ret;
    }

    private String DFk(String str, int n){
        str = subtitueE(str,EPBox);
        if(n==1)
            str = xor(str,keys[0]);//8-bit
        else str = xor(str,keys[1]);//8-bit
        String left = cipherText.substring(0,4);
        String right = cipherText.substring(4,8);
        String ret = SBox(left,1)+SBox(right,2);
        ret = subtitueE(ret,SPBox);
        return ret;
    }

    //异或操作
    private static String xor(String str, String key){
        StringBuffer sb=new StringBuffer();
       /* char []c=new char[]{};
        c=str.toCharArray();
        char []d=new char[]{};
        c=str.toCharArray();
        if (key=="1") return "1";*/
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==key.charAt(i))
                sb.append("0");
            else sb.append("1");
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

//    SPBox盒的查找，int n代表用哪一个盒处理
    public static String SBox(String str, int n){
        //行坐标
        StringBuffer sb =new StringBuffer();
        sb.append(str.charAt(0));
        sb.append(str.charAt(2));
        String r = new String(sb);
        //列坐标
        StringBuffer sb1 =new StringBuffer();
        sb1.append(str.charAt(1));
        sb1.append(str.charAt(3));
        String c = new String(sb1);
        String result = new String();
        if(n==1){
            //此处进行spbox变化，事实上，2进制的规定是不必要的
            result = SBox1[Integer.parseInt(r,2)][Integer.parseInt(c,2)];
        } else if (n==2) {
            result = SBox2[Integer.parseInt(r,2)][Integer.parseInt(c,2)];
        }

        return result;
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


}
