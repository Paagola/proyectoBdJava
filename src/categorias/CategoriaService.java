package categorias;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import interfaces.CRUD;

public class CategoriaService implements CRUD<Categoria>{

    private Connection conn;
    private Statement state;

    public CategoriaService(Connection conn) throws SQLException{
        this.conn = conn;
        this.state = this.conn.createStatement();
    }


    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id = " + id;

        if (state.executeUpdate(sql) == 1) {
            System.out.println("Fila eliminada con exito");
            return true;
        } else {
            System.out.println("No se pudo eliminar la fila");
            return false;
        }
    }

    @Override
    public ArrayList<Categoria> requestAll() throws SQLException {
        String sql = "SELECT c.id, c.nombre FROM categorias c ORDER BY c.id";

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
        String sql = String.format("INSERT INTO categorias (id, nombre) VALUES (%d, '%s')", p.getId(), p.getNombre());

        if (state.executeUpdate(sql) == 1) {
            System.out.println("Categoria anadida con exito");
            return 1;
        } else {
            System.out.println("No se pudo anadir la categoria");
            return 0;
        }
    }

    @Override
    public Categoria update(Categoria p) throws SQLException {
        String sql = String.format("UPDATE categorias SET nombre = '%s' WHERE id = %s", p.getNombre(), p.getId());

        if (state.executeUpdate(sql) == 1) {
            System.out.println("Columna modificada con exito");
            return p;
        } else {
            System.out.println("Error al modificar");
            return null;
        }
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
