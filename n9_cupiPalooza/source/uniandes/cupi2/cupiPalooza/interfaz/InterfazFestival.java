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

package uniandes.cupi2.cupiPalooza.interfaz;

import java.awt.BorderLayout;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.cupiPalooza.mundo.Banda;
import uniandes.cupi2.cupiPalooza.mundo.Escenario;
import uniandes.cupi2.cupiPalooza.mundo.Festival;

/**
 * Festival de música que permite administrar sus escenarios y las bandas que se presentan en ellos.
 */
@SuppressWarnings("serial")
public class InterfazFestival extends JFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa la ubicación del archivo con los datos del festival.
     */
    private final static String ARCHIVO_SERIALIZACION = "./data/cupiPalooza.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Festival de música.
     */
    private Festival festival;

    /**
     * Escenario actual que se está manipulando en la vista.
     */
    private Escenario actual;
    
    /**
     * Banda actual en el escenario.
     */
    private Banda bandaActual;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con el banner de la aplicación.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con los botones de navegación entre escenarios.
     */
    private PanelEscenarios panelEscenarios;

    /**
     * Panel que muestra la información de las bandas del escenario actual.
     */
    private PanelBandas panelBandas;

    /**
     * Panel con las opciones.
     */
    private PanelOpciones panelOpciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la ventana principal con la información del festival.
     */
    public InterfazFestival( )
    {
        setLayout( new BorderLayout( ) );
        setTitle( "CupiPalooza" );
        setResizable( false );
        setSize( 700, 730 );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );

        panelImagen = new PanelImagen( );
        panelEscenarios = new PanelEscenarios( this );
        panelBandas = new PanelBandas( this );
        panelOpciones = new PanelOpciones( this );

        JPanel norte = new JPanel( );
        norte.setLayout( new BorderLayout( ) );
        norte.setBorder( null );

        norte.add( panelImagen, BorderLayout.NORTH );
        norte.add( panelEscenarios, BorderLayout.CENTER );
        add( norte, BorderLayout.NORTH );
        add( panelBandas, BorderLayout.CENTER );
        add( panelOpciones, BorderLayout.SOUTH );

        try
        {
            festival = new Festival( ARCHIVO_SERIALIZACION );
            ArrayList losEscenarios = festival.darEscenarios( );
            for( int i = 0; i < losEscenarios.size( ); i++ )
            {
                Escenario escenario = ( Escenario )losEscenarios.get( i );
                panelEscenarios.actualizar( escenario.darNumero( ), true );
            }

            if( losEscenarios.size( ) != 0 )
            {
                actual = festival.darEscenario( 1 );
                bandaActual = actual.darPrimeraBanda( );
                panelBandas.actualizar( bandaActual, actual.darNumero( ), actual.darPatrocinador( ) );
                
            }
            else
            {
                panelBandas.inhabilitar( );
            }

        }
        catch( Exception e )
        {
            panelBandas.inhabilitar( );
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Cargar", JOptionPane.ERROR_MESSAGE );
        }

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este método maneja la salida del usuario, dando la opción de guardar el estado del mundo.
     */
    public void dispose( )
    {
        int resultado = JOptionPane.showConfirmDialog( this, "¿Desea guardar el estado del festival antes de salir?", "Guardar", JOptionPane.INFORMATION_MESSAGE );
        if( resultado == JOptionPane.OK_OPTION )
        {
            try
            {
                festival.guardar( ARCHIVO_SERIALIZACION );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Guardar", JOptionPane.ERROR_MESSAGE );
            }
            super.dispose( );

        }
        else if( resultado == JOptionPane.CANCEL_OPTION )
        {
            // No se cierra la aplicación
        }
        else
        {
            super.dispose( );
        }

    }

    /**
     * Cambia el escenario actual que se está viendo. <br>
     * <b> post: </b> Se va al escenario especificado por el usuario. Si el escenario no tiene patrocinador se informa al usuario y se da la posibilidad de agregarlo.
     * @param pEscenario Número del escenario.
     */
    public void irAEscenario( int pEscenario )
    {
        Escenario escenario = festival.darEscenario( pEscenario );
        if( escenario == null )
        {
            int numero = festival.darNumeroDisponible( );
            DialogoCrearEscenario dialogo = new DialogoCrearEscenario( this, numero );
            dialogo.setVisible( true );
        }
        else
        {
            actual = escenario;
            panelBandas.habilitar( );
            bandaActual = actual.darPrimeraBanda( );
            panelBandas.actualizar( bandaActual, actual.darNumero( ), actual.darPatrocinador( ) );
            
        }

    }

    /**
     * Crea un escenario. <br>
     * <b> post: </b> El escenario ahora posee patrocinador en caso de que la información introducida sea correcta.
     * @param pPatrocinador Nombre del patrocinador.
     * @param pPresupuesto Presupuesto disponible para el escenario.
     * @param pNumero Número del escenario.
     * @param pDialogo Ventana de diálogo de la que provienen los datos.
     */
    public void crearEscenario( String pPatrocinador, String pPresupuesto, int pNumero, DialogoCrearEscenario pDialogo )
    {
        double presupuesto = 0;
        if( pPatrocinador.equals( "" ) || pPresupuesto.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Agregar Escenario", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try
            {
                presupuesto = Double.parseDouble( pPresupuesto );
                if( presupuesto <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Introduzca un presupuesto válido", "Agregar Escenario", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    try
                    {
                        festival.crearEscenario( pPatrocinador, presupuesto, pNumero );
                        actual = festival.darEscenario( pNumero );
                        bandaActual = null;
                        panelBandas.actualizar( null, actual.darNumero( ), actual.darPatrocinador( ) );
                        actualizarPanelEscenarios( );
                        pDialogo.dispose( );
                    }
                    catch( Exception e )
                    {
                        JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregar Escenario", JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, "El presupuesto debe ser un valor numérico", "Agregar Escenario", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    /**
     * Crea una ventana de diálogo para agregar una banda a un escenario.
     */
    public void dialogoAgregarBanda( )
    {
        if(actual != null)
        {
            DialogoCrearBanda dialogo = new DialogoCrearBanda( this );
            dialogo.setVisible( true );
        }
    }

    /**
     * Agrega una banda a un escenario. <br>
     * <b> post: </b> Se agrega la banda al repertorio del escenario, en caso de ser válida toda la información.
     * @param pNombre Nombre de la banda.
     * @param pFans Cantidad de fans.
     * @param pCanciones Cantidad de canciones.
     * @param pCosto Costo de la banda.
     * @param pImagen Ruta de la imagen.
     * @param pDia Día de la presentación.
     * @param pHora Hora de la presentación.
     * @param pDialogo Diálogo que contiene la información.
     */
    public void agregarBanda( String pNombre, String pFans, String pCanciones, String pCosto,int pDia, String pHora, String pImagen,  DialogoCrearBanda pDialogo )
    {
        if(actual != null)
        {
            int fans = 0;
            int canciones = 0;
            double costo = 0;
            int hora = 0;

            if( pNombre.equals( "" ) || pFans.equals( "" ) || pCanciones.equals( "" ) || pCosto.equals( "" ) || pHora.equals( "" ) || pImagen.equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Agregar Banda", JOptionPane.ERROR_MESSAGE );

            }
            else
            {
                try
                {
                    fans = Integer.parseInt( pFans );
                    canciones = Integer.parseInt( pCanciones );
                    costo = Double.parseDouble( pCosto );
                    hora = Integer.parseInt( pHora );
                    if( fans <= 0 )
                    {
                        JOptionPane.showMessageDialog( this, "Introduzca una cantidad de fans válida", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( canciones <= 0 )
                    {
                        JOptionPane.showMessageDialog( this, "Introduzca una cantidad de canciones válida", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( costo <= 0 )
                    {
                        JOptionPane.showMessageDialog( this, "Introduzca un costo válido", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( hora <= 0 || hora >= 25 )
                    {
                        JOptionPane.showMessageDialog( this, "Introduzca una hora válida", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                    }
                    else
                    {
                        try
                        {
                            festival.agregarBandaAEscenario( actual.darNumero( ), pNombre, fans, canciones, costo, pDia + "/11/17 - " + hora + ":00", pImagen );
                            actual = festival.darEscenario( actual.darNumero( ) );
                            bandaActual = actual.buscarPorNombre( pNombre );
                            pDialogo.dispose( );
                            panelBandas.cambiarMostrada( bandaActual );
                        }
                        catch( Exception e )
                        {
                            JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                        }
                    }
                }
                catch( Exception e )
                {
                    JOptionPane.showMessageDialog( this, "La cantidad de fans, la cantidad de canciones, la hora de presentación y el costo deben ser valores numéricos", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                }
            }
        }

    }

    /**
     * Elimina una banda del escenario actual. <br>
     * <b> post: </b> Se ha eliminado la banda del escenario.
     */
    public void eliminarBanda( )
    {
        if(actual != null)
        {
            if( bandaActual != null )
            {
                try
                {
                    festival.eliminarBandaEscenario( actual.darNumero( ), bandaActual.darNombre( ) );
                    bandaActual = actual.darPrimeraBanda( );
                    panelBandas.actualizar( bandaActual, actual.darNumero( ), actual.darPatrocinador( ) );
                }
                catch( Exception e )
                {
                    JOptionPane.showMessageDialog( this, e.getMessage( ), "Eliminar Banda", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Elija una banda", "Eliminar Banda", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Elimina el escenario actual. <br>
     * <b> post: </b> Se eliminó el escenario actual.
     */
    public void eliminarEscenario( )
    {
        if(actual != null)
        {
            int resultado = JOptionPane.showConfirmDialog( this, "¿Está seguro que desea eliminar este escenario?", "Eliminar Escenario", JOptionPane.INFORMATION_MESSAGE );
            if( resultado == JOptionPane.OK_OPTION )
            {
                festival.eliminarEscenario( actual.darNumero( ) );
                actual = null;
                bandaActual = null;
                panelBandas.inhabilitar( );
                actualizarPanelEscenarios( );
            }
        }
    }

    /**
     * Busca una banda en el escenario actual por su nombre. <b> post: </b> Se estableció la banda buscada como la banda actual y se muestra en el escenario. Si no se
     * encuentra se informa al usuario.
     */
    public void buscarBandaPorNombre( )
    {
        
        if( actual != null )
        {
            String nombre = JOptionPane.showInputDialog( this, "Introduzca el nombre de la banda", "Buscar banda por nombre", JOptionPane.INFORMATION_MESSAGE );

            if( nombre != null )
            {

                Banda buscada = actual.buscarPorNombre( nombre );
                if( buscada != null )
                {
                    bandaActual = buscada;
                    panelBandas.cambiarMostrada( buscada );
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "No se encontró una banda con este nombre", "Buscar Banda Por Nombre", JOptionPane.ERROR_MESSAGE );
                }
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Debe estar en un escenario para poder realizar una búsqueda", "Buscar Banda Por Nombre", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Crea una ventana de diálogo para buscar una banda por su horario de presentación.
     */
    public void dialogoBuscarHorario( )
    {
        DialogoBuscarHorario dialogo = new DialogoBuscarHorario( this );
        dialogo.setVisible( true );
    }

    /**
     * Busca una banda en el escenario actual por su horario de presentación. <b> post: </b> Se estableció la banda buscada como la banda actual y se muestra en el escenario.
     * Si no se encuentra se informa al usuario.
     * @param pDia Día de la presentación.
     * @param pHora Hora de la presentación.
     * @param pVentana Ventana de la cual viene la acción.
     */
    public void buscarBandaPorHorarioPresentacion( int pDia, String pHora, DialogoBuscarHorario pVentana )
    {

        if( actual != null )
        {
            try
            {
                int hora = Integer.parseInt( pHora );

                if( hora <= 0 || hora >= 25 )
                {
                    JOptionPane.showMessageDialog( this, "Ingrese una hora válida", "Buscar Banda Por Horario de Presentación", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    String fecha = pDia + "/11/17 - " + pHora + ":00";
                    SimpleDateFormat formato = new SimpleDateFormat( "dd/MM/yy - HH:00" );
                    
                    Banda buscada = actual.buscarPorHorario( formato.parse( fecha ) );
                    pVentana.dispose( );
                    if( buscada != null )
                    {
                        bandaActual = buscada;
                        panelBandas.cambiarMostrada( buscada );
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, "No se encontró una banda con este horario", "Buscar Banda Por Horario de Presentación", JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
            catch( Exception e )
            {
                e.printStackTrace( );
                JOptionPane.showMessageDialog( this, "Ingrese una hora válida", "Buscar Banda Por Horario de Presentación", JOptionPane.ERROR_MESSAGE );
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Debe estar en un escenario para poder realizar una búsqueda", "Buscar Banda Por Horario de Presentación", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Actualiza el panel de escenarios según los escenarios existentes en el festival.
     */
    public void actualizarPanelEscenarios( )
    {
        int[] posiciones = new int[Festival.CANTIDAD_MAXIMA_ESCENARIOS];
        ArrayList escenarios = festival.darEscenarios( );
        for( int i = 0; i < escenarios.size( ); i++ )
        {
            Escenario escenario = ( Escenario )escenarios.get( i );
            posiciones[ i ] = escenario.darNumero( );
            panelEscenarios.actualizar( escenario.darNumero( ), true );
        }

        for( int i = 0; i < posiciones.length; i++ )
        {
            int comprobacion = i + 1;
            boolean existente = false;

            for( int j = 0; j < posiciones.length && !existente; j++ )
            {
                if( comprobacion == posiciones[ j ] )
                {
                    existente = true;
                }
            }

            if( !existente )
            {
                panelEscenarios.actualizar( comprobacion, false );
            }

        }
    }
    
    /**
     * Navega a la anterior banda en el escenario.
     */
    public void anterior()
    {
        if(bandaActual != null)
        {
            Banda anterior = bandaActual.darAnterior( );
            if(anterior != null)
            {
                bandaActual = anterior;
                panelBandas.cambiarMostrada( bandaActual );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Ya se encuentra en la primera banda.", "Anterior banda", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }
    
    /**
     * Navega a la siguiente banda en el escenario.
     */
    public void siguiente()
    {
        if(bandaActual != null)
        {
            Banda siguiente = bandaActual.darSiguiente( );
            if(siguiente != null)
            {
                bandaActual = siguiente;
                panelBandas.cambiarMostrada( bandaActual );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Ya se encuentra en la última banda.", "Siguiente banda", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

    /**
     * Carga el estado del mundo a partir de un archivo de texto.
     */
    public void cargar( )
    {
        JFileChooser chooser = new JFileChooser( "./data" );
        chooser.setDialogTitle( "Cargar" );
        int returnVal = chooser.showSaveDialog( this );
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
            File archivo = chooser.getSelectedFile( );
            try
            {
                festival.importarArchivoTexto( archivo );
                ArrayList losEscenarios = festival.darEscenarios( );
                for( int i = 0; i < losEscenarios.size( ); i++ )
                {
                    Escenario escenario = ( Escenario )losEscenarios.get( i );
                    panelEscenarios.actualizar( escenario.darNumero( ), true );
                }

                actual = festival.darEscenario( 1 );
                bandaActual = actual.darPrimeraBanda( );
                panelBandas.actualizar( bandaActual, actual.darNumero( ), actual.darPatrocinador( ) );
                
            }
            catch( Exception e )
            {
                e.printStackTrace( );
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Cargar archivo", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Genera un archivo de reporte con los costos del festival.
     */
    public void generarReporte( )
    {
        try
        {
            String nombreReporte = JOptionPane.showInputDialog( this, "Introduzca el nombre para el reporte", "Generar Reporte", JOptionPane.INFORMATION_MESSAGE );
            if(nombreReporte != null)
            {
                festival.generarReporte( "./data/" + nombreReporte + ".txt" );
                JOptionPane.showMessageDialog( this, "Reporte generado.", "Generar reporte", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Cargar archivo", JOptionPane.ERROR_MESSAGE );
        }

    }

    // -----------------------------------------------------------------
    // Puntos de extensión
    // -----------------------------------------------------------------

    /**
     * Extensión 1.
     */
    public void reqFuncOpcion1( )
    {

        String resultado = festival.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta 1", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extensión 2.
     */
    public void reqFuncOpcion2( )
    {

        String resultado = festival.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta 2", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param args Arreglo opcional de argumentos que se recibe por línea de comandos.
     */
    public static void main( String[] args )
    {
        InterfazFestival interfaz = new InterfazFestival( );
        interfaz.setVisible( true );

    }

}
