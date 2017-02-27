package com.apress.gerber.reminders;

import android.content.Context;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;
import android.widget.Toast;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.R.attr.action;
import static android.R.attr.description;

import static android.R.attr.scrollViewStyle;
import static android.R.drawable.ic_menu_delete;
import static android.R.id.text2;
import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.CursorMatchers.withRowString;
import static android.support.test.espresso.matcher.RootMatchers.isFocusable;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class ExampleInstrumentedTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    @Ignore
    public void lastItem_NotDisplayed() {
        // Last item should not exist if the list wasn't scrolled down.
        onView(withText("Call the Dalai Lama back")).check(doesNotExist());
    }

    @Test
    @Ignore
    public void edit0() {
        onData(anything()).inAdapterView(withId(R.id.reminders_list_view))
                .atPosition(0).perform(click());

        onView(withText("Edit Reminder"))
                .perform(click());

        // .check(matches(isCompletelyDisplayed()));
        MainActivity activity = mActivityRule.getActivity();

        onView(withText("edit0")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                // inRoot(new ToastMatcher()).
                        check(matches(isDisplayed()));
        onView(withText("edit0")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(withText("edit0")));
    }

    @Test
    @Ignore
    public void delete0() {
        onData(anything()).inAdapterView(withId(R.id.reminders_list_view))
                .atPosition(1).perform(click());

        onView(withText("Delete Reminder"))
                .perform(click());

        // .check(matches(isCompletelyDisplayed()));
        MainActivity activity = mActivityRule.getActivity();

        onView(withText("delete1")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                // inRoot(new ToastMatcher()).
                        check(matches(isDisplayed()));
        onView(withText("delete1")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(withText("delete1")));
    }

    @Test
    @Ignore
    public void menu1() {

        //openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        //onView(withId(R.id.menu_reminders)).perform(click());
        onView(isRoot()).perform(ViewActions.pressMenuKey());
        onView(withText("new Reminder")).perform(click());
        onView(withId(R.id.custom_root_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.custom_edit_reminder)).perform(typeText("shopping"));//.perform(closeSoftKeyboard ());


    }

    ;

    @Test
    @Ignore
    public void menu2() {
        MainActivity activity = mActivityRule.getActivity();
        onView(isRoot()).perform(ViewActions.pressMenuKey());
        assertFalse(mActivityRule.getActivity().isFinishing());
        onView(withText("exit")).perform(click());
        assertTrue(mActivityRule.getActivity().isFinishing());
        //onView(withClassName(is("com.apress.gerber.reminders"))).check(activity.isFinishing());

    }

    ;


    @Test
    @Ignore
    public void newreminder() {
        onView(isRoot()).perform(ViewActions.pressMenuKey());
        onView(withText("new Reminder")).perform(click());
        onView(withId(R.id.custom_edit_reminder)).perform(click());
        onView(withId(R.id.custom_edit_reminder)).perform(typeText("Shopping"), closeSoftKeyboard());
        onView(withId(R.id.custom_check_box)).perform(click());
        onView(withId(R.id.custom_button_commit)).perform(click());
        onData(withRowString(RemindersDbAdapter.COL_CONTENT, "Shopping")).check(matches(isDisplayed()));

    }

    @Test
    public void reminder() {
        MainActivity activity = mActivityRule.getActivity();
        activity.insertSomeReminders(activity.mDbAdapter, "Call the Dalai Lama back", true);

    }
}