<%-- 
    Document   : alumnosAjax
    Created on : 02-ene-2020, 11:13:38
    Author     : Francisco Luna
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../../../inc/meta.inc" />
        <title>Alumnos con Ajax</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap4-business-tycoon.css">
        <link rel="stylesheet" href="css/tablas.css">
    </head>

    <body>

        <div class="container mt-4">
            <div class="row my-3">
                <form action="Ajax" class="col justify-content-center form-inline">
                    
                    <select id="grupo" name="grupo" class="form-control mr-md-2">
                        <c:forEach items="${applicationScope.grupos}" var="grupo">
                            <option value="${grupo.denominacion}">${grupo.denominacion}</option>
                        </c:forEach>
                    </select>
                    
                    <select id="equipo" name="equipo" class="form-control">
                        <c:forEach items="${applicationScope.equipos}" var="equipo">
                            <option value="${equipo.marca}">${equipo.marca}</option>
                        </c:forEach>
                    </select>
                    
                </form>
            </div>
            
            <div class="row table-responsive">
                <table class="table table-striped">
                    
                    <thead class="thead-dark">
                        <tr>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                        </tr>
                    </thead>
                    
                    <tbody id="tabla">
                        
                    </tbody>
                </table>
            </div>
            
            <div class="row">
                <div class="col-12 col-md-4 offset-md-4">
                    <a class="btn btn-block btn-secondary" href="Volver">Inicio</a>
                    </div>
            </div>
        </div>

    </div>

</body>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/AlumnosAjax.js"></script>

</body>

</html>
