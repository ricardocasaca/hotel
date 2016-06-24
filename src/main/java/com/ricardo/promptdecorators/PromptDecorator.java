package com.ricardo.promptdecorators;

import com.ricardo.interfaces.PromptService;

import java.util.logging.Logger;

/**
 * Created by ricardo on 29/05/16.
 */
public abstract class PromptDecorator implements PromptService {
    public static final Logger log = Logger.getLogger(PromptDecorator.class.getName());

    @Override
    public abstract String getUserData(String texto);
}
