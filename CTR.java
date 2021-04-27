import java.io.*;

public class CTR {
   /*bakeer work*/
   public static String encryption1(String S){
      int k1[]=new int[]{4,1,3,2,5,7,6,8 };
   String E="";
      for (int i = 0; i < S.length(); i++) {
         E+=S.charAt((k1[i]-1));
      }
      return E;
   }
   public static String encryption2(String S){
      int k2[]=new int[]{2,1,3,6,7,4,8,5};
      S=encryption1(S);
      String E="";
      for (int i = 0; i < S.length(); i++) {
         E+=S.charAt((k2[i]-1));
      }
      return E;
   }

   /*barq work*/
   //turn from 8 bit to char
   public static char from8bitToChar(String s){
   int value=0;
   int power=1;
    for (int i = s.length()-1; i >= 0 ; i--) {
      if (s.charAt(i)=='1'){
         value+=power;
      }
      power*=2;
    }
    return (char)value;
   }

   //the XOR function
   public static String XOR(String pain,String count){
      String prod= new String();
      for (int i = 0; i < count.length(); i++) {
         if (pain.charAt(i)=='0'&&count.charAt(i)=='0') {
            prod+='0';
         }
         else if (pain.charAt(i)=='1'&&count.charAt(i)=='1') {
            prod+='0';
         }
         else if (pain.charAt(i)=='0'&&count.charAt(i)=='1') {
            prod+='1';
         }
         else if (pain.charAt(i)=='1'&&count.charAt(i)=='0') {
            prod+='1';
         }
      }
      return prod;
   }

// turn the counter to binary form string from 8 bit
   public static String CTRStart(int dec){
      String result= "00000000";
      int i=result.length()-1;
      while(dec!=0)
      {
        char a[]=result.toCharArray();
        a[i--]= String.valueOf(dec%2).charAt(0);
        result=new String(a);
        dec=dec/2;  
      }
      return  result;
   }


   public static void main(String[] args) throws FileNotFoundException {
      int startofcounter = 69; //key the start of the counter
      String word ="B";
      String cypher ="";
      long start = System.currentTimeMillis();
      for (int i = 0; i < word.length(); i++) {
         String v = XOR(CTRStart((int)word.charAt(i)), encryption2(CTRStart(startofcounter)));
         startofcounter++;
         cypher += from8bitToChar(v);
      }
      long end = System.currentTimeMillis();
      long elapsedTime = end - start;
      System.out.println("cypher txt ="+cypher);
      System.out.println("time to run the enctyption = "+ elapsedTime +" Milliseconds ,side of input = " + (byte)word.length()*8 +" bits");
   }
}