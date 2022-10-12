package Jar;

public class Main {
    public static void main(String[] args) {
        Jar<String> jar = new Jar<String>();
        jar.add("tree");
        jar.add("lake");
        jar.add("forest");
        jar.add("sky");
        System.out.println(jar.remove());
    }
}
