package hello.jdbc.connection;

public abstract class ConnectionConst {
    //불변값이니 static final
    public static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";
}
