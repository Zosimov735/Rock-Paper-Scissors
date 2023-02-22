package rockpaperscissors;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws Exception {
        // initialize randNum for choosing winner and scanner
        Scanner scanner = new Scanner(System.in);
        //create var to store computers choice of option
        String compChoice;

        //options for gameplay
        String rock = "rock";
        String paper = "paper";
        String scissors = "scissors";
        String lizard = "lizard";
        String spock = "spock";
        String fire = "fire";
        String gun = "gun";
        String lightning = "lightning";
        String devil = "devil";
        String dragon = "dragon";
        String water = "water";
        String air = "air";
        String sponge = "sponge";
        String wolf = "wolf";
        String tree = "tree";
        String human = "human";
        String snake = "snake";


        //Start gameplay, get username
        System.out.print("Enter your name:");
        String name = scanner.next();
        System.out.println("Hello, " + name);

        //check for rating within file and establish score
        File file = new File("/Users/grantcrawford/IdeaProjects/Rock-Paper-Scissors/Rock-Paper-Scissors/task/src/rockpaperscissors/rating.txt");
        Scanner readFile = new Scanner(file);
        String[] getScore;
        String userRating;
        int score = 0;
        ArrayList<String> fileContents = new ArrayList<String>();

        while(readFile.hasNextLine()){
            fileContents.add(readFile.nextLine());
        }

        for (int i = 0; i < fileContents.size(); i++){
            if (fileContents.get(i).contains(name)){
                userRating = fileContents.get(i);
                getScore = userRating.split(" ");
                score = Integer.parseInt(getScore[1]);
            }
        }
        //Get which options will be used by game (this is a crazy stupid workaround for the fact that next() doesn't read \n character created by hitting enter
        String optionsSelect = scanner.nextLine();
        optionsSelect = scanner.nextLine();

        if (optionsSelect.isEmpty()){
            optionsSelect = "rock,paper,scissors";
        }
        String[] actualOptionsFind = optionsSelect.split(",");
        ArrayList<String> actualOptions = new ArrayList<>();
        int sizeOfArr = 0;
        for (String str: actualOptionsFind){
            actualOptions.add(str);
            sizeOfArr += 1;
        }

        //choose computer choice
        Random rand = new Random();
        int randNum = rand.nextInt(sizeOfArr);

        compChoice = actualOptions.get(randNum);

        System.out.println("Okay, let's start");



        String option = scanner.next();



        while(!option.equals("!exit")) {
            if (option.equals("!rating")) {
                System.out.println(score);

            }else if (!actualOptions.contains(option)){
                System.out.println("Invalid input");
            }else if(option.equals(compChoice)) {
                System.out.println("There is a draw " + compChoice);
                score += 50;}

            else if (findWinner(actualOptions,option).contains(compChoice)){
                System.out.println("Sorry, but the computer chose " + compChoice);
            } else if (!findWinner(actualOptions, option).contains(compChoice)) {
                System.out.println("Well done. The computer chose " + compChoice + " and failed");
                score += 100;
        } else {
                System.out.println("Invalid input");
            }

            option = scanner.next();
            randNum = rand.nextInt(sizeOfArr);
            compChoice = actualOptions.get(randNum);
        }
        System.out.println("Bye!");
    }
    public static ArrayList<String> findWinner(ArrayList<String> actualOptions, String option){
        ArrayList<String> defeaters = new ArrayList<String>();
        int defeatersSize = actualOptions.size()/2;
        int positionOfUserChoice = actualOptions.indexOf(option);
        for (int i = positionOfUserChoice; i< actualOptions.size(); i++){
            if (actualOptions.size() == 3 && actualOptions.get(0).equals("rock")){
                if(option.equals("rock")){
                    defeaters.add("paper");
                    break;
                } else if (option.equals("paper")){
                    defeaters.add("scissors");
                    break;
                } else {
                    defeaters.add("rock");
                    break;
                }
            }

            if (actualOptions.get(i) == option){
                i++;
            }

            if (i == actualOptions.size()-1 && defeaters.size() != defeatersSize){
                i = 0;
            }

            if (defeaters.size() != defeatersSize){
                defeaters.add(actualOptions.get(i));
            }


        }

        return defeaters;




    }
}

