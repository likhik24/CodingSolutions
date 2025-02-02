package Arrays;

public class isNumDivisibleBy9 {
   
        static boolean isDivBy9(int n)
       {
    
           // Base cases
           if (n == 0 || n == 9)
               return true;
           if (n < 9)
               return false;
    
           // If n is greater than 9, then
           // recur for [floor(n/9) - n%8]
           System.out.print(n + " is " + (n >> 3));
           System.out.println(); //1011 0101 0010 0001
           return isDivBy9((int)(n >> 3) - (int)(n & 7));
         //  Since we need to use bitwise operators, 
         //we get the value of floor(n/8) using n>>3
         // and get value of n%8 using n&7. We need to write above expression in terms of floor(n/8) and n%8. 
       }
    
       // Driver code
       public static void main(String arg[])
       {
    
           // Let us print all multiples of 9 from
           // 0 to 100 using above method
           for (int i = 0; i < 100; i++)
               if (isDivBy9(i))
                   System.out.print(i + " ");
       }
   

}
