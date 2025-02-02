package Graph;
import java.util.*;

class FindAliasOfSubstituion {
    HashMap<String, String> aliasSubstitutionMap;
    HashMap<String, ArrayList<String>> substitutionDependencyMap = new HashMap<>();
    HashMap<String, ArrayList<String>> dependencyAdjListMap = new HashMap<>();
    HashMap<String, Integer> indegreeMap = new HashMap<>();
    public String findSubstitution(String alias) throws Exception {
        if(!this.aliasSubstitutionMap.containsKey(alias))
         throw new Exception("No alias found");
        constructDependencyMap(aliasSubstitutionMap);
        ArrayList<String> getSubstitutionsOfInput = substitutionDependencyMap.getOrDefault(alias, new ArrayList<>());

        if(getSubstitutionsOfInput.size() == 0)
        return aliasSubstitutionMap.get(alias);
        Queue<String> queue = new LinkedList<>();
        for(Map.Entry<String, Integer> mapEntr: indegreeMap.entrySet()) {
            String key = mapEntr.getKey();
            int preReqs = mapEntr.getValue();
            if(preReqs == 0)
              queue.add(key);
        }

        while(!queue.isEmpty()) {

            String currAlias = queue.poll();
            ArrayList<String> adjNeighbors = dependencyAdjListMap.getOrDefault(currAlias, new ArrayList());
            for(String neighbor: adjNeighbors) {
                indegreeMap.put(neighbor, indegreeMap.getOrDefault(neighbor, 0)-1);
                String neighborSubstitute = aliasSubstitutionMap.get(neighbor).replaceAll( currAlias, aliasSubstitutionMap.get(currAlias));
                    aliasSubstitutionMap.put(neighbor, neighborSubstitute);
                if(indegreeMap.getOrDefault(neighbor,0) == 0)
                {
                
                    
                    queue.add(neighbor);
                }
            }
        }
        
        
        if(indegreeMap.get(alias) == 0) {
            String output = aliasSubstitutionMap.get(alias).replaceAll("{", "");
            output = output.replaceAll("}", "");

         return output;
        }
        else
        throw new Exception("Not able to decode the alias found" + aliasSubstitutionMap.get(alias));


      
    }

    public void constructDependencyMap(HashMap<String, String> aliasSubstitutionMap) {
        for(Map.Entry<String, String> mapEntry: aliasSubstitutionMap.entrySet()) {
            String input = mapEntry.getValue();
            int lastCharIndex = input.lastIndexOf("}");
            int firstIndexOfSubstitution = input.indexOf("{");
            substitutionDependencyMap.putIfAbsent(mapEntry.getKey(), new ArrayList());
            indegreeMap.putIfAbsent(mapEntry.getKey(),0);
            if(firstIndexOfSubstitution < 0) continue;

            //String trimedInput = input.substring(firstIndexOfSubstitution+1, lastCharIndex);
            
            while(firstIndexOfSubstitution < input.length() && firstIndexOfSubstitution >= 0 && input.length() > 1) 
            {
               
                int currAliasEnding = input.indexOf("}");
                if(currAliasEnding < firstIndexOfSubstitution+1)
               break;
                String currAlias = input.substring(firstIndexOfSubstitution+1,currAliasEnding);
                substitutionDependencyMap.get(mapEntry.getKey()).add(currAlias);
                firstIndexOfSubstitution = input.substring(currAliasEnding+1).indexOf("{");
                indegreeMap.put(mapEntry.getKey(), indegreeMap.getOrDefault(mapEntry.getKey(), 0)+1);
                dependencyAdjListMap.putIfAbsent(currAlias, new ArrayList());
                dependencyAdjListMap.get(currAlias).add(mapEntry.getKey());
                input = input.substring(currAliasEnding+1); 
           }
        
        }
    }

public static void main(String[] args) throws Exception {
  HashMap<String, String> aliasSubstitutionMap = new HashMap<>();
  aliasSubstitutionMap.put("user2", "likhi");
  aliasSubstitutionMap.put("alias1", "/alias-{administratorname}");
  aliasSubstitutionMap.put("administratorname", "likhitha");
  
  aliasSubstitutionMap.put("user1", "/alias/desktop/{alias1}-{user2}");
  aliasSubstitutionMap.put("admin1", "/alias/downloads/{user1}/{alias1}/{user2}");

  FindAliasOfSubstituion sol = new FindAliasOfSubstituion();
  sol.aliasSubstitutionMap = aliasSubstitutionMap;
  System.out.println(sol.findSubstitution("user1"));
}

}
