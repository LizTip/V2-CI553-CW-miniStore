package catalogue;

import java.util.Collections;

/**
 * Improved version of the {@link Basket} class with additional
 * functionality for merging and sorting products.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Merges products with the same product number by updating their quantities.</li>
 *   <li>Automatically sorts the basket after adding a new product.</li>
 * </ul>
 *
 * @author Liz Tipper
 * @version 1.2
 */



public class BetterBasket extends Basket {
  /**
   * Adds a product to the basket. If a product with the same product number
   * already exists in the basket, their quantities are merged. The basket
   * is then sorted alphabetically by product number.
   *
   * @param pr The product to be added to the basket.
   * @return {@code true} if the product was added successfully or merged.
   */
  @Override
  public boolean add(Product pr) {
    for (Product prInList : this) {
      if (prInList.getProductNum().equals(pr.getProductNum())) {
        int quantity = pr.getQuantity() + prInList.getQuantity();
        prInList.setQuantity(quantity);
        return (true); // Product quantity updated
      }
    }
    super.add(pr); // Only add if no existing product was found
    Collections.sort(this);
    return (true);
  }
}