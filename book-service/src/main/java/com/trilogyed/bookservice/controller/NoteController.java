//package com.trilogyed.bookservice.controller;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//
///*Task:
// *
// */
//public class NoteController {
//    public static final String EXCHANGE = "note-exchange";
//    public static final String ROUTING_KEY = "note.list.add.note.controller";
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public NoteController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

//    @RequestMapping(value = "/account", method = RequestMethod.POST)
//    public String createAccount(@RequestBody Account account) {
//        // create message to send to email list creation queue
//        EmailListEntry msg = new EmailListEntry(account.getFirstName() + " " + account.getLastName(), account.getEmail());
//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
//        System.out.println("Message Sent");
//
//        // Now do account creation stuff...
//
//        return "Account Created";
//    }
//}
