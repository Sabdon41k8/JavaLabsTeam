package lab07.task3;

interface Account {

    String getUsername();
}

interface PasswordManageable {

    void changePassword(
            String oldPassword,
            String newPassword);
}

interface Deactivatable {

    void deleteAccount();
}

class UserAccount
        implements Account,
                   PasswordManageable,
                   Deactivatable {

    private String username;

    public UserAccount(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void changePassword(
            String oldPassword,
            String newPassword) {

        System.out.println(
                "Password changed for "
                        + username);
    }

    @Override
    public void deleteAccount() {

        System.out.println(
                "Account "
                        + username
                        + " deleted");
    }
}

class SystemServiceAccount
        implements Account {

    private String username;

    public SystemServiceAccount(
            String username) {

        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }
}