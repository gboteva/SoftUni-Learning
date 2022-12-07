package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.GunRepository;
import viceCity.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {

   private Player mainPlayer;
   private Map<String, Player> civilPlayers;
   private Repository<Gun> gunRepository;

    public ControllerImpl() {
        gunRepository = new GunRepository();
        civilPlayers = new LinkedHashMap<>();
        mainPlayer = new MainPlayer();
    }

    @Override
    public String addPlayer(String name) {
        Player civilPlayer = new CivilPlayer(name);
        civilPlayers.putIfAbsent(name, civilPlayer);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type){
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default: return GUN_TYPE_INVALID;
        }
        gunRepository.add(gun);
        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = null;

        if (gunRepository.getModels().iterator().hasNext()){
             gun = gunRepository.getModels().iterator().next();
        }

       if (gun == null){
          return GUN_QUEUE_IS_EMPTY;
       }

       if (name.startsWith("Vercetti")){
           this.mainPlayer.getGunRepository().add(gun);
           gunRepository.remove(gun);
           return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), "Tommy Vercetti");
       }

       Player player = civilPlayers.get(name);
       if (civilPlayers.get(name) == null){
           return CIVIL_PLAYER_DOES_NOT_EXIST;
       }

       player.getGunRepository().add(gun);
       gunRepository.remove(gun);

       return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
    }

    @Override
    public String fight() {

        Neighbourhood neighbourhood = new GangNeighbourhood();
        neighbourhood.action(mainPlayer,civilPlayers.values());

        StringBuilder builder = new StringBuilder();
        if (mainPlayer.getLifePoints()!= 100 || isAnybodyHarmed(civilPlayers.values())){
               builder.append(FIGHT_HAPPENED).append(System.lineSeparator());
               builder.append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints())).append(System.lineSeparator());
               int countDead = getCountOfDeadPlayer(civilPlayers.values());
               builder.append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, countDead)).append(System.lineSeparator());
               builder.append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayers.values().size() - countDead));
               return builder.toString().trim();
        }else {
            return "Everything is okay!";
        }
    }

    private int getCountOfDeadPlayer(Collection<Player> values) {
        int count = 0;
        for (Player civilPlayer : values) {
           if (!civilPlayer.isAlive()){
               count++;
           }
        }
        return count;
    }



    private boolean isAnybodyHarmed(Collection<Player> civilPlayers) {
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.getLifePoints() !=50){
                return true;
            }
        }
        return false;
    }
}
