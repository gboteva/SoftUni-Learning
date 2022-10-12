package utilities;

public class LogFile implements File{
    StringBuilder content;

    public LogFile() {
        this.content = new StringBuilder();
    }


    @Override
    public void write(String line) {
       this.content.append(line).append(System.lineSeparator());
    }

    @Override
    public int getSize() {
       return this.content.chars()
               .filter(Character::isAlphabetic)
               .sum();
    }
}
