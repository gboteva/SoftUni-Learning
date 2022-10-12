package FundamentalsCourse.AssociativeArrays_7.moreEx;

import java.util.*;

public class P03_MOBAChallenger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Map<String, LinkedHashMap <String, Integer>> players = new LinkedHashMap<>();
        // User -> Position + Skill
        while (!input.equals("Season end")){
            if (input.contains(" -> ")){
              String []  dataToAdd = input.split(" -> ");
              String user = dataToAdd[0];
              String position = dataToAdd[1];
              int skills = Integer.parseInt(dataToAdd[2]);

              if (players.get(user)==null){
                  players.put(user, new LinkedHashMap<>(){{
                      put(position, skills);
                  }});
              }else {
                  if (players.get(user).get(position)==null){
                      players.get(user).put(position, skills);
                  }else {
                      int currentSkills = players.get(user).get(position);
                      players.get(user).put(position, Math.max(skills, currentSkills));
                  }
              }


            }else {
                String []  dataToFight = input.split("\\s+");
                String firstPlayer = dataToFight[0];
                String secondPlayer = dataToFight[2];
                List<String> playersToRemove = new ArrayList<>();

                if (players.containsKey(firstPlayer) && players.containsKey(secondPlayer)){
                    for (Map.Entry<String, Integer> firstMap : players.get(firstPlayer).entrySet()) {
                        for (Map.Entry<String, Integer> secondMap : players.get(secondPlayer).entrySet()) {
                            if (firstMap.getKey().equals(secondMap.getKey())){ //fight
                                int firstSkill = firstMap.getValue();
                                int secondSkill = secondMap.getValue();

                                if (firstSkill>secondSkill){
                                    playersToRemove.add(secondPlayer);
                                }else if (secondSkill>firstSkill){
                                    playersToRemove.add(firstPlayer);
                                }
                            }
                        }
                    }
                }
                for ( String user: playersToRemove ){
                    players.remove(user);
                }
            }

            input = scanner.nextLine();
        }

        Map<String, Integer> usersMaxPoints = new HashMap<>();
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : players.entrySet()) {
            String user = entry.getKey();

            Map<String, Integer> value = entry.getValue();
            for (Map.Entry<String, Integer> skils : value.entrySet()) {
               int currentSkills = skils.getValue();
               if (usersMaxPoints.get(user)==null){
                   usersMaxPoints.put(user, currentSkills);
               }else {
                   int userPoints = usersMaxPoints.get(user);
                   usersMaxPoints.put(user, userPoints+currentSkills);
               }
            }

        }

    usersMaxPoints.entrySet().stream().sorted((f,s)->{
                int firstPoint = f.getValue();
                int secondPoint = s.getValue();
                int result = Integer.compare(secondPoint,firstPoint);
                if (result==0){
                    String firstName = f.getKey();
                    String secondName = s.getKey();
                    return firstName.compareTo(secondName);
                }
                return result;
        }).forEach(user->{
        System.out.println(user.getKey()+ ": " + user.getValue() + " skill");
            players.get(user.getKey()).entrySet().stream().sorted((f,s)->{
                int firstPoints = f.getValue();
                int secondPoints = s.getValue();
                int result = Integer.compare(secondPoints,firstPoints);
                if (result==0){
                    String position1 = f.getKey();
                    String position2 = s.getKey();
                    return position1.compareTo(position2);
                }
                return result;
            }).forEach(users->{
                System.out.println("- " + users.getKey() + " <::> " + users.getValue());
            });
        });

    }
}
