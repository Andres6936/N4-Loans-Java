package edu.jabs.loans.test;

import java.util.ArrayList;

import edu.jabs.loans.domain.LoansCenter;
import edu.jabs.loans.domain.Resource;
import edu.jabs.loans.domain.Student;

import junit.framework.TestCase;

/**
 * Test class for the loans center
 */
public class LoansCenterTest extends TestCase
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * test loans center
     */
    private LoansCenter center;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Creates a scenario with the new loans center
     */
    private void setupScenario1( )
    {
        center = new LoansCenter( );
    }

    /**
     * Creates a scenario with the new loans center with a student and a resource. then loans the resource
     */
    private void setupScenario2( )
    {
        setupScenario1( );
        try
        {
            center.addResource( "Guitar", 1 );
            center.addStudent( "Diana", 1 );
            center.loanResource( 1, 1 );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }

    }

    /**
     * Creates an additional student and loans 2 resources to her
     */
    private void setupScenario3( )
    {
        setupScenario2( );
        try
        {
            center.addStudent( "Olga", 2 );
            center.addResource( "Flute", 2 );
            center.addResource( "Base", 3 );
            center.loanResource( 1, 2 );
            center.loanResource( 1, 3 );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * tests the add resource method
     */
    public void testLoanResource( )
    {
        setupScenario1( );

        // Adds a resource
        try
        {
            center.addResource( "Guitar", 1 );
        }
        catch( Exception e1 )
        {
            fail( "The resource doesn't exists and should be able to be created" );
        }

        // Adds a student
        try
        {
            center.addStudent( "Diana", 1 );
        }
        catch( Exception e2 )
        {
            fail( "The student doesn't exists and should be able to be created"  );
        }

        // Loans a resource
        try
        {
            center.loanResource( 1, 1 );
        }
        catch( Exception e )
        {
            fail( "the resource should be available for loaning" );
        }

        // tries to loan a resource that is already on loan
        try
        {
            center.loanResource( 1, 1 );
            fail( "the resource is already on loan" );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }

    }

    /**
     * Tests the return resource method
     */
    public void testReturnResource( )
    {
        setupScenario2( );
        // returns a resource on loan
        try
        {
            center.returnResource( 1 );
        }
        catch( Exception e )
        {
            fail( "the resource is on loan and can be returned" );
        }

        // a resource that isn't on loan is returned

        try
        {
            center.returnResource( 1 );
            fail( "the resource is available, and cant be returned" );
        }
        catch( Exception e1 )
        {
            assertTrue( true );
        }

    }

    /**
     * Tests the find resource method
     */
    public void testFindResource( )
    {
        setupScenario2( );

        // tests that the resource is on loan
        Resource r = center.findResource( 1 );
        assertNotNull( "The resource must exist", r );
        assertNotNull( "the resource should be on loan", r.getStudent( ) );
        assertEquals( "the resource should be on loan to Diana", "Diana", r.getStudent( ).getName( ) );

        // the resource is returned
        try
        {
            center.returnResource( 1 );
        }
        catch( Exception e )
        {
            fail( "the resource should be returnable" );
        }

        // tests that the resource is no longer on loan
        Student e = r.getStudent( );
        assertNull( "the resource shouldn't be on loan", e );
        assertTrue( "the resource should be available", r.isAvailable( ) );
    }

    /**
     * PTests the find student method
     */
    public void testFindStudent( )
    {
        setupScenario2( );

        // tests that the student has the resource
        Student e = center.findStudent( 1 );
        assertNotNull( "The student must exist", e );
        assertEquals( "The student must be Diana", "Diana", e.getName( ) );
        assertEquals( "The student has resources", 1, e.getLoans( ).size( ) );
    }

    /**
     * tests that the add student method doesn't allow repeats
     */
    public void testAddExistingStudent( )
    {
        setupScenario1( );
        // Adds a student
        try
        {
            center.addStudent( "Diana", 1 );
        }
        catch( Exception e2 )
        {
            fail( "The student doesnt exist and it must be possible to add him" );
        }

        // Tries to add an existing resource
        try
        {
            center.addStudent( "Olga", 1 );
            fail( "A student already exists with that id and therefore it can not be added to the list" );
        }
        catch( Exception e3 )
        {
            assertTrue( true );
        }
    }

    /**
     * Tests that the add resource method doesn't allow repeats
     */
    public void testAddExistingResource( )
    {
        setupScenario1( );
        // Adds a resource
        try
        {
            center.addResource( "Guitar", 1 );
        }
        catch( Exception e1 )
        {
            fail( "the resource doesnt exist and it should be possible to add it" );
        }

        // tries to add an existing resource
        try
        {
            center.addResource( "Flute", 1 );
            fail( "A resource with that id already exists and therefore it cant be added" );
        }
        catch( Exception e3 )
        {
            assertTrue( true );
        }
    }

    /**
     * tests the get available resources method
     */
    public void testGetAvailableResources( )
    {
        setupScenario3( );
        ArrayList resources = center.getAvailableResources( );
        // checks that there are no available resources
        assertTrue( "The list should be empty", resources.size( ) == 0 );

        // a resource that was on loan is returned
        try
        {
            center.returnResource( 2 );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }

        // checks that the resource appears on the available resource list
        resources = center.getAvailableResources( );
        assertTrue( "there should be a flute on the list", resources.size( ) == 1 && ( ( Resource )resources.get( 0 ) ).getName( ).equals( "Flute" ) );
    }

    /**
     * tests the get resources on loan method
     */
    public void testGetResourcesOnLoan( )
    {
        setupScenario3( );
        ArrayList resources = center.getResourcesOnLoan( );

        // checks that all the resources created are on the list
        assertTrue( "there should be three resources on the list", resources.size( ) == 3 );
        String rec0 = ( ( Resource )resources.get( 0 ) ).getName( );
        String rec1 = ( ( Resource )resources.get( 1 ) ).getName( );
        String rec2 = ( ( Resource )resources.get( 2 ) ).getName( );
        assertTrue( "The Resources should be: Guitar, Flute y Base", rec0.equals( "Guitar" ) && rec1.equals( "Flute" ) && rec2.equals( "Base" ) );

    }

    /**
     * tests the get students method
     */
    public void testGetStudents( )
    {
        setupScenario3( );
        ArrayList students = center.getStudents( );

        // checks that the list contains all the students
        assertTrue( "the loans center should have 2 students registered", students.size( ) == 2 );
        String est0 = ( ( Student )students.get( 0 ) ).getName( );
        String est1 = ( ( Student )students.get( 1 ) ).getName( );
        assertTrue( "The students should be: Olga, Diana", est0.equals( "Diana" ) && est1.equals( "Olga" ) );

    }

    /**
     * Tests the resource exists method
     */
    public void testResourceExists( )
    {
        setupScenario1( );
        // checks that no resource has been created, especially resource1
        boolean exists = center.resourceExists( 1 );
        assertTrue( "The resource doesnt exist", !exists );

        // A resource is created
        try
        {
            center.addResource( "Guitar", 1 );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }

        // checks that the resource has been created correctly and that it exists on the list
        exists = center.resourceExists( 1 );
        assertTrue( "the resource exists", exists );
    }

    /**
     * Tests the student exists method
     */
    public void testStudentExists( )
    {
        setupScenario1( );
        // checks that no student, especially student1, exists on the list

        boolean exists = center.studentExists( 1 );
        assertTrue( "The student does not exist", !exists );

        // A student is created
        try
        {
            center.addStudent( "Diana", 1 );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }

        // checks that the student was created correctly and that it exists on the list
        exists = center.studentExists( 1 );
        assertTrue( "The student exists", exists );
    }

}