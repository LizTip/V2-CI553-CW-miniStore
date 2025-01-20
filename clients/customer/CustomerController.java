package clients.customer;

/**
 * The Customer Controller
 */

public class CustomerController
{
  private clients.customer.CustomerModel model = null;
  private clients.customer.CustomerView view  = null;

  /**
   * Constructor
   * @param model The model
   * @param view  The view from which the interaction came
   */
  public CustomerController(clients.customer.CustomerModel model, clients.customer.CustomerView view )
  {
    this.view  = view;
    this.model = model;
  }

  /**
   * Check interaction from view
   * @param pn The product number to be checked
   * @param quantity The quantity to check
   */
  public void doCheck( String pn, int quantity )
  {
    model.doCheck(pn, quantity);
  }

  public void doCheckByName( String name ){
    clients.customer.NameToNumber nameToNumber = new clients.customer.NameToNumber();
    String pn = nameToNumber.getNumberByName(name);
    model.doCheck(pn, 1); // Default quantity of 1 for name-based checks
  }

  /**
   * Clear interaction from view
   */
  public void doClear()
  {
    model.doClear();
  }
}