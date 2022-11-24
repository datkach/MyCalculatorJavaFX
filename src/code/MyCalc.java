package code;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MyCalc extends Application {

    Text text = new Text("0");

    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox();
        root.setStyle("-fx-background-color: #EDF0F2");

        Scene scene = new Scene(root);


        root.getChildren().add(configureMenu());

        HBox box = configureResultView();
        root.getChildren().add(box);

        VBox.setMargin(box, new Insets(10, 10, 2, 10));

        HBox panes = new HBox();
        panes.getChildren().addAll(configureIngenButtons(), configureButtons());

        root.getChildren().add(panes);

        primaryStage.setTitle("My calculator");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private HBox radioBox() {

        HBox panel = new HBox();

        ToggleGroup radioGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton("Градусы");
        radioButton1.setToggleGroup(radioGroup);
        radioButton1.fire();
        RadioButton radioButton2 = new RadioButton("Радианы");
        radioButton2.setToggleGroup(radioGroup);
        RadioButton radioButton3 = new RadioButton("Грады");
        radioButton3.setToggleGroup(radioGroup);

        panel.getChildren().addAll(radioButton1, radioButton2, radioButton3);

        panel.setSpacing(10);
        panel.setStyle("-fx-border-width: 0.7;" +
                "-fx-border-radius: 4;" +
                "-fx-border-color: #9fa1a3;" +
                "-fx-padding: 3 3 3 3");

        return panel;

    }


    private GridPane configureIngenButtons() {
        GridPane pane = new GridPane();

        // 1ый ряд
        pane.add(radioBox(), 0, 0, 5, 1);

        Rectangle rect = new Rectangle(40, 23, Color.rgb(237, 240, 242));
        rect.setArcHeight(4);
        rect.setArcWidth(4);
        rect.setStroke(Color.valueOf("#9fa1a3"));
        rect.setStrokeWidth(0.7);
        rect.setVisible(true);

        // 2ой ряд
        pane.add(rect, 0, 1);
        pane.add(new MyButton("Inv"), 1, 1);
        pane.add(new MyButton("ln"), 2, 1);
        pane.add(new MyButton("("), 3, 1);
        pane.add(new MyButton(")"), 4, 1);

        // 3ий ряд
        pane.add(new MyButton("Int"), 0, 2);
        pane.add(new MyButton("sinh"), 1, 2);
        pane.add(new MyButton("sin"), 2, 2);
        pane.add(new MyButton("x" + (char) 178), 3, 2);
        pane.add(new MyButton("n!"), 4, 2);

        // 4ый ряд
        pane.add(new MyButton("dms"), 0, 3);
        pane.add(new MyButton("cosh"), 1, 3);
        pane.add(new MyButton("cos"), 2, 3);
        pane.add(new MyButton("x" + '\u02B8'), 3, 3);
        pane.add(new MyButton('\u02B8' + "√x"), 4, 3);

        // 5ый ряд
        pane.add(new MyButton("π"), 0, 4);
        pane.add(new MyButton("tanh"), 1, 4);
        pane.add(new MyButton("tan"), 2, 4);
        pane.add(new MyButton("x" + (char) 179), 3, 4);
        pane.add(new MyButton('\u1D9F' + "√x"), 4, 4);

        // 6ой ряд
        pane.add(new MyButton("F-E"), 0, 5);
        pane.add(new MyButton("Exp"), 1, 5);
        pane.add(new MyButton("Mod"), 2, 5);
        pane.add(new MyButton("log"), 3, 5);
        pane.add(new MyButton("10" + '\u036F'), 4, 5);

        // Свойства колонок
        ColumnConstraints cc = new ColumnConstraints();
        cc.setFillWidth(true);
        cc.setHgrow(Priority.ALWAYS);
        pane.getColumnConstraints().addAll(cc,cc,cc,cc,cc);

        pane.setStyle("-fx-padding: 4 3 2 10");
        pane.setHgap(6);
        pane.setVgap(6);
        return pane;
    }


    private GridPane configureButtons() {

        GridPane pane = new GridPane();

        // 1ый ряд
        pane.add(new MathButton("MC"), 0, 0);
        pane.add(new MathButton("MR"), 1, 0);
        pane.add(new MathButton("MS"), 2, 0);
        pane.add(new MathButton("M+"), 3, 0);
        pane.add(new MathButton("M-"), 4, 0);


        MyButton clearButton = new MyButton("C");
        clearButton.addEventHandler(ActionEvent.ACTION, event -> text.setText("0"));

        // 2ой ряд
        pane.add(new MyButton("<-"), 0, 1);
        pane.add(new MyButton("CE"), 1, 1);
        pane.add(clearButton, 2, 1);
        pane.add(new MyButton("±"), 3, 1);
        pane.add(new MyButton("√"), 4, 1);

        // 3ий ряд
        pane.add(new NumButton("7"), 0, 2);
        pane.add(new NumButton("8"), 1, 2);
        pane.add(new NumButton("9"), 2, 2);
        pane.add(new MyButton("/"), 3, 2);
        pane.add(new MyButton("%"), 4, 2);

        // 4ый ряд
        pane.add(new NumButton("4"), 0, 3);
        pane.add(new NumButton("5"), 1, 3);
        pane.add(new NumButton("6"), 2, 3);
        pane.add(new MyButton("*"), 3, 3);
        pane.add(new MyButton("1/х"), 4, 3);

        // 5ый ряд
        pane.add(new NumButton("1"), 0, 4);
        pane.add(new NumButton("2"), 1, 4);
        pane.add(new NumButton("3"), 2, 4);
        pane.add(new MyButton("-"), 3, 4);
        pane.add(new MyButton("="), 4, 4, 1, 2);

        // 6ой ряд
        pane.add(new NumButton("0"), 0, 5, 2, 1);
        pane.add(new NumButton(","), 2, 5);
        pane.add(new MyButton("+"), 3, 5);

        // Свойства колонок
        ColumnConstraints cc = new ColumnConstraints();
        cc.setFillWidth(true);
        cc.setHgrow(Priority.ALWAYS);
        pane.getColumnConstraints().addAll(cc,cc,cc,cc,cc);



        pane.addEventFilter(ActionEvent.ACTION, event -> {
            if(event.getTarget() instanceof NumButton button){
                String current = text.getText();

                if(current.equals("0")) {
                    text.setText(button.getValue());
                }
                else {
                    text.setText(current + button.getValue());
                }

            }

        });

        pane.setStyle("-fx-padding: 4 0 2 3");
        pane.setHgap(6);
        pane.setVgap(6);
        return pane;
    }




    private HBox configureResultView() {

        HBox box = new HBox();
        box.setMaxHeight(40);

        box.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 3;" +
                "-fx-border-color: gray;" +
                "-fx-padding: 10 2 6 30;" +
                "-fx-background-color: linear-gradient(to bottom," +
                " #e8f1f9 0%, #fcfdff 50%);");



        text.setTextAlignment(TextAlignment.RIGHT);
        text.setFont(new Font(30));

        box.getChildren().add(text);
        box.setAlignment(Pos.BOTTOM_RIGHT);

        return box;
    }

    private MenuBar configureMenu() {

        MenuBar bar = new MenuBar();

        Menu view = new Menu("_Вид");

        ToggleGroup group1 = new ToggleGroup();

        RadioMenuItem option1 = new RadioMenuItem("_Обычный");
        option1.setAccelerator(KeyCombination.keyCombination("ALT+1"));
        option1.setToggleGroup(group1);
        RadioMenuItem option2 = new RadioMenuItem("_Инженерный");
        option2.setAccelerator(KeyCombination.keyCombination("ALT+2"));
        option2.setToggleGroup(group1);
        RadioMenuItem option3 = new RadioMenuItem("_Программист");
        option3.setAccelerator(KeyCombination.keyCombination("ALT+3"));
        option3.setToggleGroup(group1);
        RadioMenuItem option4 = new RadioMenuItem("_Статистика");
        option4.setAccelerator(KeyCombination.keyCombination("ALT+4"));
        option4.setToggleGroup(group1);

        SeparatorMenuItem sep1 = new SeparatorMenuItem();


        CheckMenuItem option5 = new CheckMenuItem("_Журнал");
        option5.setAccelerator(new KeyCodeCombination(KeyCode.H,
                KeyCombination.SHORTCUT_DOWN));
        CheckMenuItem option6 = new CheckMenuItem("_Группировка цифр по разрядам");

        SeparatorMenuItem sep2 = new SeparatorMenuItem();

        ToggleGroup group2 = new ToggleGroup();

        RadioMenuItem option7 = new RadioMenuItem("_Обычный");
        option7.setAccelerator(new KeyCodeCombination(KeyCode.F4,
                KeyCombination.SHORTCUT_DOWN));
        option7.setToggleGroup(group2);
        RadioMenuItem option8 = new RadioMenuItem("_Преобразование единиц");
        option8.setAccelerator(new KeyCodeCombination(KeyCode.U,
                KeyCombination.SHORTCUT_DOWN));
        option8.setToggleGroup(group2);
        RadioMenuItem option9 = new RadioMenuItem("_Вычисление даты");
        option9.setAccelerator(new KeyCodeCombination(KeyCode.E,
                KeyCombination.SHORTCUT_DOWN));
        option9.setToggleGroup(group2);


        Menu lists = new Menu("_Листы");
        MenuItem list1 = new MenuItem("_Ипотека");
        MenuItem list2 = new MenuItem("_Автолизинг");
        MenuItem list3 = new MenuItem("_Экономия(миль/галлон)");
        MenuItem list4 = new MenuItem("_Экономия топлива(л/100 км)");

        lists.getItems().addAll(list1, list2, list3, list4);



        view.getItems().addAll(option1, option2, option3, option4, sep1,
                option5, option6, sep2, option7, option8, option9, lists);


        Menu edit = new Menu("_Правка");

        MenuItem copy = new MenuItem("_Копировать");
        copy.setAccelerator(new KeyCodeCombination(KeyCode.C,
                KeyCombination.SHORTCUT_DOWN));
        MenuItem paste = new MenuItem("_Вставить");
        paste.setAccelerator(new KeyCodeCombination(KeyCode.V,
                KeyCombination.SHORTCUT_DOWN));

        SeparatorMenuItem sep3 = new SeparatorMenuItem();

        Menu journal = new Menu("_Журнал");
        MenuItem item1 = new MenuItem("_Копировать журнал");
        MenuItem item2 = new MenuItem("_Изменить");
        item2.setAccelerator(KeyCombination.keyCombination("F2"));
        MenuItem item3 = new MenuItem("_Отменить правку");
        item3.setAccelerator(KeyCombination.keyCombination("ESC"));
        MenuItem item4 = new MenuItem("_Очистить");
        item4.setAccelerator(KeyCombination.keyCombination("CTRL+ALT+D"));

        journal.getItems().addAll(item1, item2, item3, item4);

        edit.getItems().addAll(copy, paste, sep3, journal);



        Menu help = new Menu("_Справка");

        MenuItem viewHelp = new MenuItem("_Просмотреть справку");
        viewHelp.setAccelerator(KeyCombination.keyCombination("F1"));

        SeparatorMenuItem sep4 = new SeparatorMenuItem();

        MenuItem about = new MenuItem("_О программе");

        help.getItems().addAll(viewHelp, sep4, about);

        bar.getMenus().addAll(view, edit, help);

        return bar;
    }

    private static class NumButton extends MathButton {

        private final String value;

        NumButton(String text) {

            super(text);
            this.value = text;
            this.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, reflect," +
                            " #fcfdff 20%, #e8f1f9 55%, #fcfdff 90%);" +
                            "-fx-border-width: 0.7;" +
                            "-fx-border-radius: 4;" +
                            "-fx-border-color: #9fa1a3;" +
                            "-fx-padding: 4 8 4 8");
        }

        public String getValue() {
            return value;
        }

    }

    private static class MyButton extends MathButton {

        MyButton(String text) {
            super(text);
            this.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, reflect," +
                            " #f9fcff 0%,#dce0e5 55%, #bfd5ef 100%);" +
                            "-fx-border-width: 0.7;" +
                            "-fx-border-radius: 4;" +
                            "-fx-border-color: #9fa1a3;" +
                            "-fx-padding: 4 8 4 8");
        }

    }

    private static class MathButton extends Button {
        MathButton(String text) {
            super(text);
            this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
