package application.test;

import application.Farm;
import application.FileManager;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * Contains JUnit test methods for FileManager.
 */
public class TestFileManager {
    private FileManager fm;

    @BeforeEach
    public void setup() {
        fm = new FileManager();
    }

    /**
     * Loads a simple CSV containing one farm, and checks that it was read correctly
     */
    @Test
    public void loadSimpleCsv() {
        String csvContents = "date,farm_id,weight\n" +
                "2020-4-9,1,20\n" +
                "2020-4-10,1,18";

        Farm farm = fm.parse(csvContents);

        Assert.assertEquals(1, farm.getFarmIDs().size());
        Assert.assertEquals("1", farm.getFarmIDs().get(0));

        Assert.assertEquals(2, farm.viewValues().size());
        Assert.assertEquals(20, farm.getValues("04092020").get(0).getMilkWeight());
        Assert.assertEquals(18, farm.getValues("04102020").get(0).getMilkWeight());
    }

    /**
     * Loads a CSV that has extra columns (i.e. not date, farm_id, or weight), and makes sure they were ignored and the
     * relevant data was still read
     */
    @Test
    public void ignoreExtraData() {
        String csvContents = "farm_id,date,number_workers,weather,weight\n" +
                "1,2020-4-20,14,Sunny,435\n" +
                "1,2020-4-21,13,Cloudy,444";

        Farm farm = fm.parse(csvContents);

        Assert.assertEquals(1, farm.getFarmIDs().size());
        Assert.assertEquals("1", farm.getFarmIDs().get(0));

        Assert.assertEquals(2, farm.getAllDetails().size());
        Assert.assertEquals(435, farm.getValues("04202020").get(0).getMilkWeight());
        Assert.assertEquals(444, farm.getValues("04212020").get(0).getMilkWeight());
    }

    /**
     * Inputs a CSV that doesn't have one of the necessary columns, and checks that a parsing exception is thrown.
     */
    @Test
    public void missingColumns() {
        String csvContents = "date,farm_id\n" +
                "2019-11-28,Farm 1";

        // Since this CSV is missing the weight column, there should be a ParsingException when we try to load the data
        try {
            fm.parse(csvContents);

            // That should have thrown an exception and we should be in the catch block by now
            // If not, there's something wrong
            Assert.fail("Parsing CSV with missing columns should fail");
        } catch (FileManager.ParsingException ex) {
            // Success, we got an exception because there's a missing column
        }
    }

    /**
     * Tests that the farm ID can be any string, not just one that has the format 'Farm X'. (see question @1260 on
     * Piazza)
     */
    @Test
    public void farmIdAnyString() {
        String csvContents = "date,farm_id,weight\n" +
                "2020-4-21,This is some random farm Id that does not conform to the 'Farm X' format,1";

        Farm farm = fm.parse(csvContents);
        Assert.assertEquals(1, farm.getFarmIDs().size());
    }

    @Test
    public void readFarmsFromDisk() throws IOException {
        // TODO Fix the Git repository structure so that the root of the repo isn't inside the application folder
        Farm farm = fm.load("application/test/test_2019-5.csv");

        Assert.assertTrue(farm.getFarmIDs().contains("Farm 18"));

        List<Farm.Details> may4Details = farm.getValues("05042019");
        Assert.assertTrue(may4Details.stream().anyMatch(details ->
                details.getFarmID().equals("Farm 18") && details.getMilkWeight() == 2245));
    }

    @Test
    public void serializeSimpleFarm() throws Exception {
        Farm farm = new Farm();
        farm.add("04292020", "Farm 1", "9");
        farm.add("04302020", "Farm 1", "10");

        String serialized = fm.serialize(farm);

        System.out.println("Serialized farm: " + serialized);
        Assert.assertTrue(serialized.startsWith("date,farm_id,weight\n"));
        Assert.assertTrue(serialized.contains("2020-4-29,Farm 1,9\n"));
        Assert.assertTrue(serialized.contains("2020-4-30,Farm 1,10\n"));
    }
}
