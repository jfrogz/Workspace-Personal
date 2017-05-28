/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.vw.swf.sms.utilerias;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import static mx.vw.swf.cdi.DataFXLauncher.getSecurityProperty;
import mx.vw.swf.security.model.SegPerfil;
import mx.vw.swf.security.model.SegPermiso;
import mx.vw.swf.sms.model.SmsProducto;

/**
 *
 * @author fox1x0d
 */
public class CheckBoxTableCell<S, T> extends TableCell<S, T> {

    private final CheckBox checkBox;
    private final boolean seleccionar;

    public CheckBoxTableCell(boolean seleccionar) {

        this.checkBox = new CheckBox();
        this.checkBox.setAlignment(Pos.CENTER);
        this.seleccionar = seleccionar;
        setAlignment(Pos.CENTER);
        setGraphic(checkBox);

    }

    @Override
    public void updateItem(T item, boolean empty) {

        super.updateItem(item, empty);
        if (item == null) {
            setText(null);
            setGraphic(null);

        } else {

            setGraphic(checkBox);
            boolean d = false;
            if (item instanceof SegPerfil) {
                SegPerfil s = (SegPerfil) item;
                d = s.getSelected();

            }
            if (item instanceof SegPermiso) {
                SegPermiso s = (SegPermiso) item;
                d = s.getSelected();
            }
             if (item instanceof SmsProducto) {
                SmsProducto s = (SmsProducto) item;
                d = s.getSelected();
            }

            BooleanProperty b = new SimpleBooleanProperty(d);
            checkBox.selectedProperty().bindBidirectional(b);

            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                     
                    if (item instanceof SegPerfil) {
                        SegPerfil s = (SegPerfil) item;
                        if (checkBox.isSelected()) {
                            s.setSelected(Boolean.TRUE);
                        } else {
                            if(s.getPerfil().toLowerCase().contains("management".toLowerCase()) && (seleccionar)){
                                Utilerias.consoleMsg("ENTRA MENSAJE", null, this);
                                checkBox.setSelected(true);
                                Utilerias.dialogOk(getSecurityProperty("AdmininstraPerfiles.Valid.UserMng.Header"), getSecurityProperty("AdmininstraPerfiles.Valid.UserMng.Message"));
                             
                            }else{
                            s.setSelected(Boolean.FALSE);
                            }
                        }
                    } else if (item instanceof SegPermiso) {
                        SegPermiso s = (SegPermiso) item;
                        if (checkBox.isSelected()) {
                            s.setSelected(Boolean.TRUE);
                        } else {
                            s.setSelected(Boolean.FALSE);
                        }

                    } else if(item instanceof SmsProducto){
                        SmsProducto s = (SmsProducto) item;
                        if (checkBox.isSelected()) {
                            s.setSelected(Boolean.TRUE);
                        } else {
                            s.setSelected(Boolean.FALSE);
                        }
                    }

                }
            });

        }

    }

}