package FundamentalsCourse.finalExam.exam01;

import java.util.*;

public class P03_ThePianist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<String>> pieces = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\|");
            String name = input[0];
            String composer = input[1];
            String key = input[2];
            pieces.put(name, new ArrayList<>());
            pieces.get(name).add(composer);
            pieces.get(name).add(key);
        }

        String command = scanner.nextLine();
        while (!command.equals("Stop")) {
            String[] commandArr = command.split("\\|");
            String action = commandArr[0];
            String piece = commandArr[1];
            switch (action) {
                case "Add":
                    String composer = commandArr[2];
                    String key = commandArr[3];
                    if (pieces.get(piece)==null){
                        pieces.put(piece, new ArrayList<>());
                        pieces.get(piece).add(composer);
                        pieces.get(piece).add(key);
                        System.out.printf("%s by %s in %s added to the collection!%n", piece, composer, key);
                    }else {
                        System.out.printf("%s is already in the collection!%n", piece);
                    }
                    break;
                case "Remove":
                        if (pieces.get(piece)!=null){
                            pieces.remove(piece);
                            System.out.printf("Successfully removed %s!%n", piece);
                        }else {
                            System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                        }
                    break;
                case "ChangeKey":
                    String newKey = commandArr[2];
                    if (pieces.get(piece)!=null){
                        pieces.get(piece).set(1,newKey);
                        System.out.printf("Changed the key of %s to %s!%n", piece, newKey);
                    }else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                    }
                    break;
            }

            command = scanner.nextLine();
        }
        pieces.entrySet().forEach(p-> System.out.printf("%s -> Composer: %s, Key: %s%n", p.getKey(), p.getValue().get(0), p.getValue().get(1)));

    }
}
