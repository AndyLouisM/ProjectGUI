//Base class for all products the store will sell
public abstract class Product {
    private double price;

    private int stockQuantity;
    private int soldQuantity;

    // ADDED A SELLING ATTRIBUTE TO KNOW HOW MANY UNITS WERE SOLD
    private int units_sold;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
        units_sold = 0;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getPrice() {
        return price;
    }
    // ADDED GETTER
    public int getUnits_sold() {return units_sold;}
    // ADDED SETTER
    public void setStockQuantity(int stockQuantity) {this.stockQuantity = stockQuantity;}

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)

    // MODIFIED SELLUNITS METHOD TO INCLUDE UNITS SOLD ATTRIBUTE
    public double sellUnits(int amount) {
        if (amount > 0 && stockQuantity >= amount) {
            stockQuantity -= amount;
            soldQuantity += amount;
            units_sold += amount;
            return price * amount;
        }
        return 0.0;
    }

}
