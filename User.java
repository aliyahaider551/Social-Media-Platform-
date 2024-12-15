package assign3_group5sec51;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author Group5-Sec51: Lynn Abbidi 1083873, Aliya Haidar 1082079
 */
public class User {
    public Set<User> Connections;
    private Set<User> blockedUsers;
    private String username;
    private String name;
    private String password;
     Stack<String> post= new Stack<>();
    
    public User(String name, String username, String password){
        this.name=name;
        this.username=username;
        this.password= password;
        this.Connections = new HashSet<>(); 
        this.blockedUsers = new HashSet<>();
    }
    
    public void addPost(){
        System.out.println("Enter post text:");
        Scanner sc= new Scanner(System.in);
        String p= sc.next();
        post.push(p);
        System.out.println("New Post has been added!");

    }

    public void showNotification(){
        if (Connections.isEmpty()) {
            System.out.println("No Notification available.");
            return;
        }
        int notif = 0;
        for (User user : Connections) {
            System.out.println(user.getUsername()+" Connected with you");
            notif++;
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public Set<User> getBlockedUsers(){
        return blockedUsers;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addConnections(User user) {
        if (user ==null){
            System.out.println("User does not exist.");}
        else if(blockedUsers.contains(user)){
            System.out.print("User is blocked");
        }else{
            Connections.add(user);
            System.out.println(user.getUsername()+" has been added to your connections!");
        }
    }
    
    public Set<User> getConnections() {
        return Connections;
    }

        public boolean blockUser(User user) {
        if (Connections.contains(user)) {
            Connections.remove(user); // Remove from connections
        }
        return blockedUsers.add(user); // Add to blocked list
    }

    public boolean unblockUser(User user) {
        return blockedUsers.remove(user);
    }
    

    @Override
    public String toString() {
        return "User{" +", username='" + username + '\'' + ", name='" + name + '\'' + '}';
    }
}
