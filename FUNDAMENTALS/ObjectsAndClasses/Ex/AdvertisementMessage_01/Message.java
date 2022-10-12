package beforeCourse.objectsAndClasses_6.ex.AdvertisementMessage_01;

public class Message {
    String phrase;
    String event;
    String author;
    String city;

    public Message(String phrase, String event, String author, String city){
        this.phrase=phrase;
        this.event=event;
        this.author=author;
        this.city=city;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s – %s", this.phrase, this.event, this.author, this.city);
    }
}
