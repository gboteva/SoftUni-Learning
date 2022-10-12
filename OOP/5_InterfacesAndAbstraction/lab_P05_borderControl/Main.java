package lab_P05_borderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        List<Identifiable> citizens = new ArrayList<>();

        while (!line.equals("End")){
            String[] info = line.split("\\s+");

            if (info.length==3){
                String name = info[0];
                int age = Integer.parseInt(info[1]);
                String id = info[2];
                Citizen citizen = new Citizen(name,age,id);
                citizens.add(citizen);
            }else {
                String model = info[0];
                String id = info[1];
                Robot robot = new Robot(model, id);
                citizens.add(robot);
            }

            line = scanner.nextLine();
        }

        String fakeId = scanner.nextLine();

        citizens.stream().forEach(p->{
            if (p.getId().endsWith(fakeId)){
                System.out.println(p.getId());
            }
        });
    }
}
