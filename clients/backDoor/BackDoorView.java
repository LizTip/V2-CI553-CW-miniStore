package clients.backDoor;

import middle.MiddleFactory;
import middle.StockReadWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;  // Added for ActionListener interface
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 */

public class BackDoorView implements Observer
{
  private static final String RESTOCK  = "Add";
  private static final String CLEAR    = "Clear";
  private static final String QUERY    = "Query";

  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private final JLabel      pageTitle  = new JLabel();
  private final JLabel      theAction  = new JLabel();
  private final JTextField  theInput   = new JTextField();
  private final JTextField  theInputNo = new JTextField();
  private final JTextArea   theOutput  = new JTextArea();
  private final JScrollPane theSP      = new JScrollPane();
  private final JButton     theBtClear;
  private final JButton     theBtRStock;
  private final JButton     theBtQuery;

  private StockReadWriter theStock     = null;
  private BackDoorController cont= null;

  /**
   * Construct the view
   * @param rpc   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen
   * @param y     y-cordinate of position of window on screen
   */
  public BackDoorView(  RootPaneContainer rpc, MiddleFactory mf, int x, int y )
  {
    try                                             //
    {
      theStock = mf.makeStockReadWriter();          // Database access
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    Container cp         = rpc.getContentPane();    // Content Pane
    Container rootWindow = (Container) rpc;         // Root Window
    cp.setLayout(null);                             // No layout manager
    rootWindow.setSize( W, H );                     // Size of Window
    rootWindow.setLocation( x, y );

    Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is

    pageTitle.setBounds( 110, 0 , 270, 20 );
    pageTitle.setText( "Staff check and manage stock" );
    cp.add( pageTitle );

    // Replace button creation with the createButton method
    theBtQuery = createButton(QUERY, e -> cont.doQuery(theInput.getText()));
    theBtQuery.setBounds( 16, 25+60*0, 80, 40 );
    cp.add(theBtQuery);

    theBtRStock = createButton(RESTOCK, e -> cont.doRStock(theInput.getText(), theInputNo.getText()));
    theBtRStock.setBounds( 16, 25+60*1, 80, 40 );
    cp.add(theBtRStock);

    theBtClear = createButton(CLEAR, e -> cont.doClear());
    theBtClear.setBounds( 16, 25+60*2, 80, 40 );
    cp.add(theBtClear);

    theAction.setBounds( 110, 25 , 270, 20 );       // Message area
    theAction.setText( "" );                        // Blank
    cp.add( theAction );                            //  Add to canvas

    theInput.setBounds( 110, 50, 120, 40 );         // Input Area
    theInput.setText("");                           // Blank
    cp.add( theInput );                             //  Add to canvas

    theInputNo.setBounds( 260, 50, 120, 40 );       // Input Area
    theInputNo.setText("0");                        // 0
    cp.add( theInputNo );                           //  Add to canvas

    theSP.setBounds( 110, 100, 270, 160 );          // Scrolling pane
    theOutput.setText( "" );                        //  Blank
    theOutput.setFont( f );                         //  Uses font
    cp.add( theSP );                                //  Add to canvas
    theSP.getViewport().add( theOutput );           //  In TextArea
    rootWindow.setVisible( true );                  // Make visible
    theInput.requestFocus();                        // Focus is here
  }

  public void setController( BackDoorController c )
  {
    cont = c;
  }

  /**
   * Update the view, called by notifyObservers(theAction) in model,
   * @param modelC   The observed model
   * @param arg      Specific args
   */
  @Override
  public void update( Observable modelC, Object arg )
  {
    BackDoorModel model  = (BackDoorModel) modelC;
    String        message = (String) arg;
    theAction.setText( message );

    theOutput.setText( model.getBasket().getDetails() );
    theInput.requestFocus();
  }

  /**
   * Creates a reusable button with a label, action listener, and tooltip.
   * This helper method standardizes button creation across the view,
   * ensuring consistent appearance and behavior for all buttons.
   *
   * @param label The text displayed on the button
   * @param action The action to perform when the button is clicked
   * @return A configured JButton instance
   */
  private JButton createButton(String label, ActionListener action) {
    JButton button = new JButton(label);
    button.addActionListener(action);
    button.setToolTipText("Click here to " + label.toLowerCase());
    return button;
  }
}