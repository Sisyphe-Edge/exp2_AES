## Introduction
In this project, we designed a structure to use S-DES encryption which holds 8-bit group length and 10-bit key length.


## Structure - How it works?
### It contains 4 parts.
- keyScheduler.java: keyScheduler class receives a 10-bit initial key string and calculates cipher keys.
- S_DES.java: This class accepts clear text, and identifiers that identify encryption or decryption, and performs operations.
- Exploit.java: This class is used to test brute force cracking
- Main.java: Main class. Scheduling tests and the running of various UI modules, monitoring user operations
- Main.form: a format sheet
![export.png](image%2Fexport.png)



## Examination


### **第1关：基本测试**

   **加密**：输入8bit的明文和10bit的密钥，输出是8bit的密文。
   **解密**：输入8bit的密文和10bit的密钥，输出是8bit的明文。

![8-bitBinary.png](image%2F8-bitBinary.png)



### **第2关：交叉测试**

   **与其他小组交叉测试-- 输出相同，测试成功**
   ![test.png](image%2Ftest.png)




### **第3关：扩展功能**

   **考虑到向实用性扩展**，加密算法的数据输入可以是ASII编码字符串(分组为1 Byte)，对应地输出也可以是ACII字符串。由于ASCII码范围为0~253，而加密后可能为负数，则有一定可能输出乱码。
   **加密**：输入n-byte的明文和10bit的密钥，输出是n-byte的密文。
   **解密**：输入n-byte的密文和n-byte的密钥，输出是n-byte的明文。


![chanllenge3.png](image%2Fchanllenge3.png)



### **第4关：暴力破解**

   **使用多线程暴力破解相同密钥的明文和密文对。**

![1.gif](image%2F1.gif)


   **暴力破解成功**

![exploit.png](image%2Fexploit.png)


   **暴力破解失败**
   
![exploitD.png](image%2FexploitD.png)


![exploitSuc.png](image%2FexploitSuc.png)




### **第5关：封闭测试**

#### 设计代码，随机输入一对明密文对，出现不止一个密钥。

![2.gif](image%2F2.gif)


#### 测试如下：

![chanllenge5.png](image%2Fchanllenge5.png)



## Users' Manual

1. 加密(ENCRYPT部分)

    1. 在“please input ciphertext”中输入8-bit长度的需要加密的原文；

    1. 在“please input 10-bit Key”中输入10-bit长度的密钥；

    1. 点击“confirm”后在“CipherText”中出现生成的密文。

2. 解密(DECRYPT部分)

    1. 在“please input ciphertext”中输入8-bit长度的密文；

    1. 在“please input 10-bit Key”中输入10-bit长度的密钥（此处应确保和原文加密时所使用的密钥一致）；

    1. 点击“confirm”后在“PlainText”中出现生成的原文。

![8-bitBinary.png](image%2F8-bitBinary.png)


### author: Yiming Yan, Xiangyu Ran. CQU.