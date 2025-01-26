package Configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class iOSConfiguration {
    private String bundleId;
    private String path;
    private String platformVersion;
    private String udid;
    private int wdaConnectionTimeoutInMinutes;
}
