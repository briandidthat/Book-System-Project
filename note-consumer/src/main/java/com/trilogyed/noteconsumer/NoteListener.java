package com.trilogyed.noteconsumer;

import com.trilogyed.noteconsumer.util.messages.NoteListEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/*Task:
 * Define a Listener that will be listening to the wire, and printing out any new entries
 */
@Service
public class NoteListener {
    @RabbitListener(queues = NoteConsumerApplication.QUEUE_NAME)
    public void receiveMessage(NoteListEntry noteListEntry) {
        System.out.println(noteListEntry.toString());
    }
}
