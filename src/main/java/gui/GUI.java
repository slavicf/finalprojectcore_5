package gui;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import settings.Settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GUI {

    private int width = 700;
    private int height = 800;
    private Font font = new Font(12);
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
//        window.setResizable(false);
        window.getIcons().add(new Image("https://cdn1.iconfinder.com/data/icons/logotypes/32/youtube-256.png"));
        window.show();
    }

    private void scene0() {                          // Главный

        Label label0 = label("Добро пожаловать!");
        Label label1 = label("YouTube анализатор. Версия 0.00000001 альфа");

        Button button1 = button("Перейти в YouTube Analytics");
        button1.setOnAction(e -> window.setScene(s1analytics));

        Button button2 = button("Перейти в Настройки");
        button2.setOnAction(e -> window.setScene(s9settings));

        Pane layout = new VBox(spacing);
        layout.getChildren().addAll(label0, label1, button1, button2);
        s0main = new Scene(layout, width, height);
    }

    private void scene1() {                          // YouTube Analytics

        Label label0 = label("YouTube Analytics");
        Label label1 = label("Выберите задачу");

        Button button0 = button("Вернуться на главный экран");
        button0.setOnAction(e -> window.setScene(s0main));

        Button button1 = button("[I] Отобразить глобальную информацию о канале");
        button1.setOnAction(e -> task1());

        Button button2 = button("[II] Сравнить глобальную информацию о каналах");
        button2.setOnAction(e -> task2());

        Button button3 = button("[III] Сортировать каналы по их данным");
        button3.setOnAction(e -> task3());

        Button button4 = button("[IV] Медиа резонанс");
        button4.setOnAction(e -> task4());

        Button button5 = button("[V] Сравнить Медиа резонанс");
        button5.setOnAction(e -> task5());

        Button button6 = button("[VI] Сортировать по Медиа резонансу");
        button6.setOnAction(e -> task6());

        Pane layout = new VBox(spacing);
        layout.getChildren().addAll(label0, label1, button1, button2, button3, button4, button5, button6);
        s1analytics = new Scene(layout, width, height);
    }

    private void scene2() {                          // Экран запроса
        Pane layout = new VBox(spacing);
        layout.getChildren().addAll(nodes);
        s2query = new Scene(layout, width, height);
        window.setScene(s2query);
    }

    private void scene9() {                          // Настройки
        Label label0 = label("Настройки");

        Button button0 = button("Вернуться на главный экран");
        button0.setOnAction(e -> window.setScene(s0main));

        Label label1 = label("Сохранять кэш?");
        TextField textField1 = textField(settings.getSaveCache().toString());
        Button button1 = button("Применить");
        button1.setOnAction(e -> {
            String string = textField1.getText().toLowerCase();
            if (string.equals("true")) settings.setSaveCache(true);
            else if (string.equals("false")) settings.setSaveCache(false);
        });

        Label label2 = label("Путь к кэшу");
        TextField textField2 = textField(settings.getPathToCache());
        Button button2 = button("Применить");
        button2.setOnAction(e -> settings.setPathToCache(textField2.getText()));

        Label label3 = label("Отображать время затраченное на выполнение задачи?");
        TextField textField3 = textField(settings.getCalculateTimeForQuery().toString());
        Button button3 = button("Применить");
        button3.setOnAction(e -> {
            String string = textField3.getText().toLowerCase();
            if (string.equals("false")) settings.setCalculateTimeForQuery(false);
            else if (string.equals("true")) settings.setCalculateTimeForQuery(true);
        });

        Pane layout = new VBox(spacing);
        layout.getChildren().addAll(label0, button0, label1, textField1, button1,
                label2, textField2, button2, label3, textField3, button3);
        s9settings = new Scene(layout, width, height);
    }

    private void head(String text, String text2) {
        nodes = new ArrayList<>();

        Button button0 = button("Вернуться на главный экран");
        button0.setOnAction(e -> window.setScene(s0main));
        nodes.add(button0);
        Button button1 = button("Перейти в YouTube Analytics");
        button1.setOnAction(e -> window.setScene(s1analytics));
        nodes.add(button1);
        nodes.add(label(text));     // Название запроса
        nodes.add(label("Результат запроса"));
        nodes.add(label(text2));
        nodes.add(textField("UCuXYmUOJSbEH1x88WUV1aMg"));
    }

    private void task1() {
        head("Отобразить глобальную информацию о канале", "Введите ChannelID:");
        Button button1 = button("Выполнить");
        button1.setOnAction(e -> {
//            System.out.println("Task1");
            try {
                String channelId = ((TextField) nodes.get(5)).getText();
                String string = Query1.query1(channelId, settings);
                ((Label) nodes.get(3)).setText(string);
            } catch (UnirestException | IOException | ExecutionException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        nodes.add(button1);
        scene2();
    }

    private void task2() {
        head("Сравнить глобальную информацию о каналах", "Введите ChannelID1 и ChannelID2 через пробел:");
        Button button1 = button("Выполнить");
        button1.setOnAction(e -> {
           // System.out.println("Task2");
            try {
                String[] channelIds = ((TextField) nodes.get(5)).getText().split(" ");
                String string = Query2.query2(channelIds[0], channelIds[1], settings);
                ((Label) nodes.get(3)).setText(string);
            } catch (ExecutionException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        nodes.add(button1);
        scene2();
    }

    private void task3() {
        head("Сортировать каналы по их данным", "Введите массив каналов через пробел:");
        nodes.add(label("Введите способ сортировки title/date/subscribers/videos/views:"));
//        nodes.add(textField("title"));
        ObservableList<String> options = FXCollections.observableArrayList("title", "date", "subscribers", "videos", "views");
        nodes.add(comboBox(options));
        Button button1 = button("Выполнить");
        button1.setOnAction(e -> {
            //System.out.println("Task3");
            try {
                String[] channelIds = ((TextField) nodes.get(5)).getText().split(" ");
//                String sortSwitcher = ((TextField) nodes.get(7)).getText().toLowerCase();
                String sortSwitcher = (String) ((ComboBox) nodes.get(7)).getValue();
                String string = Query3.query3(channelIds, sortSwitcher, settings);
                ((Label) nodes.get(3)).setText(string);
            } catch (UnirestException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        nodes.add(button1);
        scene2();
    }

    private void task4() {
        head("Медиа резонанс", "Введите ChannelID:");
        Button button1 = button("Выполнить");
        button1.setOnAction(e -> {
//            System.out.println("Task4");
            try {
                String channelId = ((TextField) nodes.get(5)).getText();
                String string = Query.task4(channelId, settings);
                ((Label) nodes.get(3)).setText(string);
            } catch (ExecutionException | InterruptedException | UnirestException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        nodes.add(button1);
        scene2();
    }

    private void task5() {
        head("Сравнить Медиа резонанс", "Введите ChannelID1 и ChannelID2 через пробел:");
        Button button1 = button("Выполнить");
        button1.setOnAction(e -> {
//            System.out.println("Task5");
            try {
                String[] channelIds = ((TextField) nodes.get(5)).getText().split(" ");
                String string = Query.query5(channelIds[0], channelIds[1], settings);
                ((Label) nodes.get(3)).setText(string);
            } catch (ExecutionException | InterruptedException | UnirestException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        nodes.add(button1);
        scene2();
    }

    private void task6() {
        head("Сортировать каналы по Медиа резонансу", "Введите массив каналов через пробел:");
        nodes.add(label("Введите способ сортировки title/date/subscribers/videos/views/comments:"));
//        nodes.add(textField("title"));
        ObservableList<String> options = FXCollections.observableArrayList("title", "date", "subscribers", "videos", "views", "comments");
        nodes.add(comboBox(options));
        Button button1 = button("Выполнить");
        button1.setOnAction(e -> {
//            System.out.println("Task6");
            try {
                String[] channelIds = ((TextField) nodes.get(5)).getText().split(" ");
//                String sortSwitcher = ((TextField) nodes.get(7)).getText().toLowerCase();
                String sortSwitcher = (String) ((ComboBox) nodes.get(7)).getValue();
                String string = Query.query6(channelIds, sortSwitcher, settings);
                ((Label) nodes.get(3)).setText(string);
            } catch (ExecutionException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        nodes.add(button1);
        scene2();
    }

    private Label label(String text) {
        Label label = new Label(text);
        label.setFont(font);
        return label;
    }

    private TextField textField(String text){
        TextField textField = new TextField(text);
        textField.setFont(font);
        return textField;
    }

    private ComboBox comboBox(ObservableList options){
        ComboBox comboBox = new ComboBox(options);
        comboBox.setValue(options.get(0));
        comboBox.setStyle("-fx-font: " + font + "px;");
        return comboBox;
    }

    private Button button(String text){
        Button button = new Button(text);
        button.setFont(font);
        return button;
    }
}
