package nl.sogyo.mancala;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GameState {
    JFrame frame;
    JPanel panel;
    JTextArea jTextArea = new JTextArea(5,20);
    JButton [] buttons = new JButton[14];
    int frameWidth = 700;
    int frameHeight = 550;
    Pit StartGameBoard = new Pit();
    
    void createGUIFrame(){
        
        CapturePane capturePane = new CapturePane();
        System.setOut(new PrintStream(new StreamCapturer("Game Referee:  ", capturePane, System.out)));
        capturePane.setAutoscrolls(true);
        /*frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth,frameHeight);

        panel = new JPanel();
        frame.add(panel);

        frame.setVisible(true);
        CreateAndUpdateAllbuttons();
        frame.repaint(); */
        
        frame = new JFrame("Mancala");
        frame.setSize(frameWidth,frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        //JScrollPane theList = new JScrollPane(jTextArea);
        JScrollPane theList = new JScrollPane(capturePane);
        theList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        theList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //Box textBox = new Box(BoxLayout.Y_AXIS);  NOT SURE IF THIS IS NEEDED
        //textBox.add(theList);                     SAME HERE
        
        
        background.add(BorderLayout.SOUTH, theList);
        //background.add(BorderLayout.WEST, nameBox);
        frame.getContentPane().add(background);
        
        
        panel = new JPanel();
        background.add(panel);
        //CreateAndUpdateAllbuttons();


        startNewGame();
        frame.setVisible(true);
        frame.repaint();
    }
    
    void startNewGame(){
        JButton newGameButton = new JButton("New Game");
        newGameButton.setBounds(50, 50, 100, 50);
        newGameButton.setLayout(null);
        newGameButton.addActionListener(new NewGameButtonListener());
        panel.add(newGameButton);
    }
    
    void CreateAndUpdateAllbuttons(){
        panel.removeAll(); 
        BoardMember loopThroughBoard = StartGameBoard;
        JLabel name;
        int buttonXPosition = 150;
        int buttonYPosition = 50;
        int labelXPosition = 50;
        int numberOfBoardMembers = 0;

        while (numberOfBoardMembers < 14){
            buttons[numberOfBoardMembers] = new JButton(Integer.toString(loopThroughBoard.getTotalStones()));
            buttons[numberOfBoardMembers].putClientProperty("boardMember", loopThroughBoard);
            buttons[numberOfBoardMembers].addActionListener(new ButtonClickListener());
            buttons[numberOfBoardMembers].setBounds(buttonXPosition, buttonYPosition, 50, 50);
            buttons[numberOfBoardMembers].setLayout(null);
            
            switch (numberOfBoardMembers) {
                case 6:
                    name = new JLabel(loopThroughBoard.getClass().getSimpleName() + " Player 1");
                    break;
                case 13:
                    name = new JLabel(loopThroughBoard.getClass().getSimpleName() + " Player 2");
                    break;
                default:
                    name = new JLabel(loopThroughBoard.getClass().getSimpleName() + " " + (numberOfBoardMembers+1));
                    break;
            }
            
            //reCollor active Players buttons
            if (loopThroughBoard.getOwner().getIsActiveTurn() == false){
                buttons[numberOfBoardMembers].setBackground(Color.GRAY);
            } else if (loopThroughBoard.getTotalStones() == 0){
                buttons[numberOfBoardMembers].setBackground(Color.LIGHT_GRAY);
            }
            
            name.setBounds(labelXPosition, buttonYPosition, 100, 50);
            name.setLayout(null);
            panel.add(name);
            panel.add(buttons[numberOfBoardMembers]);
            
            loopThroughBoard = loopThroughBoard.getNeighbour();
             numberOfBoardMembers++;
            if (numberOfBoardMembers == 7){
                buttonXPosition += 50;                   
                buttonYPosition -= 100;
                labelXPosition = buttonXPosition + 75;
            }
            if (numberOfBoardMembers > 7){
                buttonYPosition -= 50;
            } else {
                buttonYPosition +=50;
            }
        }
    }

    class ButtonClickListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
        JButton pitSelected = (JButton) event.getSource();
        BoardMember buttonsLinkedTo = (BoardMember) pitSelected.getClientProperty("boardMember");
        buttonsLinkedTo.pickAndPlayPit();
        CreateAndUpdateAllbuttons();
        frame.repaint();
        }
    }
    
    class NewGameButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent a) {
            CreateAndUpdateAllbuttons();
            frame.repaint();
        }

    }
    
    
    
    
    
public static class CapturePane extends JPanel implements Consumer {

    private JLabel output;

    public CapturePane() {
        setLayout(new BorderLayout());
        output = new JLabel("<html>");
        add(new JScrollPane(output));
    }

    @Override
    public void appendText(final String text) {
        if (EventQueue.isDispatchThread()) {
            output.setText(output.getText() + text + "<br>");
        } else {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    appendText(text);
                }
            });

        }
    }        
}

public interface Consumer {        
    public void appendText(String text);        
}


public static class StreamCapturer extends OutputStream {

    private StringBuilder buffer;
    private String prefix;
    private Consumer consumer;
    private PrintStream old;

    public StreamCapturer(String prefix, Consumer consumer, PrintStream old) {
        this.prefix = prefix;
        buffer = new StringBuilder(128);
        buffer.append("[").append(prefix).append("] ");
        this.old = old;
        this.consumer = consumer;
    }

    @Override
    public void write(int b) throws IOException {
        char c = (char) b;
        String value = Character.toString(c);
        buffer.append(value);
        if (value.equals("\n")) {
            consumer.appendText(buffer.toString());
            buffer.delete(0, buffer.length());
            buffer.append("[").append(prefix).append("] ");
        }
        old.print(c);
    }        
}
}
