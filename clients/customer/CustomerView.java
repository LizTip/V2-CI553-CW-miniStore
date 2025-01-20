package clients.customer;

import clients.packing.Picture;
import middle.MiddleFactory;
import middle.StockReader;
import clients.customer.CustomerController;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 */

public class CustomerView implements Observer {

  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width of window pixels

  private final JLabel pageTitle = new JLabel();
  private final JLabel theAction = new JLabel();
  private final JTextField theInput = new JTextField();
  private final JTextArea theOutput = new JTextArea();
  private final JScrollPane theSP = new JScrollPane();
  private final JButton theBtCheck = new JButton("Check");
  private final JButton theBtClear = new JButton("Clear");

  private Picture thePicture = new Picture(80, 80);
  private StockReader theStock = null;
  private CustomerController cont = null;

  /**
   * Construct the view
   * @param rpc   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen
   * @param y     y-cordinate of position of window on screen
   */
  public CustomerView(RootPaneContainer rpc, MiddleFactory mf, int x, int y) {
    try {
      theStock = mf.makeStockReader();  // Database Access
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
    }

    Container cp = rpc.getContentPane();
    Container rootWindow = (Container) rpc;
    cp.setLayout(null);
    rootWindow.setSize(W, H);
    rootWindow.setLocation(x, y);

    Font f = new Font("Monospaced", Font.PLAIN, 12);

    pageTitle.setBounds(110, 0, 270, 20);
    pageTitle.setText("Search products");
    pageTitle.setToolTipText("Displays the page title for the search functionality");
    cp.add(pageTitle);

    theInput.setToolTipText("Enter the product number here");
    theInput.setBounds(110, 50, 270, 40);
    cp.add(theInput);

    theAction.setToolTipText("Displays system messages such as search results or errors");
    theAction.setBounds(110, 25, 270, 20);
    theAction.setText(" ");
    cp.add(theAction);

    theBtCheck.setToolTipText("Click to check the product details");
    theBtCheck.setBounds(16, 25 + 60 * 0, 80, 40);
    theBtCheck.addActionListener(e -> {
      if (cont != null) {
        cont.doCheck(theInput.getText(), 1);
      }
    });
    cp.add(theBtCheck);

    theBtClear.setToolTipText("Click to clear the input fields");
    theBtClear.setBounds(16, 25 + 60 * 1, 80, 40);
    theBtClear.addActionListener(e -> {
      if (cont != null) {
        cont.doClear();
      }
    });
    cp.add(theBtClear);

    theSP.setBounds(110, 100, 270, 160);
    theOutput.setText("");
    theOutput.setFont(f);
    cp.add(theSP);
    theSP.getViewport().add(theOutput);

    thePicture.setBounds(16, 25 + 60 * 2 - 10, 80, 80);
    thePicture.setToolTipText("Displays the image of the selected product");
    cp.add(thePicture);
    thePicture.clear();

    rootWindow.setVisible(true);
    theInput.requestFocus();
  }

  public void setController(CustomerController c) {
    cont = c;
  }

  @Override
  public void update(Observable modelC, Object arg) {
    clients.customer.CustomerModel model = (clients.customer.CustomerModel) modelC;
    String message = (String) arg;
    theAction.setText(message);
    ImageIcon image = model.getPicture();
    if (image == null) {
      thePicture.clear();
    } else {
      thePicture.set(image);
    }
    theOutput.setText(model.getBasket().getDetails());
    theInput.requestFocus();
  }
}