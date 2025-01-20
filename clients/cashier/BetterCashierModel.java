package clients.cashier;

import catalogue.BetterBasket;
import middle.MiddleFactory;

/**
 * An enhanced version of the {@link CashierModel} that integrates
 * the {@link BetterBasket} class for advanced basket management,
 * such as merging and sorting products.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Uses {@link BetterBasket} to improve basket functionality.</li>
 *   <li>Maintains compatibility with the existing {@link CashierModel} interface.</li>
 * </ul>
 *
 * @see CashierModel
 * @see BetterBasket
 * @author Liz Tipper
 * @version 1.0
 */

public class BetterCashierModel  extends CashierModel{
    /**
     * Construct the model of the Cashier
     *
     * @param mf The factory to create the connection objects
     */
public BetterCashierModel(MiddleFactory mf) {
        super(mf);
    }

    /**
     * Overrides the {@code makeBasket} method to return an instance of
     * {@link BetterBasket}, enabling advanced basket management features.
     *
     * @return A new instance of {@link BetterBasket}.
     */

    @Override
    protected BetterBasket makeBasket()
    {
        return new BetterBasket();
    }
}
