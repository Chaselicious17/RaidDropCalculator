package RaidDropCalc;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class RaidDropCalculator extends Application implements EventHandler<ActionEvent>
{
    private Stage stage;
    private Button bCalculate;
    private TextField tfTotalPTS;
    private Text tTotalPTS;
    private TableView<Item> tvItemList;
    private TableView<Chance> tvChancelist;
    private TableColumn ItemCol;
    private TableColumn ChanceCol;
    private ObservableList<Item> olItemList;
    private ObservableList<Chance> olChanceList;


    // Initialize window
    @Override
    public void start(Stage primaryStage)
    {
        stage = primaryStage;
        stage.setTitle("Raid Drop Calculator");

        // Calculate Button setup
        bCalculate = new Button();
        bCalculate.setText("Calculate");
        bCalculate.setPrefHeight(55);
        bCalculate.setPrefWidth(100);
        bCalculate.setOnAction(this);

        // Total Points Text setup
        tfTotalPTS = new TextField();
        tfTotalPTS.setMaxWidth(65);
        tTotalPTS = new Text("Total Raid Points:");

        // Item ObservableList setup
        olItemList = FXCollections.observableArrayList(
                new Item("Any drop"),
                new Item("Dex scroll"),
                new Item("Arcane scroll"),
                new Item("Dragon sword"),
                new Item("Dragon harpoon"),
                new Item("Dragon thrownaxe x100"),
                new Item("Twisted buckler"),
                new Item("Dragon hunter crossbow"),
                new Item("Dinh's bulwark"),
                new Item("Ancestral hat"),
                new Item("Ancestral robe top"),
                new Item("Ancestral robe bottom"),
                new Item("Dragon claws"),
                new Item("Elder Maul"),
                new Item("Kodai insignia"),
                new Item("Twisted Bow"));

        olChanceList = raidCalc(0);

        // Item TableView setup
        tvItemList = new TableView<>();
        tvItemList.setEditable(false);
        tvItemList.setMinHeight(410);
        tvItemList.setMaxHeight(410);
        tvItemList.setMinWidth(226);
        tvItemList.setMaxWidth(226);

        tvChancelist = new TableView<>();
        tvChancelist.setEditable(false);
        tvChancelist.setMinHeight(410);
        tvChancelist.setMaxHeight(410);
        tvChancelist.setMinWidth(90);
        tvChancelist.setMaxWidth(90);

        ItemCol = new TableColumn("Item");
        ItemCol.setSortable(false);
        ItemCol.setMinWidth(224);
        ItemCol.setMaxWidth(224);
        ItemCol.setResizable(false);
        ItemCol.setCellValueFactory(
                new PropertyValueFactory<>("itemType"));

        ChanceCol = new TableColumn("Chance (%)");
        ChanceCol.setSortable(false);
        ChanceCol.setMinWidth(88);
        ChanceCol.setMaxWidth(88);
        ChanceCol.setResizable(false);
        ChanceCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        ChanceCol.setCellValueFactory(
                new PropertyValueFactory<>("chanceType"));

        tvItemList.setItems(olItemList);
        tvItemList.getColumns().add(ItemCol);
        tvChancelist.setItems(olChanceList);
        tvChancelist.getColumns().add(ChanceCol);

        // Layout setup
        StackPane layout = new StackPane();
        // Calculate Button formatting
        layout.setAlignment(bCalculate, Pos.BOTTOM_RIGHT);
        layout.setMargin(bCalculate,new Insets(4,4,4,4));

        // Total/Indiv Points Text formatting
        layout.setAlignment(tfTotalPTS, Pos.BOTTOM_LEFT);
        layout.setMargin(tfTotalPTS, new Insets(0,0,26,100));
        layout.setAlignment(tTotalPTS, Pos.BOTTOM_LEFT);
        layout.setMargin(tTotalPTS, new Insets(0,0,33,4));

        layout.setAlignment(tvItemList, Pos.TOP_LEFT);
        layout.setAlignment(tvChancelist, Pos.TOP_RIGHT);
        // Add components to layout
        layout.getChildren().addAll(bCalculate, tfTotalPTS, tTotalPTS, tvItemList);
        layout.getChildren().add(tvChancelist);

        // Scene setup
        Scene scene = new Scene(layout, 300, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Handle calculate button click
    @Override
    public void handle(ActionEvent event)
    {
        if(event.getSource() == bCalculate)
        {
            int userTotal;

            if(isParsable(tfTotalPTS.getText()))
            {
                userTotal = Integer.parseInt(tfTotalPTS.getText());
            }
            else
            {
                tfTotalPTS.setText("Error");
                return;
            }

            // Calculate Button setup
            bCalculate = new Button();
            bCalculate.setText("Calculate");
            bCalculate.setPrefHeight(55);
            bCalculate.setPrefWidth(100);
            bCalculate.setOnAction(this);

            // Total Points Text setup
            tfTotalPTS = new TextField();
            tfTotalPTS.setMaxWidth(65);
            tTotalPTS = new Text("Total Raid Points:");

            // Item ObservableList setup
            olItemList = FXCollections.observableArrayList(
                    new Item("Any drop"),
                    new Item("Dex scroll"),
                    new Item("Arcane scroll"),
                    new Item("Dragon sword"),
                    new Item("Dragon harpoon"),
                    new Item("Dragon thrownaxe x100"),
                    new Item("Twisted buckler"),
                    new Item("Dragon hunter crossbow"),
                    new Item("Dinh's bulwark"),
                    new Item("Ancestral hat"),
                    new Item("Ancestral robe top"),
                    new Item("Ancestral robe bottom"),
                    new Item("Dragon claws"),
                    new Item("Elder Maul"),
                    new Item("Kodai insignia"),
                    new Item("Twisted Bow"));

            olChanceList = raidCalc(userTotal);

            // Item TableView setup
            tvItemList = new TableView<>();
            tvItemList.setEditable(false);
            tvItemList.setMinHeight(410);
            tvItemList.setMaxHeight(410);
            tvItemList.setMinWidth(226);
            tvItemList.setMaxWidth(226);

            tvChancelist = new TableView<>();
            tvChancelist.setEditable(false);
            tvChancelist.setMinHeight(410);
            tvChancelist.setMaxHeight(410);
            tvChancelist.setMinWidth(90);
            tvChancelist.setMaxWidth(90);

            ItemCol = new TableColumn("Item");
            ItemCol.setSortable(false);
            ItemCol.setMinWidth(224);
            ItemCol.setMaxWidth(224);
            ItemCol.setResizable(false);
            ItemCol.setCellValueFactory(
                    new PropertyValueFactory<>("itemType"));

            ChanceCol = new TableColumn("Chance (%)");
            ChanceCol.setSortable(false);
            ChanceCol.setMinWidth(88);
            ChanceCol.setMaxWidth(88);
            ChanceCol.setResizable(false);
            ChanceCol.setStyle("-fx-alignment: CENTER-RIGHT;");
            ChanceCol.setCellValueFactory(
                    new PropertyValueFactory<>("chanceType"));

            tvItemList.setItems(olItemList);
            tvItemList.getColumns().add(ItemCol);
            tvChancelist.setItems(olChanceList);
            tvChancelist.getColumns().add(ChanceCol);

            // Layout setup
            StackPane layout = new StackPane();
            // Calculate Button formatting
            layout.setAlignment(bCalculate, Pos.BOTTOM_RIGHT);
            layout.setMargin(bCalculate,new Insets(4,4,4,4));

            // Total/Indiv Points Text formatting
            layout.setAlignment(tfTotalPTS, Pos.BOTTOM_LEFT);
            layout.setMargin(tfTotalPTS, new Insets(0,0,26,100));
            layout.setAlignment(tTotalPTS, Pos.BOTTOM_LEFT);
            layout.setMargin(tTotalPTS, new Insets(0,0,33,4));

            layout.setAlignment(tvItemList, Pos.TOP_LEFT);
            layout.setAlignment(tvChancelist, Pos.TOP_RIGHT);
            // Add components to layout
            layout.getChildren().addAll(bCalculate, tfTotalPTS, tTotalPTS, tvItemList);
            layout.getChildren().add(tvChancelist);

            // Scene setup
            Scene scene = new Scene(layout, 300, 480);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static boolean isParsable(String test)
    {
        boolean parsable = true;
        int tooFewPoints;
        try{
            tooFewPoints = Integer.parseInt(test);
            if(tooFewPoints < 300){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e){
            parsable = false;
        }
        return parsable;
    }

    public static ObservableList<Chance> raidCalc(double totalPoints)
    {
        double m1 = totalPoints / 7125.0;
        ObservableList<Chance> retList = FXCollections.observableArrayList(
                new Chance(String.format("%2.4f %%", m1)),
                new Chance(String.format("%2.4f %%", (m1) * (20.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (20.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (5.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (5.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (5.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (4.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (4.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (3.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (3.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (3.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (3.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (3.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (2.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (2.0 / 84.0))),
                new Chance(String.format("%2.4f %%", (m1) * (2.0 / 84.0))));

        return retList;
    }
}

