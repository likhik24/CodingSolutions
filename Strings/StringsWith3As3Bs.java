package Strings;

public class StringsWith3As3Bs {
    public String maxAsandBs(int A, int B) {
        //we need to insert max possible As and BS without AAA,BBB substring forming so we can insert atmost 2 as consecutively without b
        StringBuilder res = new StringBuilder();
        String a = "a";
        String b = "b";
        int i=0;
        int j=0;
        if (B > A) {
            a = b;
            b = "a";
            i = B;
            j = A;
        }
        while (i-- > 0) {
            res.append(a);
            if (i > j) {
                res.append(a);
                --i;
            }
            if (j-- > 0) res.append(b);
        }
        return res.toString();
    }
}
