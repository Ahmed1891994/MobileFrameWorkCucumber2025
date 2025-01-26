package Configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DriverConfiguration {
    private int timeoutInSeconds;
    private int intervalInMillis;
    private int waitForShortPeriodInSeconds;
    private int intervalForShortPeriodInMillis;
    private int newCommandTimeoutInMinutes;
}
