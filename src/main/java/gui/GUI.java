package gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import settings.Settings;

import java.util.ArrayList;

public class GUI {

    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private int spacing = 10;
    private Stage window;
    private Scene s0main, s1analytics, s2query, s9settings;
    private Settings settings;
    private static ArrayList<Node> nodes;

    public GUI(Stage window, Settings settings) {
        this.window = window;
        this.settings = settings;

        scene0();
        scene1();
//        scene2();
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
        button1.setOnAction(e -> task1());

        Button button2 = new Button("Задача 2");
        button2.setOnAction(e -> task2());

        Button button3 = new Button("Задача 3");
        button3.setOnAction(e -> task3());

        Button button4 = new Button("Задача 4");
        button4.setOnAction(e -> task4());

        Button button5 = new Button("Задача 5");
        button5.setOnAction(e -> task5());

        Button button6 = new Button("Задача 6");
        button6.setOnAction(e -> task6());

        Pane layout = new VBox(spacing);
        layout.getChildren().addAll(label0, label1, button1, button2, button3, button4, button5, button6);
        s1analytics = new Scene(layout, WIDTH, HEIGHT);
    }

    private void head(String text) {
        nodes = new ArrayList<>();

        Button button0 = new Button("Вернуться на главный экран");
        button0.setOnAction(e -> window.setScene(s0main));
        nodes.add(button0);
        Button button1 = new Button("Перейти в YouTube Analytics");
        button1.setOnAction(e -> window.setScene(s1analytics));
        nodes.add(button1);
        nodes.add(new Label(text));            // Название запроса
        nodes.add(new Label("Краткое описание результата выполнения запроса"));

    }

    private void task1() {
        head("Отобразить глобальную информацию о канале");
        nodes.add(new Label("Введите ChannelID:"));
        nodes.add(new TextField());
        Button button1 = new Button("Выполнить");
        button1.setOnAction(e -> System.out.println("Task1"));
        nodes.add(button1);
        scene2();
    }

    private void task2() {
        head("Сравнить глобальную информацию о каналах");
        nodes.add(new Label("Введите ChannelID1 и ChannelID2 через пробел:"));
        nodes.add(new TextField());
        Button button1 = new Button("Выполнить");
        button1.setOnAction(e -> System.out.println("Task2"));
        nodes.add(button1);
        scene2();
    }

    private void task3() {
        head("Сортировать каналы по их данным");
        nodes.add(new Label("Введите массив каналов через пробел:"));
        nodes.add(new TextField());
        nodes.add(new Label("Введите способ сортировки name/date/subscribers/videos/views:"));
        nodes.add(new TextField());
        Button button1 = new Button("Выполнить");
        button1.setOnAction(e -> System.out.println("Task3"));
        nodes.add(button1);
        scene2();
    }

    private void task4() {
        head("Медиа резонанс");
        nodes.add(new Label("Введите ChannelID:"));
        nodes.add(new TextField());
        Button button1 = new Button("Выполнить");
        button1.setOnAction(e -> System.out.println("Task4"));
        nodes.add(button1);
        scene2();
    }

    private void task5() {
        head("Сравнить Медиа резонанс");
        nodes.add(new Label("Введите ChannelID1 и ChannelID2 через пробел:"));
        nodes.add(new TextField());
        Button button1 = new Button("Выполнить");
        button1.setOnAction(e -> System.out.println("Task5"));
        nodes.add(button1);
        scene2();
    }

    private void task6() {
        head("Сортировать каналы по Медиа резонансу");
        nodes.add(new Label("Введите массив каналов через пробел:"));
        nodes.add(new TextField());
        nodes.add(new Label("Введите способ сортировки name/date/subscribers/videos/views:"));
        nodes.add(new TextField());
        Button button1 = new Button("Выполнить");
        button1.setOnAction(e -> System.out.println("Task6"));
        nodes.add(button1);
        scene2();
    }

    private void scene2() {                          // Экран запроса
        Pane layout = new VBox(spacing);
        layout.getChildren().addAll(nodes);
        s2query = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(s2query);
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
