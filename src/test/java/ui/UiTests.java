package ui;

import managers.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.VehicleFinder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UiTests {

    private static final Logger logger = LoggerFactory.getLogger(UiTests.class);
    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
    }

    @BeforeMethod
    public void beforeTest() {
        driver = LocalDriverManager.getDriver();
    }

    @AfterMethod
    public void afterTest() {
        LocalDriverManager.removeDriver();
    }


    public int[] twoSum(int[] nums, int target) {
        int[] result = {};

        for (int i = 0; i < nums.length - 1; i++) {
            int sum = 0;

            for (int a = i + 1; i < nums.length; i++) {
                sum = nums[i] + nums[a];

                if (sum == target) {

                    return new int[]{nums[i], nums[a]};
                }
            }


        }
        return result;
    }

    @Test
    public void chechSum() {
        int target = 9;
        int[] nums = {2, 7, 11, 15};


    }

    @Test
    public void openCopart() {
        logger.info("Starting test ");
        VehicleFinder finder = new VehicleFinder();
        finder.openPage()
                .selectMake("Acura")
                .selectYears("2015", "2019");

    }

    @Test
    public void luxTests() {
        final ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(1);
        objects.add(2);
        objects.add(3);

        final Set<Integer> collect = new HashSet<>(objects);

        System.out.println(collect);
    }

    public boolean isSimple(int number) {
        boolean isSimple = true;

        for (int i = 2; i < number; i++) {
            double result = number % i;
            if (result == 0) isSimple = false;
        }
        
        return isSimple;
    }
}
