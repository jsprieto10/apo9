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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import uniandes.cupi2.cupiPalooza.mundo.Banda;

/**
 * Panel para ver la información de las bandas en el escenario actual.
 */
@SuppressWarnings("serial")
public class PanelBandas extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Comando para ir a la siguiente banda.
     */
    public final static String SIGUIENTE = "Siguiente";
    
    /**
     * Comando para ir a la anterior banda.
     */
    public final static String ANTERIOR = "Anterior";

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
     * Imagen de la banda actual.
     */
    private JLabel imagenBanda;

    /**
     * Etiqueta con el nombre del escenario, mostrando el patrocinador.
     */
    private JLabel lblPatrocinador;

    /**
     * Etiqueta nombre.
     */
    private JLabel lblNombre;

    /**
     * Etiqueta cantidad de fans.
     */
    private JLabel lblFans;

    /**
     * Campo de texto para la cantidad de fans.
     */
    private JTextField txtFans;

    /**
     * Etiqueta cantidad de canciones.
     */
    private JLabel lblCanciones;

    /**
     * Campo de texto para la cantidad de canciones.
     */
    private JTextField txtCanciones;

    /**
     * Etiqueta costo.
     */
    private JLabel lblCosto;

    /**
     * Campo de texto para el costo.
     */
    private JTextField txtCosto;
    
    /**
     * Botón para ir a la siguiente banda.
     */
    private JButton btnSiguiente;
    
    /**
     * Botón para ir a la anterior banda.
     */
    private JButton btnAnterior;

    /**
     * Imagen de fondo actual en el panel.
     */
    private ImageIcon fondo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel con la información de las bandas en un escenario.
     * @param pPrincipal Instancia principal de la aplicación.
     */
    public PanelBandas( InterfazFestival pPrincipal )
    {
        interfaz = pPrincipal;
        fondo = new ImageIcon( "./data/imagenes/esc0.png" );
        setLayout( new BorderLayout( ) );

        JPanel info = new JPanel( );
        info.setForeground( null );
        info.setOpaque( false );
        info.setLayout( new BorderLayout( ) );

        lblPatrocinador = new JLabel( "" );
        lblPatrocinador.setForeground( Color.WHITE );
        lblPatrocinador.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        lblPatrocinador.setFont( new Font( "Arial", Font.BOLD, 24 ) );
        lblPatrocinador.setHorizontalAlignment( SwingConstants.CENTER );
        info.add( lblPatrocinador, BorderLayout.NORTH );

        JPanel cuadros1 = new JPanel( );
        cuadros1.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        cuadros1.setLayout( new BorderLayout( ) );
        cuadros1.setOpaque( false );
        info.add( cuadros1, BorderLayout.CENTER );

        JPanel cuadros2 = new JPanel( );
        cuadros2.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        cuadros2.setLayout( new GridLayout( 1, 7, 5, 5 ) );
        cuadros2.setOpaque( false );
        info.add( cuadros2, BorderLayout.SOUTH );

        lblNombre = new JLabel( " " );
        lblNombre.setForeground( Color.WHITE );
        lblNombre.setFont( new Font( "Arial", Font.BOLD, 18 ) );
        lblNombre.setHorizontalAlignment( SwingConstants.CENTER );
        cuadros1.add( lblNombre, BorderLayout.CENTER );

        lblFans = new JLabel( "Fans: " );
        lblFans.setForeground( Color.WHITE );
        lblFans.setHorizontalAlignment( SwingConstants.RIGHT );
        cuadros2.add( lblFans );
        txtFans = new JTextField( );
        txtFans.setEditable( false );
        cuadros2.add( txtFans );

        lblCanciones = new JLabel( "Canciones: " );
        lblCanciones.setForeground( Color.WHITE );
        lblCanciones.setHorizontalAlignment( SwingConstants.RIGHT );
        cuadros2.add( lblCanciones );
        txtCanciones = new JTextField( );
        txtCanciones.setEditable( false );
        cuadros2.add( txtCanciones );

        lblCosto = new JLabel( "Costo: " );
        lblCosto.setForeground( Color.WHITE );
        lblCosto.setHorizontalAlignment( SwingConstants.RIGHT );
        cuadros2.add( lblCosto );
        txtCosto = new JTextField( );
        txtCosto.setEditable( false );
        cuadros2.add( txtCosto );
        cuadros2.add( new JLabel() );
        
        btnAnterior = new JButton( "<<" );
        btnAnterior.setActionCommand( ANTERIOR );
        btnAnterior.addActionListener( this );
        
        btnSiguiente = new JButton( ">>" );
        btnSiguiente.setActionCommand( SIGUIENTE );
        btnSiguiente.addActionListener( this );

        JPanel centro = new JPanel( );
        centro.setLayout( new BorderLayout( ) );
        centro.setOpaque( false );
        add( centro, BorderLayout.CENTER );
        imagenBanda = new JLabel( );
        centro.add( imagenBanda, BorderLayout.CENTER );
        centro.add( info, BorderLayout.NORTH );
        centro.add( btnAnterior, BorderLayout.WEST );
        centro.add( btnSiguiente, BorderLayout.EAST );


    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el panel con la información que entra por parámetro.
     * @param pPrimera La primera banda del escenario.
     * @param pNumero Número del escenario.
     * @param pPatrocinador Nombre del patrocinador del escenario.
     */
    public void actualizar( Banda pPrimera, int pNumero, String pPatrocinador )
    {
        habilitar( );
        imagenBanda.setIcon( null );
        txtFans.setText( "" );
        txtCanciones.setText( "" );
        txtCosto.setText( "" );
        lblNombre.setText( " " );
        lblPatrocinador.setText( "Escenario " + pPatrocinador );
        Graphics graficos = this.getGraphics( );
        if( graficos != null )
        {
            fondo = new ImageIcon( "./data/imagenes/esc" + pNumero + ".png" );
            repaint( );
        }
        if(pPrimera != null)
        {
            cambiarMostrada( pPrimera );
        }

    }

    /**
     * Actualiza el panel a su vista inhabilitada.
     */
    public void inhabilitar( )
    {
        imagenBanda.setIcon( null );
        btnSiguiente.setEnabled( false );
        btnAnterior.setEnabled( false );
        txtFans.setText( "" );
        txtCanciones.setText( "" );
        txtCosto.setText( "" );
        lblPatrocinador.setText( " " );
        lblNombre.setText( " " );
        Graphics graficos = this.getGraphics( );
        if( graficos != null )
        {
            fondo = new ImageIcon( "./data/imagenes/esc0.png" );
            repaint( );
        }
    }

    /**
     * Actualiza el panel a su vista habilitada.
     */
    public void habilitar( )
    {
        btnSiguiente.setEnabled( true );
        btnAnterior.setEnabled( true );
    }

    /**
     * Pinta el panel y sus componentes.
     * @param pG Superficie del panel.
     */
    public void paintComponent( Graphics pG )
    {
        pG.drawImage( fondo.getImage( ), 0, 0, null );
        setOpaque( false );
        super.paintComponent( pG );
    }
    

    /**
     * Cambia la banda que se muestra.
     * @param bandaActual banda a mostrar.
     */
    public void cambiarMostrada( Banda bandaActual )
    {
        imagenBanda.setIcon( new ImageIcon( bandaActual.darRutaImagen( ) ) );
        lblNombre.setText( bandaActual.darNombre( ) + " - " + bandaActual.darHorarioPresentacion( ).toString().substring( 0, 16 ) );
        txtFans.setText( bandaActual.darCantidadDeFans( ) + "" );
        txtCanciones.setText( bandaActual.darCantidadDeCanciones( ) + "" );
        NumberFormat formatter = NumberFormat.getInstance( );
        String moneyString = formatter.format( bandaActual.darCosto( ) );
        txtCosto.setText( "$" + moneyString );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if(comando.equals( ANTERIOR ))
        {
            interfaz.anterior();
        }
        else if( comando.equals( SIGUIENTE ) )
        {
            interfaz.siguiente( );
        }
    }


}
