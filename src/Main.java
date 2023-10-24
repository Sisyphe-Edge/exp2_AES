package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                keyScheduler key = new keyScheduler(keytxtE);
                AES aes = new AES(plaintxtE, key.getKeys(),1);
                cipherTextShow.setText(aes.getResultCipher());
                //如果是其他字符串组成的
               /* else{
                    plaintxtChar = plaintxtE.toCharArray();
                    String[] in=new String[plaintxtChar.length];
                    StringBuffer c=new StringBuffer();//输出String串
                    //字符格式转换
                    for(int i=0;i<plaintxtChar.length;i++){
                        in[i]=formatTransform(plaintxtChar[i]);
                        System.out.println(in[i]);
                    }
                    for(int i=0;i<plaintxtChar.length;i++){
//                        S_DES sDes = new S_DES(in[i], key.getKeys(), 1);
//                        c[i]=sDes.getResultCipher();
//                        System.out.println(sDes.getResultCipher());
//                        c.append(stringToChar(sDes.getResultCipher()));
                    }
                    cipherTextShow.setText(new String(c));
                }*/
            }
        });
        DE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ciphertxtD = cipherD.getText();
                System.out.println("密文是："+ciphertxtD);
                keytxtD = keyD.getText();
                System.out.println("10-bit Key = "+keytxtD);
                keyScheduler key = new keyScheduler(keytxtD);
                AES aes = new AES(ciphertxtD, key.getKeys(),2);
                plainTextShow.setText(aes.getResultPlain());
                //如果是其他字符串组成的
               /* else{
                    ciphertxtChar = ciphertxtD.toCharArray();
                    String[] in=new String[ciphertxtChar.length];
                    StringBuffer c=new StringBuffer();//输出String串
                    //字符格式转换
                    for(int i=0;i<ciphertxtChar.length;i++){
                        in[i]=formatTransform(ciphertxtChar[i]);
//                        System.out.println(in[i]);
                    }
                    for(int i=0;i<ciphertxtChar.length;i++){
//                        S_DES sDes = new S_DES(in[i], key.getKeys(), 2);
//                        System.out.println(sDes.getResultPlain());
//                        c.append(stringToChar(sDes.getResultPlain()));
                    }
                    plainTextShow.setText(new String(c));
                }*/
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
//        main.testExploit();

    }

/*    char[] plaintxtChar;
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
    public char stringToChar(String str){
        int i=Integer.parseInt(str,2);
        System.out.println("输出字符的编码是："+i);
        char a = (char) i;
        System.out.println("输出字符是："+a);
        return a;
    }*/

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
}
