package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;

public class MissionImpl implements Mission{

    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Collection<String> exhibits = state.getExhibits();

        for (Explorer explorer : explorers) {
            if (exhibits.isEmpty()){
                break;
            }
            Collection<String> toRemove = new ArrayList<>();

                for (String exhibit : exhibits) {
                    if (explorer.canSearch()){
                        explorer.getSuitcase().getExhibits().add(exhibit);
                        toRemove.add(exhibit);
                        explorer.search();
                    }else {
                        break;
                    }
                }

                exhibits.removeAll(toRemove);
        }
    }
}
