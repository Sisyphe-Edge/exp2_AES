
## Incuduction
The first experiment to implement S-DES algorithm.

## File Structure

### Main
创建scheduler对象

### scheduler
#### Functions
- Inpput 
- Output 
#### Data
  | DataName    | Type    | Description|
  | ----------- | ------------ |---------------|
  |plainText      | int[8]       |初始key值|
  |cipherText   | int[8]       |输出的密文|
|keyOriginal |int[10]|表示初始的key10-bit|  
  
#### Function
- getPlainText(){}
- setCipherText(bool[]){}

### key scheduler
<img width="473" alt="image" src="https://github.com/Sisyphe-Edge/exp1_SDES/assets/54466829/c48d018b-fcad-41b9-8556-089b76e9b70b" style="justify-content=:center">

#### Data:

  | DataName    | Type  |Access  | Description|
  | ----------- | ------------|------- |---------------|
  | key[10]     | Boolean   | private   |初始key值|
  | key[16][8]  | Boolean[16][16]   |public   |Ki表示每一轮的key值|

#### Function:
**P10** P_{10}=(3,5,2,7,4,10,1,9,8,6)
**P8** P_{8}= _ _ (6,3,7,4,8,5,10,9)
**左右分开 split**
**getKey()** return key[][]




### S-DES scheduler
<img width="383" alt="image" src="https://github.com/Sisyphe-Edge/exp1_SDES/assets/54466829/292a3e4b-b8ec-4a4e-afda-713669a7120a">

#### Data:

  | DataName    | Type  | Access  | Description|
  | ----------- | ------------|------- |---------------|
  | plainTextBool     | Boolean[8]  |  private    |初始明文|
  | cipherTextBool  | Boolean[8] | privaate     |最终密文|
  | ciperT|Boolean [16][8] | private|每一轮的密文|
    | Key10     | Boolean   | private   |初始key值|
 | Key[16][8]  | Boolean[16][16]   |public   |Ki表示每一轮的key值|
#### Function:

**public setPlainText(int[])**
**格式转换**
	- intToBool(int[],bool[])
	- boolToInt(int[], bool[])  
**EPBox** 
**SBox1**
**SBox2**
**SPBox**
**Xor function**
**split function**

  
