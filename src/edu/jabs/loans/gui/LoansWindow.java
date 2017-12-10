package edu.jabs.loans.gui;

import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * Window that displays the resources that a student has on loan
 */
public class LoansWindow extends JFrame
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * Resources on loan
     */
    private ArrayList resourcesOnLoan;
    /**
     * Image pane to display the resources on loan
     */
    private StudentLoansImagePane imagePane;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates the main window for the application
     * @param loans List of resources on loan. loans != null
     */
    public LoansWindow( ArrayList loans )
    {
        resourcesOnLoan = loans;
        imagePane = new StudentLoansImagePane( this );
        add( imagePane );
        setLocation( 300, 50 );
        setTitle( "Resources on loan" );
        pack( );
        setResizable( true );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the list of resources on loan
     * @return list of resources on loan.
     */
    public ArrayList getLoans( )
    {
        return resourcesOnLoan;
    }

    /**
     * runs the ok action
     */
    public void accept( )
    {
        dispose( );
    }
}