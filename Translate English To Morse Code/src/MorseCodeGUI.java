/**
 * Created by zeronoob on 24.2.2017 г..
 */


/* GUI */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class MorseCodeGUI extends JFrame {


    private static final long serialVersionUID = 1L;

    /* Variable Declaration  */
    private  Container container;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem exitItem;
    private JMenuItem howToItem;
    private JMenuItem aboutItem;

    private JSplitPane splitPane;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;

    private JButton toMorseCode;
    private JButton toEnglish;
    private JButton clearTextArea;

    private JLabel englishLabel;
    private JLabel morseLabel;

    private JTextArea englishTextArea;
    private JTextArea morseTextArea;

    private JScrollPane scroll1;
    private JScrollPane scroll2;

    private EnglishToMorse engToMorse;
    private MorseToEnglish morseToEng;

    private String string;

    /* Constructor */
    public MorseCodeGUI() {

        super( "Morse Code" );

        initComponents();

        setSize( 515, 400 );
        setResizable( false );
        setLocationRelativeTo( null );
        setVisible(true);
    }

    /*Initialize GUI components*/
    public void initComponents()
    {
        container = getContentPane();
        container.setLayout( new BorderLayout() );

    /* Create Menu bar */
        menuBar = new JMenuBar();

    /* Create File menu */
        fileMenu = new JMenu( "File" );
        fileMenu.setMnemonic( 'F' );
        fileMenu.setToolTipText( "File Menu" );

    /* Create  Exit menu item */
        exitItem = new JMenuItem( "Exit" );
        exitItem.setMnemonic( 'x' );
        exitItem.setToolTipText( "Exit application" );
        fileMenu.add( exitItem );

        menuBar.add( fileMenu );

    /* Create  Help menu */
        helpMenu = new JMenu( "Help" );
        helpMenu.setMnemonic( 'H' );
        helpMenu.setToolTipText( "Help Menu" );


    /*Create  How to use menu item */
        howToItem = new JMenuItem( "How to Use" );
        howToItem.setMnemonic( 'U' );
        howToItem.setToolTipText( "How to Use" );
        helpMenu.add( howToItem );

    /* Create About menu item */
        aboutItem = new JMenuItem( "About" );
        aboutItem.setMnemonic( 'A' );
        aboutItem.setToolTipText( "About" );
        helpMenu.add( aboutItem );

        menuBar.add( helpMenu );
        setJMenuBar( menuBar );

    /* Create left panel */
        leftPanel = new JPanel();
        leftPanel.setLayout( new BorderLayout() );

    /* Create English label */
        englishLabel = new JLabel( "Enter your English sentences:" );
        leftPanel.add( englishLabel, BorderLayout.NORTH );

    /* Create English Textarea */
        englishTextArea = new JTextArea();
        englishTextArea.setWrapStyleWord( true );
        englishTextArea.setLineWrap( true );
        scroll1 = new JScrollPane( englishTextArea );
        scroll1.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scroll1.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        leftPanel.add( scroll1, BorderLayout.CENTER );

    /* Create right panel */
        rightPanel = new JPanel();
        rightPanel.setLayout( new BorderLayout() );

    /* Create Morse Code label */
        morseLabel = new JLabel( "Enter your Morse Code:" );
        rightPanel.add( morseLabel, BorderLayout.NORTH );

    /* Create Morse Code Text area */
        morseTextArea = new JTextArea();
        morseTextArea.setWrapStyleWord( true );
        morseTextArea.setLineWrap( true );
        morseTextArea.setFont( new Font( "Arial", Font.BOLD, 16 ) );
        morseTextArea.setToolTipText( "Separate each Morse-coded letter " +
                "by a space & each Morse-coded word by space-\\w-space" );
        scroll2 = new JScrollPane( morseTextArea );
        scroll2.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scroll2.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        rightPanel.add( scroll2, BorderLayout.CENTER );


    /* Create split pane */
        splitPane = new JSplitPane( );
        splitPane.setOrientation( JSplitPane.HORIZONTAL_SPLIT );
        splitPane.setOneTouchExpandable( true );
        splitPane.setLeftComponent( leftPanel );
        splitPane.setRightComponent( rightPanel );
        splitPane.setDividerLocation( 257 );
        container.add( splitPane, BorderLayout.CENTER );

    /* Create Convert to Morse Code button */
        toMorseCode = new JButton( "Convert to Morse Code" );
        toMorseCode.setMnemonic( 'M' );
        toMorseCode.setToolTipText( "Convert English to Morse Code" );

    /*Create Convert to English  button */
        toEnglish = new JButton( "Convert to English" );
        toEnglish.setMnemonic( 'g' );
        toEnglish.setToolTipText( "Convert Morse Code to English" );

    /*Create Clear Area button */
        clearTextArea = new JButton( "Clear" );
        clearTextArea.setMnemonic( 'C' );
        clearTextArea.setToolTipText( "Clear both text areas" );


    /* Attach buttons to south panel */
        bottomPanel = new JPanel();
        bottomPanel.setLayout( new FlowLayout() );
        bottomPanel.add( toMorseCode );
        bottomPanel.add( toEnglish );
        bottomPanel.add( clearTextArea );
        container.add( bottomPanel, BorderLayout.PAGE_END );

    /* Action Event Listener */
        ActionEventHandler handler = new ActionEventHandler();
        exitItem.addActionListener( handler );
        howToItem.addActionListener( handler );
        aboutItem.addActionListener( handler );
        toMorseCode.addActionListener( handler );
        toEnglish.addActionListener( handler );
        clearTextArea.addActionListener( handler );

    /* Instantiate classes EnglishToMorse & MorseToEnglish */
        engToMorse = new EnglishToMorse();
        morseToEng = new MorseToEnglish();

    }

    /* Action Event Handler */
    private class ActionEventHandler implements ActionListener
    {
        public void actionPerformed ( ActionEvent e)
        {
      /* Convert to Morse Code button */
            if ( e.getSource() == toMorseCode )
            {
                String stringToTokenize = englishTextArea.getText();

        /* Use class StringTokenizer to break a string
         * into individual words */

                StringTokenizer tokens = new StringTokenizer( stringToTokenize );
                char charArray[];
                string = new String();

                morseTextArea.setText( "" );

                while( tokens.hasMoreTokens() )
                {
                    string = tokens.nextToken();
                    charArray = new char[ string.length() ];

          /* Convert a string of word into character array
           *  to get the individual character of a word */


                    charArray = string.toCharArray();

                    for ( int i = 0; i < charArray.length; i++ )
                    {
                        morseTextArea.append( engToMorse.convertToMorse( charArray[ i ] ) + " " );
                    }

                    morseTextArea.append( "   " );
                }
            }

      /* Convert to English button */
            else if ( e.getSource() == toEnglish )
            {
                String stringToTokenize = morseTextArea.getText();

        /* Use class StringTokenizer to break a string
         *  into individual words(използваме класa StringTokenizer
         */

                StringTokenizer tokens = new StringTokenizer( stringToTokenize );
                string = new String();

                englishTextArea.setText( "" );

                while( tokens.hasMoreTokens() )
                {
                    string = tokens.nextToken();

                    englishTextArea.append( morseToEng.convertToEnglish( string ) );
                }

            }

      /* Clear Area button */
            else if ( e.getSource() == clearTextArea )
            {
                englishTextArea.setText( "" );
                morseTextArea.setText( "" );
            }

      /*Exit menu item */
            else if ( e.getSource() == exitItem )
            {
                System.exit( 0 );
            }

      /* How to use menu item */
            else if ( e.getSource() == howToItem )
            {
                String string = "Enter a sentence in English or Morse code to get the equivalent translation.";
                JOptionPane.showMessageDialog( null, string, "How To Use",
                        JOptionPane.INFORMATION_MESSAGE );
            }
            /** About menu item */
            else if ( e.getSource() == aboutItem )
            {
                String string = " Translate Morse Code Ver. 0.1\n created by Ivan Ivanov\n"
                        + "ivan_a_ivanov@hotmail.com";
                JOptionPane.showMessageDialog( null, string, "About",
                        JOptionPane.INFORMATION_MESSAGE );

            }

        }

    }

}
