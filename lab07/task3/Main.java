package lab07.task3;

public class Main {

    public static void main(
            String[] args) {

        UserAccount user =
                new UserAccount("Marat");

        SystemServiceAccount service =
                new SystemServiceAccount(
                        "BackupService");

        user.deleteAccount();

        System.out.println(
                service.getUsername());
    }
}