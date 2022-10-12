package ex_p06_militaryElite;

public interface Mission {
    String getCodeName();
    State getState();

    void completeMission();
}
