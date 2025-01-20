package clients.customer;

import catalogue.Basket;
import catalogue.Product;
import debug.DEBUG;
import middle.MiddleFactory;
import middle.OrderProcessing;
import middle.StockException;
import middle.StockReader;

import javax.swing.*;
import java.util.Observable;

/**
 * Implements the Model of the customer client
 */
public class CustomerModel extends Observable {
    private Product theProduct = null;          // Current product
    private Basket theBasket = null;          // Bought items

    private String pn = "";                    // Product being processed

    private StockReader theStock = null;
    private OrderProcessing theOrder = null;
    private ImageIcon thePic = null;

    /*
     * Construct the model of the Customer
     * @param mf The factory to create the connection objects
     */
    public CustomerModel(MiddleFactory mf) {
        try                                          //
        {
            theStock = mf.makeStockReader();           // Database access
        } catch (Exception e) {
            DEBUG.error("CustomerModel.constructor\n" +
                    "Database not created?\n%s\n", e.getMessage());
        }
        theBasket = makeBasket();                    // Initial Basket
    }

    /**
     * return the Basket of products
     *
     * @return the basket of products
     */
    public Basket getBasket() {
        return theBasket;
    }

    /**
     * Check if the product is in Stock
     *
     * @param productNum The product number
     * @param quantity The quantity to check
     */
    public void doCheck(String productNum, int quantity) {
        theBasket.clear();                          // Clear s. list
        String theAction = "";
        pn = productNum.trim();                    // Product no.
        NameToNumber nameToNumber = new NameToNumber();

        try {
            if (!theStock.exists(pn)) {
                pn = nameToNumber.getNumberByName(productNum);
            }
        } catch (StockException e) {
        } catch (NullPointerException e) {
        }

        try {
            if (theStock.exists(pn))              // Stock Exists?
            {                                     // T
                Product pr = theStock.getDetails(pn); //  Product
                if (pr.getQuantity() >= quantity)    //  In stock?
                {
                    theAction =                      //   Display
                            String.format("%s : %7.2f (%2d) ", //
                                    pr.getDescription(),       //    description
                                    pr.getPrice(),            //    price
                                    pr.getQuantity());        //    quantity
                    pr.setQuantity(quantity);       //   Set requested quantity
                    theBasket.add(pr);             //   Add to basket
                    thePic = theStock.getImage(pn); //    product
                } else {                           //  F
                    theAction =                    //   Inform
                            pr.getDescription() +   //    product not
                                    " not in stock";//    in stock
                }
            } else {                              // F
                theAction =                       //  Inform Unknown
                        "Unknown product number " + productNum;  //  product number
            }
        } catch (StockException e) {
            DEBUG.error("CustomerClient.doCheck()\n%s",
                    e.getMessage());
        }
        setChanged();
        notifyObservers(theAction);
    }

    /**
     * Clear the products from the basket and reset the display.
     * This method:
     * - Clears all products from the basket
     * - Resets the display message
     * - Removes any displayed product image
     * - Notifies observers of the changes
     */
    public void doClear() {
        String theAction = "";
        theBasket.clear();                        // Clear s. list
        theAction = "Enter Product Number";       // Set display
        thePic = null;                            // No picture
        setChanged();
        notifyObservers(theAction);
    }

    /**
     * Return a picture of the product
     *
     * @return An instance of an ImageIcon
     */
    public ImageIcon getPicture() {
        return thePic;
    }

    /**
     * Ask for update of view called at start.
     * Notifies observers with initial state.
     */
    private void askForUpdate() {
        setChanged();
        notifyObservers("START only"); // Notify
    }

    /**
     * Make a new Basket
     *
     * @return an instance of a new Basket
     */
    protected Basket makeBasket() {
        return new Basket();
    }
}