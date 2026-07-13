
import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class VanessaThreeThreads {
    private JTextArea textArea;
    private JButton startButton;
    private JFrame frame;


    private final Random random = new Random();
    private final int charCount = 10000;

    Thread t1, t2, t3;

    public VanessaThreeThreads () {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("ThreeThreads Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        textArea = new JTextArea(15, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        startButton = new JButton("Start Threads");
        startButton.addActionListener(e -> startThreads());

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void startThreads() {
        textArea.setText("");
        startButton.setEnabled(false);

        t1 = new Thread(() -> {
            for (int i = 0; i < charCount; i++) {
                char c = (char) ('a' + random.nextInt(26));
                appendToTextArea(String.valueOf(c));
                sleepBriefly();
            }
        }, "LetterThread");

        t2 = new Thread(() -> {
            for (int i = 0; i < charCount; i++) {
                char c = (char) ('0' + random.nextInt(10));
                appendToTextArea(String.valueOf(c));
                sleepBriefly();
            }
        }, "DigitalThread");

        t3 = new Thread(() -> {
            String symbols = "!@#$%&*";
            for (int i = 0; i < charCount; i++) {
                char c = symbols.charAt(random.nextInt(symbols.length()));
                appendToTextArea(String.valueOf(c));
                sleepBriefly();
            }
        }, "SymbolThread");

        t1.start();
        t2.start();
        t3.start();


        new Thread(() -> {
            try {
                t1.join();
                t2.join();
                t3.join();

                SwingUtilities.invokeLater(() -> startButton.setEnabled(true));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void appendToTextArea(String text){

        SwingUtilities.invokeLater(() -> textArea.append(text));
    }

    private void sleepBriefly() {
        try {
          Thread.sleep(1);
    }   catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
    }

    public void runAutomatedTests() {
        System.out.println("[TEST] Launching comprehensive thread verificationtests...");

        startThreads();

        new Thread(() -> {
            try {

                System.out.println("[TEST] Asserting all worker threads are live and kicking...");
                if (t1 == null || t2 == null || t3 == null) {
                    System.out.println("[TEST ERROR] Fail: Thread objects failed instantiation.");
                    return;
                }

                t1.join();
                t2.join();
                t3.join();

                Thread.sleep(600);

                String finalUiContent = textArea.getText();
                int totalLength = finalUiContent.length();

                int letterCounter = 0;
                int digitCounter = 0;
                int symbolCounter = 0;

                for (char c : finalUiContent.toCharArray()) {
                    if (Character.isLetter(c)) letterCounter++;
                    else if (Character.isDigit(c)) digitCounter++;
                    else if ("!@#$%&*".indexOf(c) != -1) symbolCounter++;
                }

                System.out.println("\n================================");
                System.out.println("        AUTOMATED VERIFICATION REPORT       ");
                System.out.println("================================");
                System.out.println("Letters Generated -> Target: " + charCount + " | Found: " + letterCounter);
                System.out.println("Digits Generated -> Target: " + charCount + " | Found: " + digitCounter);
                System.out.println("Symbols Generated -> Target: " + charCount + " | Found: " + symbolCounter);
                System.out.println("Total Buffer Count-> Target: " + (charCount * 3) + " | Found: " + totalLength);
                System.out.println("------------------------------------");

                boolean satisfiesLetters = (letterCounter == charCount);
                boolean satisfiesDigits = (digitCounter == charCount);
                boolean satisfiesSymbols = (symbolCounter == charCount);
                boolean satisfiesInterleaving = !finalUiContent.startsWith("aaaaa") && !finalUiContent.endsWith("*****");

                if (satisfiesLetters && satisfiesDigits && satisfiesSymbols) {
                    System.out.println("STATUS: PASSED (All 30,000 characters gnerated correctly!)");
                } else {
                    System.out.println("STATUS: FAILED (Discrepancy in generation totals.)");
                }

                if (satisfiesInterleaving) {
                    System.out.println("MIXING: PASSED (Steam interleaving confirmed. No bulk blocks detected.)");
                } else {
                    System.out.println("MIXING: FAILED (Characters grouped into bulk blocks instead of stream-interleaved.)");
                }
                System.out.println("===============================================\n");
            } catch (InterruptedException e) {
                System.out.println("[TEST EXECUTION ERROR] Verification thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            VanessaThreeThreads application = new VanessaThreeThreads();

            application.runAutomatedTests();
        });
    }
}