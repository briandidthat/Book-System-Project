package com.trilogyed.noteconsumer;

import com.trilogyed.noteconsumer.util.feign.NoteClient;
import com.trilogyed.noteconsumer.util.messages.NoteListEntry;
import com.trilogyed.noteconsumer.util.model.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Task:
 * Define a Listener that will be listening to the wire, and printing out any new entries
 */
@Service
public class NoteListener {
    @Autowired
    NoteClient noteClient;

    @RabbitListener(queues = NoteConsumerApplication.QUEUE_NAME)
    public Note createNote(NoteListEntry noteListEntry) {
        Note note = new Note(noteListEntry.getBookId(), noteListEntry.getNote());
        note = noteClient.createNote(note);
        System.out.println(note.toString());
        return note;
    }
//    @RabbitListener(queues = NoteConsumerApplication.QUEUE_NAME)
    public void updateNote(NoteListEntry noteListEntry) {
        Note note = new Note(noteListEntry.getBookId(), noteListEntry.getNote());
        noteClient.updateNote(noteListEntry.getNoteId(), note);
    }
//    @RabbitListener(queues = NoteConsumerApplication.QUEUE_NAME)
    public void deleteNote(int noteId) {
        noteClient.deleteNote(noteId);
    }

}
