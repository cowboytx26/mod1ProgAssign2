/*
Short Description:  This program will validate a credit card number.
Author:  Brian Wiatrek
Date:  August 22, 2024
*/
import java.io.*;

import static java.lang.Character.getNumericValue;

public class Main {

    public static class creditCardValidation {

        public static int sumOfDoubleEvenPlace(long number){
            int summation=0;
            while (number > 0){
                int remainder = getDigit(getSingleDigit(number % 100, 0) *2);
                //System.out.printf("Modulus: %d", number % 100);
                //System.out.printf("Single Digit: %d", getSingleDigit(number % 100, 0));
                //System.out.printf(" remainder: %d\n", remainder);
                summation = summation + remainder;
                number = number/100;
            }
            return summation;
        }

        public static int getDigit(int number){
            int remainder = number % 10;
            if (number/10 > 0){
                remainder += (number/10) % 10;
            }
            return remainder;
        }

        public static int sumOfOddPlace(long number){
            int summation=0;
            while (number > 0){
                int remainder = getDigit(getSingleDigit(number % 100, 1));
                //System.out.printf("Modulus: %d", number % 100);
                //System.out.printf("Single Digit: %d", getSingleDigit(number % 100, 1));
                //System.out.printf(" remainder: %d\n", remainder);
                summation = summation + remainder;
                number = number/100;
            }
            return summation;
        }

        public static int getSize(long d){
            int counter = 0;
            while (d > 0){
                counter++;
                d = d/10;
            }
            return counter;
        }

        public static int getSize(int d){
            int counter = 0;
            while (d > 0){
                counter++;
                d = d/10;
            }
            return counter;
        }

        public static long getDigit(long number, int k){
            return Long.parseLong(String.valueOf(number).substring(0,k));
        }

        public static int getSingleDigit(long number, int k){
            //if the length of the string is one and k is 0, then return 0
            if ((String.valueOf(number).length() == 1) && (k==0)) {
                return 0;
            }
            //if the length of the string is one and k is 1, then make k 0
            if ((String.valueOf(number).length() == 1) && (k==1)) {
                k = 0;
            }
            return getNumericValue(String.valueOf(number).charAt(k));
        }

        public static boolean prefixMatched(long number, int d){
            return (getDigit(number, getSize(d)) == d);
        }

        public static boolean isValid(long number){
            return ((((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10)==0) &&
                    ((prefixMatched(number,4) || prefixMatched(number, 5) || prefixMatched(number, 37) || prefixMatched(number, 6))) &&
                    ((getSize(number)>=13) && (getSize(number)<=16))
            );
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Please enter a credit card number:\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long inputNumber = Long.parseLong(reader.readLine());
        //System.out.printf("Even Sum: %d\n", creditCardValidation.sumOfDoubleEvenPlace(inputNumber));
        //System.out.printf("Odd Sum: %d\n", creditCardValidation.sumOfOddPlace(inputNumber));
        System.out.printf("Credit Card Number: %d", inputNumber);
        if (creditCardValidation.isValid(inputNumber)) System.out.println(" is valid.");
        else System.out.println(" is invalid");
    }
}