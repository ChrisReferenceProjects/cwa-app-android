package de.rki.coronawarnapp.ui.onboarding

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.rki.coronawarnapp.R
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import testhelpers.BaseUITest
import testhelpers.takeScreenshot
import testhelpers.Screenshot
import testhelpers.SystemUIDemoModeRule
import testhelpers.launchFragment2
import testhelpers.launchFragmentInContainer2
import tools.fastlane.screengrab.locale.LocaleTestRule

@RunWith(AndroidJUnit4::class)
class OnboardingFragmentTest : BaseUITest() {

    @Rule
    @JvmField
    val localeTestRule = LocaleTestRule()

    @get:Rule
    val systemUIDemoModeRule = SystemUIDemoModeRule()

    @Before
    fun setUp() {
        setupMockViewModel(object : OnboardingFragmentViewModel.Factory {
            override fun create(): OnboardingFragmentViewModel = OnboardingFragmentViewModel()
        })
    }

    @After
    fun teardown() {
        clearAllViewModels()
        unmockkAll()
    }

    @Test
    fun launch_fragment() {
        launchFragment2<OnboardingFragment>()
    }

    @Screenshot
    @Test
    fun capture_screenshot() {
        launchFragmentInContainer2<OnboardingFragment>()
        takeScreenshot<OnboardingFragment>()

        onView(withId(R.id.onboarding_easy_language)).perform(scrollTo())
        takeScreenshot<OnboardingFragment>("2")
    }
}

@Module
abstract class OnboardingFragmentTestModule {
    @ContributesAndroidInjector
    abstract fun onboardingFragment(): OnboardingFragment
}
