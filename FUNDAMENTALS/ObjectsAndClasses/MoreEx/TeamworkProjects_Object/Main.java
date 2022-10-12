package FundamentalsCourse.objectsAndClasses_6.MoreEx.TeamworkProjects_Object;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split("-");
            String creator = data[0];
            String name = data[1];

            if (isContainsThisTeam(projects, name)) {
                System.out.printf("Team %s was already created!%n", name);
            } else if (isExistCreator(projects, creator)) {
                System.out.printf("%s cannot create another team!%n", creator);
            } else {
                Project project = new Project(name, creator);
                projects.add(project);
                System.out.printf("Team %s has been created by %s!%n", name, creator);
            }
        }

        String command = scanner.nextLine();
        while (!command.equals("end of assignment")) {
            String[] data = command.split("->");
            String user = data[0];
            String team = data[1];
            if (!isContainsThisTeam(projects, team)) {
                System.out.printf("Team %s does not exist!%n", team);
            } else if (isExistingMember(projects, user)) {
                System.out.printf("Member %s cannot join team %s!%n", user, team);
            } else {
                for (Project current : projects ){
                    if (current.getName().equals(team)){
                        current.setMembers(user);
                    }
                }

            }

            command = scanner.nextLine();
        }
        List<String> disband = new ArrayList<>();
        List<Project> projectToRemove = new ArrayList<>();
        for (Project entry : projects) {
            if (entry.getMembers().isEmpty()){
                disband.add(entry.getName());
                projectToRemove.add(entry);
            }
        }
        for ( Project project: projectToRemove ){
            projects.remove(project);
        }

        for (Project entry : projects) {
           entry.getMembers().sort(Comparator.naturalOrder());
        }


        projects.stream().sorted((a,b)->{
            int first = a.getMembers().size();
            int second = b.getMembers().size();
            int res = Integer.compare(second,first);
            if (res==0){
                return a.getName().compareTo(b.getName());
            }
            return res;
        })
                .forEach(t->{
            System.out.printf("%s%n- %s%n-- %s%n",
                    t.getName(), t.getCreator(), String.join("\n-- ", t.getMembers()));
        });

        disband.sort(Comparator.naturalOrder());
        System.out.println("Teams to disband:");
        for (String name : disband ){
            System.out.println(name);
        }

    }

    private static boolean isExistCreator(List <Project> projects, String creator) {
        for (Project current: projects){
            if (current.getCreator().equals(creator)){
                return true;
            }
        }
        return false;
    }

    private static boolean isContainsThisTeam(List<Project> projects, String name) {
        for (Project current: projects) {
            if (current.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private static boolean isExistingMember(List <Project> projects, String user) {
        for (Project current: projects) {

            for (String member : current.getMembers()) {
                if (member.equals(user)) {
                    return true;
                }
            }
           if (current.getCreator().equals(user)){
               return true;
            }
        }
        return false;
    }
}

