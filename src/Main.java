package src;

public class Main(String[] args) {
      
        // 示例
        char[] plaintext = "11010110".toCharArray();
        char[][] keys = new char[16][8];
        for (int i = 0; i < 16; i++) {
            keys[i] = "10111011".toCharArray();  // 替换为实际密码
        }

        S_DES desScheduler = new S_DES(plaintext, keys);
  
    }
} 
