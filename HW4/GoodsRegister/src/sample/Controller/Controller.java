package sample.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Model.productModel;
import sample.Model.stockModel;

import java.util.Optional;

public class Controller {


    public static Stage STAGE;
    public Button exitBtn;
    public AnchorPane paneButtons;
    public AnchorPane paneContent;
    public AnchorPane rootPane;

    public void stocks() {
        paneContent.getChildren().clear();
        final ObservableList<stockModel> stockObservableList = FXCollections.observableArrayList();
        stockObservableList.addAll(
                new stockModel(1, "Первый"),
                new stockModel(2, "Второй")
        );
        final ListView<stockModel> stockListView = new ListView<>();
        stockListView.setItems(stockObservableList);
        stockListView.setCellFactory(sListView -> new stockListViewCell());
        stockListView.setPrefHeight(250.0);
        paneContent.getChildren().addAll(stockListView);
    }

    public void products() {
        paneContent.getChildren().clear();
        final ObservableList<productModel> productObservableList = FXCollections.observableArrayList();
        final Button removeButton = new Button("Удалить выделенное");
        final Button addButton = new Button("Добавить продукт");
        final ListView<productModel> productListView = new ListView<>();
        final Text text = new Text("Выберите название склада.");
        final ChoiceBox<String> choiseSklad = new ChoiceBox<>();
        final HBox controlsButtons = new HBox(10);

        TextInputDialog addDialog = new TextInputDialog("walter");
        addDialog.setTitle("Text Input Dialog");
        addDialog.setHeaderText("Look, a Text Input Dialog");
        addDialog.setContentText("Please enter your name:");


        // Text
        AnchorPane.setTopAnchor(text, 10.0);
        AnchorPane.setLeftAnchor(text, 10.0);
        AnchorPane.setRightAnchor(text, 10.0);
        // ChoiseBox
        choiseSklad.setItems(FXCollections.observableArrayList(
        "Один", "Два", "Три"));
        //choiseSklad.getItems().addAll("item1", "item2", "item3");
        AnchorPane.setTopAnchor(choiseSklad, 40.0);
        AnchorPane.setLeftAnchor(choiseSklad, 10.0);
        AnchorPane.setRightAnchor(choiseSklad, 10.0);
        choiseSklad.getSelectionModel().selectedItemProperty()
                .addListener((ov, value, new_value) -> {
                    productObservableList.clear();
                    productObservableList.addAll(
                            new productModel(1, new_value, 1),
                            new productModel(1, "deff1", 1),
                            new productModel(1, "defff", 1)
                    );
                    addButton.setDisable(false);
                });
        productListView.setItems(productObservableList);
        productListView.setCellFactory(pListView -> new productListViewCell());
        productListView.setPrefSize(250.0,250.0);
        AnchorPane.setTopAnchor(productListView, 70.0);
        AnchorPane.setLeftAnchor(productListView, 10.0);
        AnchorPane.setRightAnchor(productListView, 10.0);
        productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> removeButton.setDisable(false));
        // Control button in Hbox
        removeButton.setDisable(true);
        addButton.setDisable(true);
        removeButton.setOnAction(event -> {
            final int selectedIdx = productListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                //int itemToRemove = productListView.getSelectionModel().getSelectedItem().getProductId();

               /* final int newSelectedIdx =
                        (selectedIdx == productListView.getItems().size() - 1)
                                ? selectedIdx - 1
                                : selectedIdx;
                */
                productListView.getItems().remove(selectedIdx);
                removeButton.setDisable(true);
                //productListView.getSelectionModel().select(newSelectedIdx);
            }
        });
        addButton.setOnAction(event -> {
            Optional<String> result = addDialog.showAndWait();
            result.ifPresent(name -> System.out.println("Your name: " + name));
        });
        controlsButtons.setAlignment(Pos.CENTER);
        controlsButtons.getChildren().addAll(removeButton, addButton);
        AnchorPane.setTopAnchor(controlsButtons, 330.0);
        AnchorPane.setLeftAnchor(controlsButtons, 10.0);
        AnchorPane.setRightAnchor(controlsButtons, 10.0);
        paneContent.getChildren().addAll(text, choiseSklad,productListView, controlsButtons);

    }

    public void finder() {
        STAGE.close();
    }

    public void findProduct() {
        STAGE.close();
    }

    public void closeApp() {
        STAGE.close();
    }
}
