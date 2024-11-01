
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import= "modelo.Compra" %>
<%@page import= "modelo.Proveedor" %>
<%@page import= "modelo.Producto" %>

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
    <link rel="stylesheet" href="../../resources/css/ventas.css" />
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
<div class="modal fade" id="modal_compra" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
       <div class="modal-dialog modal-xl">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Formulario de Compras</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    


                    <form id="miFormulario"  action="<%= request.getContextPath() %>/sr_compras" method="post" class="form-group needs-validation">
                        
                    <div class="form-columns">
                       <div class="column-left">
                           
                         <div class="form-group">                             
                           <label  for = "lbl_id" class="form-label"><b>ID</b></label>
                           <input type="text" name="id_compra" id="id_compra" class="form-control" value="0" readonly>
                         </div>
                           <br>
                           
                         <div class="form-group">
                            <label for="lbl_no_factura" class="form-label"><b>No. Orden de la compra</b></label>
                            <input type="number" name="txt_no_orden" id="txt_no_orden" class="form-control" required>
                         </div>
                            <br>
          
                         <div class="form-group"> 
                            <label for="lbl_id_cliente" class="form-label"><b>Proveedor</b></label>
                            <select name="drop_proveedor" id="drop_proveedor" class="form-select" required>
                                <option selected disabled value="">Seleccione un Proveedor</option>
                                <%
                                Proveedor proveedor = new Proveedor();
                                HashMap<String,String> drop = proveedor.drop_proveedor(); // Método para obtener clientes
                                for(String id : drop.keySet()){
                                    out.println("<option value='" + id + "'>" + drop.get(id) + "</option>");
                                }
                                %>
                            </select>
                         </div>
                            <br>
                            
                         <div class="form-group"> 
                            <label for="lbl_fecha_ingreso" class="form-label"><b>Fecha de Orden</b></label>
                            <input type="date" name="txt_fecha_orden" id="txt_fecha_orden" class="form-control" required>
                         </div>
                            <br>
                            
                            <div class="form-group"> 
                            <label for="lbl_fecha_ingreso" class="form-label"><b>Fecha de Ingreso</b></label>
                            <input type="datetime-local" name="txt_fecha_ingreso" id="txt_fecha_ingreso" class="form-control" required>
                         </div>
                            
                            
                       </div>
                            
                        <div class="column-right">
                        
                            <br>
                            <button type="button" class="btn btn-primary" onclick="agregarFila()">Nueva Fila</button>
                            <br>

                                    <table class="table" id="tb_detalles">
                                    <thead>
                                        <tr>                                        
                                            <th></th>                              
                                            <th>Producto</th>
                                            <th>Cantidad</th>
                                            <th>Precio Costo Unitario</th> 
                                            <th>Accion</th>
                                        </tr>
                                    </thead>
                                        <tbody>
                                              <!-- Aquí se agregarán las filas dinámicamente -->
                                          </tbody>
                                </table>
                        </div>         
              </div>
                            <br>
                            <button name="btn_crear" id="btn_crear" value="crear" class="btn btn-primary" type="submit"><i class="bi bi-floppy"></i></button>
                            <button name="btn_actualizar" id="btn_actualizar" value="actualizar" class="btn btn-warning" type="submit"><i class="bi bi-arrow-down-up"  onclick="agregarDetalles()"></i></button>
                            <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" type="submit" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false"><i class="bi bi-trash"></i></button>
                        </form>


                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
                </div>

            </div>
        </div>
    </div>

 
                          

<script>
    function agregarFila() {
        // Crear una nueva fila
        const nuevaFila = document.createElement('tr');

        // Crear el contenido de la fila con los nombres adecuados
        nuevaFila.innerHTML = `
            <td><input type="hidden" name="id_compra_detalle"id="id_compra_detalle" value="0"></td>
            <td>
                <select name="drop_productos" id="drop_productos" class="form-select" required>
                    <option selected disabled value="">Seleccione un Producto</option>
                    <%
                        Producto producto = new Producto();
                        HashMap<String, String> dropProductos = producto.drop_productos();
                        for (String id : dropProductos.keySet()) {
                            String nombreProducto = dropProductos.get(id);
                            out.println("<option value='" + id + "'>" + nombreProducto + "</option>");
                        }
                    %>
                </select>
            </td>
            <td><input type="number" name="txt_cantidad" class="form-control" required></td>
            <td><input type="number" name="txt_precio_costo_unitario" class="form-control" step="0.01" min="0" required></td>
            <td>
                <button type="button" class="btn btn-danger" onclick="eliminarFila(this)">Eliminar</button>
            </td>
        `;

        // Agregar la nueva fila al cuerpo de la tabla
        document.querySelector('#tb_detalles tbody').appendChild(nuevaFila);
    }

    function eliminarFila(boton) {
        const fila = boton.closest('tr');
        fila.remove();
    }
</script>

                            
                            
                            
     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="myModalLabel">Detalles de Compra</h5>    
            </div>
            <div class="modal-body" id="modalBody">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>                                   
                                    <th>Producto</th>
                                    <th>Cantidad</th>
                                    <th>Precio Costo Unitario</th>                                 
                                </tr>
                            </thead>
                            <tbody id="tableBody">
                                <!-- Las filas de detalles se agregarán aquí dinámicamente -->
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
                </div>
        </div>
    </div>
</div>

                            
                            
                            
                            
                            
                            
    <div class="page-inner">
        <div class="page-header">
            <h3 class="fw-bold mb-3">Tabla Compras</h3>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_compra" onclick="limpiar()">Nuevo</button>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                          <table id="basic-datatables" class="display table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>No. De Orden</th>
                                        <th>Proveedor</th>
                                        <th>Fecha de la orden</th>            
                                        <th>Fecha Ingreso</th>
                                        <th>Detalles</th> 
                                    </tr>
                                </thead>
                                <tbody id="tbl_compras">
                                      <%
                                Compra compra = new Compra();
                                DefaultTableModel tabla = compra.leer(); 
                                for (int t = 0; t < tabla.getRowCount(); t++) {
                                    // Agregando los IDs como atributos ocultos en la fila
                                    out.println("<tr data-id='" + tabla.getValueAt(t, 0) + "' " +
                                                "data-id_p='" + tabla.getValueAt(t, 5) + "'>"); 
                                    out.println("<td>" + tabla.getValueAt(t, 0) + "</td>"); 
                                    out.println("<td>" + tabla.getValueAt(t, 1) + "</td>"); 
                                    out.println("<td>" + tabla.getValueAt(t, 2) + "</td>"); 
                                    out.println("<td>" + tabla.getValueAt(t, 3) + "</td>"); 
                                    out.println("<td>" + tabla.getValueAt(t, 4) + "</td>"); 

                                    out.println("<td><button type='button' class='btn btn-primary' onclick='verDetalleCompra(" + tabla.getValueAt(t, 0) + ")'>Detalles de Compra</button></td>");
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
    
 <script>
function verDetalleCompra(id_compra) {
    $.ajax({
        url: '<%= request.getContextPath() %>/sr_compras',
        method: 'POST',
        data: { 
            id_compra: id_compra,  // Aquí puedes enviar el id_venta
            modal_type: 'ver_detalle'  // Opcional: puedes agregar un tipo de modal
        },
        dataType: 'json',
        success: function(data) {
            if (data.length > 0) {
                // Limpiar las filas anteriores de la tabla
                $('#tableBody').empty();
                
                // Llenar la tabla con los datos
                data.forEach(function(detalle) {
                    var row = '<tr>' +
                              
                              '<td>' + detalle.producto + '</td>' + 
                              '<td>' + detalle.cantidad + '</td>' +
                              '<td>' + detalle.precio_costo_unitario.toFixed(2) + '</td>' + // Formato a 2 decimales
                              '</tr>';
                    $('#tableBody').append(row);
                });
                
                // Mostrar el modal
                $('#myModal').modal('show');
            } else {
                alert('No se encontraron detalles para esta compra.');
            }
        },
        error: function() {
            alert('No se pudieron cargar los detalles de la compra.');
        }
    });
}
</script>
    
       

 <script type="text/javascript">
// Función para limpiar los campos del formulario y la tabla de detalles
function limpiar() {
    $("#id_compra").val(0);
    $("#txt_no_orden").val('');  
    $("#drop_proveedor").val(''); 
    $("#txt_fecha_orden").val(''); 
    $("#txt_fecha_ingreso").val(''); 
    $("#id_compra_detalle").val('');  
    $("#txt_cantidad").val(''); 
    $("#txt_precio_costo_unitario").val(''); 

    // Limpiar las filas de la tabla de detalles
    $('#tb_detalles tbody').empty();
}

function cargarDetalles(id_compra) {
    $.ajax({
        url: '<%= request.getContextPath() %>/sr_compras', // URL del servlet
        type: 'POST', // Cambiado a POST
        data: {
            modal_type: 'ver_detalle',
            id_compra: id_compra
        },
        dataType: 'json',
        success: function(detalles) {
            console.log("Detalles recibidos:", detalles); // Para ver los datos recibidos en la consola

            // Limpiar la tabla antes de cargar nuevos detalles
            $('#tb_detalles tbody').empty();

            // Verificar si hay detalles para cargar
            if (detalles && detalles.length > 0) {
                detalles.forEach(function(detalle) {
                    // Crear una nueva fila con los detalles de cada producto
                    var nuevaFila = 
                        "<tr>" +
                            "<td><input type='hidden' name='id_compra_detalle' id='id_compra_detalle' value='" + detalle.id_compra_detalle + "'></td>" + // Cambiado a hidden
                            "<td>" +
                                "<select name='drop_productos' id='drop_productos'class='form-select' required>" +
                                    "<option selected disabled value=''>Seleccione un Producto</option>" +
                                    "<option value='" + detalle.id_producto + "' selected>" + detalle.producto + "</option>" +
                                "</select>" +
                            "</td>" +
                            "<td><input type='number' name='txt_cantidad' class='form-control' value='" + detalle.cantidad + "' required></td>" +
                            "<td><input type='number' name='txt_precio_costo_unitario' class='form-control' step='0.01' min='0' value='" + detalle.precio_costo_unitario + "' required></td>" +
                            "<td>" +
                                "<button type='button' class='btn btn-danger' onclick='eliminarFila(this)'>Eliminar</button>" +
                            "</td>" +
                        "</tr>";
                    
                    // Agregar la nueva fila al cuerpo de la tabla de detalles
                    $('#tb_detalles tbody').append(nuevaFila);
                });
            } else {
                alert("No se encontraron detalles para esta compra.");
            }
        },
        error: function(xhr, status, error) {
            console.error("Error al cargar los detalles de la compra", error); // Cambiado a console.error
            console.error("Respuesta del servidor:", xhr.responseText); // Esto mostrará el HTML devuelto
            alert("Error al cargar los detalles de la compra.");
        }
    });
}






// Función para eliminar una fila en la tabla de detalles
function eliminarFila(boton) {
    // Obtener la fila y el ID de compra detalle de esa fila
    const fila = boton.closest('tr');
    const idCompraDetalle = $(fila).find("input[name='id_compra_detalle']").val();
    
    // Verificar que haya un ID de compra detalle válido
    if (idCompraDetalle) {
        // Enviar solicitud AJAX para eliminar la fila en la base de datos
        $.ajax({
            url: '<%= request.getContextPath() %>/sr_compras', // URL del servlet
            type: 'POST',
            data: {
                modal_type: 'eliminar_detalle',
                id_compra_detalle : idCompraDetalle
            },
            success: function(response) {
                // Eliminar la fila del DOM solo si la eliminación en la base de datos fue exitosa
                fila.remove();
                alert("Producto eliminado correctamente.");
            },
            error: function(xhr, status, error) {
                console.error("Error al eliminar el detalle de la compra:", error);
                alert("Error al eliminar el producto.");
            }
        });
    } else {
        // Si no hay un ID válido, eliminar solo del DOM (por si es una fila recién creada que aún no existe en BD)
        fila.remove();
    }
}


// Evento para seleccionar los datos al hacer clic en una fila de la tabla
$(document).ready(function() {
    $('#tbl_compras').on('click', 'tr', function(evt) {
        // Evitar que el modal se abra si se hace clic en un botón dentro de la fila
        if ($(evt.target).is('button')) {
            return; // Si el clic es en un botón, no hacer nada
        }

        // Llamar a la función limpiar antes de cargar nuevos datos
        limpiar();

        var target = $(evt.currentTarget);

        // Obtener los datos desde los atributos de la fila
        var id = target.data('id'); 
        var id_proveedor = target.data('id_p');  
        var no_orden = target.find("td").eq(1).html().trim(); 
        var fecha_orden = target.find("td").eq(3).html().trim();         
        var fecha_ingreso = target.find("td").eq(4).html().trim();  

        // Asignar los valores al formulario del modal
        $("#id_compra").val(id);
        $("#drop_proveedor").val(id_proveedor);
        $("#txt_no_orden").val(no_orden);
        $("#txt_fecha_orden").val(fecha_orden);
        $("#txt_fecha_ingreso").val(fecha_ingreso);

        // Llamar a cargarDetalles para llenar la tabla de detalles con los datos de compra
        cargarDetalles(id);

        // Mostrar el modal
        $("#modal_compra").modal('show');
    });
});
</script>



<script type="text/javascript">
$(document).ready(function() {
    // Función para validar y guardar
    function validarYGuardar(accion, event) {
        const filas = document.querySelectorAll('#tb_detalles tbody tr');

        // Validar que al menos una fila esté presente en la tabla
        if (filas.length === 0) {
            alert("La tabla no puede estar vacía. Por favor, agregue al menos un producto.");
            event.preventDefault(); // Prevenir la acción predeterminada (como el cierre del modal)
            return; // No proceder si no hay filas
        }

        // Si hay al menos una fila, puedes continuar con la lógica de crear o actualizar
        if (accion === 'crear') {
            alert("Creando nuevos datos...");
            // Aquí podrías enviar el formulario o realizar la acción para crear
            // $('#miFormulario').submit(); // Descomentar si quieres enviar el formulario
        } else if (accion === 'actualizar') {
            alert("Actualizando datos...");
            // Aquí podrías enviar el formulario o realizar la acción para actualizar
            // $('#miFormulario').submit(); // Descomentar si quieres enviar el formulario
        }
    }

    // Asignar la función a los botones
    $('#btn_crear').click(function(event) {
        validarYGuardar('crear', event); // Pasar el evento
    });

    $('#btn_actualizar').click(function(event) {
        validarYGuardar('actualizar', event); // Pasar el evento
    });
});
</script>

  </body>
</html>
