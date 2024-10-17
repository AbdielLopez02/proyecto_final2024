

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
<div class="modal fade" id="modal_empleado">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Formulario</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
          <form  action="<%= request.getContextPath() %>/sr_empleado"  method="post" class="form-group needs-validation" novalidate> <!-- get = envio de parametrosurl, post = oculto -->
        
            <label  for = "lbl_id" class="form-label"><b>ID</b></label>
            <input type="text" name="txt_id" id="txt_id" class="form-control" value = "0" readonly>
            
            <br>
         
            <label for = "lbl_nombres"class="form-label"><b>Nombre</b></label>
            <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ejemplo: Nombre1 Nombre2"required>
            <br>

            <label for = "lbl_apellidos"class="form-label"><b>Apellidos</b></label>
            <input type="text" name="txt_apellidos" id="txt_apellidos"class="form-control"  placeholder="Ejemplo: Apellido1 Apellido 2"required>
            <br>
            
            <label for = "lbl_direccion"class="form-label"><b>Direccion</b></label>
            <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ejemplo: Guatemala No.casa"required>
            <br>

            <label for = "lbl_telefono"class="form-label"><b>Telefono</b></label>
            <input type="text" name="txt_telefono" id="txt_telefono" class="form-control"  placeholder="Ejemplo:1234-1232"required>
            <br>
            
             <label for = "lbl_codigo" class="form-label"><b>DPI</b></label>
            <input type="text" name="txt_dpi" id="txt_dpi" class="form-control" placeholder="Ejemplo: 1111 22222 0101" required maxlength="15">
            <br>
            
            <label for = "lbl_genero" class="form-label"><b>Genero</b></label>
                <select id="txt_genero" name="txt_genero" class="form-select" required>
                   
                    <option value="0">Masculino</option>
                    <option value="1">Femenino</option>
                </select>         
            <br>
            
            <label for = "lbl_fn"class="form-label"><b>F.Nacimiento</b></label>
            <input type="date" name="txt_fn" id="txt_fn" class="form-control"  placeholder="Ejemplo:2003-09-09"required>
            <br>
            
            <label for = "lbl_fn" class="form-label"><b>Puestos</b></label>
            <select  name="drop_puesto" id="drop_puesto" class="form-select" required>
                
                <option selected disabled value="">Elija un puesto</option>
               
                <%
                Puesto puesto = new Puesto();
                HashMap<String,String> drop = puesto.drop_puesto();
                for(String i:drop.keySet()){
                
                out.println("<option value = '"+i+"'>"+drop.get(i)+"</option>");
                    }
                
                
                
                %>
                
            </select>
            
            <br>
            
            <label for = "lbl_fl"class="form-label"><b>F. inicio de labores</b></label>
            <input type="date" name="txt_fl" id="txt_fl" class="form-control"  placeholder="Ejemplo:2003-09-09"required>
            <br>
            <label for = "lbl_fi"class="form-label"><b>F. de ingreso</b></label>
            <input type="datetime-local" name="txt_fi" id="txt_fi" class="form-control"  placeholder="Ejemplo:2003-09-09"required>
            <br>
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
              <h3 class="fw-bold mb-3">Tabla Empleados</h3>
             
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="card">
                  <div class="card-header">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_empleado" onclick="limpiar()">Nuevo</button>
                  </div>
                  <div class="card-body">
                    <div class="table-responsive">
                      <table id="basic-datatables"
                        class="display table table-striped table-hover">
    <thead>
        <tr>          
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>Dirección</th>
            <th>Teléfono</th>
            <th>DPI</th> <!-- Campo DPI -->
            <th>Género</th> <!-- Campo Género -->
            <th>Fecha Nacimiento</th> <!-- Campo Fecha Nacimiento -->
            <th>Puesto</th>       
            <th>Fecha Inicio Labores</th> <!-- Campo Fecha Inicio Labores -->
            <th>Fecha Ingreso</th> <!-- Campo Fecha Ingreso -->
        </tr>
    </thead>
    <tbody id="tbl_empleados">
        <%
        Empleado empleado = new Empleado();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla = empleado.leer();
       for(int t=0;t<tabla.getRowCount(); t++){
               out.println("<tr data-id=" + tabla.getValueAt(t, 0) + " data-id_p=" + tabla.getValueAt(t, 9) + ">");
               out.println("<td>"  + tabla.getValueAt(t, 1) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 2) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 3) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 4) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 5) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 6) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 7) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 8) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 10) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 11) + "</td>");

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
    $("#txt_id").val(0);
    $("#txt_nombres").val('');
    $("#txt_apellidos").val('');
    $("#txt_direccion").val('');
    $("#txt_telefono").val('');
    $("#txt_dpi").val('');
    $("#txt_genero").val(0);
    $("#txt_fn").val('');
    $("#drop_puesto").val(1);
    $("#txt_fl").val('');
    $("#txt_fi").val('');
}

// Evento para seleccionar los datos al hacer clic en una fila de la tabla
$('#tbl_empleados').on('click', 'tr td', function(evt) {
    var target, id, id_p, nombres, apellidos, direccion, telefono, dpi, genero, nacimiento, fecha_inicio_labores, fecha_ingreso;
    target = $(evt.target);  // Usar 'evt' como el evento
    id = target.parent().data('id');  // id empleado
    id_p = target.parent().data('id_p');  // id puesto
        
    // Obtener los valores de las columnas en el orden correcto
    nombres = target.parent("tr").find("td").eq(0).html();  // Nombres
    apellidos = target.parent("tr").find("td").eq(1).html().trim();  // Apellidos
    direccion = target.parent("tr").find("td").eq(2).html().trim();  // Dirección
    telefono = target.parent("tr").find("td").eq(3).html().trim();  // Teléfono
    dpi = target.parent("tr").find("td").eq(4).html().trim();  // DPI
    genero = target.parent("tr").find("td").eq(5).html().trim();  // Género
    nacimiento = target.parent("tr").find("td").eq(6).html().trim();  // Fecha nacimiento
    fecha_inicio_labores = target.parent("tr").find("td").eq(8).html().trim();  // Fecha inicio labores
    fecha_ingreso = target.parent("tr").find("td").eq(9).html().trim();  // Fecha ingreso

    // Convertir género de texto a número
    if (genero === "Masculino") {
        $("#txt_genero").val(0);
    } else if (genero === "Femenino") {
        $("#txt_genero").val(1);
    }

    // Verificar los valores en la consola para depuración
    console.log("ID:", id);
    console.log("Nombres:", nombres);
    console.log("Apellidos:", apellidos);
    console.log("Dirección:", direccion);
    console.log("Teléfono:", telefono);
    console.log("DPI:", dpi);
    console.log("Género:", genero);
    console.log("Fecha de Nacimiento:", nacimiento);
    console.log("Fecha de Inicio de Labores:", fecha_inicio_labores);
    console.log("Fecha de Ingreso:", fecha_ingreso);

    // Asignar los valores al formulario
    $("#txt_id").val(id);
    $("#txt_nombres").val(nombres);
    $("#txt_apellidos").val(apellidos);
    $("#txt_direccion").val(direccion);
    $("#txt_telefono").val(telefono);
    $("#txt_dpi").val(dpi);
    $("#txt_fn").val(nacimiento);
    $("#drop_puesto").val(id_p);
    $("#txt_fl").val(fecha_inicio_labores);
    $("#txt_fi").val(fecha_ingreso);

    // Mostrar el modal
    $("#modal_empleado").modal('show');
});







</script> 
  </body>
</html>
