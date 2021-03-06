package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Tests whether jokeTextVIew is visible and if it does not have empty text
 * if jokeTextView is visible then the async test finished successfully.
 */
@RunWith(AndroidJUnit4.class)
public class jokeTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testJokeActivity() {
        onView((withId(R.id.jokeButton))).perform(click());
        onView(withId(R.id.jokeTextView)).check((matches((not(withText(""))))));
    }

}
