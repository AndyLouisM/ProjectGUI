import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ElectronicStoreApp extends Application {

    private ElectronicStore model;

    public ElectronicStoreApp() {model = ElectronicStore.createStore();}


    public void start(Stage primaryStage) {
        // CREATE PANE AND VIEW
        Pane pane_1 = new Pane();
        ElectronicStoreView view = new ElectronicStoreView();


        ControllerUpdate(view, model);

        // ADD EVENT HANDLING FOR ADD TO CART
        view.getAdd_to_cart_button().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String product = view.getStore_list().getSelectionModel().getSelectedItem();
                model.addCurrentCart(model.FindStringProduct(product));
                view.getCurrent_cart_list().setItems(FXCollections.observableArrayList(model.convert_product_to_string_list(model.getCurrent_cart())));
                ControllerUpdate(view, model);
            }
        });

        // ADD EVENT HANDLING FOR REMOVING FROM CART
        view.getRemove_from_cart_button().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String product = view.getCurrent_cart_list().getSelectionModel().getSelectedItem();
                int selected_product_before_delete = view.getCurrent_cart_list().getSelectionModel().getSelectedIndex();
                model.RemoveProductFromCart(model.FindStringProduct(product));
                ControllerUpdate(view, model);
                view.getCurrent_cart_list().getSelectionModel().select(selected_product_before_delete);
            }
        });

        // ADD EVENT HANDLING FOR RESETTING THE STORE
        view.getReset_store_button().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model = ElectronicStore.createStore();
                ControllerUpdate(view, model);
            }
        });

        // ADD EVENT HANDLING FOR COMPLETING A PURCHASE FROM THE STORE
        view.getComplete_sale_button().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ArrayList<String> products_to_complete_sale = model.convert_product_to_string_list(model.getCurrent_cart());

                for (String y: products_to_complete_sale) {
                    model.sellProduct(y);
                }
                // CLEAR THE CART ONCE THE SALE IS COMPLETED
                model.ClearCart();
                view.ClearCartAmount();

                view.getCurrent_cart_list().setItems(FXCollections.observableArrayList(new ArrayList<String>()));
                ControllerUpdate(view, model);
            }
        });

        view.getStore_list().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                int seleted_index = view.getStore_list().getSelectionModel().getSelectedIndex();
                if (seleted_index != -1) {
                    view.getAdd_to_cart_button().setDisable(false);
                }
                else {view.getAdd_to_cart_button().setDisable(true);}
            }
        });


        view.getCurrent_cart_list().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                String list_first_element = view.getCurrent_cart_list().getItems().getFirst();

                if (list_first_element.equals("Nothing in cart. Added products will be showed here.")) {
                    view.getRemove_from_cart_button().setDisable(true);
                    view.getComplete_sale_button().setDisable(true);
                }
                else {
                    view.getRemove_from_cart_button().setDisable(false);
                    view.getComplete_sale_button().setDisable(false);
                }
            }
        });













                pane_1.getChildren().addAll(view);
        primaryStage.setTitle("Electronic Store Applicaton");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(pane_1, 800, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {launch(args);}

// UPDATE THE VIEW, USING THE PROVIDED MODEL THROUGH A METHOD
    public void ControllerUpdate(ElectronicStoreView view, ElectronicStore model) {
        view.update(model);
    }

}


