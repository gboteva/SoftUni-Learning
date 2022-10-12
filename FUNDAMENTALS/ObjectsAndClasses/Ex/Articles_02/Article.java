package beforeCourse.objectsAndClasses_6.ex.Articles_02;

public class Article {
    private String title;
    private String content;
    private String author;

    public Article(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String setContent(String newContent) {
        return this.content = newContent;
    }

    public String setAuthor(String newAuthor){
        return this.author=newAuthor;
    }

    public String setTitle (String newTitle){
        return this.title=newTitle;
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %s", this.title,this.content, this.author);
    }
}
