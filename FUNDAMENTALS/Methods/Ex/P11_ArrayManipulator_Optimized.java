package FundamentalsCourse.methods_4.ex;

import java.util.Arrays;
import java.util.Scanner;

public class P11_ArrayManipulator_Optimized {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String[] inputCommandArr = command.split("\\s+");
            String action = inputCommandArr[0];
            switch (action) {
                case "exchange":
                    int index = Integer.parseInt(inputCommandArr[1]);
                    if (index >= 0 && index <= array.length - 1) {
                        array = getExchangeArray(array, index);
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                case "max":
                    String evenOrOddMax = inputCommandArr[1];
                    int maxIndex = getMaxIndex(array, evenOrOddMax);
                    if (maxIndex != -1) {
                        System.out.println(maxIndex);
                    } else {
                        System.out.println("No matches");
                    }
                    break;
                case "min":
                    String evenOrOddMin = inputCommandArr[1];
                    int minIndex = getMinIndex(array, evenOrOddMin);
                    if (minIndex != -1) {
                        System.out.println(minIndex);
                    } else {
                        System.out.println("No matches");
                    }
                    break;
                case "first":
                    int count = Integer.parseInt(inputCommandArr[1]);
                    String evenOrOdd = inputCommandArr[2];
                    if (count > array.length) {
                        System.out.println("Invalid count");
                    } else {
                        int[] firstArray = getFirstElements(array, count, evenOrOdd);
                        if (firstArray.length == 0) {
                            System.out.println("[]");
                        } else {
                            System.out.print("[");
                            for (int i = 0; i < firstArray.length; i++) {
                                if (i == firstArray.length - 1) {
                                    System.out.print(firstArray[i] + "]");
                                    System.out.println();
                                } else {
                                    System.out.print(firstArray[i] + ", ");
                                }
                            }
                        }

                    }
                    break;
                case "last":
                    int countLast = Integer.parseInt(inputCommandArr[1]);
                    String evenOrOddLast = inputCommandArr[2];
                    if (countLast > array.length) {
                        System.out.println("Invalid count");
                    } else {
                        int[] firstArray = getLastElements(array, countLast, evenOrOddLast);
                        if (firstArray.length == 0) {
                            System.out.println("[]");
                        } else {
                            System.out.print("[");
                            for (int i = 0; i < firstArray.length; i++) {
                                if (i == firstArray.length - 1) {
                                    System.out.print(firstArray[i] + "]");
                                    System.out.println();
                                } else {
                                    System.out.print(firstArray[i] + ", ");
                                }
                            }
                        }
                    }
                    break;
            }

            command = scanner.nextLine();
        }

        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                System.out.print(array[i] + "]");
            } else {
                System.out.print(array[i] + ", ");
            }
        }
    }

    private static int[] getLastElements(int[] array, int countLast, String evenOrOddLast) {
        int countEvenNumber = 0;
        int countOddNumbers = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                countEvenNumber++;
            } else {
                countOddNumbers++;
            }
        }
        int[] allEvenElements = new int[countEvenNumber]; //all Even Numbers
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                allEvenElements[index] = array[i];
                index++;
            }
        }
        int[] allOddElements = new int[countOddNumbers];   //all Odd Numbers
        int indexOdd = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                allOddElements[indexOdd] = array[i];
                indexOdd++;
            }
        }

        if (evenOrOddLast.equals("even")) {
            if (countEvenNumber >= countLast) {
                int[] evenArray = new int[countLast];
                for (int i = 0; i < countLast; i++) {
                    evenArray[i] = allEvenElements[allEvenElements.length - countLast + i];
                }
                return evenArray;
            } else if (countEvenNumber == 0) {
                int[] evenArray = new int[0];
                return evenArray;
            } else {
                return allEvenElements;
            }
        } else {
            if (countOddNumbers >= countLast) {
                int[] oddArray = new int[countLast];
                for (int i = 0; i < countLast; i++) {
                    oddArray[i] = allOddElements[allOddElements.length - countLast + i];
                }
                return oddArray;
            } else if (countOddNumbers == 0) {
                int[] oddArray = new int[0];
                return oddArray;
            } else {
                return allOddElements;
            }
        }
    }

    private static int[] getFirstElements(int[] array, int count, String evenOrOdd) {
        int countEvenNumber = 0;
        int countOddNumbers = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                countEvenNumber++;
            } else {
                countOddNumbers++;
            }
        }
        int[] allEvenElements = new int[countEvenNumber]; //all Even Numbers
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                allEvenElements[index] = array[i];
                index++;
            }
        }
        int[] allOddElements = new int[countOddNumbers];   //all Odd Numbers
        int indexOdd = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                allOddElements[indexOdd] = array[i];
                indexOdd++;
            }
        }

        if (evenOrOdd.equals("even")) {
            if (countEvenNumber >= count) {
                int[] evenArray = new int[count];
                for (int i = 0; i < count; i++) {
                    evenArray[i] = allEvenElements[i];
                }
                return evenArray;
            } else if (countEvenNumber == 0) {
                int[] evenArray = new int[0];
                return evenArray;
            } else {
                return allEvenElements;
            }
        } else {
            if (countOddNumbers >= count) {
                int[] oddArray = new int[count];
                for (int i = 0; i < count; i++) {
                    oddArray[i] = allOddElements[i];
                }
                return oddArray;
            } else if (countOddNumbers == 0) {
                int[] oddArray = new int[0];
                return oddArray;
            } else {
                return allOddElements;
            }
        }

    }


    private static int getMinIndex(int[] array, String evenOrOddMin) {
        int minNumber = Integer.MAX_VALUE;
        int minIndex = -1;
        if (evenOrOddMin.equals("even")) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] % 2 == 0) {
                    if (array[i] <= minNumber) {
                        minNumber = array[i];
                        minIndex = i;
                    }
                }
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] % 2 != 0) {
                    if (array[i] <= minNumber) {
                        minNumber = array[i];
                        minIndex = i;
                    }
                }
            }
        }
        return minIndex;
    }

    private static int getMaxIndex(int[] array, String evenOrOdd) {
        int maxNumber = Integer.MIN_VALUE;
        int maxIndex = -1;

        if (evenOrOdd.equals("even")) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] % 2 == 0) {
                    if (array[i] >= maxNumber) {
                        maxNumber = array[i];
                        maxIndex = i;
                    }
                }
            }

        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] % 2 != 0) {
                    if (array[i] >= maxNumber) {
                        maxNumber = array[i];
                        maxIndex = i;
                    }
                }
            }
        }
        return maxIndex;
    }

    private static int[] getExchangeArray(int[] array, int index) {
        int[] firstArr = new int[index + 1];
        for (int i = 0; i <= index; i++) {
            firstArr[i] = array[i];
        }

        int[] secondArr = new int[array.length - index - 1];
        for (int i = index + 1; i < array.length; i++) {
            secondArr[i - (index + 1)] = array[i];
        }

        for (int i = 0; i < secondArr.length; i++) {
            array[i] = secondArr[i];  // 4 5 - - -
        }
        for (int i = 0; i < firstArr.length; i++) {
            array[secondArr.length + i] = firstArr[i];
        }
        return array;
    }
}
