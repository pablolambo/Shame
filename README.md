# Shame calculator

Basic arithmetic calculations with a unique twist — it incorporates a shame bar that increases upon incorrect predictions. Users can input mathematical expressions, make predictions, and experience consequences for incorrect guesses, fostering a gamified learning environment. The application also features visual cues, such as displaying images, to enhance the user experience based on their performance.

## What for? 

This Java calculator study project is designed for educational purposes to enhance my understanding of Java Swing components and component programming concept. It provides a simple graphical user interface (GUI) for performing basic operations with multiple functionalities and non-functionalities.

## Prerequisites

- Java Development Kit (JDK) installed on your machine.

## Running the Calculator
Compile Java files
```bash
javac src/Calculator.java src/CalculatorController.java
```
Run the program
```bash
java src.Calculator
```

### 10 Functionalities:
1. **Basic Calculation:**
  - Allows users to perform basic arithmetic calculations (addition, subtraction, multiplication, division).
2. **Result Display:**
  - Results of the calculations in a field in which user can copy calculations.
3. **Prediction Input:**
  - Accepts user predictions for the calculation result.
4. **Number inputs**
  - App takes those numbers and make calculation on them.
5. **Streak Recognition:**
  - Recognizes consecutive correct predictions and rewards the user.
6. **Menu tabs:**
  - User can interact with two tabs - "Help"and "About"
7. **Displays Images or messages:**
  - Displays a images or messages based on user interaction with UI.
8. **Prediction Blocking:**
  - Doesn't allow the prediction after result is calculated.
9.  **Random Calculation Generator button:**
  - Generates random calculations with numbers between 1 and 100 and a random operator.
10. **Meme Display button:**
  - Shows a meme to the user as a break from calculations.


### 21 Non-functionalities:
1. **Welcome Message:**
  - Displays a random welcoming message when the application starts.
2. **Color Feedback:**
  - Changes the color of the prediction field to green for correct predictions and red for incorrect predictions.
3. **Proximity Display:**
  - Displays the proximity value when the user makes a prediction.
4. **Instructions are provided:**
  - Above on the menu the tab "Help" provides information about using the calculator.
5. **User Interface Layout:**
  - Defines the layout and appearance of the graphical user interface (GUI).
6. **Image Icons:**
  - Uses images (icons) for the Shame Bar, Good Job recognition, shame image, and memes.
7. **Prediction Percentage Bar:**
  - Shows a horizontal bar indicating the percentage of correct predictions compared to total predictions.
8. **Prediction counters:**
  - Counts good or wrong predictions.
9. **Bar titles:**
  - Firstly, the bars has given titles  to indicate what it is for user.
10. **Percentage is shown in bars:**
  - When first calculations and predictions are made, the bar titles change to dynamically calculated percentage.
11. **Inteligent meme display:**
  - It would be boring if we saw the same meme for couple times in a row, algorithm prevents this situation.
12. **Fair award system:**
  - When user is almost correct with his predictions, shame calculator doesn't punish nor reward him.
13. **Smooth icon for generating random numbers:**
  - UI looks more appealing with this dice icon symboling that it generates randon numbers.
14. **Input validation:**
  - Every field is validated.
15. **User information system:**
  - When something is not valid, the app will inform user about it by message dialogs.
16. **Information about application is provided:**
  - Provides information details about the application.
17. **History Panel Cleanup:**
  - Automatically trims excessive entries in the history panel, ensuring it maintains a manageable size for improved user readability and navigation.
18. **Rounding to two decimal places:**
  - To make result more readable and user-friendly the application rounds it.
19. **History panel has dynamic field size:**
  - For providing a visually optimized display.
20. **Good Job Recognition:**
  - Recognizes and rewards the user for achieving three correct predictions in a row.
21. **Shame Bar:**
  - Represents the user's correctness in predictions.
