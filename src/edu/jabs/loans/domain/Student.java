package edu.jabs.loans.domain;

import java.util.ArrayList;

/**
 * The student that gets resources on loan from the university loans center
 */
public class Student
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Name of the student
     */
    private String name;

    /**
     * Id number of the student
     */
    private int id;

    /**
     * List of the resources that the student has on loan
     */
    private ArrayList loans;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates a new student with the given name and id <br>
     * <b>post: </b> the student has a name and a code, and no resources on loan
     * @param aName Name of the new student. aName != null and aName != "".
     * @param anId id number of the new student. anId is a valid id number.
     */
    public Student( String aName, int anId )
    {
        name = aName;
        id = anId;
        loans = new ArrayList( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the name of the student.
     * @return name of the student.
     */
    public String getName( )
    {
        return name;
    }

    /**
     * Returns the student id number.
     * @return student id number.
     */
    public int getId( )
    {
        return id;
    }

    /**
     * Returns the list of resources that the student has on loan.
     * @return list of resources that the student has on loan.
     */
    public ArrayList getLoans( )
    {
        return loans;
    }

    /**
     * Adds a new resource to the list of loans<br>
     * <b>post: </b> the new resource is added to the student's list of loans
     * @param newResource New resource to be added to the loans list. newResource != null and is not currently on loan to any other student.
     */
    public void addResourceToLoans( Resource newResource )
    {
        loans.add( newResource );
    }

    /**
     * Removes the given resource from the student's list of loans <br>
     * <b>post: </b> if the resource appeared on the student's loans list, it is removed from that list<br>
     * if the resource doesn't appear on the student's loans list, nothing happens.
     * @param resourceOnLoan id for the resource that is being returned. resourceOnLoan is a valid id.
     */
    public void returnResource( int resourceOnLoan )
    {
        int counter = 0;
        Resource resourceToDelete = null;
        boolean deleted = false;

        // cycle that finds the resource to return
        while( counter < loans.size( ) && !deleted )
        {
            resourceToDelete = ( Resource )loans.get( counter );
            if( resourceToDelete.getId( ) == resourceOnLoan )
            {
                deleted = true;
            }
            counter++;
        }
        if( deleted == true )
        {
            loans.remove( resourceToDelete );
        }
    }
}
