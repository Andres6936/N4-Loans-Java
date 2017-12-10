package edu.jabs.loans.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import edu.jabs.loans.domain.Resource;
import edu.jabs.loans.domain.Student;

/**
 * Student test class
 */
public class StudentTest extends TestCase
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * the student for the scenario 1
     */
    private Student student1;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Prepares the scenario 1
     */
    private void setupScenario1( )
    {
        student1 = new Student( "Diana", 1 );
    }

    /**
     * tests the actions
     */
    public void testGetAndAdd( )
    {
        setupScenario1( );
        String name1 = student1.getName( );
        int id1 = student1.getId( );
        ArrayList loans1 = student1.getLoans( );

        assertEquals( "The student doesn't have the right name", "Diana", name1 );
        assertEquals( "The student doesn't have the right id", 1, id1 );
        assertEquals( "the student shouldn't have any loans", 0, loans1.size( ) );

        student1.addResourceToLoans( new Resource( "Guitar", 1 ) );
        student1.addResourceToLoans( new Resource( "Flute", 2 ) );

        loans1 = student1.getLoans( );
        assertEquals( "The student should have 2 resources on loan", 2, loans1.size( ) );
        assertTrue( "The resources should be guitar and flute", ( ( Resource )loans1.get( 0 ) ).getName( ).equals( "Guitar" ) && ( ( Resource )loans1.get( 1 ) ).getName( ).equals( "Flute" ) );

        student1.returnResource( 1 );

        loans1 = student1.getLoans( );
        assertEquals( "the student should have 1 resource", 1, loans1.size( ) );
        assertTrue( "the resource should be flute", ( ( Resource )loans1.get( 0 ) ).getName( ).equals( "Flute" ) );
    }

}