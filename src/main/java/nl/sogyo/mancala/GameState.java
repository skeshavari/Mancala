package nl.sogyo.mancala;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

public class GameState {
    JFrame frame;
    JPanel panel;
    JTextArea jTextArea = new JTextArea();
    JButton [] buttons = new JButton[14];
    int frameWidth = 700;
    int frameHeight = 700;
    Pit StartGameBoard = new Pit();
    
    void createGUIFrame(){
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth,frameHeight);

        panel = new JPanel();
        frame.add(panel);

        frame.setVisible(true);
        CreateAndUpdateAllbuttons();
        frame.repaint();
    }
    
    void CreateAndUpdateAllbuttons(){
        panel.removeAll(); 
        BoardMember loopThroughBoard = StartGameBoard;
        JLabel name;
        int buttonXPosition = 150;
        int buttonYPosition = 150;
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
}