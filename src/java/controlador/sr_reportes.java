package controlador; // Cambia esto al paquete adecuado

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.conexion;
public class sr_reportes extends HttpServlet {

    private conexion cn;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipoReporte = request.getParameter("tipo");
        String formato = request.getParameter("formato"); // "csv" o "html"

        if (formato == null || tipoReporte == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros insuficientes.");
            return;
        }

        if (formato.equals("csv")) {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + tipoReporte + ".csv\"");
        } else if (formato.equals("html")) {
            response.setContentType("text/html");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato no válido.");
            return;
        }

        PrintWriter out = response.getWriter();

        if (formato.equals("csv")) {
            generarCSV(out, tipoReporte);
        } else {
            generarHTML(out, tipoReporte);
        }
    }

    private void generarCSV(PrintWriter out, String tipoReporte) {
        DefaultTableModel tabla = obtenerDatos(tipoReporte);

        if (tabla == null || tabla.getRowCount() == 0) {
            out.println("No se encontraron datos para el reporte.");
            return;
        }

        // Generar encabezado CSV
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            out.print(tabla.getColumnName(i));
            if (i < tabla.getColumnCount() - 1) out.print(",");
        }
        out.println();

        // Generar datos CSV
        for (int i = 0; i < tabla.getRowCount(); i++) {
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                out.print(tabla.getValueAt(i, j));
                if (j < tabla.getColumnCount() - 1) out.print(",");
            }
            out.println();
        }
    }

    private void generarHTML(PrintWriter out, String tipoReporte) {
        DefaultTableModel tabla = obtenerDatos(tipoReporte);

        if (tabla == null || tabla.getRowCount() == 0) {
            out.println("<p>No se encontraron datos para el reporte.</p>");
            return;
        }

        // Generar tabla HTML
        out.println("<table border='1'>");
        out.println("<tr>");
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            out.println("<th>" + tabla.getColumnName(i) + "</th>");
        }
        out.println("</tr>");

        for (int i = 0; i < tabla.getRowCount(); i++) {
            out.println("<tr>");
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                out.println("<td>" + tabla.getValueAt(i, j) + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
    }

    private DefaultTableModel obtenerDatos(String tipoReporte) {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new conexion();
            cn.abrir_conexion();

            String query = "";
            switch (tipoReporte) {
                case "proveedores":
                    query = "SELECT id_proveedor, proveedor, nit, direccion, telefono FROM proveedores;";
                    String[] encabezadoProveedores = {"ID", "Proveedor", "NIT", "Dirección", "Teléfono"};
                    tabla.setColumnIdentifiers(encabezadoProveedores);
                    break;

                case "marcas":
                    query = "SELECT id_marca, marca FROM marcas;";
                    String[] encabezadoMarcas = {"ID marca", "Marca"};
                    tabla.setColumnIdentifiers(encabezadoMarcas);
                    break;

                case "ventas":
                    query = "SELECT v.id_venta, v.no_factura, v.serie, v.fecha_factura, " +
                             "c.nombres AS nombre_cliente, e.nombres AS nombre_empleado, " +
                             "v.fecha_ingreso, c.id_cliente, e.id_empleado " +
                             "FROM ventas v " +
                             "INNER JOIN clientes c ON v.id_cliente = c.id_cliente " +
                             "INNER JOIN empleados e ON v.id_empleado = e.id_empleado " +
                             "ORDER BY v.id_venta ASC;";
                    String[] encabezadoVentas = {"ID Venta", "No Factura", "Serie", "Fecha Factura", "Nombre Cliente", "Nombre Empleado", "Fecha Ingreso", "ID Cliente", "ID Empleado"};
                    tabla.setColumnIdentifiers(encabezadoVentas);
                    break;

                case "productos":
                    query = "SELECT id_producto, producto, marca, descripcion, precio_costo, precio_venta, existencia, fecha_ingreso FROM productos;";
                    String[] encabezadoProductos = {"ID Producto", "Producto", "Marca", "Descripción", "Precio Costo", "Precio Venta", "Existencia", "Fecha Ingreso"};
                    tabla.setColumnIdentifiers(encabezadoProductos);
                    break;

                case "compras":
                    query = "SELECT id_compra, proveedor, producto, cantidad, precio_costo, fecha_compra FROM compras;";
                    String[] encabezadoCompras = {"ID Compra", "Proveedor", "Producto", "Cantidad", "Precio Costo", "Fecha Compra"};
                    tabla.setColumnIdentifiers(encabezadoCompras);
                    break;

                default:
                    return null; // Tipo de reporte no válido
            }

            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            String[] datos = new String[tabla.getColumnCount()];

            while (consulta.next()) {
                for (int i = 0; i < tabla.getColumnCount(); i++) {
                    datos[i] = consulta.getString(i + 1); // Los índices en ResultSet comienzan en 1
                }
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al obtener datos para el reporte: " + ex.getMessage());
        }
        return tabla;
    }
}
