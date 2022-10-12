package FundamentalsCourse.AssociativeArrays_7.moreEx;

import java.net.FileNameMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class P04_Snowwhite {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // цвят -> име, сила
        Map<String, LinkedHashMap<String, Integer>> dwarfs =  new LinkedHashMap<>();

        while (!input.equals("Once upon a time")){
            String [] data = input.split(" <:> ");
            String name = data[0];
            String hatColor = data[1];
            int physics = Integer.parseInt(data[2]);

            if (dwarfs.get(hatColor)==null){
                dwarfs.put(hatColor, new LinkedHashMap<>(){{
                    put(name,physics);
                }});
            }else {
                if (dwarfs.get(hatColor).get(name)==null){
                    dwarfs.get(hatColor).put(name, physics);
                }else {
                    int currentPhysics = dwarfs.get(hatColor).get(name);
                    dwarfs.get(hatColor).put(name, Math.max(currentPhysics,physics));
                }
            }

            input = scanner.nextLine();
        }

        Map<String, Integer> print = new LinkedHashMap<>();
        //key->color name count; value -> power

        for(Map.Entry<String, LinkedHashMap<String, Integer>> entry : dwarfs.entrySet()) {
            for(Map.Entry<String, Integer> subEntry : entry.getValue().entrySet()) {
                print.put(entry.getKey() + " " + subEntry.getKey() + " " + entry.getValue().size(), subEntry.getValue());
            }
        }

        print.entrySet().stream().sorted((pair2, pair1) -> {
            int sort = Integer.compare(pair1.getValue(), pair2.getValue()); //по сила
            if (sort == 0) {
                String[] sort1 = pair1.getKey().split(" ");
                String[] sort2 = pair2.getKey().split(" ");
                sort = sort1[2].compareTo(sort2[2]);  //по брой
            }
            return sort;
        }).forEach(pair -> {
            String[] printing = pair.getKey().split(" ");
            System.out.printf("(%s) %s <-> %d\n", printing[0], printing[1], pair.getValue());
        });


    }
}
