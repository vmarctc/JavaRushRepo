import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Converter extends Application {
    private final Label[] labelsForText = new Label[4];
    private final TextField fieldForInput = new TextField();
    private final TextField fieldForResult = new TextField();
    private final Button calcButton = new Button("Calculate");
    private final Button clearButton = new Button("Clear");
    private final ComboBox<String> chooseCurrency = new ComboBox<String>();
    private final ComboBox<String> resultCurrency = new ComboBox<String>();
    private String[] stringsForParsing = new String[7];
    private double[] doublesForParsing = new double[7];
    private static final double COURSE_USD = 26.97;
    private static final double COURSE_EUR = 31.42;
    private static final double COURSE_UAH_USD = 0.037082;
    private static final double COURSE_UAH_EUR = 0.031824;
    private static final double COURSE_EUR_USD = 1.16;
    private static final double COURSE_USD_EUR = 0.86;

    private static double convertFromUSDtoUAH(double a) {
        return a * COURSE_USD;
    }

    private static double convertFromEURtoUAH(double a) {
        return a * COURSE_EUR;
    }

    private static double convertFromUAHtoUSD(double a) {
        return a * COURSE_UAH_USD;
    }

    private static double convertFromUAHtoEUR(double a) {
        return a * COURSE_UAH_EUR;
    }

    private static double convertFromEURtoUSD(double a) {
        return a * COURSE_EUR_USD;
    }

    private static double convertFromUSDtoEUR(double a) {
        return a * COURSE_USD_EUR;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Currency Converter");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(30);
        grid.setVgap(10);
        Scene scene = new Scene(grid, 400, 250);
        stage.setScene(scene);
        stage.show();

        chooseCurrency.getItems().addAll("UAH - hryvna", "USD - dollar", "EUR - euro");
        chooseCurrency.setPrefSize(150, 20);
        resultCurrency.getItems().addAll("UAH - hryvna", "USD - dollar", "EUR - euro");
        resultCurrency.setPrefSize(150, 20);
        calcButton.setPrefSize(130, 40);
        clearButton.setPrefSize(130, 40);

        grid.add(new Label("Enter amount"), 0, 0);
        grid.add(new Label("Result"), 2, 0);
        grid.add(new Label("Choose currency"), 0, 2);
        grid.add(new Label("Convert to"), 2, 2);
        grid.add(chooseCurrency, 0, 3);
        grid.add(resultCurrency, 2, 3);
        grid.add(fieldForInput, 0, 1);
        grid.add(fieldForResult, 2, 1);
        grid.add(calcButton, 0, 6);
        grid.add(clearButton, 2, 6);

        calcButton.setOnAction(event -> {
            stringsForParsing[0] = fieldForInput.getText();

            try {
                doublesForParsing[0] = Double.parseDouble(stringsForParsing[0]);
                if (chooseCurrency.getValue() == "USD - dollar" && resultCurrency.getValue() == "UAH - hryvna") {
                    doublesForParsing[1] = convertFromUSDtoUAH(doublesForParsing[0]);
                    stringsForParsing[1] = Double.toString(doublesForParsing[1]);
                    fieldForResult.setText(stringsForParsing[1] + "UAH");
                } else if (chooseCurrency.getValue() == "EUR - euro" && resultCurrency.getValue() == "UAH - hryvna") {
                    doublesForParsing[2] = convertFromEURtoUAH(doublesForParsing[0]);
                    stringsForParsing[2] = Double.toString(doublesForParsing[2]);
                    fieldForResult.setText(stringsForParsing[2] + "UAH");
                } else if (chooseCurrency.getValue() == "UAH - hryvna" && resultCurrency.getValue() == "USD - dollar") {
                    doublesForParsing[3] = convertFromUAHtoUSD(doublesForParsing[0]);
                    stringsForParsing[3] = Double.toString(doublesForParsing[3]);
                    fieldForResult.setText(stringsForParsing[3] + "USD");
                } else if (chooseCurrency.getValue() == "UAH - hryvna" && resultCurrency.getValue() == "EUR - euro") {
                    doublesForParsing[4] = convertFromUAHtoEUR(doublesForParsing[0]);
                    stringsForParsing[4] = Double.toString(doublesForParsing[4]);
                    fieldForResult.setText(stringsForParsing[4] + "EUR");
                } else if (chooseCurrency.getValue() == "EUR - euro" && resultCurrency.getValue() == "USD - dollar") {
                    doublesForParsing[5] = convertFromEURtoUSD(doublesForParsing[0]);
                    stringsForParsing[5] = Double.toString(doublesForParsing[5]);
                    fieldForResult.setText(stringsForParsing[5] + "USD");
                } else if (chooseCurrency.getValue() == "USD - dollar" && resultCurrency.getValue() == "EUR - euro") {
                    doublesForParsing[6] = convertFromUSDtoEUR(doublesForParsing[0]);
                    stringsForParsing[6] = Double.toString(doublesForParsing[6]);
                    fieldForResult.setText(stringsForParsing[6] + "EUR");
                }
            } catch (Exception e) {

                System.out.println("Enter the number in field for input currency!");
            }

        });
        clearButton.setOnAction(event -> {
            fieldForInput.clear();
            fieldForResult.clear();

        });

    }
}
