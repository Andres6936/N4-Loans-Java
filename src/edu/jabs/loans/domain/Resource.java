package edu.jabs.loans.domain;

/**
 * Resource that is loaned out to the students from the university loans center
 */
public class Resource
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Indicates whether a resource is on loan
     */
    public static final int ON_LOAN = 0;

    /**
     * Indicates whether a resource is available
     *
     */
    public static final int AVAILABLE = 1;

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * id number for the resource
     */
    private int id;

    /**
     * Name of the resource
     */
    private String name;

    /**
     * Indicates if the resource is available or on loan
     */
    private int state;

    /**
     * the student that has the resource on loan, if no student has it on loan, the value is null
     */
    private Student student;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates a new resource with the given name and id address<br>
     * <b>post: </b> the resource is created with the given name and id. the student is null
     * @param aName The name of the resource. aName != null and aName != "".
     * @param anId the id number of the resource. anId is a valid id.
     */
    public Resource( String aName, int anId )
    {
        name = aName;
        id = anId;
        student = null;
        state = AVAILABLE;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the name of the resource
     * @return name of the resource.
     */
    public String getName( )
    {
        return name;
    }

    /**
     * Returns the id number of the resource
     * @return id number of the resource.
     */
    public int getId( )
    {
        return id;
    }

    /**
     * Indicates if the status is available
     * @return true if available, false otherwise.
     */
    public boolean isAvailable( )
    {
        return state == AVAILABLE;
    }

    /**
     * Returns the student that has the resource on loan
     * @return student that has the resource on loan, if the resource is available, return null.
     */
    public Student getStudent( )
    {
        return student;
    }

    /**
     * Loans the resource to the given student <br>
     * <b>post: </b> The resource is on loan to the given student
     * @param aStudent the student that is getting the resource on loan. aStudent != null.
     * @throws Exception if the resource is already on loan.
     */
    public void loan( Student aStudent ) throws Exception
    {
        if( state == ON_LOAN )
        {
            throw new Exception( "The resource is already on loan" );
        }
        state = ON_LOAN;
        student = aStudent;
    }

    /**
     * Returns the resource that is on loan
     * @throws Exception if the resource is not on loan
     */
    public void returnResource( ) throws Exception
    {
        if( state == AVAILABLE )
        {
            throw new Exception( "The resource is not on loan" );
        }
        state = AVAILABLE;
        student = null;
    }
}
