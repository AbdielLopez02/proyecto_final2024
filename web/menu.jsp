<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Menu" %>
<%
    Menu menu = new Menu();
    List<Menu> menus = menu.obtenerMenus();
    List<Menu> menusJerarquicos = menu.construirJerarquia(menus);
    request.setAttribute("menus", menusJerarquicos);
%>
<html>
<head>
    <title>Menú</title>
    <!-- Fonts and icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
   <link rel="stylesheet" href="resources/resources2/css/bootstrap.min.css" />

    <!-- Otros archivos CSS locales -->
    <link href="https://fonts.googleapis.com/css2?family=Baloo+2&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="resources/resources2/css/kaiadmin.min.css" />
    <link rel="stylesheet" href="resources/css/main.css" />
    <link rel="stylesheet" href="resources/css/cards.scss" />
    <link rel="stylesheet" href="resources/resources2/css/plugins.min.css" />
    <link rel="stylesheet" href="resources/resources2/css/demo.css" />
</head>




<body>
     
    
    
      <!-- Sidebar -->
      <div class="sidebar" data-background-color="dark">
        <div class="sidebar-logo">
          <!-- Logo Header -->
          <div class="logo-header" data-background-color="dark">
           <a href="/proyecto_f/vista/main/main.jsp" class="logo">
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
            <a href="/proyecto_f/vista/main/main.jsp" class="nav-link">
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
             
             
             <c:forEach var="menu" items="${menus}">
                <c:if test="${menu.idPadre == 0}">
                    <li class="nav-item">
                        <a data-bs-toggle="collapse" href="#menu${menu.id}" >
                            <i class="${menu.icono}"></i>
                            <p class="menu-header">${menu.nombre}</p>
                            <span class="caret"></span>
                        </a>
                        <div class="collapse" id="menu${menu.id}">
                            <ul class="nav nav-collapse">
                                <li>
                                    <a href="${menu.url}">
                                        <span class="sub-item">${menu.nombre}</span>
                                    </a>
                                </li>
                                <c:forEach var="submenu" items="${menu.submenus}">
                                    <li>
                                        <a href="${submenu.url}">
                                            <span class="sub-item">${submenu.nombre}</span>
                                        </a>
                                    </li>
                                      <c:forEach var="submenu" items="${submenu.submenus}">
                                    <li>
                                        <a href="${submenu.url}">
                                            <span class="sub-item">${submenu.nombre}</span>
                                        </a>
                                    </li>
                                      </c:forEach>
                             </c:forEach>
                            
                            </ul>
                        </div>
                    </li>
                </c:if>
            </c:forEach>

          
            </ul>
          </div>
        </div>
      </div>
      <!-- End Sidebar -->

     

    
    
  
      <!-- End Sidebar -->
  <!--   Core JS Files  -->
  
  <script src="resources/resources2/js/core/jquery-3.7.1.min.js"></script>
<script src="resources/resources2/js/core/popper.min.js"></script>
<script src="resources/resources2/js/core/bootstrap.min.js"></script>
  

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha384-LZTz/rX2m2H/aAe4NYq40YxjL89DICM2Fev4M/tvSRe9TbFg8beP2B3Rar9A3zM0" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-e8dtFdxD4qRvTndk65X8zRLUblX2n8jNiMmjGu/AeGKeRi9c18OqdfdNwsHbcDBn" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 

<!-- Otros scripts -->


<script src="resources/resources2/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
<script src="resources/resources2/js/kaiadmin.min.js"></script>
<script src="resources/resources2/js/setting-demo2.js"></script>

<script>
    // Verificación del Referer: Redirige al index si se accede desde una URL copiada
    if (document.referrer === "") {
        // Redirigir a index.html con un parámetro de warning
        window.location.href = window.location.origin + "/proyecto_f/index.html?warning=access_denied";
    }
</script>
</body>
</html>
