package edu.jabs.loans.test;

import junit.framework.TestCase;

import edu.jabs.loans.domain.Resource;
import edu.jabs.loans.domain.Student;

/**
 * Resource Test class
 */
public class ResourceTest extends TestCase
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The resource for the scenario 1
     */
    private Resource resource1;
    /**
     * The student for the scenario 1
     */
    private Student student1;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Sets test scenario 1 up
     */
    private void setupScenario1( )
    {
        resource1 = new Resource( "Guitar", 1 );
        student1 = new Student( "Diana", 1 );
    }

    /**
     * Tests the loaning and returning of a resource. Also makes sure that the actions work correctly
     */
    public void testLoanAndReturn( )
    {
        setupScenario1( );
        String name1 = resource1.getName( );
        int id1 = resource1.getId( );
        Student student = resource1.getStudent( );

        assertEquals( "The resource does not have the right name", "Guitar", name1 );
        assertEquals( "The resource does not have the right id", 1, id1 );
        assertTrue( "the resource should be available", resource1.isAvailable( ) );
        assertNull( "the resource doesn't have the right student", student );

        try
        {
            resource1.loan( student1 );
        }
        catch( Exception e )
        {
            fail( "The resource is available and therefore can be loaned" );
        }
        assertTrue( "The resource shouldn't be available", !resource1.isAvailable( ) );
        assertEquals( "the resource doesn't have the right student", student1, resource1.getStudent( ) );

        try
        {
            resource1.returnResource( );
        }
        catch( Exception e )
        {
            fail( "the resource is on loan and can be returned" );
        }
        assertTrue( "the resource should be available", resource1.isAvailable( ) );
    }

}