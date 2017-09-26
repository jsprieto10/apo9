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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Panel con las opciones de la aplicaci�n.
 */
@SuppressWarnings("serial")
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para agregar una banda.
     */
    public final static String AGREGAR_BANDA = "Agregar Banda";

    /**
     * Comando para eliminar una banda.
     */
    public final static String ELIMINAR_BANDA = "Eliminar Banda";

    /**
     * Comando para eliminar el escenario.
     */
    public final static String ELIMINAR_ESCENARIO = "Eliminar Escenario";
    
    /**
     * Comando para buscar una banda.
     */
    public final static String BUSCAR = "Buscar una banda";

    /**
     * Comando para cargar el estado del festival.
     */
    public final static String CARGAR = "Cargar";

    /**
     * Comando para generar un reporte.
     */
    public final static String REPORTE = "Generar Reporte";

    /**
     * Comando para buscar una banda por su nombre.
     */
    public final static String BUSCAR_POR_NOMBRE = "Por Nombre";

    /**
     * Comando para buscar una banda por su horario de presentaci�n.
     */
    public final static String BUSCAR_POR_HORARIO = "Por Horario de Presentaci�n";

    /**
     * Comando para la opci�n 1.
     */
    public final static String OPC_1 = "Opci�n 1";

    /**
     * Comando para la opci�n 2.
     */
    public final static String OPC_2 = "Opci�n 2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia principal de la aplicaci�n.
     */
    private InterfazFestival interfaz;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n para agregar una banda.
     */
    private JButton btnAgregarBanda;

    /**
     * Bot�n para eliminar una banda.
     */
    private JButton btnEliminarBanda;

    /**
     * Bot�n para eliminar el escenario.
     */
    private JButton btnEliminarEscenario;
    
    /**
     * Combo box con las opciones de b�squeda.
     */
    private JComboBox comboBusqueda;

    /**
     * Bot�n para buscar una banda.
     */
    private JButton btnBuscar;

    /**
     * Bot�n para cargar.
     */
    private JButton btnCargar;

    /**
     * Bot�n para generar un reporte.
     */
    private JButton btnGenerarReporte;

    /**
     * Bot�n para llamar la opci�n 1.
     */
    private JButton btnOpc1;

    /**
     * Bot�n para llamar la opci�n 2.
     */
    private JButton btnOpc2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel con los botones y sus respectivos comandos.
     * @param pPrincipal instancia principal de la aplicaci�n.
     */
    public PanelOpciones( InterfazFestival pPrincipal )
    {
        interfaz = pPrincipal;
        setLayout( new BorderLayout( ) );       
        
        JPanel acciones = new JPanel( );
        acciones.setLayout( new GridLayout( 1, 3 ) );
        acciones.setBorder( new TitledBorder( "Acciones" ) );
        add(acciones, BorderLayout.NORTH);
        JPanel busqueda = new JPanel( );
        busqueda.setLayout( new GridLayout( 1, 2 ) );
        busqueda.setBorder( new TitledBorder( "Buscar una Banda" ) );
        add( busqueda, BorderLayout.CENTER );
        JPanel opciones = new JPanel( );
        opciones.setLayout( new GridLayout( 1, 4 ) );
        opciones.setBorder( new TitledBorder( "Opciones" ) );
        add( opciones, BorderLayout.SOUTH );

        btnAgregarBanda = new JButton( "Agregar Banda" );
        btnAgregarBanda.setActionCommand( AGREGAR_BANDA );
        btnAgregarBanda.addActionListener( this );
        acciones.add( btnAgregarBanda );

        btnEliminarBanda = new JButton( "Eliminar Banda" );
        btnEliminarBanda.setActionCommand( ELIMINAR_BANDA );
        btnEliminarBanda.addActionListener( this );
        acciones.add( btnEliminarBanda );

        btnEliminarEscenario = new JButton( "Eliminar Escenario" );
        btnEliminarEscenario.setActionCommand( ELIMINAR_ESCENARIO );
        btnEliminarEscenario.addActionListener( this );
        acciones.add( btnEliminarEscenario );       

        comboBusqueda = new JComboBox( );
        comboBusqueda.addItem( BUSCAR_POR_NOMBRE );
        comboBusqueda.addItem( BUSCAR_POR_HORARIO );
        busqueda.add( comboBusqueda );

        btnBuscar = new JButton( BUSCAR );
        btnBuscar.addActionListener( this );
        btnBuscar.setActionCommand( BUSCAR );
        busqueda.add( btnBuscar );

        btnCargar = new JButton( CARGAR );
        btnCargar.addActionListener( this );
        btnCargar.setActionCommand( CARGAR );
        opciones.add( btnCargar );

        btnGenerarReporte = new JButton( REPORTE );
        btnGenerarReporte.addActionListener( this );
        btnGenerarReporte.setActionCommand( REPORTE );
        opciones.add( btnGenerarReporte );

        btnOpc1 = new JButton( OPC_1 );
        btnOpc1.addActionListener( this );
        btnOpc1.setActionCommand( OPC_1 );
        opciones.add( btnOpc1 );

        btnOpc2 = new JButton( OPC_2 );
        btnOpc2.addActionListener( this );
        btnOpc2.setActionCommand( OPC_2 );
        opciones.add( btnOpc2 );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de eventos del usuario.
     * @param pEvento Evento de usuario. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( AGREGAR_BANDA ) )
        {
            interfaz.dialogoAgregarBanda( );
        }
        else if( comando.equals( ELIMINAR_BANDA ) )
        {
            interfaz.eliminarBanda( );
        }
        else if( comando.equals( ELIMINAR_ESCENARIO ) )
        {
            interfaz.eliminarEscenario( );
        }
        else if( comando.equals( BUSCAR ) )
        {
            String busqueda = ( String )comboBusqueda.getSelectedItem( );
            if( busqueda.equals( BUSCAR_POR_NOMBRE ) )
            {
                interfaz.buscarBandaPorNombre( );
            }
            else if( busqueda.equals( BUSCAR_POR_HORARIO ) )
            {
                interfaz.dialogoBuscarHorario( );
            }
        }
        else if( comando.equals( CARGAR ) )
        {
            interfaz.cargar( );
        }
        else if( comando.equals( REPORTE ) )
        {
            interfaz.generarReporte( );
        }
        else if( comando.equals( OPC_1 ) )
        {
            interfaz.reqFuncOpcion1( );
        }
        else if( comando.equals( OPC_2 ) )
        {
            interfaz.reqFuncOpcion2( );
        }
    }
}
