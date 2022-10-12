package word;

public class CutTransform implements TextTransform{
    private String lastCutText;
    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        lastCutText = text.substring(startIndex, endIndex);
        text.replace(startIndex, endIndex, "");
    }

    public String getLastCutText() {
        return lastCutText;
    }
}
