package FundamentalsCourse.AssociativeArrays_7.moreEx;

import java.util.*;

public class P05_DragonArmy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        // type - > name->list of states
        Map<String, TreeMap<String, List<Integer>>> dragons = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String [] data = scanner.nextLine().split("\\s+");
            String type = data[0];
            String name = data[1];
            int damage = getDamage(data);
            int health = getHealth(data);
            int armor = getArmor(data);

            if (dragons.get(type)==null){
                dragons.put(type, new TreeMap<>());
                dragons.get(type).put(name, new ArrayList<>());
                dragons.get(type).get(name).add(damage);
                dragons.get(type).get(name).add(health);
                dragons.get(type).get(name).add(armor);

            }else {
                if (dragons.get(type).get(name)==null){
                    dragons.get(type).put(name, new ArrayList<>());
                    dragons.get(type).get(name).add(damage);
                    dragons.get(type).get(name).add(health);
                    dragons.get(type).get(name).add(armor);
                }else {
                    dragons.get(type).get(name).set(0, damage);
                    dragons.get(type).get(name).set(1, health);
                    dragons.get(type).get(name).set(2, armor);
                }
            }
        }
                     // type - > name->list of states
        for (Map.Entry<String, TreeMap<String, List<Integer>>> entry : dragons.entrySet()) {
            String type = entry.getKey();

            double avgDamage = 0;
            double avgHealt = 0;
            double avgArmor= 0;
            TreeMap<String, List<Integer>> names = entry.getValue();
            for (Map.Entry<String, List<Integer>> dragon : names.entrySet()) {
              avgDamage+=  dragon.getValue().get(0);
              avgHealt+= dragon.getValue().get(1);
              avgArmor+= dragon.getValue().get(2);
            }
            avgDamage=avgDamage / names.size();
            avgHealt = avgHealt / names.size();
            avgArmor = avgArmor / names.size();

            System.out.printf("%s::(%.2f/%.2f/%.2f)%n", type, avgDamage, avgHealt, avgArmor);

            for (Map.Entry<String, List<Integer>> dragon : names.entrySet()) {
                System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n"
                , dragon.getKey(), dragon.getValue().get(0), dragon.getValue().get(1), dragon.getValue().get(2));
            }

        }


    }

    private static int getDamage(String[] data) {
        String damage = data[2];
        if (damage.equals("null")){
            return 45;
        }else {
            return Integer.parseInt(damage);
        }
    }

    private static int getHealth(String[] data) {
        String health = data[3];
        if (health.equals("null")){
            return 250;
        }else {
            return Integer.parseInt(health);
        }
    }

    private static int getArmor(String[] data) {
        String armor = data[4];
        if (armor.equals("null")){
            return 10;
        }else {
            return Integer.parseInt(armor);
        }
    }
}
