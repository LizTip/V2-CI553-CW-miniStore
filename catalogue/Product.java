package catalogue;

/**
 * Represents a product in the catalogue, storing details such as product number,
 * description, price, and stock quantity. Implements {@link Comparable} to allow
 * sorting based on the product number.
 *
 * <h2>Key Attributes:</h2>
 * <ul>
 *   <li>Product Number</li>
 *   <li>Description</li>
 *   <li>Price</li>
 *   <li>Quantity</li>
 * </ul>
 *
 * @author  Mike Smith University of Brighton
 * @version 2.0
 */

public class Product implements Comparable<Product>
{
  private static final long serialVersionUID = 20092506;
  private String theProductNum;       // Product number
  private String theDescription;      // Description of product
  private double thePrice;            // Price of product
  private int    theQuantity;         // Quantity involved

  /**
   * Construct a product details
   * @param aProductNum Product number
   * @param aDescription Description of product
   * @param aPrice The price of the product
   * @param aQuantity The Quantity of the product involved
   */
  public Product( String aProductNum, String aDescription,
                  double aPrice, int aQuantity )
  {
    theProductNum  = aProductNum;     // Product number
    theDescription = aDescription;    // Description of product
    thePrice       = aPrice;          // Price of product
    theQuantity    = aQuantity;       // Quantity involved
  }

  /**
   * Retrieves the product number.
   *
   * @return The product number.
   */
  
  public String getProductNum()  { return theProductNum; }
  /**
   * Retrieves the product description.
   *
   * @return The product description.
   */
  public String getDescription() { return theDescription; }

  /**
   * Retrieves the product price.
   *
   * @return The price of the product.
   */
  public double getPrice()       { return thePrice; }
  /**
   * Retrieves the product quantity.
   *
   * @return The quantity of the product.
   */
  public int    getQuantity()    { return theQuantity; }

  /**
   * Updates the product number.
   *
   * @param aProductNum The new product number.
   */
  public void setProductNum( String aProductNum )
  { 
    theProductNum = aProductNum;
  }

  /**
   * Updates the product description.
   *
   * @param aDescription The new product description.
   */
  public void setDescription( String aDescription )
  { 
    theDescription = aDescription;
  }

  /**
   * Updates the product price.
   *
   * @param aPrice The new price of the product.
   */
  public void setPrice( double aPrice )
  { 
    thePrice = aPrice;
  }

  /**
   * Updates the product quantity.
   *
   * @param aQuantity The new quantity of the product.
   */
  public void setQuantity( int aQuantity )
  { 
    theQuantity = aQuantity;
  }

  /**
   * Compares this product with another product based on their product numbers.
   *
   * @param p1 The product to compare against.
   * @return A negative integer, zero, or a positive integer if this product's
   *         number is less than, equal to, or greater than the specified product's number.
   */
  @Override
  public int compareTo(Product p1) {
    return this.getProductNum().compareTo(p1.getProductNum());
  }
}
