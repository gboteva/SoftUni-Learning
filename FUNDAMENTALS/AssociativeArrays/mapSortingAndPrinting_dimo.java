import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class mapSortingAndPrinting_dimo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> students = new LinkedHashMap<>();
        students.put("Ivan", 3);
        students.put("Georgi", 4);
        students.put("Todor", 6);
        students.put("Asen", 2);
        students.put("Pesho", 5);
        students.put("Ognyan", 6);
        students.put("Galin", 3);

        students.values().stream().forEach(System.out::println); //принтира само стойностите
        System.out.println("*****************************");
        students.keySet().stream().forEach(System.out::println); //принитра само ключовете
        System.out.println("*****************************");
        students.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));  //принтира ключ и стойност разделени с празно място
        System.out.println("*****************************");

        students.entrySet().stream().sorted(
                (left, right)->{
                    return left.getValue().compareTo(right.getValue());
                }
        ).forEach(entry-> System.out.println(entry.getKey() + " " + entry.getValue())); // принтира ключ и стойност разделени с празно място, сортирани по стойност - нарастващ ред

        System.out.println("*****************************");
        students.entrySet().stream().sorted(
                (left, right)->{
                    return right.getValue().compareTo(left.getValue());
                }
        ).forEach(entry-> System.out.println(entry.getKey() + " " + entry.getValue())); // принтира ключ и стойност разделени с празно място, сортирани по стойност - намаляващ ред


        System.out.println("*****************************");
        //принтиране с подреждане във низходящ ред по стойности и във възходящ ред по ключове:
        students.entrySet().stream().sorted(
                (left, right)->{
                    int result = right.getValue().compareTo(left.getValue());  //първо по стойност
                    if (result==0){
                        result = left.getKey().compareTo(right.getKey());   //после по ключ
                    }
                    return result;
                }
        ).forEach(entry-> System.out.println(entry.getKey() + " " + entry.getValue()));

        System.out.println("*****************************");
    }
}
