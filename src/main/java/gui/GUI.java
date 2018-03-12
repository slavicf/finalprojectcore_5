package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    public static Stage window;
    public static Scene s0main, s1analytics, s2query, s9settings;
//    private static ArrayList<Node> layout = new ArrayList<>();

    public static void setup() {
        scene0();
        scene1();
        scene2();
        scene9();
    }

    private static void scene0() {                          // Главный
        VBox layout = new VBox(5);

        Label label0 = new Label("Добро пожаловать!");
        Label label1 = new Label("YouTube анализатор. Версия 0.00000001 альфа");

        Button button1 = new Button("Перейти в YouTube Analytics");
        button1.setOnAction(e -> window.setScene(s1analytics));

        Button button2 = new Button("Перейти в Настройки");
        button2.setOnAction(e -> window.setScene(s9settings));

        layout.getChildren().addAll(label0, label1, button1, button2);
        s0main = new Scene(layout, WIDTH, HEIGHT);
    }

    private static void scene1() {                          // YouTube Analytics
        VBox layout = new VBox(5);

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

        layout.getChildren().addAll(label0, label1, button1, button2, button3, button4, button5, button6);
        s1analytics = new Scene(layout, WIDTH, HEIGHT);
    }

    private static void scene2() {                          // Экран запроса
        VBox layout = new VBox(5);

        Label label0 = new Label("Название запроса");
        Label label1 = new Label("Краткое описание результата выполнения запроса");
        Label label2 = new Label("Описание формата ввода данных");

        Button button0 = new Button("Вернуться на главный экран");
        button0.setOnAction(e -> window.setScene(s0main));

        layout.getChildren().addAll(label0, label1, label2, button0);
        s2query = new Scene(layout, WIDTH, HEIGHT);
    }

    private static void scene9() {                          // Настройки
        VBox layout = new VBox(5);

        Label label0 = new Label("Настройки");
        Label label1 = new Label("Использовать кэш");
        Label label2 = new Label("Путь к кэшу");
        Label label3 = new Label("Отображать время затраченное на выполнение задачи?");

        Button button0 = new Button("Вернуться на главный экран");
        button0.setOnAction(e -> window.setScene(s0main));

        layout.getChildren().addAll(label0, button0, label1, label2, label3);
        s9settings = new Scene(layout, WIDTH, HEIGHT);
    }

}
