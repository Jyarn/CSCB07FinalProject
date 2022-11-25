package login;
import ddb.Database;

public abstract class User {
    protected String type; // usr type
    protected Database ddbLoc; // location of user data
    protected String username;

    protected User (Database loc, String type) {
        this.ddbLoc = loc;
        this.type = type;
    }

    public boolean login (String password) {
        return false;
    }

    public void register (String password) {
        ddbLoc.write(username);

        ddbLoc.cd("password");
        ddbLoc.write(password);
        ddbLoc.cd("..");
    }

    public boolean isRegistered() {
        return ddbLoc.read() != null;
    }

    public abstract boolean push ();
    public abstract boolean pull ();
}
