/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import Controllers.ConfiguracionController;
import DAO.ConeccionAccess;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.CodeSource;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.action.Action;
import protocolsearch.ProtocolSearch;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author fox1x0d
 */
public class Utilerias {

    private static Pagination pagination;
    
    public static boolean escribirArchivoDeConfiguracion() {
        try {
            
            CodeSource codeSource = ProtocolSearch.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            File miArchivo = new File(jarDir + ProtocolSearch.ActualConfig.obtenerCaracterSeparador(jarDir) + "miConfiguracion.txt");
            FileOutputStream fos = new FileOutputStream(miArchivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ProtocolSearch.ActualConfig);
            oos.close();
            return true;
        } catch (URISyntaxException ex) {
            System.out.println("" + ex);
        } catch (FileNotFoundException ex) {
            System.out.println("" + ex);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
        finally
        {
            return false;
        }
    }
    
    public static boolean leerArchivoDeConfiguracion() {
        try {
            ObjectInputStream ois = null;
            CodeSource codeSource = ProtocolSearch.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();            
            File miArchivo = new File(jarDir + ProtocolSearch.ActualConfig.obtenerCaracterSeparador(jarDir) + "miConfiguracion.txt");           
            FileInputStream fis = new FileInputStream(miArchivo);
            ois = new ObjectInputStream(fis);
            ProtocolSearch.ActualConfig = (ConfiguracionActual)  ois.readObject();
            ois.close();
           //Pendiente Verificar que el archivo se leyo correctamente y mandar el correcto valor booleano
            
            ConeccionAccess access = new ConeccionAccess(ProtocolSearch.ActualConfig.getPathAccess(), ProtocolSearch.ActualConfig.getUsario(), ProtocolSearch.ActualConfig.getPassword());
            ProtocolSearch.ActualConfig.setConexion(access.EstablecerConexion());
            
            return true;
        } catch (URISyntaxException | ClassNotFoundException ex) {
            System.out.println("" + ex);
        } catch (FileNotFoundException ex) {
            System.out.println("" + ex);
        } catch (IOException ex) {
            System.out.println("" + ex);
        } catch (Exception ex)
        {
            System.out.println("" + ex);
        }
        return false;
    }

    public static String EncodeLatin (String cadenaTexto){
        // Vemos si el formato entrante es ASCII o UTF8
        CharsetEncoder isoEncoder = Charset.forName("ISO-8859-1").newEncoder();
        CharsetEncoder iso15Encoder = Charset.forName("ISO-8859-15").newEncoder();
        CharsetEncoder utf8Encoder = Charset.forName("UTF-8").newEncoder();
        CharsetEncoder latinEncoder = Charset.forName("LATIN1").newEncoder();
        CharsetEncoder winEncoder = Charset.forName("windows-1256").newEncoder();
        
        Boolean isISO = isoEncoder.canEncode(cadenaTexto);
        Boolean isISO15 = iso15Encoder.canEncode(cadenaTexto);
        Boolean isUTF8 =  utf8Encoder.canEncode(cadenaTexto);
        Boolean isLATIN = latinEncoder.canEncode(cadenaTexto);
        Boolean isWin = winEncoder.canEncode(cadenaTexto);
// 
//        if(isISO)
//            return "ISO";
//        if(isUTF8)
//            return "UTF8";
//        if(isLATIN)
//            return "LAtin";
// Convertir de UTF-8 a ISO-8859-1
        
            Charset utf8charset = Charset.forName("UTF-8");
            Charset latin1 = Charset.forName("LATIN1");
            Charset iso1 = Charset.forName("ISO-8859-1");
            Charset iso15 = Charset.forName("ISO-8859-15");
            Charset win = Charset.forName("windows-1256");
            
            
            
            CharBuffer data =null;
            // Decode UTF-8
            if(isWin){
            ByteBuffer bb = ByteBuffer.wrap(cadenaTexto.getBytes());
            data = win.decode(bb);
            }else
            
            
            if(isISO){
            ByteBuffer bb = ByteBuffer.wrap(cadenaTexto.getBytes());
            data = iso1.decode(bb);
            }else 
                if(isUTF8){
            ByteBuffer bb = ByteBuffer.wrap(cadenaTexto.getBytes());
            data = utf8charset.decode(bb);
            }
            
            
            // Encode ISO-8559-1
            ByteBuffer outputBuffer = utf8charset.encode(data);
            byte[] outputData = outputBuffer.array();

            String datos = new String(outputData);
            return datos;
        
    }
    
    public static Pagination creaPaginacionTableView(int renglonesMostrar, SortedList<?> lista, TableView<?> tabla, GridPane gridBotones) {

        int tamLista = lista.size();
        int totalPaginas = tamLista / renglonesMostrar;
        int modulo = tamLista % renglonesMostrar;
        if (modulo > 0) {
            totalPaginas = totalPaginas + 1;
        }

        pagination = new Pagination(totalPaginas, 0);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                if ((pageIndex > tamLista / renglonesMostrar)) {

                    return null;
                } else {
                    return createPage(tamLista, pageIndex, lista, tabla, renglonesMostrar, gridBotones);
                }
            }
        });
        pagination.setMaxPageIndicatorCount(10);
        return pagination;
    }

    public static VBox createPage(int tamLista, int pageIndex, SortedList<?> list, TableView tabla, int renglonesMostrar, GridPane gridBotones) {
        VBox box = new VBox(5);
        int inicio = pageIndex * renglonesMostrar;
        tabla.getItems().clear();
        tabla.setItems(FXCollections.observableArrayList(list.subList(inicio, Math.min(inicio + renglonesMostrar, tamLista))));
        box.getChildren().clear();
        box.getChildren().add(tabla);
        box.getChildren().add(gridBotones);
        return box;
    }
/*
    public static ObservableList<String> comboPaginacion() {
        String paginas[] = DataFXLauncher.mensajesPropertiesSeg.getProperty("paginas").split(",");
        ObservableList<String> options = FXCollections.observableArrayList(paginas);
        return options;
    }
*/
    public static VBox creaFiltro(String etiqueta) {
        TextField text = new TextField();
        Label label = new Label(etiqueta);
        VBox filtro = new VBox();
        filtro.setAlignment(Pos.CENTER);
        filtro.getChildren().addAll(label, text);
        filtro.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        filtro.autosize();
        filtro.setFillWidth(true);
        return filtro;
    }

    public static BorderPane creaFiltro2(String etiqueta) {
        TextField text = new TextField();
        Label label = new Label(etiqueta);

        BorderPane filtro = new BorderPane();
        filtro.setTop(label);
        filtro.setCenter(text);
        filtro.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        filtro.autosize();
        return filtro;
    }

    public static Stage nuevaVentana(String nombreFxml, Object controlador, String titulo) {
        try {
            InputStream is = null;
            Node node = null;
            Stage stage = new Stage();
            is = Utilerias.class.getResourceAsStream(nombreFxml);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(controlador);
            node = (Pane) fxmlLoader.load(is);
            Scene scene = new Scene((Pane) node);
            //scene.getStylesheets().setAll(Utilerias.class.getResource("/css/estilos.css").toExternalForm());
            stage.setTitle(titulo);
            stage.setScene(scene);
            return stage;
        } catch (IOException ex) {
            Logger.getLogger(Utilerias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static EventHandler<KeyEvent> permiteLetrasNumeros() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                boolean pasa = false;

                if (e.getCharacter().matches("[A-Za-z_0-9+ñÑ.+\\s+áéíóú+]")) {
                } else {
                    e.consume();
                }
            }
        };
    }

    public static EventHandler<KeyEvent> permiteLetras() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();

                if (e.getCharacter().matches("[A-Za-z+ñÑ.+\\s+áéíóú+]")) {
                } else {
                    e.consume();
                }
            }
        };
    }

    public static EventHandler<KeyEvent> permiteNumeros() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();

                if (e.getCharacter().matches("[0-9]")) {
                } else {
                    e.consume();
                }
            }
        };
    }

    public static boolean formatoEmail(String email) {
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean emailFormato = email.matches(pattern);
        return emailFormato;
    }

    public static void cerrar(Event event) {
        Node node = (Node) event.getSource();
        Stage st = (Stage) node.getScene().getWindow();
        st.close();
    }
    

    public static SortedList<?> creaFiltro(FilteredList<?> filteredData, TableView tablaView) {
        SortedList<?> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablaView.comparatorProperty());
        return sortedData;
    }

    public static void headerValuesColumnTableView(String[] nombresHeader, String[] valuesHeader, TableView<?> tablaView, boolean indice) {
        int inicio = indice ? 1 : 0;

        for (int i = 0; i <= nombresHeader.length - 1; i++) {
            tablaView.getColumns().get(i).setText(nombresHeader[i]);

        }
        for (int j = 0; j <= valuesHeader.length - 1; j++) {
            tablaView.getColumns().get(j + inicio).setCellValueFactory(new PropertyValueFactory<>(valuesHeader[j]));

        }

    }
/*
    public static Action dialogConfirm(String header, String mensje) {
        Action response = Dialogs.create()
                .title(DataFXLauncher.getProperty("Sistema.Nombre"))
                .masthead(header)
                .message(mensje)
                .actions(Dialog.ACTION_YES, Dialog.ACTION_CANCEL)
                .showConfirm();

        return response;
    }

    public static void dialogOk(String header, String mensje) {
        Dialogs.create()
                .title(DataFXLauncher.getProperty("Sistema.Nombre"))
                .masthead(header)
                .message(mensje)
                .showInformation();

    }

    public static void creaPaginaTableView(int index, int limit, SortedList<?> lista, TableView tabla, boolean fade) {
        int newIndex = index * limit;
        tabla.getItems().clear();
        tabla.setItems(null);
        tabla.setItems(FXCollections.observableArrayList(lista.subList(Math.min(newIndex, lista.size()), Math.min(lista.size(), newIndex + limit))));
        if (fade) {
            new FadeInRightTransition(tabla).play();
        }
    }
    public static void creaPaginaTableView(int index, int limit, List<?> lista, TableView tabla, boolean fade) {
        int newIndex = index * limit;
        //tabla.getItems().clear();
        tabla.setItems(null);
        tabla.getSelectionModel().clearSelection();
        tabla.setItems(FXCollections.observableArrayList(lista.subList(Math.min(newIndex, lista.size()), Math.min(lista.size(), newIndex + limit))));
        if (fade) {
            new FadeInRightTransition(tabla).play();
        }
    }
*/
    public static void inicializaPaginador(Pagination paginador, SortedList<?> lista, int renglonesMostrar) {

        int tamLista = lista.size();
        int totalPaginas = tamLista / renglonesMostrar;
        int modulo = tamLista % renglonesMostrar;
        if (modulo > 0) {
            totalPaginas = totalPaginas + 1;
        }
        paginador.setPageCount(totalPaginas);
        paginador.setCurrentPageIndex(0);
        paginador.setMaxPageIndicatorCount(10);
    }    
    public static void inicializaPaginador(Pagination paginador, List<?> lista, int renglonesMostrar) {

        int tamLista = lista.size();
        int totalPaginas = tamLista / renglonesMostrar;
        int modulo = tamLista % renglonesMostrar;
        if (modulo > 0) {
            totalPaginas = totalPaginas + 1;
        }
        paginador.setPageCount(totalPaginas);
        paginador.setCurrentPageIndex(0);
        paginador.setMaxPageIndicatorCount(10);
    }
/*
    public static void consoleMsg(String cMensaje, Exception e, Object theClass) {
        mx.vw.swf.fwk.Utilerias.consoleMsg(cMensaje, e, theClass);
    }
*/
    public static void addTextLimiter(final TextField tx, final int maxLength) {
        tx.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tx.getText().length() > maxLength) {
                    String s = tx.getText().substring(0, maxLength);
                    tx.setText(s);
                }
            }
        });
    }

    public static boolean isAInt(String value) {
        try {
            int valor = Integer.parseInt(value);
            return true;
        } catch (Exception exp) {
            return false;
        }
    }
    
    public static ObservableList<String> listaDeArchivos (String path){
        //Obtentemos la lista de archivos
        //path = "/Users/juanfernandoroblesguzman/Documents";
        File f = new File(path);
        
        if(f.exists() && f.isDirectory()){
            File[] list = f.listFiles((File file) -> {
                String[] okFileExtensions = new String[]{".doc", ".docx", ".rtf"};
                for (String extension : okFileExtensions) {
                    if (file.getName().toLowerCase().endsWith(extension)) {
                        return true;
                    }
                }
                return false;
            });
            ObservableList<String> ListaDeArchivos = FXCollections.observableArrayList();
            for (File archivo : list){
                ListaDeArchivos.add(archivo.getName().replaceAll(".docx", "").replaceAll(".doc", "").replace(".rtf", ""));             
            }
            return ListaDeArchivos;
        }
        return null;
    } 
}
