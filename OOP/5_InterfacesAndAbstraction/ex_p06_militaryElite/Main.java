package ex_p06_militaryElite;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Map<Integer, PrivateImpl> privates = new LinkedHashMap<>();

        while (!line.equals("End")) {
            String[] inputInfo = line.split("\\s+");
            int id = Integer.parseInt(inputInfo[1]);
            String firstName = inputInfo[2];
            String lastName = inputInfo[3];

            switch (inputInfo[0]) {
                case "Private":
                    double privateSalary = Double.parseDouble(inputInfo[4]);
                    PrivateImpl aPrivate = new PrivateImpl(id, firstName, lastName, privateSalary);
                    System.out.println(aPrivate);
                    privates.putIfAbsent(id, aPrivate);
                    break;

                case "LieutenantGeneral":
                    double ltGenSalary = Double.parseDouble(inputInfo[4]);
                    LieutenantGeneral lieutenantGeneral = new LieutenantGeneralImpl(id, firstName, lastName, ltGenSalary);

                    addPrivates(inputInfo, privates, lieutenantGeneral);
                    System.out.println(lieutenantGeneral);
                    break;

                case "Engineer":
                    double engSalary = Double.parseDouble(inputInfo[4]);
                    String corps = inputInfo[5];
                    if (!corps.equals(Corps.Airforces.toString()) && !corps.equals(Corps.Marines.toString())) {
                        break;
                    }
                    Corps corpsToAdd = Corps.valueOf(corps);
                    EngineerImpl engineer = new EngineerImpl(id, firstName, lastName, engSalary, corpsToAdd);

                    addRepairs(inputInfo, engineer);
                    System.out.println(engineer);
                    break;

                case "Commando":
                    double commandoSalary = Double.parseDouble(inputInfo[4]);
                    String commandoCorps = inputInfo[5];
                    if (!commandoCorps.equals(Corps.Airforces.toString()) && !commandoCorps.equals(Corps.Marines.toString())) {
                        break;
                    }
                    Corps corpsToCommando = Corps.valueOf(commandoCorps);
                    CommandoImpl commando = new CommandoImpl(id, firstName, lastName, commandoSalary, corpsToCommando);

                    addMissions(inputInfo, commando);
                    System.out.println(commando);
                    break;

                case "Spy":
                    String codeNumber = inputInfo[4];
                    Spy spy = new SpyImpl(id, firstName, lastName, codeNumber);
                    System.out.println(spy);
                    break;

            }

            line = scanner.nextLine();
        }

    }

    private static void addMissions(String[] inputInfo, CommandoImpl commando) {
        for (int i = 6; i < inputInfo.length; i += 2) {
            String missionCodeName = inputInfo[i];
            String missionState = inputInfo[i + 1];
            if (missionState.equals(State.inProgress.toString()) || missionState.equals(State.finished.toString())) {
                State state = State.valueOf(missionState);
                Mission mission = new MissionImpl(missionCodeName, state);
                commando.addMission(mission);
            }
        }
    }

    private static void addRepairs(String[] inputInfo, EngineerImpl engineer) {
        for (int i = 6; i < inputInfo.length; i += 2) {
            String repairPart = inputInfo[i];
            int repairHours = Integer.parseInt(inputInfo[i + 1]);
            Repair repair = new RepairImpl(repairPart, repairHours);
            engineer.addRepair(repair);
        }
    }

    private static void addPrivates(String[] inputInfo, Map<Integer, PrivateImpl> privates, LieutenantGeneral lieutenantGeneral) {
        for (int i = 5; i < inputInfo.length; i++) {
            int privateID = Integer.parseInt(inputInfo[i]);
            PrivateImpl privateImpl = privates.get(privateID);
            lieutenantGeneral.addPrivate(privateImpl);
        }
    }
}
