package Tree;
import java.util.*;

public class DeleteDuplicateFolders {
   /* Due to a bug, there are many duplicate folders in a file system. You are given a 2D array paths, where paths[i] is an array representing an absolute path to the ith folder in the file system.

    For example, ["one", "two", "three"] represents the path "/one/two/three".
    Two folders (not necessarily on the same level) are identical if they contain the same non-empty set of identical subfolders and underlying subfolder structure. The folders do not need to be at the root level to be identical. If two or more folders are identical, then mark the folders as well as all their subfolders.

    For example, folders "/a" and "/b" in the file structure below are identical. They (as well as their subfolders) should all be marked:
            /a
/a/x
/a/x/y
/a/z
/b
/b/x
/b/x/y
/b/z
    However, if the file structure also included the path "/b/w", then the folders "/a" and "/b" would not be identical. Note that "/a/x" and "/b/x" would still be considered identical even with the added folder.
    Once all the identical folders and their subfolders have been marked, the file system will delete all of them. The file system only runs the deletion once, so any folders that become identical after the initial deletion are not deleted.

    Return the 2D array ans containing the paths of the remaining folders after deleting all the marked folders. The paths may be returned in any order.



    Example 1:


    Input: paths = [["a"],["c"],["d"],["a","b"],["c","b"],["d","a"]]
    Output: [["d"],["d","a"]]
    Explanation: The file structure is as shown.
    Folders "/a" and "/c" (and their subfolders) are marked for deletion because they both contain an empty
    folder named "b".
    Example 2:


    Input: paths = [["a"],["c"],["a","b"],["c","b"],["a","b","x"],["a","b","x","y"],["w"],["w","y"]]
    Output: [["c"],["c","b"],["a"],["a","b"]] */

       class Folder {
           List<Folder> list;
           HashMap<String, Folder> map;
           String name;
           String key;
           boolean deleted ;

           Folder(String name){
               this.name = name;
               map = new HashMap<>();
               list = new ArrayList<>();
               key = "";
               deleted = false;
           }
       }
       Folder root = new Folder("");
       HashMap<String, Integer> keyCountMap;
       public void addPaths(List<String> path) {
           Folder current = root;
           for(String pat: path) {
               if (!current.map.containsKey(pat)) {
                   current.map.putIfAbsent(pat, new Folder(pat));
                   current.list.add(current.map.get(pat));

               }
               current = current.map.get(pat);
           }
       }
       List<List<String>> result = new ArrayList<>();
       public List<List<String>> deleteDuplicateFolder(List<List<String>> paths){
           keyCountMap = new HashMap<>();
           for(List<String> subpaths: paths)
               addPaths(subpaths);
           for(Folder f: root.list) {
               generateKey(f);
           }
           for(Folder f:root.list)
               deleteDuplicates(f);
           for (List<String> path : paths){
               if (isValid(path))
                   result.add(path);
           }

           return result;
       }

       private boolean isValid(List<String> path){
           Folder current = root;

           for (String f : path){
               current = current.map.get(f);

               if (current.deleted)
                   return false;
           }

           return true;
       }

       private void deleteDuplicates(Folder f) {
           if(f.list.size() > 0 && keyCountMap.get(f.key) > 1) {

               f.deleted = true;
               return ;
           }

           for(Folder sb: f.list)
               deleteDuplicates(sb);
       }

       public String generateKey(Folder fold) {
           if(fold.list.size() == 0)
               return  "";
           StringBuilder sb = new StringBuilder();
           // sort to order matches
           Collections.sort(fold.list, (a, b) -> a.name.compareTo(b.name));

           for(Folder sub: fold.list) {
               sb.append("(");
               sb.append(sub.name + generateKey(sub));
               sb.append(")");
           }
           keyCountMap.put(String.valueOf(sb), keyCountMap.getOrDefault(String.valueOf(sb),0)+1);
           fold.key = String.valueOf(sb);
           return String.valueOf(sb);
       }

}
