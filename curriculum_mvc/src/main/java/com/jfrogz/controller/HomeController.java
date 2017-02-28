package com.jfrogz.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class HomeController {

    public static Log logger = LogFactory.getLog(HomeController.class);

    @GetMapping("/curriculum")
    public ModelAndView curriculum (){
            logger.info("Comienza la solicitud del curriculum");
            ModelAndView andView = new ModelAndView("curriculum");
            andView.addObject("nombre", "Fernando Robles Guzmán");
            andView.addObject("carrera", "Ing. Ciencias de la Computación");
            andView.addObject("descripcionNot", "<h3>Auxiliar / Desarrollador de aplicaciones</h3>\n" +
                    "                                                            <p>Comienzo como capturista transcribiendo los datos importantes de los libros físicos a libros digitales de Excel.</p>\n" +
                    "\n" +
                    "                                                            <p>Se me asigna el archivo de la notaría para almacenar, guardar, reparar y llevar control de los apéndices, estos apéndices son los documentos originales que son utilizados para realizar alguna operación notarial, estos documentos son todos originales; el control que se llevaba también lo realizaba con libros de Excel.</p>\n" +
                    "\n" +
                    "                                                            <p>Analizo el proceso de mis nuevas asignaciones y analizo, desarrollo e implemento un programa escrito en java para llevar el control de los apéndices, realizando impresiones de cada uno de los movimientos que se realizaban, estos eran acuses, los cuales me debian firmar, este fue uno de los procesos que automatice.</p>\n" +
                    "\n" +
                    "                                                            <p>Aparte de tener mis propias actividades a realizar como lo era el cuidado, la ordenación, corrección y almacenado de los apéndices, captura de protocolos, noté que podía automatizar más procesos, el siguiente fue el de crear un programa que buscara archivos de Word en formatos (.doc, .rtf, .docx, .txt) que representaran un protocolo y así poder añadirlos a una carpeta y poder vincularlos a otro software. Esta operación que regularmente se hacía manualmente entre un tiempo de (8 hrs -16 hrs) fue optimizado, realizando el mismo trabajo en 3 min.</p>\n" +
                    "                                                            <p>Realice un programa en java que extraía información de una base de datos en un reporte que el programa en el cual se capturaba dicha información no podía ofrecer, el programa generaba reportes de acuerdo a su número de volumen creando “índices” importantes para el proceso: “Visita del comité del Archivo General de Notarías del Estado de Puebla”, ya que estos índices se tenían que entregar como parte de la documentación requerida por el comité.</p>\n" +
                    "                                                            <p>Programas solicitados por la parte de administración para la impresión de recibos de forma automática, se almacenaba en una base de datos la información que se requería, como era el nombre, fecha, monto y  se imprimían los recibos con la información antes mencionada junto con la información general de la notaría, Este programa llevaba el control de todos los recibos “Pagados, con adeudo y cancelados” pudiendo imprimir reportes por mes y año.</p>\n" +
                    "                                                            <p>Realice un programa que almacenaba toda la información referente a los primeros y segundos avisos preventivos. Los avisos preventivos tienen una vigencia, y al concluir ésta se podía hacer uso del bien inmueble para cualquier trámite que las personas interesadas pudieran realizar. El programa almacenaba la información con el fin de dar seguimiento y lanzar alarmas si es que los avisos estaban a punto de caducar, o si el aviso fue rechazado, o si ya no se necesitaba dicho recordatorio.<p>\n" +
                    "\n" +
                    "                                                            <p>Se me solicito un programa que vendría siendo el ultimo que desarrollaría para ellos, el programa se me solicito que fuera stand-alone, y que unificara la funcionalidad de todos los anteriores y que se le añadiera mucha más funcionalidad, al querer optimizar el proceso general de la notaria pensé en un sitio web en el cual los clientes mismos pudieran loguearse viendo ellos mismos el proceso de su operación. Este programa se comenzó pero no se terminó.</p>\n" +
                    "                                                            <p><strong>\n" +
                    "                                                                Nota importante:</strong> De todos los proyectos realizados, solo 3 fueron solicitados y solo 2 terminados y liberados, los demás se realizaron por iniciativa propia.<br>\n" +
                    "                                                                Nunca disminuyeron mis demás actividades, al contrario aumentaron, como:\n" +
                    "                                                            <p>El de mandar a empastar los apéndices que ya completaban toda una serie, capturando apéndices faltantes o el ir al Registro Público de la Propiedad a sacar copias del apéndices faltantes.</p>\n" +
                    "                                                            <p>Llevar todos los días los documentos que se requerían firmar a la casa de la Notario para que se firmaran y se continuara el proceso de la operación.</p>\n" +
                    "                                                            <p>Chofer de las abogadas que requerían ir a algún municipio de la entidad de Puebla, o algún otro lugar para recabar firmas a los clientes, ya que algunas abogadas no sabían manejar estándar.\n" +
                    "                                                            </p>");
       return andView;
    }

    @GetMapping("/home")
    public ModelAndView toHome (){
        logger.info("Comienza la solicitud del curriculum");
        ModelAndView andView = new ModelAndView("home");
        andView.addObject("nombre", "fernando");
        return andView;
    }
}
