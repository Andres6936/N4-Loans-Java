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

import edu.jabs.loans.domain.Student;

/**
 * Image pane with the information of a student
 */
public class StudentsImagePane extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    private static final String ADD = "add";
    private static final String VIEW_LOANS = "view loans";

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------
    /**
     * The applications main window
     */
    private LoansGUI mainWindow;

    // -----------------------------------------------------------------
    // GUI Attributes
    // -----------------------------------------------------------------
    private JList listOfStudents;
    private JScrollPane scrollStudents;
    private JButton addButton;
    private JButton viewButton;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates the students image pane
     * @param gui main window. gui != null
     */
    public StudentsImagePane( LoansGUI gui )
    {
        //Stores the main GUI
        mainWindow = gui;

        //Configures border and layout
        setLayout( new BorderLayout( ) );
        setBorder( BorderFactory.createTitledBorder( "Students" ) );

        //Creates, initializes and organizes the main gui attributes
        listOfStudents = new JList( );
        scrollStudents = new JScrollPane( );
        scrollStudents.setPreferredSize( new Dimension( 300, 150 ) );
        scrollStudents.setBorder( new CompoundBorder( new EmptyBorder( 10, 10, 10, 10 ), new TitledBorder( "Listed" ) ) );
        scrollStudents.setViewportView( listOfStudents );
        scrollStudents.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollStudents.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        add( scrollStudents, BorderLayout.CENTER );

        JPanel panel = new JPanel( );
        panel.setLayout( new GridLayout( 5, 1, 1, 20 ) );
        panel.add( new JLabel( ) );

        addButton = new JButton( "Add" );
        addButton.setActionCommand( ADD );
        addButton.addActionListener( this );
        panel.add( addButton );

        viewButton = new JButton( );
        viewButton.setText( "View Loans" );
        viewButton.setActionCommand( VIEW_LOANS );
        viewButton.addActionListener( this );
        panel.add( viewButton );
        panel.add( new JLabel( "" ) );

        add( panel, BorderLayout.EAST );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the list of students
     * @param list of students. list != null.
     */
    public void updateStudents( ArrayList list )
    {
        String[] students = new String[list.size( )];
        Student student;
        for( int i = 0; i < list.size( ); i++ )
        {
            student = ( Student )list.get( i );
            students[ i ] = student.getId( ) + "-" + student.getName( );
        }
        listOfStudents.setListData( students );
    }

    /**
     * Returns the id number for the selected student
     * @return the student's code.
     */
    public int getSelectedStudent( )
    {
        String student = ( String )listOfStudents.getSelectedValue( );
        if( student == null )
        {
            return -1;
        }
        String info[] = student.split( "-" );
        return Integer.parseInt( info[ 0 ] );
    }

    /**
     * Clears the selection from the list
     */
    public void clearSelection( )
    {
        listOfStudents.clearSelection( );
    }

    /**
     * Handles and executes the actions for the image pane's buttons <br>
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
            String sId = JOptionPane.showInputDialog( this, "Id number", "New Student", JOptionPane.QUESTION_MESSAGE );
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
                JOptionPane.showMessageDialog( this, "Invalid id number", "New Student", JOptionPane.ERROR_MESSAGE );
                return;
            }
            name = JOptionPane.showInputDialog( this, "Name", "New Student", JOptionPane.QUESTION_MESSAGE );
            if( name == null || name.trim( ).equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "The name is required", "New Resource", JOptionPane.ERROR_MESSAGE );
                return;
            }
            mainWindow.addStudent( name, idNumber );
        }
        else if( command.equals( VIEW_LOANS ) )
        {
            // gets the student id number
            int estudiante = getSelectedStudent( );

            // Makes sure that the information is correct
            if( estudiante == -1 )
            {
                JOptionPane.showMessageDialog( this, "You have not selected any student", "Loans report", JOptionPane.WARNING_MESSAGE );
                return;
            }
            mainWindow.viewResourcesOnLoanStudent( estudiante );
        }
    }
}