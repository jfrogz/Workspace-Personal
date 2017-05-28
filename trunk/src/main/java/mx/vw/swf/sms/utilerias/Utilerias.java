/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.utilerias;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import mx.vw.swf.cdi.DataFXLauncher;
import mx.vw.swf.security.model.SegUsuario;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author fox1x0d
 */
public class Utilerias {

    private static Pagination pagination;

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

    public static ObservableList<String> comboPaginacion() {
        String paginas[] = DataFXLauncher.mensajesPropertiesSeg.getProperty("paginas").split(",");
        ObservableList<String> options = FXCollections.observableArrayList(paginas);
        return options;
    }

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

    public static Stage nuevaVentana(String nombreFxml, Object controlador, String titulo) throws IOException {
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

    public static void consoleMsg(String cMensaje, Exception e, Object theClass) {
        mx.vw.swf.fwk.Utilerias.consoleMsg(cMensaje, e, theClass);
    }

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

}
