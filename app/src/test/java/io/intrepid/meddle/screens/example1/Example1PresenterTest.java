package io.intrepid.meddle.screens.example1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.intrepid.meddle.testutils.BasePresenterTest;
import io.intrepid.meddle.testutils.TestPresenterConfiguration;

import static org.mockito.Mockito.verify;

public class Example1PresenterTest extends BasePresenterTest<Example1Presenter> {

    @Mock
    Example1Contract.View mockView;

    @Before
    public void setup() {
        presenter = new Example1Presenter(mockView, TestPresenterConfiguration.createTestConfiguration());
    }

    @Test
    public void testOnButtonClicked() throws Exception {
        presenter.onButtonClicked();
        verify(mockView).gotoExample2();
    }
}
