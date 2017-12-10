package edu.jabs.loans.domain;

import java.util.ArrayList;

/**
 * Loans center for the university
 */
public class LoansCenter
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The list of students that can get resources on loan from the university
     */
    private ArrayList students;

    /**
     * The list of resources that the university has available
     */
    private ArrayList resources;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates the loans center with no resources or students.  <br>
     * <b>post: </b> the loans center has no resources or students.
     */
    public LoansCenter( )
    {
        students = new ArrayList( );
        resources = new ArrayList( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Registers a loan for a student <br>
     * <b>post: </b> the resources' status is changed to on loan and the resource is added to the list of loans of the student that took it.
     * @param studentId id of the student getting the resource on loan. studentId must be registered.
     * @param resourceId resource id that is to be loaned. resourceId must be registered.
     * @throws Exception if the resource is already on loan.
     */
    public void loanResource( int studentId, int resourceId ) throws Exception
    {
        Resource resource = findResource( resourceId );

        if( !resource.isAvailable( ) )
        {
            throw new Exception( "The resource is already on loan" );
        }

        Student student = findStudent( studentId );
        resource.loan( student );
        student.addResourceToLoans( resource );

    }

    /**
     * Returns the resource with the given id <br>
     * <b>post: </b> The resource status is available and the resource is removed from the list of loans of the student that has it.
     * @param resourceId id for the resource to be returned. resourceId must be registered.
     * @throws Exception if the resource is not on loan
     */
    public void returnResource( int resourceId ) throws Exception
    {
        Resource resource = findResource( resourceId );
        if( resource.isAvailable( ) )
        {
            throw new Exception( "The resource is not currently on loan" );
        }

        Student student = resource.getStudent( );

        resource.returnResource( );
        student.returnResource( resourceId );
    }

    /**
     * Finds a student, given his code.
     * @param theId id of the student were trying to find.
     * @return the student with that id, if he's not found, return null.
     */
    public Student findStudent( int theId )
    {
        int counter = 0;
        boolean found = false;
        Student student = null;

        while( counter < students.size( ) && !found )
        {
            student = ( Student )students.get( counter );
            if( student.getId( ) == theId )
            {
                found = true;
            }
            counter++;
        }

        if( found )
            return student;
        else
            return null;
    }

    /**
     * Finds a resource given its id
     * @param resourceId id of the resource were looking for.
     * @return the resource, if its not found return null.
     */
    public Resource findResource( int resourceId )
    {
        int counter = 0;
        boolean found = false;
        Resource resource = null;
        while( counter < resources.size( ) && !found )
        {
            resource = ( Resource )resources.get( counter );
            if( resource.getId( ) == resourceId )
            {
                found = true;
            }
            counter++;
        }
        if( found )
            return resource;
        else
            return null;
    }

    /**
     * Adds a new student to the loans center <br>
     * <b>post: </b> The student is added to the list of registered students in the loans center.
     * @param name Name of the new student. name != null and name !="".
     * @param theId id of the new student. theId is a valid id number
     * @throws Exception if the id already exists on the list.
     */
    public void addStudent( String name, int theId ) throws Exception
    {
        // verifies that there is no student with that id already
        if( studentExists( theId ) )
        {
            // if it exists, throw an exception
            throw new Exception( "The student id already exists in the system" );
        }
        // if the id doesn't exist, the student is added to the list
        students.add( new Student( name, theId ) );
    }

    /**
     * Adds a new resource to the loans center. <br>
     * <b>post: </b> a new resource is added to the list of resources in the loans center.
     * @param name Name of the new resource. name != null and name !="".
     * @param theId Id of the new resource. theId is a valid id number.
     * @throws Exception if the id is already registered.
     */
    public void addResource( String name, int theId ) throws Exception
    {
        // Checks to see if the id already exists on the system
        if( resourceExists( theId ) )
        {
            // if it does, throw exception
            throw new Exception( "the code for that resource already exists in the system" );
        }
        // if it doesn't exist the resource is added to the loans center resource list
        resources.add( new Resource( name, theId ) );
    }

    /**
     * Returns the list of available resources in the list center
     * @return list of available resources.
     */
    public ArrayList getAvailableResources( )
    {
        int counter = 0;
        ArrayList available = new ArrayList( );

        // runs thru all the resources and returns the ones available
        while( counter < resources.size( ) )
        {
            Resource resource = ( Resource )resources.get( counter );
            if( resource.isAvailable( ) )
            {
                available.add( resource );
            }
            counter++;
        }
        return available;
    }

    /**
     * Returns the list of resources on loan from the loans center
     * @return list of resources on loan.
     */
    public ArrayList getResourcesOnLoan( )
    {
        int counter = 0;
        ArrayList loans = new ArrayList( );

        // runs thru all the resources and returns the ones on loan
        while( counter < resources.size( ) )
        {
            Resource resource = ( Resource )resources.get( counter );
            if( !resource.isAvailable( ) )
            {
                loans.add( resource );
            }
            counter++;
        }
        return loans;
    }

    /**
     * Returns the list of students in the system
     * @return list of students.
     */
    public ArrayList getStudents( )
    {
        return students;
    }

    /**
     * Indicates if the resource is already registered.
     * @param theId id of the resource that were looking for.
     * @return true if the resource exists, false otherwise.
     */
    public boolean resourceExists( int theId )
    {
        return findResource( theId ) != null;
    }

    /**
     * Indicates if the student is already registered.
     * @param theId id of the student that were looking for.
     * @return true if the student exists, false otherwise.
     */
    public boolean studentExists( int theId )
    {
        return findStudent( theId ) != null;
    }

    // -----------------------------------------------------------------
    // Extension points
    // -----------------------------------------------------------------

    /**
     * Method for extension 1
     * @return answer 1
     */
    public String method1( )
    {
        return "Answer 1";
    }

    /**
     * Method for extension 1
     * @return answer 2
     */
    public String method2( )
    {
        return "Answer 2";
    }
}