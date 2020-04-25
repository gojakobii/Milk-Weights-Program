package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Allows saving/loading the farm data to disk, using CSV files.
 */
public class FileManager {
    /**
     * Reads and parses the given CSV file to a map of farm IDs to farms.
     *
     * @param filename the name of the CSV file to load
     * @return a map containing the farms that were in the CSV. It's possible to iterate through these by using
     * {@link Map#values()}.
     */
    public Map<String, Farm> load(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder contents = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            contents.append(line);
            contents.append("\n");
        }

        return parse(contents.toString());
    }

    /**
     * Parse the given contents from a CSV file to a map of farm IDs to farms.
     *
     * @param contents the contents from a CSV file
     * @return a map containing the farms that were in the CSV. It's possible to iterate through these by using
     * {@link Map#values()}.
     */
    public Map<String, Farm> parse(String contents) {
        String[] lines = contents.trim().split("\\r?\\n"); // split by newline

        // Maps farmID to the corresponding farm object
        Map<String, Farm> farms = new HashMap<>();

        // Index of each column in the CSV file
        // E.g. if the first column was the date, then dateColumnIndex would be set to 0
        // These are needed so we know the position of each column
        int dateColumnIndex = -1;
        int farmIDColumnIndex = -1;
        int weightColumnIndex = -1;

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].isBlank()) continue;

            String[] columns = lines[i].split(",");

            if (i == 0) {
                // This is the first line, it contains the CSV file's columns
                // e.g. date,farm_id,weight

                // We want to record the index of the date column, the farm ID column, and the weight column so that we
                // know where these are for later rows

                for (int j = 0; j < columns.length; j++) {
                    String column = columns[j];
                    if (column.equals("date")) dateColumnIndex = j;
                    else if (column.equals("farm_id")) farmIDColumnIndex = j;
                    else if (column.equals("weight")) weightColumnIndex = j;
                }

                // Make sure the date, farm_id, and weight columns were all found
                if (dateColumnIndex == -1 || farmIDColumnIndex == -1 || weightColumnIndex == -1)
                    throw new ParsingException("Missing one or more of these columns: date, farm_id, weight");
            } else {
                // This isn't the first line, so it will contain one data point for some farm
                String date = columns[dateColumnIndex];
                String farmID = columns[farmIDColumnIndex];
                String weightStr = columns[weightColumnIndex];

                // Convert weight to an int
                // (note: because the farm ID can be any string, we don't convert it to an int)
                int weight;
                try {
                    weight = Integer.parseInt(weightStr);
                } catch (NumberFormatException ex) {
                    throw new ParsingException("Couldn't parse weight '" + weightStr + "' as an integer", ex);
                }

                // Find or create the farm object
                Farm farm;
                if (farms.containsKey(farmID)) {
                    farm = farms.get(farmID);
                } else {
                    farm = new Farm(farmID);
                    farms.put(farmID, farm);
                }

                // Add data-point for this day into the farm
                farm.add(date, farmID, weightStr);
            }
        }

        // Return the farms map
        return farms;
    }

    public static class ParsingException extends RuntimeException {
        public ParsingException(String message) {
            super(message);
        }

        public ParsingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
