package Configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ScreenShotConfiguration {
    private String ScreenshotStorage;
    private String ScreenshotTrigger;
    private String ScreenshotDirectory;
}
