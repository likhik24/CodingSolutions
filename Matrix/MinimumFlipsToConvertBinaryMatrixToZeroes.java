package Matrix;

import java.util.*;

public class MinimumFlipsToConvertBinaryMatrixToZeroes {

            class BFSEntry{

                // tracks state of matrix
                int[][] mat;

                int level;

                // tracks number of zeroes in matrix
                int numZeroes;

                BFSEntry(){

                }

                // copy constructor
                // this is needed because we need to have a copy
                // of matrix in each state
                BFSEntry(BFSEntry other){

                    mat = new int[other.mat.length][other.mat[0].length];

                    // copy values from matrix of other entry to matrix of current entry
                    for(int r = 0; r < other.mat.length; r++){
                        for(int c = 0; c < other.mat[0].length; c++){
                            mat[r][c] = other.mat[r][c];
                        }
                    }

                    // increment level because bfs
                    level = other.level + 1;

                    numZeroes = other.numZeroes;
                }


                // iterates over matrix to produce hashcode  //for hashing mechanism as we have only 0,1 we can increment counter for every cell and multiply cell value with counter which will give us unique hashcode
                //
                public int hashCode(){
                    int hash = 0;
                    int counter = 1;

                    for(int r = 0; r < mat.length; r++){
                        for(int c = 0; c < mat[0].length; c++){
                            hash += mat[r][c] * counter;
                            counter++;
                        }
                    }

                    return hash;
                }

                public boolean equals(Object o){
                    if(o == null)
                        return false;
                    if(o == this)
                        return true;
                    if(!o.getClass().equals(this.getClass()))
                        return false;

                    // check if both matrices are same
                    BFSEntry that = (BFSEntry) o;
                    for(int r = 0; r < mat.length; r++){
                        for(int c = 0; c < mat[0].length; c++){
                            if(mat[r][c] != that.mat[r][c])
                                return false;
                        }
                    }

                    return true;
                }

                public void print(){
                    System.out.println("-----");
                    System.out.println("matrix = ");
                    for(int[] m : mat){
                        System.out.println(Arrays.toString(m));
                    }
                    System.out.println("level = "+level);
                    System.out.println("numZeroes = "+numZeroes);
                }

                // flips a cell and its neighbors
                public void flip(int r, int c){

                    flipCell(r, c);

                    for(int[] d : directions){

                        int nr = r + d[0];
                        int nc = c + d[1];

                        if(isValid(nr, nc))
                            flipCell(nr, nc);

                    }
                }

                // flips individual cells and updates number of zeroes
                private void flipCell(int r, int c){

                    if(mat[r][c] == 0){
                        mat[r][c] = 1;
                        numZeroes--;
                    }

                    else{
                        mat[r][c] = 0;
                        numZeroes++;
                    }

                }

                private boolean isValid(int r, int c){
                    if(r < 0 || c < 0 || r >= mat.length || c >= mat[0].length)
                        return false;
                    return true;
                }

            }

            int[][] directions = new int[][]{{0, 1} ,{0, -1}, {1, 0}, {-1, 0}};

            public int minFlips(int[][] mat) {

                // construct first bfs entry
                BFSEntry start = new BFSEntry();
                start.mat = mat;
                start.level = 0;

                for(int r = 0; r < mat.length; r++){
                    for(int c = 0; c < mat[0].length; c++){
                        if(mat[r][c] == 0)
                            start.numZeroes++;
                    }
                }


                List<BFSEntry> queue = new LinkedList<>();
                queue.add(start);
                HashSet<BFSEntry> visited = new HashSet<>();
                visited.add(start);

                while(!queue.isEmpty()){

                    BFSEntry dequed = queue.remove(0);

                    // if matrix is full of zeroes return level
                    if(dequed.numZeroes == mat.length * mat[0].length)
                        return dequed.level;

                    // iterate over all cells of current bfsentry
                    for(int r = 0; r < dequed.mat.length; r++){
                        for(int c = 0; c < dequed.mat[0].length; c++){

                            // flip each cell and check if the new state
                            // after flipping is already visited or not
                            BFSEntry copy = new BFSEntry(dequed);
                            copy.flip(r, c);

                            // add this state to queue only if we haven't encountered it before
                            // no need to track which cells are flipped
                            // this is because we can flip one cell more than 2 times
                            // so for hashing we only look at the matrix
                            // if matrix are identical then state is visited so skip it
                            if(!visited.contains(copy)){
                                visited.add(copy);
                                queue.add(copy);
                            }

                        }
                    }
                }

                return -1;
            }

        public static void main(String[] args) {
            MinimumFlipsToConvertBinaryMatrixToZeroes flips = new MinimumFlipsToConvertBinaryMatrixToZeroes();
            System.out.println(flips.minFlips(new int[][]{{0,0,1}, {1,0,1}, {1,1,0}}));
        }
}
