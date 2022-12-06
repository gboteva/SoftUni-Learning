package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OperationImpl implements Operation {

    public OperationImpl() {
    }

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        for (Discoverer discoverer : discoverers) {
            List<String> toRemove = new ArrayList<>();

            for (String exhibit : spot.getExhibits()) {

                if (discoverer.canDig()) {
                    discoverer.dig();
                    discoverer.getMuseum().getExhibits().add(exhibit);
                    toRemove.add(exhibit);
                }else {
                    break;
                }

            }
            toRemove.forEach(e->spot.getExhibits().remove(e));
        }

    }
}
