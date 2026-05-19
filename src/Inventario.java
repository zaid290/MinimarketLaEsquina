import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventario {

    private ArrayList<Producto> productos;
    private double presupuesto;
    private int capacidadMaxima;
    private Scanner sc;

    public Inventario(double presupuesto, int capacidadMaxima, Scanner sc) {
        this.productos = new ArrayList<>();
        this.presupuesto = presupuesto;
        this.capacidadMaxima = capacidadMaxima;
        this.sc = sc;
        cargarDatosIniciales();
    }


    public void cargarDatosIniciales(){
        productos.add(new ProductoPerecedero(
                "P001", "Leche entera 1L", 20, 1.20,
                "Lácteos", LocalDate.now().plusDays(15)));
        productos.add(new ProductoPerecedero(
                "P002", "Pan de molde", 8, 2.50,
                "Panadería", LocalDate.now().plusDays(5)));
        productos.add(new ProductoNoPerecedero(
                "N001", "Arroz 1kg", 50, 1.80,
                "Granos", "Plástico"));
        productos.add(new ProductoNoPerecedero(
                "N002", "Detergente 1L", 15, 3.50,
                "Limpieza", "Plástico"));
    }

    public void registrarProducto(){
        System.out.println("Tipo: 1) Perecedero  2) No perecedero");
        int tipo = Integer.parseInt(sc.nextLine());
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        if (codigo.isEmpty()){
            System.out.println("ERROR: El código es obligatorio.");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        if (nombre.isEmpty()){
            System.out.println("ERROR: El nombre es obligatorio.");
            return;
        }
        System.out.print("Cantidad: ");
        String sCant = sc.nextLine();
        if (sCant.isEmpty()){
            System.out.println("ERROR: La cantidad es obligatoria.");
            return;
        }
        int cantidad = Integer.parseInt(sCant);
        if (cantidad < 0){
            System.out.println("ERROR: La cantidad no puede ser negativa.");
            return;
        }
        System.out.print("Precio: ");
        double precio = Double.parseDouble(sc.nextLine());
        if (precio < 0){
            System.out.println("ERROR: El precio no puede ser negativo.");
            return;
        }
        System.out.print("Categoría: ");
        String categoria = sc.nextLine();
        if (categoria.isEmpty()){
            System.out.println("ERROR: La categoría es obligatoria.");
            return;
        }

        if (buscarPorCodigo(codigo) != null){
            System.out.println("ERROR: Ya existe un producto con código " + codigo);
            return;
        }

        if (tipo == 1){
            System.out.print("Fecha vencimiento (yyyy-MM-dd): ");
            LocalDate fecha = LocalDate.parse(sc.nextLine());
            productos.add(new ProductoPerecedero(
                    codigo, nombre, cantidad, precio, categoria, fecha));
        } else {
            System.out.print("Material: ");
            String material = sc.nextLine();
            if (material.isEmpty()){
                System.out.println("ERROR: El material es obligatorio.");
                return;
            }
            productos.add(new ProductoNoPerecedero(
                    codigo, nombre, cantidad, precio, categoria, material));
        }
        System.out.println("Producto registrado correctamente.");
    }

    public void listarInventario(){
        System.out.println("\n--- INVENTARIO ---");
        for (Producto p : productos){
            p.calcularValorTotal();
            System.out.println(p);
        }
    }

    public void consultarProducto(){
        System.out.print("Código a buscar: ");
        String cod = sc.nextLine();
        Producto p = buscarPorCodigo(cod);
        if (p != null){
            p.calcularValorTotal();
            System.out.println(p);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void actualizarStock(){
        System.out.println("1) Entrada  2) Salida");
        int t = Integer.parseInt(sc.nextLine());
        System.out.print("Código: ");
        String cod = sc.nextLine();
        System.out.print("Unidades: ");
        int u = Integer.parseInt(sc.nextLine());
        Producto p = buscarPorCodigo(cod);
        if (p == null){
            System.out.println("ERROR: Producto no encontrado.");
        } else if (t == 1) {
            p.setCantidad(p.getCantidad() + u);
            System.out.println("Entrada registrada.");
        } else if (t == 2) {
            if (u > p.getCantidad()){
                System.out.println("ERROR: Stock insuficiente. Stock actual: " + p.getCantidad());
            } else {
                p.setCantidad(p.getCantidad() - u);
                System.out.println("Salida registrada.");
            }
        }
    }

    public void eliminarProducto(){
        System.out.print("Código a eliminar: ");
        String cod = sc.nextLine();
        Producto p = buscarPorCodigo(cod);
        if (p != null) {
            productos.remove(p);
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void verAlertas(){
        System.out.println("\n--- ALERTAS ---");
        ArrayList<Producto> alertas = productosConAlerta();
        if (alertas.isEmpty()){
            System.out.println("Sin alertas.");
        } else {
            for (Producto p : alertas){
                p.calcularValorTotal();
                if (p instanceof ProductoPerecedero){
                    ProductoPerecedero pp = (ProductoPerecedero) p;
                    System.out.println("[ALERTA] " + p +
                            "Días para vencer: " + pp.getDiasParaVencer());
                } else {
                    System.out.println("[ALERTA STOCK BAJO] " + p);
                }
            }
        }
    }

    public void reporteGeneral(){
        System.out.println("\n--- REPORTE GENERAL ---");
        System.out.println("Productos distintos: " + productos.size());
        System.out.println("Unidades totales: " + totalUnidades() +
                " / " + capacidadMaxima);
        System.out.println("Valor total: $" + valorTotalInventario() +
                " / Presupuesto: $" + presupuesto);
        System.out.println("Productos con alerta: " + productosConAlerta().size());
    }

    public Producto buscarPorCodigo(String codigo){
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public int totalUnidades(){
        int total = 0;
        for (Producto p : productos) {
            total += p.getCantidad();
        }
        return total;
    }

    public double valorTotalInventario(){
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularValorTotal();
        }
        return total;
    }

    public ArrayList<Producto> productosConAlerta(){
        ArrayList<Producto> alertas = new ArrayList<>();
        for (Producto p : productos) {
            if (p.requiereAlerta()) {
                alertas.add(p);
            }
        }
        return alertas;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }
}