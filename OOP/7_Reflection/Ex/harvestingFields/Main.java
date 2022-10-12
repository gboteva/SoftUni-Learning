package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Class reflectionClazz = RichSoilLand.class;

        Field[] fields = reflectionClazz.getDeclaredFields();

        String command = scanner.nextLine();

        while (!command.equals("HARVEST")) {
            String searchModifier = command;

            printField(fields, searchModifier);

            command = scanner.nextLine();
        }

    }

    private static void printField(Field[] fields, String searchModifier) {

        switch (searchModifier) {
            case "private":
                for (Field field : fields) {
                    String modifier = Modifier.toString(field.getModifiers());
                    if (modifier.equals("private")) {
                        System.out.printf("%s %s %s%n", modifier, field.getType().getSimpleName(), field.getName());
                    }
                }
                break;
            case "public":
                for (Field field : fields) {
                    String modifier = Modifier.toString(field.getModifiers());
                    if (modifier.equals("public")) {
                        System.out.printf("%s %s %s%n", modifier, field.getType().getSimpleName(), field.getName());
                    }
                }
                break;
            case "protected":
                for (Field field : fields) {
                    String modifier = Modifier.toString(field.getModifiers());
                    if (modifier.equals("protected")) {
                        System.out.printf("%s %s %s%n", modifier, field.getType().getSimpleName(), field.getName());
                    }
                }
                break;
            case "all":
                for (Field field : fields) {
                    System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName());
                }
                break;
        }
    }
}
