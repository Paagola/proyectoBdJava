package productos;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import interfaces.CRUD;


public class ProductoService implements CRUD<Producto> {

    private Connection con;
    private Statement statement;
    private int max_id;
    ArrayList<Producto> productos;

    public ProductoService(Connection con) throws SQLException {
        this.con = con;
        this.statement = this.con.createStatement();
        this.max_id = Integer.MIN_VALUE;
        this.productos = requestAll();
    }

    public ArrayList<Producto> requestAll() throws SQLException {
        ArrayList<Producto> prods = new ArrayList<>();

        String sql = "SELECT id, nombre, precio, stock, categoria_id  FROM productos ORDER BY id;";

        ResultSet query = statement.executeQuery(sql);

        while (query.next()) {
            int id = query.getInt("id");
            String nombre = query.getString("nombre");
            double precio = query.getDouble("precio");
            int stock = query.getInt("stock");
            int cat = query.getInt("categoria_id");

            max_id = Math.max(id, max_id);

            prods.add(new Producto(id, nombre, precio, stock, cat));
        }

        return prods;

    }

    @Override
    public int create(Producto p) throws SQLException {
        int id = p.getId();
        String nombre = p.getNombre();
        double precio = p.getPrecio();
        int stock = p.getStock();
        
        if (id <= max_id){
            max_id++;
            id = max_id;
        }

        String sql = String.format("INSERT INTO productos (id, nombre, precio, stock) VALUES (%d, '%s', %.2f, %d);", id,
                nombre, precio, stock);

        if (statement.executeUpdate(sql) == 1) {
            return p.getId();
        } else {
            return 0;
        }
    }

    @Override
    public Producto requestById(int id) throws SQLException {
        String sql = "SELECT * FROM productos WHERE id = " + id;

        ResultSet query = statement.executeQuery(sql);
        if (query.next()) {
            String nombre = query.getString("nombre");
            double precio = query.getDouble("precio");
            int stock = query.getInt("stock");

            return new Producto(id, nombre, precio, stock);
        } else {
            return null;
        }
    }

    @Override
    public Producto update(Producto p) throws SQLException {
        Producto prod = requestById(p.getId());

        prod.setNombre(p.getNombre());
        prod.setPrecio(p.getPrecio());
        prod.setStock(p.getStock());

        String sql = String.format("UPDATE productos SET nombre = '%s', precio = %.2f, stock = %d WHERE id = %d",
                p.getNombre(), p.getPrecio(), p.getStock(), p.getId());

        if (statement.executeUpdate(sql) == 1) {
            System.out.println("Producto actualizado.");
            return prod;
        } else {
            System.out.println("Error al actualizar el producto.");
            return null;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {

        String sql = "DELETE FROM productos WHERE id = " + id+";";

        if (statement.executeUpdate(sql) == 1) {
            System.out.println("Producto actualizado.");
            return true;
        }
        System.out.println("Error al actualizar el producto.");
        return false;

    }

    public void requestProdCat() throws SQLException{

        String sql = "SELECT p.nombre, c.nombre AS categoria FROM productos p LEFT JOIN categorias c ON p.categoria_id = c.id;";

        ResultSet res = statement.executeQuery(sql);

        while (res.next()) {
            String nombreP = res.getString("nombre");
            String nombreC = res.getString("categoria");
            System.out.printf("%-25s | %-10s%n", nombreP, nombreC);
        }
    }
}
