import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.plaf.ColorUIResource;
// ALL VIEW OBJECTS
public class ElectronicStoreView extends Pane {
    private ListView<String>    Popular_list;
    private ListView<String>    Store_list;
    private ListView<String>    Current_cart_list;
    private TextField           number_of_sales_box;
    private TextField           revenue_box;
    private TextField           sale_box;
    private Button              reset_store_button;
    private Button              add_to_cart_button;
    private Button          remove_from_cart_button;
    private Button      complete_sale_button;
    private Label       store_summary_label;
    private Label       number_of_sale_label;
    private Label       revenue_label;
    private Label       dollar_per_sale_label;
    private Label       most_popular_item_label;
    private Label       store_stock_label;
    private Label       current_cart_label;
    private Label       current_cart_amount;

    // GETTER METHODS:
    public ListView<String> getPopular_list() {return Popular_list;}
    public ListView<String> getStore_list() {return Store_list;}
    public ListView<String> getCurrent_cart_list() {return Current_cart_list;}
    public TextField getNumber_of_sales_box() {return number_of_sales_box;}
    public TextField getRevenue_box() {return revenue_box;}
    public TextField getSale_box() {return sale_box;}
    public Button getReset_store_button() {return reset_store_button;}
    public Button getAdd_to_cart_button() {return add_to_cart_button;}
    public Button getRemove_from_cart_button() {return remove_from_cart_button;}
    public Button getComplete_sale_button() {return complete_sale_button;}
    public Label getStore_summary_label() {return store_summary_label;}
    public Label getNumber_of_sale_label() {return number_of_sale_label;}
    public Label getRevenue_label() {return revenue_label;}
    public Label getDollar_per_sale_label() {return dollar_per_sale_label;}
    public Label getMost_popular_item_label() {return most_popular_item_label;}
    public Label getStore_stock_label() {return store_stock_label;}
    public Label getCurrent_cart_label() {return current_cart_label;}
    public Label getCurrent_cart_amount() {return current_cart_amount;}
    // MAIN CONSTRUCTOR CODE
    public ElectronicStoreView() {
        // CREATE THE LIST VIEWS
        Popular_list = new ListView<String>();
        Store_list = new ListView<String>();
        Current_cart_list = new ListView<String>();
        // CREATE THE TEXT BOXES
        number_of_sales_box = new TextField();
        revenue_box = new TextField();
        sale_box = new TextField();
        // CREATE THE BUTTONS
        reset_store_button = new Button();
        add_to_cart_button = new Button();
        remove_from_cart_button = new Button();
        complete_sale_button  = new Button();
        // CREATE THE LABELS
        store_summary_label = new Label();
        number_of_sale_label = new Label();
        revenue_label = new Label();
        dollar_per_sale_label = new Label();
        most_popular_item_label = new Label();
        store_stock_label = new Label();
        current_cart_label = new Label();
        current_cart_amount = new Label();
        // RESIZE AND RELOCATE THE LIST VIEWS
        Popular_list.relocate(10,190);
        Popular_list.setPrefSize(150, 150);

        Store_list.relocate(170,40);
        Store_list.setPrefSize(305,300);

        Current_cart_list.relocate(485,40);
        Current_cart_list.setPrefSize(305,300);
        // RESIZE AND RELOCATE THE TEXT BOXES
        number_of_sales_box.relocate(70,40);
        revenue_box.relocate(70,75);
        sale_box.relocate(70,110);

        number_of_sales_box.setPrefSize(90,15);
        revenue_box.setPrefSize(90,15);
        sale_box.setPrefSize(90,15);
        // RESIZE AND RELOCATE THE BUTTONS
        reset_store_button.relocate(20, 350);
        add_to_cart_button.relocate(249, 350);
        remove_from_cart_button.relocate(484,350);
        complete_sale_button.relocate(638,350);

        reset_store_button.setPrefSize(128,40);
        add_to_cart_button.setPrefSize(153,40);
        remove_from_cart_button.setPrefSize(153,40);
        complete_sale_button.setPrefSize(153,40);
        // RENAME THE BUTTONS
        reset_store_button.setText("Reset Store");
        add_to_cart_button.setText("Add to Cart");
        remove_from_cart_button.setText("Remove from Cart");
        complete_sale_button.setText("Complete Sale");
        // RENAME THE LABELS
        store_summary_label.setText("Store Summary: ");
        number_of_sale_label.setText("  # Sales:  ");
        revenue_label.setText("Revenue:  ");
        dollar_per_sale_label.setText("  $ / Sale:  ");
        most_popular_item_label.setText("Most Popular Items: ");
        store_stock_label.setText(" Store Stock:");
        current_cart_amount.setText("");
        current_cart_label.setText("Current Cart ($" + current_cart_amount.getText() + "):");
        // ALIGN AND RELOCATE THE LABELS
        store_summary_label.setTextAlignment(TextAlignment.LEFT);
        number_of_sale_label.setTextAlignment(TextAlignment.LEFT);
        revenue_label.setTextAlignment(TextAlignment.LEFT);
        dollar_per_sale_label.setTextAlignment(TextAlignment.LEFT);
        most_popular_item_label.setTextAlignment(TextAlignment.CENTER);
        store_stock_label.setTextAlignment(TextAlignment.CENTER);
        current_cart_label.setTextAlignment(TextAlignment.CENTER);

        store_summary_label.relocate(40,20);
        number_of_sale_label.relocate(10,43);
        revenue_label.relocate(10,78);
        dollar_per_sale_label.relocate(10,113);
        most_popular_item_label.relocate(35,170);
        store_stock_label.relocate(296,20);
        current_cart_label.relocate(607, 20);
        // ADD ALL OBJECTS TO PANE
        getChildren().addAll(Popular_list, Store_list,Current_cart_list,
                sale_box,revenue_box, number_of_sales_box,
                reset_store_button, add_to_cart_button,
                remove_from_cart_button,complete_sale_button,
                store_summary_label, number_of_sale_label,
                revenue_label, dollar_per_sale_label,
                most_popular_item_label,store_stock_label,
                current_cart_label, current_cart_amount);
        setPrefSize(780, 380);

    }
    public void update(ElectronicStore store) {
        ArrayList<String> store_stock_string = store.stock_String_list();
        ArrayList<String> current_cart_string = store.current_cart_list();
        ArrayList<String> top_3_string = store.convert_product_to_string_list(store.Top3PopularProducts());

        // UPDATE THE STORE STOCK
        this.Store_list.setItems(FXCollections.observableArrayList(store_stock_string));

        // UPDATE THE CURRENT CART
        this.Current_cart_list.setItems((FXCollections.observableArrayList(current_cart_string)));

        // UPDATE THE STORE TOP 3
        this.Popular_list.setItems(FXCollections.observableArrayList(top_3_string));

        // SET DEFAULT VALUES FOR EMPTY TEXT FIELDS, OR MODEL VALUES
        String sales_num_text = this.number_of_sales_box.getText();
        if (sales_num_text.isEmpty()) {
            this.number_of_sales_box.setText("0");
        } else {
            this.number_of_sales_box.setText(store.getSales() + "");
        }

        String revenue_text = this.revenue_box.getText();
        if (revenue_text.isEmpty()) {
            this.revenue_box.setText("0.00");
        } else {
            this.revenue_box.setText(store.getRevenue() + "");
        }

        String dollar_per_sale_text = this.sale_box.getText();
        if (dollar_per_sale_text.isEmpty()) {
            this.sale_box.setText("N/A");
        } else {
            this.sale_box.setText(store.getAveragesale() + "");
        }

        // SET DEFAULT VALUE FOR EMPTY LIST FOR CURRENT CART VIEWS
        if (this.Current_cart_list.getItems().isEmpty()) {
            ArrayList<String> empty_list = new ArrayList<String>();
            empty_list.add("Nothing in cart. Added products will be showed here.");
            this.Current_cart_list.setItems(FXCollections.observableArrayList(empty_list));
        }

        // UPDATE THE CURRENT CART TOTAL AMOUNT
        String current_cart_amount_text = this.current_cart_amount.getText();
        if (current_cart_amount_text.isEmpty()) {
            this.current_cart_amount.setText("0.00");
        } else {
            this.current_cart_amount.setText(store.getCurrent_cart_total() + "");
        }

        // GET THE ORIGINAL CART AMOUNT LABEL OUT OF THE SCREEN
        this.current_cart_amount.relocate(-50, -50);
        current_cart_label.setText("Current Cart ($" + current_cart_amount.getText() + "):");

        // SET THE SELECTED INDEX TO ALWAYS BE NOTHING, SET TO -1
        this.add_to_cart_button.setDisable(true);

        // SET THE BUTTONS TO BE DISABLED WHEN STARTING THE APPLICATION
        this.getRemove_from_cart_button().setDisable(true);
        this.getComplete_sale_button().setDisable(true);
    }

    // CLEAR THE TOTAL PRODUCTS PRICE FROM THE CART
    public void ClearCartAmount() {
        // SET TO EMPTY STRING TO RESET
        this.current_cart_amount.setText("");
    }
}
