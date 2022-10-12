package FundamentalsCourse.finalExam.exam04;

import java.util.*;

public class P03_HeroesOfCodeAndLogicVII {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Integer>> heroes = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String heroName = input[0];
            int HP = Integer.parseInt(input[1]);
            int MP = Integer.parseInt(input[2]);

            if (heroes.get(heroName) == null) {
                heroes.put(heroName, new ArrayList<>());
                heroes.get(heroName).add(HP);
                heroes.get(heroName).add(MP);
            } else {
                if (heroes.get(heroName).get(0) + HP > 100) {
                    heroes.get(heroName).set(0, 100);
                } else {
                    heroes.get(heroName).set(0, heroes.get(heroName).get(0) + HP);
                }
                if (heroes.get(heroName).get(1) + MP > 200) {
                    heroes.get(heroName).set(1, 200);
                } else {
                    heroes.get(heroName).set(1, heroes.get(heroName).get(1) + MP);
                }
            }
        }

        String command = scanner.nextLine();
        while (!command.equals("End")) {
            String[] line = command.split(" - ");
            String action = line[0];
            String name = line[1];

            switch (action) {
                case "CastSpell":
                    int needMP = Integer.parseInt(line[2]);
                    String spellName = line[3];
                    if (heroes.get(name).get(1) >= needMP) {
                        heroes.get(name).set(1, heroes.get(name).get(1) - needMP);
                        System.out.printf("%s has successfully cast %s and now has %d MP!%n", name, spellName, heroes.get(name).get(1));
                    } else {
                        System.out.printf("%s does not have enough MP to cast %s!%n", name, spellName);
                    }
                    break;
                case "TakeDamage":
                    int damage = Integer.parseInt(line[2]);
                    String attacker = line[3];
                    heroes.get(name).set(0, heroes.get(name).get(0) - damage);

                    if (heroes.get(name).get(0) > 0) {
                        System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n", name, damage, attacker, heroes.get(name).get(0));
                    } else {
                        heroes.remove(name);
                        System.out.printf("%s has been killed by %s!%n", name, attacker);
                    }

                    break;
                case "Recharge":
                    int amountRech = Integer.parseInt(line[2]);
                    int forRech = 200 - heroes.get(name).get(1);
                    if (heroes.get(name).get(1) + amountRech > 200) {
                        heroes.get(name).set(1, 200);
                        System.out.printf("%s recharged for %d MP!%n", name, forRech);
                    } else {
                        heroes.get(name).set(1, heroes.get(name).get(1) + amountRech);
                        System.out.printf("%s recharged for %d MP!%n", name, amountRech);
                    }

                    break;
                case "Heal":
                    int amountHeal = Integer.parseInt(line[2]);
                    int forHeal = 100 - heroes.get(name).get(0);
                    if (heroes.get(name).get(0) + amountHeal > 100) {
                        heroes.get(name).set(0, 100);
                        System.out.printf("%s healed for %d HP!%n", name, forHeal);
                    } else {
                        heroes.get(name).set(0, heroes.get(name).get(0) + amountHeal);
                        System.out.printf("%s healed for %d HP!%n", name, amountHeal);
                    }
                    break;
            }


            command = scanner.nextLine();
        }
        for (Map.Entry<String, List<Integer>> hero : heroes.entrySet()) {
            List<Integer> points = hero.getValue();
            System.out.println(hero.getKey());
            System.out.printf("\t HP: %d%n", points.get(0));
            System.out.printf("\t MP: %d%n", points.get(1));
            ;
        }


    }
}
