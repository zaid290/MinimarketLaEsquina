import java.time.LocalDate;

public abstract class Producto {

    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    private String categoria;
    private LocalDate fechaIngreso;
    private double valorTotal;


    public Producto(String codigo, String nombre, int cantidad,
                    double precio, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaIngreso = LocalDate.now();
    }



    public abstract double calcularValorTotal();

    public abstract boolean requiereAlerta();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString(){

        return "Código: " + codigo +
                " Nombre: " + nombre +
                " Categoría: " + categoria +
                " Cantidad: " + cantidad +
                " Precio: $" + precio +
                " Valor Total: $" + valorTotal + '\n';
    }

}
