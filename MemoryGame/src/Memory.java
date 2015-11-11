import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import static java.awt.Color.*;

public class Memory extends JFrame {

    private String themePath = "res\\themes\\minions\\";
    private JLabel output = new JLabel();
    private JLabel guess = new JLabel();
    private JLabel[] boxLabel = new JLabel[26];
    private ImageIcon card1 = new ImageIcon(themePath + "card1.jpg");
    private ImageIcon card2 = new ImageIcon(themePath + "card2.jpg");
    private ImageIcon card3 = new ImageIcon(themePath + "card3.jpg");
    private ImageIcon card4 = new ImageIcon(themePath + "card4.jpg");
    private ImageIcon card5 = new ImageIcon(themePath + "card5.jpg");
    private ImageIcon card6 = new ImageIcon(themePath + "card6.jpg");
    private ImageIcon card7 = new ImageIcon(themePath + "card7.jpg");
    private ImageIcon card8 = new ImageIcon(themePath + "card8.jpg");
    private ImageIcon card9 = new ImageIcon(themePath + "card9.jpg");
    private ImageIcon card10 = new ImageIcon(themePath + "card10.jpg");
    private ImageIcon card11 = new ImageIcon(themePath + "card11.jpg");
    private ImageIcon card12 = new ImageIcon(themePath + "card12.jpg");
    private ImageIcon card13 = new ImageIcon(themePath + "card13.jpg");
    private ImageIcon back = new ImageIcon(themePath + "back.jpg");

    private ImageIcon[] choiceIcon = new ImageIcon[13];
    private static JButton newButton = new JButton();
    private static JButton exitButton = new JButton();

    private int choice;
    private int index;
    private int[] picked = new int[2];
    private int[] behind = new int[26];
    private int guessesCounter;
    private int remaining;
    private Timer timer;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Cards Memory Game");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.WHITE);
        JButton newGame = new JButton();
        JLabel text = new JLabel();
        JButton exit = new JButton();

        ImageIcon icon = new ImageIcon("res/themes/Yoda/bgmain.jpg");
        JLabel bg = new JLabel();

        JRadioButton minionsTheme = new JRadioButton("Minions easy");
        minionsTheme.setBackground(Color.WHITE);
        JRadioButton minionsThemeMedium = new JRadioButton("Minions medium");
        minionsThemeMedium.setBackground(Color.WHITE);
        JRadioButton minionsThemeExpert = new JRadioButton("Minions expert");
        minionsThemeExpert.setBackground(Color.WHITE);
        JRadioButton pandaTheme = new JRadioButton("KungFu easy");
        pandaTheme.setBackground(Color.WHITE);
        JRadioButton pandaThemeMedium = new JRadioButton("KungFu medium");
        pandaThemeMedium.setBackground(Color.WHITE);
        JRadioButton pandaThemeExpert = new JRadioButton("KungFu expert");
        pandaThemeExpert.setBackground(Color.WHITE);
        JRadioButton yodaTheme = new JRadioButton("StarWars easy");
        yodaTheme.setBackground(Color.WHITE);
        JRadioButton yodaThemeMedium = new JRadioButton("StarWars medium");
        yodaThemeMedium.setBackground(Color.WHITE);
        JRadioButton yodaThemeExpert = new JRadioButton("StarWars expert");
        yodaThemeExpert.setBackground(Color.WHITE);

        ButtonGroup themes = new ButtonGroup();
        themes.add(minionsTheme);
        themes.add(minionsThemeMedium);
        themes.add(minionsThemeExpert);
        themes.add(pandaTheme);
        themes.add(pandaThemeMedium);
        themes.add(pandaThemeExpert);
        themes.add(yodaTheme);
        themes.add(yodaThemeMedium);
        themes.add(yodaThemeExpert);
        bg.setIcon(icon);
        newGame.setText("New Game");
        text.setText("Best score: " + bestScore("scores/score.txt"));
        exit.setText("Exit");
        newGame.setPreferredSize(new Dimension(200, 60));
        exit.setPreferredSize(new Dimension(200, 60));
        yodaTheme.setSelected(true);
        pandaTheme.getModel().setEnabled(true);
        panel.add(minionsTheme);
        panel.add(minionsThemeMedium);
        panel.add(minionsThemeExpert);
        panel.add(pandaTheme);
        panel.add(pandaThemeMedium);
        panel.add(pandaThemeExpert);
        panel.add(yodaTheme);
        panel.add(yodaThemeMedium);
        panel.add(yodaThemeExpert);
        panel.add(newGame);
        panel.add(text);
        panel.add(exit);
        panel.add(bg);
        frame.add(panel);
        frame.setSize(1200, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        newGame.addActionListener(e -> {
            if (minionsTheme.isSelected()) {
                Memory newGame1 = new Memory(1, 12);
                newGame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else if (pandaTheme.isSelected()) {
                Memory newGame1 = new Memory(2, 12);
                newGame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else if (yodaTheme.isSelected()) {
                Memory newGame1 = new Memory(3, 12);
                newGame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else if (minionsThemeMedium.isSelected()) {
                Memory newGame1 = new Memory(4, 16);
                newGame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else if (minionsThemeExpert.isSelected()) {
                Memory newGame1 = new Memory(5, 20);
                newGame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else if (pandaThemeMedium.isSelected()) {
                Memory newGame1 = new Memory(6, 16);
                newGame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else if (pandaThemeExpert.isSelected()) {
                Memory newGame1 = new Memory(7, 20);
                newGame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else if (yodaThemeMedium.isSelected()) {
                Memory newGame1 = new Memory(8, 16);
                newGame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else if (yodaThemeExpert.isSelected()) {
                Memory newGame1 = new Memory(9, 20);
                newGame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            //start the game
            newButton.doClick();
        });

        exit.addActionListener(e -> System.exit(0));
    }

    public Memory(int themeNumber, int numOfCards) {
        //FullScreen
        setUndecorated(true);
        setVisible(true);
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
        setBounds(maximumWindowBounds);

        //put Theme
        changeTheme(themeNumber);

        setTitle("Cards Memory Game");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(new GridBagLayout());

        //position controls
        GridBagConstraints gridConstraints = new GridBagConstraints();
        guess.setText("Guesses: 0");
        guess.setForeground(GREEN);
        guess.setFont(new Font("Arial", Font.BOLD, 18));
        guess.setVisible(true);
        getContentPane().add(guess);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(10, 10, 10, 10);

        boxLabel[0] = new JLabel();
        boxLabel[1] =  new JLabel();
        boxLabel[2] =  new JLabel();
        boxLabel[3] =  new JLabel();
        boxLabel[4] =  new JLabel();
        boxLabel[5] =  new JLabel();
        boxLabel[6] =  new JLabel();
        boxLabel[7] =  new JLabel();
        boxLabel[8] =  new JLabel();
        boxLabel[9] =  new JLabel();
        boxLabel[10] = new JLabel();
        boxLabel[11] = new JLabel();
        boxLabel[12] = new JLabel();
        boxLabel[13] = new JLabel();
        boxLabel[14] = new JLabel();
        boxLabel[15] = new JLabel();
        boxLabel[16] = new JLabel();
        boxLabel[17] = new JLabel();
        boxLabel[18] = new JLabel();
        boxLabel[19] = new JLabel();
        boxLabel[20] = new JLabel();
        boxLabel[21] = new JLabel();
        boxLabel[22] = new JLabel();
        boxLabel[23] = new JLabel();
        boxLabel[24] = new JLabel();
        boxLabel[25] = new JLabel();

        int x = 0;
        int y = 1;
        for (int i = 0; i < numOfCards; i++) {
            gridConstraints = new GridBagConstraints();
            boxLabel[i].setPreferredSize(new Dimension(80, 80));
            boxLabel[i].setIcon(back);
            gridConstraints.gridx = x;
            gridConstraints.gridy = y;
            gridConstraints.insets = new Insets(5, 5, 5, 5);
            getContentPane().add(boxLabel[i], gridConstraints);
            //when user click on some card
            boxLabel[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    labelMouseClicked(e, numOfCards);
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

        guessesCounter = 0;
        remaining = numOfCards / 2;
        guess.setText("Guesses: 0");
        //Random sort 16 integers using Shuffle
        //Behind contains indexes 0 to 7 for hidden pictures
        behind = sortIntegers(numOfCards);
        for (int i = 0; i < numOfCards; i++) {
            //reset image
            boxLabel[i].setIcon(back);
            if (behind[i] > (numOfCards / 2) - 1) {
                behind[i] = behind[i] - (numOfCards / 2);
            }
        }
        choice = 0;
        newButton.setEnabled(false);

        exitButton.setText("Exit");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = numOfCards / 2 - 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(0, 5, 5, 5);
        getContentPane().add(exitButton, gridConstraints);
        exitButton.addActionListener(this::exitButtonActionPerformed);
        timer = new Timer(1, this::delayTimerActionPerformed);
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())),
                (int) (0.5 * (screenSize.height - getHeight())),
                (int) (0.5 * (screenSize.width - getWidth())),
                (int) (0.5 * (screenSize.width - getWidth())));

        choiceIcon[0] = card1;
        choiceIcon[1] = card2;
        choiceIcon[2] = card3;
        choiceIcon[3] = card4;
        choiceIcon[4] = card5;
        choiceIcon[5] = card6;
        choiceIcon[6] = card7;
        choiceIcon[7] = card8;
        choiceIcon[8] = card9;
        choiceIcon[9] = card10;
        choiceIcon[10] = card11;
        choiceIcon[11] = card12;
        choiceIcon[12] = card13;
    }

    private void labelMouseClicked(MouseEvent e, int numCards) {

        Component clickedComponent = e.getComponent();
        for (index = 0; index < numCards; index++) {
            if (clickedComponent == boxLabel[index]) {
                break;
            }
        }
        //If user try to pick already selected box
        if ((choice == 1 && index == picked[0]) ||
                behind[index] == -1) {
            newButton.doClick();
            return;
        }

        //Display selected picture
        boxLabel[index].setIcon(choiceIcon[behind[index]]);
        if (choice == 0) {
            picked[0] = index;
            choice = 1;
            return;
        }

        //Use the timer to process remaining code to allow label
        //control to refresh
        timer.start();
    }

    private void exitButtonActionPerformed(ActionEvent e) {
        if (exitButton.getText().equals("Exit")) {
            this.dispose(); //Close the program
        } else {
            newButton.setEnabled(true);
            exitButton.setText("Exit");

        }
    }

    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }

    private void delayTimerActionPerformed(ActionEvent e) {
        //finish processing of display
        timer.stop();
        guessesCounter++;
        guess.setText("Guesses: " + String.valueOf(guessesCounter));

        picked[1] = index;
//        if(guessesCounter == 20) {
//            if ((themeVersion == 5) || (themeVersion == 7) || (themeVersion == 9)) {
//                exitButton.doClick();
//                newButton.requestFocus();
//                gameOverMessage("No more guesses left! GAME OVER!");
//            }
//        } else if(guessesCounter == 24){
//            if((themeVersion == 4)||(themeVersion == 6)||(themeVersion == 8)){
//                exitButton.doClick();
//                newButton.requestFocus();
//                gameOverMessage("No more guesses left! GAME OVER!");
//            }
//        } else if(guessesCounter==28){
//            if((themeVersion==1)||(themeVersion==2)||(themeVersion==3)){
//                exitButton.doClick();
//                newButton.requestFocus();
//                gameOverMessage("No more guesses left! GAME OVER!");
//            }
         if (behind[picked[0]] == behind[picked[1]]) {
            behind[picked[0]] = -1;
            behind[picked[1]] = -1;
            remaining--;
            gameOverMessage("Good job!");
        } else {
            gameOverMessage("Try again!");
            //delay 1 second
            long t = System.currentTimeMillis();
            while (System.currentTimeMillis() - t < 1000){
                boxLabel[picked[0]].setIcon(back);
                boxLabel[picked[1]].setIcon(back);
            }
        }

        choice = 0;
        if (remaining == 0) {
            // save best score in a file
            try {
                // Create file
                File scoreFile = new File("scores/score.txt");
                PrintWriter out = new PrintWriter(new FileWriter(scoreFile, true));
                out.println("Score: " + guessesCounter);
                out.close();
            } catch (Exception err){
                System.err.println("Error: " + err.getMessage());
            }
            //exitButton.doClick();
            newButton.requestFocus();
            String outputText;
            if(guessesCounter <= 11){
                outputText = "That couldn't be true. You are so lucky, bro!!!";
                gameOverMessage(outputText);
            } else if (guessesCounter > 11 && guessesCounter <= 13){
                outputText = "Lol, nice game. You have really good memory!";
                gameOverMessage(outputText);
            } else if (guessesCounter >=14 && guessesCounter < 16){
                outputText = "Not bad, bravo!";
                gameOverMessage(outputText);
            } else if(guessesCounter >=17 && guessesCounter <= 19){
                outputText = "You need a little bit more concentration";
                gameOverMessage(outputText);
            } else {
                outputText = "Come on. You can do it better! Try again!";
                gameOverMessage(outputText);
            }
        }
    }

    private void gameOverMessage(String outputText) {
        output.setText(outputText);
        output.setForeground(RED);
        output.setFont(new Font("Arial Narrow",Font.BOLD, 25));
        output.setVisible(true);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = 5;
        gridConstraints.insets = new Insets(0,0,0,0);
        getContentPane().add(output, gridConstraints);
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

    private void changeTheme(int themeCode){

        switch (themeCode){
            case 1: themePath = "res\\themes\\minions\\";
                int themeVersion = 1;
                break;
            case 2: themePath = "res\\themes\\Kung Fu Panda\\";
                themeVersion = 2;
                break;
            case 3: themePath = "res\\themes\\Yoda\\";
                themeVersion = 3;
                break;
            case 4: themePath = "res\\themes\\minions\\";
                themeVersion = 4;
                break;
            case 5: themePath = "res\\themes\\minions\\";
                themeVersion = 5;
                break;
            case 6: themePath = "res\\themes\\Kung Fu Panda\\";
                themeVersion = 6;
                break;
            case 7: themePath = "res\\themes\\Kung Fu Panda\\";
                themeVersion = 7;
                break;
            case 8: themePath = "res\\themes\\Yoda\\";
                themeVersion = 8;
                break;
            case 9: themePath = "res\\themes\\Yoda\\";
                themeVersion = 9;
        }

        card1 = new ImageIcon(themePath + "card1.jpg");
        card2 = new ImageIcon(themePath + "card2.jpg");
        card3 = new ImageIcon(themePath + "card3.jpg");
        card4 = new ImageIcon(themePath + "card4.jpg");
        card5 = new ImageIcon(themePath + "card5.jpg");
        card6 = new ImageIcon(themePath + "card6.jpg");
        card7 = new ImageIcon(themePath + "card7.jpg");
        card8 = new ImageIcon(themePath + "card8.jpg");
        card9 = new ImageIcon(themePath + "card9.jpg");
        card10 = new ImageIcon(themePath + "card10.jpg");
        card11 = new ImageIcon(themePath + "card11.jpg");
        card12 = new ImageIcon(themePath + "card12.jpg");
        card13 = new ImageIcon(themePath + "card13.jpg");
        back = new ImageIcon(themePath + "back.jpg");
        setContentPane(new JLabel(new ImageIcon(themePath + "background.jpg"))); //Put Background image
    }

    private static int bestScore(String file) {
        ArrayList<Integer> scores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] text = line.split(" ");
                scores.add(Integer.parseInt(text[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(scores);
        return scores.get(0);
    }
}

class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}