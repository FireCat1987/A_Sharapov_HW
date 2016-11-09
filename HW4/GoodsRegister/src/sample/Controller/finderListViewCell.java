package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import sample.Model.Product;

import java.io.IOException;

class finderListViewCell extends ListCell<Product> {
    @FXML
    private Label productIdF;
    @FXML
    private Label productNameF;
    @FXML
    private Label productPriceF;
    @FXML
    private Label stockNameF;
    @FXML
    private GridPane paneProduct;
    private FXMLLoader mLLoaderProduct;
    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);
        if(empty || product == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoaderProduct == null) {
                mLLoaderProduct = new FXMLLoader(getClass().getResource("../view/find.fxml"));
                mLLoaderProduct.setController(this);
                try {
                    mLLoaderProduct.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            productIdF.setText(String.valueOf(product.getProductId()));
            productNameF.setText(product.getProductName());
            productPriceF.setText(product.getProductCost().toString());
            stockNameF.setText(product.getProductStockName());
            setText(null);
            setGraphic(paneProduct);
        }

    }
}

