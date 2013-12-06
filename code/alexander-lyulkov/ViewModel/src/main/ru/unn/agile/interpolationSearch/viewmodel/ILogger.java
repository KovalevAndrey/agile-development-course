package ru.unn.agile.interpolationSearch.viewmodel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sasha
 * Date: 12/5/13
 * Time: 6:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ILogger {
    void AddNote(String note);
    String[] ReadLog();

}
