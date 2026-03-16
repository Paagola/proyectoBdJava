package productos;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private int categoria;
    
    public Producto(int id, String nombre, double precio, int stock, int categoria)  {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

     public Producto(int id, String nombre, double precio, int stock)  {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getCategoria_id() {
        return categoria;
    }

    public void setCategoria_id(int categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {

        if (this.categoria == 0) {
            return String.format("id: %-5d | nombre: %-22s | precio: %-7.2f | stock: %-4d | categoria: null", id, nombre, precio, stock); 
        } else {
            return String.format("id: %-5d | nombre: %-22s | precio: %-7.2f | stock: %-4d | categoria: %s", id, nombre, precio, stock, categoria); 
        }
        
    }
}