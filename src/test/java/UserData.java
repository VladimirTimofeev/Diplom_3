import object.User;

public class UserData {

    public static User expectedCreateUser() {
        return new User("asdqwe@dsa.dsa", "123321", "Viktor");
    }

    public static User expectedUserWithoutIncorrectPassword() {
        return new User("asdqwe@dsa.dsa", "1233", "Viktor");
    }
}
