/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogotá - Colombia)
 * Departamento de  Ingeniería  de  Sistemas    y   Computación
 * Licenciado   bajo    el  esquema Academic Free License versión 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiPalooza
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPalooza.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.cupiPalooza.mundo.Banda;
import uniandes.cupi2.cupiPalooza.mundo.CupoMaximoException;
import uniandes.cupi2.cupiPalooza.mundo.ElementoExistenteException;
import uniandes.cupi2.cupiPalooza.mundo.Escenario;

/**
 * Clase usada para verificar que los métodos de la clase Escenario estén correctamente implementados.
 */
public class EscenarioTest
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Escenario del festival
     */
    private Escenario escenario;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------
    /**
     * Crea un escenario sin bandas. Este método se ejecuta antes de cada método de prueba.
     */
    @Before
    public void setupEscenario1( )
    {
        escenario = new Escenario( 1, "Fujifilm", 3000000 );
    }

    /**
     * Agrega 3 bandas al escenario
     */
    public void setupEscenario2( )
    {
        try
        {
            escenario.agregarBanda( "Aventureros", 125000, 4, 750000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/pop.png" );
            escenario.agregarBanda( "Borbotones", 100000, 3, 850000, new Date( 2017, 11, 4, 14, 0 ), "./data/imagenes/bandas/rock.png" );
            escenario.agregarBanda( "Caifanes", 50000, 8, 1200000, new Date( 2017, 11, 4, 16, 0 ), "./data/imagenes/bandas/country.png" );
        }
        catch( Exception e )
        {
            fail( "No debería generarse excepción." );
        }
    }

    /**
     * Agrega 2 bandas al escenario para dejarlo al máximo de capacidad
     */
    public void setupEscenario3( )
    {
        setupEscenario2( );
        try
        {
            escenario.agregarBanda( "On Planets", 110000, 5, 20000, new Date( 2017, 11, 5, 10, 0 ), "./data/imagenes/bandas/pop.png" );
            escenario.agregarBanda( "Other", 80000, 7, 20000, new Date( 2017, 11, 5, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        }
        catch( Exception e )
        {
            fail( "No debería generarse excepción." );
        }
    }

    // -----------------------------------------------------------------
    // Métodos de prueba
    // -----------------------------------------------------------------

    /**
     * <b>Prueba 1:</b> Verifica el método Escenario.<br>
     * <b>Métodos a probar:</b><br>
     * Festival<br>
     * darNumero<br>
     * darPatrocinador<br>
     * darPresupuesto<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se crea el escenario correctamente.<br>
     */
    @Test
    public void testEscenario( )
    {
        assertEquals( "El número del escenario no es correcto.", 1, escenario.darNumero( ) );
        assertNotNull( "El patrocinador del escenario debería existir.", escenario.darPatrocinador( ) );
        assertTrue( "El presupuesto actual debe ser mayor a 0.", 0 < escenario.darPresupuesto( ) );

    }

    /**
     * <b>Prueba 2:</b> Verifica el método darCostoAcumulado.<br>
     * <b>Métodos a probar:</b><br>
     * darCostoAcumulado<br>
     * <b>Casos de prueba:</b><br>
     * 1. No hay ninguna banda en el escenario.<br>
     * 2. Hay cinco bandas en el escenario.
     */
    @Test
    public void testDarCostoAcumulado( )
    {
        // Caso de prueba 1
        assertTrue( "El costo acumulado no es correcto.", 0 == escenario.darCostoAcumulado( ) );

        // Caso de prueba 2
        setupEscenario3( );
        assertTrue( "El costo acumulado no es correcto.", 2840000 == escenario.darCostoAcumulado( ) );
    }

    /**
     * <b>Prueba 3:</b> Verifica el método agregarBanda.<br>
     * <b>Métodos a probar:</b><br>
     * agregarBanda<br>
     * <b>Casos de prueba:</b><br>
     * 1. Ya existe una banda con el nombre dado.<br>
     * 2. No existe una banda con el nombre dado y se puede agregar.<br>
     * 3. No hay presupuesto suficiente para agregar la banda.<br>
     * 4. El escenario ya tiene todas las bandas permitidas.<br>
     * 5. Ya existe una banda con el horario de presentación deseado.
     */
    @Test
    public void testAgregarBanda( )
    {
        setupEscenario2( );
        // Caso de prueba 1
        try
        {
            escenario.agregarBanda( "Aventureros", 10, 10, 50000, new Date( 2017, 11, 3, 10, 0 ), "rutaImagen" );
            fail( "Debería generar excepción pues existe una banda por este nombre." );
        }
        catch( ElementoExistenteException e )
        {
            // Debe generar excepción
        }
        catch( Exception e )
        {
            fail( "No genera la excepción correcta." );
        }

        // Caso de prueba 2
        try
        {
            escenario.agregarBanda( "Otra banda", 10, 10, 100000, new Date( 2017, 11, 3, 10, 0 ), "rutaImagen" );
            assertNotNull( "El escenario debería tener la banda.", escenario.buscarPorNombre( "Otra banda" ) );
        }
        catch( Exception e )
        {
            fail( "Debió agregar la banda exitosamente." );
        }

        // Caso de prueba 3
        try
        {
            escenario.agregarBanda( "Otra banda 2", 10, 10, 300000, new Date( 2017, 11, 3, 10, 0 ), "rutaImagen" );
            fail( "No debería haber presupuesto suficiente para agregar la banda." );
        }
        catch( CupoMaximoException e )
        {
            // Debe generar excepción
        }
        catch( Exception e )
        {
            fail( "No genera la excepción correcta." );
        }

        // Caso de prueba 4
        try
        {
            escenario.agregarBanda( "Otra banda 3", 10, 10, 100, new Date( 2017, 11, 3, 11, 0 ), "rutaImagen" );
            escenario.agregarBanda( "Otra banda 4", 10, 10, 100, new Date( 2017, 11, 3, 12, 0 ), "rutaImagen" );
            escenario.agregarBanda( "Otra banda 5", 10, 10, 100, new Date( 2017, 11, 3, 13, 0 ), "rutaImagen" );
            escenario.agregarBanda( "Otra banda 6", 10, 10, 100, new Date( 2017, 11, 3, 14, 0 ), "rutaImagen" );
            escenario.agregarBanda( "Otra banda 7", 10, 10, 100, new Date( 2017, 11, 3, 15, 0 ), "rutaImagen" );
            escenario.agregarBanda( "Otra banda 8", 10, 10, 100, new Date( 2017, 11, 3, 16, 0 ), "rutaImagen" );
            escenario.agregarBanda( "Otra banda 9", 10, 10, 100, new Date( 2017, 11, 3, 17, 0 ), "rutaImagen" );
            escenario.agregarBanda( "Otra banda 10", 10, 10, 100, new Date( 2017, 11, 3, 18, 0 ), "rutaImagen" );
            escenario.agregarBanda( "Otra banda 11", 10, 10, 300, new Date( 2017, 11, 3, 19, 0 ), "rutaImagen" );
            fail( "No hay más espacio en el escenario, no debió agregar la banda." );
        }
        catch( CupoMaximoException e )
        {
            // Debe generar excepción
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            fail( "No genera la excepción correcta" );
        }

        // Caso de prueba 5
        try
        {
            escenario.agregarBanda( "Otra banda mas", 10, 10, 100, new Date( 2017, 11, 3, 11, 0 ), "rutaImagen" );
            fail( "Ya hay una banda con dicho horario de presentación, no debió agregar la banda." );
        }
        catch( CupoMaximoException e )
        {
            // Debe generar excepción
        }
        catch( Exception e )
        {
            fail( "No genera la excepción correcta" );
        }

    }

    /**
     * <b>Prueba 3:</b> Verifica el método agregarBanda, que los elementos se agregan en el orden correcto.<br>
     * <b>Métodos a probar:</b><br>
     * agregarBanda<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se agrega un elemento al inicio de la lista.<br>
     * 2. Se agrega un elemento en la mitad.<br>
     * 3. Se agrega un elemento al final de la lista.
     */
    @Test
    public void testAgregarBanda2( )
    {
        setupEscenario2( );

        try
        {
            // Caso de prueba 1
            escenario.agregarBanda( "Al Inicio", 125000, 4, 750000, new Date( 2017, 11, 3, 12, 0 ), "./data/imagenes/bandas/pop.png" );
            Banda actual = escenario.darPrimeraBanda( );

            while( actual != null && actual.darSiguiente( ) != null )
            {
                if( actual.darSiguiente( ) != null && actual.compararPorHorarioPresentacion( actual.darSiguiente( ) ) == 1 )
                {
                    fail( "Las bandas no están ordenadas por fecha" );
                }
                actual = actual.darSiguiente( );
            }

            // Caso de prueba 2
            escenario.agregarBanda( "A La Mitad", 125000, 4, 750000, new Date( 2017, 11, 4, 13, 0 ), "./data/imagenes/bandas/pop.png" );

            actual = escenario.darPrimeraBanda( );

            while( actual != null && actual.darSiguiente( ) != null )
            {
                if( actual.darSiguiente( ) != null && actual.compararPorHorarioPresentacion( actual.darSiguiente( ) ) == 1 )
                {
                    fail( "Las bandas no están ordenadas por fecha." );
                }
                actual = actual.darSiguiente( );
            }

            // Caso de prueba 3
            escenario.agregarBanda( "Al Final", 125000, 4, 750000, new Date( 2017, 11, 5, 13, 0 ), "./data/imagenes/bandas/pop.png" );

            actual = escenario.darPrimeraBanda( );

            while( actual != null && actual.darSiguiente( ) != null )
            {
                if( actual.darSiguiente( ) != null && actual.compararPorHorarioPresentacion( actual.darSiguiente( ) ) == 1 )
                {
                    fail( "Las bandas no están ordenadas por fecha." );
                }
                actual = actual.darSiguiente( );
            }
        }
        catch( Exception e )
        {
            // No pasa por aquí
        }
    }

    /**
     * <b>Prueba 4:</b> Verifica el método eliminarBanda.<br>
     * <b>Métodos a probar:</b><br>
     * eliminarBanda<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se elimina la primera banda en el escenario. <br>
     * 2. Se elimina la banda de la mitad en el escenario. <br>
     * 3. Se elimina la banda al final en el escenario. <br>
     * 4. No permite eliminar la última banda del escenario.
     */
    @Test
    public void testEliminarBanda( )
    {
        setupEscenario2( );
        
        // Caso de prueba 1
        try
        {
            escenario.eliminarBanda( "Aventureros" );
            assertNull( "La banda no debería existir en el escenario.", escenario.buscarPorNombre( "Aventureros" ) );
            assertEquals( "Eliminó más de una banda.", escenario.darCantidadBandas( ), 2 );
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción" );
        }
        // Caso de prueba 2
        try
        {
            escenario.agregarBanda( "Aventureros", 125000, 4, 750000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/pop.png" );
            escenario.eliminarBanda( "Borbotones" );
            assertNull( "La banda no debería existir en el escenario.", escenario.buscarPorNombre( "Borbotones" ) );
            assertEquals( "Eliminó más de una banda.", escenario.darCantidadBandas( ), 2 );
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción" );
        }
        // Caso de prueba 3
        try
        {
            escenario.agregarBanda( "Borbotones", 100000, 3, 850000, new Date( 2017, 11, 4, 14, 0 ), "./data/imagenes/bandas/rock.png" );
            escenario.eliminarBanda( "Caifanes" );
            
            assertNull( "La banda no debería existir en el escenario.", escenario.buscarPorNombre( "Caifanes" ) );
            assertEquals( "Eliminó más de una banda.", escenario.darCantidadBandas( ), 2 );
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción." );
        }
        // Caso de prueba 4
        try
        {
            escenario.eliminarBanda( "Borbotones" );
            escenario.eliminarBanda( "Aventureros" );
            fail( "No debería eliminar la banda Aventureros pues es la última en el escenario." );
        }
        catch( Exception e )
        {
            // Debe generar excepción
        }

    }

    /**
     * <b>Prueba 5:</b> Verifica el método buscarPorNombre.<br>
     * <b>Métodos a probar:</b><br>
     * buscarPorNombre<br>
     * <b>Casos de prueba:</b><br>
     * 1. La banda buscada existe en el escenario.<br>
     * 2. La banda buscada no existe en el escenario.
     */
    @Test
    public void testBuscarPorNombre( )
    {
        setupEscenario2( );

        // Caso de prueba 1
        assertNotNull( "Debió encontrar la banda.", escenario.buscarPorNombre( "Caifanes" ) );
        assertEquals( "La banda encontrada no es la correcta.", escenario.buscarPorNombre( "Caifanes" ).darCantidadDeFans( ), 50000 );

        // Caso de prueba 2
        assertNull( "No debió encontrar la banda pues no existe.", escenario.buscarPorNombre( "Los Inigualables" ) );
    }

    /**
     * <b>Prueba 6:</b> Verifica el método buscarPorHorario.<br>
     * <b>Métodos a probar:</b><br>
     * buscarPorHorario<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existe una banda con el horario de presentación buscado.<br>
     * 2. No existe una banca con el horario de presentación buscado.
     */
    @Test
    public void testBuscarPorHorario( )
    {
        setupEscenario2( );

        // Caso de prueba 1
        assertNotNull( "Debió encontrar la banda.", escenario.buscarPorHorario( new Date( 2017, 11, 4, 16, 0 ) ) );
        assertTrue( "La banda encontrada no es la correcta.", escenario.buscarPorHorario( new Date( 2017, 11, 4, 16, 0 ) ).darNombre( ).equals( "Caifanes" ) );

        // Caso de prueba 2
        assertNull( "No debió encontrar la banda pues no existe.", escenario.buscarPorHorario( new Date( 2017, 11, 4, 18, 0 ) ) );
    }

    /**
     * Prueba 7: Verifica el método cambiarSiguiente. <br>
     * <b>Métodos a probar:</b> <br>
     * Escenario<br>
     * darSiguiente<br>
     * cambiarSiguiente<br>
     * <b> Casos de prueba: </b><br>
     * 1) No hay escenario siguiente. <br>
     * 2) Hay un escenario siguiente.
     */
    @Test
    public void testCambiarSiguiente( )
    {
        // Caso de prueba 1
        Escenario escenario1 = new Escenario( 2, "Casio", 4000000 );
        assertNull( "El elemento siguiente debe ser null pues no ha sido asignado.", escenario.darSiguiente( ) );
        escenario.cambiarSiguiente( escenario1 );
        assertNotNull( "El elemento siguiente debe existir pues ha sido asignado.", escenario.darSiguiente( ) );
        assertEquals( "El elemento siguiente no coincide.", escenario1.darPatrocinador( ), escenario.darSiguiente( ).darPatrocinador( ) );
        
        // Caso de prueba 2
        Escenario escenario2 = new Escenario( 4, "Sony", 4000000 );
        escenario.cambiarSiguiente( escenario2 );
        assertNotNull( "El elemento siguiente debe existir pues ha sido asignado.", escenario.darSiguiente( ) );
        assertEquals( "El elemento siguiente no coincide.", escenario2.darPatrocinador( ), escenario.darSiguiente( ).darPatrocinador( ) );
    }

    /**
     * Prueba 8: Verifica el método darCantidadBandas. <br>
     * <b>Métodos a probar:</b> <br>
     * Escenario<br>
     * darCantidadBandas<br>
     * <b> Casos de prueba: </b><br>
     * 1) El escenario no tiene bandas. <br>
     * 3) El escenario tiene 5 bandas.
     */
    @Test
    public void testDarCantidadBandas( )
    {
        assertTrue( "El número de bandas debería ser 0.", escenario.darCantidadBandas( ) == 0 );
        setupEscenario3( );
        assertTrue( "El número de bandas debería ser 5.", escenario.darCantidadBandas( ) == 5 );

    }

}
