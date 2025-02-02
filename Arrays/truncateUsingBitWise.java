package Arrays;
class truncateUsingBitWise {
  

        /**
         * @param n
         * @return
         */
        static boolean truncated(int n)
       {
    //to truncate the last 2 digits we need to divide by 100 we need to find the 2 power multiple to do bitwise operations 
    //rightshift i.e for 1234 outut is 12 so right shift by digits for 6 bytes i.e 64 and subtrack 36 

           // Base cases
          int x = (n >> 6);
          int k = (x >> 4);
           System.out.print(n + " is " + (x) + " remainder is " + (x>>4) + " " + ( k << 3));
           return false;
       
         //  Since we need to use bitwise operators, 
         //we get the value of floor(n/8) using n>>3
         // and get value of n%8 using n&7. We need to write above expression in terms of floor(n/8) and n%8. 
       }
    
       // Driver code
       public static void main(String arg[])
       {
    
           // Let us print all multiples of 9 from
           // 0 to 100 using above method
         int n=1275;
               if (truncated(n))
                   System.out.print(n + " ");
       }
   

}
