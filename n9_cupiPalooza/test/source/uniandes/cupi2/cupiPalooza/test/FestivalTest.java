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

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import uniandes.cupi2.cupiPalooza.mundo.CupoMaximoException;
import uniandes.cupi2.cupiPalooza.mundo.ElementoExistenteException;
import uniandes.cupi2.cupiPalooza.mundo.Escenario;
import uniandes.cupi2.cupiPalooza.mundo.Festival;
import uniandes.cupi2.cupiPalooza.mundo.PersistenciaException;

/**
 * Clase usada para verificar que los métodos de la clase Festival estén correctamente implementados.
 */
public class FestivalTest
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Festival de música con sus escenarios y bandas
     */
    private Festival festival;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------

    /**
     * Crea una instancia de la clase festival. Este método se ejecuta antes de cada método de prueba.
     */
    @Before
    public void setupEscenario1( )
    {
        try
        {
            festival = new Festival( "" );
            festival.importarArchivoTexto( new File( "./test/data/archivo1.txt" ) );
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción." );
        }
    }

    /**
     * Crea un festival, asignando un patrocinador al cuarto escenario y tres bandas al mismo.
     */
    public void setupEscenario2( )
    {
        try
        {
            festival.crearEscenario( "Huawei", 20000000, 4 );
            festival.agregarBandaAEscenario( 4, "Aventureros", 125000, 4, 750000, "03/11/17 - 10:00", "./data/imagenes/bandas/pop.png" );
            festival.agregarBandaAEscenario( 4, "Borbotones", 100000, 3, 850000, "03/11/17/11/17:00", "./data/imagenes/bandas/rock.png" );
            festival.agregarBandaAEscenario( 4, "Caifanes", 50000, 8, 1200000, "03/11/17 - 13:00", "./data/imagenes/bandas/country.png" );
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción." );
        }
    }

    /**
     * Crea un festival, asignando un patrocinador al quinto escenario y tres bandas al mismo.
     */
    public void setupEscenario3( )
    {
        try
        {
            festival.crearEscenario( "Sony", 600000, 5 );
            festival.agregarBandaAEscenario( 5, "On Planets", 125000, 4, 200000, "05/11/17 - 10:00", "./data/imagenes/bandas/pop.png" );
            festival.agregarBandaAEscenario( 5, "Other", 100000, 3, 200000, "05/11/17 - 13:00", "./data/imagenes/bandas/rock.png" );
            festival.agregarBandaAEscenario( 5, "Airia", 50000, 8, 100000, "05/11/17 - 16:00", "./data/imagenes/bandas/country.png" );
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción." );
        }
    }

    /**
     * Crea un festival sin escenarios.
     */
    public void setupEscenario4( )
    {
        try
        {
            festival = new Festival( "" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debería generar excepción." );
        }
    }

    // -----------------------------------------------------------------
    // Métodos de prueba
    // -----------------------------------------------------------------

    /**
     * <b>Prueba 1:</b> Verifica el método Festival.<br>
     * <b>Métodos a probar:</b><br>
     * Festival<br>
     * darEscenario<br>
     * darEscenarios<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se crea el festival.
     */
    @Test
    public void testFestival( )
    {
        setupEscenario1( );
        Escenario escenario1 = festival.darEscenario( 1 );
        Escenario escenario2 = festival.darEscenario( 2 );
        Escenario escenario3 = festival.darEscenario( 3 );
        Escenario escenario4 = festival.darEscenario( 4 );
        Escenario escenario5 = festival.darEscenario( 5 );

        assertNotNull( "No se inicializó el escenario 1 correctamente.", escenario1 );
        assertNotNull( "No se inicializó el escenario 2 correctamente.", escenario2 );
        assertNotNull( "No se inicializó el escenario 3 correctamente.", escenario3 );
        assertNull( "No se inicializó el escenario 4 correctamente.", escenario4 );
        assertNull( "No se inicializó el escenario 5 correctamente.", escenario5 );

        assertEquals( "La cantidad  de bandas en el escenario no es correcto.", 3, festival.darEscenarios( ).size( ), 0 );

        assertEquals( "El número del escenario 1 no es correcto.", 1, escenario1.darNumero( ), 0 );
        assertEquals( "El número del escenario 2 no es correcto.", 2, escenario2.darNumero( ), 0 );
        assertEquals( "El número del escenario 3 no es correcto.", 3, escenario3.darNumero( ), 0 );

        assertTrue( "El patrocinador del escenario 1 no es correcto.", escenario1.darPatrocinador( ).equals( "Movistar" ) );
        assertTrue( "El patrocinador del escenario 2 no es correcto.", escenario2.darPatrocinador( ).equals( "Adidas" ) );
        assertTrue( "El patrocinador del escenario 3 no es correcto.", escenario3.darPatrocinador( ).equals( "Google" ) );

    }
    /**
     * <b>Prueba 2:</b> Verifica el método darNumeroDisponible.<br>
     * <b>Métodos a probar:</b><br>
     * darNumeroDisponible<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existen los escenarios 1, 2 y 3.<br>
     * 2. Existen todos los escenarios.<br>
     * 3. Existen los escenarios 1, 3 y 5.<br>
     */
    @Test
    public void testDarNumeroDisponible( )
    {
        // Caso de prueba 1
        assertEquals( "El siguiente número disponible es incorrecto.", 4, festival.darNumeroDisponible( ), 0 );

        // Caso de prueba 2
        setupEscenario2( );
        setupEscenario3( );
        assertEquals( "El siguiente número disponible es incorrecto, no hay más escenarios.", 0, festival.darNumeroDisponible( ), 0 );

        // Caso de prueba 3
        festival.eliminarEscenario( 4 );
        assertEquals( "El siguiente número disponible es incorrecto.", 4, festival.darNumeroDisponible( ), 0 );
        festival.eliminarEscenario( 2 );
        assertEquals( "El siguiente número disponible es incorrecto.", 2, festival.darNumeroDisponible( ), 0 );
    }

    /**
     * <b>Prueba 3:</b> Verifica el método crearEscenario.<br>
     * <b>Métodos a probar:</b><br>
     * crearEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. Ya existe un escenario con el patrocinador dado.<br>
     * 2. No existe un escenario con el patrocinador dado.<br>
     * 3. Ya existen todos los escenarios permitidos.<br>
     * 4. No hay ningún escenario en el festival.<br>
     */
    @Test
    public void testCrearEscenario( )
    {

        setupEscenario2( );

        // Caso de prueba 2
        try
        {
            festival.crearEscenario( "Huawei", 50000, 5 );
            fail( "No debió agregar el patrocinador, porque ya existe." );
        }
        catch( ElementoExistenteException e )
        {
            assertEquals( "No debería haber agregado el escenario al festival.", 4, festival.darEscenarios( ).size( ) );
            // Debe generar excepción
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción." );
        }

        // Caso de prueba 3
        try
        {
            festival.crearEscenario( "Kinder Eggs", 50000, 5 );
            assertEquals( "Debería haber agregado el escenario al festival.", 5, festival.darEscenarios( ).size( ) );
        }
        catch( Exception e )
        {
            fail( "Debió asignar el patrocinador" );
        }

        // Caso de prueba 4
        try
        {
            festival.crearEscenario( "Oracle", 50000, 6 );
            fail( "Se alcanzó el número máximo de escenarios, no debería crear el escenario." );
        }
        catch( CupoMaximoException e )
        {
            assertEquals( "No debería haber agregado el escenario al festival.", 5, festival.darEscenarios( ).size( ) );
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción." );
        }

        setupEscenario4( );
        try
        {
            festival.crearEscenario( "Kinder Eggs", 50000, 1 );
            assertEquals( "Debería haber agregado el escenario al festival.", 1, festival.darEscenarios( ).size( ) );
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción." );
        }
    }

    /**
     * <b>Prueba 4:</b> Verifica el método eliminarEscenario.<br>
     * <b>Métodos a probar:</b><br>
     * eliminarEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. El escenario a eliminar no existe.<br>
     * 2. El escenario a eliminar se encuentra el final de la lista.<br>
     * 3. El escenario a eliminar se encuentra en la mitad de la lista.<br>
     * 4. El escenario a eliminar se encuentra al principio de la lista.<br>
     * 5. No hay ningún escenario en el festival.
     */
    @Test
    public void testEliminarEscenario( )
    {
        setupEscenario2( );
        // Caso de prueba 1
        festival.eliminarEscenario( 5 );
        assertEquals( "No debería haber eliminado el escenario.", 4, festival.darEscenarios( ).size( ) );

        // Caso de prueba 2
        festival.eliminarEscenario( 4 );
        assertEquals( "Debería haber eliminado el escenario.", 3, festival.darEscenarios( ).size( ) );
        assertNull( "No debería encontrar el escenario.", festival.darEscenario( 4 ) );

        // Caso de prueba 3
        festival.eliminarEscenario( 2 );
        assertEquals( "Debería haber eliminado el escenario.", 2, festival.darEscenarios( ).size( ) );
        assertNull( "No debería encontrar el escenario.", festival.darEscenario( 4 ) );

        // Caso de prueba 4
        festival.eliminarEscenario( 1 );
        assertEquals( "Debería haber eliminado el escenario.", 1, festival.darEscenarios( ).size( ) );
        assertNull( "No debería encontrar el escenario.", festival.darEscenario( 4 ) );

        festival.eliminarEscenario( 3 );
        assertEquals( "Debería haber eliminado el escenario.", 0, festival.darEscenarios( ).size( ) );
        assertNull( "No debería encontrar el escenario.", festival.darEscenario( 4 ) );

        // Caso de prueba 5
        try
        {
            festival.eliminarEscenario( 1 );
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción." );
        }

    }

    /**
     * <b>Prueba 5:</b> Verifica el método agregarBandaAEscenario.<br>
     * <b>Métodos a probar:</b><br>
     * agregarBandaAEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. Ya existe una banda con el mismo nombre en el escenario.<br>
     * 2. No existe una banda con ese nombre en el escenario.<br>
     * 3. El presupuesto no alcanza para agregar la banda.<br>
     * 4. Se alcanzó el límite de bandas en el escenario. <br>
     * 5. Ya existe una banda con el horario de presentación especificado.
     */
    @Test
    public void testAgregarBandaAEscenario( )
    {
        setupEscenario3( );
        // Caso de prueba 1
        Escenario escenario = festival.darEscenario( 5 );
        try
        {
            festival.agregarBandaAEscenario( 5, "On Planets", 10, 10, 50000, "04/11/17 - 12:00", "rutaImagen" );
            fail( "No debió agregar la banda pues ya existe en este escenario." );
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
            festival.agregarBandaAEscenario( 5, "Otra banda", 10, 10, 50000, "04/11/17 - 10:00", "rutaImagen" );
            assertNotNull( "El escenario debería tener la banda.", escenario.buscarPorNombre( "Otra banda" ) );
        }
        catch( Exception e )
        {
            fail( "Debió agregar la banda exitosamente." );
        }

        // Caso de prueba 3
        try
        {
            festival.agregarBandaAEscenario( 5, "Otra banda 2", 10, 10, 200000, "04/11/17 - 12:00", "rutaImagen" );
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
            festival.agregarBandaAEscenario( 5, "Otra banda 3", 10, 10, 600, "04/11/17 - 13:00", "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 4", 10, 10, 600, "04/11/17 - 14:00", "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 5", 10, 10, 600, "04/11/17 - 15:00", "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 6", 10, 10, 600, "04/11/17 - 16:00", "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 7", 10, 10, 600, "04/11/17 - 17:00", "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 8", 10, 10, 600, "04/11/17 - 18:00", "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 9", 10, 10, 600, "04/11/17 - 19:00", "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 10", 10, 10, 600, "04/11/17 - 20:00", "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 11", 10, 10, 600, "04/11/17 - 21:00", "rutaImagen" );
            fail( "No debe haber mas espacio en el escenario para agregar la banda." );
        }
        catch( CupoMaximoException e )
        {
            // Debe generar excepción
        }
        catch( Exception e )
        {
            fail( "No genera la excepción correcta." );
        }

        // Caso de prueba 5
        try
        {
            festival.agregarBandaAEscenario( 5, "Otra banda más", 10, 10, 600, "04/11/17 - 13:00", "rutaImagen" );
        }
        catch( CupoMaximoException e )
        {
            // Debe generar excepción
        }
        catch( Exception e )
        {
            fail( "No genera la excepción correcta." );
        }

    }

    /**
     * <b>Prueba 6:</b> Verifica el método eliminarBandaEscenario.<br>
     * <b>Métodos a probar:</b><br>
     * eliminarBandaEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. La banda a eliminar existe en el escenario.
     */
    @Test
    public void testEliminarBandaEscenario( )
    {
        setupEscenario3( );
        // Caso de prueba 1
        try
        {
            Escenario escenario = festival.darEscenario( 5 );
            festival.eliminarBandaEscenario( 5, "On Planets" );
            assertNull( "La bandan no debería existir en el escenario.", escenario.buscarPorNombre( "On Planets" ) );
        }
        catch( Exception e )
        {
            fail( "No debería generar excepción." );
        }

        // Caso de prueba 2
        try
        {
            festival.eliminarBandaEscenario( 5, "Other" );
            festival.eliminarBandaEscenario( 5, "Airia" );
            fail( "No debería eliminar la banda Airia pues es la última en el escenario." );
        }
        catch( Exception e )
        {
            // Debe generar excepción
        }
    }

    /**
     * <b>Prueba 7:</b> Verifica el método cargar.<br>
     * <b>Métodos a probar:</b><br>
     * cargar<br>
     * <b>Casos de prueba:</b><br>
     * 1. No se carga el archivo porque no es válido.<br>
     * 2. Se carga el archivo.
     */
    @Test
    public void testCargar( )
    {
        // Caso de prueba 1
        try
        {
            festival.cargar( "./data/archivo1.txt" );
            fail( "No debió cargar el estado pues el tipo del archivo no es válido." );
        }
        catch( PersistenciaException e )
        {
            // Debe generar excepción
        }

        // Caso de prueba 2
        try
        {
            festival.guardar( "./test/data/archivoTest.data" );
            festival.cargar( "./test/data/archivoTest.data" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debió generar excepción pues este archivo es válido." );
        }
    }

    /**
     * <b>Prueba 8:</b> Verifica el método guardar.<br>
     * <b>Métodos a probar:</b><br>
     * guardar<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se guarda el archivo.
     */
    @Test
    public void testGuardar( )
    {
        // Caso de prueba 1
        try
        {
            festival.guardar( "./test/data/archivoTest.data" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debió generar excepción pues este archivo es válido." );
        }
    }
}
