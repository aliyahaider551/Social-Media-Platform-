package assign3_group5sec51;

import java.util.*;
/**
 *
 * @author  Group5-Sec51: Lynn Abbidi 1083873, Aliya Haidar 1082079
 */
public class userGraph extends UndirectedGraph<User> {
    private List<User> users;
    private List<Set<User>> adjacencyList;  // List to store connections

    public userGraph() {
        users = new ArrayList<>();
        adjacencyList = new ArrayList<>();
    }

    // Add user to the graph
    public void add(User user) {
        if(user==null){
            System.out.println("User not found");
        }
        if(users.contains(user)){
            System.out.println("User already exists.");
        }
        users.add(user);
        adjacencyList.add(new HashSet<>());  // Initialize with an empty HashSet for connections
    }


    public boolean addEdge(int userIndex1, int userIndex2) {
        User user1 = users.get(userIndex1);
        User user2 = users.get(userIndex2);
        
        if (user1.getBlockedUsers().contains(user2)||user2.getBlockedUsers().contains(user1)){
            System.out.println("Cannot add edge: User is blocked...");
            return false;
        }
        
        adjacencyList.get(userIndex1).add(users.get(userIndex2));
        adjacencyList.get(userIndex2).add(users.get(userIndex1));  // Since it's an undirected graph
        return true;
    }

    // Remove an edge (connection) between two users
    public boolean removeEdge(int userIndex1, int userIndex2) {
        boolean removed1 = adjacencyList.get(userIndex1).remove(users.get(userIndex2)); // Remove from user1's connections
        boolean removed2 = adjacencyList.get(userIndex2).remove(users.get(userIndex1)); // Remove from user2's connections
        return removed1 && removed2;  // Return true if both were removed
    }

    // Get the index of a user in the graph
    public int getIndex(User user) {
        int index = users.indexOf(user);
        if(user==null||index==-1){
        System.out.println("User is not found.");}
        return index;
    }

    // Get all users in the graph
    public List<User> getVertices() {
     if (users == null) {
        return Collections.emptyList();}       
        return users;
    }

    // Suggest friends for a user (based on mutual connections)
    public Set<User> suggestFriends(User loggedinUser) {
        Set<User> suggestions = new HashSet<>();
        Set<User> directConnections = getConnections(loggedinUser);

        // Loop through direct connections to find their connections
        for (User friend : directConnections) {
            Set<User> friendsOfFriend = getConnections(friend);

            for (User potentialFriend : friendsOfFriend) {
                // Suggest if not a direct connection and not the user themselves
                if (!directConnections.contains(potentialFriend) && !potentialFriend.equals(loggedinUser)) {
                    suggestions.add(potentialFriend);
                }
            }
        }

        return suggestions;
    }


    public List<String> showPost(User loggedinUser) {
        List<String> posts = new ArrayList<>();
        int userIndex = getIndex(loggedinUser);

        if (userIndex == -1) {
            System.out.println("User not found.");
            return posts;
        }

        for (User connectedUser : adjacencyList.get(userIndex)) {
            posts.addAll(connectedUser.post);
            System.out.println("Post from user:"+connectedUser.getUsername()+ " , Post: "+connectedUser.post);
        }

        return posts;
    }

    // Get a user's connections
    public Set<User> getConnections(User user) {
        if(user==null || !this.users.contains(user)){
        System.out.println("User not found");}
        return adjacencyList.get(getIndex(user));
    }
}