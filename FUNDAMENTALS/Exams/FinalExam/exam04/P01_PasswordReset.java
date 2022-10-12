package FundamentalsCourse.finalExam.exam04;

import java.util.Scanner;

public class P01_PasswordReset {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        String line = scanner.nextLine();

        while (!line.equals("Done")) {
            String[] command = line.split("\\s+");
            String action = command[0];
            switch (action) {
                case "TakeOdd":
                    StringBuilder oddPass = new StringBuilder();
                    for (int i = 0; i <password.length() ; i++) {
                        if (i%2!=0){
                            oddPass.append(password.charAt(i));
                        }
                    }
                    password=String.valueOf(oddPass);
                    System.out.println(password);
                    break;
                case "Cut":
                    int index = Integer.parseInt(command[1]);
                    int lenght = Integer.parseInt(command[2]);
                    String substringToRemove = password.substring(index, index+lenght);
                    password = password.replaceFirst(substringToRemove,"");

                    System.out.println(password);
                    break;
                case "Substitute":
                    String substring = command[1];
                    String substitude = command[2];
                    if (password.contains(substring)) {
                        password = password.replace(substring, substitude);
                        System.out.println(password);
                    }else {
                        System.out.println("Nothing to replace!");
                    }
                    break;
            }

            line = scanner.nextLine();
        }
        System.out.println("Your password is: " + password);
    }
}
