
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import= "modelo.Proveedor" %>
<%@page import = "java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
<div class="modal fade" id="modal_proveedor">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Formulario</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
          <form  action="<%= request.getContextPath() %>/sr_proveedor"  method="post" class="form-group needs-validation" novalidate> <!-- get = envio de parametrosurl, post = oculto -->
        
            <label  for = "lbl_id" class="form-label"><b>ID</b></label>
            <input type="text" name="txt_id" id="txt_id" class="form-control" value = "0" readonly>
            
            <br>
         
            <label for = "lbl_nombres"class="form-label"><b>Proveedor</b></label>
            <input type="text" name="txt_proveedor" id="txt_proveedor" class="form-control" placeholder="Ejemplo: Nombre1 Nombre2"required>
            <br>
        
            <label for = "lbl_direccion"class="form-label"><b>Nit</b></label>
            <input type="text" name="txt_nit" id="txt_nit" class="form-control" placeholder="Ejemplo: 283321"required>
            <br>
                              
            <label for = "lbl_telefono"class="form-label"><b>Direccion</b></label>
            <input type="text" name="txt_direccion" id="txt_direccion" class="form-control"  placeholder="Guatemala Av12.z2"required>
            <br>
            
            <label for = "lbl_telefono"class="form-label"><b>Telefono</b></label>
            <input type="text" name="txt_telefono" id="txt_telefono" class="form-control"  placeholder="Ejemplo:1234-1232"required>
            
           
            <br>
            <button name="btn_crear" id="btn_crear" value="crear" class="btn btn-primary" type="submit"><i class="bi bi-floppy"></i></button>
            <button name="btn_actualizar" id="btn_actualizar" value="actualizar" class="btn btn-warning" type="submit"><i class="bi bi-arrow-down-up"></i></button>
            <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" type="submit" onclick="javascript:if(!confirm('¿Desea Eliminarl?'))return false"><i class="bi bi-trash"></i></button>
  </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
                
          <div class="page-inner">
            <div class="page-header">
              <h3 class="fw-bold mb-3">Tabla Proveedores</h3>
             
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="card">
                  <div class="card-header">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_proveedor" onclick="limpiar()">Nuevo</button>
                  </div>
                  <div class="card-body">
                    <div class="table-responsive">
                      <table id="basic-datatables"
                        class="display table table-striped table-hover">
    <thead>
        <tr>          
            <th>Proveedor</th>
            <th>NIT</th>
            <th>Direccion</th>
            <th>Teléfono</th>         
        </tr>
    </thead>
    <tbody id="tbl_proveedores">
        <%
        Proveedor proveedor = new Proveedor();
        DefaultTableModel tabla = proveedor.leer();

        // Recorrer la tabla para generar filas
        for(int t = 0; t < tabla.getRowCount(); t++) {
            // ID oculto
            String idProveedor = tabla.getValueAt(t, 0).toString();

            out.println("<tr data-id='" + idProveedor + "'>");
            out.println("<td>" + tabla.getValueAt(t, 1) + "</td>"); // Proveedor
            out.println("<td>" + tabla.getValueAt(t, 2) + "</td>"); // NIT
            out.println("<td>" + tabla.getValueAt(t, 3) + "</td>"); // Dirección
            out.println("<td>" + tabla.getValueAt(t, 4) + "</td>"); // Teléfono
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
        $("#txt_proveedor").val('');
        $("#txt_nit").val('');
        $("#txt_direccion").val('');
        $("#txt_telefono").val('');
    }

    // Evento para seleccionar los datos al hacer clic en una fila de la tabla
    $('#tbl_proveedores').on('click', 'tr td', function(evt) {
        var target, id, proveedor, nit, direccion, telefono;
        
        target = $(evt.target);  // Usar 'evt' como el evento
        id = target.parent().data('id');  // ID del proveedor

        // Asignar valores a cada campo según el índice de columna en la tabla
        proveedor = target.parent("tr").find("td").eq(0).html().trim();  // Proveedor
        nit = target.parent("tr").find("td").eq(1).html().trim();  // NIT
        direccion = target.parent("tr").find("td").eq(2).html().trim();  // Dirección
        telefono = target.parent("tr").find("td").eq(3).html().trim();  // Teléfono

        // Verificar los valores en la consola para depuración
        console.log("ID:", id);
        console.log("Proveedor:", proveedor);
        console.log("NIT:", nit);
        console.log("Dirección:", direccion);
        console.log("Teléfono:", telefono);

        // Asignar los valores al formulario
        $("#txt_id").val(id);
        $("#txt_proveedor").val(proveedor);
        $("#txt_nit").val(nit);
        $("#txt_direccion").val(direccion);
        $("#txt_telefono").val(telefono);

        // Mostrar el modal de edición
        $("#modal_proveedor").modal('show');
    });
</script>



  </body>
</html>
