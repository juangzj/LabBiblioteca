<%@page import="com.umariana.bibliotecaweb.ListaDobleEnlace"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />

    <!-- Font Awesome icons (free version) -->
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css" rel="stylesheet">


    <!-- Core theme CSS (includes custom styles) -->
    <link href="css/styles.css" rel="stylesheet" />

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/latest/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>


    <title>Biblioteca</title>
</head>


<body id="page-top">
    <%
    //Variables que mandan por el Sv al entrar
    String usuario=request.getParameter("usuario");
    String cedula=request.getParameter("cedula");
    %>
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand" href="login.jsp?usuario=<%=usuario%>&cedula=<%=cedula%>"><img src="assets/img/logo.png" alt="..." /></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                Menu
                <i class="fas fa-bars ms-1"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                    <li class="nav-item"><a class="nav-link" href="#inicio">Inicio</a></li>
                    <li class="nav-item"><a class="nav-link" href="#services">Servicios</a></li>
                    <li class="nav-item"><a class="nav-link" href="#services-new">Agregar</a></li>
                    <li class="nav-item"><a class="nav-link" href="#contact">Mis libros</a></li>
                     <li class="nav-item"><a class="nav-link" href="index.jsp">Cerrar Sesion</a></li>
                </ul>
            </div>
        </div>
    </nav>
     <%
        ServletContext context = getServletContext();//Contexto del servlet
        ListaDobleEnlace lista = ListaDobleEnlace.deserializarLista(context);//Deserializar
        //Si esta nula se crea 
        if(lista==null){
         lista=new ListaDobleEnlace();
        }
        //Obtiene parametros para buscar o mostrar alerta
        String busqueda=request.getParameter("busqueda");
        String tabla= "";
        String alerta=request.getParameter("alert");
        if(alerta!=null && alerta.equals("error")){
                   %>
            <!-- Método que permite mostrar el modal que informa que ya existe el ID en la lista -->
            <script>
                $(document).ready(function () {
                    errorLibro();
                });

            </script>
            <% 
        }
        //Se llena la tabla
        if(busqueda!=null){
           tabla=lista.MostrarListaBusqueda(busqueda, cedula);
        } else if(busqueda==null){
            tabla= lista.MostrarLista(cedula);
}
    %>
    <!-- principal-->
    <header class="masthead" id="inicio">
        <div class="container">
            <div class="masthead-subheading">Te damos la bienvenida,  <%out.println(request.getParameter("usuario"));%></div>
            <div class="masthead-heading text-uppercase">Gran biblioteca digital</div>
            <div class="search-container">
                <form class="search-form" role="search" action="SvEditar" method="GET">
                    <div>
                        <input name="usuario" value="<%=usuario%>" hidden>
                        <input name="cedula" value="<%=cedula%>" hidden>
                        <input class="form-control search-input" type="search" name="busqueda" placeholder="Buscar autor/titulo" aria-label="Search">
                        <button class="btn btn-outline-light custom-btn" name="busqueda" type="submit"  href="#contact">Buscar</button>
                    </div> 
                </form>
            </div>
            
        </div>
    </header>

    <!-- Servicios-->
    <section class="page-section" id="services">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">Servicios</h2>
                <h3 class="section-subheading text-muted">Mira los servicios que tenemos para ti.</h3>
            </div>
            <div class="row text-center">
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <i class="fas fa-circle fa-stack-2x text-primary"></i>
                        <i class="fa-solid fa-magnifying-glass fa-stack-1x fa-inverse"></i>
                    </span>
                    <h4 class="my-3">Busca libros</h4>
                    <p class="text-muted">"Descubre un mundo de conocimiento con nuestra función de búsqueda de libros.
                         Encuentra tu próxima aventura literaria con un solo clic. ¡Explora, lee y disfruta!.</p>
                </div>
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <i class="fas fa-circle fa-stack-2x text-primary"></i>
                        <i class="fas fa-laptop fa-stack-1x fa-inverse"></i>
                        <i class="fa-solid fa-plus"></i>
                    </span>
                    <h4 class="my-3">Agrega libros</h4>
                    <p class="text-muted">Amplía tu biblioteca personal con facilidad. Con nuestra función de agregar libros, 
                        puedes organizar y guardar tus títulos favoritos de manera sencilla. ¡Haz crecer tu colección literaria con un solo toque!</p>
                </div>
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <i class="fas fa-circle fa-stack-2x text-primary"></i>
                        <i class="fas fa-lock fa-stack-1x fa-inverse"></i>
                    </span>
                    <h4 class="my-3">Edita libros</h4>
                    <p class="text-muted">Personaliza tu experiencia literaria con nuestra función de editar libros. Dale un toque
                         único a tus lecturas, ajusta detalles y crea la versión perfecta para ti. ¡Haz que cada libro sea realmente tuyo!</p>
                </div>
            </div>
        </div>
    </section>

    <!-- agregar libro-->
    <section class="page-section" id="services-new">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">Agrega tu libro</h2>
            </div>
            <!-- Cierre de etiqueta nav -->
            <!-- Clase contenedora -->
            <div class="container p-4">
                <div class="row">
                    <!-- Columna izquierda para el formulario -->

                    <div class="card">
                        <div class="card-body"> <!-- Tarjeta de trabajo -->
                            <br>
                            <!-- Formulario que recibe todos los datos para agregar una tarea -->
                            <form action="SvLibros" method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="usuarioNombre" type="text" value="">
                                <div class="col-auto">
                                    <label class="visually-hidden">Titulo</label>
                                    <div class="input-group">
                                        <div class="input-group-text">Titulo</div>
                                        <input id="titulo" name="titulo" type="text" class="form-control" required>
                                        <input name="usuario" value="<%=usuario%>" hidden>
                                        <input name="cedula" value="<%=cedula%>" hidden>
                                    </div>
                                </div>

                                <br>
                                <div class="col-auto">
                                    <label class="visually-hidden">Autor</label>
                                    <div class="input-group">
                                        <div class="input-group-text">Autor</div>
                                        <input id="autor" name="autor" type="text" class="form-control" required>
                                    </div>
                                </div>
                                <br>
                                <div class="col-auto">
                                    <label class="visually-hidden">Año publicación</label>
                                    <div class="input-group">
                                        <div class="input-group-text">Año publicacion</div>
                                        <input id="anio" name="anio" type="number" minlength="4" maxlength="4" step="1"
                                            min="0" class="form-control" required>
                                    </div>
                                </div>
                                <br>
                                <div class="col-auto">
                                    <label class="visually-hidden">Foto portada</label>
                                    <div class="input-group">
                                        <div class="input-group-text">Foto portada</div>
                                        <input id="foto" name="foto" type="file" class="form-control" accept="img/*"
                                            required>
                                    </div>
                                </div>
                                <br>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="prestamo" value="true"
                                        id="flexCheckDefault">
                                    <label class="form-check-label" for="flexCheckDefault">
                                        ¿El libro fue prestado?
                                    </label>
                                </div>
                                <br>
                                <center>
                                    <button type="submit" class="btn btn-success">Agregar Tarea</button>
                                </center>
                            </form>
                        </div>
                    </div>
                </div> <!-- Cierre de la clase card card-body -->
                <!-- Cierre de la clase col-lg-4 col-md-4 -->
            </div>
    </section>


    <!-- mirar libros-->
    <section class="page-section" id="contact">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">Mira tus libros</h2>
            </div>

            <div class="container p-4">
                <!-- Columna del lado derecho para la tabla de datos -->

                <div class="card card-body"> <!-- Tarjeta de trabajo -->
                    <table class="table table-striped table-hover"> <!-- Estilo de la tabla de datos -->
                        <thead>
                            <tr>
                                <!-- Titulos de la tabla de datos -->
                                <th>Titulo</th>
                                <th>Autor</th>
                                <th>Año publicacion</th>
                                 <th>Portada</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%=tabla%>
                        </tbody>
                    </table> <!-- Cierre de la etiqueta table-->
                </div> <!-- Cierre de la clase card card-body -->
            </div> <!-- Cierre de la clase col-lg-8 col-md-8 -->
        </div>
    </section>

    <!-- Footer-->
    <footer class="footer py-4">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-4 text-lg-start">Copyright &copy; StarBooks</div>
                <div class="col-lg-4 my-3 my-lg-0">
                    <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Twitter"><i
                            class="fab fa-twitter"></i></a>
                    <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Facebook"><i
                            class="fab fa-facebook-f"></i></a>
                    <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="LinkedIn"><i
                            class="fab fa-linkedin-in"></i></a>
                </div>
                <div class="col-lg-4 text-lg-end">
                    <a class="link-dark text-decoration-none" href="#!">Estructuras de datos</a>
                </div>
            </div>
        </div>
    </footer>
    
    <!-- Modal de confirmación de eliminación -->
    <div class="modal fade" id="eliminar" tabindex="-1" aria-labelledby="eliminarLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="eliminarLabel">Confirmar eliminación</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <p>¿Estás seguro de que deseas eliminar el libro <span id="titulo-libro-eliminar"></span>?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                     <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModalConfirm" onclick="eliminarLibro(1)">Eliminar</button>
                </div>
            </div>
        </div>
    </div>
    <!-- modal 1 -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Detalles del libro</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="libro-details">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>
<div class="modal" tabindex="-1" id="libroError">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Error</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>No fue posible guardarlo, intenta de nuevo.</p>
      </div>
    </div>
  </div>
</div>

  <!------------------------------------------ Modales para editar las caracteristicas ---------------------------------------------->

    <!-- Modal principal para editar las caracteristicas de una tarea -->
    <div class="modal fade" id="editModalConfirm" tabindex="-1" aria-labelledby="editModalLabelConfirm" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabelConfirm">Editar información del libro</h5>
               
                </div>
                <div class="modal-body">
                    <div id="editModalConfirm" style="display: flex; justify-content: center;">
                        <button type="button" id="pasar" class="btn btn-success" data-bs-toggle="modal" style="margin-right: 15px;" >Editar autor</button>
                        <button type="button" id="pasar1"  class="btn btn-success" data-bs-toggle="modal" style="margin-right: 15px;" >Editar año</button>
                        <button type="button" id="pasar2"  class="btn btn-success" data-bs-toggle="modal" style="margin-right: 15px;" >Editar foto</button>
                        <button type="button" id="pasar3"  class="btn btn-success" data-bs-toggle="modal" style="margin-right: 15px;">Editar estado</button>
                        
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="margin-right: 10px;">Cancelar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal para editar el autor -->
    <div class="modal fade" id="editAutor" tabindex="-1" role="dialog" aria-labelledby="editTituloLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <form id="miFormularioTitulo" action="SvEditar" method="POST">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTituloLabel">Editar autor</></h5>
                         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div id="tarea-detalles" style="display: flex; justify-content: center;">
                            <label class="visually-hidden" for="descripcion">Autor</label>
                            <div class="input-group">
                                <div class="input-group-text">Ingresa el nuevo autor</div>
                                <input type="text" class="form-control" id="nuevoAutor" name="autor" required>
                                <input type="text" name="titulo" hidden required>
                                <input type="text" name="usuario" value="<%=usuario%>" hidden required> 
                                <input type="text" name="cedula" value="<%=cedula%>" hidden required>
                                
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                       <button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close" style="margin-right: 10px;">Cancelar</button>

                        <button type="submit" class="btn btn-danger" onclick="editarCaracteristicas('nuevoTitulo')">Actualizar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
        <!-- Modal para editar el año -->
    <div class="modal fade" id="editAnio" tabindex="-1" role="dialog" aria-labelledby="editTituloLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <form id="miFormularioTitulo" action="SvEditar" method="POST">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editAnioLabel">Editar autor</></h5>
                    </div>
                    <div class="modal-body">
                        <div id="tarea-detalles" style="display: flex; justify-content: center;">
                            <label class="visually-hidden" for="descripcion">Año</label>
                            <div class="input-group">
                                <div class="input-group-text">Ingresa el nuevo año</div>
                                <input type="number" class="form-control" id="nuevoAnio" name="anio" required>
                                <input type="text" name="titulo" hidden required>
                                <input type="text" name="usuario" value="<%=usuario%>" hidden required>
                                <input type="text" name="cedula" value="<%=cedula%>" hidden required>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                       <button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close"style="margin-right: 10px;">Cancelar</button>
                        <button type="submit" class="btn btn-danger" onclick="editarCaracteristicas('nuevoAnio')">Actualizar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
            <!-- Modal para editar la foto -->
    <div class="modal fade" id="editFoto" tabindex="-1" role="dialog" aria-labelledby="editTituloLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <form id="miFormularioTitulo" action="SvEditar" method="POST" enctype="multipart/form-data">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTituloLabel">Editar foto</h5>
                    </div>
                    <div class="modal-body">
                        <div id="tarea-detalles" style="display: flex; justify-content: center;">
                            <label class="visually-hidden" for="descripcion">Foto</label>
                            <div class="input-group">
                                <div class="input-group-text">Ingresa la nueva foto</div>
                                <input type="file" accept="img/*" class="form-control" id="nuevaFoto" name="foto" required>
                                <input type="text" name="verificacionFotos" value="si" hidden  required>
                                <input type="text" name="titulo" hidden required>
                                <input type="text" name="usuario" value="<%=usuario%>" hidden required>
                                <input type="text" name="cedula" value="<%=cedula%>" hidden required>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close"style="margin-right: 10px;">Cancelar</button>
                        <button type="submit" class="btn btn-danger" onclick="editarCaracteristicas('nuevaFoto')">Actualizar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
            <!-- Modal para editar el estado -->
    <div class="modal fade" id="editEstado" tabindex="-1" role="dialog" aria-labelledby="editEstadoLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <form id="miFormularioTitulo" action="SvEditar" method="POST" enctype="multipart/form-data">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTituloLabel">Editar estado</h5>
                    </div>
                    <div class="modal-body">
                        <div id="tarea-detalles" style="display: flex; justify-content: center;">
                            <label class="visually-hidden" for="descripcion">Estado</label>
                            <div class="input-group">
 
                                <input class="form-check-input" type="checkbox" name="prestamo" value="true" id="flexCheckDefault">
                                <label class="form-check-label" for="flexCheckDefault">
                                    ¿El libro fue prestado?
                                </label>
                                <input type="text" name="estado" value="si" hidden  required>
                                <input type="text" name="titulo"  hidden required>
                                <input type="text" name="usuario" value="<%=usuario%>" hidden required>
                                <input type="text" name="cedula" value="<%=cedula%>" hidden required>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close"style="margin-right: 10px;">Cancelar</button>
                        <button type="submit" class="btn btn-danger" onclick="editarCaracteristicas('nuevaFoto')">Actualizar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="js/scripts.js"></script>
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

    <script>
  /**
   * Esta función se encarga de mostrar el modal de confirmación antes de eliminar un libro.
   * Se dispara cuando se muestra el modal.
   */
  $('#eliminar').on('show.bs.modal', function (event) {
    // Obtiene el botón que desencadenó el evento de mostrar el modal
    var button = $(event.relatedTarget);

    // Obtiene el id de la tarea desde el atributo 'data-nombre' del botón
    var titulo1 = button.data('nombre');

    // Obtiene el modal actual
    var modal = $(this);

    // Almacena el nombre de la tarea en la variable global 'id'
    titulo = titulo1;
  });

  /**
   * Esta función se encarga de eliminar un libro a través de una solicitud AJAX al servidor.
   */
  function eliminarLibro() {
    // Obtiene el id de la tarea desde una variable previamente definida (id)
    var titulo2 = titulo;

    // Realiza una solicitud AJAX al servlet 'SvEliminar' para eliminar la solicitud
    $.ajax({
      url: 'SvLibros?titulo=' + titulo, // URL del servlet con el parámetro 'id' para la eliminación
      method: 'GET', // Método HTTP utilizado para la solicitud (GET en este caso)
      success: function (data) {
        // En caso de éxito en la solicitud:

        // Cierra el modal de eliminación
        $('#eliminar').modal('hide');

        // Recarga la página actual para reflejar los cambios
        location.reload();
      },
      error: function () {
        // En caso de error en la solicitud:

        // Registra un mensaje de error en la consola (para fines de depuración)
        console.log('Error al eliminar ');
      }
    });
  }
      // funcion para mostrar los datos en la ventana modal
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Botón que desencadenó el evento
        var nombreLibro = button.data('nombre'); // Obtener  el nombre del perro

        // Realiza una solicitud AJAX al servlet para obtener los detalles del perro por su nombre
        $.ajax({
            url: 'SvGestionVer?titulo=' + nombreLibro, // Enviamos el nombre y tipo de accion para saber el camino del switch
            method: 'GET',
            success: function (data) {
                // Actualiza el contenido del modal con los detalles del perro
                $('#libro-details').html(data);
            },
            error: function () {
                // Maneja errores aquí si es necesario
                console.log('Error al cargar los detalles del perro.');
            }
        });
    });
        function errorLibro() {
                $("#libroError").modal("show");
        }
        
     $('#editModalConfirm').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var tit = button.data('nombre');
         $('#titulo').text(tit);
        titulo=tit;
    });


  $('#editModalConfirm').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var tit = button.data('nombre');
    $('#titulo').text(tit);
    titulo = tit;

    // Al hacer clic en el botón, pasa la variable titulo a las modales
    $('#pasar').click(function () {
        $('#editAutor').data('titulo', titulo);
        $('#editAutor').modal('show'); // Muestra modal2
    });

    $('#pasar1').click(function () {
        // Asegúrate de definir el modal correspondiente (editAnio) antes de acceder a él
        $('#editAnio').data('titulo', titulo);
        $('#editAnio').modal('show'); // Muestra modal2
    });

    $('#pasar2').click(function () {
        // Asegúrate de definir el modal correspondiente (editFoto) antes de acceder a él
        $('#editFoto').data('titulo', titulo);
        $('#editFoto').modal('show'); // Muestra modal2
    });

    $('#pasar3').click(function () {
        // Asegúrate de definir el modal correspondiente (editEstado) antes de acceder a él
        $('#editEstado').data('titulo', titulo);
        $('#editEstado').modal('show'); // Muestra modal2
    });
});

// JavaScript para modal2
    $('#editAutor').on('show.bs.modal', function (event) {
        // Obtiene la variable pasada desde modal1 y la muestra en modal2
        var tit2 = $('#editAutor').data('titulo');
        $('#titEditar').text(tit2);

        var inputElement = $('#editAutor').find('input[name="titulo"]');

       inputElement.val(tit2);


    });
        $('#editAnio').on('show.bs.modal', function (event) {
        // Obtiene la variable pasada desde modal1 y la muestra en modal2
        var tit2 = $('#editAnio').data('titulo');
        $('#titEditar').text(tit2);

        var inputElement = $('#editAnio').find('input[name="titulo"]');

       inputElement.val(tit2);


    });
        $('#editFoto').on('show.bs.modal', function (event) {
        // Obtiene la variable pasada desde modal1 y la muestra en modal2
        var tit2 = $('#editFoto').data('titulo');
        $('#titEditar').text(tit2);

        var inputElement = $('#editFoto').find('input[name="titulo"]');

       inputElement.val(tit2);


    });
       $('#editEstado').on('show.bs.modal', function (event) {
        // Obtiene la variable pasada desde modal1 y la muestra en modal2
        var tit2 = $('#editEstado').data('titulo');
        $('#titEditar').text(tit2);

        var inputElement = $('#editEstado').find('input[name="titulo"]');

       inputElement.val(tit2);


    });
</script>
 <%@include file= "templates/footer.jsp" %> 
