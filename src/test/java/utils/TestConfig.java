package utils;

public class TestConfig {

    public static void main(String[] args) {

        System.out.println(
                ConfigReader.getProperty("browser"));

        System.out.println(
                ConfigReader.getProperty("url"));
    }
}