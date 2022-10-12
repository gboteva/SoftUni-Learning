package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        Class<BlackBoxInt> clazz = BlackBoxInt.class;
        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Object blackBoxInt  = constructor.newInstance();

        String inputLine = scanner.nextLine();
        Method[] methods = clazz.getDeclaredMethods();
        Field innerValue = clazz.getDeclaredField("innerValue");
        innerValue.setAccessible(true);


        while (!inputLine.equals("END")){
            String command = inputLine.split("_")[0];
            int value = Integer.parseInt(inputLine.split("_")[1]);

            Arrays.stream(methods).filter(m->m.getName().equals(command)).forEach(method -> {
                try {
                    method.setAccessible(true);
                    method.invoke(blackBoxInt,value);
                    System.out.println(innerValue.getInt(blackBoxInt));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });

            inputLine = scanner.nextLine();
        }



    }
}
