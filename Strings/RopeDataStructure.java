package Strings;

public class RopeDataStructure {
    int MAX_LEAF_LENGTH = 2;
class Rope {
    Rope left;
    Rope right;
    Rope parent;
    String value="";
    int length=0; //left rope length

    Rope() {

    }
}

    Rope root1;
    public Rope constructRopeDS(String arr, Rope root, Rope parent, int l , int r) {

           Rope  temp = new Rope();
            //temp.left = temp.right = null;
             temp.parent = parent;

           if(r-l <= MAX_LEAF_LENGTH) {
               root = temp;
               root.value = arr.substring(l,r+1);
               root.left = root.right = null;
               root.length = r-l;
               return root;
           }
           else {

               root = temp;
               if(root1 == null)
                   root1 = root;
               root.value = "";
               temp.length = (r-l)/2;
               root = temp;

               int mid = (l+r)/2;
               root.left = constructRopeDS(arr,root.left, root, l, mid);
               root.right = constructRopeDS(arr,root.right, root, mid+1, r);

         }

           return root;

    }

    public void concatenateWord(Rope root1, String newWord) {
        Rope root2 = constructRopeDS(newWord, null, null, 0, newWord.length()-1);

        Rope root = new Rope();
        root.left = root1;
        root.right = root2;
        root.length = getWeight(root1);
        System.out.println(root.length);
    }

    public char index(int index, Rope root) {

         if(root == null)
             return (char)0;
        if(index > root.length && root.right != null) //root.length always contains the length of left half of the tree
        {
            return index(index-root.length, root.right);
        }
        else if(index < root.length && root.left != null) {
            return index(index, root.left);
        }
        else {
            System.out.println(root.length);
            System.out.println(root.left);
            System.out.println(root.right);
           return (char)0 ; //return root.value.charAt(index);
        }
    }

    public int getWeight(Rope root) {
        if(root == null)
            return 0;
        if(root.left == null & root.right == null)
            return root.length;
        return getWeight(root.left) + getWeight(root.right);
    }

    void printString(Rope root1) {

        if (root1 == null)
            return;
        if (root1.left == null && root1.right == null) {
            System.out.println(root1.value);
        return;
    }
        printString(root1.left);
        printString(root1.right);
    }

    public static void main(String[] args) {
        RopeDataStructure ds = new RopeDataStructure();
        String input = "Hi This is geeksforgeeks. ";
        char[] chars = input.toCharArray();
        ds.constructRopeDS(input,null,null, 0, input.length()-1);
        ds.printString(ds.root1);
        //ds.concatenateWord(ds.root1, "likhitha kommineni");
        System.out.println(ds.index(12, ds.root1));
}}
