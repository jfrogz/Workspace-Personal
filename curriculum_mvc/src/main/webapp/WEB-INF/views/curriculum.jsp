<%@ page import="javax.swing.*" %>
<%@ page import="com.jfrogz.Indentity.Generales" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jfrogz.Indentity.Telefono" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Curriculum de Fernando Robles</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/static/css/estilos.css">
</head>
<body>
<%
    Generales generales = (Generales) request.getAttribute("datosGen");
%>
<section class="jumbotron jumbotron-frog">
    <div class="container">
        <h1 class="titulo-blog">${datosGen.nombreCompleto}</h1>
        <p>${datosGen.carrera}</p>
    </div>
</section>

<section class="main container">
    <div class="row">
        <aside class="col-md-3">
            <a href="#" class="thumb pull-left">
                <img class="img-thumbnail img-responsive img-circle " src="/static/img/Personal Foto.jpg"
                     alt="widowmaker">
            </a>
            <div>
                <h4>Contacto</h4>
                <div class="btn-toolbar">
                    <div class="btn-group btn-group-md btn-group-justified" role="group">
                        <a href="#" class="btn btn-block btn-primary glyphicon glyphicon-envelope"
                           data-toggle="modal"></a>
                        <a href="#ventana1" class="btn btn-block btn-primary glyphicon glyphicon-phone"
                           data-toggle="modal"></a>
                        <a href="#" class="btn btn-block btn-primary glyphicon glyphicon-earphone"
                           aria-hidden="true"></a>
                        <a href="#" class="btn btn-block btn-primary glyphicon glyphicon-link"
                           aria-hidden="true"></a>
                    </div>
                </div>
                <!-- Contacto -->
                <div class="modal fade" id="ventana1">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Contenido de la ventana -->
                            <div class="modal-body">
                                <div class="list-group">
                                    <%
                                        List<Telefono> list = generales.getTelefonos();
                                        for (Telefono telefono : list) {
                                            out.print("<div class=\"panel panel-primary\">\n" +
                                                    "<div class=\"panel-heading\">" + telefono.getDescripcion() + "</div>\n" +
                                                    "<div class=\"panel-body\">" +
                                                    "<span class=\"text-success " + telefono.getNombreIcon() + "\">" + telefono.getNumero() + "</span></div>\n" +
                                                    "</div>");
                                        }
                                    %>
                                </div>
                            </div>
                            <!-- Footer de la Ventana -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <!-- Status profesional-->
                <div class="panel panel-primary  hidden-xs">
                    <div class="panel-heading">
                        <h3 class="panel-title">Status profesional</h3>
                    </div>
                    <div class="panel-body">
                        <h4 class="label label-success">
                            <%
                                if (generales.isEmpleado()) {
                                    out.print("<span class=\"glyphicon glyphicon-ok\"></span>");
                                }
                            %>
                            Empleado</h4><br>
                        <h4 class="label label-primary"><%
                            if (generales.isEscuchandoOfertas()) {
                                out.print("<span class=\"glyphicon glyphicon-ok\"></span>");
                            }
                        %>  Escuchando ofertas</h4>
                        <br>
                        <h4 class="label label-default"><%
                            if (!generales.isEmpleado()) {
                                out.print("<span class=\"glyphicon glyphicon-ok\"></span>");
                            }
                        %>  Disponible</h4>
                    </div>
                </div>
                <!-- Sobre mi -->
                <div class="panel panel-primary  hidden-xs ">
                    <div class="panel-heading">
                        <h3 class="panel-title">Sobre mi</h3>
                    </div>
                    <div class="panel-body text-justify">
                        <%
                            String[] parrafos = generales.getSobreMi().split("  ");
                            String formato = "";
                            for (String parrafo : parrafos) {
                                formato += "<p>" + parrafo + "</p>";
                            }
                        %>
                        <%=formato%>
                    </div>
                </div>
            </div>
        </aside>
        <section class="posts col-md-9">
            <br>
            <div role="tabpanel">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#ExpLaboral" aria-controls="ExpLaboral"
                                                              data-toggle="tab" role="tab">Experiencia Laboral</a>
                    </li>
                    <li role="presentation"><a href="#Formacion" aria-controls="Formacion" data-toggle="tab"
                                               role="tab">Formacion</a>
                    </li>
                    <li role="presentation"><a href="#Competencias" aria-controls="Competencias" data-toggle="tab"
                                               role="tab">Competencias</a></li>
                    <li role="presentation"><a href="#Intereses" aria-controls="Intereses" data-toggle="tab"
                                               role="tab">Intereses</a>
                    </li>
                    <li role="presentation"><a href="#Contacto" aria-controls="Contacto" data-toggle="tab"
                                               role="tab">Contacto</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="ExpLaboral">
                        <!-- Acordeon -->
                        <br>
                        <div class="panel-group" id="accordion" role="tablist">
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="heading1">
                                    <h4 class="panel-title">
                                        <a href="#collapseGenco" data-toggle="collapse" data-parent="#accordion">
                                            GENCO CONSULTING<span class="badge pull-right">04 ABRIL 2016 ~  </span></a>
                                    </h4>
                                </div>
                                <div id="collapseGenco" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="media">
                                            <div class="media-left media-middle hidden-xs hidden-md"><a
                                                    href="#"><img
                                                    src="/static/img/Logo_Genco.jpg" alt="">
                                            </a></div>
                                            <div class="media-body">
                                                <div role="tabpanel">
                                                    <ul class="nav nav-tabs" role="tablist">
                                                        <li role="presentation" class="active"><a
                                                                href="#secGencoActividades"
                                                                aria-controls="secGencoActividades"
                                                                data-toggle="tab"
                                                                role="tab">Actividades</a></li>
                                                        <li role="presentation"><a href="#secGencoHerramientas"
                                                                                   aria-controls="secGencoHerramientas"
                                                                                   data-toggle="tab" role="tab">Software
                                                            Utilizado</a></li>
                                                        <li role="presentation"><a href="#secGencoContacto"
                                                                                   aria-controls="secGencoContacto"
                                                                                   data-toggle="tab"
                                                                                   role="tab">Contacto</a></li>
                                                    </ul>
                                                    <div class="tab-content">
                                                        <div role="tabpanel" class="tab-pane active"
                                                             id="secGencoActividades">

                                                            <h3>Consultor / Desarrollador de aplicaciones WEB</h3>
                                                            <p>La actividad era la de dar mantenimiento a una
                                                                aplicación
                                                                dedicada al cálculo de una nómina, este software
                                                                está
                                                                desarrollando en JAVA utilizando el framework ADF,
                                                                tecnologías propias de ORACLE.</p>
                                                            <p>El mantenimiento consisitia en realizar pruebas
                                                                dinamicas
                                                                para detectar los issues que posteriormente
                                                                reparaba, en
                                                                algunos otros casos se generaban requerimientos los
                                                                cuales tenia de implementar, programando la base de
                                                                datos que se almacena con la tecnología ORACLE y su
                                                                respectiva lógica y vista.</p>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane"
                                                             id="secGencoHerramientas">
                                                            <h3>Software Utilizado </h3>
                                                            <ul class="list-group">
                                                                <li class="list-group-item list-group-item-warning">
                                                                    JDEVELOPER, VISUAL STUDIO, ECLIPSE, NETBEANS
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    ORACLE DATA BASE, SQL SERVER
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    ADF
                                                                    FRAMEWORK
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    NAVICAT, SQL DEVELOPER
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    TRELLO
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    GIT,
                                                                    GIT HUB, BITBUCKET
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    JAVA, C#
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane" id="secGencoContacto">
                                                            <h3>Contacto</h3>
                                                            <p></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="heading1">
                                    <h4 class="panel-title">
                                        <a href="#collapseTSystems" data-toggle="collapse" data-parent="#accordion">
                                            T-SYSTEMS MÉXICO<span
                                                class="badge pull-right">01 JULIO 2015 ~ 29 ENERO 2016</span></a>
                                    </h4>
                                </div>
                                <div id="collapseTSystems" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="media">
                                            <div class="media-left media-middle hidden-xs hidden-md"><a
                                                    href="#"><img
                                                    src="/static/img/tsystems4.jpg" alt="">
                                            </a></div>
                                            <div class="media-body">
                                                <div role="tabpanel">
                                                    <ul class="nav nav-tabs" role="tablist">
                                                        <li role="presentation" class="active"><a
                                                                href="#secTSysmtesActividades"
                                                                aria-controls="secTSysmtesActividades"
                                                                data-toggle="tab"
                                                                role="tab">Actividades</a></li>
                                                        <li role="presentation"><a href="#secTSystemsHerramientas"
                                                                                   aria-controls="secTSystemsHerramientas"
                                                                                   data-toggle="tab" role="tab">Software
                                                            Utilizado</a></li>
                                                        <li role="presentation"><a href="#secTSystemsContacto"
                                                                                   aria-controls="secTSystemsContacto"
                                                                                   data-toggle="tab"
                                                                                   role="tab">Contacto</a></li>
                                                    </ul>
                                                    <div class="tab-content">
                                                        <div role="tabpanel" class="tab-pane active"
                                                             id="secTSysmtesActividades">

                                                            <h3>Tester de aplicaciones WEB</h3>
                                                            <p>Se me contrata como tester de aplicaciones web, en la
                                                                empresa se me capacita con herramientas dedicadas a
                                                                realizar pruebas de software tales como HP LOAD
                                                                RUNNER,
                                                                SILK, SILK MANAGER, con las cuales realizamos
                                                                diferentes
                                                                tipos de pruebas como: Pruebas de performance,
                                                                pruebas
                                                                de caja negra, pruebas funcionales, etc.</p>
                                                            <p>En algun momento se me transfiere al area de
                                                                desarrollo
                                                                en el cual se me encomienda realizar pruebas
                                                                tempranas,
                                                                es decir, realizar pruebas del software antes de
                                                                mandarlo al area de pruebas, esto con el fin de
                                                                minimizar los issues, entregar un producto de mejor
                                                                calidad y en menor tiempo.</p>
                                                            <p>Todos los días en el DAILY SCRUM reportaba todos los
                                                                issues detectatos en el transcurso del día anterior,
                                                                issues corregidos y cerrados.</p>
                                                            <p>Entre otras actividades tambien estuve apoyando en el
                                                                área de calidad en donde verificaba que los nuevos
                                                                requerimientos que se nos pedian no estuvieran fuera
                                                                del
                                                                sprint acordado. Revisaba que tuvieramos las reglas
                                                                de
                                                                negocio que se requerian para armar el módulo
                                                                solicitado.</p>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane"
                                                             id="secTSystemsHerramientas">
                                                            <h3>Software Utilizado </h3>
                                                            <ul class="list-group">
                                                                <li class="list-group-item list-group-item-warning">
                                                                    VISUAL STUDIO, SILK, SILK CENTRAL, HP LOAD
                                                                    RUNNER,
                                                                    ISSUE TRACKER
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SQL
                                                                    SERVER
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    SQL
                                                                    SERVER MANAGER STUDIO
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SCRUM
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    C#
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane"
                                                             id="secTSystemsContacto">
                                                            <h3>Contacto</h3>
                                                            <p></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="heading1">
                                    <h4 class="panel-title">
                                        <a href="#collapseVW" data-toggle="collapse" data-parent="#accordion">
                                            VOLKSWAGEN MÉXICO<span class="badge pull-right">19 ENERO 2015 ~ 19 ABRIL 2015</span></a>
                                    </h4>
                                </div>
                                <div id="collapseVW" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="media">
                                            <div class="media-left media-middle hidden-xs hidden-md"><a
                                                    href="#"><img
                                                    src="img/vw.jpg" alt="">
                                            </a></div>
                                            <div class="media-body">
                                                <div role="tabpanel">
                                                    <ul class="nav nav-tabs" role="tablist">
                                                        <li role="presentation" class="active"><a
                                                                href="#secVWActividades"
                                                                aria-controls="secVWActividades" data-toggle="tab"
                                                                role="tab">Actividades</a></li>
                                                        <li role="presentation"><a href="#secVWHerramientas"
                                                                                   aria-controls="secVWHerramientas"
                                                                                   data-toggle="tab" role="tab">Software
                                                            Utilizado</a></li>
                                                        <li role="presentation"><a href="#secVWContacto"
                                                                                   aria-controls="secVWContacto"
                                                                                   data-toggle="tab"
                                                                                   role="tab">Contacto</a></li>
                                                    </ul>
                                                    <div class="tab-content">
                                                        <div role="tabpanel" class="tab-pane active"
                                                             id="secVWActividades">

                                                            <h3>Desarrollador de aplicaciones JAVA</h3>
                                                            <p>Se desarrollo un software standalone usando la
                                                                tecnología
                                                                JavaFX que sirve como interfaz entre el usuario y
                                                                una
                                                                báscula industrial.</p>
                                                            <p>Su funcionalidad consisitia en registrar el peso
                                                                total de
                                                                los camiones que entraban y salian con material, el
                                                                software hacia los calculos de los pesos de material
                                                                que
                                                                se compraban y vendian generando asi un ticket de
                                                                entrada y uno de salida para calcular los montos y
                                                                poder
                                                                facturar, estos datos son almacenados en una base de
                                                                datos desarrollada en SQL Server.</p>
                                                            <p>Un framework utilizado fue Hibernate que fue
                                                                utilizado
                                                                como una de las capas MVC de la arquitectura.
                                                            <p>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane"
                                                             id="secVWHerramientas">
                                                            <h3>Software Utilizado </h3>
                                                            <ul class="list-group">
                                                                <li class="list-group-item list-group-item-warning">
                                                                    NETBEANS, IREPORT
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SQL
                                                                    SERVER
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    HIBERNATE FRAMEWORK
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SVN,
                                                                    TORTOISE
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    JAVA, JAVA FX, CSS3
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SCRUM
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    SONAR QUBE, MAVEN,
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane" id="secVWContacto">
                                                            <h3>Contacto</h3>
                                                            <p></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="heading1">
                                    <h4 class="panel-title">
                                        <a href="#collapseN48" data-toggle="collapse" data-parent="#accordion">
                                            NOTARIA PUBLICA NO. 48<span class="badge pull-right">21 FEBRERO 2006 ~ 17 ENERO 2015</span></a>
                                    </h4>
                                </div>
                                <div id="collapseN48" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="media">
                                            <div class="media-left media-middle hidden-xs hidden-md"><a
                                                    href="#"><img
                                                    src="img/notaria.jpg" alt="">
                                            </a></div>
                                            <div class="media-body">
                                                <div role="tabpanel">
                                                    <ul class="nav nav-tabs" role="tablist">
                                                        <li role="presentation" class="active"><a
                                                                href="#secN48Actividades"
                                                                aria-controls="secN48Actividades" data-toggle="tab"
                                                                role="tab">Actividades</a></li>
                                                        <li role="presentation"><a href="#secN48Herramientas"
                                                                                   aria-controls="secN48Herramientas"
                                                                                   data-toggle="tab" role="tab">Software
                                                            Utilizado</a></li>
                                                        <li role="presentation"><a href="#secN48Contacto"
                                                                                   aria-controls="secN48Contacto"
                                                                                   data-toggle="tab"
                                                                                   role="tab">Contacto</a></li>
                                                    </ul>
                                                    <div class="tab-content">
                                                        <div role="tabpanel" class="tab-pane active"
                                                             id="secN48Actividades">
                                                            ${descripcionNot}
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane"
                                                             id="secN48Herramientas">
                                                            <h3>Software Utilizado </h3>
                                                            <ul class="list-group">
                                                                <li class="list-group-item list-group-item-warning">
                                                                    VISUAL STUDIO, ECLIPSE, NETBEANS, CRISTAL
                                                                    REPORT,
                                                                    IREPORT
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SQL
                                                                    SERVER, MYSQL, ACCESS
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    SPRING, HIBERNATE
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    MYSQL WORKBENCH, SQL SERVER MANAGER STUDIO
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    JAVA, C#, CSS 3, HTML
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane" id="secN48Contacto">
                                                            <h3>Contacto</h3>
                                                            <p></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="Formacion">
                        <!-- Acordeon -->
                        <br>
                        <div class="panel-group" id="accordion" role="tablist">
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="heading2">
                                    <h4 class="panel-title">
                                        <a href="#collapseBUAP" data-toggle="collapse" data-parent="#accordion">UNIVERSIDAD<span
                                                class="badge pull-right">Generación 2014</span></a></h4>
                                </div>
                                <div id="collapseBUAP" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <section class="posts col-md-9">

                                            <article class="post clearfix">
                                                <a href="#" class="thumb pull-left">
                                                    <img class="thumbnail" src="img/widowmaker.jpg" alt="buap">
                                                </a>
                                                <h3 class="post-title">
                                                    <a href="#">BENEMERITA UNIVERSIDAD AUTÓNOMA DE PUEBLA</a>
                                                </h3>
                                                <p><span class="post-fecha"><strong>Generación:</strong> 2014</span>
                                                </p>
                                                <p><span class="post-fecha"><strong>Carrera:</strong> Ingeniería en Ciencias de la Computación</span>
                                                </p>
                                                <p><span class="post-fecha"><strong>Documento obtenido:</strong> Carta pasante</span>
                                                </p>
                                                <p class="post-contenido text-justify">
                                                    Actualmente el titulo se encuentra en tramite.
                                                </p>

                                            </article>


                                        </section>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="heading1">
                                    <h4 class="panel-title">
                                        <a href="#collapseTSystems" data-toggle="collapse" data-parent="#accordion">
                                            T-SYSTEMS MÉXICO<span
                                                class="badge pull-right">01 JULIO 2015 ~ 29 ENERO 2016</span></a>
                                    </h4>
                                </div>
                                <div id="collapseTSystems" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="media">
                                            <div class="media-left media-middle hidden-xs hidden-md"><a
                                                    href="#"><img
                                                    src="img/tsystems4.jpg" alt="">
                                            </a></div>
                                            <div class="media-body">
                                                <div role="tabpanel">
                                                    <ul class="nav nav-tabs" role="tablist">
                                                        <li role="presentation" class="active"><a
                                                                href="#secTSysmtesActividades"
                                                                aria-controls="secTSysmtesActividades"
                                                                data-toggle="tab"
                                                                role="tab">Actividades</a></li>
                                                        <li role="presentation"><a href="#secTSystemsHerramientas"
                                                                                   aria-controls="secTSystemsHerramientas"
                                                                                   data-toggle="tab" role="tab">Software
                                                            Utilizado</a></li>
                                                        <li role="presentation"><a href="#secTSystemsContacto"
                                                                                   aria-controls="secTSystemsContacto"
                                                                                   data-toggle="tab"
                                                                                   role="tab">Contacto</a></li>
                                                    </ul>
                                                    <div class="tab-content">
                                                        <div role="tabpanel" class="tab-pane active"
                                                             id="secTSysmtesActividades">

                                                            <h3>Tester de aplicaciones WEB</h3>
                                                            <p>Se me contrata como tester de aplicaciones web, en la
                                                                empresa se me capacita con herramientas dedicadas a
                                                                realizar pruebas de software tales como HP LOAD
                                                                RUNNER,
                                                                SILK, SILK MANAGER, con las cuales realizamos
                                                                diferentes
                                                                tipos de pruebas como: Pruebas de performance,
                                                                pruebas
                                                                de caja negra, pruebas funcionales, etc.</p>
                                                            <p>En algun momento se me transfiere al area de
                                                                desarrollo
                                                                en el cual se me encomienda realizar pruebas
                                                                tempranas,
                                                                es decir, realizar pruebas del software antes de
                                                                mandarlo al area de pruebas, esto con el fin de
                                                                minimizar los issues, entregar un producto de mejor
                                                                calidad y en menor tiempo.</p>
                                                            <p>Todos los días en el DAILY SCRUM reportaba todos los
                                                                issues detectatos en el transcurso del día anterior,
                                                                issues corregidos y cerrados.</p>
                                                            <p>Entre otras actividades tambien estuve apoyando en el
                                                                área de calidad en donde verificaba que los nuevos
                                                                requerimientos que se nos pedian no estuvieran fuera
                                                                del
                                                                sprint acordado. Revisaba que tuvieramos las reglas
                                                                de
                                                                negocio que se requerian para armar el módulo
                                                                solicitado.</p>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane"
                                                             id="secTSystemsHerramientas">
                                                            <h3>Software Utilizado </h3>
                                                            <ul class="list-group">
                                                                <li class="list-group-item list-group-item-warning">
                                                                    VISUAL STUDIO, SILK, SILK CENTRAL, HP LOAD
                                                                    RUNNER,
                                                                    ISSUE TRACKER
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SQL
                                                                    SERVER
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    SQL
                                                                    SERVER MANAGER STUDIO
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SCRUM
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    C#
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane"
                                                             id="secTSystemsContacto">
                                                            <h3>Contacto</h3>
                                                            <p></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="heading1">
                                    <h4 class="panel-title">
                                        <a href="#collapseVW" data-toggle="collapse" data-parent="#accordion">
                                            VOLKSWAGEN MÉXICO<span class="badge pull-right">19 ENERO 2015 ~ 19 ABRIL 2015</span></a>
                                    </h4>
                                </div>
                                <div id="collapseVW" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="media">
                                            <div class="media-left media-middle hidden-xs hidden-md"><a
                                                    href="#"><img
                                                    src="img/vw.jpg" alt="">
                                            </a></div>
                                            <div class="media-body">
                                                <div role="tabpanel">
                                                    <ul class="nav nav-tabs" role="tablist">
                                                        <li role="presentation" class="active"><a
                                                                href="#secVWActividades"
                                                                aria-controls="secVWActividades" data-toggle="tab"
                                                                role="tab">Actividades</a></li>
                                                        <li role="presentation"><a href="#secVWHerramientas"
                                                                                   aria-controls="secVWHerramientas"
                                                                                   data-toggle="tab" role="tab">Software
                                                            Utilizado</a></li>
                                                        <li role="presentation"><a href="#secVWContacto"
                                                                                   aria-controls="secVWContacto"
                                                                                   data-toggle="tab"
                                                                                   role="tab">Contacto</a></li>
                                                    </ul>
                                                    <div class="tab-content">
                                                        <div role="tabpanel" class="tab-pane active"
                                                             id="secVWActividades">

                                                            <h3>Desarrollador de aplicaciones JAVA</h3>
                                                            <p>Se desarrollo un software standalone usando la
                                                                tecnología
                                                                JavaFX que sirve como interfaz entre el usuario y
                                                                una
                                                                báscula industrial.</p>
                                                            <p>Su funcionalidad consisitia en registrar el peso
                                                                total de
                                                                los camiones que entraban y salian con material, el
                                                                software hacia los calculos de los pesos de material
                                                                que
                                                                se compraban y vendian generando asi un ticket de
                                                                entrada y uno de salida para calcular los montos y
                                                                poder
                                                                facturar, estos datos son almacenados en una base de
                                                                datos desarrollada en SQL Server.</p>
                                                            <p>Un framework utilizado fue Hibernate que fue
                                                                utilizado
                                                                como una de las capas MVC de la arquitectura.
                                                            <p>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane"
                                                             id="secVWHerramientas">
                                                            <h3>Software Utilizado </h3>
                                                            <ul class="list-group">
                                                                <li class="list-group-item list-group-item-warning">
                                                                    NETBEANS, IREPORT
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SQL
                                                                    SERVER
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    HIBERNATE FRAMEWORK
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SVN,
                                                                    TORTOISE
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    JAVA, JAVA FX, CSS3
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SCRUM
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    SONAR QUBE, MAVEN,
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane" id="secVWContacto">
                                                            <h3>Contacto</h3>
                                                            <p></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="heading1">
                                    <h4 class="panel-title">
                                        <a href="#collapseN48" data-toggle="collapse" data-parent="#accordion">
                                            NOTARIA PUBLICA NO. 48<span class="badge pull-right">21 FEBRERO 2006 ~ 17 ENERO 2015</span></a>
                                    </h4>
                                </div>
                                <div id="collapseN48" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="media">
                                            <div class="media-left media-middle hidden-xs hidden-md"><a
                                                    href="#"><img
                                                    src="img/notaria.jpg" alt="">
                                            </a></div>
                                            <div class="media-body">
                                                <div role="tabpanel">
                                                    <ul class="nav nav-tabs" role="tablist">
                                                        <li role="presentation" class="active"><a
                                                                href="#secN48Actividades"
                                                                aria-controls="secN48Actividades" data-toggle="tab"
                                                                role="tab">Actividades</a></li>
                                                        <li role="presentation"><a href="#secN48Herramientas"
                                                                                   aria-controls="secN48Herramientas"
                                                                                   data-toggle="tab" role="tab">Software
                                                            Utilizado</a></li>
                                                        <li role="presentation"><a href="#secN48Contacto"
                                                                                   aria-controls="secN48Contacto"
                                                                                   data-toggle="tab"
                                                                                   role="tab">Contacto</a></li>
                                                    </ul>
                                                    <div class="tab-content">
                                                        <div role="tabpanel" class="tab-pane active"
                                                             id="secN48Actividades">

                                                            <h3>Auxiliar / Desarrollador de aplicaciones</h3>
                                                            <p>Comienzo como capturista transcribiendo los datos
                                                                importantes de los libros físicos a libros digitales
                                                                de
                                                                Excel.</p>

                                                            <p>Se me asigna el archivo de la notaría para almacenar,
                                                                guardar, reparar y llevar control de los apéndices,
                                                                estos apéndices son los documentos originales que
                                                                son
                                                                utilizados para realizar alguna operación notarial,
                                                                estos documentos son todos originales; el control
                                                                que se
                                                                llevaba también lo realizaba con libros de
                                                                Excel.</p>

                                                            <p>Analizo el proceso de mis nuevas asignaciones y
                                                                analizo,
                                                                desarrollo e implemento un programa escrito en java
                                                                para
                                                                llevar el control de los apéndices, realizando
                                                                impresiones de cada uno de los movimientos que se
                                                                realizaban, estos eran acuses, los cuales me debian
                                                                firmar, este fue uno de los procesos que
                                                                automatice.</p>

                                                            <p>Aparte de tener mis propias actividades a realizar
                                                                como
                                                                lo era el cuidado, la ordenación, corrección y
                                                                almacenado de los apéndices, captura de protocolos,
                                                                noté
                                                                que podía automatizar más procesos, el siguiente fue
                                                                el
                                                                de crear un programa que buscara archivos de Word en
                                                                formatos (.doc, .rtf, .docx, .txt) que representaran
                                                                un
                                                                protocolo y así poder añadirlos a una carpeta y
                                                                poder
                                                                vincularlos a otro software. Esta operación que
                                                                regularmente se hacía manualmente entre un tiempo de
                                                                (8
                                                                hrs -16 hrs) fue optimizado, realizando el mismo
                                                                trabajo
                                                                en 3 min.</p>
                                                            <p>Realice un programa en java que extraía información
                                                                de
                                                                una base de datos en un reporte que el programa en
                                                                el
                                                                cual se capturaba dicha información no podía
                                                                ofrecer, el
                                                                programa generaba reportes de acuerdo a su número de
                                                                volumen creando “índices” importantes para el
                                                                proceso:
                                                                “Visita del comité del Archivo General de Notarías
                                                                del
                                                                Estado de Puebla”, ya que estos índices se tenían
                                                                que
                                                                entregar como parte de la documentación requerida
                                                                por el
                                                                comité.</p>
                                                            <p>Programas solicitados por la parte de administración
                                                                para
                                                                la impresión de recibos de forma automática, se
                                                                almacenaba en una base de datos la información que
                                                                se
                                                                requería, como era el nombre, fecha, monto y se
                                                                imprimían los recibos con la información antes
                                                                mencionada junto con la información general de la
                                                                notaría, Este programa llevaba el control de todos
                                                                los
                                                                recibos “Pagados, con adeudo y cancelados” pudiendo
                                                                imprimir reportes por mes y año.</p>
                                                            <p>Realice un programa que almacenaba toda la
                                                                información
                                                                referente a los primeros y segundos avisos
                                                                preventivos.
                                                                Los avisos preventivos tienen una vigencia, y al
                                                                concluir ésta se podía hacer uso del bien inmueble
                                                                para
                                                                cualquier trámite que las personas interesadas
                                                                pudieran
                                                                realizar. El programa almacenaba la información con
                                                                el
                                                                fin de dar seguimiento y lanzar alarmas si es que
                                                                los
                                                                avisos estaban a punto de caducar, o si el aviso fue
                                                                rechazado, o si ya no se necesitaba dicho
                                                                recordatorio.
                                                            <p>

                                                            <p>Se me solicito un programa que vendría siendo el
                                                                ultimo
                                                                que desarrollaría para ellos, el programa se me
                                                                solicito
                                                                que fuera stand-alone, y que unificara la
                                                                funcionalidad
                                                                de todos los anteriores y que se le añadiera mucha
                                                                más
                                                                funcionalidad, al querer optimizar el proceso
                                                                general de
                                                                la notaria pensé en un sitio web en el cual los
                                                                clientes
                                                                mismos pudieran loguearse viendo ellos mismos el
                                                                proceso
                                                                de su operación. Este programa se comenzó pero no se
                                                                terminó.</p>
                                                            <p><strong>
                                                                Nota importante:</strong> De todos los proyectos
                                                                realizados, solo 3 fueron solicitados y solo 2
                                                                terminados y liberados, los demás se realizaron por
                                                                iniciativa propia.<br>
                                                                Nunca disminuyeron mis demás actividades, al
                                                                contrario
                                                                aumentaron, como:
                                                            <p>El de mandar a empastar los apéndices que ya
                                                                completaban
                                                                toda una serie, capturando apéndices faltantes o el
                                                                ir
                                                                al Registro Público de la Propiedad a sacar copias
                                                                del
                                                                apéndices faltantes.</p>
                                                            <p>Llevar todos los días los documentos que se requerían
                                                                firmar a la casa de la Notario para que se firmaran
                                                                y se
                                                                continuara el proceso de la operación.</p>
                                                            <p>Chofer de las abogadas que requerían ir a algún
                                                                municipio
                                                                de la entidad de Puebla, o algún otro lugar para
                                                                recabar
                                                                firmas a los clientes, ya que algunas abogadas no
                                                                sabían
                                                                manejar estándar.
                                                            </p>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane"
                                                             id="secN48Herramientas">
                                                            <h3>Software Utilizado </h3>
                                                            <ul class="list-group">
                                                                <li class="list-group-item list-group-item-warning">
                                                                    VISUAL STUDIO, ECLIPSE, NETBEANS, CRISTAL
                                                                    REPORT,
                                                                    IREPORT
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    SQL
                                                                    SERVER, MYSQL, ACCESS
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    SPRING, HIBERNATE
                                                                </li>
                                                                <li class="list-group-item list-group-item-default">
                                                                    MYSQL WORKBENCH, SQL SERVER MANAGER STUDIO
                                                                </li>
                                                                <li class="list-group-item list-group-item-warning">
                                                                    JAVA, C#, CSS 3, HTML
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane" id="secN48Contacto">
                                                            <h3>Contacto</h3>
                                                            <p></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="Competencias">
                        <h3>Seccion #3</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                            consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="Intereses">
                        <h3>Intereses #3</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                            consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="Contacto">
                        <h3>Contacto #3</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                            consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    </div>
                </div>
            </div>
        </section>
    </div>
</section>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
                <p>
                    Juan fernando Robles Guzman
                </p>
            </div>
            <div class="col-xs-6">
                <li>
                    <a href="#">Inicio</a>
                    <a href="#">Cursos</a>
                    <a href="#">Pruebas</a>
                </li>
            </div>
        </div>
    </div>
</footer>

</body>
<script src="/static/js/jquery-1.12.4.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
</html>