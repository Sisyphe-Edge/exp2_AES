package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                        plaintxtChar = plaintxtE.toCharArray();
                        String[] in=new String[plaintxtChar.length];
                        StringBuffer c=new StringBuffer();//输出String串
                        //字符格式转换
                        for(int i=0;i<plaintxtChar.length;i++){
                            in[i]=formatTransform(plaintxtChar[i]);
                            System.out.println(in[i]);
                        }
                        for(int i=0;i<ciphertxtChar.length;i+=2){
                            AES aes = new AES(in[i]+in[i+1],key.getKeys(),1);
//                        System.out.println(sDes.getResultPlain());
                            c.append(stringToChar(aes.getResultPlain()));
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
                            AES aes2 = new AES(aes1.getResultCipher(), key1.getKeys(),1);
                            cipherTextShow.setText(aes2.getResultCipher());
                            c.append(stringToChar(aes2.getResultPlain()));
                        }
                        cipherTextShow.setText(new String(c));
                    }
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
                            AES aes1 = new AES(in[i]+in[i+1],key3.getKeys(),2);
                            AES aes2 = new AES(aes1.getResultPlain(),key2.getKeys(),2);
                            AES aes3 = new AES(aes2.getResultPlain(),key1.getKeys(),2);
//                        System.out.println(sDes.getResultPlain());
                            c.append(stringToChar(aes3.getResultPlain()));
                        }
                        plainTextShow.setText(new String(c));
                    }
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
//        main.meet_in_the_middle("1010101010101010","1100001110101111");
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
        int i=Integer.parseInt(str.substring(0,8),2);
        int j = Integer.parseInt(str.substring(8,16),2);
        System.out.println("输出字符的编码是："+i+j);
        char a = (char) i;
        char b = (char) j;
        String s = new String();
        s = String.valueOf(a)+ b;
        System.out.println("输出字符是："+s);
        return s;
    }

    public void testExploit(){
        long stime = System.currentTimeMillis();
        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = new Date(Long.parseLong(String.valueOf(timeStamp)));
        String sd1 = sdf1.format(begin);      // 时间戳转换成时间
        System.out.println("\r\n暴力破解开始时间：" + sd1+"\r\n");

        Exploit exploit = new Exploit("11111111","00101011");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date end = new Date(Long.parseLong(String.valueOf(timeStamp)));
        String sd2 = sdf2.format(end);
        System.out.println("\r\n"+"暴力破解结束时间：" + sd2 );
        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("执行时长：%d 毫秒.\r\n", (etime - stime));
    }

    public void closeBased(){
                //寻找一对明密文串，没有key与之对应
        System.out.println("\r\n寻找一对明密文串，没有key与之对应：\r\n");
        String str = new String();
        for(int i=0;i<1024;i++){
            str = String.format("%8s", Integer.toBinaryString(i)).replace(' ','0');
            Exploit exploit = new Exploit(str,"00101011");
        }
    }

    public void meet_in_the_middle(String plain, String cipher){
        String[] afterkey1 = new String[65535];
        String[] afterkey2 = new String[65535];
        String s = new String();
        String k = new String();
        for(int i=0;i<65536;i++){
            k = int2_16bitString(i);
            keyScheduler key = new keyScheduler(k);
            AES aes = new AES(plain,key.getKeys(),1);
            afterkey1[i] = aes.getResultCipher();
        }
        for (int j=0;j<65536;j++){
            k = int2_16bitString(j);
            keyScheduler key = new keyScheduler(k);
            AES aes = new AES(cipher,key.getKeys(),2);
            afterkey2[j] = aes.getResultPlain();
        }
        for (int z=0; z< 65536; z++){
            for (int x=0;x<65536;x++){
                if(afterkey1[z] == afterkey2[x]) {
                    System.out.println("key1 = "+int2_16bitString(z)+"  key2 = "+int2_16bitString(x));
                }
            }
        }
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
        System.out.println(new String(sb).length());
        return new String(sb);
    }
}
