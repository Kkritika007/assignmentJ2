package com.example.a2java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.List;

public class HistoryController {

    @FXML
    private Label historyLabel;

    private List<String> currencyHistory;

    // Method to receive currency history data from the first view
    public void setCurrencyHistory(List<String> currencyHistory) {
        this.currencyHistory = currencyHistory;
        // Display currency history in the UI
        showHistory();
    }

    private void showHistory() {
        // Display currency history in the label
        if (currencyHistory != null && !currencyHistory.isEmpty()) {
            StringBuilder historyText = new StringBuilder();
            for (String entry : currencyHistory) {
                historyText.append(entry).append("\n");
            }
            historyLabel.setText(historyText.toString());
        } else {
            historyLabel.setText("No currency conversion history available.");
        }
    }

    @FXML
    void resetHistoryButtonClicked(ActionEvent event) {
        // Clear currency history
        currencyHistory.clear();
        // Clear displayed history in the UI
        historyLabel.setText("");
    }

    @FXML
    void goBackButtonClicked(ActionEvent event) {
        // Close the current window (History view)
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}


