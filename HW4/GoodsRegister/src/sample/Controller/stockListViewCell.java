package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import sample.Model.Stock;

import java.io.IOException;

class stockListViewCell extends ListCell<Stock> {
    @FXML
    private Label stockId;
    @FXML
    private Label stockName;
    @FXML
    private GridPane paneStock;
    private FXMLLoader mLLoaderStock;

    @Override
    protected void updateItem(Stock stock, boolean empty) {
        super.updateItem(stock, empty);
        if (empty || stock == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoaderStock == null) {
                mLLoaderStock = new FXMLLoader(getClass().getResource("../view/stockItem.fxml"));
                mLLoaderStock.setController(this);
                try {
                    mLLoaderStock.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            stockId.setText(String.valueOf(stock.getStockId()));
            stockName.setText(stock.getStockName());
            setText(null);
            setGraphic(paneStock);
        }

    }
}
