

public class Tracker {
    @Author(name = "George")
    public static void printMethodsByAuthor(Class<?> cl) {
        System.out.println(cl.getName());
    }

    @Author(name =  "Galka")
    private static void testAnnotation(){
        System.out.println("test");
    }
}

