/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.exception;

/**
 * IBusException is a subclass of RuntimeException that is thrown when an error occurs in
 * the IBus
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 28 2022
 */
public class IBusException extends RuntimeException {
    /**
     * Constructs an IBusException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * @param message the String that contains a detailed message
     */
    public IBusException(String message) {
        super(message);
    }
}
