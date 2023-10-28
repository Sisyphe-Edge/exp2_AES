package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main{
    private JLabel title;
    private JLabel cipher2;
    private JLabel plain;
    private JTextField cipherD;
    private JTextField keyD;
    private JLabel key2InputField;
    private JButton DE;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l5;
    private JTextField plaintextE;
    private JTextField keyE;
    private JLabel cipherE;
    private JButton PEE;
    private JButton EE;
    private JLabel decrypt;
    private JLabel encrypt;
    private JLabel ciphertext;
    private JLabel pleaseInput10BitLabel;
    private JPanel panel;
    private JLabel l7;
    private JLabel plainD;
    private JTextArea cipherTextShow;
    private JTextArea plainTextShow;

    public String plaintxtE = new String(); //用户输入明文

    public String keytxtE = new String(); //用户输入key

    public String ciphertxtD = new String(); //用户输入密文

    public String keytxtD = new String(); //用户输入key


    public Main() {
//        Random rand = new Random();
//        //初始化IV
//        int num = rand.nextInt(65536);
//        IV = int2_16bitString(num);

        cipherTextShow.setEditable(false);
        plainTextShow.setEditable(false);
        EE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plaintxtE = plaintextE.getText();
                System.out.println("明文是："+plaintxtE);
                keytxtE = keyE.getText();
                System.out.println("16-bit Key = "+keytxtE);
                // 16-bit key
                if(keytxtE.length()==16){
                    keyScheduler key = new keyScheduler(keytxtE);
                    // 如果是01字符串
                    if(formatCheck(plaintxtE)){
                        AES aes = new AES(plaintxtE, key.getKeys(),1);
                        cipherTextShow.setText(aes.getResultCipher());
                    }
                    //如果是其他字符串组成的
                    else{
                        String[] k=new String[3];
                        k=key.getKeys();
                        System.out.println("0 "+k[0]+k[1].length());
                        System.out.println("1 "+k[1]);
                        System.out.println("2 "+k[2]);
                        plaintxtChar = plaintxtE.toCharArray();
                        String[] in=new String[plaintxtChar.length];
                        StringBuffer c=new StringBuffer();//输出String串
                        //字符格式转换
                        for(int i=0;i<plaintxtChar.length;i++){
                            in[i]=formatTransform(plaintxtChar[i]);
                            System.out.println(in[i]);
                        }
                        StringBuffer sb = new StringBuffer();
                        AES a;
                        for(int i=0;i<plaintxtChar.length;i+=2){
                            sb.append(in[i]);sb.append(in[i+1]);
                            System.out.println("sb="+new String(sb).length());

                            a = new AES(new String(sb),key.getKeys(),1);
                            System.out.println("result+"+a.getResultCipher());
                            c.append(stringToChar(a.getResultCipher()));
                            sb.setLength(0);
                        }
                        cipherTextShow.setText(new String(c));
                    }
                }
                // 32-bit key
                else if(keytxtE.length()==32){
                    keyScheduler key1 = new keyScheduler(keytxtE.substring(0,16));
                    keyScheduler key2 = new keyScheduler(keytxtE.substring(16,32));

                    // 如果是01字符串
                    if(formatCheck(plaintxtE)){
                        AES aes1 = new AES(plaintxtE, key1.getKeys(),1);
                        AES aes2 = new AES(aes1.getResultCipher(), key1.getKeys(),1);
                        cipherTextShow.setText(aes2.getResultCipher());
                    }
                    //如果是其他字符串组成的

                }
                // 48-bit key
                else if(keytxtE.length()==48){
                    keyScheduler key1 = new keyScheduler(keytxtE.substring(0,16));
                    keyScheduler key2 = new keyScheduler(keytxtE.substring(16,32));
                    keyScheduler key3 = new keyScheduler(keytxtE.substring(32,48));
                    // 如果是01字符串
                    if(formatCheck(plaintxtE)){
                        AES aes1 = new AES(plaintxtE, key1.getKeys(),1);
                        AES aes2 = new AES(aes1.getResultCipher(), key2.getKeys(),1);
                        AES aes3 = new AES(aes2.getResultCipher(), key3.getKeys(),1);
                        cipherTextShow.setText(aes3.getResultCipher());
                    }
                    //如果是其他字符串组成的
                    else{
                        plaintxtChar = plaintxtE.toCharArray();
                        String[] in=new String[plaintxtChar.length];
                        StringBuffer c=new StringBuffer();//输出String串
                        //字符格式转换
                        for(int i=0;i<plaintxtChar.length;i++){
                            in[i]=formatTransform(plaintxtChar[i]);
                            System.out.println(in[i]);
                        }
                        for(int i=0;i<ciphertxtChar.length;i+=2){
                            AES aes1 = new AES(in[i]+in[i+1],key1.getKeys(),1);
                            AES aes2 = new AES(aes1.getResultCipher(),key2.getKeys(),1);
                            AES aes3 = new AES(aes2.getResultCipher(), key3.getKeys(),1);
//                        System.out.println(sDes.getResultPlain());
                            c.append(stringToChar(aes3.getResultPlain()));
                        }

                        cipherTextShow.setText(new String(c));
                    }
                }

            }
        });
        DE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ciphertxtD = cipherD.getText();
                System.out.println("密文是："+ciphertxtD);
                keytxtD = keyD.getText();
                System.out.println("10-bit Key = "+keytxtD);
                if(keytxtD.length() ==16){
                    keyScheduler key = new keyScheduler(keytxtD);
                    if(formatCheck(ciphertxtD)){
                        AES aes = new AES(ciphertxtD, key.getKeys(),2);
                        plainTextShow.setText(aes.getResultPlain());}
                    //如果是其他字符串组成的
                    else{
                        ciphertxtChar = ciphertxtD.toCharArray();
                        String[] in=new String[ciphertxtChar.length];
                        String s = new String();
                        StringBuffer c=new StringBuffer();//输出String串
                        //字符格式转换
                        for(int i=0;i<ciphertxtChar.length;i++){
                            in[i]=formatTransform(ciphertxtChar[i]);
//                        System.out.println(in[i]);
                        }
                        for(int i=0;i<ciphertxtChar.length;i+=2){
                            AES aes = new AES(in[i]+in[i+1],key.getKeys(),2);
//                        System.out.println(sDes.getResultPlain());
                            c.append(stringToChar(aes.getResultPlain()));
                        }
                        plainTextShow.setText(new String(c));
                    }
                }
                else if(keytxtD.length() == 32){
                    keyScheduler key1 = new keyScheduler(keytxtD.substring(0,16));
                    keyScheduler key2 = new keyScheduler(keytxtD.substring(16,32));
                    if(formatCheck(ciphertxtD)){
                        AES aes1 = new AES(ciphertxtD, key2.getKeys(),2);
                        AES aes2 = new AES(aes1.getResultPlain(), key1.getKeys(),2);
                        plainTextShow.setText(aes2.getResultPlain());}
                    //如果是其他字符串组成的
                    else{
                        ciphertxtChar = ciphertxtD.toCharArray();
                        String[] in=new String[ciphertxtChar.length];
                        String s = new String();
                        StringBuffer c=new StringBuffer();//输出String串
                        //字符格式转换
                        for(int i=0;i<ciphertxtChar.length;i++){
                            in[i]=formatTransform(ciphertxtChar[i]);
//                        System.out.println(in[i]);
                        }
                        for(int i=0;i<ciphertxtChar.length;i+=2){
                            AES aes1 = new AES(in[i]+in[i+1],key1.getKeys(),2);
                            AES aes2 = new AES(aes1.getResultPlain(),key2.getKeys(),2);
//                        System.out.println(sDes.getResultPlain());
                            c.append(stringToChar(aes2.getResultPlain()));
                        }
                        plainTextShow.setText(new String(c));
                    }
                }
                else if(keytxtD.length() == 48){
                    keyScheduler key1 = new keyScheduler(keytxtD.substring(0,16));
                    keyScheduler key2 = new keyScheduler(keytxtD.substring(16,32));
                    keyScheduler key3 = new keyScheduler(keytxtD.substring(32,48));
                    if(formatCheck(ciphertxtD)){
                        AES aes1 = new AES(ciphertxtD, key3.getKeys(),2);
                        AES aes2 = new AES(aes1.getResultPlain(), key2.getKeys(),2);
                        AES aes3 = new AES(aes2.getResultPlain(), key1.getKeys(),2);
                        plainTextShow.setText(aes3.getResultPlain());}
                    //如果是其他字符串组成的

                }

            }
        });
    }

    public static void main(String[] args) {
        Main main = new Main();
        JFrame frame = new JFrame("Main");
        frame.setContentPane(main.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
//        main.CBCScheduler();
//        main.meet_in_the_middle("1010101010101010","1001111110011000");
//        main.testExploit();

    }

    char[] plaintxtChar;
    char[] ciphertxtChar;
    public static boolean formatCheck(String str){
        Pattern pattern = Pattern.compile("[0-1]*");
        return  pattern.matcher(str).matches();
    }
    public String formatTransform(char c){
        StringBuffer sb=new StringBuffer();
        int value = Integer.valueOf(c);
        sb.append("0");
        sb.append(Integer.toString(value&255,2));
        return new String(sb);
    }
    public String stringToChar(String str){ //此处resultplain输出是16bit
        System.out.println("输出字符的编码是："+str.length());
        int i=Integer.parseInt(str.substring(0,8),2);
        int j = Integer.parseInt(str.substring(8,16),2);
        System.out.println("输出字符的编码是："+i+j);
        char a = (char) i;
        char b = (char) j;
        String s = new String();
        StringBuffer sb = new StringBuffer();
        sb.append(a);sb.append(b);

        return new String(sb);
    }


    public void meet_in_the_middle(String plain, String cipher){
        System.out.println("\n\nPlaintext = "+plain+"  CipherText = "+cipher+"\n");
        String[] afterkey1 = new String[65535];
        String[] afterkey2 = new String[65535];
        AES aes1;
        AES aes2;
        for(int i=0;i<65535;i++){
            String k = int2_16bitString(i);
            keyScheduler key = new keyScheduler(k);
            aes1 = new AES(plain,key.getKeys(),1);
            aes2 = new AES(cipher,key.getKeys(),2);
            afterkey1[i] = aes1.getResultCipher();
            afterkey2[i] = aes2.getResultPlain();
        }
        int flag =0;
        for (int z=0; z< 65535; z++){
            for (int x=0;x<65535;x++){
                if(afterkey1[z].equals(afterkey2[x])) {
                    System.out.println("key1 = "+int2_16bitString(z)+"  key2 = "+int2_16bitString(x));
                    flag++;
                }
            }
        }
        if(flag==0) System.out.println("\n中间相遇攻击未找到符合条件的密钥");
        else  System.out.println("\n一共有 "+flag+" 对密钥满足条件");
    }
    private String IV = new String();
    private String[] P = new String[3];
    private String[] EP = new String[3];

    private String[] C = new String[3];
    private String[] DC = new String[3];


    public void CBCScheduler(){
        int n;
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("\n请选择: 加密输入1，解密输入2，退出输入3");
            n = sc.nextInt();
            CBC(n);
        }while(n!=3);
        return;
    }


    public void CBC(int n){

        String text = new String("3");
        String key = new String();
        String[] keys = new String[3];

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        System.out.println("请输入密钥：");
        key=sc.nextLine();


        if(n==1){
            System.out.println("请输入明文：");
            text=sc.nextLine();
        }
        else if(n==2){
            System.out.println("请输入密文：");
            text=sc2.nextLine();
        }
        //获得密钥
        keyScheduler ks = new keyScheduler(key);
        keys = ks.getKeys();
        AES aes;

        int length = text.length();
        int i;int j;

            if(n==1){
                i=0;j=0;
                System.out.println("加密后的密文是：");
                while (i<length){
                    P[j]=text.substring(i,i+16);
                    // S-AES加密
                    aes = new AES(P[j], keys, 1);
                    EP[j] = aes.getResultCipher();
                    i+=16;j++;
                }
                for(int k=0;k<3;k++){
                    if(k==0){
                        EP[k] = xor(IV, EP[k]);
                    }
                    else
                        EP[k] = xor(EP[k-1], EP[k]);
                    System.out.print(EP[k]+" ");
                }
                System.out.println(" ");
            }
            else{
                System.out.println("加密后的明文是：");
                i=0;j=0;
                while (i<length){
                    C[j]=text.substring(i,i+16);
                    i+=16;j++;
                }
                for(int k=2;k>=0;k--){
                    if(k==0){
                        DC[k] = xor(IV, C[k]);
                    }
                    else DC[k] = xor(C[k-1], C[k]);
                    System.out.print(C[k]+" ");
                }
                while (i<length){
                    aes = new AES(DC[j], keys, 2);
                    DC[j] = aes.getResultPlain();
                    i+=16;j++;
                }
                System.out.println(" ");

            }

    }
    private static String xor(String str, String key){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==key.charAt(i))
                sb.append("0");
            else sb.append("1");
        }
        return new String(sb);
    }
    public String int2_16bitString(int n){
        String str = new String();
        StringBuffer sb = new StringBuffer();
        str = Integer.toBinaryString(n);
        int l = str.length();
        while (l<16){
            l++;
            sb.append(0);
        }
        sb.append(str);
//        System.out.println(new String(sb).length());
        return new String(sb);
    }

}
