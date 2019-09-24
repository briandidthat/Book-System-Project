package com.trilogyed.notequeueconsumer;

import com.trilogyed.notequeueconsumer.model.Note;
import com.trilogyed.notequeueconsumer.util.feign.NoteClient;
import com.trilogyed.notequeueconsumer.util.messages.NoteEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            System.out.println("Weird error: " + e.getMessage());
        }
    }
}
