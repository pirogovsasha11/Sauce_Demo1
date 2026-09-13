package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withLockedPermission() {
        return new User(PropertyReader.getProperty("saucedemo.locked.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withEmptyUserPermission() {
        return new User(PropertyReader.getProperty("saucedemo.empty.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withEmptyPasswordPermission() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.empty.password"));
    }

    public static User withErrorPermission() {
        return new User(PropertyReader.getProperty("saucedemo.error.password"),
                PropertyReader.getProperty("saucedemo.password"));
    }
}
