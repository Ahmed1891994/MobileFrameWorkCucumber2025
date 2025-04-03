package Configurations;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Configuration {
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
    private AndroidConfiguration androidConfiguration;
    private iOSConfiguration iOSConfiguration;
    private DriverConfiguration driverConfiguration;
    private ScreenShotConfiguration screenShotConfiguration;

    public Configuration readConfigurations() {
        Gson gson = new Gson();
        Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "Configuration.json");
        logger.debug("Attempting to read configuration file from path: {}", filePath);

        try (Stream<String> stream = Files.lines(filePath)) {
            String source = stream.collect(Collectors.joining(System.lineSeparator()));
            logger.debug("Successfully read configuration file. Binding data to Configuration class.");
            return gson.fromJson(source, Configuration.class);
        } catch (IOException e) {
            logger.error("Failed to read the configuration file from path: {}", filePath, e);
            throw new RuntimeException("Failed to read the configuration file: " + filePath, e);
        }
    }
}