package com.algorithms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.BitSet;

@Component
public class BloomFilter {

    @Autowired
    private Environment environment;

    private static final int SIZE = 10000; // Size of the bit array
    private BitSet bitSet;

    // Use @Value to inject values from application.properties directly
    @Value("${bloomfilter.basePath}")
    private String baseFilePath;

    public BloomFilter() {
        this.bitSet = new BitSet(SIZE);
    }

    private void loadFromFile() {
        File directory = new File(baseFilePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, environment.getProperty("bloomfilter.bookFileName"));

        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                bitSet = (BitSet) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist, initializing a new BitSet.");
            saveToFile(); // Optionally save an empty BitSet to the file
        }
    }

    private void saveToFile() {
        File directory = new File(baseFilePath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it does not exist
        }

        File file = new File(directory, environment.getProperty("bloomfilter.bookFileName"));

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(bitSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String value) {
        int[] hashes = getHashes(value);
        for (int hash : hashes) {
            bitSet.clear(hash);
        }
        saveToFile();
    }

    public void add(String value) {
        int[] hashes = getHashes(value);
        for (int hash : hashes) {
            bitSet.set(hash);
        }
        saveToFile();
    }

    public boolean contains(String value) {
        int[] hashes = getHashes(value);
        for (int hash : hashes) {
            if (!bitSet.get(hash)) {
                return false; // If any position is 0, it's definitely not present
            }
        }
        return true; // All positions are 1, might be present
    }

    private int[] getHashes(String value) {
        int[] hashes = new int[3]; // For 3 hash functions
        for (int i = 0; i < 3; i++) {
            hashes[i] = Math.abs(value.hashCode() + i) % SIZE;
        }
        return hashes;
    }
}
