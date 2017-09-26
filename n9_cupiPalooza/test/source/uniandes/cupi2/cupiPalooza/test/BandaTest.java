/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogot� - Colombia)
 * Departamento de  Ingenier�a  de  Sistemas    y   Computaci�n
 * Licenciado   bajo    el  esquema Academic Free License versi�n 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiPalooza
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPalooza.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.cupiPalooza.mundo.Banda;

/**
 * Clase usada para verificar que los m�todos de la clase Banda est�n correctamente implementados.
 */
public class BandaTest
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Escenario del festival
     */
    private Banda banda;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------
    /**
     * Crea una instancia de la clase Banda. Este m�todo se ejecuta antes de cada m�todo de prueba.
     */
    @Before
    public void setupEscenario1( )
    {
        Date fecha = new Date( 2017, 11, 4, 12, 0 );
        banda = new Banda( "Caifanes", 56000, 5, 5000000, fecha, "./data/imagenes/bandas/rock.png" );
    }

    /**
     * Crea una banda anterior y una banda siguiente para la banda.
     */
    public void setupEscenario2( )
    {
        Banda siguiente = new Banda( "Aventureros", 125000, 4, 750000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/pop.png" );
        Banda anterior = new Banda( "Borbotones", 100000, 3, 850000, new Date( 2017, 11, 4, 14, 0 ), "./data/imagenes/bandas/rock.png" );
        banda.cambiarSiguiente( siguiente );
        banda.cambiarAnterior( anterior );
    }

    // -----------------------------------------------------------------
    // M�todos de prueba
    // -----------------------------------------------------------------

    /**
     * <b>Prueba 1:</b> Verifica el m�todo Banda.<br>
     * <b>M�todos a probar:</b><br>
     * Banda<br>
     * darNombre<br>
     * darNumeroDeFans<br>
     * darNumeroDeCanciones<br>
     * darCosto<br>
     * darRutaImagen<br>
     * darSiguiente<br>
     * darAnterior<br>
     * <b>Casos de prueba:</b><br>
     */
    @Test
    public void testBanda( )
    {
        assertTrue( "El nombre no es correcto", banda.darNombre( ).equals( "Caifanes" ) );
        assertEquals( "El n�mero de fans no es correcto", banda.darCantidadDeFans( ), 56000 );
        assertEquals( "El n�mero de canciones no es correcto", banda.darCantidadDeCanciones( ), 5 );
        assertTrue( "El costo no es correcto", 5000000 == banda.darCosto( ) );
        assertTrue( "La ruta de la imagen no es correcta", banda.darRutaImagen( ).equals( "./data/imagenes/bandas/rock.png" ) );

        assertNull( "La banda no deber�a tener un elemento anterior.", banda.darAnterior( ) );
        assertNull( "La banda no deber�a tener un elemento siguiente.", banda.darSiguiente( ) );

        setupEscenario2( );
        assertNotNull( "La banda deber�a tener un elemento siguiente.", banda.darSiguiente( ) );
        assertNotNull( "La banda deber�a tener un elemento anterior.", banda.darAnterior( ) );
    }

    /**
     * Prueba 2: Verifica el m�todo compararPorNombre. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * compararPorNombre<br>
     * <b> Casos de prueba: </b><br>
     * 1) Las dos bandas tienen el mismo nombre.<br>
     * 2) La banda contra la cual se compara tiene un nombre lexicogr�ficamente mayor. <br>
     * 3) La banda contra la cual se compara tiene un nombre lexicogr�ficamente menor. <br>
     */
    @Test
    public void testCompararPorNombre( )
    {
        // Caso de prueba 1
        Banda banda1 = new Banda( "Caifanes", 56000, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorNombre( banda1 ), 0 );

        // Caso de prueba 2
        Banda banda2 = new Banda( "Sin Banderas", 56000, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorNombre( banda2 ), -1 );

        // Caso de prueba 3
        Banda banda3 = new Banda( "Ava", 56000, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorNombre( banda3 ), 1 );
    }

    /**
     * Prueba 3: Verifica el m�todo compararPorCantidadDeFans. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * compararPorCantidadDeFans<br>
     * <b> Casos de prueba: </b><br>
     * 1) Las dos bandas tienen la misma cantidad de fans.<br>
     * 2) La banda contra la cual se compara tiene menor cantidad de fans. <br>
     * 3) La banda contra la cual se compara tiene mayor cantidad de fans. <br>
     */
    @Test
    public void testCompararPorCantidadDeFans( )
    {
        // Caso de prueba 1
        Banda banda1 = new Banda( "Banda 1", 56000, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeFans( banda1 ), 0 );

        // Caso de prueba 2
        Banda banda2 = new Banda( "Banda 2", 560, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeFans( banda2 ), 1 );

        // Caso de prueba 3
        Banda banda3 = new Banda( "Banda 3", 72000, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeFans( banda3 ), -1 );
    }

    /**
     * Prueba 4: Verifica el m�todo compararPorCantidadDeCanciones. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * compararPorCantidadDeCanciones<br>
     * <b> Casos de prueba: </b><br>
     * 1) Las dos bandas tienen la misma cantidad de canciones.<br>
     * 2) La banda contra la cual se compara tiene menor cantidad de canciones. <br>
     * 3) La banda contra la cual se compara tiene mayor cantidad de canciones. <br>
     */
    @Test
    public void testCompararPorCantidadDeCanciones( )
    {
        // Caso de prueba 1
        Banda banda1 = new Banda( "Banda 1", 56000, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeCanciones( banda1 ), 0 );

        // Caso de prueba 2
        Banda banda2 = new Banda( "Banda 2", 56000, 3, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeCanciones( banda2 ), 1 );

        // Caso de prueba 3
        Banda banda3 = new Banda( "Banda 3", 56000, 7, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeCanciones( banda3 ), -1 );
    }

    /**
     * Prueba 5: Verifica el m�todo compararPorHorarioPresentacion. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * compararPorHorarioPresentacion<br>
     * <b> Casos de prueba: </b><br>
     * 1) Las dos bandas tienen el mismo horario de presentaci�n.<br>
     * 2) La banda contra la cual se compara se presenta antes. <br>
     * 3) La banda contra la cual se compara se presenta despu�s. <br>
     */
    @Test
    public void testCompararPorHorarioPresentacion( )
    {
        // Caso de prueba 1
        Banda banda1 = new Banda( "Banda 1", 56000, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorHorarioPresentacion( banda1 ), 0 );

        // Caso de prueba 2
        Banda banda2 = new Banda( "Banda 2", 56000, 5, 5000000, new Date( 2017, 11, 3, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorHorarioPresentacion( banda2 ), 1 );

        // Caso de prueba 3
        Banda banda3 = new Banda( "Banda 3", 56000, 5, 5000000, new Date( 2017, 11, 5, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertEquals( "El resultado de la comparaci�n no es correcto", banda.compararPorHorarioPresentacion( banda3 ), -1 );
    }

    /**
     * Prueba 8: Verifica el m�todo cambiarSiguiente. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * darSiguiente<br>
     * cambiarSiguiente<br>
     * <b> Casos de prueba: </b><br>
     * 1) No hay una banda siguiente. <br>
     * 2) Hay una banda siguiente.
     */
    @Test
    public void testCambiarSiguiente( )
    {
        // Caso de prueba 1
        Banda banda1 = new Banda( "Banda 1", 56000, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertNull( "El elemento siguiente debe ser null pues no ha sido asignado.", banda.darSiguiente( ) );
        banda.cambiarSiguiente( banda1 );
        assertNotNull( "El elemento siguiente debe existir pues ha sido asignado.", banda.darSiguiente( ) );
        assertEquals( "El elemento siguiente no coincide", banda1.darNombre( ), banda.darSiguiente( ).darNombre( ) );

        // Caso de prueba 2
        Banda banda2 = new Banda( "Banda 2", 56000, 5, 5000000, new Date( 2017, 11, 4, 17, 0 ), "./data/imagenes/bandas/rock.png" );
        banda.cambiarSiguiente( banda2 );
        assertNotNull( "El elemento siguiente debe existir pues ha sido asignado.", banda.darSiguiente( ) );
        assertEquals( "El elemento siguiente no coincide", banda2.darNombre( ), banda.darSiguiente( ).darNombre( ) );
    }

    /**
     * Prueba 9: Verifica el m�todo cambiarAnterior. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * darAnterior<br>
     * cambiarAnterior<br>
     * <b> Casos de prueba: </b><br>
     * 1) No hay una banda anterior. <br>
     * 2) Hay una banda anterior.
     */
    @Test
    public void testCambiarAnterior( )
    {
        // Caso de prueba 1
        Banda banda1 = new Banda( "Banda 1", 56000, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        assertNull( "El elemento anterior debe ser null pues no ha sido asignado.", banda.darAnterior( ) );
        banda.cambiarAnterior( banda1 );
        assertNotNull( "El elemento anterior debe existir pues ha sido asignado.", banda.darAnterior( ) );
        assertEquals( "El elemento anterior no coincide", banda1.darNombre( ), banda.darAnterior( ).darNombre( ) );

        // Caso de prueba 2
        Banda banda2 = new Banda( "Banda 2", 56000, 5, 5000000, new Date( 2017, 11, 4, 12, 0 ), "./data/imagenes/bandas/rock.png" );
        banda.cambiarAnterior( banda2 );
        assertNotNull( "El elemento anterior debe existir pues ha sido asignado.", banda.darAnterior( ) );
        assertEquals( "El elemento anterior no coincide", banda2.darNombre( ), banda.darAnterior( ).darNombre( ) );
    }

}
