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
        char[] result = new char[8];
         for (int i = 0; i < 8; i++) {
            result[i] = a[IP[i] - 1];
        }
        return result;
    }

    private void nonIPBox(char[] a){
        char[] result = new char[8];
        for (int i = 0; i < 8; i++) {
            result[i] = a[IP[i] - 1];
        }
        return result;
    }

    private void EPBox(char[] a){
        char[] result = new char[8];
        for (int i = 0; i < 8; i++) {
            result[i] = a[EP[i] - 1];
        }
        return result;
    }

    private void SPBox(char[] a){
        char[] result = new char[4];
        // Split the 8-bit input into two 4-bit parts
        char[] left = Arrays.copyOfRange(input, 0, 4);
        char[] right = Arrays.copyOfRange(input, 4, 8);

        // Perform S-box substitution for S0 and S1
        int s0Row = Integer.parseInt("" + left[0] + left[3], 2);
        int s0Col = Integer.parseInt("" + left[1] + left[2], 2);
        int s1Row = Integer.parseInt("" + right[0] + right[3], 2);
        int s1Col = Integer.parseInt("" + right[1] + right[2], 2);

        int s0Output = S0[s0Row][s0Col];
        int s1Output = S1[s1Row][s1Col];

        // Convert S-box outputs to binary strings
        String s0OutputBinary = String.format("%2s", Integer.toBinaryString(s0Output)).replace(' ', '0');
        String s1OutputBinary = String.format("%2s", Integer.toBinaryString(s1Output)).replace(' ', '0');

        // Concatenate the S-box outputs
        String spResult = s0OutputBinary + s1OutputBinary;

        // Convert the result to a character array
        for (int i = 0; i < 4; i++) {
            result[i] = spResult.charAt(i);
        }

        return result;
    }

    private char[] SBox1(char[] a) {
        return SPBox(a);
    }

    private char[] SBox2(char[] a) {
        return SPBox(a);
    }

    private char[] Xor(char[] a, char[] b) {
        char[] result = new char[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = (a[i] == b[i]) ? '0' : '1';
        }
        return result;
    }

    public static void main(String[] args) {
        
        // Example usage
        char[] plaintext = "11010110".toCharArray();
        char[][] keys = new char[16][8];
        for (int i = 0; i < 16; i++) {
            keys[i] = "10111011".toCharArray();  // Replace with your actual key values
        }

        S_DES desScheduler = new S_DES(plaintext, keys);

    }


}
