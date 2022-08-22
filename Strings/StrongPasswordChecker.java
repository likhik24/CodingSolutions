package Strings;
/*A password is considered strong if the below conditions are all met:

        It has at least 6 characters and at most 20 characters.
        It contains at least one lowercase letter, at least one uppercase letter, and at least one digit.
        It does not contain three repeating characters in a row (i.e., "...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
        Given a string password, return the minimum number of steps required to make password strong. if password is already strong, return 0.

        In one step, you can:

        Insert one character to password,
        Delete one character from password, or
        Replace one character of password with another character.


        Example 1:

        Input: password = "a"
        Output: 5
        Example 2:

        Input: password = "aA1"
        Output: 3
        Example 3:

        Input: password = "1337C0d3"
        Output: 0 */

public class StrongPasswordChecker {


        public int strongPasswordChecker(String password) {
            //conditions to check atleast 6 chars, at
            char[] chararr = password.toCharArray();
            int[] arr = new int[password.length()]; //to store number of repeats consecutively from this index
            int l=1;
            int u=1;
            int d=1;
            int res = 0;

            for(int i=0;i<chararr.length;) {
                if(Character.isDigit(chararr[i]))
                    d=0;
                if(Character.isLowerCase(chararr[i]))
                    l=0;
                if(Character.isUpperCase(chararr[i]))
                    u=0;
                int j=i;
                while(i<chararr.length && chararr[j] == chararr[i])
                    i++;
                arr[j] = i-j;
            }
            int missingChars = l+u+d;

            if(password.length() < 6) {
                int remLen = Math.max(0, 6-password.length()-missingChars);
                res += remLen+missingChars;
                // we dont need to check for repeating chars as missingchars and remlength will handle those changes as we can insert missing chars at a
                // position to make count < 3 for repeating chars as there can be atmost a match of 5 repeating chars as password.length<5 we can insert missing char which
                // is atleast one  in middle to make password length as 6 so that fixes the consecutive repeating characters proble,
            }
            else {

                int overLen = Math.max(0, password.length()-20); //check number of characters that are greater than 20 that we can use for deleting any repeting characters
                int left_over_length = 0; // after using up overlen num of characters , if we still we have repeating characters we will use replacing chars option to change the characters as it has more power and takes min operation
                res += overLen;
                for(int k=1;k<3;k++) {
                    for(int i=0;i<arr.length && overLen>0;i++) {
                        if(arr[i] < 3 | arr[i]%3 != (k-1)) continue;
                        arr[i] -= Math.min(overLen,k); //this is to remove 1 char, 2 chars based on the value of arr[i] if its multiple of 3 we remove 1 char , if its multiple of 3 +1 we need to delete 2 chars , if its multiple of 3+2 , we need to delete 3 chars, so k is the no of chars to delete
                        overLen -=k;
                    }
                }
                for(int i=0;i<arr.length;i++) {
                    if(arr[i] >= 3 && overLen > 0) {
                        int need = arr[i]-2; // need to remove all chars except 2
                        arr[i] -= overLen;
                        overLen -= need;
                    }
                    if(arr[i] >= 3) left_over_length += arr[i]/3; //this will tell us no of repeating  chars left that are after deleting max chars that are possible to bring length to 20 (example 5/3 = 1, so we can replace 1 character which will fix
                    // repeating 5 characters to repeating 2 chars which is acceptable  )
                }
                res += Math.max(left_over_length, missingChars);

            }
            return res;

            // we can do bfs to only modify least number of chars and return once password is strong
            // to check if its strong , 1st check length is atleast 6 , 1 lowercase, 1 uppercase and 1 digit
            //first remove any invalid chars in substring
        }

}
