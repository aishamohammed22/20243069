import java.util.*;

// ===== MODEL =====
class Comment {
    private int id;
    private String author;
    private String text;

    public Comment(int id, String author, String text) {
        this.id = id;
        this.author = author;
        this.text = text;
    }

    public int getId() { return id; }
    public String getAuthor() { return author; }
    public String getText() { return text; }
}

class Post {
    private int id;
    private String title;
    private String content;
    private List<Comment> comments;

    public Post(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public List<Comment> getComments() { return comments; }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}

// ===== VIEW =====
class PostView {
    public void displayPost(Post post) {
        System.out.println("Post ID: " + post.getId());
        System.out.println("Title: " + post.getTitle());
        System.out.println("Content: " + post.getContent());
        System.out.println("Comments:");

        for (Comment c : post.getComments()) {
            System.out.println(" - " + c.getAuthor() + ": " + c.getText());
        }

        System.out.println("----------------------");
    }
}

// ===== CONTROLLER =====
class PostController {
    private List<Post> posts;
    private PostView view;

    public PostController(PostView view) {
        this.posts = new ArrayList<>();
        this.view = view;
    }

    public void addPost(int id, String title, String content) {
        posts.add(new Post(id, title, content));
    }

    public void addCommentToPost(int postId, int commentId, String author, String text) {
        for (Post post : posts) {
            if (post.getId() == postId) {
                post.addComment(new Comment(commentId, author, text));
            }
        }
    }

    public void displayPosts() {
        for (Post post : posts) {
            view.displayPost(post);
        }
    }
}

// ===== MAIN =====
public class Main {
    public static void main(String[] args) {

        PostView view = new PostView();
        PostController controller = new PostController(view);

        controller.addPost(1, "First Post", "Hello World!");
        controller.addPost(2, "Second Post", "MVC is awesome");

        controller.addCommentToPost(1, 1, "Omar", "Nice post!");
        controller.addCommentToPost(1, 2, "Ali", "Thanks for sharing");
        controller.addCommentToPost(2, 3, "Sara", "Great explanation");

        controller.displayPosts();
    }
}