package org.tim.hightlevel.comands;

import org.tim.exceptions.EchoException;

/**
 * User: Tim
 * 01.06.2015 21:04
 */
public interface IComand {
    public abstract void executre() throws EchoException;
}