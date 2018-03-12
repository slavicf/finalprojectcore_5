package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import settings.Settings;

public class GUI {

    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private int spacing = 10;
    private Stage window;
    private Scene s0main, s1analytics, s2query, s9settings;
    private Settings settings;
//    private static ArrayList<Node> layout = new ArrayList<>();

    public GUI(Stage window, Settings settings) {
        this.window = window;
        this.settings = settings;

        scene0();
        scene1();
        scene2();
        scene9();

        window.setScene(s0main);
        window.setTitle("YouTube анализатор");
        window.show();
    }

    private void scene0() {                          // Главный

        Label label0 = new Label("Добро пожаловать!");
        Label label1 = new Label("YouTube анализатор. Версия 0.00000001 альфа");

        Button button1 = new Button("Перейти в YouTube Analytics");
        button1.setOnAction(e -> window.setScene(s1analytics));

        Button button2 = new Button("Перейти в Настройки");
        button2.setOnAction(e -> window.setScene(s9settings));

        Pane layout = new VBox(spacing);
        layout.getChildren().addAll(label0, label1, button1, button2);
        s0main = new Scene(layout, WIDTH, HEIGHT);
    }

    private void scene1() {                          // YouTube Analytics

        Label label0 = new Label("YouTube Analytics");
        Label label1 = new Label("Выберите задачу");

        Button button0 = new Button("Вернуться на главный экран");
        button0.setOnAction(e -> window.setScene(s0main));

        Button button1 = new Button("Задача 1");
        button1.setOnAction(e -> window.setScene(s2query));

        Button button2 = new Button("Задача 2");
        button2.setOnAction(e -> window.setScene(s2query));

        Button button3 = new Button("Задача 3");
        button3.setOnAction(e -> window.setScene(s2query));

        Button button4 = new Button("Задача 4");
        button4.setOnAction(e -> window.setScene(s2query));

        Button button5 = new Button("Задача 5");
        button5.setOnAction(e -> window.setScene(s2query));

        Button button6 = new Button("Задача 6");
        button6.setOnAction(e -> window.setScene(s2query));

        Pane layout = new VBox(spacing);
        layout.getChildren().addAll(label0, label1, button1, button2, button3, button4, button5, button6);
        s1analytics = new Scene(layout, WIDTH, HEIGHT);
    }

    private void scene2() {                          // Экран запроса

        Label label0 = new Label("Название запроса");
        Label label1 = new Label("Краткое описание результата выполнения запроса");
        Label label2 = new Label("Описание формата ввода данных");

        Button button0 = new Button("Вернуться на главный экран");
        button0.setOnAction(e -> window.setScene(s0main));

        Button button1 = new Button("Перейти в YouTube Analytics");
        button1.setOnAction(e -> window.setScene(s1analytics));

        Pane layout = new VBox(spacing);
        layout.getChildren().addAll(label0, label1, label2, button0, button1);
        s2query = new Scene(layout, WIDTH, HEIGHT);
    }

    private void scene9() {                          // Настройки

        Label label0 = new Label("Настройки");

        Button button0 = new Button("Вернуться на главный экран");
        button0.setOnAction(e -> window.setScene(s0main));

        Label label1 = new Label("Сохранять кэш?");
        TextField textField1 = new TextField(settings.getSaveCache().toString());
        Button button1 = new Button("Применить");
        button1.setOnAction(e -> {
            String string = textField1.getText().toLowerCase();
            if(string.equals("true")) settings.setSaveCache(true);
            else if(string.equals("false")) settings.setSaveCache(false);
        });

        Label label2 = new Label("Путь к кэшу");
        TextField textField2 = new TextField(settings.getPathToCache());
        Button button2 = new Button("Применить");
        button2.setOnAction(e -> settings.setPathToCache(textField2.getText()));

        Label label3 = new Label("Отображать время затраченное на выполнение задачи?");
        TextField textField3 = new TextField(settings.getCalculateTimeForQuery().toString());
        Button button3 = new Button("Применить");
        button3.setOnAction(e -> {
            String string = textField3.getText().toLowerCase();
            if(string.equals("false")) settings.setCalculateTimeForQuery(false);
            else if(string.equals("true")) settings.setCalculateTimeForQuery(true);
        });

        Pane layout = new VBox(spacing);
        layout.getChildren().addAll(label0, button0, label1, textField1, button1,
                label2, textField2, button2, label3, textField3, button3);
        s9settings = new Scene(layout, WIDTH, HEIGHT);
    }

}
