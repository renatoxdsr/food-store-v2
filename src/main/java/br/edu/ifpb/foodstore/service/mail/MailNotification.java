package br.edu.ifpb.foodstore.service.mail;

import br.edu.ifpb.foodstore.domain.Customer;
import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.edu.ifpb.foodstore.service.mail.MailNotificationToCustomer;
import br.edu.ifpb.foodstore.service.mail.MailNotificationToAdmin;

@Service
@RequiredArgsConstructor
public interface MailNotification {
    void send(String message, Customer customer);
}
