package ex_p06_militaryElite;

public class MissionImpl implements Mission{
    private String codeName;
    private State state;

    public MissionImpl(String codeName, State state) {
        this.codeName = codeName;
        this.setState(state);
    }

    private void setState(State state){
        if (state.equals(State.inProgress)|| state.equals(State.finished)){
            this.state = state;
        }
    }

    @Override
    public String getCodeName() {
        return this.codeName;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public void completeMission(){
        this.state = State.finished;
    }

    @Override
    public String toString() {
        //Code Name: {codeName} State: {state}
        return String.format("Code Name: %s State: %s", this.codeName, this.state.toString());
    }
}
