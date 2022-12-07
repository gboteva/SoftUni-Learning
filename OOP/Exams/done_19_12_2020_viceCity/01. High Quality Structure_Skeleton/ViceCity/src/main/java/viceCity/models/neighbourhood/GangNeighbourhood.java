package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;


import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
         Collection<Gun> tommiGuns = mainPlayer.getGunRepository().getModels();

        while (mainPlayer.isAlive() && !isAllDead(civilPlayers) ) {

            for (Gun gun : tommiGuns) {

                for (Player civilPlayer : civilPlayers) {

                    while (gun.canFire()) {
                        if (civilPlayer.isAlive()) {
                            civilPlayer.takeLifePoints(gun.fire());
                        }else {
                            break;
                        }

                    }

                }


            }

            if (isAllDead(civilPlayers)) {
                break;
            }



            for (Player civilPlayer : civilPlayers) {
                if (civilPlayer.isAlive()) {
                    Collection<Gun> playerGuns = civilPlayer.getGunRepository().getModels();
                    for (Gun gun : playerGuns) {
                        while (gun.canFire()) {
                            mainPlayer.takeLifePoints(gun.fire());
                            if (!mainPlayer.isAlive()){
                                break;
                            }
                        }
                        if (!mainPlayer.isAlive()){
                            break;
                        }
                    }

                }
                if (!mainPlayer.isAlive()){
                    break;
                }
            }
            if (isRunOutGuns(tommiGuns)){
                break;
            }
        }

    }

    private boolean isRunOutGuns(Collection<Gun> guns) {
        for (Gun gun : guns) {
            if (gun.canFire()) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllDead(Collection<Player> civilPlayers) {
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                return false;
            }
        }
        return true;
    }


}
