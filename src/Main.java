import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Inventario inventario = new Inventario(5000.00, 1000, sc);
        int opcion = -1;

        while (opcion != 0){

            System.out.println("\n=== MINIMARKET LA ESQUINA ===");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar inventario");
            System.out.println("3. Consultar producto");
            System.out.println("4. Actualizar stock");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Ver alertas");
            System.out.println("7. Reporte general");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion){
                case 1:
                    inventario.registrarProducto();
                    break;
                case 2:
                    inventario.listarInventario();
                    break;
                case 3:
                    inventario.consultarProducto();
                    break;
                case 4:
                    inventario.actualizarStock();
                    break;
                case 5:
                    inventario.eliminarProducto();
                    break;
                case 6:
                    inventario.verAlertas();
                    break;
                case 7:
                    inventario.reporteGeneral();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        sc.close();
    }
}
