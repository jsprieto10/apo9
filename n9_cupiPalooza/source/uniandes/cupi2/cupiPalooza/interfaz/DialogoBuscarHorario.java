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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import uniandes.cupi2.cupiPalooza.mundo.Banda;

/**
 * Diálogo para introducir la información necesaria para buscar una banda por su horario de presentación.
 */
@SuppressWarnings("serial")
public class DialogoBuscarHorario extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para buscar.
     */
    public final static String BUSCAR = "Buscar";

    /**
     * Comando para cancelar el proceso.
     */
    public final static String CANCELAR = "Cancelar";
    
    /**
     * Comando para indicar el día 3.
     */
    public final static int TRES = 3;
    
    /**
     * Comando para indicar el día 4.
     */
    public final static int CUATRO = 4;
    
    /**
     * Comando para indicar el día 5.
     */
    public final static int CINCO = 5;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia principal de la aplicación.
     */
    private InterfazFestival interfaz;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta del día.
     */
    private JLabel lblDia;

    /**
     * Combo box con las opciones de día.
     */
    private JComboBox comboBusquedaDia;

    /**
     * Etiqueta de la hora.
     */
    private JLabel lblHora;

    /**
     * Campo de texto para la hora.
     */
    private JTextField txtHora;

    /**
     * Botón para buscar.
     */
    private JButton btnBuscar;

    /**
     * Botón para cancelar el proceso.
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la ventana de diálogo de la búsqueda.
     * @param pPrincipal Instancia principal de la aplicación.
     */
    public DialogoBuscarHorario( InterfazFestival pPrincipal )
    {
        interfaz = pPrincipal;
        setLayout( new BorderLayout( ) );
        setSize( 450, 250 );
        setTitle( "Buscar Banda Por Horario de Presentación" );
        setLocationRelativeTo( null );
        
        JLabel letrero = new JLabel("Introduzca el horario de presentación: ");
        letrero.setHorizontalAlignment( SwingConstants.CENTER );
        letrero.setBorder( new EmptyBorder( 15, 0, 0, 0 ) );
        add(letrero, BorderLayout.NORTH);

        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 2, 2 ) );
        campos.setBorder( new EmptyBorder( 30, 30, 30, 30 ) );
        add( campos, BorderLayout.CENTER );

        lblDia = new JLabel("Día de presentación: Noviembre ");
        campos.add( lblDia );
        comboBusquedaDia = new JComboBox( );
        comboBusquedaDia.addItem( TRES );
        comboBusquedaDia.addItem( CUATRO );
        comboBusquedaDia.addItem( CINCO );
        campos.add( comboBusquedaDia );
        lblHora = new JLabel( "Hora: " );
        campos.add( lblHora );
        txtHora = new JTextField( );
        campos.add( txtHora );
        
        JPanel botones = new JPanel( );
        botones.setBorder( new EmptyBorder( 0, 30, 20, 30 ) );
        botones.setLayout( new GridLayout( 1, 2 ) );
        add( botones, BorderLayout.SOUTH );

        btnBuscar = new JButton( "Buscar" );
        btnBuscar.setActionCommand( BUSCAR );
        btnBuscar.addActionListener( this );
        botones.add( btnBuscar );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );
        botones.add( btnCancelar );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de eventos del usuario.
     * @param pEvento Evento de usuario. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( BUSCAR ) )
        {
            int dia = Integer.parseInt( comboBusquedaDia.getSelectedItem( ).toString( ) );
            interfaz.buscarBandaPorHorarioPresentacion( dia, txtHora.getText( ), this );
        }
        else if( comando.equals( CANCELAR ) )
        {
            this.dispose( );
        }
        
    }
}
