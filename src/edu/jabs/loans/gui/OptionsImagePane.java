package edu.jabs.loans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Presents the extension options for the exercise
 */
public class OptionsImagePane extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    /**
     * The command for option 1
     */
    private final String OPTION_1 = "option 1";

    /**
     * The command for option 2
     */
    private final String OPTION_2 = "option 2";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Main window for the application
     */
    private LoansGUI mainWindow;

    // -----------------------------------------------------------------
    // GUI Attributes
    // -----------------------------------------------------------------
    /**
     * Button for option 1
     */
    private JButton buttonOption1;

    /**
     * Button for option 2
     */
    private JButton buttonOption2;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Creates the image pane with the extension point buttons
     * @param main main window. main != null.
     */
    public OptionsImagePane( LoansGUI main )
    {
        mainWindow = main;

        buttonOption1 = new JButton( "Option 1" );
        buttonOption1.setActionCommand( OPTION_1 );
        buttonOption1.addActionListener( this );
        add( buttonOption1 );

        buttonOption2 = new JButton( "Option 2" );
        buttonOption2.setActionCommand( OPTION_2 );
        buttonOption2.addActionListener( this );
        add( buttonOption2 );

    }
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Executes the actions associated with the image pane buttons<br>
     * <b>post: </b> The action that corresponds to the button that was pressed is executed.
     * @param event The clicking of a button. event != null.
     */
    public void actionPerformed( ActionEvent event )
    {
        String comando = event.getActionCommand( );
        if( OPTION_1.equals( comando ) )
        {
            mainWindow.funcReqOption1( );
        }
        else if( OPTION_2.equals( comando ) )
        {
            mainWindow.funcReqOption2( );
        }
    }
}