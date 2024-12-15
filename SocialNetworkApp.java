package assign3_group5sec51;

import java.util.*;
/**
 *
 * @author Group5-Sec51: Lynn Abbidi 1083873, Aliya Haidar 1082079
 */
public class SocialNetworkApp {
    static User loggedinuser = null;
    static userGraph userGraph = new userGraph();
    public static void main(String[] args) {
        Mainmenu();
    }


    public static void Mainmenu() {
        System.out.println("1. Register: ");
        System.out.println("2. Login: ");
        System.out.println("3. Exit: ");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Your choice: ");
        int input = scanner.nextInt();
        while (input != 3) {
            switch (input) {
                case 1:
                    addUser();
                    break;
                case 2:
                    loginuser();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("1. Register: ");
            System.out.println("2. Login: ");
            System.out.println("3. Exit: ");
            System.out.println("Enter your choice: ");
            input = scanner.nextInt();
        }
    }


    public static void secondaryLogin() {
        int userChoice;
        do {
            displayMenu();
            userChoice = userMenuChoice();
            switch (userChoice) {
                case 1:
                    loggedinuser = null; return;  // Logout
                case 2:
                    addConnection(); break;     // Connect two users
                case 3:
                    displayConnections(); break; // Display connections for a user
                case 4:
                    suggestFriends(); break;    // Suggest friends
                case 5:
                    removeConnection(); break;  // Remove a connection
                case 6:
                    displayAllUsers(); break;   // Display all users and their connections
                case 7:
                    userGraph.showPost(loggedinuser); break;   // Advanced features (can be expanded)
               case 8:
                    loggedinuser.addPost(); break;
               case 9:
                    loggedinuser.showNotification(); break;
               case 10:
                   blockUser(); break;
               case 11:
                   unblockUser(); break;
                default:
                    System.out.println("Thank you for using the Social Network App. Goodbye!");
            }
        } while (userChoice != 0);
    }


    public static void loginuser() {
        System.out.println("Enter Username:");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        User u = findUser(username);
        if (u == null) {
            System.out.println("User doesn't exist.");
            return;
        }
        System.out.println("Enter Password: ");
        String password = sc.next();
        if (u.getPassword().equals(password)) {
            System.out.println("Login successful!");
            loggedinuser = u;
            secondaryLogin();
        } else {
            System.out.println("Incorrect password.");
        }
    }

    // Validate username
    public static boolean isValidUsername(String username) {
        for (User u1 : userGraph.getVertices()) {
            if (username.equals(u1.getUsername())) {
                System.out.println("Username is not valid");
                return false;
            }
        }
        System.out.println("Username is valid");
        return true;
    }

    // Display menu options after login
    public static void displayMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Social Network Connections Graph");
        System.out.println("Welcome " + loggedinuser.getName());
        System.out.println("---------------------------------------------------------");
        System.out.println("1-  Logout");
        System.out.println("2 - Connect two users");
        System.out.println("3 - Display connections for a user");
        System.out.println("4 - Suggest friends");
        System.out.println("5 - Remove a connection");
        System.out.println("6 - Display all users and their connections");
        System.out.println("7 - Show Post");
        System.out.println("8-  Add Post");
        System.out.println("9-  Show Connection Notifications:");
        System.out.println("10- Block a user");
        System.out.println("11- Unblock a user");
        System.out.println("0 - Quit");
        System.out.println("---------------------------------------------------------");
    }


    public static int userMenuChoice() {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Your choice (0 - 9):");
            choice = input.nextInt();
        } while (choice > 11 || choice < 0);
        return choice;
    }


    public static void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter Name: ");
        String name = scanner.next();
        System.out.println("Please Enter your Username: ");
        String username = scanner.next();
        while (!isValidUsername(username)) {
            username = scanner.next();
        }
        System.out.println("Please Enter your password: ");
        String password = scanner.next();
        User user1 = new User(name, username, password);
        userGraph.add(user1);
        System.out.println("User added to the network.");
    }


    public static void addConnection() {
        System.out.println("Enter username to establish connection: ");
        Scanner sc = new Scanner(System.in);
        String u1 = sc.next();
        User u = findUser(u1);
        if (u == null) {
            System.out.println("Invalid Username");
        } else {
            userGraph.addEdge(userGraph.getIndex(loggedinuser), userGraph.getIndex(u));
            loggedinuser.Connections.add(u);
            u.Connections.add(loggedinuser);
            System.out.println("Connection established between " + loggedinuser.getUsername() + " and " + u.getUsername());
        }
    }


    public static User findUser(String username) {
        for (User u1 : userGraph.getVertices()) {
            if (username.equals(u1.getUsername())) {
                return u1;
            }
        }
        return null;
    }


    public static void displayConnections() {
        System.out.println(loggedinuser.getUsername() + "'s Connections: " + loggedinuser.Connections);
    }

    // Suggest friends based on mutual connections
    public static void suggestFriends() {
        Set<User> suggestions = userGraph.suggestFriends(loggedinuser);
        System.out.println("Friend suggestions for " + loggedinuser.getUsername() + ": " + suggestions);
    }


    public static void removeConnection() {
        System.out.println("Enter username to remove connection: ");
        Scanner sc = new Scanner(System.in);
        String u1 = sc.next();
        User u = findUser(u1);
        if (u != null) {
            userGraph.removeEdge(userGraph.getIndex(loggedinuser), userGraph.getIndex(u));  // Remove connection
            System.out.println("Connection removed between " + loggedinuser.getUsername() + " and " + u.getUsername());
        } else {
            System.out.println("User not found.");
        }
    }

    public static void blockUser(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter user to block: ");
        String username = input.next();
        User user = findUser(username);
        
        if(user==null){
            System.out.println("User does not exist");
        }else if(loggedinuser.blockUser(user)){
            System.out.println("Succesffuly blocked "+user.getUsername());
        }else{ System.out.println("Unable to block user, try again.");}
            
    }
    
    public static void unblockUser(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter user to unblock: ");
        String username = input.next();
        User user = findUser(username);
        
        if(user==null){
            System.out.println("User does not exist");
        }else if(loggedinuser.unblockUser(user)){
            System.out.println("Successfully unblocked "+user.getUsername());
        }else{ System.out.println("Unable to block user, try again.");}        
        
    }
    
    public static void displayAllUsers() {
        for (User u : userGraph.getVertices()) {
            System.out.println(u.getUsername() + " -> " + u.Connections);
        }
    }
    
    

}

