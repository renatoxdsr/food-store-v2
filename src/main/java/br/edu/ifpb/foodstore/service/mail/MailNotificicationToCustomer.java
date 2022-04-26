package br.edu.ifpb.foodstore.service.mail;

import br.edu.ifpb.foodstore.domain.Customer;
import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailNotificationToCustomer implements MailNotification{
    private final LogService logService;
    
    public void sendMailNotificationToCustomer(String message, Customer customer){
        this.message = message;
        this.customer = customer;
    }

    @Override
    public void send(String message, Customer customer) {
            logService.info("send mail notification to "+ customer.getEmail());
            logService.debug(message);
            }
}