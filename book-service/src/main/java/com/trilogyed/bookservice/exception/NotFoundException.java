package com.trilogyed.bookservice.exception;

/*Task:
 *  Will just allow you to send a notFound error
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

}
