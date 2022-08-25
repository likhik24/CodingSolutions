package Strings;

//Rabin Karp
public class StringPatternMatching {

int prime = 101;

//generate hashvalue of the pattern and while iterating through text we try to find texthash to be equal to pattern hash , only then we iterate through text and pattern one by one
    // time complexity : O(m+n) WORST CASE O(MN)
public void matchingString(String text, String pattern) {
    int hashvalue = 1;
    int d = 256; //to support 256 ascii characters
    for(int i=0;i<pattern.length()-1;i++) {
        hashvalue = (hashvalue * d) % prime;
    }
    int patternhash = 0;
    int texthash = 0;

    for(int i=0;i<pattern.length();i++) {
       patternhash = (d*patternhash + (pattern.charAt(i)))%prime;
       texthash =  (d*texthash + (text.charAt(i)))%prime;
    }
    int j=0;
    for(int i=0; i<=text.length()-pattern.length();i++) {
        if(patternhash == texthash) {
            /* Check for characters one by one */
            for (j = 0; j < pattern.length(); j++)
            {
                if (text.charAt(i+j) != pattern.charAt(j))
                    break;
            }

            // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
            if (j == pattern.length())
                System.out.println("Pattern found at index " + i);
        }
        if(i < text.length()-pattern.length()) {
         texthash = (d*(texthash-(int)text.charAt(i)*hashvalue) + (int)text.charAt(i+pattern.length()))%prime;
         if(texthash < 0)
             texthash += prime;
        }

    }
}
    public static void main(String[] args)
    {
        String txt = "GEEKS FOR GEEKS";
        String pat = "GEEK";

        // A prime number
        int q = 101;
        StringPatternMatching matching = new StringPatternMatching();

        // Function Call
        matching.matchingString( txt, pat);
    }

}
