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
 * Diálogo para introducir la información necesaria para asignar una banda a un escenario.
 */
@SuppressWarnings("serial")
public class DialogoCrearBanda extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para escoger una imagen del selector de archivos.
     */
    public final static String IMAGEN = "Imagen";

    /**
     * Comando para agregar la banda.
     */
    public final static String AGREGAR = "Agregar Banda";

    /**
     * Comando para cancelar el proceso.
     */
    public final static String CANCELAR = "Cancelar";

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
     * Etiqueta del nombre.
     */
    private JLabel lblNombre;

    /**
     * Campo de texto para el nombre.
     */
    private JTextField txtNombre;

    /**
     * Etiqueta de la cantidad de fans.
     */
    private JLabel lblCantidadDeFans;

    /**
     * Campo de texto para la cantidad de fans.
     */
    private JTextField txtCantidadDeFans;

    /**
     * Etiqueta de la cantidad de canciones.
     */
    private JLabel lblCantidadDeCanciones;

    /**
     * Campo de texto para la cantidad de canciones.
     */
    private JTextField txtCantidadDeCanciones;

    /**
     * Etiqueta del costo.
     */
    private JLabel lblCosto;

    /**
     * Campo de texto para el costo.
     */
    private JTextField txtCosto;
    
    /**
     * Etiqueta del día de presentación.
     */
    private JLabel lblDia;

    /**
     * Radio botón para escoger el día 3 de Noviembre.
     */
    private JRadioButton rbtnDia3;

    /**
     * Radio botón para escoger el día 4 de Noviembre.
     */
    private JRadioButton rbtnDia4;

    /**
     * Radio botón para escoger el día 5 de Noviembre.
     */
    private JRadioButton rbtnDia5;
    
    /**
     * Etiqueta de la hora.
     */
    private JLabel lblHora;

    /**
     * Campo de texto para la hora.
     */
    private JTextField txtHora;

    /**
     * Etiqueta de la ruta de la imagen.
     */
    private JLabel lblImagen;

    /**
     * Campo de texto para la ruta de la imagen.
     */
    private JTextField txtImagen;

    /**
     * Botón para cargar una imagen.
     */
    private JButton btnImagen;

    /**
     * Botón para agregar la banda.
     */
    private JButton btnAgregar;

    /**
     * Botón para cancelar el proceso.
     */
    private JButton btnCancelar;


    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la ventana de diálogo de la banda.
     * @param pPrincipal Instancia principal de la aplicación.
     */
    public DialogoCrearBanda( InterfazFestival pPrincipal )
    {
        interfaz = pPrincipal;
        setLayout( new BorderLayout( ) );
        setSize( 500, 320 );
        setTitle( "Agregar Banda" );
        setLocationRelativeTo( null );

        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 8, 2 ) );
        campos.setBorder( new EmptyBorder( 30, 20, 30, 20 ) );
        add( campos, BorderLayout.CENTER );

        lblNombre = new JLabel( "Nombre: " );
        campos.add( lblNombre );
        txtNombre = new JTextField( );
        campos.add( txtNombre );
        lblCantidadDeFans = new JLabel( "Cantidad de Fans: " );
        campos.add( lblCantidadDeFans );
        txtCantidadDeFans = new JTextField( );
        campos.add( txtCantidadDeFans );
        lblCantidadDeCanciones = new JLabel( "Cantidad de Canciones: " );
        campos.add( lblCantidadDeCanciones );
        txtCantidadDeCanciones = new JTextField( );
        campos.add( txtCantidadDeCanciones );
        lblCosto = new JLabel( "Costo: " );
        campos.add( lblCosto );
        txtCosto = new JTextField( );
        campos.add( txtCosto );
        lblDia = new JLabel("Día de presentación: Noviembre ");
        campos.add( lblDia );
        JPanel radioBotones = new JPanel();
        radioBotones.setLayout( new GridLayout( 1, 3 ) );
        rbtnDia3=new JRadioButton("3",true);
        radioBotones.add( rbtnDia3 );
        rbtnDia4=new JRadioButton("4",false);
        radioBotones.add( rbtnDia4 );
        rbtnDia5=new JRadioButton("5",false);
        radioBotones.add( rbtnDia5 );
        ButtonGroup grupoBotones = new ButtonGroup();
        grupoBotones.add(rbtnDia3);
        grupoBotones.add(rbtnDia4);
        grupoBotones.add(rbtnDia5);
        campos.add( radioBotones );
        lblHora = new JLabel( "Hora: " );
        campos.add( lblHora );
        txtHora = new JTextField( );
        campos.add( txtHora );
        lblImagen = new JLabel( "Ruta Imagen: " );
        campos.add( lblImagen );
        txtImagen = new JTextField( );
        txtImagen.setEditable( false );
        campos.add( txtImagen );
        campos.add( new JLabel( ) );
        btnImagen = new JButton( "Cargar Imagen" );
        btnImagen.setActionCommand( IMAGEN );
        btnImagen.addActionListener( this );
        campos.add( btnImagen );

        JPanel botones = new JPanel( );
        botones.setBorder( new EmptyBorder( 0, 30, 20, 30 ) );
        botones.setLayout( new GridLayout( 1, 2 ) );
        add( botones, BorderLayout.SOUTH );

        btnAgregar = new JButton( "Agregar" );
        btnAgregar.setActionCommand( AGREGAR );
        btnAgregar.addActionListener( this );
        botones.add( btnAgregar );

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

        if( comando.equals( AGREGAR ) )
        {
            int dia = 3;
            if(rbtnDia4.isSelected( ))
            {
                dia = 4;
            }
            else if(rbtnDia5.isSelected( ))
            {
                dia = 5;
            }
            interfaz.agregarBanda( txtNombre.getText( ), txtCantidadDeFans.getText( ), txtCantidadDeCanciones.getText( ), txtCosto.getText( ), dia, txtHora.getText( ), txtImagen.getText( ), this );
        }
        else if( comando.equals( CANCELAR ) )
        {
            this.dispose( );
        }
        else if( comando.equals( IMAGEN ) )
        {
            JFileChooser fc = new JFileChooser( "./data/imagenes/bandas" );
            fc.showOpenDialog( this );

            if( fc.getSelectedFile( ) != null )
            {
                txtImagen.setText( fc.getSelectedFile( ).getPath( ) );
            }
        }

    }
}
