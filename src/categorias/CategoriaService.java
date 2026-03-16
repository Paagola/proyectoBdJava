package categorias;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import interfaces.CRUD;
import productos.Producto;

public class CategoriaService implements CRUD<Categoria>{

    private Connection conn;
    private Statement state;

    public CategoriaService(Connection conn) throws SQLException{
        this.conn = conn;
        this.state = this.conn.createStatement();
    }


    @Override
    public boolean delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<Categoria> requestAll() throws SQLException {
        String sql = "SELECT c.id, c.nombre FROM categorias c";

        ResultSet query = state.executeQuery(sql);
        
        ArrayList<Categoria> categorias = new ArrayList<>();

        while(query.next()){
            int id = query.getInt("id");
            String nombre = query.getString("nombre"); 

            categorias.add(new Categoria(id, nombre));
        }

        return categorias;
    }

    @Override
    public int create(Categoria p) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Categoria update(Categoria p) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


    @Override
    public Categoria requestById(int id) throws SQLException {
        String sql = "SELECT id, nombre FROM categorias WHERE id = " + id+";";

        ResultSet res = state.executeQuery(sql);

        if (res.next()) {
            int ide = res.getInt("id");
            String nombre = res.getString("nombre");
            return new Categoria(ide, nombre);
        }

        return null;

    }
}
