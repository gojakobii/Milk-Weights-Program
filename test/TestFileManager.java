package application.test;

import application.Farm;
import application.FileManager;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

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
                "2020-4-20,Farm 1,20\n" +
                "2020-4-21,Farm 1,18";

        Map<String, Farm> farms = fm.parse(csvContents);

        Assert.assertEquals(1, farms.size());
        Farm farm = farms.get("Farm 1");
        Assert.assertNotNull(farm);
        Assert.assertEquals("Farm 1", farm.getFarmID());
        Assert.assertEquals(20, farm.getRecord("2020-4-20"));
        Assert.assertEquals(18, farm.getRecord("2020-4-21"));
    }

    /**
     * Loads a CSV that has extra columns (i.e. not date, farm_id, or weight), and makes sure they were ignored and the
     * relevant data was still read
     */
    @Test
    public void ignoreExtraData() {
        String csvContents = "farm_id,date,number_workers,weather,weight\n" +
                "Farm 1,2020-4-20,14,Sunny,435\n" +
                "Farm 1,2020-4-21,13,Cloudy,444";

        Map<String, Farm> farms = fm.parse(csvContents);

        Assert.assertEquals(1, farms.size());
        Farm farm = farms.get("Farm 1");
        Assert.assertNotNull(farm);
        Assert.assertEquals("Farm 1", farm.getFarmID());
        Assert.assertEquals(435, farm.getRecord("2020-4-20"));
        Assert.assertEquals(444, farm.getRecord("2020-4-21"));
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

        Map<String, Farm> farms = fm.parse(csvContents);
        Assert.assertEquals(1, farms.size());
    }

    @Test
    public void readFarmsFromDisk() throws IOException {
        // TODO Fix the Git repository structure so that the root of the repo isn't inside the application folder
        Map<String, Farm> farms = fm.load("application/test/test_2019-5.csv");

        Farm farm = farms.get("Farm 18");
        Assert.assertEquals("Farm 18", farm.getFarmID());
        Assert.assertEquals(2387, farm.getRecord("2019-5-1"));
        Assert.assertEquals(2503, farm.getRecord("2019-5-2"));
        Assert.assertEquals(2573, farm.getRecord("2019-5-30"));
        Assert.assertEquals(2267, farm.getRecord("2019-5-31"));
    }
}
