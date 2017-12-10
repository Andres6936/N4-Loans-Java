package edu.jabs.loans.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.jabs.loans.domain.Resource;

/**
 * Image pane that displays a student's loans
 */
public class StudentLoansImagePane extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    private static final String OK = "ok";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * The image pane's parent window
     */
    private LoansWindow window;

    // -----------------------------------------------------------------
    // Gui Attributes
    // -----------------------------------------------------------------
    private JList resourceList;
    private JScrollPane resourceScroll;
    private JButton okButton;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates the loans image pane for a student
     * @param theWindow image pane window. theWindow != null.
     */
    public StudentLoansImagePane( LoansWindow theWindow )
    {
        window = theWindow;

        setLayout( new BorderLayout( ) );
        setBorder( BorderFactory.createTitledBorder( "Resources on loan" ) );

        resourceList = new JList( );
        updateLoans( window.getLoans( ) );
        resourceScroll = new JScrollPane( );
        resourceScroll.setPreferredSize( new Dimension( 300, 150 ) );
        resourceScroll.setViewportView( resourceList );
        resourceScroll.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        resourceScroll.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        add( resourceScroll, BorderLayout.CENTER );

        okButton = new JButton( "OK" );
        okButton.setActionCommand( OK );
        okButton.addActionListener( this );
        add( okButton, BorderLayout.SOUTH );
    }

    /**
     * Updates the list of loans for a student
     * @param list of resources. list != null.
     */
    private void updateLoans( ArrayList list )
    {
        String[] resources = new String[list.size( )];
        Resource resource;
        for( int i = 0; i < list.size( ); i++ )
        {
            resource = ( Resource )list.get( i );
            resources[ i ] = resource.getId( ) + "-" + resource.getName( );
        }
        resourceList.setListData( resources );
    }

    /**
     * Executes the actions associated with the image pane buttons <br>
     * <b>post: </b> The action that corresponds to the button that was pressed is executed.
     * @param event The clicking of a button. event != null.
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );

        if( command.equals( OK ) )
        {
            window.accept( );
        }
    }
}