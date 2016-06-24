package com.ricardo.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricardo on 01/06/16.
 */
public class UserInputTest {
    private String userInput = "teste";

    @Test
    public void lerInputUsuario() throws Exception {
        UserInput uI = new UserInput();
        assertEquals(this.userInput, uI.lerInputUsuario());
    }

    @Before
    public void setUp() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(this.userInput.getBytes());
        System.setIn(in);
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(System.in);
    }
}