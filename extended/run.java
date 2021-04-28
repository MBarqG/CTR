public class run {
   /*bakeer work*/
   //encrypt the block using the first key
   public static String encryption1(String S){
      int k1[]=new int[]{4,1,3,2,5,7,6,8 };
   String E="";
      for (int i = 0; i < S.length(); i++) {
         E+=S.charAt((k1[i]-1));
      }
      return E;
   }
   //encrypt the block using the secend key
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

   public static void main(String[] args){
      int startofcounter = 69; //key the start of the counter
      String [] words={"I","recently","discovered","I","could","make","fudge","with","just","chocolate","chips,","sweetened","condensed","milk,","vanilla","extract,","and","a","thick","pot","on","slow","heat.","I","tried","it","with","dark","chocolate","chunks","and","I","tried","it","with","semi-sweet","chocolate","chips.","It's","better","with","both","kinds.","It","comes","out","pretty","bad","with","just","the","dark","chocolate.","The","best","add-ins","are","crushed","almonds","and","marshmallows","--","what","you","get","from","that","is","Rocky","Road.","It","takes","about","twenty","minutes","from","start","to","fridge,","and","then","it","takes","about","six","months","to","work","off","the","twenty","pounds","you","gain","from","eating","it.","All","things","in","moderation,","friends.","All","things","in","moderation."};
      System.out.println("time,size");
      for (int  j = 0; j < words.length; j++) {
            long start = System.nanoTime();
            String word=words[j];
            String cypher ="";
            for (int i = 0; i < word.length(); i++) {
               if (startofcounter >= 256) {
                  startofcounter=0;
               }
               String v = XOR(CTRStart((int)word.charAt(i)), encryption2(CTRStart(startofcounter)));
               startofcounter++;
               cypher += from8bitToChar(v);
            }
            long end = System.nanoTime();
            long elapsedTime = end - start;
            System.out.println(elapsedTime +","+ (byte)word.length()*8);
         }
   }
}