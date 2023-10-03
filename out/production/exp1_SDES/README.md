
## Incuduction
The first experiment to implement S-DES algorithm.

## File Structure

### src.Main
创建scheduler对象

### scheduler
#### Functions
- Inpput 
- Output 
#### Data
  | DataName    | Type     | Description    |
  |----------|----------------|---------------|
  |plainText      | char[8]  | 初始明文           |
  |cipherText   | char[8]  | 输出的密文          |
|keyOriginal | char[10] | 表示初始的key10-bit |  
  
#### Function
- getPlainText(){}
- setCipherText(bool[]){}

### key scheduler
<img width="473" alt="image" src="https://github.com/Sisyphe-Edge/exp1_SDES/assets/54466829/c48d018b-fcad-41b9-8556-089b76e9b70b" style="justify-content=:center">

#### Data:

  | DataName    | Type         |Access  | Description|
  |--------------| ------------|------- |---------------|
  | key[10]     | char         | private   |初始key值|
  | key[16][8]  | cahr[16][16] |public   |Ki表示每一轮的key值|

#### Function:
**P10** P_{10}=(3,5,2,7,4,10,1,9,8,6)
**P8** P_{8}= _ _ (6,3,7,4,8,5,10,9)
**左右分开 split**
**getKey()** return key[][]




### S-DES scheduler
<img width="383" alt="image" src="https://github.com/Sisyphe-Edge/exp1_SDES/assets/54466829/292a3e4b-b8ec-4a4e-afda-713669a7120a">

#### Data:

| DataName    | Type         | Access  | Description  |
  |--------------| ------------|--------------|---------------|
| plainText | int (8-bit)  | private     | 初始明文int      |
| ciperT         | char [16][8] | private| 每一轮的密文       |
| Key[16][8]     | char[16][16] |public   | Ki表示每一轮的key值 |
#### Function:

**public setPlainText(int[])**

*已删去--格式转换*
	- intToBool(int[],bool[])
	- boolToInt(int[], bool[])  

**EPBox**
**SBox1**
**SBox2**
**SPBox**
**Xor function**
**split function**

  
