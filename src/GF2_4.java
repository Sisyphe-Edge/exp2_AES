package src;

public class GF2_4 {
    // 有限域GF(2^4)的本原多项式（x^4 + x + 1）
    private static final int PRIMITIVE_POLYNOMIAL = 0b10011;

    // 有限域GF(2^4)的乘法 此处输入输出皆是二进制
    public static int multiplyGF2_4(int a, int b) {
        int result = 0;

        while (b > 0) {
            if ((b & 1) == 1) {
                result ^= a;
            }
            a <<= 1;
            if ((a & 0x10) > 0) {
                a ^= PRIMITIVE_POLYNOMIAL;
            }
            b >>= 1;
        }

        return result; // 十进制int数字
    }

//    public static void main(String[] args) {
//        int a = 10; // 对应多项式 x^3 + x
//        int b = 13; // 对应多项式 x^3 + x^2 + 1
//
//        int product = multiplyGF2_4(a, b);
//        System.out.println(product);
//        System.out.println("Product in GF(2^4): " + Integer.toBinaryString(product));
//    }
}
