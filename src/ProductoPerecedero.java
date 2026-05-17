import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProductoPerecedero extends Producto{

    private LocalDate fechaVencimiento;
    private int diasParaVencer;

    public ProductoPerecedero(String codigo, String nombre, int cantidad,
                              double precio, String categoria,
                              LocalDate fechaVencimiento) {
        super(codigo, nombre, cantidad, precio, categoria);
        this.fechaVencimiento = fechaVencimiento;
    }


    @Override
    public double calcularValorTotal() {
        double valor = getCantidad() * getPrecio();
        setValorTotal(valor);
        return getValorTotal();
    }

    @Override
    public boolean requiereAlerta() {
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), fechaVencimiento);
        this.diasParaVencer = (int) dias;
        return getCantidad() < 5 || dias <= 7;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }

    public void setDiasParaVencer(int diasParaVencer) {
        this.diasParaVencer = diasParaVencer;
    }
}
