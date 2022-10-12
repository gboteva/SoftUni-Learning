package exams.firstExam;

import java.util.Scanner;

public class P02_PresentDelivery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countOfPresents = Integer.parseInt(scanner.nextLine());
        int countRow = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[countRow][countRow];

        fillTheMatrix(scanner, matrix);

        String command = scanner.nextLine();
        int countHappyKids = 0;
        boolean isOutside = false;
        boolean isPresentOver = false;

        while (!command.equals("Christmas morning")) {
            int santaRowInitial = getSantaRow(matrix);
            int santaColInitial = getSantaCol(matrix);

            int santaRow = santaRowInitial;
            int santaCol = santaColInitial;
            matrix[santaRow][santaCol] = "-";

            switch (command) {
                case "up":
                    santaRow -= 1;
                    break;
                case "down":
                    santaRow += 1;
                    break;
                case "right":
                    santaCol += 1;
                    break;
                case "left":
                    santaCol -= 1;
                    break;
            }
            if (isOutside(matrix, santaRow, santaCol)) {
                //ако излезе от полето и няма S?
                matrix[santaRowInitial][santaColInitial] = "S";
                isOutside = true;
                break;
            }


            if (matrix[santaRow][santaCol].equals("V")) {
                countOfPresents--;
                countHappyKids++;
            } else if (matrix[santaRow][santaCol].equals("-")) {
                matrix[santaRow][santaCol] = "S";
                command = scanner.nextLine();
                continue;
            } else if (matrix[santaRow][santaCol].equals("C")) {
                int up = santaRow-1;
                int down = santaRow+1;
                int left = santaCol-1;
                int right = santaCol+1;
                if (up<0 || down>= matrix.length || left<0 || right>=matrix.length){
                    matrix[santaRow][santaCol] = "S";
                    isOutside=true;
                    break;
                }else {
                    countOfPresents=countOfPresents-3;
                    countHappyKids = countHappyKids+3;
                }
                goUp(matrix, santaRow, santaCol);
                goDown(matrix, santaRow, santaCol);
                goLeft(matrix, santaRow, santaCol);
                goRight(matrix, santaRow, santaCol);
            }

            matrix[santaRow][santaCol] = "S";

            if (countOfPresents == 0) {
                isPresentOver = true;
                break;
            }

            command = scanner.nextLine();
        }

        //ако е излязъл от полето:
        // ако е свършил подаръците:
            if ( isPresentOver || isOutside){
                System.out.println("Santa ran out of presents!");
            }

            printMatrix(matrix);

            if (!isThereMoreGoodKids(matrix)){
                //ако е дал подаръци на всички добри деца
                System.out.printf("Good job, Santa! %d happy nice kid/s.%n", countHappyKids);
            } else {
                // ако са останали добри деца без подаръци;
                int countLeftGoodKids = getLeftGoodKids(matrix);
                System.out.printf("No presents for %d nice kid/s.%n", countLeftGoodKids);
            }



    }

    private static int getLeftGoodKids(String[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("V")){
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isThereMoreGoodKids(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("V")){
                    return true;
                }
            }
        }
        return false;
    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void goRight(String[][] matrix, int santaRow, int santaCol) {
        santaCol = santaCol +1;
        if (santaCol >= 0 && santaCol < matrix.length) {
            if (matrix[santaRow][santaCol].equals("V") || matrix[santaRow][santaCol].equals("X")) {
                matrix[santaRow][santaCol] = "-";
            }
        }
    }

    private static void goLeft(String[][] matrix, int santaRow, int santaCol) {
        santaCol = santaCol - 1;
        if (santaCol >= 0 && santaCol < matrix.length) {
            if (matrix[santaRow][santaCol].equals("V") || matrix[santaRow][santaCol].equals("X")) {
                matrix[santaRow][santaCol] = "-";
            }
        }
    }

    private static void goDown(String[][] matrix, int santaRow, int santaCol) {
        santaRow = santaRow + 1;
        if (santaRow >= 0 && santaRow < matrix.length) {
            if (matrix[santaRow][santaCol].equals("V") || matrix[santaRow][santaCol].equals("X")) {
                matrix[santaRow][santaCol] = "-";
            }
        }
    }

    private static void goUp(String[][] matrix, int santaRow, int santaCol) {
        santaRow = santaRow - 1;
        if (santaRow >= 0 && santaRow < matrix.length) {
            if (matrix[santaRow][santaCol].equals("V") || matrix[santaRow][santaCol].equals("X")) {
                matrix[santaRow][santaCol] = "-";
            }
        }
    }

    private static int getSantaCol(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("S")) {
                    return j;
                }
            }

        }
        return -1;
    }

    private static boolean isOutside(String[][] matrix, int row, int col) {
        if (row > matrix.length) {
            return true;
        } else if (row < 0) {
            return true;
        } else if (col < 0) {
            return true;
        } else if (col > matrix.length) {
            return true;
        }
        return false;
    }

    private static int getSantaRow(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("S")) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static void fillTheMatrix(Scanner scanner, String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = scanner.nextLine().split("\\s+");
        }
    }
}
