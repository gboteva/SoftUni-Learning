package ex_p05_Telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable{
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder builder = new StringBuilder();

        for (String url : urls) {
            if (ValidateUrls(url)){
                builder.append("\n").append("Browsing: ").append(url).append("!");
            } else {
                builder.append("\n").append("Invalid URL!");
            }
        }
        return builder.toString();
    }

    private boolean ValidateUrls(String url) {
        for (int i = 0; i < url.length(); i++) {
            if (Character.isDigit(url.charAt(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public String call() {
        StringBuilder builder = new StringBuilder();

        for (String number : this.numbers) {
            if (ValidateNumber(number)){
                builder.append("\n").append("Calling... ").append(number);
            }else {
                builder.append("\n").append("Invalid number!");
            }

        }
        return builder.toString();
    }

    private boolean ValidateNumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (Character.isLetter(number.charAt(i))){
                return false;
            }
        }
        return  true;
    }
}
