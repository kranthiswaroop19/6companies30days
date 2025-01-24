import java.util.*;

class ThroneInheritance {
    private String king;                          // The king's name
    private Map<String, List<String>> family;    // Family tree: parent -> list of children
    private Set<String> deaths;                  // Set to track dead people

    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.family = new HashMap<>();
        this.deaths = new HashSet<>();
        this.family.put(kingName, new ArrayList<>()); // Initialize the king's entry
    }

    public void birth(String parentName, String childName) {
        // Add the child to the parent's list of children
        family.putIfAbsent(parentName, new ArrayList<>());
        family.get(parentName).add(childName);
        family.putIfAbsent(childName, new ArrayList<>()); // Initialize child's entry
    }

    public void death(String name) {
        deaths.add(name); // Mark the person as dead
    }

    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order); // Start DFS from the king
        return order;
    }

    private void dfs(String current, List<String> order) {
        if (!deaths.contains(current)) {
            order.add(current); // Add to the inheritance order if the person is not dead
        }
        // Recur for all children of the current person
        if (family.containsKey(current)) {
            for (String child : family.get(current)) {
                dfs(child, order);
            }
        }
    }
}
