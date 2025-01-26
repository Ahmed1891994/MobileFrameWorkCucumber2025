package Configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AndroidConfiguration {
    private String appPackage;
    private String appActivity;
    private int adbExecuteTimeoutInMinutes;
    private boolean getAutoGrantPermissions;
}