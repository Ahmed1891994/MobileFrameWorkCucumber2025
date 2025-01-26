package Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum UserType {
    VALID_STANDARD(UserData::getValiduserstandard),
    LOCKED_USER(UserData::getLockeduser),
    VALID_PREMIUM(UserData::getValiduserpremium);

    private static final Logger logger = LoggerFactory.getLogger(UserType.class);
    private final UserCredentialsGetter credentialsGetter;

    UserType(UserCredentialsGetter credentialsGetter) {
        this.credentialsGetter = credentialsGetter;
    }

    /**
     * Retrieves the user credentials for the specified user type.
     *
     * @param userData The UserData object containing user credentials.
     * @return The UserCredentials object for the specified user type.
     */
    public UserCredentials getCredentials(UserData userData) {
        logger.debug("Retrieving credentials for user type: {}", this.name());
        UserCredentials credentials = credentialsGetter.getCredentials(userData);
        logger.debug("Retrieved credentials for user type {}: {}", this.name(), credentials);
        return credentials;
    }

    @FunctionalInterface
    private interface UserCredentialsGetter {
        UserCredentials getCredentials(UserData userData);
    }
}