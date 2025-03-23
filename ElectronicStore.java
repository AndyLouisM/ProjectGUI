//Class representing an electronic store
//Has an array of products that represent the items the store can sell

// CHANGED ALL ARRAYS TO ARRAYLIST, FOR EASIER ACCESS AND DELETION OF PRODUCTS
import com.sun.javafx.scene.shape.ArcHelper;

import java.util.*;
import java.util.HashMap;
public class ElectronicStore {
    public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
    private int curProducts;
    private String name;
    private ArrayList<Product> stock; //Array to hold all products
    private double revenue;
    // ADDED A CURRENT CART ARRAY, AN ARRAY OF CURRENTLY SELECTED PRODUCTS
    private ArrayList<Product> current_cart;
    private int sales;
    private double averagesale;
    private double current_cart_total;
    private ArrayList<Product> temporary_removed_products;
    private ArrayList<Product> copy_stock;
    private ArrayList<Product> remove_all_stocks;

    public ElectronicStore(String initName) {
        revenue = 0.0;
        name = initName;
        stock = new ArrayList<Product>();
        curProducts = 0;
        sales = 0;
        averagesale = 0.0;
        current_cart_total = 0.0;
        temporary_removed_products = new ArrayList<Product>();
        copy_stock = new ArrayList<Product>();
        // CURRENT CART ARRAY
        current_cart = new ArrayList<Product>();
        remove_all_stocks = new ArrayList<Product>();
    }

    public String getName() {return name;}

    // ADD A GETTER FOR STOCK, AND CURRENT CART ARRAY, AND OTHER ATTRIBUTES
    public ArrayList<Product> getStock() {return stock;}
    public ArrayList<Product> getCurrent_cart() {return current_cart;}
    public int getSales() {return sales;}
    public double getRevenue() {return revenue;}
    public double getAveragesale() {return averagesale;}
    public double getCurrent_cart_total() {return current_cart_total;}
    public ArrayList<Product> getRemove_all_stocks() {return remove_all_stocks;}


    // ADD A STRING ARRAY WITH ONLY STRINGS OF ALL PRODUCTS METHOD
    public ArrayList<String> stock_String_list() {
        ArrayList<String> stock_string_array = new ArrayList<String>();
        stock_string_array = this.convert_product_to_string_list(this.stock);
        return stock_string_array;
    }

    // ADD A STRING ARRAY METHOD FOR CURRENTLY SELECTED ITEMS IN CART

    public ArrayList<String> current_cart_list(){

        ArrayList<String> current_cart_string_list = new ArrayList<String>();

        HashMap<Product, Integer> productHashmap = new HashMap<>();

        for (Product f: this.current_cart) {
            productHashmap.put(f, 1);
        }

        int counter = 0;
        for (Product f: this.current_cart) {
            counter = 0;
            for (Product h: this.current_cart) {
                if (h.toString().equals(f.toString())) {
                    counter++;
                    productHashmap.put(h, counter);
                }
            }
        }

        for (Product k: productHashmap.keySet())
            current_cart_string_list.add("|" + productHashmap.get(k) + "| " + k.toString());

        return current_cart_string_list;
    }



    // ADD A METHOD TO GET THE TOP 3 MOST POPULAR ITEMS
    public ArrayList<Product> Top3PopularProducts() {
        this.copy_stock = new ArrayList<>(this.stock);
        ArrayList<Product> top_3_results = new ArrayList<Product>();

        int top1 = -1;
        int top2 = -1;
        int top3 = -1;

        for (int c = 0; c < this.stock.size(); c++) {
            Product the_product = stock.get(c);
            if (the_product != null) {
                int unit_sold_of_product = the_product.getUnits_sold();

                if (top1 == -1 || unit_sold_of_product > stock.get(top1).getUnits_sold()) {
                    top3 = top2;
                    top2 = top1;
                    top1 = c;
                } else if (top2 == -1 || unit_sold_of_product > stock.get(top2).getUnits_sold()) {
                    top3 = top2;
                    top2 = c;
                } else if (top3 == -1 || unit_sold_of_product > stock.get(top3).getUnits_sold()) {
                    top3 = c;
                }
            }

        }
        if (top3 != -1) {top_3_results.add(stock.get(top1));}
        if (top2 != -1) {top_3_results.add(stock.get(top2));}
        if (top3 != -1) {top_3_results.add(stock.get(top3));}

        return top_3_results;
    }

    public ArrayList<String> convert_product_to_string_list(ArrayList<Product> array_given) {
        ArrayList<String> string_list = new ArrayList<String>();

        for (Product h: array_given) {
            if (h != null) {
                string_list.add(h.toString());
            }
        }
        return string_list;
    }

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct) {
        if (curProducts < MAX_PRODUCTS) {
            stock.add(newProduct);
            curProducts++;
            return true;
        }
        return false;
    }

    public void addCurrentCart(Product f) {
        if (f != null) {
            int imaginary_stock = f.getStockQuantity();
            if (imaginary_stock > 1) {
                this.current_cart.add(f);
                this.current_cart_total += f.getPrice();
                f.setStockQuantity(imaginary_stock - 1);



            }
            else if (imaginary_stock == 1) {
                this.current_cart.add(f);
                this.remove_all_stocks.add(f);
                this.stock.remove(f);
                this.current_cart_total += f.getPrice();
                f.setStockQuantity(f.getStockQuantity() - 1);



            }
            else {
                // NEVER REACHED ... BUT IN CASE IT DOES, THERE IS NO MORE STOCK
                System.out.println("NO MORE STOCK!");
            }
        }
    }

    public void RemoveProductFromCart(Product d) {



        if (d != null) {
            Product j = this.FindProduct(d);
            this.current_cart.remove(j);
            this.current_cart_total -= j.getPrice();



            if (this.FindStringProduct(j.toString()) == null) {
                this.stock.add(j);
                int original_stock = 0;
                for (Product t: this.remove_all_stocks) {

                    if ((j.toString().equals(t.toString()) || (j.toString().substring(4).equals(t.toString())))) {
                        original_stock = t.getStockQuantity();
                        break;
                    }
                }
                if (j.getStockQuantity() < original_stock) {
                    j.setStockQuantity(j.getStockQuantity() + 1);
                }


            }
            else {

                Product h = this.FindStringProduct(j.toString());
                h.setStockQuantity(h.getStockQuantity() + 1);
            }



        }
        else {

            if (this.FindProduct(d) == null) {
                this.stock.add(this.remove_all_stocks.getLast());
                this.current_cart.remove(this.remove_all_stocks.getLast());
            }

            Product removed_product = this.remove_all_stocks.getLast();
            removed_product.setStockQuantity(removed_product.getStockQuantity() + 1);
            if (removed_product.getStockQuantity() == 0) {
                this.current_cart.remove(removed_product);
            }


        }


        }



    public void sellProduct(String sellthis) {
        for (Product b: this.stock) {
            if (b != null) {
                if (b.toString().equals(sellthis) || b.toString().equals(sellthis.substring(4))) {
                    if (b.getStockQuantity() >= 1) {
                        b.sellUnits(1);
                        this.revenue += b.getPrice();
                        this.sales++;
                        averagesale = (double) (this.revenue / this.sales);
                        this.current_cart.add(b);
                    }
                    if (b.getStockQuantity() == 0) {
                        curProducts--;
                        this.current_cart.add(b);
                        this.RemoveProductFromCart(b);
                    }
                }

            }

        }
    }

    public Product FindProduct(Product h) {
        for (Product w: this.stock) {
            if (h != null) {
                if ((h.toString().equals(w.toString())) || (h.toString().substring(4)).equals(w.toString())) {
                    return w;
                }
            }

        }

        return null;

    }

    public Product FindStringProduct(String h) {
        for (Product w: this.stock) {
            if (h != null) {
                if (w.toString().equals(h) || w.toString().equals(h.substring(4))) {
                    return w;
                }
            }

        }

        return null;

    }


    public void ClearCart() {
        this.current_cart = new ArrayList<Product>();
        this.current_cart_total = 0.0;
    }

    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);

        return store1;

    }
}
