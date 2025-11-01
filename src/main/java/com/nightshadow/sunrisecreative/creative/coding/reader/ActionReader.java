package com.nightshadow.sunrisecreative.creative.coding.reader;

import com.nightshadow.sunrisecreative.creative.coding.handler.Actions;

import static com.nightshadow.sunrisecreative.creative.coding.parsing.Placeholders.getPlaceholder;

public class ActionReader {
    String action;
    Object[] args;
    Selector currentlySelector;
    public ActionReader(String actionCode, Object[] args, Selector selector) {
        this.action = actionCode;
        this.args = args;
        this.currentlySelector = selector;
    }
    public Object[] getArgs() {
        return args;
    }
    public String getAction() {
        return action;
    }
    public Object getArgument(int index) {
        return args[index];
    }
    public void execute() {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) args[i] = getPlaceholder(args[i].toString(),currentlySelector);
        }



        Object result = Actions.getAction(action).execute(currentlySelector,args);
        if (result instanceof Selector) {
            currentlySelector = (Selector) result;
        }
    }
    public Selector getSelector() {return currentlySelector;}
}
