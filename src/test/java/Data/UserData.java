package Data; // This should match the package name (folder structure)

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData {
    private UserCredentials validuserstandard;
    private UserCredentials lockeduser;
    private UserCredentials validuserpremium;
}