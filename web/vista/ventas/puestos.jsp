
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import= "modelo.Puesto" %>
<%@page import= "modelo.Empleado" %>
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
      <!-- Sidebar -->
      <div class="sidebar" data-background-color="dark">
        <div class="sidebar-logo">
          <!-- Logo Header -->
          <div class="logo-header" data-background-color="dark">
           <a href="../../index.html" class="logo">
                <img
                    src="../../resources/img/bot.png"
                    alt="navbar brand"
                    class="navbar-brand"
                    height="50" >
                
                <span class="logo-text">System Project</span> 
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
    <div class="sidebar-wrapper scrollbar scrollbar-inner">
          <div class="sidebar-content">
            <ul class="nav nav-secondary">  
                <li class="nav-item active">
            <a href="../../index.html" class="nav-link">
                <i class="bi bi-house-door-fill"></i>
                <p>Home</p>
            </a>
        </li>
              <li class="nav-section">
                <span class="sidebar-mini-icon">
                <i class="bi bi-three-dots"></i>                
                </span>
                <h4 class="text-section">Menu Tablas</h4>
              </li>
             
              
              <li class="nav-item">
                <a data-bs-toggle="collapse" href="#productos">
                  <i class="bi bi-box-seam-fill"></i>
                  <p>Productos</p>
                  <span class="caret"></span>
                </a>
                <div class="collapse" id="productos">
                     <ul class="nav nav-collapse">
                    <li>
                      <a href="../productos/productos.jsp">
                        <span class="sub-item">CRUD Productos</span>
                      </a>
                    </li>   
                    <li>                        
                        <a href="../productos/productos/marcas.jsp">
                        <span class="sub-item">CRUD Marcas</span>
                      </a>
                    </li>
                  </ul>
                  
                </div>
              </li>
              <li class="nav-item">
                <a data-bs-toggle="collapse" href="#ventas">
                   <i class="bi bi-currency-exchange"></i>
                   <p>Ventas</p>
                  <span class="caret"></span>
                </a>
                <div class="collapse" id="ventas">
                  <ul class="nav nav-collapse">
                     <li>
                      <a href="../ventas/ventas.jsp">
                        <span class="sub-item">CRUD Ventas</span>
                      </a>
                    </li>
                    <li>
                      <a href="../ventas/clientes.jsp">
                        <span class="sub-item">CRUD Clientes</span>
                      </a>
                    </li>
                    <li>
                      <a href="../ventas/empleados.jsp">
                        <span class="sub-item">CRUD Empleados</span>
                      </a>
                    </li>
                    <li>
                      <a href="../ventas/puestos.jsp">
                        <span class="sub-item">CRUD Puestos</span>
                      </a>
                    </li>
                  </ul>
                </div>
              </li>             
              <li class="nav-item">
                <a data-bs-toggle="collapse" href="#compras">
                    <i class="bi bi-cart4"></i>
                    <p>Compras</p>
                  <span class="caret"></span>
                </a>
                <div class="collapse" id="compras">
                  <ul class="nav nav-collapse">
                    <li>
                      <a href="../compras/compras.jsp">
                        <span class="sub-item">CRUD Compras</span>
                      </a>
                    </li> 
                    <li>
                      <a href="../compras/proveedores.jsp">
                        <span class="sub-item">CRUD Proveedores</span>
                      </a>
                    </li>                    
                  </ul>
                </div>
              </li>
               <li class="nav-item">
                <a data-bs-toggle="collapse" href="#reportes">
                    <i class="bi bi-file-earmark-spreadsheet"></i>
                    <p>Reportes</p>
                  <span class="caret"></span>
                </a>
                <div class="collapse" id="reportes">
                  <ul class="nav nav-collapse">
                    <li>
                      <a href="../reportes/reportes.jsp">
                        <span class="sub-item">Realizar reporte</span>
                      </a>
                    </li>                   
                  </ul>
                </div>
              </li>
             
              
            </ul>
          </div>
        </div>
      </div>
      <!-- End Sidebar -->

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

        <div class="container">
             <!-- The Modal -->
<div class="modal fade" id="modal_puestos">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Formulario</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
          <form  action="<%= request.getContextPath() %>/sr_puestos"  method="post" class="form-group needs-validation" novalidate> <!-- get = envio de parametrosurl, post = oculto -->
        
            <label  for = "lbl_id_puestos" class="form-label"><b>ID</b></label>
            <input type="text" name="txt_id_puesto" id="txt_id_puesto" class="form-control" value = "0" readonly>
            
            <br>                                
            <label for = "lbl_puestos" class="form-label"><b>Puestos</b></label>
            <input type="text" id="txt_puesto" name="txt_puesto" class="form-control" required placeholder="Ingrese el puesto">
                                          
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
              <h3 class="fw-bold mb-3">Tabla Puestos</h3>
             
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="card">
                  <div class="card-header">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_puestos" onclick="limpiar()">Nuevo</button>
                  </div>
                  <div class="card-body">
                    <div class="table-responsive">
                      <table id="basic-datatables"
                        class="display table table-striped table-hover">
    <thead>
        <tr>          
            <th>ID</th>           
            <th>Puesto</th>                   
        </tr>
    </thead>
    <tbody id="tbl_puestos">
        <%
        
        Puesto puesto = new Puesto();

        DefaultTableModel tabla = puesto.leer(); // Cargar datos de la tabla 'puestos'
            for (int t = 0; t < tabla.getRowCount(); t++) {
                out.println("<tr data-id='" + tabla.getValueAt(t, 0) + "'>");
                out.println("<td>" + tabla.getValueAt(t, 0) + "</td>"); // id_puesto
                out.println("<td>" + tabla.getValueAt(t, 1) + "</td>"); // puesto
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

       <footer class="footer">
          <div class="container-fluid d-flex justify-content-between">
            <nav class="pull-left">
              
            </nav>
            
            
          </div>
        </footer>
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
    $("#txt_id_puesto").val(0);  
    $("#txt_puesto").val('');     
}

// Evento para seleccionar los datos al hacer clic en una fila de la tabla
$('#tbl_puestos').on('click', 'tr td', function(evt) {
    var target, id_puesto, puesto;

    target = $(evt.target);  
    id_puesto = target.parent().data('id');  
    puesto = target.parent("tr").find("td").eq(1).html().trim();  

    // Verificar los valores en la consola para depuración
    console.log("ID Puesto:", id_puesto);
    console.log("Puesto:", puesto);

    // Asignar los valores al formulario
    $("#txt_id_puesto").val(id_puesto);
    $("#txt_puesto").val(puesto);

    // Mostrar el modal
    $("#modal_puestos").modal('show');
});
</script>

  </body>
</html>
