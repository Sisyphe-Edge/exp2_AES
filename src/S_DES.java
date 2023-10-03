package src;

public class S_DES {
    // public int plainText;
    private char[] plainText;//初始明文
    private String[] keys;//密钥串
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
    public S_DES(char[] pText, char[][] keyT){
//        plainText = new char[8];
//        keys = new char[16][8];
        plainText=pText;
//        keys=keyT;
/*        for(int i=0;i<16;i++){
            System.out.print(keys[i]);
        }
        System.out.print(plainText);*/
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

//    SPBox盒的查找，int n代表用哪一个盒处理
    public static String SPBox(String str, int n){
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

//    private void IPBox(char[] a){
//        char[] result = new char[8];
//         for (int i = 0; i < 8; i++) {
//            result[i] = a[IP[i] - 1];
//        }
//        return result;
//    }
//
//    private void nonIPBox(char[] a){
//        char[] result = new char[8];
//        for (int i = 0; i < 8; i++) {
//            result[i] = a[IP[i] - 1];
//        }
//        return result;
//    }
//
//    private char[] SBox1(char[] a) {
//        return SPBox(a);
//    }
//
//    private char[] SBox2(char[] a) {
//        return SPBox(a);
//    }
//
//    private char[] Xor(char[] a, char[] b) {
//        char[] result = new char[a.length];
//        for (int i = 0; i < a.length; i++) {
//            result[i] = (a[i] == b[i]) ? '0' : '1';
//        }
//        return result;
//    }
    
}
