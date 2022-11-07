package algonquin.cst2335.lab4;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class passwordTests {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Testing password 12345 which is too short, no upper/lowercase
     * letter and no special characters. Checks to see if textView3
     * contains "BAD PASSWORD".
     * Simple test using expresso.
     **/
    @Test
    public void passwordTests() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextTextPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button), withText("Button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.button), withText("Button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("BAD PASSWORD")));
    }

    /**
     * Testing a password with no uppercase or special characters.
     * this will make sure that the logic in the java code changes
     * the text to bad password when it doesn't conform to the
     * password requirements. Checks if textView3 is set to "BAD
     * PASSWORD" if true test returns successful.
     **/
    @Test
    public void testFindMissingUpperCase(){
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("1a23456"));
        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());
        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("BAD PASSWORD")));
    }

    /**
     * Testing a password with no lowercase.
     * this will make sure that the logic in the java code changes
     * the text to bad password when it doesn't conform to the
     * password requirements. Checks if textView3 is set to "BAD
     * PASSWORD" if true test returns successful.
     **/
    @Test
    public void testFindMissingLowerCase(){
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("PASSWORD1!"));
        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());
        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("BAD PASSWORD")));
    }

    /**
     * Testing a password with no numbers.
     * this will make sure that the logic in the java code changes
     * the text to bad password when it doesn't conform to the
     * password requirements. Checks if textView3 is set to "BAD
     * PASSWORD" if true test returns successful.
     **/
    @Test
    public void testFindMissingNumber(){
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("Password!"));
        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());
        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("BAD PASSWORD")));
    }

    /**
     * Testing a password with no special characters.
     * this will make sure that the logic in the java code changes
     * the text to bad password when it doesn't conform to the
     * password requirements. Checks if textView3 is set to "BAD
     * PASSWORD" if true test returns successful.
     **/
    @Test
    public void testFindMissingSpecial(){
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("Password1"));
        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());
        ViewInteraction textView = onView(withId(R.id.textView3));
        textView.check(matches(withText("BAD PASSWORD")));
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }


            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }


        };


    }

}
