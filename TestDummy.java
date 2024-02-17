package edu.pasadena.cs.cs03b;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestDummy {

   @Test
   public void testDummy() {
      // action
      int iResult = GuessBirthday.dummy();
      // assertion
      assertEquals(1, iResult);

      // Test cases (via console) with N up to 5.
      // Create an instance of GuessBirthday
      GuessBirthday guess = new GuessBirthday();
      int roundedResult = (int) Math.round(Math.log(32) / Math.log(2));
      int[][] day = new int[roundedResult][16];
      guess.Builder(31, day); // Correctly passing both parameters to the Builder method

      // Manually build the Testday array with the same logic as in the Builder method
      int[][] Testday = new int[roundedResult][16];
      for (int i = 1; i <= 31; i++) {
         int temp = i;
         for (int j = Testday.length - 1; j >= 0; j--) {
            if (temp >= Math.pow(2, j)) {
               temp -= Math.pow(2, j);
               for (int k = 0; k < Testday[j].length; k++) {
                  if (Testday[j][k] == 0) {
                     Testday[j][k] = i;
                     break;
                  }
               }
            }
         }
      }

      // Compare the 'day' array from the GuessBirthday instance with the manually
      // built Testday array
      assertArrayEquals(day, Testday);

      // Have test cases (via console) with N up to 8.
      // Create an instance of GuessBirthday for guessing a number (choice 2)
      GuessBirthday guessNumber = new GuessBirthday(); // 假设GuessBirthday构造函数可以接受choice作为参数
      int numberRange = 255; // For choice 2, the range is 1-255
      roundedResult = (int) Math.round(Math.log(numberRange + 1) / Math.log(2));
      int[][] dayForNumber = new int[roundedResult][(numberRange + 1) / 2];
      guessNumber.Builder(numberRange, dayForNumber); // Building the sets for numbers

      // Manually build the Testday array for numbers
      int[][] TestdayForNumber = new int[roundedResult][(numberRange + 1) / 2];
      for (int i = 1; i <= numberRange; i++) {
         int temp = i;
         for (int j = TestdayForNumber.length - 1; j >= 0; j--) {
            if (temp >= Math.pow(2, j)) {
               temp -= Math.pow(2, j);
               for (int k = 0; k < TestdayForNumber[j].length; k++) {
                  if (TestdayForNumber[j][k] == 0) {
                     TestdayForNumber[j][k] = i;
                     break;
                  }
               }
            }
         }
      }

      // Compare the 'day' array for numbers from the GuessBirthday instance with the
      // manually built Testday array
      assertArrayEquals(dayForNumber, TestdayForNumber);
   }
}
