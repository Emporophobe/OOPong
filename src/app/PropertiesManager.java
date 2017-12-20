package app;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class PropertiesManager {
    private static PropertiesManager instance = null;
    private static Properties prop = new Properties();
    private static OutputStream output = null;

    private static Map<String, String> defaults = Map.ofEntries(
            Map.entry("width", "800"),
            Map.entry("height", "600"),
            Map.entry("players", "2"),
            Map.entry("max_score", "10"),
            Map.entry("paddle_width", "20"),
            Map.entry("paddle_height", "100"),
            Map.entry("paddle_speed", "15"),
            Map.entry("ball_size", "20"),
            Map.entry("max_balls", "5"),
            Map.entry("ball_speed", "7"),
            Map.entry("balls_collide", "true"),
            Map.entry("breakout", "false"),
            Map.entry("block_height", "30"),
            Map.entry("block_width", "20")

    );

    private PropertiesManager() {
        try {
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            output = new FileOutputStream("config.properties");
            setMissingProperties();
        } catch (FileNotFoundException e) {
            setDefaultProperties();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                prop.store(output, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setMissingProperties() {
        defaults.keySet().forEach(key -> {
            if (!prop.containsKey(key)) {
                prop.setProperty(key, defaults.get(key));
            }
        });
    }

    private static void setDefaultProperties() {
        // set the properties value
        defaults.keySet().forEach(key -> prop.setProperty(key, defaults.get(key)));

        // save properties to project root folder
        try {
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getIntProperty(String key) {
        if (instance == null) {
            instance = new PropertiesManager();
        }
        return Integer.parseInt(prop.getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        if (instance == null) {
            instance = new PropertiesManager();
        }
        return Boolean.parseBoolean(prop.getProperty(key));
    }
}
