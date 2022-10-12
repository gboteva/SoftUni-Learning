import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        Class clazz = Tracker.class;

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            Author annotation = method.getAnnotation(Author.class);
            if (annotation!=null){
                System.out.println(method.getName() + " " + annotation.name());
            }
        }
    }
}
