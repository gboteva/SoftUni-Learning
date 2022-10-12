package definingClasses.pokemonTrainer;

import java.util.*;

public class Trainer {
    private String name;
    private int numberOfBadges;
    private Map<String, List<Pokemon>> pokemons;

    public Trainer(String name) {
        this.name = name;
        this.numberOfBadges = 0;
        this.pokemons = new LinkedHashMap<>();
    }

    protected void addPokemon (Pokemon pokemon){
        this.pokemons.putIfAbsent(pokemon.getElement(), new ArrayList<>());
        this.pokemons.get(pokemon.getElement()).add(pokemon);
    }

    protected boolean hasPokemonWithElement(String element){
        return this.pokemons.containsKey(element) && this.pokemons.get(element).size()>0;
    }

    protected void action (String element){
        if (this.hasPokemonWithElement(element)){
            increaseBadges(element);
        }else {
            decreaseHealth();
        }
    }

    protected void increaseBadges(String element){
        if (hasPokemonWithElement(element)){
            this.numberOfBadges++;
        }
    }

    protected void decreaseHealth (){
        this.pokemons.values().forEach(l-> l.forEach(Pokemon::decreaseHealth));
        removeDeadPokemons();
    }

    private void removeDeadPokemons (){

        while (checkForDeadPokemons()) {
            Pokemon deadPokemon = getDeadPokemon();
            if (deadPokemon!=null){
            this.pokemons.get(deadPokemon.getElement()).remove(deadPokemon);
            }
        }
    }

    private boolean checkForDeadPokemons (){
        for (Map.Entry<String, List<Pokemon>> entry : this.pokemons.entrySet()) {
            List<Pokemon> list = entry.getValue();
            for (Pokemon pokemon : list) {
                if (pokemon.getHealth()<=0){
                    return true;
                }
            }
        }
        return false;
    }

    private Pokemon getDeadPokemon(){
        for (Map.Entry<String, List<Pokemon>> entry : this.pokemons.entrySet()) {
            List<Pokemon> list = entry.getValue();
            for (Pokemon pokemon : list) {
                if (pokemon.getHealth()<=0){
                    return pokemon;
                }
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", this.name, this.numberOfBadges, getNumberOfPokemons(pokemons));
    }

    public int getNumberOfBadges() {
        return numberOfBadges;
    }

    private int getNumberOfPokemons(Map<String, List<Pokemon>> pokemons) {
        int count = 0;
        for (Map.Entry<String, List<Pokemon>> entry : pokemons.entrySet()) {
            List<Pokemon> list = entry.getValue();
            count += list.size();
        }
        return count;
    }
}
