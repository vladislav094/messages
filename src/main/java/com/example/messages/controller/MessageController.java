package com.example.messages.controller;

import com.example.messages.dto.MessageDTO;
import com.example.messages.entity.Message;
import com.example.messages.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> readAll() {
        return mappingResponseListMessage(messageService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Message>> readById(@PathVariable Integer id) {
        return new ResponseEntity<>(messageService.readById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Message> create(@RequestBody MessageDTO message) {
        return mappingResponseMessage(messageService.create(message));
    }

    @PutMapping
    public ResponseEntity<Message> update(@PathVariable Integer id, @RequestBody Message message) {
        return mappingResponseMessage(messageService.update(id, message));
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Integer id) {
        messageService.delete(id);
        return HttpStatus.OK;
    }

    private ResponseEntity<Message> mappingResponseMessage(Message message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private ResponseEntity<List<Message>> mappingResponseListMessage(List<Message> messages) {
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
