package Common;

import java.io.File;

public class Constants {
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    public static final int STREAK_THRESHOLD = 3;
    public static final int SHAME_PROGRESS_VALUE = 25;
    public static final double DEFAULT_PREDICTION = 0.0006;
    public static final String[] WELCOME_MESSAGES = {
            "Welcome to the Shame Calculator!",
            "Get ready for a unique calculator experience!",
            "Make predictions and avoid the shame bar!",
            "Ready to calculate with a twist? Let's go!",
            "Strive for correct answers and stay shame-free!",
            "Welcome back! Ready for some calculations?",
            "Make accurate predictions to avoid the shame!",
            "This calculator adds a fun challenge to math!",
            "Predict wisely to keep the shame bar at bay!",
            "Let's calculate with a bit of excitement!"
    };

    public static final String PROJECT_DIRECTORY = new File(System.getProperty("user.dir")).getParent();

    public static final String[] MEME_PATHS = {
            PROJECT_DIRECTORY + "/Images/Memes/javatrzy.png",
            PROJECT_DIRECTORY + "/Images/Memes/arraysize.png",
            PROJECT_DIRECTORY + "/Images/Memes/javalife.png"
    };

    public static final String HELP_MESSAGE = "Instructions:\n\n" +
            "1. Enter two numbers in the input fields.\n" +
            "2. Select an operator from the drop-down menu.\n" +
            "3. Click the '=' button to calculate the result.\n" +
            "4. Make a prediction of the result in the prediction field.\n" +
            "5. The Shame Bar progresses if your prediction is incorrect.\n" +
            "6. Strive for correct predictions to avoid the Shame Bar.\n" +
            "7. Get three correct predictions in a row for a Good Job reward.\n" +
            "8. You can take a break and enjoy a meme by clicking 'Show me a meme'.\n\n" +
            "Have fun calculating and avoiding shame!";

    public static final String ABOUT_MESSAGE = "Shame Calculator v1.0.0\n" +
            "Developed by Paweł Frankowski\n" +
            "Email: pawelspam42@gmail.com\n" +
            "Github: github.com/pablolambo\n";
}
