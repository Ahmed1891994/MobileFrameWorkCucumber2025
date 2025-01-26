package Data; // This should match the package name (folder structure)

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentials {
    private String username;
    private String password;
}