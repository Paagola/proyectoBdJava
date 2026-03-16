package conection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiConexion {

    private String url;
    private String user;
    private String password;
    Connection conn;

    public MiConexion(String url, String user, String password) throws SQLException {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection conectar() {
        this.conn = null;

        try {
            this.conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return null;
        }
    }

    public boolean desconectar() throws SQLException {

        if (conn != null) {
            try{
            conn.close();
            return true;
            }catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("No se ha establecido ninguna conexion todavia");
            return false;
        }
        
    }
}

// 
//
