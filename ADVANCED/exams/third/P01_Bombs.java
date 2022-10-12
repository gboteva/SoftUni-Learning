package exams.third;

import java.util.*;

public class P01_Bombs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> effects = new ArrayDeque<>(); //queue
        int[] numbers = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();

        for (int num : numbers) {
            effects.offer(num);
        }

        String [] moreNumbers = scanner.nextLine().split(", ");
        ArrayDeque<Integer> casings = new ArrayDeque<>(); //stack
        for (String num : moreNumbers) {
            casings.push(Integer.parseInt(num));
        }

        Map<String, Integer> resultMap = new TreeMap<>();
        resultMap.put("Datura Bombs", 0);
        resultMap.put("Cherry Bombs", 0);
        resultMap.put("Smoke Decoy Bombs", 0);

        while (!effects.isEmpty() && !casings.isEmpty()) {
            int bombCas = casings.peek(); //stack
            int bombEff = effects.peek(); //queue
            int result = bombEff + bombCas;
            if (result == 40) {
                int current = resultMap.get("Datura Bombs");
                resultMap.put("Datura Bombs", current + 1);
                effects.poll();
                casings.pop();
            } else if (result == 60) {
                int current = resultMap.get("Cherry Bombs");
                resultMap.put("Cherry Bombs", current + 1);
                effects.poll();
                casings.pop();
            } else if (result == 120) {
                int current = resultMap.get("Smoke Decoy Bombs");
                resultMap.put("Smoke Decoy Bombs", current + 1);
                effects.poll();
                casings.pop();
            } else {
                casings.pop();
                casings.push(bombCas - 5);
            }

            if (resultMap.get("Datura Bombs") >= 3 && resultMap.get("Cherry Bombs") >= 3 && resultMap.get("Smoke Decoy Bombs") >= 3){
                break;
            }

        }

        if (resultMap.get("Datura Bombs") >= 3 && resultMap.get("Cherry Bombs") >= 3 && resultMap.get("Smoke Decoy Bombs") >= 3) {
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }

        List<String> stringEffects = new ArrayList<>();

        if (effects.isEmpty()) {
            System.out.println("Bomb Effects: empty");
        } else {
            while (!effects.isEmpty()) {
                stringEffects.add(String.valueOf(effects.poll()));
            }
            System.out.print("Bomb Effects: ");
            System.out.println(String.join(", ", stringEffects));
        }

        List<String> stringCasings = new ArrayList<>();
        if (casings.isEmpty()){
            System.out.println("Bomb Casings: empty");
        }else {
            while (!casings.isEmpty()) {
                stringCasings.add(String.valueOf(casings.pop()));
            }
            System.out.print("Bomb Casings: ");
            System.out.println(String.join(", ", stringCasings));
        }

        resultMap.entrySet().forEach(e-> System.out.println(e.getKey() + ": " + e.getValue()));




    }
}



