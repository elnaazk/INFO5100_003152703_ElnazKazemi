package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {
    private TextField aField, bField;
    private Label opLabel, resultLabel;
    private char op = '+';

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) {
        aField = new TextField(); aField.setPrefColumnCount(6);
        bField = new TextField(); bField.setPrefColumnCount(6);

        opLabel = new Label(String.valueOf(op));
        Label eq = new Label("=");

        resultLabel = new Label(" ");

        Button plus = new Button("+");
        Button minus = new Button("-");
        Button times = new Button("×");
        Button divide = new Button("÷");

        plus.setOnAction(e -> setOp('+'));
        minus.setOnAction(e -> setOp('-'));
        times.setOnAction(e -> setOp('*'));
        divide.setOnAction(e -> setOp('/'));

        Button calc = new Button("Calc");
        Button clear = new Button("Clear");

        calc.setOnAction(e -> compute());
        clear.setOnAction(e -> {
            aField.clear();
            bField.clear();
            resultLabel.setText(" ");
        });

        GridPane top = new GridPane();
        top.setHgap(10);
        top.setVgap(10);
        top.add(aField, 0, 0);
        top.add(opLabel, 1, 0);
        top.add(bField, 2, 0);
        top.add(eq, 3, 0);
        top.add(resultLabel, 4, 0);

        HBox ops = new HBox(10, plus, minus, times, divide);
        ops.setAlignment(Pos.CENTER);

        HBox actions = new HBox(10, calc, clear);
        actions.setAlignment(Pos.CENTER);

        VBox root = new VBox(12, top, ops, actions);
        root.setPadding(new Insets(18));
        root.setAlignment(Pos.CENTER);

        stage.setTitle("Calculator");
        stage.setScene(new Scene(root, 360, 180));
        stage.show();
    }

    private void setOp(char c) {
        op = c;
        opLabel.setText(String.valueOf(c));
    }

    private void compute() {
        try {
            double a = Double.parseDouble(aField.getText().trim());
            double b = Double.parseDouble(bField.getText().trim());
            double r;
            if (op == '+') r = a + b;
            else if (op == '-') r = a - b;
            else if (op == '*') r = a * b;
            else {
                if (b == 0) { resultLabel.setText("NaN"); return; }
                r = a / b;
            }
            resultLabel.setText(String.valueOf(r));
        } catch (Exception ex) {
            resultLabel.setText("invalid");
        }
    }
}
