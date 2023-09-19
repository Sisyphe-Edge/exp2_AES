# exp1_SDES
## Incuduction
The first experiment to implement S-DES algorithm.

## File Structure

### scheduler
#### Functions
- Inpput
- Output
####

### key scheduler
<img width="473" alt="image" src="https://github.com/Sisyphe-Edge/exp1_SDES/assets/54466829/c48d018b-fcad-41b9-8556-089b76e9b70b" style="justify-content=:center">

#### Data:
  - Key10: 
  - Key[16][8]：
  | DataName    | Type    | Description|
  | ----------- | ------------ |---------------|
  | Key10     | Boolean       |初始key值|
  | Key[16][8]  | Boolean[16][16]        |Ki表示每一轮的key值|

#### Function:
**P10** P_{10}=(3,5,2,7,4,10,1,9,8,6)
**P8** P_{8}= _ _ (6,3,7,4,8,5,10,9)
**左右分开 split“**

### S-DES scheduler
<img width="383" alt="image" src="https://github.com/Sisyphe-Edge/exp1_SDES/assets/54466829/292a3e4b-b8ec-4a4e-afda-713669a7120a">

- Data:
  - plainText
  - cipherText
  - 

- Function:
  
**EPBox**
**SBox1**
**SBox2**
**SPBox**
**Xor function**
**split function**


  
