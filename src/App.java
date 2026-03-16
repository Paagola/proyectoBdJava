import java.sql.Connection;
import java.util.ArrayList;

import categorias.Categoria;
import categorias.CategoriaService;
import conection.MiConexion;
import productos.Producto;
import productos.ProductoService;

public class App {
    public static void main(String[] args) throws Exception {

        String url = "jdbc:postgresql://aws-1-eu-west-1.pooler.supabase.com:6543/postgres";
        String user = "postgres.iguzjgdmruvfoynljgga";
        String pass = "Contraseña1234";
        MiConexion conection = new MiConexion(url, user, pass);

        Connection conn = conection.conectar();

        if (conn != null) {
            System.out.println("Conexion exitosa");

            CategoriaService cate_con = new CategoriaService(conn);
            ProductoService conex_prods = new ProductoService(conn);

            boolean salir = true;

            while (salir) {
                menu();
                int opcion = Integer.parseInt(System.console().readLine("-> "));

                switch (opcion) {
                    case 1:
                        menProds();
                        int opProds = Integer.parseInt(System.console().readLine("-> "));

                        switch (opProds) {
                            case 1: // mostrar productos
                                System.out.println("\n----------MOSTRAR PRODUCTOS------------");
                                ArrayList<Producto> productos = conex_prods.requestAll();

                                if (productos.size() != 0) {
                                    for (Producto producto : productos) {
                                        System.out.println(producto.toString());
                                    }
                                } else {
                                    System.out.println("Lo sentimos aun no hay ningun producto");
                                }

                                break;

                            case 2: // Anadir productos

                                System.out.println("\n----------ANADIR PRODUCTO------------"); {

                                try {
                                    int id = Integer.parseInt(System.console().readLine("Id: "));
                                    String nombre = System.console().readLine("Nombre: ");
                                    double precio = Double.parseDouble(System.console().readLine("Precio: "));
                                    int stock = Integer.parseInt(System.console().readLine("Stock: "));


                                    if (conex_prods.create(new Producto(id, nombre, precio, stock)) == 1) {
                                    System.out.println("Producto creado con exito");
                                }
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Error, se han introducidos datos no validos");
                                } catch (Exception e) {
                                    System.out.println("Error inesperado, vuelve a intentarlos mas tarde.");
                                }
                                

                                
                            }

                                break;

                            case 3: // Buscar por id
                                System.out.println("\n-------------BUSCAR ID----------------");
                                 {
                                int id = Integer.parseInt(System.console().readLine("-> "));
                                System.out.println(conex_prods.requestById(id).toString());
                                }

                                break;

                            case 4: // Actualizar producto
                            System.out.println("\n-------------ACTUALIZAR----------------");
                            {
                                try {
                                    int id = Integer.parseInt(System.console().readLine("Id: "));
                                    String nombre = System.console().readLine("Nombre: ");
                                    double precio = Double.parseDouble(System.console().readLine("Precio: "));
                                    int stock = Integer.parseInt(System.console().readLine("Stock: "));


                                    Producto producto = new Producto(id, nombre, precio, stock);
                                    producto = conex_prods.update(producto);
                                    System.out.println("Producto actualizado: " + producto.toString());
                                }
                                catch (IllegalArgumentException e) {
                                    System.out.println("Error, se han introducidos datos no validos");
                                } catch (Exception e) {
                                    System.out.println("Error inesperado, vuelve a intentarlos mas tarde.");
                                }
                            }

                                break;

                            case 5: // Eliminar producto
                            System.out.println("\n-------------ELIMINAR----------------");
                            {
                                int id = Integer.parseInt(System.console().readLine("Id: "));
                                if (conex_prods.delete(id)) {
                                    System.out.println("\nProducto " + id + " eliminado correctamente");
                                }
                            }

                            default:
                                break;
                        }

                        break;
                    case 2: //CATEGORIAS

                        menCate();
                        int opcionCate = Integer.parseInt(System.console().readLine("-> "));

                        switch (opcionCate) {
                            case 1:
                                System.out.println("\n----------MOSTRAR CATEGORIAS------------");
                                ArrayList<Categoria> categorias = cate_con.requestAll();

                                if (categorias.size() != 0) {
                                    for (Categoria categoria : categorias) {
                                        System.out.println(categoria.toString());
                                    }
                                } else {
                                    System.out.println("Lo sentimos aun no hay ningun producto");
                                }
                                break;
                            
                            case 2:
                                conex_prods.requestProdCat();
                            break;
                            case 3: //Anadir categoria
                                
                                System.out.println("\n----------ANADIR CATEGORIA-----------"); 
                            
                            {

                                try {
                                    int id = Integer.parseInt(System.console().readLine("Id: "));
                                    String nombre = System.console().readLine("Nombre: ");


                                    if (cate_con.create(new Categoria(id, nombre)) == 1) {
                                    System.out.println("Producto creado con exito");
                                }
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Error, se han introducidos datos no validos");
                                } catch (Exception e) {
                                    System.out.println("Error inesperado, vuelve a intentarlos mas tarde." + e.getMessage());
                                }
                            }

                                break; 

                            case 4: //Actualizar categoria
                                System.out.println("\n----------ACTUALIZAR CATEGORIA-----------"); 

                                {
                                    int id = Integer.parseInt(System.console().readLine("Id: "));
                                    String nombre = System.console().readLine("Nombre: ");

                                    cate_con.update(new Categoria(id, nombre));
                                       
                                }

                                break;

                            case 5: //Eliminar categoria


                                System.out.println("\n----------ELIMINAR CATEGORIA-----------"); 

                                {
                                    int id = Integer.parseInt(System.console().readLine("Id: "));

                                    cate_con.delete(id);
                                }
                                break;
                        
                            default:
                                System.out.println("Opcion invalida");
                                break;
                        }

                        break;

                    case 3:
                        if (conection.desconectar()) {
                            System.out.println("Desconexion exitosa");
                        }
                        salir = false;
                        break;

                    default:
                        System.out.println("Opcion inválida");
                        break;
                }
            }

        } else {
            System.out.println("Se produjo un error con la conexion, vuelve a intentarlo mas tarde.");
        }

        if (conn != null) {
            conection.desconectar();
        }

    }

    public static void menu() {
        System.out.println("""
                \n1. Productos
                2. Categoria
                3. Salir
                """);
    }

    public static void menProds() {
        System.out.println("""
                1. Mostrar productos
                2. Anadir producto
                3. Buscar producto por id
                4. Actualizar producto
                5. Eliminar producto
                """);
    }

    public static void menCate() {
        System.out.println("""
                1. Mostrar categorias
                2. Nombre de Producto y Nombre de Categoria
                3. Anadir categoria
                4. Actualizar categoria
                5. Eliminar categoria
                """);
    }

    //
}
