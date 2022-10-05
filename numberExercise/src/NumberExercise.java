
public class NumberExercise {

    public static int[] restsOfDigits(int number){
        int[] result = {0, 0};
        do{
            result[0] += number %10;
            number = number /10;
            result[1] ++;
        }while(number > 0);
        result[0] = result[0] % 3;
        return result;
    }
    public static int[] digits(int number, int numberOfDigits){
        int[] result = new int[numberOfDigits];
        for(int i = numberOfDigits - 1; i >= 0; i--){
            result[i] = number %10;
            number = number/10;
        }
        return result;
    }

    public static void increment(int[] digits, int rest){

        switch (rest) {
            case 1 :
                for(int i = 0; i < 2; i++){
                    for(int j = 0; j < digits.length; j++){
                        if(digits[j] < 9){
                            digits[j] += 1;
                            break;
                        }
                    }
                }
                break;
            case 2 :
                    for(int j = 0; j < digits.length; j++){
                        if(digits[j] < 9){
                            digits[j] += 1;
                            break;
                        }
                    }
                break;
            default:
                break;
        }
    }



    public static int[] modifyNumbers(int[] numbers) {
        int movesLeft = 6;
        int[] finalNumbers = new int[3];
        int[][] digitDetails = new int[3][];
        int[][] digits = new int[3][];
        int sumOfRests = 0;
        for (int i = 0; i < 3; i++) {
            digitDetails[i] = restsOfDigits(numbers[i]);
            digits[i] = digits(numbers[i], digitDetails[i][1]);
            sumOfRests += digitDetails[i][0];
            increment(digits[i], digitDetails[i][0]);
        }
        movesLeft -= sumOfRests;
        if(movesLeft >=3){
            int i = 0;
            int index = -1;
            while(movesLeft != 0){
                index++;
                int indexOfNumber = 0;
                if(Integer.compare(digitDetails[0][1], digitDetails[1][1]) > 0){
                    if(Integer.compare(digitDetails[0][1], digitDetails[2][1]) > 0){
                        indexOfNumber = 0;
                        if(digits[0][index] < 7) {
                            digits[0][index] += 3;
                            movesLeft = 0;
                            break;
                        }
                    }
                    else {
                        indexOfNumber = 2;
                    }
                }
                else {
                    if(Integer.compare(digitDetails[1][1], digitDetails[2][1]) > 0){
                        indexOfNumber = 1;
                    }
                    else {
                        indexOfNumber = 2;
                    }
                }
                if(digits[indexOfNumber][index] < 7) {
                    digits[indexOfNumber][index] += 3;
                    movesLeft = 0;
                }
            }
        }
        int sum = sum(digits);
        for(int i = 0; i < 3 ; i++)
            finalNumbers[i] = reconstruction(digits[i]);

        return finalNumbers;
    }
    public static int reconstruction(int[] number){
        int result = 0;
        for(int j = 0; j < number.length; j++){
            int power = (number.length - 1) - j;
            result += (number[j] * Math.pow(10, power));
        }
        return result;
    }
    public static int sum(int[][] digits){
        int result = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < digits[i].length; j++){
                int power = (digits[i].length - 1) - j;
               result += (digits[i][j] * Math.pow(10, power));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        if(args.length == 3){
            int[] numbers = new int[3];
            for(int i = 0; i < 3; i++){
                numbers[i] = Integer.parseInt(args[i]);
            }
            int[] finalNumbers = NumberExercise.modifyNumbers(numbers);
            for(int i = 0; i < 3; i++)
                System.out.println(finalNumbers[i]);
        }
    }
}
