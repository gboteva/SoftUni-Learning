package beforeCourse.objectsAndClasses_6.ex.ArticleV02_04;

public class Article {
    private String title;
    private String content;
    private String author;

    public Article (String title, String content, String author){
        this.title =title;
        this.content=content;
        this.author=author;
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %s", this.title, this.content, this.author);
    }
}
