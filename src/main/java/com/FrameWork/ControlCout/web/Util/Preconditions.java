/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.web.Util;

   
import com.FrameWork.ControlCout.web.errors.IllegalBusinessLogiqueException;
import com.FrameWork.ControlCout.web.errors.MyResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Administrator
 */
public class Preconditions {
    
       private final static Logger LOG = LoggerFactory.getLogger(Preconditions.class);

    private Preconditions() {
        throw new AssertionError();
    }

    // API
    /**
     * Check if some value was found, otherwise throw exception.
     *
     * @param expression has value true if found, otherwise false
     * @param message
     * @throws MyResourceNotFoundException if expression is false, means value
     * not found.
     */
    public static void checkBusinessLogique(final boolean expression, String message) {
        if (!expression) {

            throw new IllegalBusinessLogiqueException(message);
        }
    }

    public static void checkBusinessLogique(final boolean expression, String message, String... detailMessage) {
        if (!expression) {
            String detailMsgBuilder = String.join(";", detailMessage);
            IllegalBusinessLogiqueException X = new IllegalBusinessLogiqueException(message, new Throwable(detailMsgBuilder));
            LOG.error(message, new Throwable(detailMsgBuilder));
            throw X;
        }
    }

    /**
     * Check if some value was found, otherwise throw exception.
     *
     * @param <T>
     * @param resource
     * @return
     * @throws MyResourceNotFoundException if expression is false, means value
     * not found.
     */
    public static <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new MyResourceNotFoundException();
        }

        return resource;
    }

    public static <T> T checkFound(final T resource, String message) {
        if (resource == null) {
            throw new MyResourceNotFoundException(message);
        }

        return resource;
    }

}
