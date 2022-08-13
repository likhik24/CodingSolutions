package Arrays;
import java.util.*;
public class FindPrimeNumbers {
public void printPrime(int n) {
    boolean[] arr = new boolean[n+1];

    Arrays.fill(arr, true);
//    Sieve of Eratosthenes
//    Sieve of Eratosthenes is one of the oldest and easiest methods for finding prime numbers up to a given number. It is based on marking as composite all the multiples of a prime. To do so, it starts with 2 as the first prime number and marks all of its multiples (\texttt{4, 6, 8, ...}). Then, it marks the next unmarked number (\texttt{3}) as prime and crosses out all its multiples (\texttt{6, 9, 12, ...}). It does the same for all the other numbers up to n:
//
//
//    However, as we can see, some numbers get crossed several times. In order to avoid it,
//    for each prime p, we can start from p^2 to mark off its multiples.
//    The reason is that once we get to a prime p in the process, all its multiples smaller than p^2 have already been crossed out.
//    For example, let’s imagine that we get to 7. Then, we can see that \texttt{14, 21, 28, 35,} and \texttt{42} have already been marked off by \texttt{2, 3, 2, 5,} and \texttt{3}. As a result, we can begin with \texttt{49}.
//
//    We can write the algorithm in the form of pseudocode as follows:
    //first we set all nums after current i*i which are multiples of i as false;
    // repeat this step for all arr[i] which are true
    for(int i=2; i<=Math.sqrt(n);i++ ) {
        if(arr[i] == true) {
            int j = i*i;
          while(j<n) {
                arr[j] = false;
                j = j+i;
            }
        }
    }
    int count=0;
    for(int i=2;i<n;i++) {
        if(arr[i] == true) {
            System.out.println(i);
            count++;
        }
    }
    System.out.println(count);
}
public static void main(String[] args) {
    FindPrimeNumbers num = new FindPrimeNumbers();
    num.printPrime(100);
}}
