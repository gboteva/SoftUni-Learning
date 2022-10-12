package FundamentalsCourse.AssociativeArrays_7.moreEx;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class P02_Judge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Contest -> User + MaxPoints
        Map<String, LinkedHashMap<String, Integer>> courses = new LinkedHashMap<>();

        while (!input.equals("no more time")) {
            String userName = input.split(" -> ")[0];
            String contest = input.split(" -> ")[1];
            int points = Integer.parseInt(input.split(" -> ")[2]);


            if (courses.get(contest) == null) {
                courses.put(contest, new LinkedHashMap<>(){{
                    put(userName,points);
                }});

            } else {
                if (!courses.get(contest).containsKey(userName)) {
                    courses.get(contest).put(userName, points);

                } else {
                    courses.get(contest).put(userName, Math.max(courses.get(contest).get(userName), points));
                }
            }

            input = scanner.nextLine();
        }

        //User -> TotalPoint
        Map<String, Integer> statistics = new LinkedHashMap<>();
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : courses.entrySet()) {
            String course = entry.getKey();
            Map<String, Integer> users = entry.getValue();
            for (Map.Entry<String, Integer> user : users.entrySet()) {
                String currentUser = user.getKey();
                int currentPoint = user.getValue();
                if (statistics.get(currentUser) ==null){
                    statistics.put(currentUser, currentPoint);
                }else {
                    int point = statistics.get(currentUser);
                    statistics.put(currentUser, point + currentPoint);
                }

            }
        }

        // Contest -> User + MaxPoints
        AtomicInteger count = new AtomicInteger(1);
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : courses.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size() + " participants");
            Map<String, Integer> value = entry.getValue();
            value.entrySet().stream().sorted((f,s)->{
                int firstPoint = f.getValue();
                int secondPoint = s.getValue();
                int result = Integer.compare(secondPoint,firstPoint);
                if (result==0){
                    String firstName = f.getKey();
                    String secondName = s.getKey();
                    return firstName.compareTo(secondName);
                }
                return result;
            })
                    .forEach(user->{
                System.out.println(count + ". "  + user.getKey() + " <::> " + user.getValue());
                count.getAndIncrement();
            } );
            count.set(1);
        }

        System.out.println("Individual standings:");
        AtomicInteger counter = new AtomicInteger(1);
        //User -> TotalPoint
        statistics.entrySet().stream().sorted((f,s)->{
            int fPoint = f.getValue();
            int sPoint = s.getValue();
            int result = Integer.compare(sPoint, fPoint);
            if (result==0){
                String fName = f.getKey();
                String sName = s.getKey();
                return fName.compareTo(sName);
            }
            return result;
        }).forEach(user->{
            System.out.println(counter + ". " + user.getKey() + " -> " + user.getValue());
            counter.getAndIncrement();
        });


    }
}

