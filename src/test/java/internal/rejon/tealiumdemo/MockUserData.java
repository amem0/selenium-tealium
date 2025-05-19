package internal.rejon.tealiumdemo;

public class MockUserData {
    public String firstName = "John";
    public String middleName = "Smith";
    public String lastName = "Doe";
    public String emailAddress = "nothankyou@example.org";
    public String password = "password";

    public boolean signUp4Newsletter = false;
    public boolean rememberMe = false;

    public MockUserData() {}

    public MockUserData(String firstName, String middleName, String lastName, String emailAddress, String password,
                        boolean signUp4Newsletter, boolean rememberMe) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.signUp4Newsletter = signUp4Newsletter;
        this.rememberMe = rememberMe;
    }
}
