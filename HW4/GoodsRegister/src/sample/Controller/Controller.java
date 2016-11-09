package sample.Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import sample.Model.Product;
import sample.Model.Stock;

import java.util.Optional;

public class Controller {


    public static Stage STAGE;
    public Button exitBtn;
    public AnchorPane paneButtons;
    public AnchorPane paneContent;
    public AnchorPane rootPane;

    public void stocks() {
        paneContent.getChildren().clear();
        final ObservableList<Stock> stockObservableList = FXCollections.observableArrayList();
        stockObservableList.addAll(
                new Stock(1, "Первый"),
                new Stock(2, "Второй")
        );
        final ListView<Stock> stockListView = new ListView<>();
        stockListView.setItems(stockObservableList);
        stockListView.setCellFactory(sListView -> new stockListViewCell());
        stockListView.setPrefHeight(250.0);
        paneContent.getChildren().addAll(stockListView);
    }

    public void products() {
        paneContent.getChildren().clear();
        final ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        final Button removeButton = new Button("Удалить выделенное");
        final Button addButton = new Button("Добавить продукт");
        final Button editButton = new Button("Изменить продукт");
        final ListView<Product> productListView = new ListView<>();
        final Text text = new Text("Выберите название склада.");
        final ChoiceBox<String> choiseSklad = new ChoiceBox<>();
        final HBox controlsButtons = new HBox(10);
        // Dialog add
        final Dialog<Pair<String, String>> addDialog = new Dialog<>();
        addDialog.initStyle(StageStyle.UTILITY);
        Stage stageAddDialog = (Stage) addDialog.getDialogPane().getScene().getWindow();
        stageAddDialog.setIconified(true);
        addDialog.setTitle("Добавление продукта");
        addDialog.setHeaderText("Введите название и модель продукта.");
        ButtonType addButtonType = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        addDialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Название");
        TextField model = new TextField();
        model.setPromptText("Модель");

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Модель:"), 0, 1);
        grid.add(model, 1, 1);
        Node addSubmitButton = addDialog.getDialogPane().lookupButton(addButtonType);
        addSubmitButton.setDisable(true);

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            addSubmitButton.setDisable(newValue.trim().isEmpty());
        });
        addDialog.getDialogPane().setContent(grid);
        Platform.runLater(name::requestFocus);
        addDialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Pair<>(name.getText(), model.getText());
            }
            return null;
        });
        //Dialog edit

        final Dialog<Pair<String, String>> editDialog = new Dialog<>();
        editDialog.initStyle(StageStyle.UTILITY);
        editDialog.setTitle("Изменение продукта");
        editDialog.setHeaderText("Введите название и модель продукта.");
        ButtonType editButtonType = new ButtonType("Изменить", ButtonBar.ButtonData.OK_DONE);
        editDialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);
        GridPane gridEdit = new GridPane();
        gridEdit.setHgap(10);
        gridEdit.setVgap(10);
        gridEdit.setPadding(new Insets(20, 150, 10, 10));

        TextField nameEdit = new TextField();
        nameEdit.setPromptText("Название");
        TextField modelEdit = new TextField();
        modelEdit.setPromptText("Модель");

        gridEdit.add(new Label("Название:"), 0, 0);
        gridEdit.add(nameEdit, 1, 0);
        gridEdit.add(new Label("Модель:"), 0, 1);
        gridEdit.add(modelEdit, 1, 1);
        Node editSubmitButton = editDialog.getDialogPane().lookupButton(editButtonType);
        editSubmitButton.setDisable(true);

        nameEdit.textProperty().addListener((observable, oldValue, newValue) -> {
            editSubmitButton.setDisable(newValue.trim().isEmpty());
        });
        editDialog.getDialogPane().setContent(gridEdit);

        editDialog.setResultConverter(dialogButton -> {
            if (dialogButton == editButtonType) {
                return new Pair<>(nameEdit.getText(), modelEdit.getText());
            }
            return null;
        });
        // Text
        AnchorPane.setTopAnchor(text, 10.0);
        AnchorPane.setLeftAnchor(text, 10.0);
        AnchorPane.setRightAnchor(text, 10.0);
        // ChoiseBox
        choiseSklad.setItems(FXCollections.observableArrayList(
        "Один", "Два", "Три"));
        AnchorPane.setTopAnchor(choiseSklad, 40.0);
        AnchorPane.setLeftAnchor(choiseSklad, 10.0);
        AnchorPane.setRightAnchor(choiseSklad, 10.0);
        choiseSklad.getSelectionModel().selectedItemProperty()
                .addListener((ov, value, new_value) -> {
                    productObservableList.clear();
                    productObservableList.addAll(
                            new Product(1, new_value, 1),
                            new Product(1, "deffffffffffffffffffffffffffffff1", 1),
                            new Product(1, "defff", 1)
                    );
                    addButton.setDisable(false);
                });
        //ListTitle
        final Label title1 = new Label("ID");
        final Label title2 = new Label("Название");
        final Label title3 = new Label("Цена");
        AnchorPane.setTopAnchor(title1, 70.0);
        AnchorPane.setLeftAnchor(title1, 15.0);

        AnchorPane.setTopAnchor(title2, 70.0);
        AnchorPane.setLeftAnchor(title2, 100.0);
        AnchorPane.setRightAnchor(title2, 100.0);
        title2.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(title3, 70.0);

        AnchorPane.setRightAnchor(title3, 25.0);
        // ListView
        productListView.setItems(productObservableList);
        productListView.setCellFactory(pListView -> new productListViewCell());
        productListView.setPrefSize(465.0,250.0);
        AnchorPane.setTopAnchor(productListView, 100.0);
        AnchorPane.setLeftAnchor(productListView, 10.0);
        AnchorPane.setRightAnchor(productListView, 10.0);
        productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {removeButton.setDisable(false);
            editButton.setDisable(false);});

        // Control button in Hbox
        removeButton.setDisable(true);
        addButton.setDisable(true);
        editButton.setDisable(true);
        removeButton.setOnAction(event -> {
            final int selectedIdx = productListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                //int itemToRemove = productListView.getSelectionModel().getSelectedItem().getProductId();

               /* final int newSelectedIdx =
                        (selectedIdx == productListView.getItems().size() - 1)
                                ? selectedIdx - 1
                                : selectedIdx;
                */
                Product p = productListView.getSelectionModel().getSelectedItem();
                System.out.println("DELETE: id=" +  p.getProductId() + ", name=" +  p.getProductName() + "" +
                        ", model=" +  p.getProductPrice());
                productListView.getItems().remove(selectedIdx);

                removeButton.setDisable(true);
                //productListView.getSelectionModel().select(newSelectedIdx);
            }
        });
        editButton.setOnAction(event -> {
            final int selectedIdx = productListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                Product p = productListView.getSelectionModel().getSelectedItem();
                nameEdit.setText(p.getProductName());
                modelEdit.setText(p.getProductPrice().toString());
                Optional<Pair<String, String>> resultEdit = editDialog.showAndWait();
                resultEdit.ifPresent(nameAndModel -> {
                    System.out.println("EDITED: Name=" + nameAndModel.getKey() + ", Model=" + nameAndModel.getValue());
                });
            }
        });
        addButton.setOnAction(event -> {
            Optional<Pair<String, String>> resultAdd = addDialog.showAndWait();
            resultAdd.ifPresent(nameAndModel -> {
                System.out.println("ADDED: Name=" + nameAndModel.getKey() + ", Model=" + nameAndModel.getValue());
            });
        });
        controlsButtons.setAlignment(Pos.CENTER);
        controlsButtons.getChildren().addAll(editButton, removeButton, addButton);
        AnchorPane.setTopAnchor(controlsButtons, 360.0);
        AnchorPane.setLeftAnchor(controlsButtons, 10.0);
        AnchorPane.setRightAnchor(controlsButtons, 10.0);
        //Add to Pane
        paneContent.getChildren().addAll(text, choiseSklad, title1, title2, title3, productListView, controlsButtons);

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
