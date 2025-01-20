package clients.cashier;

/**
 * The Cashier Controller acts as the intermediary between the
 * {@link CashierView} and {@link CashierModel}. It handles user interactions
 * from the view and delegates them to the model for processing.
 *
 * <h2>Key Responsibilities:</h2>
 * <ul>
 *   <li>Handles "Check," "Buy," and "Bought/Pay" actions triggered by the view.</li>
 *   <li>Maintains separation of concerns by ensuring the view and model do not interact directly.</li>
 * </ul>
 *
 * @see CashierView
 * @see CashierModel
 * @author
 * Liz Tipper
 * @version 1.0
 */

public class CashierController
{
  private CashierModel model = null; // The model managing the business logic
  private CashierView  view  = null; // The view capturing user interactions

  /**
   * Constructs the Cashier Controller, linking the provided model and view.
   *
   * @param model The {@link CashierModel} responsible for the business logic.
   * @param view  The {@link CashierView} capturing user input and displaying output.
   */
  public CashierController( CashierModel model, CashierView view )
  {
    this.view  = view;
    this.model = model;
  }

  /**
   * Handles the "Check" action from the view, verifying the availability
   * of a product in the stock system.
   *
   * @param pn          The product number to be checked.
   * @param buyQuantity The quantity of the product requested by the user.
   */
  public void doCheck( String pn, int buyQuantity )
  {
    model.doCheck(pn, buyQuantity);
  }

  /**
   * Handles checking a product by its name instead of product number.
   * This method converts the product name to its corresponding number
   * before checking availability.
   *
   * @param name The name of the product to be checked
   */
  public void doCheckByName(String name) {
    clients.customer.NameToNumber nameToNumber = new clients.customer.NameToNumber();
    String pn = nameToNumber.getNumberByName(name);
    model.doCheck(pn, 1);  // Default quantity of 1 for name-based checks
  }

  /**
   * Handles the "Buy" action from the view, processing the purchase of
   * the currently selected product.
   */
  public void doBuy()
  {
    model.doBuy();
  }

  /**
   * Handles the "Bought/Pay" action from the view, finalising the purchase
   * and clearing the current basket.
   */
  public void doBought()
  {
    model.doBought();
  }
}