package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenBMIIsNotCorrect(){
        //given
        double weight = 69.5;
        double height = 1.72;
        //when
        boolean bmi = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(bmi);
    }

    @Test
    void shouldExcept_IllegalArgumentException(){
        //given
        double weight = 69.5;
        double height = 0.;
        //then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }
    @ParameterizedTest(name = "{0} weight")
    @ValueSource(doubles = {89.2, 89.4, 89.6})
    void shouldReturnTrue_whenBMIIsCorrect(double weight) {
        //given
        double height = 1.72;
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);
        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "{0} weight, {1} height")
    @CsvSource({"69.5,1.72", "63.3,1.74", "79.3,1.82"})
    void shouldReturnFalse_whenBMIIsNotCorrectCsvSource(double weight, double height) {
        //when
        boolean state = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(state);

    }

    @ParameterizedTest(name = "{0} weight, {1} height")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_whenBMIIsNotCorrectCsvFileSource(double weight, double height) {
        //when
        boolean fromCSV = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(fromCSV);
    }

    @Test
    void shouldReturnUserWithTheWorstBMI() {
        //when
        User user = FitCalculator.findUserWithTheWorstBMI(TestConstants.TEST_USERS_LIST);
        //then
        assertAll("Should return user with the worst BMI",
                () -> assertEquals(97.3, user.getWeight()),
                () -> assertEquals(1.79, user.getHeight()
        ));
    }

    @Test
    void shouldReturnNullForEmptyUsersList() {
        //when
        User user = FitCalculator.findUserWithTheWorstBMI(Collections.emptyList());
        //then
        assertNull(user);
    }

    @Test
    void shouldReturnTestUsersBMIScore() {
        //when
        double[] BMIScore = FitCalculator.calculateBMIScore(TestConstants.TEST_USERS_LIST);
        //then
        assertArrayEquals(TestConstants.TEST_USERS_BMI_SCORE, BMIScore);
    }
}