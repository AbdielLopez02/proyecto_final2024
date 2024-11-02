<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import= "modelo.Marca" %>
<%@page import= "modelo.Producto" %>
<%@page import = "java.util.HashMap" %>
<%@page import= "java.util.List" %>
<%@page import= "modelo.Menu" %>

<%@page contentType= "text/html" pageEncoding="UTF-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
 <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>UMG</title>
    <meta content="width=device-width, initial-scale=1.0, shrink-to-fit=no" name="viewport" />
<link rel="icon" class="navbar-brand" href="resources/img/bot-blanco.png" type="image/x-icon" />

    <!-- Fonts and icons -->
    <!-- Carga los íconos de Bootstrap desde CDN 5.3.3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

    <!-- CSS Files -->
    <!-- Aquí eliminas la carga de Bootstrap desde el CDN 5.3.3 si prefieres usar la versión local -->
    <link rel="stylesheet" href="../../resources/resources2/css/bootstrap.min.css" />

    <!-- Otros archivos CSS locales -->
    <link href="https://fonts.googleapis.com/css2?family=Baloo+2&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/resources2/css/kaiadmin.min.css" />
    <link rel="stylesheet" href="../../resources/css/main.css" />
    <link rel="stylesheet" href="../../resources/css/cards.scss" />
    <link rel="stylesheet" href="../../resources/resources2/css/plugins.min.css" />
    <link rel="stylesheet" href="../../resources/resources2/css/demo.css" />
    
 </head>
  
<body>
   
    <div class="wrapper">
     
      <%@ include file="/menu.jsp" %>
       
      <div class="main-panel">
        <div class="main-header">
          <div class="main-header-logo">
            <!-- Logo Header -->
            <div class="logo-header" data-background-color="dark">
              <a href="../../index.html" class="logo">
                <img
                  src="assets/img/kaiadmin/logo_light.svg"
                  alt="navbar brand"
                  class="navbar-brand"
                  height="20"
                  />
              </a>
              <div class="nav-toggle">
                <button class="btn btn-toggle toggle-sidebar">
                  <i class="gg-menu-right"></i>
                </button>
                <button class="btn btn-toggle sidenav-toggler">
                  <i class="gg-menu-left"></i>
                </button>
               </div>
              <button class="topbar-toggler more">
                <i class="gg-more-vertical-alt"></i>
              </button>
            </div>
            <!-- End Logo Header -->
          </div>
            
            
            
            
            
            
            
            
          <!-- Navbar Header -->
             <nav class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom">
                <div class="container-fluid">
                 <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">


              <li class="nav-item topbar-user dropdown hidden-caret">
                <%
                String nombreUsuario = (String) session.getAttribute("nombreUsuario"); // Recupera el nombre de usuario de la sesión
                %>
                <a class="dropdown-toggle profile-pic" data-bs-toggle="dropdown" href="#" aria-expanded="false">
                    <i class="bi bi-person-circle"></i>
                    <span class="logo-text">Bienvenido Usuario:</span>
                    <span class="profile-username">
                        <span class="fw-bold"><%= nombreUsuario != null ? nombreUsuario : "Usuario" %></span>
                    </span>
                </a>



                <ul class="dropdown-menu dropdown-user animated fadeIn">
                  <div class="dropdown-user-scroll scrollbar-inner">
                    <li class="dropdown-item">
                      <a href="../../index.html" class="d-flex align-items-center">
                        <i class="bi bi-box-arrow-left me-2"></i>
                        <span>Logout</span>
                      </a>
                      </li>
                    </div>
                  </ul>
                </li>
              </ul>
            </div>
          </nav>


          <!-- End Navbar -->
       </div>

        <!--Espacio para contenido-->

                  
         

        
        
        <div class="container">
    <!-- The Modal -->
    <div class="modal fade" id="modal_productos">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Formulario de Productos</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="<%= request.getContextPath() %>/sr_producto" method="post" enctype="multipart/form-data" class="form-group needs-validation" novalidate>
                        <label for="lbl_id" class="form-label"><b>ID</b></label>
                        <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                        <br>

                        <label for="lbl_producto" class="form-label"><b>Nombre del Producto</b></label>
                        <input type="text" name="txt_producto" id="txt_producto" class="form-control" placeholder="Nombre del producto" required>
                        <br>

                         <label for = "lbl_fn" class="form-label"><b>Marca</b></label>
                    <select  name="drop_marcas" id="drop_marcas" class="form-select" required>

                        <option selected disabled value="">Elija una marca</option>

                        <%
                        Marca marca = new Marca();
                        HashMap<String,String> drop = marca.drop_marcas();
                        for(String i:drop.keySet()){

                        out.println("<option value = '"+i+"'>"+drop.get(i)+"</option>");
                            }



                        %>

                    </select>
                        <br>

                        <label for="lbl_descripcion" class="form-label"><b>Descripción</b></label>
                        <input type="text" name="txt_descripcion" id="txt_descripcion" class="form-control" placeholder="Descripción del producto" required>
                        <br>

                             <label for="lbl_imagen" class="form-label"><b>Imagen del Producto</b></label>
        <input type="file" name="txt_imagen" id="txt_imagen" class="form-control" accept="image/*">
        <br>
        <!-- Aquí se muestra la imagen seleccionada -->
        <img id="modal_imagen" src="" alt="Imagen del producto" width="100" />
        <br>
        <br>
        <br>
        <br>

                        <label for="lbl_precio_costo" class="form-label"><b>Precio Costo</b></label>
                        <input type="number" name="txt_precio_costo" id="txt_precio_costo" class="form-control" placeholder="Precio de costo" required>
                        <br>

                        <label for="lbl_precio_venta" class="form-label"><b>Precio Venta</b></label>
                        <input type="number" name="txt_precio_venta" id="txt_precio_venta" class="form-control" placeholder="Precio de venta" required>
                        <br>

                        <label for="lbl_existencia" class="form-label"><b>Existencia</b></label>
                        <input type="number" name="txt_existencia" id="txt_existencia" class="form-control" placeholder="Cantidad en existencia" required>
                        <br>

                        <label for="lbl_fecha_ingreso" class="form-label"><b>Fecha de Ingreso</b></label>
                        <input type="datetime-local" name="txt_fecha_ingreso" id="txt_fecha_ingreso" class="form-control" required>
                        <br>

                        <button name="btn_crear" id="btn_crear" value="crear" class="btn btn-primary" type="submit"><i class="bi bi-floppy"></i></button>
                        <button name="btn_actualizar" id="btn_actualizar" value="actualizar" class="btn btn-warning" type="submit"><i class="bi bi-arrow-down-up"></i></button>
                        <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" type="submit" onclick="javascript:if(!confirm('¿Desea eliminar?'))return false"><i class="bi bi-trash"></i></button>
                    </form>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Tabla de Productos -->
    <div class="page-inner">
        <div class="page-header">
            <h3 class="fw-bold mb-3">Tabla de Productos</h3>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_productos" onclick="limpiar()">Nuevo Producto</button>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="basic-datatables" class="display table table-striped table-hover">
                                <thead>
                                    <tr>
                                        
                                        
                                        <th>Producto</th>
                                        <th>Marca</th>
                                        <th>Descripción</th>
                                        <th>Imagen</th>
                                        <th>Precio Costo</th>
                                        <th>Precio Venta</th>
                                        <th>Existencia</th>
                                        <th>Fecha Ingreso</th>
                                    </tr>
                                </thead>
                                <tbody id="tbl_productos">
                                    <%
                                    Producto producto = new Producto();
                                    DefaultTableModel tabla = new DefaultTableModel();
                                    tabla = producto.leer();
                                    for(int t=0;t<tabla.getRowCount(); t++){
                                        out.println("<tr data-id=" + tabla.getValueAt(t, 0) + " data-id_m=" + tabla.getValueAt(t, 2) + ">");
                                        out.println("<td>"  + tabla.getValueAt(t, 1) + "</td>");
                                        out.println("<td> " + tabla.getValueAt(t, 3) + "</td>");
                                        out.println("<td> " + tabla.getValueAt(t, 4) + "</td>");
        out.println("<td><img src=\"" + request.getContextPath() + "/" + tabla.getValueAt(t, 5) + "\" width=\"100\" height=\"auto\" style=\"object-fit: cover;\"></td>");
                                        out.println("<td> " + tabla.getValueAt(t, 6) + "</td>");
                                        out.println("<td> " + tabla.getValueAt(t, 7) + "</td>");
                                        out.println("<td> " + tabla.getValueAt(t, 8) + "</td>");
                                        out.println("<td> " + tabla.getValueAt(t, 9) + "</td>");
                                       
                                       
                                        

                                        out.println("</tr>");

                                     }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



                  
                  
        
      </div>
    </div>
      
      
      
    
    
    
    
    
    
    
    
    
    
     
    <!--   Core JS Files   -->
    <script src="../../resources/resources2/js/core/jquery-3.7.1.min.js"></script>
    <script src="../../resources/resources2/js/core/popper.min.js"></script>
    <script src="../../resources/resources2/js/core/bootstrap.min.js"></script>
    
    
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha384-LZTz/rX2m2H/aAe4NYq40YxjL89DICM2Fev4M/tvSRe9TbFg8beP2B3Rar9A3zM0" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-e8dtFdxD4qRvTndk65X8zRLUblX2n8jNiMmjGu/AeGKeRi9c18OqdfdNwsHbcDBn" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 


    <!-- jQuery Scrollbar -->
    <script src="../../resources/resources2/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
    <!-- Datatables -->
    <script src="../../resources/resources2/js/plugin/datatables/datatables.min.js"></script>
    <!-- Kaiadmin JS -->
    <script src="../../resources/resources2/js/kaiadmin.min.js"></script>
    <!-- Kaiadmin DEMO methods, don't include it in your project! -->
    <script src="../../resources/resources2/js/setting-demo2.js"></script>
    <script>
      $(document).ready(function () {
        $("#basic-datatables").DataTable({});

        $("#multi-filter-select").DataTable({
          pageLength: 5,
          initComplete: function () {
            this.api()
              .columns()
              .every(function () {
                var column = this;
                var select = $(
                  '<select class="form-select"><option value=""></option></select>'
                )
                  .appendTo($(column.footer()).empty())
                  .on("change", function () {
                    var val = $.fn.dataTable.util.escapeRegex($(this).val());

                    column
                      .search(val ? "^" + val + "$" : "", true, false)
                      .draw();
                  });

                column
                  .data()
                  .unique()
                  .sort()
                  .each(function (d, j) {
                    select.append(
                      '<option value="' + d + '">' + d + "</option>"
                    );
                  });
              });
          },
        });

        // Add Row
        $("#add-row").DataTable({
          pageLength: 5,
        });

        var action =
          '<td> <div class="form-button-action"> <button type="button" data-bs-toggle="tooltip" title="" class="btn btn-link btn-primary btn-lg" data-original-title="Edit Task"> <i class="fa fa-edit"></i> </button> <button type="button" data-bs-toggle="tooltip" title="" class="btn btn-link btn-danger" data-original-title="Remove"> <i class="fa fa-times"></i> </button> </div> </td>';

        $("#addRowButton").click(function () {
          $("#add-row")
            .dataTable()
            .fnAddData([
              $("#addName").val(),
              $("#addPosition").val(),
              $("#addOffice").val(),
              action,
            ]);
          $("#addRowModal").modal("hide");
        });
      });
    </script>
    
    
<script type="text/javascript">
function limpiar() {
    $("#txt_id").val(0);
    $("#txt_producto").val('');
    $("#drop_marcas").val(''); // Asegúrate de que este ID corresponda al dropdown de marcas
    $("#txt_descripcion").val('');
    $("#txt_imagen").val('');
    $("#txt_precio_costo").val('');
    $("#txt_precio_venta").val('');
    $("#txt_existencia").val('');
    $("#txt_fecha_ingreso").val('');
}

$(document).ready(function () {
    // Evento para seleccionar los datos al hacer clic en una fila de la tabla
    $('#tbl_productos').on('click', 'tr', function(evt) {
        console.log("Fila clickeada");
        var target = $(this); // Usar 'this' para referirse a la fila seleccionada
        var id = target.data('id'); // ID del producto
        var id_m = target.data('id_m'); // ID de la marca

        // Obtener los valores de las columnas en el orden correcto
        var producto = target.find("td").eq(0).text().trim(); // Producto
        var descripcion = target.find("td").eq(2).text().trim(); // Descripción
        var imagen = target.find("td").eq(3).find("img").attr("src"); // URL de la Imagen
        var precio_costo = target.find("td").eq(4).text().trim(); // Precio Costo
        var precio_venta = target.find("td").eq(5).text().trim(); // Precio Venta
        var existencia = target.find("td").eq(6).text().trim(); // Existencia
        var fecha_ingreso = target.find("td").eq(7).text().trim(); // Fecha de Ingreso

        // Verificar los valores en la consola para depuración
        console.log("ID:", id);
        console.log("Producto:", producto);
        console.log("Descripción:", descripcion);
        console.log("Imagen URL:", imagen);
        console.log("Precio Costo:", precio_costo);
        console.log("Precio Venta:", precio_venta);
        console.log("Existencia:", existencia);
        console.log("Fecha de Ingreso:", fecha_ingreso);

        // Asignar los valores al formulario
        $("#txt_id").val(id);
        $("#txt_producto").val(producto);
        $("#drop_marcas").val(id_m); // Asegúrate de que el ID de este campo sea correcto
        $("#txt_descripcion").val(descripcion);
        $("#txt_precio_costo").val(precio_costo);
        $("#txt_precio_venta").val(precio_venta);
        $("#txt_existencia").val(existencia);
        $("#txt_fecha_ingreso").val(fecha_ingreso);

        // Mostrar la imagen en el modal sin afectar el input file
        $("#modal_imagen").attr("src", imagen);

        // Mostrar el modal
        $("#modal_productos").modal('show');
    });
});
</script>





       


  </body>
</html>
