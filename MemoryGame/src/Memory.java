
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;

public class Memory extends JFrame {

    JLabel guess = new JLabel();
    JLabel label0 = new JLabel();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JLabel label4 = new JLabel();
    JLabel label5 = new JLabel();
    JLabel label6 = new JLabel();
    JLabel label7 = new JLabel();
    JLabel label8 = new JLabel();
    JLabel label9 = new JLabel();
    JLabel label10 = new JLabel();
    JLabel label11 = new JLabel();
    JLabel label12 = new JLabel();
    JLabel label13 = new JLabel();
    JLabel label14 = new JLabel();
    JLabel label15 = new JLabel();
    JLabel[] boxLabel = new JLabel[16];
    ImageIcon card1 = new ImageIcon("res\\card1.jpg");
    ImageIcon card2 = new ImageIcon("res\\card2.jpg");
    ImageIcon card3 = new ImageIcon("res\\card3.jpg");
    ImageIcon card4 = new ImageIcon("res\\card4.jpg");
    ImageIcon card5 = new ImageIcon("res\\card5.jpg");
    ImageIcon card6 = new ImageIcon("res\\card6.jpg");
    ImageIcon card7 = new ImageIcon("res\\card7.jpg");
    ImageIcon card8 = new ImageIcon("res\\card8.jpg");
    ImageIcon back = new ImageIcon("res\\back.jpg");
    ImageIcon[] choiceIcon = new ImageIcon[8];
    static JButton newButton = new JButton();
    JButton exitButton = new JButton();

    Random myRandom = new Random();
    int choice;
    int index;
    int[] picked = new int[2];
    int[] behind = new int[16];
    int guessesCounter;
    int remaining;
    static AudioClip successMatch;
    static AudioClip notSuccessMatch;
    Timer timer;

    public static void main(String[] args) {

        new Memory().setVisible(true);
        //get sounds
        try {
            successMatch = Applet.newAudioClip(new URL("file:" + "res\\Hellow.wav"));
            notSuccessMatch = Applet.newAudioClip(new URL("file:" + "res\\Banana Peel Slip.wav"));
        } catch (Exception e) {
        }
        //start the game
        newButton.doClick();
    }

    public Memory() {
        //frame constructor
        setTitle("Memory Game");
        //set Resizable false
        getContentPane().setBackground(Color.CYAN);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        //position controls
        GridBagConstraints gridConstraints = new GridBagConstraints();
        guess.setText("Guesses: 0");
        guess.setForeground(Color.WHITE);
        guess.setFont(new Font("Arial", Font.BOLD, 18));
        guess.setVerticalTextPosition(JLabel.BOTTOM);
        guess.setHorizontalAlignment(JLabel.CENTER);
        guess.setVisible(true);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        boxLabel[0] = label0;
        boxLabel[1] = label1;
        boxLabel[2] = label2;
        boxLabel[3] = label3;
        boxLabel[4] = label4;
        boxLabel[5] = label5;
        boxLabel[6] = label6;
        boxLabel[7] = label7;
        boxLabel[8] = label8;
        boxLabel[9] = label9;
        boxLabel[10] = label10;
        boxLabel[11] = label11;
        boxLabel[12] = label12;
        boxLabel[13] = label13;
        boxLabel[14] = label14;
        boxLabel[15] = label15;

        int x = 0;
        int y = 1;
        for (int i = 0; i < boxLabel.length; i++) {
            gridConstraints = new GridBagConstraints();
            boxLabel[i].setPreferredSize(new Dimension(130, 130));
            boxLabel[i].setIcon(back);
            gridConstraints.gridx = x;
            gridConstraints.gridy = y;
            gridConstraints.insets = new Insets(5,5,5,5);
            getContentPane().add(boxLabel[i], gridConstraints);
            //when user click on some card
            boxLabel[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    labelMouseClicked(e);
                }
            });
            x++;
            if (x > 3) {
                x = 0;
                y += 1;
            }
        }

        newButton.setText("New Game");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 6;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(newButton, gridConstraints);

        newButton.addActionListener(e -> {
            newButtonActionPerformed(e);
        });

        exitButton.setText("Exit");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 7;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(0,5, 5, 5);
        getContentPane().add(exitButton, gridConstraints);
        exitButton.addActionListener(e -> {
            exitButtonActionPerformed(e);
        });
        timer = new Timer(1, e -> {
            delayTimerActionPerfomed(e);
        });
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())),(int) (0.5 * (screenSize.width - getWidth())),(int) (0.5 * (screenSize.width - getWidth())));

        choiceIcon[0] = card1;
        choiceIcon[1] = card2;
        choiceIcon[2] = card3;
        choiceIcon[3] = card4;
        choiceIcon[4] = card5;
        choiceIcon[5] = card6;
        choiceIcon[6] = card7;
        choiceIcon[7] = card8;
    }

    private void labelMouseClicked(MouseEvent e) {

        Component clickedComponent = e.getComponent();
        for (index = 0; index < boxLabel.length; index++) {
            if (clickedComponent == boxLabel[index]) {
                break;
            }
        }
        //If user try to pick already selected box
        if ((choice == 1 && index == picked[0]) ||
                behind[index] == -1) {
            //?newButton.doClick())
            return;
        }

        //Display selected picture
        boxLabel[index].setIcon(choiceIcon[behind[index]]);
        if (choice == 0) {
            picked[0] = index;
            choice = 1;
            return;
        }

        //Use the timer to proccess remaining code to allow label
        //control to refresh
        timer.start();
    }

    private void newButtonActionPerformed(ActionEvent e) {
        guessesCounter = 0;
        remaining = 8;
        guess.setText("Guesses: 0");
        //Random sort 16 integers using Shuffle
        //Behind contains indexes 0 to 7 for hidden pictures
        behind = sortIntegers(16);
        for (int i = 0; i < boxLabel.length; i++) {
            //reset image
            boxLabel[i].setIcon(back);
            if (behind[i] > 7) {
                behind[i] = behind[i] - 8;
            }
        }
        choice = 0;
        newButton.setEnabled(false);
        exitButton.setText("Stop");
    }

    private void exitButtonActionPerformed(ActionEvent e) {
        if (exitButton.getText().equals("Exit")) {
            exitForm(null);
        } else {
            exitButton.setText("Exit");
            newButton.setEnabled(true);
        }
    }

    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }

    private void delayTimerActionPerfomed(ActionEvent e) {
        //finish proccessing of display

        timer.stop();
        guessesCounter++;
        guess.setText("Guesses: " + String.valueOf(guessesCounter));
        picked[1] = index;
        if (behind[picked[0]] == behind[picked[1]]) {
            //if match, play sound
            successMatch.play();
            behind[picked[0]] = -1;
            behind[picked[1]] = -1;
            remaining--;
        } else {
            //if no match, blank picture, restore backs
            notSuccessMatch.play();

            //delay 1 second
            long t = System.currentTimeMillis();
            do {
            } while (System.currentTimeMillis() - t < 1000);
            boxLabel[picked[0]].setIcon(back);
            boxLabel[picked[1]].setIcon(back);
        }
        choice = 0;
        if (remaining == 0) {
            exitButton.doClick();
            newButton.requestFocus();
        }
    }

    private int[] sortIntegers(int n) {
        //return n randomly sorted integer 0 -> n - 1

        int nArray[] = new int[n];
        int temp, s;
        Random myRandom = new Random();
        //initialize array from 0 to n - 1
        for (int i = 0; i < n; i++) {
            nArray[i] = i;
        }
        //i is number ot items remaining in list
        for (int i = n; i >= 1; i--) {
            s = myRandom.nextInt(i);
            temp = nArray[s];
            nArray[s] = nArray[i - 1];
            nArray[i - 1] = temp;
        }
        return (nArray);
    }
}