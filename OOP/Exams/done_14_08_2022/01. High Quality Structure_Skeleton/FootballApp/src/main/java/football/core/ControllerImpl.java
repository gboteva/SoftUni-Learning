package football.core;


import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.LinkedHashMap;
import java.util.Map;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private SupplementRepository supplement;
    private Map<String, Field> fields;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new LinkedHashMap<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field;

        if ("ArtificialTurf".equals(fieldType)) {
            field = new ArtificialTurf(fieldName);
        } else if ("NaturalGrass".equals(fieldType)) {
            field = new NaturalGrass(fieldName);
        } else {
            throw new NullPointerException(INVALID_FIELD_TYPE);
        }

        this.fields.put(fieldName, field);
        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);

    }

    @Override
    public String deliverySupplement(String type) {

        Supplement supplement;

        if ("Powdered".equals(type)) {
            supplement = new Powdered();
        } else if ("Liquid".equals(type)) {
            supplement = new Liquid();
        } else {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }

        this.supplement.add(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {

        Supplement supplementToAdd = this.supplement.findByType(supplementType);

        if (supplementToAdd == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        Field field = this.fields.get(fieldName);

        if (field != null) {
            field.addSupplement(supplementToAdd);
            this.supplement.remove(supplementToAdd);
        }

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player;

        if ("Women".equals(playerType)) {
            player = new Women(playerName, nationality, strength);
        } else if ("Men".equals(playerType)) {
            player = new Men(playerName, nationality, strength);
        }else {
            throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }


        Field field = this.fields.get(fieldName);
        boolean isValidFieldForWomen = "Women".equals(playerType) && "ArtificialTurf".equals(field.getClass().getSimpleName());
        boolean isValidFieldForMen = "Men".equals(playerType) && "NaturalGrass".equals(field.getClass().getSimpleName());

        if (isValidFieldForWomen || isValidFieldForMen) {
            field.addPlayer(player);
            return String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
        } else {
            return FIELD_NOT_SUITABLE;
        }

    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = this.fields.get(fieldName);

        field.drag();

        return String.format(PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = this.fields.get(fieldName);

        int totalStrength = field.getPlayers().stream().mapToInt(Player::getStrength).sum();

        return String.format(STRENGTH_FIELD, fieldName, totalStrength);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();

        for (Field field : this.fields.values()) {
            builder.append(field.getInfo()).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
