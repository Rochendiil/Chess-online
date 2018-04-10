/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schaakappxml.Spel;

import Interfaces.ISessieManager;
import Interfaces.ISpelCommunicator;
import Server.SpelCommunicator;
import Spel.Spel;
import Spel.Vak;
import Spel.ZetEvent;
import Stuk.SchaakStuk;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import schaakappxml.SchaakAppGUI;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Luuk
 */
public class SpelFXMLController implements Initializable {

    @FXML
    private GridPane bord_grid;
    @FXML
    private GridPane geslagentegenstander_grid;
    @FXML
    private GridPane geslagenjou_grid;
    @FXML
    private Label zet_lbl;
    @FXML
    private Label end_lbl;
    private final int size = 8;
    private Spel spel;
    private boolean color;
    private Vak selected;
    private ISpelCommunicator spelcommunicator;
    private ArrayList<SchaakStuk> geslagenstukken;
    private boolean aanzet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        spel = SchaakAppGUI.getSchaakapp().getSpel();
        if (spel.getSpeler1().getNaam().equals(SchaakAppGUI.getSchaakapp().getLoggedin().getNaam())) {
            color = true;
            aanzet = true;

        } else {
            color = false;
            aanzet = false;

        }
        geslagenstukken = new ArrayList<>();

        try {
            spelcommunicator = new SpelCommunicator(this);
            System.out.println("Connection registry succesfull");
            if (color) {
                spelcommunicator.subscribe(spel.getId() + ", zwart");
               spelcommunicator.subscribe(spel.getId() + ", opgevenzwart");
            } else {
                spelcommunicator.subscribe(spel.getId() + ", wit");
                spelcommunicator.subscribe(spel.getId() + ", opgevenwit");
            }
            

        } catch (RemoteException ex) {
            Logger.getLogger(SpelFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        initSpel(color, null);
    }

    public void initSpel(boolean kleur, ArrayList<Vak> beschikbaarezetten) {

        if (aanzet) {
            zet_lbl.setText("u bent aan zet");
        } else {
            zet_lbl.setText("tegenstander is aan zet");
        }
        bord_grid.getColumnConstraints().clear();
        bord_grid.getRowConstraints().clear();
        bord_grid.setGridLinesVisible(true);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                StackPane square = new StackPane();
                String color;
                Vak vak;

                if (!kleur) {
                    if ((row + col) % 2 == 0) {
                        color = "DimGrey";
                    } else {
                        color = "white";
                    }
                    square.setStyle("-fx-background-color: " + color + ";");
                    vak = spel.getBord().getVakken()[(size - 1) - col][row];
                } else {
                    if ((row + col) % 2 == 0) {
                        color = "white";
                    } else {
                        color = "DimGrey";
                    }
                    square.setStyle("-fx-background-color: " + color + ";");
                    vak = spel.getBord().getVakken()[col][(size - 1) - row];

                }

                if (beschikbaarezetten != null && beschikbaarezetten.contains(vak)) {
                    square.setStyle("-fx-background-color: green; \n" + "-fx-border-color: black;");
                    square.setOnMouseClicked(e -> zet(vak));
                } else if (selected == vak) {
                    square.setStyle("-fx-background-color: blue;");
                } else {
                    square.setOnMouseClicked(e -> click(vak));
                }

                if (vak.getStuk() != null) {
                    ImageView view = new ImageView(vak.getStuk().getImage());
                    view.setFitHeight(50);
                    view.setFitWidth(50);
                    square.getChildren().addAll(view);

                }

                bord_grid.add(square, col, row);

            }
        }

        for (int i = 0; i < size; i++) {
            bord_grid.getColumnConstraints().add(new ColumnConstraints(70));
            bord_grid.getRowConstraints().add(new RowConstraints(70));
        }
        int zwartcounter = 0;
        int witcounter = 0;
        for (int i = 0; i < geslagenstukken.size(); i++) {

            if (geslagenstukken.get(i).getKleur() == color) {
                geslagentegenstander_grid.getColumnConstraints().clear();
                geslagentegenstander_grid.getRowConstraints().clear();

                StackPane square = new StackPane();
                ImageView view = new ImageView(geslagenstukken.get(i).getImage());
                view.setFitHeight(50);
                view.setFitWidth(50);
                square.getChildren().addAll(view);
                geslagentegenstander_grid.add(square, witcounter, 0);
                witcounter++;
                geslagentegenstander_grid.getColumnConstraints().add(new ColumnConstraints(70));
                geslagentegenstander_grid.getRowConstraints().add(new RowConstraints(70));
            } else {
                geslagenjou_grid.getColumnConstraints().clear();
                geslagenjou_grid.getRowConstraints().clear();

                StackPane square = new StackPane();
                ImageView view = new ImageView(geslagenstukken.get(i).getImage());
                view.setFitHeight(50);
                view.setFitWidth(50);
                square.getChildren().addAll(view);
                geslagenjou_grid.add(square, zwartcounter, 0);
                zwartcounter++;
                geslagenjou_grid.getColumnConstraints().add(new ColumnConstraints(70));
                geslagenjou_grid.getRowConstraints().add(new RowConstraints(70));
            }

        }

    }

    private void click(Vak vak) {
        if (aanzet) {
            selected = null;
            initSpel(color, null);
            if (vak.getStuk() != null && vak.getStuk().getKleur() == color) {
                selected = vak;
                initSpel(color, vak.getStuk().getBeschikbareZetten());
            }
        }

    }

    private void zet(Vak vak) {
        if (vak.getStuk() != null) {
            geslagenstukken.add(vak.getStuk());
        }
        vak.setStuk(selected.getStuk());
        selected.removeStuk();
        selected = null;

        ZetEvent event = new ZetEvent(vak.getStuk().getId() + "," + vak.getNaam());
        if (color) {
            try {
                spelcommunicator.ZetDoen(spel.getId(), "wit", event);
            } catch (RemoteException ex) {
                Logger.getLogger(SpelFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                spelcommunicator.ZetDoen(spel.getId(), "zwart", event);
            } catch (RemoteException ex) {
                Logger.getLogger(SpelFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        aanzet = false;
        initSpel(color, null);
        

    }

   
    public void opgeven() throws RemoteException, NotBoundException{
        ZetEvent event = new ZetEvent("opgeven");

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Weet je zeker dat je wilt opgeven?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (color) {
                spelcommunicator.Opgeven(spel.getId(), "wit", event);
            } else {
                spelcommunicator.Opgeven(spel.getId(), "zwart", event);
            }
            try {
                Registry registry = LocateRegistry.getRegistry("localhost",1099);
                ISessieManager sessiemanager = (ISessieManager) registry.lookup("sessiemanager");
                sessiemanager.StopSpel(spel.getId());
                SchaakAppGUI.showQueue();
            } catch (IOException ex) {
                Logger.getLogger(SpelFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    public void ontvangZet(ZetEvent zet) {
        aanzet = true;
        System.out.println("ontvangen zet: " + zet.getZetString());
        String[] split = zet.getZetString().split(",");
        SchaakStuk stuk = null;
        Vak oudvak = null;
        Vak nieuwvak = null;
        int id = Integer.parseInt(split[0]);
        for (int i = 0; i < spel.getStukken().size(); i++) {
            if (spel.getStukken().get(i).getId() == id) {
                stuk = spel.getStukken().get(i);
            }
        }
        Vak vak;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                vak = spel.getBord().getVakken()[i][j];
                if (vak.getNaam().equals(split[1])) {
                    nieuwvak = vak;
                } else if (vak.getStuk() != null && vak.getStuk().getId() == stuk.getId()) {
                    oudvak = vak;
                }
            }
        }
        if (nieuwvak.getStuk() != null) {
            geslagenstukken.add(nieuwvak.getStuk());
        }

        oudvak.removeStuk();
        nieuwvak.setStuk(stuk);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initSpel(color, null);
            }
        });
        

    }

    public void tegenstanderGeeftOp() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Tegenstander heeft opgegeven,je wint!");
                alert.showAndWait();
                try {
                    SchaakAppGUI.showQueue();
                } catch (IOException ex) {
                    Logger.getLogger(SpelFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        

        System.out.println("tegenstander geeft op");
    }

}
