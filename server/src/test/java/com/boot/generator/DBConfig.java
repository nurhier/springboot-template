package com.boot.generator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nurhier
 * @date 2020/3/7
 */
@Getter
@Setter
@ToString
public class DBConfig {
    /**
     * db url
     */
    private String url;
    /**
     * driver name
     */
    private String driverName;
    /**
     * db user name
     */
    private String userName;
    /**
     * db password
     */
    private String password;

    private static DBConfig dbConfig = new DBConfig();

    static {
        Yaml yaml = new Yaml();
        try (InputStream in = DBConfig.class.getClassLoader().getResourceAsStream("code-generator.yml");) {
            Map<String, Object> properties = yaml.loadAs(in, Map.class);
            properties.forEach((key, value) -> {
                try {
                    dbConfig.getClass().getDeclaredField(key).set(dbConfig, value);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConfig getInstance() {
        return dbConfig;
    }
}
