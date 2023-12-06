package com.example.messages.service;

import com.example.messages.dto.MessageDTO;
import com.example.messages.entity.Message;
import com.example.messages.exceptions.ResourceNotFoundException;
import com.example.messages.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> readAll() {
        return messageRepository.findAll();
    }

    public Optional<Message> readById(Integer id) {
        return messageRepository.findById(id);
    }

    public Message create(MessageDTO message) {
        return messageRepository.save(Message.builder()
                .text(message.getText())
                .build());
    }

    public Message update(Integer id, Message text) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Messages does not exist with the ID" + id));
        message.setText(text.getText());
        return messageRepository.save(message);
    }

    public void delete(Integer id) {
        messageRepository.deleteById(id);
    }
}
