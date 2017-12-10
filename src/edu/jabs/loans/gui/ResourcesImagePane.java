package edu.jabs.loans.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import edu.jabs.loans.domain.Resource;

/**
 * Image pane that displays the information for the resources
 */
public class ResourcesImagePane extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------
    private final static String LOAN = "loan";
    private final static String RETURN = "return";
    private static final String ADD = "add";
    private static final String VIEW = "view";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * Main window for the application, parent of the image pane
     */
    private LoansGUI mainWindow;

    //-----------------------------------------------------------------
    // Gui Attributes
    //-----------------------------------------------------------------
    private JList availableResourcesList;
    private JScrollPane availableResourcesScroll;
    private JList resourcesOnLoanList;
    private JScrollPane resourcesOnLoanScroll;
    private JButton addButton;
    private JButton loanButton;
    private JButton returnButton;
    private JButton viewButton;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates the image pane for the resources
     * @param gui Main window. gui != null
     */
    public ResourcesImagePane( LoansGUI gui )
    {
        //stores the main window
        mainWindow = gui;

        //configures border and layout
        setLayout( new BorderLayout( ) );
        setBorder( BorderFactory.createTitledBorder( "Resources" ) );

        //Creates, initializes and organizes the gui attributes
        JPanel listImagePane = new JPanel( );
        listImagePane.setLayout( new BorderLayout( ) );

        availableResourcesList = new JList( );
        availableResourcesScroll = new JScrollPane( );
        availableResourcesScroll.setPreferredSize( new Dimension( 300, 150 ) );
        availableResourcesScroll.setBorder( new CompoundBorder( new EmptyBorder( 10, 10, 10, 10 ), new TitledBorder( "Available" ) ) );
        availableResourcesScroll.setViewportView( availableResourcesList );
        availableResourcesScroll.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        availableResourcesScroll.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        listImagePane.add( availableResourcesScroll, BorderLayout.CENTER );

        resourcesOnLoanList = new JList( );
        resourcesOnLoanScroll = new JScrollPane( );
        resourcesOnLoanScroll.setPreferredSize( new Dimension( 300, 150 ) );
        resourcesOnLoanScroll.setBorder( new CompoundBorder( new EmptyBorder( 10, 10, 10, 10 ), new TitledBorder( "On Loan" ) ) );
        resourcesOnLoanScroll.setViewportView( resourcesOnLoanList );
        resourcesOnLoanScroll.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        resourcesOnLoanScroll.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        listImagePane.add( resourcesOnLoanScroll, BorderLayout.SOUTH );

        JPanel buttonsImagePane = new JPanel( );
        buttonsImagePane.setLayout( new GridLayout( 7, 1, 1, 20 ) );
        buttonsImagePane.add( new JLabel( "" ) );

        addButton = new JButton( "Add" );
        addButton.setActionCommand( ADD );
        addButton.addActionListener( this );
        buttonsImagePane.add( addButton );

        loanButton = new JButton( );
        loanButton.setText( "Loan" );
        loanButton.setActionCommand( LOAN );
        loanButton.addActionListener( this );
        buttonsImagePane.add( loanButton );

        buttonsImagePane.add( new JLabel( "" ) );

        returnButton = new JButton( );
        returnButton.setText( "Return" );
        returnButton.setActionCommand( RETURN );
        returnButton.addActionListener( this );
        buttonsImagePane.add( returnButton );

        viewButton = new JButton( );
        viewButton.setText( "View" );
        viewButton.setActionCommand( VIEW );
        viewButton.addActionListener( this );
        buttonsImagePane.add( viewButton );
        buttonsImagePane.add( new JLabel( "" ) );

        add( listImagePane, BorderLayout.CENTER );
        add( buttonsImagePane, BorderLayout.EAST );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * returns the id number for the resource available for loaning that was selected
     * @return id number for the resource.
     */
    public int getSelectedAvailableResource( )
    {
        String resource = ( String )availableResourcesList.getSelectedValue( );
        if( resource == null )
        {
            return -1;
        }
        String info[] = resource.split( "-" );
        return Integer.parseInt( info[ 0 ] );
    }

    /**
     * returns the id number for the resource on loan that was selected
     * @return id number for the resource.
     */
    public int getSelectedOnLoanResource( )
    {
        String resource = ( String )resourcesOnLoanList.getSelectedValue( );
        if( resource == null )
        {
            return -1;
        }
        String info[] = resource.split( "-" );
        return Integer.parseInt( info[ 0 ] );
    }

    /**
     * Updates the list of available resources
     * @param list list of available resources. list != null
     */
    public void updateAvailableResources( ArrayList list )
    {
        String[] resources = new String[list.size( )];
        Resource resource;
        for( int i = 0; i < list.size( ); i++ )
        {
            resource = ( Resource )list.get( i );
            resources[ i ] = resource.getId( ) + "-" + resource.getName( );
        }
        availableResourcesList.setListData( resources );
    }

    /**
     * Updates the list of resources on loan
     * @param list list of resources on loan. list != null
     */
    public void updateResourcesOnLoan( ArrayList list )
    {
        String[] resources = new String[list.size( )];
        Resource resource;
        for( int i = 0; i < list.size( ); i++ )
        {
            resource = ( Resource )list.get( i );
            resources[ i ] = resource.getId( ) + "-" + resource.getName( );
        }
        resourcesOnLoanList.setListData( resources );
    }

    /**
     * Executes the actions associated with the image pane buttons <br>
     * <b>post: </b> The action that corresponds to the button that was pressed is executed.
     * @param event The clicking of a button. event != null.
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );
        String name;
        int idNumber;

        if( command.equals( ADD ) )
        {
            String sId = JOptionPane.showInputDialog( this, "Id number", "New Resource", JOptionPane.QUESTION_MESSAGE );
            if( sId == null )
            {
                return;
            }
            try
            {
                idNumber = Integer.parseInt( sId );
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "invalid id number", "New Resource", JOptionPane.ERROR_MESSAGE );
                return;
            }
            name = JOptionPane.showInputDialog( this, "Name", "New Resource", JOptionPane.QUESTION_MESSAGE );
            if( name == null || name.trim( ).equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "The name is required", "New Resource", JOptionPane.ERROR_MESSAGE );
                return;
            }
            mainWindow.addResource( name, idNumber );
        }

        else if( command.equals( LOAN ) )
        {
            mainWindow.loanResource( );
        }

        else if( command.equals( RETURN ) )
        {
            //gets the id for the selected resource
            int resource = getSelectedOnLoanResource( );

            // makes sure that the information is correct
            if( resource == -1 )
            {
                JOptionPane.showMessageDialog( this, "you have not selected any of the resources on loan", "Returning of a resource", JOptionPane.WARNING_MESSAGE );
                return;
            }
            mainWindow.returnResource( resource );
        }

        else if( command.equals( VIEW ) )
        {
            // gets the id number for the selected on loan resource
            int recurso = getSelectedOnLoanResource( );

            // checks that the information is correct
            if( recurso == -1 )
            {
                JOptionPane.showMessageDialog( this, "You have not selected a resource on loan", "View of loans", JOptionPane.WARNING_MESSAGE );
                return;
            }
            mainWindow.viewStudentWithResourceOnLoan( recurso );
        }
    }
}