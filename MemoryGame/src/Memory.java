import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;

public class Memory extends JFrame {

    private int themeRegulator;
    private String themePath = "res\\themes\\minions\\";
    private int themeVersion;
    private JLabel output = new JLabel();
    private JLabel guess = new JLabel();
    private JLabel label0 = new JLabel();
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel label3 = new JLabel();
    private JLabel label4 = new JLabel();
    private JLabel label5 = new JLabel();
    private JLabel label6 = new JLabel();
    private JLabel label7 = new JLabel();
    private JLabel label8 = new JLabel();
    private JLabel label9 = new JLabel();
    private JLabel label10 = new JLabel();
    private JLabel label11 = new JLabel();
    private JLabel label12 = new JLabel();
    private JLabel label13 = new JLabel();
    private JLabel label14 = new JLabel();
    private JLabel label15 = new JLabel();
    private JLabel[] boxLabel = new JLabel[16];
    private ImageIcon card1 = new ImageIcon(themePath + "card1.jpg");
    private ImageIcon card2 = new ImageIcon(themePath + "card2.jpg");
    private ImageIcon card3 = new ImageIcon(themePath + "card3.jpg");
    private ImageIcon card4 = new ImageIcon(themePath + "card4.jpg");
    private ImageIcon card5 = new ImageIcon(themePath + "card5.jpg");
    private ImageIcon card6 = new ImageIcon(themePath + "card6.jpg");
    private ImageIcon card7 = new ImageIcon(themePath + "card7.jpg");
    private ImageIcon card8 = new ImageIcon(themePath + "card8.jpg");
    private ImageIcon back = new ImageIcon(themePath + "back.jpg");
    private ImageIcon[] choiceIcon = new ImageIcon[8];
    private static JButton newButton = new JButton();
    private JButton exitButton = new JButton();

    private Random myRandom = new Random();
    private int choice;
    private int index;
    private int[] picked = new int[2];
    private int[] behind = new int[16];
    private int guessesCounter;
    private int remaining;
    private static AudioClip successMatch;
    private static AudioClip notSuccessMatch;
    private Timer timer;
    private boolean firstGame = true;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Card Game");  //Title needs change
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        String welcome = "Welcome to our beautiful game!";
        JLabel Welcome = new JLabel(welcome, SwingConstants.CENTER);
        JButton NewGame = new JButton();
        JButton Options = new JButton();
        JButton Exit = new JButton();
        JRadioButton minionsTheme = new JRadioButton("Minions Theme Easy");
        JRadioButton minionsThemeMedium = new JRadioButton("Minions Theme Medium");
        JRadioButton minionsThemeExpert = new JRadioButton("Minions Theme Expert");
        JRadioButton pandaTheme = new JRadioButton("Kung Fu Panda Theme");
        JRadioButton pandaThemeMedium = new JRadioButton("Kung Fu Panda Theme Medium");
        JRadioButton pandaThemeExpert = new JRadioButton("Kung Fu Panda Theme Expert");
        JRadioButton yodaTheme = new JRadioButton("Yoda Theme Easy");
        JRadioButton yodaThemeMedium = new JRadioButton("Yoda Theme Medium");
        JRadioButton yodaThemeExpert = new JRadioButton("Yoda Theme Expert");
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
        NewGame.setText("New Game");
       Options.setText("Best score: " + BestScore("scores/score.txt"));
        Exit.setText("Exit");
        Welcome.setPreferredSize(new Dimension(200,60));
        NewGame.setPreferredSize(new Dimension(200,60));
        Options.setPreferredSize(new Dimension(200,60));
        Exit.setPreferredSize(new Dimension(200,60));
        yodaTheme.setSelected(true);
        pandaTheme.getModel().setEnabled(true);   //enabled the Panda theme
        panel.add(minionsTheme);
        panel.add(minionsThemeMedium);
        panel.add(minionsThemeExpert);
        panel.add(pandaTheme);
        panel.add(pandaThemeMedium);
        panel.add(pandaThemeExpert);
        panel.add(yodaTheme);
        panel.add(yodaThemeMedium);
        panel.add(yodaThemeExpert);
        panel.add(Welcome);
        panel.add(NewGame);
        panel.add(Options);
        panel.add(Exit);
        frame.add(panel);
        frame.setSize(300, 380);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        NewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(minionsTheme.isSelected()){
                    Memory newGame = new Memory(1);
                    newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //this allows fullscreen
                }else if(pandaTheme.isSelected()){
                    Memory newGame = new Memory(2);
                    newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //this allows fullscreen
                }else if(yodaTheme.isSelected()){
                    Memory newGame = new Memory(3);
                    newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //this allows fullscreen
                }else if(minionsThemeMedium.isSelected()){
                    Memory newGame = new Memory(4);
                    newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //this allows fullscreen
                }
                else if(minionsThemeExpert.isSelected()){
                    Memory newGame = new Memory(5);
                    newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //this allows fullscreen
                }
                else if(pandaThemeMedium.isSelected()){
                    Memory newGame = new Memory(6);
                    newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //this allows fullscreen
                }
                else if(pandaThemeExpert.isSelected()){
                    Memory newGame = new Memory(7);
                    newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //this allows fullscreen
                }
                else if(yodaThemeMedium.isSelected()){
                    Memory newGame = new Memory(8);
                    newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //this allows fullscreen
                }
                else if(yodaThemeExpert.isSelected()){
                    Memory newGame = new Memory(9);
                    newGame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //this allows fullscreen
                }

                //start the game
                newButton.doClick();
            }
        });

        Exit.addActionListener(e -> System.exit(0));
    }

    public Memory(int themeNumber) {
        //frame constructor

        //FullScreen
        setUndecorated(true);
        setVisible(true);
        GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
        setBounds(maximumWindowBounds);

        //Put Theme
        changeTheme(themeNumber); // 1.Minions 2.Kung Fu Panda 3.Flowers


        setTitle("Memory Game");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(new GridBagLayout());

        //position controls
        GridBagConstraints gridConstraints = new GridBagConstraints();
        guess.setText("Guesses: 0");
        guess.setForeground(Color.BLACK);
        guess.setFont(new Font("Arial", Font.BOLD, 18));
        guess.setVisible(true);
        getContentPane().add(guess);
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

        newButton.addActionListener(this::newButtonActionPerformed);

        exitButton.setText("Exit");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 7;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(0,5, 5, 5);
        getContentPane().add(exitButton, gridConstraints);
        exitButton.addActionListener(this::exitButtonActionPerformed);
        timer = new Timer(1, this::delayTimerActionPerfomed);
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
        if(!firstGame){
            output.setText("");
        }
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
            this.dispose(); //Close the program
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
        if(guessesCounter==20){
            if(themeVersion==5){
                exitButton.doClick();
                newButton.requestFocus();
                GameOverOutput("No more guesses left! GAME OVER!");
            }
            else if(themeVersion==7){
                exitButton.doClick();
                newButton.requestFocus();
                GameOverOutput("No more guesses left! GAME OVER!");
            }
            else if(themeVersion==9){
                exitButton.doClick();
                newButton.requestFocus();
                GameOverOutput("No more guesses left! GAME OVER!");
            }
        }
        else if(guessesCounter==24){
            if(themeVersion==4){
                exitButton.doClick();
                newButton.requestFocus();
                GameOverOutput("No more guesses left! GAME OVER!");
            }
            else if(themeVersion==6){
                exitButton.doClick();
                newButton.requestFocus();
                GameOverOutput("No more guesses left! GAME OVER!");
            }
            else if(themeVersion==8){
                exitButton.doClick();
                newButton.requestFocus();
                GameOverOutput("No more guesses left! GAME OVER!");
            }

        }
        else if(guessesCounter==28){
            if(themeVersion==1){
                exitButton.doClick();
                newButton.requestFocus();
                GameOverOutput("No more guesses left! GAME OVER!");
            }
            else if(themeVersion==2){
                exitButton.doClick();
                newButton.requestFocus();
                GameOverOutput("No more guesses left! GAME OVER!");
            }
            else if(themeVersion==3){
                exitButton.doClick();
                newButton.requestFocus();
                GameOverOutput("No more guesses left! GAME OVER!");
            }

        }
        else if (behind[picked[0]] == behind[picked[1]]) {
            behind[picked[0]] = -1;
            behind[picked[1]] = -1;
            remaining--;
            GameOverOutput("Good job!");
        } else {
            GameOverOutput("Try again!");
            //delay 1 second
            long t = System.currentTimeMillis();
            do {
            } while (System.currentTimeMillis() - t < 1000);

            boxLabel[picked[0]].setIcon(back);
            boxLabel[picked[1]].setIcon(back);
        }
        choice = 0;
        if (remaining == 0) {
            // save best score in a file
            try {
                // Create file
                File scoreFile = new File("scores/score.txt");
                PrintWriter out = new PrintWriter(new FileWriter(scoreFile, true));
                out.println("Score " + guessesCounter);
                out.close();
            } catch (Exception err){//Catch exception if any
                System.err.println("Error: " + err.getMessage());
            }
            exitButton.doClick();
            newButton.requestFocus();
            String outputText;
            if(guessesCounter<=11){
                outputText ="That couldn't be true.You are so lucky, bro!!!";
                GameOverOutput(outputText);
            }
            else if (guessesCounter>11&&guessesCounter<=13){
                outputText= "Lol, nice game. You have really good memory!";
                GameOverOutput(outputText);
            } else if (guessesCounter>=14&&guessesCounter<16){
                outputText="Not bad, bravo!";
                GameOverOutput(outputText);
            } else if(guessesCounter>=17&&guessesCounter<=19){
                outputText="You need a little bit more concentration";
                GameOverOutput(outputText);
            } else{
                outputText="Come on. You can do it better! Try again!";
                GameOverOutput(outputText);
            }
        }
    }

    private void GameOverOutput(String outputText) {
        output.setText(outputText);
        output.setForeground(Color.RED);
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
                themeVersion=1;
                break;
            case 2: themePath = "res\\themes\\Kung Fu Panda\\";
                themeVersion=2;
                break;
            case 3: themePath = "res\\themes\\Yoda\\";
                themeVersion=3;
                break;
            case 4: themePath = "res\\themes\\minions\\";
                themeVersion=4;
                break;
            case 5: themePath = "res\\themes\\minions\\";
                themeVersion=5;
                break;
            case 6: themePath = "res\\themes\\Kung Fu Panda\\";
                themeVersion=6;
                break;
            case 7: themePath = "res\\themes\\Kung Fu Panda\\";
                themeVersion=7;
                break;
            case 8: themePath = "res\\themes\\Yoda\\";
                themeVersion=8;
                break;
            case 9: themePath = "res\\themes\\Yoda\\";
                themeVersion=9;


        }


        card1 = new ImageIcon(themePath + "card1.jpg");
        card2 = new ImageIcon(themePath + "card2.jpg");
        card3 = new ImageIcon(themePath + "card3.jpg");
        card4 = new ImageIcon(themePath + "card4.jpg");
        card5 = new ImageIcon(themePath + "card5.jpg");
        card6 = new ImageIcon(themePath + "card6.jpg");
        card7 = new ImageIcon(themePath + "card7.jpg");
        card8 = new ImageIcon(themePath + "card8.jpg");
        back = new ImageIcon(themePath + "back.jpg");
        setContentPane(new JLabel(new ImageIcon(themePath + "background.jpg"))); //Put Background image
    }

    private static int BestScore(String file) {
        ArrayList<Integer> scores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] text = line.split(" ");
                scores.add(Integer.parseInt(text[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(scores);
        return scores.get(0);
    }
}
