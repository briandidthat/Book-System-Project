package com.trilogyed.notequeueconsumer;

import com.trilogyed.notequeueconsumer.model.Note;
import com.trilogyed.notequeueconsumer.util.feign.NoteClient;
import com.trilogyed.notequeueconsumer.util.messages.NoteEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MessageListener
 * Purpose: To listen to the wire for incoming messages
 * Needed Annotations:
 * @Service - will tell spring to look for this class and treat it as a service
 * @RabbitListener - will define the queue used for that specific method/ class
 */

@Service
public class MessageListener {
    @Autowired
    NoteClient noteClient;

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveNote(NoteEntry noteEntry) {
        System.out.println(noteEntry.toString());

        try {
            if (noteEntry.getNoteId() == 0) {
                Note note = new Note(noteEntry.getBookId(), noteEntry.getNote());
                noteClient.createNote(note);
                System.out.println("Creating the following note: " + noteEntry.toString());
            } else {
                Note note = new Note(noteEntry.getBookId(), noteEntry.getNote());
                note.setNoteId(noteEntry.getNoteId());
                System.out.println("Updating the following note: " + noteEntry.toString());
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
