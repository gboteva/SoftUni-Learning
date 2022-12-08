package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {

        List<String> items = new ArrayList<>(planet.getItems());

        for (Astronaut astronaut : astronauts) {
            if (items.isEmpty()){
                break;
            }

            if (astronaut.canBreath()){
                for (int i = 0; i < items.size() ; i++) {
                    astronaut.breath();
                    astronaut.getBag().getItems().add(items.get(i));
                    items.remove(i);
                    i--;
                    if (!astronaut.canBreath()){
                        break;
                    }
                }
            }
        }
    }

}
