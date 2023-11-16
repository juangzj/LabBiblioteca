<%@include file= "templates/header.jsp" %>

   
<!-- Section: Design Block -->

<section class="vh-100" style="background-color: #f1ddc7;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col col-xl-10">
        <div class="card" style="border-radius: 15px;  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);">
          <div class="row g-0">
            
            <div class="col-md-6 col-lg-7 d-flex align-items-center">
              <div class="card-body p-4 p-lg-5 text-black">
                  <center><h1>BIBLIOTECA</h1></center>
                  <br>
                <form action="SvLogin" method="POST">

                  <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Entra con tu cuenta</h5>
                  <!-- ALERTS -->
                    <div class="alert alert-success alert-dismissible" role="alert" id="liveAlertBtn"  style="display: none;" >
                        Te has registrado. Ingresa!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                  <div class="alert alert-danger alert-dismissible" role="alert" id="liveAlertBtn1"  style="display: none;" >
                        No te hemos registrado, intenta de nuevo 
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                  <div class="alert alert-danger alert-dismissible" role="alert" id="liveAlertBtn2"  style="display: none;" >
                        Datos incorrectos, intenta de nuevo
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                     <!-- PEDIR DATOS -->
                    <div class="form-outline mb-4">
                      <input type="number" name="cedula" class="form-control form-control-lg" required />
                    <label class="form-label" for="form2Example17">Cedula</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="password" name="contrasena" id="form2Example27" class="form-control form-control-lg" required />
                    <label class="form-label" for="form2Example27">Contraseña</label>
                  </div>

                  <div class="pt-1 mb-4">
                    <button class="btn btn-dark btn-lg btn-block" type="submit">Entrar</button>
                  </div>

                  <p class="mb-5 pb-lg-2" style="color: #393f81;">No está registrado? 
                      <a href="#!" data-bs-toggle="modal" data-bs-target="#exampleModal" style="color: #393f81;">Regístrate aquí</a></p>
                </form>

              </div>
                
            </div>
              <div class="col-md-6 col-lg-5 d-none d-md-block">
              <img src="https://plus.unsplash.com/premium_photo-1668198395277-de6e6f748065?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1yZWxhdGVkfDh8fHxlbnwwfHx8fHw%3D&w=1000&q=80"
                alt="login form" class="img-fluid" style="border-radius: 1rem " />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

 <!-- MODAL REGISTRO -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Registrate</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
         <form action="SvRegistrarUsuario" method="POST" class="row g-3 needs-validation">
                        
            <div class="col-md-4 input-group">
                <label class="input-group-text" for="cedula">Tu cédula:</label>
                <input type="number" id="cedula" name="cedula" class="form-control" required pattern="[0-9]*" required />
            </div>
            <div class="col-md-4 input-group">
                <label class="input-group-text" for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" class="form-control" required>
            </div>

            <div class="col-md-4 input-group">
                <label class="input-group-text" for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" class="form-control" required pattern="[^\s]+" title="No se permiten espacios" required/>               
            </div>
            
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Registrarse</button>
        </form>
      </div>
    </div>
  </div>
</div>


<script>
 //CASO REGISTRO EXITOSO
        document.addEventListener("DOMContentLoaded", function () {
        
        const registroSuccessAlert = document.getElementById('liveAlertBtn');

        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('alert') && urlParams.get('alert') === 'registro') {
            registroSuccessAlert.style.display = 'block';
        }
    });
    //CASO REGISTRO NO EXITOSO
    document.addEventListener("DOMContentLoaded", function () {
        
        const registroDangerAlert = document.getElementById('liveAlertBtn1');

        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('alert') && urlParams.get('alert') === 'registroerror') {
            registroDangerAlert.style.display = 'block';
        }
    });
    //CASO NO INGRESO
    document.addEventListener("DOMContentLoaded", function () {
        
        const registroDangerAlert = document.getElementById('liveAlertBtn2');

        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('alert') && urlParams.get('alert') === 'erroringreso') {
            registroDangerAlert.style.display = 'block';
        }
    });
</script>
<%@include file= "templates/footer.jsp" %> 
    