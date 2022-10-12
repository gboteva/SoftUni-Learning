package exams.firstExam;

import java.util.*;

public class P01_SantasPresentFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] matArr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int [] magicArr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> materials = new ArrayDeque<>(); //stack
        for (int num : matArr ){
            materials.push(num);
        }

        ArrayDeque<Integer> magicLevel = new ArrayDeque<>(); //queue
        for ( int num : magicArr ){
            magicLevel.offer(num);
        }

        Map<String, Integer> presents = new TreeMap<>();
        presents.put("Doll", 0);
        presents.put("Wooden train", 0);
        presents.put("Teddy bear", 0);
        presents.put("Bicycle", 0);

        while (materials.size()>0 && magicLevel.size()>0){
            int material = materials.peek();
            int magicValue = magicLevel.peek();
            int result = material*magicValue;

            if (result==150){
                presents.put("Doll", presents.get("Doll") + 1);
                materials.pop();
                magicLevel.poll();
            }else if (result==250){
                presents.put("Wooden train", presents.get("Wooden train") + 1);
                materials.pop();
                magicLevel.poll();
            }else if (result==300){
                presents.put("Teddy bear", presents.get("Teddy bear") + 1);
                materials.pop();
                magicLevel.poll();
            }else if (result==400){
                presents.put("Bicycle", presents.get("Bicycle") + 1);
                materials.pop();
                magicLevel.poll();
            }else {
                if (result<0){
                    int sum = material + magicValue;
                    materials.pop();
                    magicLevel.poll();
                    materials.push(sum);
                }else if (result>0){
                    magicLevel.poll();
                    material = material+15;
                    materials.pop();
                    materials.push(material);
                }else {
                    if (material==0){
                        materials.pop();
                    }
                    if (magicValue==0){
                        magicLevel.poll();
                    }
                }
            }
        }

        if ((presents.get("Doll")>=1 && presents.get("Wooden train")>=1)
                ||(presents.get("Teddy bear")>=1 && presents.get("Bicycle")>=1)){
            System.out.println("The presents are crafted! Merry Christmas!");
        }else {
            System.out.println("No presents this Christmas!");
        }

        if (!materials.isEmpty()){
            System.out.print("Materials left: ");
            String [] arr = stackToString(materials);
            System.out.print(String.join(", ", arr));
            System.out.println();
        }
        if (!magicLevel.isEmpty()){
            System.out.print("Magic left: ");
            String [] arr = queueToString(magicLevel);
            System.out.print(String.join(", ", arr));
            System.out.println();
        }

        for (Map.Entry<String, Integer> entry : presents.entrySet()) {
            if (entry.getValue()!=0){
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }


    }

    public static String[] stackToString(ArrayDeque<Integer> materials){
        String [] materialString = new String[materials.size()];
        for (int i = 0; i < materialString.length; i++) {
            materialString[i] = materials.pop().toString();
        }
        return materialString;
    }

    public static String[] queueToString(ArrayDeque<Integer> magicLevel){
        String [] magicString = new String[magicLevel.size()];
        for (int i = 0; i < magicString.length; i++) {
            magicString[i] = magicLevel.poll().toString();
        }
        return magicString;
    }
}
