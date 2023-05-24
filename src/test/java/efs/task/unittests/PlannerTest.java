package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PlannerTest {
    Planner planner = new Planner();

    @BeforeEach
    public void beforeEach() {
        planner = new Planner();
    }

    @ParameterizedTest(name = "activityLevel = {0}")
    @EnumSource(ActivityLevel.class)
    void shouldCalculateDailyCaloriesDemand(ActivityLevel activityLevel) {
        //given
        User user = TestConstants.TEST_USER;
        int caloriesDemand = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);
        //when
        int calculateDemand = planner.calculateDailyCaloriesDemand(user, activityLevel);
        //then
        assertEquals(caloriesDemand, calculateDemand);
    }

    @Test
    void shouldCalculateDailyIntake() {
        //given
        User user = TestConstants.TEST_USER;
        //when
        DailyIntake dailyIntake = planner.calculateDailyIntake(user);
        //then
        assertAll(
                () -> assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getCalories(), dailyIntake.getCalories()),
                () -> assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getProtein(), dailyIntake.getProtein()),
                () -> assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getFat(), dailyIntake.getFat()),
                () -> assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getCarbohydrate(), dailyIntake.getCarbohydrate())
        );
    }
}
