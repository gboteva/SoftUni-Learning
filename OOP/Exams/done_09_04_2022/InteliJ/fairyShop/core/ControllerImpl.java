package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import fairyShop.repositories.Repository;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Helper> helperRepository;
    private Repository<Present> presentRepository;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
        if (type.equals("Happy")){
            Helper helper = new Happy(helperName);
            this.helperRepository.add(helper);
        } else if (type.equals("Sleepy")){
            Helper helper = new Sleepy(helperName);
            this.helperRepository.add(helper);
        }else {
            throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }

        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        if (helperRepository.findByName(helperName) == null){
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        Instrument instrument = new InstrumentImpl(power);
        helperRepository.findByName(helperName).addInstrument(instrument);
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        Present presentToCraft = this.presentRepository.findByName(presentName);

        Helper helperToWork = this.helperRepository.getModels()
                .stream().filter(h->h.getEnergy() > 50)
                .findFirst()
                .orElse(null);

        if (helperToWork == null){
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        Shop shop = new ShopImpl();
        shop.craft(presentToCraft, helperToWork);

        String isPresentDone;
        if (presentToCraft.isDone()){
            isPresentDone = "done";
        }else {
            isPresentDone = "not done";
        }

        int countBrokenInstruments = (int) helperToWork.getInstruments().stream()
                .filter(Instrument::isBroken).count();

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(PRESENT_DONE, presentName, isPresentDone));
        builder.append(String.format(COUNT_BROKEN_INSTRUMENTS, countBrokenInstruments));
        return builder.toString().trim();
    }

    @Override
    public String report() {
        int countDonePresents = (int) this.presentRepository.getModels().stream()
                .filter(Present::isDone).count();

        StringBuilder output = new StringBuilder();

        output.append(countDonePresents).append(" presents are done!").append(System.lineSeparator());
        output.append("Helpers info:").append(System.lineSeparator());

        for (Helper helper : this.helperRepository.getModels()) {
            output.append("Name: ").append(helper.getName()).append(System.lineSeparator());
            output.append("Energy: ").append(helper.getEnergy()).append(System.lineSeparator());
            long countNotBrokenInstruments = helper.getInstruments().stream().filter(i-> !i.isBroken()).count();
            output.append("Instruments: ").append(countNotBrokenInstruments).append(" not broken left").append(System.lineSeparator());
        }

        return output.toString().trim();
    }
}
