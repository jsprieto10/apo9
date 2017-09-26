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
 * Festival de m�sica que permite administrar sus escenarios y las bandas que se presentan en ellos.
 */
@SuppressWarnings("serial")
public class InterfazFestival extends JFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa la ubicaci�n del archivo con los datos del festival.
     */
    private final static String ARCHIVO_SERIALIZACION = "./data/cupiPalooza.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Festival de m�sica.
     */
    private Festival festival;

    /**
     * Escenario actual que se est� manipulando en la vista.
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
     * Panel con el banner de la aplicaci�n.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con los botones de navegaci�n entre escenarios.
     */
    private PanelEscenarios panelEscenarios;

    /**
     * Panel que muestra la informaci�n de las bandas del escenario actual.
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
     * Crea la ventana principal con la informaci�n del festival.
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Este m�todo maneja la salida del usuario, dando la opci�n de guardar el estado del mundo.
     */
    public void dispose( )
    {
        int resultado = JOptionPane.showConfirmDialog( this, "�Desea guardar el estado del festival antes de salir?", "Guardar", JOptionPane.INFORMATION_MESSAGE );
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
            // No se cierra la aplicaci�n
        }
        else
        {
            super.dispose( );
        }

    }

    /**
     * Cambia el escenario actual que se est� viendo. <br>
     * <b> post: </b> Se va al escenario especificado por el usuario. Si el escenario no tiene patrocinador se informa al usuario y se da la posibilidad de agregarlo.
     * @param pEscenario N�mero del escenario.
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
     * <b> post: </b> El escenario ahora posee patrocinador en caso de que la informaci�n introducida sea correcta.
     * @param pPatrocinador Nombre del patrocinador.
     * @param pPresupuesto Presupuesto disponible para el escenario.
     * @param pNumero N�mero del escenario.
     * @param pDialogo Ventana de di�logo de la que provienen los datos.
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
                    JOptionPane.showMessageDialog( this, "Introduzca un presupuesto v�lido", "Agregar Escenario", JOptionPane.ERROR_MESSAGE );
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
                JOptionPane.showMessageDialog( this, "El presupuesto debe ser un valor num�rico", "Agregar Escenario", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    /**
     * Crea una ventana de di�logo para agregar una banda a un escenario.
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
     * <b> post: </b> Se agrega la banda al repertorio del escenario, en caso de ser v�lida toda la informaci�n.
     * @param pNombre Nombre de la banda.
     * @param pFans Cantidad de fans.
     * @param pCanciones Cantidad de canciones.
     * @param pCosto Costo de la banda.
     * @param pImagen Ruta de la imagen.
     * @param pDia D�a de la presentaci�n.
     * @param pHora Hora de la presentaci�n.
     * @param pDialogo Di�logo que contiene la informaci�n.
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
                        JOptionPane.showMessageDialog( this, "Introduzca una cantidad de fans v�lida", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( canciones <= 0 )
                    {
                        JOptionPane.showMessageDialog( this, "Introduzca una cantidad de canciones v�lida", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( costo <= 0 )
                    {
                        JOptionPane.showMessageDialog( this, "Introduzca un costo v�lido", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( hora <= 0 || hora >= 25 )
                    {
                        JOptionPane.showMessageDialog( this, "Introduzca una hora v�lida", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
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
                    JOptionPane.showMessageDialog( this, "La cantidad de fans, la cantidad de canciones, la hora de presentaci�n y el costo deben ser valores num�ricos", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
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
     * <b> post: </b> Se elimin� el escenario actual.
     */
    public void eliminarEscenario( )
    {
        if(actual != null)
        {
            int resultado = JOptionPane.showConfirmDialog( this, "�Est� seguro que desea eliminar este escenario?", "Eliminar Escenario", JOptionPane.INFORMATION_MESSAGE );
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
     * Busca una banda en el escenario actual por su nombre. <b> post: </b> Se estableci� la banda buscada como la banda actual y se muestra en el escenario. Si no se
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
                    JOptionPane.showMessageDialog( this, "No se encontr� una banda con este nombre", "Buscar Banda Por Nombre", JOptionPane.ERROR_MESSAGE );
                }
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Debe estar en un escenario para poder realizar una b�squeda", "Buscar Banda Por Nombre", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Crea una ventana de di�logo para buscar una banda por su horario de presentaci�n.
     */
    public void dialogoBuscarHorario( )
    {
        DialogoBuscarHorario dialogo = new DialogoBuscarHorario( this );
        dialogo.setVisible( true );
    }

    /**
     * Busca una banda en el escenario actual por su horario de presentaci�n. <b> post: </b> Se estableci� la banda buscada como la banda actual y se muestra en el escenario.
     * Si no se encuentra se informa al usuario.
     * @param pDia D�a de la presentaci�n.
     * @param pHora Hora de la presentaci�n.
     * @param pVentana Ventana de la cual viene la acci�n.
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
                    JOptionPane.showMessageDialog( this, "Ingrese una hora v�lida", "Buscar Banda Por Horario de Presentaci�n", JOptionPane.ERROR_MESSAGE );
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
                        JOptionPane.showMessageDialog( this, "No se encontr� una banda con este horario", "Buscar Banda Por Horario de Presentaci�n", JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
            catch( Exception e )
            {
                e.printStackTrace( );
                JOptionPane.showMessageDialog( this, "Ingrese una hora v�lida", "Buscar Banda Por Horario de Presentaci�n", JOptionPane.ERROR_MESSAGE );
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Debe estar en un escenario para poder realizar una b�squeda", "Buscar Banda Por Horario de Presentaci�n", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Actualiza el panel de escenarios seg�n los escenarios existentes en el festival.
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
                JOptionPane.showMessageDialog( this, "Ya se encuentra en la �ltima banda.", "Siguiente banda", JOptionPane.INFORMATION_MESSAGE );
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
    // Puntos de extensi�n
    // -----------------------------------------------------------------

    /**
     * Extensi�n 1.
     */
    public void reqFuncOpcion1( )
    {

        String resultado = festival.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta 1", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extensi�n 2.
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
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
     * @param args Arreglo opcional de argumentos que se recibe por l�nea de comandos.
     */
    public static void main( String[] args )
    {
        InterfazFestival interfaz = new InterfazFestival( );
        interfaz.setVisible( true );

    }

}
