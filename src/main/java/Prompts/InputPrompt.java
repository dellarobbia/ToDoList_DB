package Prompts;

import java.util.Scanner;

/**
 * Prompt that accepts an input from the user.
 * @author Andrew McKay
 */
public class InputPrompt implements Prompts{
    //Properties
    private String prompt;
    private String userInput;

    //Getters & Setters
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getUserInput() {
        return userInput;
    }
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    //Constructor
    /**
     * Constructor for InputPrompt w/ a defined prompt.
     * @param prompt String of text to output to the console to ask for input.
     */
    public InputPrompt (String prompt){
        setPrompt(prompt);
    }

    //Methods
    private void retrieveUserInput(Scanner scanner){
        setUserInput(scanner.nextLine());
    }

    /**
     * Display the prompt and accept an input from the user.
     */
    @Override
    public void displayPrompt() {
        System.out.println(prompt);
        retrieveUserInput(new Scanner(System.in));
    }
}