package ddb;

import app.MainActivity;

public class Driver {
    public void print (Object pnt) {
        if (pnt != null) {
            MainActivity.funnyText.setText(pnt.toString());
        }

        else {
            MainActivity.funnyText.setText("null");
        }
    }

    public Driver () {

    }

    public void main () {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "some");
        ddb.write("body once told me the world is gonna roll me\n" +
                "I ain't the sharpest tool in the shed\n" +
                "She was looking kind of dumb with her finger and her thumb\n" +
                "In the shape of an \"L\" on her forehead\n" +
                "\n" +
                "Well, the years start coming and they don't stop coming\n" +
                "Fed to the rules and I hit the ground running\n" +
                "Didn't make sense not to live for fun\n" +
                "Your brain gets smart but your head gets dumb\n" +
                "\n" +
                "So much to do, so much to see\n" +
                "So what's wrong with taking the back streets?\n" +
                "You'll never know if you don't go (GO!)\n" +
                "You'll never shine if you don't glow\n" +
                "\n" +
                "Hey, now, you're an all-star, get your game on, go play\n" +
                "Hey, now, you're a rock star, get the show on, get paid\n" +
                "And all that glitters is gold\n" +
                "Only shooting stars break the mold\n" +
                "\n" +
                "It's a cool place and they say it gets colder\n" +
                "You're bundled up now wait 'til you get older\n" +
                "But the meteor man beg to differ\n" +
                "Judging by the hole in the satellite picture\n" +
                "\n" +
                "The ice we skate is getting pretty thin\n" +
                "The water's getting warm so you might as well swim\n" +
                "My world's on fire. How about yours?\n" +
                "That's the way I like it and I'll never get bored\n" +
                "\n" +
                "Hey, now, you're an all-star, get your game on, go play\n" +
                "Hey, now, you're a rock star, get the show on, get paid\n" +
                "And all that glitters is gold\n" +
                "Only shooting stars break the mold\n" +
                "\n" +
                "Go for the moon\n" +
                "Go for the moon\n" +
                "Go for the moon\n" +
                "Go for the moon\n" +
                "\n" +
                "Hey, now, you're an all-star, get your game on, go play\n" +
                "Hey, now, you're a rock star, get the show on, get paid\n" +
                "And all that glitters is gold\n" +
                "Only shooting stars\n" +
                "\n" +
                "Somebody once asked could I spare some change for gas\n" +
                "I need to get myself away from this place\n" +
                "I said yep, what a concept\n" +
                "I could use a little fuel myself\n" +
                "And we could all use a little change\n" +
                "\n" +
                "Well, the years start coming and they don't stop coming\n" +
                "Fed to the rules and I hit the ground running\n" +
                "Didn't make sense not to live for fun\n" +
                "Your brain gets smart but your head gets dumb\n" +
                "\n" +
                "So much to do, so much to see\n" +
                "So what's wrong with taking the back streets?\n" +
                "You'll never know if you don't go\n" +
                "You'll never shine if you don't glow\n" +
                "\n" +
                "Hey, now, you're an all-star, get your game on, go play\n" +
                "Hey, now, you're a rock star, get the show on, get paid\n" +
                "And all that glitters is gold\n" +
                "Only shooting stars break the mold\n" +
                "\n" +
                "And all that glitters is gold\n" +
                "Only shooting stars break the mold ");
        print(ddb.read());
    }
}
