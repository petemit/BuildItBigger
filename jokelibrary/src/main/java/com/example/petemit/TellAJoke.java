package com.example.petemit;

public class TellAJoke {
    private static String[] bestJokes= {
            "Where does a general put his army? \n" +
                    "-In his sleevey",
            "Why don't you play poker in the savannah?  \n" +
                    "-Too many Cheetahs...",
            "It is what's inside that matters \n" +
                    "-the fridge is a perfect example",
            "What is never very funny and flies through the air? \n" +
                    "-Peter Pun",
            "What's harder than getting an elephant into a car? \n" +
                    "-Getting two elephants into a car",
            "What do you get when you mix an elephant and peanut butter? \n" +
                    "-An elephant that sticks to the roof of your mouth",
            "Why are elephants so wrinkly?  \n" +
                    "-Have you ever tried to iron one?",
            "How do billboards talk with one another? \n" +
                    "-Sign Language",
            "When is a door not a door? \n" +
                    "-When it's ajar",
            "Which side of a duck has the most feathers? \n" +
                    "-The outside",
            "What shakes and lies at the bottom of the ocean? \n" +
                    "-A nervous wreck",
            "What did the red light say to the green light? \n" +
                    "-Don't look, I'm changing.",
            "Why did the golfer wear two pairs of pants? \n" +
                    "-In case he got a hole in one.",
            "What's the difference between America and a memory stick? \n" +
                    "-One's USA and the other's USB."

    };

    /** Picks a random joke from the bestJokes Array
     *
     * @return
     */
    public static String getAJoke(){
       return bestJokes[(int) (Math.random()*(bestJokes.length))];
    }
}
