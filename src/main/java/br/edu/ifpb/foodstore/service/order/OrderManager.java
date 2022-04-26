package br.edu.ifpb.foodstore.service.order;

import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.mail.MailNotification;
import br.edu.ifpb.foodstore.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderManager {

    private final PaymentService paymentService;
    private final MailNotification mailNotification;
    private final LogService logService;

    public void payOrder(Order order, PaymentService.PaymentType paymentType) {
        order.setStatus(Order.OrderEnum.IN_PROGRESS);
        try {
            paymentService.doPayment(paymentType);
            order.setStatus(Order.OrderEnum.PAYMENT_SUCCESS);
            mailNotification.sendMailNotificationToAdmin(String.format("Order %d completed successfully", order.getId()));
            mailNotification.sendMailNotificationToCustomer(String.format("Order %d completed successfully", order.getId()), order.getCustomer());
            logService.info("payment finished");
        } catch (Exception e) {
            logService.error("payment refused");
            order.setStatus(Order.OrderEnum.PAYMENT_REFUSED);
            mailNotification.sendMailNotificationToAdmin(String.format("Order %d refused", order.getId()));
        }
    }

    public void cancelOrder(Order order) throws OrderException {
        if (order.getStatus() == Order.OrderEnum.CANCELED) {
            throw new OrderException(order.getStatus().cancel());
        }
        else {
            logService.info(order.getStatus().cancel());
        }

        order.setStatus(Order.OrderEnum.CANCELED);

        mailNotification.sendMailNotificationToAdmin(String.format("Order %d canceled", order.getId()));
        mailNotification.sendMailNotificationToCustomer(String.format("Order %d canceled", order.getId()), order.getCustomer());
        logService.debug(String.format("order %d canceled", order.getId()));
    }

}
