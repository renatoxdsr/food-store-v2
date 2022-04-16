package br.edu.ifpb.foodstore.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar creationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    private OrderStatus status = OrderStatus.IN_PROGRESS;

    public enum OrderStatus {
        IN_PROGRESS, CANCELED, PAYMENT_SUCCESS, PAYMENT_REFUSED
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public void removeItem(OrderItem item) {
        this.items.remove(item);
    }

}
