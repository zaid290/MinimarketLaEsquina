public class ProductoNoPerecedero extends Producto{

    private String material;

    public ProductoNoPerecedero(String codigo, String nombre, int cantidad,
                                double precio, String categoria,
                                String material) {
        super(codigo, nombre, cantidad, precio, categoria);
        this.material = material;
    }


    @Override
    public double calcularValorTotal() {
        double valor = getCantidad() * getPrecio();
        setValorTotal(valor);
        return getValorTotal();
    }

    @Override
    public boolean requiereAlerta() {
        return getCantidad() < 5;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
