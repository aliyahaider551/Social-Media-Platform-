# Social Media Platform (Java)

This **Social Media Platform** project is a Java-based application that enables users to create profiles, connect with other users, post updates, and manage their social interactions. It uses graph data structures to manage user relationships and provides features for exploring connections, suggesting friends, and managing posts and notifications.

## Features

1. **User Management**:
   - Register new users with a name, username, and password.
   - Login functionality with username and password validation.

2. **Social Connections**:
   - Connect with other users and manage connections.
   - Block and unblock users.
   - View connections for a specific user.
   - Suggest friends based on mutual connections.

3. **Posts and Notifications**:
   - Add and view posts from connected users.
   - Receive notifications for new connections.

4. **Advanced Graph Features**:
   - Represent user relationships as an undirected graph.
   - Suggest friends using graph traversal.
   - Display all users and their connections.

5. **Admin and User-Friendly Features**:
   - Display all users in the network.
   - Easily manage connections and posts.

---

## Project Structure

### Classes and Key Responsibilities

1. **`AbstractGraph<V>`**:
   - Abstract class representing a generic graph.
   - **Key Methods**:
     - `addVertex(V v)`: Add a vertex to the graph.
     - `addEdge(int u, int v)`: Add an edge between two vertices.
     - `dfs(int v)`: Perform Depth-First Search starting from a vertex.
     - `bfs(int v)`: Perform Breadth-First Search starting from a vertex.

2. **`DirectedGraph<V>`**:
   - Subclass of `AbstractGraph` for directed graphs.

3. **`UndirectedGraph<V>`**:
   - Subclass of `AbstractGraph` for undirected graphs.

4. **`User`**:
   - Represents a user in the social network.
   - **Attributes**:
     - `name`, `username`, `password`: User's personal details.
     - `Connections`: Set of user's direct connections.
     - `blockedUsers`: Set of users blocked by the user.
     - `post`: Stack of the user's posts.
   - **Key Methods**:
     - `addConnections(User user)`: Add a user to connections.
     - `blockUser(User user)`: Block a user.
     - `unblockUser(User user)`: Unblock a user.
     - `addPost()`: Add a new post.

5. **`userGraph`**:
   - Subclass of `UndirectedGraph` for managing user connections.
   - **Key Methods**:
     - `add(User user)`: Add a user to the graph.
     - `addEdge(int u, int v)`: Connect two users.
     - `removeEdge(int u, int v)`: Remove a connection between two users.
     - `suggestFriends(User user)`: Suggest friends based on mutual connections.
     - `showPost(User user)`: Display posts from a user's connections.

6. **`SocialNetworkApp`**:
   - Main class that runs the application and handles user interactions.
   - **Key Functionalities**:
     - Register new users.
     - Login and manage user sessions.
     - Connect or remove connections between users.
     - Add, view, and manage posts.
     - Block and unblock users.

---

## Usage Instructions

1. **Run the Application**:
   - Clone the repository and open the project in your preferred Java IDE.
   - Run the `SocialNetworkApp` class to start the application.

2. **Main Menu**:
   - **1**: Register a new user.
   - **2**: Login with an existing user account.
   - **3**: Exit the application.

3. **Post-Login Menu**:
   - **1**: Logout.
   - **2**: Connect with another user.
   - **3**: View your connections.
   - **4**: Get friend suggestions.
   - **5**: Remove a connection.
   - **6**: Display all users and their connections.
   - **7**: View posts from your connections.
   - **8**: Add a post.
   - **9**: View notifications.
   - **10**: Block a user.
   - **11**: Unblock a user.
   - **0**: Quit the application.

---

## Example Workflow

1. **Register a User**:
   - Input your name, username, and password to create an account.

2. **Login**:
   - Use your username and password to access the platform.

3. **Connect with Users**:
   - Find and connect with other users in the network.

4. **Post and View Updates**:
   - Add posts and view updates from your connections.

5. **Friend Suggestions**:
   - Get friend recommendations based on mutual connections.

6. **Manage Connections**:
   - Block or unblock users as needed.

---

## Data Structures Used

- **Graph**:
  - Users and their connections are represented as vertices and edges.
  - Supports both directed and undirected graph representations.

- **Set**:
  - Used to store user connections and blocked users efficiently.

- **Stack**:
  - Stores user posts, allowing Last-In-First-Out (LIFO) access.

---

## Developers

- **Lynn Abbidi** (ID: 1083873)
- **Aliya Haidar** (ID: 1082079)
