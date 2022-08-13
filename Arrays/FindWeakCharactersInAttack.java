package Arrays;

//1st solution more intuitive is to sort by attackid and then descending order of defense id using cusotm comparator like
//
// Arrays.sort(properties, new Comparator<>() {
//            public int compare(int[] property1, int[] property2) {
//                if(property1[0] == property2[0])
//                    return Integer.compare(property2[1], property1[1]);
//                return Integer.compare(property1[0], property2[0]);
//            }
//        }); //descending order is because as we read from right of array to find weak characters if (3,1)(3,2) is in order in array then (3,1) will be considered as weak property as 1 < 2 but 3=3 i.e same attack so this is not weak character, so we sort by descending when property[0] values are equal and
//        set defensecharacter = math.max(property[1], defensecharacter) //which will always have highest defense
//this code sorting will take timecomplexity O(NLOGN) and Space complexity O(logn) We only need two variables maxDefense and weakCharacters to solve the problem.
//        Some space will be used for sorting the list. The space complexity of the sorting algorithm depends on the implementation of each programming language. For instance, in Java, the Arrays.sort() for primitives is implemented as a variant of the quicksort algorithm whose space complexity is O(\log N)O(logN).

//int  weakCharacters = 0;
//        for(int i=properties.length-1; i>=0;i--) {
//        if(properties[i][1] < maxDefense) {
//        weakCharacters++;
//
//        }
//        maxDefense = Math.max(properties[i][1], maxDefense);

//OPTIMIZED SOLUTION: group values by attack and store max defense value for each attack , we say a char is weak if attack+1 defense is greater than current attacks defense
//Complexity Analysis
//
//        Here, NN is the number of pairs in the given list properties, and KK is the maximum attack value possible.
//
//        Time complexity: O(N + K)
//
//        The iteration over the pairs to find the maximum defense value for a particular attack value takes O(N)O(N) time. The iteration over the possible value of the attack property takes O(K)O(K) time. The iteration over the properties to count the weak characters takes O(N)O(N) time. Therefore, the total time complexity equals to O(N + K)O(N+K).
//
//        Space complexity: O(K)
//
//        The array maxDefense will be of size KK to store the defense value corresponding to all the attack values.
public class FindWeakCharactersInAttack {
      public int numberOfWeakCharacters(int[][] properties) {
            int maxAttack = 0;
            // Find the maximum atack value
            for (int[] property : properties) {
                int attack = property[0];
                maxAttack = Math.max(maxAttack, attack);
            }

            int maxDefense[] = new int[maxAttack + 2];
            // Store the maximum defense for an attack value
            for (int[] property : properties) {
                int attack = property[0];
                int defense = property[1];

                maxDefense[attack] = Math.max(maxDefense[attack], defense);
            }

            // Store the maximum defense for attack greater than or equal to a value
            for (int i = maxAttack - 1; i >= 0; i--) {
                maxDefense[i] = Math.max(maxDefense[i], maxDefense[i + 1]);
            }

            int weakCharacters = 0;
            for (int[] property : properties) {
                int attack = property[0];
                int defense = property[1];

                // If their is a greater defense for properties with greater attack
                if (defense < maxDefense[attack + 1]) {
                    weakCharacters++;
                }
            }

            return weakCharacters;
        }

}
