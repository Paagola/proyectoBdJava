package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUD<Producto> {

    public int create(Producto p) throws SQLException;
    public Producto requestById(int id) throws SQLException;
    public Producto update(Producto p) throws SQLException;
    public boolean delete(int id) throws SQLException;
    public ArrayList<Producto> requestAll() throws SQLException;
}
