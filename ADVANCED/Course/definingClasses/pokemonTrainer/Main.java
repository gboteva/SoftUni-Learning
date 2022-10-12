package definingClasses.pokemonTrainer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String line = scanner.nextLine();

        Map<String, Trainer> trainerMap = new LinkedHashMap<>();

        while (!line.equals("Tournament")){
            String[] tokens = line.split("\\s+");
            String trainerName = tokens[0];
            String pokemonName = tokens[1];
            String pokemonElement = tokens[2];
            int health = Integer.parseInt(tokens[3]);

            Trainer trainer = new Trainer(trainerName);
            Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, health);

            if (trainerMap.containsKey(trainerName)){
                trainerMap.get(trainerName).addPokemon(pokemon);
            }else {
                trainer.addPokemon(pokemon);
                trainerMap.put(trainerName, trainer);
            }

            line = scanner.nextLine();
        }

        line = scanner.nextLine();

        while (!line.equals("End")){
            String element = line;

            for (Trainer trainer : trainerMap.values()) {
                trainer.action(element);
            }

            line = scanner.nextLine();
        }

        trainerMap.values().stream()
                .sorted((first, second)->Integer.compare(second.getNumberOfBadges(), first.getNumberOfBadges()))
                .forEach(System.out::println);

    }
}
