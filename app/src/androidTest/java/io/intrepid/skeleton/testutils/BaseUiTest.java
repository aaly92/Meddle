package io.intrepid.skeleton.testutils;

import org.junit.After;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.intrepid.skeleton.InstrumentationTestApplication;

public class BaseUiTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @After
    public void tearDown() {
        InstrumentationTestApplication.clearRestApiOverride();
        InstrumentationTestApplication.clearUserSettingsOverride();
    }
}
