package br.edu.ifpb.foodstore.service.payment;

import br.edu.ifpb.foodstore.service.log.LogHandler;
import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    public enum PaymentType {
        CREDIT_CARD, DEBIT, BILLET, PAYPAL
    }

    private final LogService logService;

    public void doPayment(PaymentType paymentType) throws Exception {
        switch (paymentType) {
            case CREDIT_CARD:
                logService.info("Do credit card payment!");
                break;
            case DEBIT:
                logService.info("Do debit payment!");
                break;
            case PAYPAL:
                logService.info("Do paypal payment!");
                break;
            case BILLET:
                logService.info("Do billet payment!");
                break;
            default:
                throw new Exception("unknown payment method");
        }
    }

}
