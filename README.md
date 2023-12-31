# Shame calculator

Basic arithmetic calculations with a unique twist — it incorporates a shame bar that increases upon incorrect predictions. Users can input mathematical expressions, make predictions, and experience consequences for incorrect guesses, fostering a gamified learning environment. The application also features visual cues, such as displaying images, to enhance the user experience based on their performance.

## What for? 

This Java calculator study project is designed for educational purposes to enhance my understanding of Java Swing components and component programming concept. It provides a simple graphical user interface (GUI) for performing basic operations with multiple functionalities and non-functionalities.

## Prerequisites

- Java Development Kit (JDK) installed on your machine.

## How to run the Shame Calculator
Just open ShameCalculator.jar file.

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

# Raport on component based programming:

The Shame Calculator application is built on a component-based design, a software architecture that divides the system into modular, reusable, and independent components. This report highlights the reasons why a component-based approach was chosen for the development of the Shame Calculator.

Advantages of Component-Based Design:

1. Modularity:
- Improved Maintenance: Components in the Shame Calculator are self-contained, making it easier to identify, isolate, and fix issues without affecting other parts of the application.
- Code Reusability: Components are designed to be reusable across different parts of the application or even in future projects, reducing redundancy and promoting efficiency.
2. Scalability:
- Ease of Integration: New features or improvements can be seamlessly integrated by adding or updating specific components without impacting the entire application.
- Enhanced Collaboration: Multiple developers can work on different components concurrently, facilitating parallel development and faster project completion.
3. Flexibility and Adaptability:
- Customization: Components can be customized or replaced without affecting the overall application, allowing for flexibility in adapting to changing requirements.
- Upgradability: The modular nature of components makes it easier to update or replace individual elements, ensuring the application remains up-to-date.
4. Improved Testing and Debugging:
- Isolation of Issues: Testing and debugging are more manageable due to the isolation of components. Issues can be traced back to specific modules, streamlining the debugging process.
- Unit Testing: Individual components can undergo unit testing, ensuring that each part of the application functions correctly in isolation before integration.
5. Enhanced User Interface:
- Consistent Design: Components, such as buttons, input fields, and progress bars, maintain a consistent design throughout the application, providing users with a unified and familiar interface.
- Easy Styling: GUI components can be styled uniformly, ensuring a cohesive and visually appealing user experience.
##Implementation of Component-Based Design in the Shame Calculator:
1. GUI Components:

- Buttons, TextFields, and Combo Boxes: These elements are distinct components responsible for user interaction, prediction input, and generating random calculations. Their modular design ensures ease of use and consistency in the UI.
2. Shame Bar and Prediction Percentage Bar:
- Progress Bars: These components represent the user's correctness and prediction percentage, respectively. Their independence allows for straightforward updates based on user input, without affecting other functionalities.
3. History Panel:
- Text Area Component: The history panel is implemented using a text area component, providing a modular solution for displaying past calculations and predictions.
4. Meme Display:
- Image Icons: The meme display functionality utilizes image icons as components, enhancing the visual appeal of the application.
5. Logical Components:
- Prediction Logic: The logic for handling predictions, streak recognition, and user rewards is encapsulated within independent components, promoting maintainability and readability.
# Conclusion:
The Shame Calculator application's adoption of a component-based design brings numerous advantages, including modularity, scalability, flexibility, improved testing, and an enhanced user interface. This approach ensures that the application is not only functional but also maintainable and adaptable to future changes or expansions. The organization of the Shame Calculator into discrete, interconnecting components is a strategic decision that contributes to its overall robustness and user-friendly design.
