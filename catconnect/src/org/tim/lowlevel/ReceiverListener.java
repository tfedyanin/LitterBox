package org.tim.lowlevel;

/**
 * User: Тимофей
 * 12.04.2015 17:22
 */
public interface ReceiverListener {
    void receive(final char ch);
    void receive(final String string);
}
