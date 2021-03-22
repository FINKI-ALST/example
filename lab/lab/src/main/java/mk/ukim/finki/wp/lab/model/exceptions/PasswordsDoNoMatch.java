package mk.ukim.finki.wp.lab.model.exceptions;

public class PasswordsDoNoMatch extends RuntimeException{
    public PasswordsDoNoMatch() {
        super("Passwords do not match");
    }
}
