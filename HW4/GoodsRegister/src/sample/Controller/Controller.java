package sample.Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import sample.Model.Connect;
import sample.Model.Product;
import sample.Model.Request;
import sample.Model.Stock;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Controller {


    public static Stage STAGE;
    public Button exitBtn;
    public AnchorPane paneButtons;
    public AnchorPane paneContent;
    public AnchorPane rootPane;

    private static Connect connect;
    private static Request request;

    public void stocks() {
        paneContent.getChildren().clear();
        connect = new Connect();
        request = new Request(connect.ConnectBD());
        final ObservableList<Stock> stockObservableList = FXCollections.observableArrayList();
        final Button removeButton = new Button("Удалить склад");
        final Button addButton = new Button("Добавить склад");
        final Button editButton = new Button("Изменить склад");
        stockObservableList.addAll(request.getStocks());
        final ListView<Stock> stockListView = new ListView<>();
        final HBox controlsButtons = new HBox(10);
        final Text infoText = new Text("");
        final Text text = new Text("Управдение складами.");
        // Text
        AnchorPane.setTopAnchor(text, 10.0);
        AnchorPane.setLeftAnchor(text, 10.0);
        AnchorPane.setRightAnchor(text, 10.0);

        AnchorPane.setBottomAnchor(infoText, 10.0);
        AnchorPane.setLeftAnchor(infoText, 10.0);
        AnchorPane.setRightAnchor(infoText, 10.0);
        // Dialog add
        final Dialog<String> addDialog = new Dialog<>();
        connect = new Connect();
        request = new Request(connect.ConnectBD());
        addDialog.initStyle(StageStyle.UTILITY);
        addDialog.setTitle("Добавление склада");
        addDialog.setHeaderText("Введите название склада.");
        ButtonType addButtonType = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        addDialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Название");

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);

        Node addSubmitButton = addDialog.getDialogPane().lookupButton(addButtonType);
        addSubmitButton.setDisable(true);

        name.textProperty().addListener((observable, oldValue, newValue) -> addSubmitButton.setDisable(newValue.trim().isEmpty()));
        addDialog.getDialogPane().setContent(grid);
        Platform.runLater(name::requestFocus);
        addDialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return name.getText();
            }
            return null;
        });
        //Dialog edit

        final Dialog<Pair<Integer, String>> editDialog = new Dialog<>();
        editDialog.initStyle(StageStyle.UTILITY);
        editDialog.setTitle("Изменение склада");
        editDialog.setHeaderText("Введите название склада.");
        ButtonType editButtonType = new ButtonType("Изменить", ButtonBar.ButtonData.OK_DONE);
        editDialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);
        GridPane gridEdit = new GridPane();
        gridEdit.setHgap(10);
        gridEdit.setVgap(10);
        gridEdit.setPadding(new Insets(20, 150, 10, 10));

        TextField nameEdit = new TextField();
        nameEdit.setPromptText("Название");


        gridEdit.add(new Label("Название:"), 0, 0);
        gridEdit.add(nameEdit, 1, 0);

        Node editSubmitButton = editDialog.getDialogPane().lookupButton(editButtonType);
        editSubmitButton.setDisable(true);

        nameEdit.textProperty().addListener((observable, oldValue, newValue) -> editSubmitButton.setDisable(newValue.trim().isEmpty()));
        editDialog.getDialogPane().setContent(gridEdit);

        editDialog.setResultConverter(dialogButton -> {
            if (dialogButton == editButtonType) {
                return new Pair<>(stockListView.getSelectionModel().getSelectedItem().getStockId(), nameEdit.getText());
            }
            return null;
        });

        //Confirm dialog
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Удаление склада");
        alert.setHeaderText("Подтвердите удаление");
        alert.setContentText("Внимание! При удалении склада, будут удалены все товары находящиеся на нём!");
        //ListTitle
        final Label title1 = new Label("ID");
        final Label title2 = new Label("Название");

        AnchorPane.setTopAnchor(title1, 70.0);
        AnchorPane.setLeftAnchor(title1, 15.0);

        AnchorPane.setTopAnchor(title2, 70.0);
        AnchorPane.setLeftAnchor(title2, 100.0);
        AnchorPane.setRightAnchor(title2, 100.0);
        title2.setAlignment(Pos.CENTER);
        // ListView
        stockListView.setItems(stockObservableList);
        stockListView.setCellFactory(sListView -> new stockListViewCell());
        stockListView.setPrefSize(465.0, 250.0);
        AnchorPane.setTopAnchor(stockListView, 100.0);
        AnchorPane.setLeftAnchor(stockListView, 10.0);
        AnchorPane.setRightAnchor(stockListView, 10.0);
        stockListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeButton.setDisable(false);
            editButton.setDisable(false);
        });
        removeButton.setDisable(true);
        editButton.setDisable(true);
        removeButton.setOnAction(event -> {
            final int selectedIdx = stockListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent()) {
                    if (result.get() == ButtonType.OK) {
                        Stock p = stockListView.getSelectionModel().getSelectedItem();
                        request.delStock(p.getStockId());
                        System.out.println("DELETE: id=" + p.getStockId() + ", name=" + p.getStockName());
                        stockListView.getItems().remove(selectedIdx);
                        stockListView.getSelectionModel().select(-1);
                        if (stockListView.getItems().size() > 1) {
                            removeButton.setDisable(true);
                            editButton.setDisable(true);
                        }
                    }
                }
            }
        });
        editButton.setOnAction(event -> {
            final int selectedIdx = stockListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                Stock p = stockListView.getSelectionModel().getSelectedItem();
                nameEdit.setText(p.getStockName());
                Optional<Pair<Integer, String>> resultEdit = editDialog.showAndWait();
                resultEdit.ifPresent(idAndPrice -> {
                    if (!idAndPrice.getValue().equals("")) {
                        request.updateStock(new Stock(idAndPrice.getKey(), idAndPrice.getValue()));
                        stockObservableList.clear();
                        stockObservableList.addAll(request.getStocks());
                        infoText.setText("");
                        System.out.println("EDITED: id=" + idAndPrice.getKey() + ", name=" + idAndPrice.getValue());
                    } else {
                        infoText.setText("Редактирование не выполнено, так как не заполнены требуемые поля!");
                    }
                });
            }
        });
        addButton.setOnAction(event -> {
            Optional<String> resultAdd = addDialog.showAndWait();
            resultAdd.ifPresent(nameStock -> {
                if (!nameStock.equals("")) {
                    request.addStock(nameStock);
                    stockObservableList.clear();
                    stockObservableList.addAll(request.getStocks());
                    infoText.setText("");
                    System.out.println("ADDED: name=" + nameStock);
                } else {
                    infoText.setText("Добавление не выполнено, так как не заполнены требуемые поля!");
                }
            });
        });
        controlsButtons.setAlignment(Pos.CENTER);
        controlsButtons.getChildren().addAll(editButton, removeButton, addButton);
        AnchorPane.setTopAnchor(controlsButtons, 360.0);
        AnchorPane.setLeftAnchor(controlsButtons, 10.0);
        AnchorPane.setRightAnchor(controlsButtons, 10.0);
        paneContent.getChildren().addAll(text, title1, title2, stockListView, controlsButtons, infoText);
    }

    public void products() {
        paneContent.getChildren().clear();
        final ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        final ObservableList<Stock> choiseStockObservableList = FXCollections.observableArrayList();
        final Button removeButton = new Button("Удалить выделенное");
        final Button addButton = new Button("Добавить продукт");
        final Button editButton = new Button("Изменить продукт");
        final ListView<Product> productListView = new ListView<>();
        final Text text = new Text("Выберите название склада.");
        final Text infoText = new Text("");
        final ChoiceBox<String> choiseSklad = new ChoiceBox<>();
        final HBox controlsButtons = new HBox(10);
        // Dialog add
        final Dialog<Pair<String, String>> addDialog = new Dialog<>();
        connect = new Connect();
        request = new Request(connect.ConnectBD());
        addDialog.initStyle(StageStyle.UTILITY);
        addDialog.setTitle("Добавление продукта");
        addDialog.setHeaderText("Введите название и цену продукта.");
        ButtonType addButtonType = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        addDialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Название");
        TextField cost = new TextField();
        cost.setPromptText("Цена");

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Цена:"), 0, 1);
        grid.add(cost, 1, 1);
        Node addSubmitButton = addDialog.getDialogPane().lookupButton(addButtonType);
        addSubmitButton.setDisable(true);

        name.textProperty().addListener((observable, oldValue, newValue) -> addSubmitButton.setDisable(newValue.trim().isEmpty()));
        Platform.runLater(name::requestFocus);
        addDialog.getDialogPane().setContent(grid);

        addDialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Pair<>(name.getText(), cost.getText());
            }
            return null;
        });
        //Dialog edit

        final Dialog<Pair<String, String>> editDialog = new Dialog<>();
        editDialog.initStyle(StageStyle.UTILITY);
        editDialog.setTitle("Изменение продукта");
        editDialog.setHeaderText("Введите название и цену продукта.");
        ButtonType editButtonType = new ButtonType("Изменить", ButtonBar.ButtonData.OK_DONE);
        editDialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);
        GridPane gridEdit = new GridPane();
        gridEdit.setHgap(10);
        gridEdit.setVgap(10);
        gridEdit.setPadding(new Insets(20, 150, 10, 10));

        TextField nameEdit = new TextField();
        nameEdit.setPromptText("Название");
        TextField costEdit = new TextField();
        costEdit.setPromptText("Цена");

        gridEdit.add(new Label("Название:"), 0, 0);
        gridEdit.add(nameEdit, 1, 0);
        gridEdit.add(new Label("Цена:"), 0, 1);
        gridEdit.add(costEdit, 1, 1);
        Node editSubmitButton = editDialog.getDialogPane().lookupButton(editButtonType);
        editSubmitButton.setDisable(true);

        nameEdit.textProperty().addListener((observable, oldValue, newValue) -> editSubmitButton.setDisable(newValue.trim().isEmpty()));
        editDialog.getDialogPane().setContent(gridEdit);

        editDialog.setResultConverter(dialogButton -> {
            if (dialogButton == editButtonType) {
                return new Pair<>(nameEdit.getText(), costEdit.getText());
            }
            return null;
        });
        // Text
        AnchorPane.setTopAnchor(text, 10.0);
        AnchorPane.setLeftAnchor(text, 10.0);
        AnchorPane.setRightAnchor(text, 10.0);

        AnchorPane.setBottomAnchor(infoText, 10.0);
        AnchorPane.setLeftAnchor(infoText, 10.0);
        AnchorPane.setRightAnchor(infoText, 10.0);
        // ChoiseBox
        List<Stock> ls = request.getStocks();
        choiseStockObservableList.addAll(request.getStocks());

        choiseSklad.setItems(choiseStockObservableList.stream().map(Stock::getStockName).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        AnchorPane.setTopAnchor(choiseSklad, 40.0);
        AnchorPane.setLeftAnchor(choiseSklad, 10.0);
        AnchorPane.setRightAnchor(choiseSklad, 10.0);
        choiseSklad.getSelectionModel().selectedItemProperty()
                .addListener((ov, value, new_value) -> {
                    productObservableList.clear();
                    productObservableList.addAll(request.getProductsByStock(new_value));
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
        productListView.setPrefSize(465.0, 250.0);
        AnchorPane.setTopAnchor(productListView, 100.0);
        AnchorPane.setLeftAnchor(productListView, 10.0);
        AnchorPane.setRightAnchor(productListView, 10.0);
        productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeButton.setDisable(false);
            editButton.setDisable(false);
        });

        // Control button in Hbox
        removeButton.setDisable(true);
        addButton.setDisable(true);
        editButton.setDisable(true);
        removeButton.setOnAction(event -> {
            final int selectedIdx = productListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                Product p = productListView.getSelectionModel().getSelectedItem();
                request.delProduct(p.getProductId());
                System.out.println("DELETE: id=" + p.getProductId() + ", name=" + p.getProductName() + "" +
                        ", cost=" + p.getProductCost());
                productListView.getItems().remove(selectedIdx);
                productListView.getSelectionModel().select(-1);
                if (productListView.getItems().size() > 1) {
                    removeButton.setDisable(true);
                    editButton.setDisable(true);
                }
            }
        });
        editButton.setOnAction(event -> {
            final int selectedIdx = productListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                Product p = productListView.getSelectionModel().getSelectedItem();
                nameEdit.setText(p.getProductName());
                costEdit.setText(p.getProductCost().toString());
                Optional<Pair<String, String>> resultEdit = editDialog.showAndWait();
                resultEdit.ifPresent(nameAndPrice -> {
                    if (!nameAndPrice.getKey().equals("") && !nameAndPrice.getValue().equals("")) {
                        request.updateProduct(new Product(productListView.getSelectionModel().getSelectedItem().getProductId(), nameAndPrice.getKey(), choiseSklad.getSelectionModel().getSelectedIndex(), Double.parseDouble(nameAndPrice.getValue())));
                        productObservableList.clear();
                        productObservableList.addAll(request.getProductsByStock(choiseSklad.getSelectionModel().getSelectedItem()));
                        infoText.setText("");
                        System.out.println("EDITED: Name=" + nameAndPrice.getKey() + ", Cost=" + nameAndPrice.getValue());
                    } else {
                        infoText.setText("Редактирование не выполнено, так как не заполнены требуемые поля!");
                    }
                });
            }
        });
        addButton.setOnAction(event -> {
            Optional<Pair<String, String>> resultAdd = addDialog.showAndWait();
            resultAdd.ifPresent(nameAndCost -> {
                if (!nameAndCost.getKey().equals("") && !nameAndCost.getValue().equals("")) {
                    request.addProduct(new Product(nameAndCost.getKey(), choiseSklad.getSelectionModel().getSelectedIndex(), Double.parseDouble(nameAndCost.getValue())));
                    productObservableList.clear();
                    productObservableList.addAll(request.getProductsByStock(choiseSklad.getSelectionModel().getSelectedItem()));
                    infoText.setText("");
                    System.out.println("ADDED: Name=" + nameAndCost.getKey() + ", cost=" + nameAndCost.getValue());
                } else {
                    infoText.setText("Добавление не выполнено, так как не заполнены требуемые поля!");
                }
            });
        });
        controlsButtons.setAlignment(Pos.CENTER);
        controlsButtons.getChildren().addAll(editButton, removeButton, addButton);
        AnchorPane.setTopAnchor(controlsButtons, 360.0);
        AnchorPane.setLeftAnchor(controlsButtons, 10.0);
        AnchorPane.setRightAnchor(controlsButtons, 10.0);
        //Add to Pane
        paneContent.getChildren().addAll(text, choiseSklad, title1, title2, title3, productListView, controlsButtons, infoText);

    }

    public void finder() {
        paneContent.getChildren().clear();
        connect = new Connect();
        request = new Request(connect.ConnectBD());
        final ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        final Button searchButton = new Button("Найти");
        final ListView<Product> findProductListView = new ListView<>();
        final Text text = new Text("Введите название искомого продукта");
        final Text infoText = new Text("");
        final TextField searchField = new TextField();
        final Button removeButton = new Button("Удалить выделенный продукт");
        final Button editButton = new Button("Изменить продукт");
        final HBox searchBox = new HBox(10);
        final HBox controlBox = new HBox(10);
        //Dialog edit
        final Dialog<Pair<String, String>> editDialog = new Dialog<>();
        editDialog.initStyle(StageStyle.UTILITY);
        editDialog.setTitle("Изменение продукта");
        editDialog.setHeaderText("Введите название и цену продукта.");
        ButtonType editButtonType = new ButtonType("Изменить", ButtonBar.ButtonData.OK_DONE);
        editDialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);
        GridPane gridEdit = new GridPane();
        gridEdit.setHgap(10);
        gridEdit.setVgap(10);
        gridEdit.setPadding(new Insets(20, 150, 10, 10));

        TextField nameEdit = new TextField();
        nameEdit.setPromptText("Название");
        TextField costEdit = new TextField();
        costEdit.setPromptText("Цена");

        gridEdit.add(new Label("Название:"), 0, 0);
        gridEdit.add(nameEdit, 1, 0);
        gridEdit.add(new Label("Цена:"), 0, 1);
        gridEdit.add(costEdit, 1, 1);
        Node editSubmitButton = editDialog.getDialogPane().lookupButton(editButtonType);
        editSubmitButton.setDisable(true);

        nameEdit.textProperty().addListener((observable, oldValue, newValue) -> editSubmitButton.setDisable(newValue.trim().isEmpty()));
        editDialog.getDialogPane().setContent(gridEdit);

        editDialog.setResultConverter(dialogButton -> {
            if (dialogButton == editButtonType) {
                return new Pair<>(nameEdit.getText(), costEdit.getText());
            }
            return null;
        });


        // Control button in Hbox
        removeButton.setDisable(true);
        editButton.setDisable(true);
        removeButton.setOnAction(event -> {
            final int selectedIdx = findProductListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                Product p = findProductListView.getSelectionModel().getSelectedItem();
                request.delProduct(p.getProductId());
                System.out.println("DELETE: id=" + p.getProductId() + ", name=" + p.getProductName() + "" +
                        ", cost=" + p.getProductCost());
                findProductListView.getItems().remove(selectedIdx);
                findProductListView.getSelectionModel().select(-1);
                if (findProductListView.getItems().size() > 1) {
                    removeButton.setDisable(true);
                    editButton.setDisable(true);
                }
            }
        });
        editButton.setOnAction(event -> {
            final int selectedIdx = findProductListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                Product p = findProductListView.getSelectionModel().getSelectedItem();
                nameEdit.setText(p.getProductName());
                costEdit.setText(p.getProductCost().toString());
                Optional<Pair<String, String>> resultEdit = editDialog.showAndWait();

                resultEdit.ifPresent(nameAndPrice -> {
                    if (!nameAndPrice.getKey().equals("") && !nameAndPrice.getValue().equals("")) {
                        request.updateProduct(new Product(findProductListView.getSelectionModel().getSelectedItem().getProductId(), nameAndPrice.getKey(), Double.parseDouble(nameAndPrice.getValue()), findProductListView.getSelectionModel().getSelectedItem().getProductStockName()));
                        productObservableList.clear();
                        productObservableList.addAll(request.findProducts(searchField.getText()));
                        infoText.setText("");
                        System.out.println("EDITED: Name=" + nameAndPrice.getKey() + ", Cost=" + nameAndPrice.getValue());
                    } else {
                        infoText.setText("Редактирование не выполнено, так как не заполнены требуемые поля!");
                    }
                });
            }
        });
        //Search
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchButton.setDisable(newValue.trim().isEmpty()));
        Platform.runLater(searchField::requestFocus);
        searchButton.setOnAction(event -> {
            String searchRequest = searchField.getText();
            if (!searchRequest.equals("")) {
                productObservableList.clear();
                productObservableList.addAll(request.findProducts(searchRequest));
            } else {
                infoText.setText("Не введено значение для поиска");
            }

        });
        searchBox.setAlignment(Pos.CENTER);
        searchBox.getChildren().addAll(searchField, searchButton);
        AnchorPane.setTopAnchor(searchBox, 40.0);
        AnchorPane.setLeftAnchor(searchBox, 10.0);
        AnchorPane.setRightAnchor(searchBox, 10.0);

        AnchorPane.setTopAnchor(text, 10.0);
        AnchorPane.setLeftAnchor(text, 10.0);
        AnchorPane.setRightAnchor(text, 10.0);
        AnchorPane.setBottomAnchor(infoText, 10.0);
        AnchorPane.setLeftAnchor(infoText, 10.0);
        AnchorPane.setRightAnchor(infoText, 10.0);

        //ListTitle
        final Label title1 = new Label("ID");
        final Label title2 = new Label("Название");
        final Label title3 = new Label("Цена");
        final Label title4 = new Label("Склад");
        AnchorPane.setTopAnchor(title1, 70.0);
        AnchorPane.setLeftAnchor(title1, 15.0);
        AnchorPane.setTopAnchor(title2, 70.0);
        AnchorPane.setLeftAnchor(title2, 150.0);
        title2.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(title3, 70.0);
        AnchorPane.setLeftAnchor(title3, 300.0);
        AnchorPane.setTopAnchor(title4, 70.0);
        AnchorPane.setLeftAnchor(title4, 350.0);
        title4.setAlignment(Pos.CENTER);
        // ListView
        findProductListView.setItems(productObservableList);
        findProductListView.setCellFactory(pListView -> new finderListViewCell());
        findProductListView.setPrefSize(465.0, 250.0);
        findProductListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeButton.setDisable(false);
            editButton.setDisable(false);
        });
        AnchorPane.setTopAnchor(findProductListView, 100.0);
        AnchorPane.setLeftAnchor(findProductListView, 10.0);
        AnchorPane.setRightAnchor(findProductListView, 10.0);

        controlBox.setAlignment(Pos.CENTER);
        controlBox.getChildren().addAll(editButton, removeButton);
        AnchorPane.setTopAnchor(controlBox, 360.0);
        AnchorPane.setLeftAnchor(controlBox, 10.0);
        AnchorPane.setRightAnchor(controlBox, 10.0);


        paneContent.getChildren().addAll(text, searchBox, title1, title2, title3, title4, findProductListView, controlBox, infoText);
    }


    public void closeApp() {
        STAGE.close();
    }
}
