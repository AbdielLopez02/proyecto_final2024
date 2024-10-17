<!DOCTYPE html>
<html lang="en">
 <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>UMG</title>
    <meta content="width=device-width, initial-scale=1.0, shrink-to-fit=no" name="viewport" />
<link rel="icon" class="navbar-brand" href="resources/img/bot-blanco.png" type="image/x-icon" />

    <!-- Fonts and icons -->
    <!-- Carga los �conos de Bootstrap desde CDN 5.3.3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

    <!-- CSS Files -->
    <!-- Aqu� eliminas la carga de Bootstrap desde el CDN 5.3.3 si prefieres usar la versi�n local -->
    <link rel="stylesheet" href="../../resources/resources2/css/bootstrap.min.css" />

    <!-- Otros archivos CSS locales -->
    <link href="https://fonts.googleapis.com/css2?family=Baloo+2&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/resources2/css/kaiadmin.min.css" />
    <link rel="stylesheet" href="../../resources/css/main.css" />
    <link rel="stylesheet" href="../../resources/css/cards.scss" />

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
                String nombreUsuario = (String) session.getAttribute("nombreUsuario"); // Recupera el nombre de usuario de la sesi�n
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

        

                  
          <div class="postcard dark green">
    <a class="postcard__img_link" href="#">
      <img class="postcard__img" src="../../resources/img/card.jpg" alt="Image Title">
    </a>
    <div class="postcard__text">
      <h1 class="postcard__title green"><a href="#">BIENVENIDOS</a></h1>
      <div class="postcard__bar"></div>
      <div class="postcard__preview-txt">
      Explora las opciones de nuestro Men� Tablas
      para descubrir todas las funciones disponibles.
      </div>
      <ul class="postcard__tagbox">
      </ul>
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
      
      
      
      
      
      
      
      
      
     <!-- Core JS Files -->
    <script src="../../resources/resources2/js/core/jquery-3.7.1.min.js"></script>
    <script src="../../resources/resources2/js/core/popper.min.js"></script>
    <script src="../../resources/resources2/js/core/bootstrap.min.js"></script>
    <!-- jQuery Scrollbar -->
    <script src="../../resources/resources2/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
    <!-- Scrollbar JS -->
    <script src="../../resources/resources2/js/kaiadmin.min.js"></script>
  
  </body>
</html>
