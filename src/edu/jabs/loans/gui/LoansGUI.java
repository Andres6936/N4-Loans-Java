package edu.jabs.loans.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.jabs.loans.domain.LoansCenter;
import edu.jabs.loans.domain.Resource;
import edu.jabs.loans.domain.Student;

/**
 * Main window for the application
 */
public class LoansGUI extends JFrame
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * the loans center
     */
    private LoansCenter loansCenter;

    /**
     * Resources image pane
     */
    private ResourcesImagePane resourcesImagePane;

    /**
     * Students image pane
     */
    private StudentsImagePane studentsImagePane;

    /**
     * Extension image pane
     */
    private OptionsImagePane optionsImagePane;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates the main window for the application
     */
    public LoansGUI( )
    {
        // builds the loans center
        loansCenter = new LoansCenter( );

        // sets the layout
        setLayout( new BorderLayout( ) );

        // organizes the window
        resourcesImagePane = new ResourcesImagePane( this );
        studentsImagePane = new StudentsImagePane( this );
        optionsImagePane = new OptionsImagePane( this );
        add( resourcesImagePane, BorderLayout.NORTH );
        add( studentsImagePane, BorderLayout.CENTER );
        add( optionsImagePane, BorderLayout.SOUTH );
        setTitle( "Uniandes loans center" );
        setLocation( 300, 50 );
        pack( );
        setResizable( true );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Adds a new student the loans center <br>
     * <b>post: </b> the new student is added to the list of students
     * @param name The student's name. name != null y name != "".
     * @param id the student's id number. id is a valid id number.
     */
    public void addStudent( String name, int id )
    {
        try
        {
            loansCenter.addStudent( name, id );
            studentsImagePane.updateStudents( loansCenter.getStudents( ) );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "New Student", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Adds a new resource to the loans center<br>
     * <b>post: </b> the new resource is added to the list of available resources
     * @param name the name of the resource. name != null and name != "".
     * @param id the resource id number. id is a valid id number.
     */
    public void addResource( String name, int id )
    {
        try
        {
            loansCenter.addResource( name, id );
            resourcesImagePane.updateAvailableResources( loansCenter.getAvailableResources( ) );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "New Resource", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Loans a resource to one of the students. <br>
     * <b>post: </b> the resource stops being available, it is added to the list of resources on loan.
     */
    public void loanResource( )
    {
        // gets the selected values from the list of students and resources
        int resource = resourcesImagePane.getSelectedAvailableResource( );
        int student = studentsImagePane.getSelectedStudent( );

        // checks that the information is correct
        if( resource == -1 )
        {
            JOptionPane.showMessageDialog( this, "You have not selected an available resource", "Loaning of resources", JOptionPane.WARNING_MESSAGE );
            return;
        }
        if( student == -1 )
        {
            JOptionPane.showMessageDialog( this, "You have not selected a student", "Loaning of resources", JOptionPane.WARNING_MESSAGE );
            return;
        }

        // handles the loaning process
        try
        {
            loansCenter.loanResource( student, resource );
            resourcesImagePane.updateAvailableResources( loansCenter.getAvailableResources( ) );
            resourcesImagePane.updateResourcesOnLoan( loansCenter.getResourcesOnLoan( ) );
            studentsImagePane.clearSelection( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Loaning of resources", JOptionPane.ERROR_MESSAGE );
            return;
        }

    }

    /**
     * Returns a given resource <br>
     * <b>post: </b> the resource becomes available again, it is added to the list of available resources.
     * @param resource Resource to be returned. resource is registered in the system.
     */
    public void returnResource( int resource )
    {
        // handles the returning process
        try
        {
            loansCenter.returnResource( resource );
            resourcesImagePane.updateAvailableResources( loansCenter.getAvailableResources( ) );
            resourcesImagePane.updateResourcesOnLoan( loansCenter.getResourcesOnLoan( ) );
            studentsImagePane.clearSelection( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Returning of a resource", JOptionPane.ERROR_MESSAGE );
            return;
        }
    }

    /**
     * Views the student that has a given resource
     * @param resourceId id of the resource to be viewed. resourceId is registered
     */
    public void viewStudentWithResourceOnLoan( int resourceId )
    {

        Resource resource = loansCenter.findResource( resourceId );
        Student student = resource.getStudent( );
        JOptionPane.showMessageDialog( this, "The resource with the id number " + resourceId + " was loaned to the student " + student.getName( ) + " (id " + student.getId( ) + ")", "Student loans", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * CViews the resources that a student has on loan. <br>
     * @param studentId the student's id number. studentId is registered.
     */
    public void viewResourcesOnLoanStudent( int studentId )
    {
        Student student = loansCenter.findStudent( studentId );
        ArrayList loans = student.getLoans( );
        LoansWindow window = new LoansWindow( loans );
        window.setVisible( true );
    }

    // -----------------------------------------------------------------
    // Extension points
    // -----------------------------------------------------------------

    /**
     * Executes extension 1
     */
    public void funcReqOption1( )
    {
        String answer = loansCenter.method1( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Executes extension 2
     */
    public void funcReqOption2( )
    {
        String answer = loansCenter.method2( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );

    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Starts the application
     * @param args - parameters for the execution, none are required
     */
    public static void main( String[] args )
    {
        LoansGUI i = new LoansGUI( );
        i.setVisible( true );
    }
}