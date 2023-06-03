package md.master.app.core.services;

import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import md.master.app.api.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayPalService {
    private final OrderService orderService;

    @Transactional
    public OrderRequest createOrderRequest(Long orderId){
        md.master.app.core.entities.Order order = orderService.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not Found"));

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("master-market")
                .landingPage("BILLING")
                .shippingPreference("SET_PROVIDED_ADDRESS");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequestList = new ArrayList<>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .referenceId(orderId.toString())
                .description("master-market")
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("EUR").value(String.valueOf(order.getTotalPrice()))
                        .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("EUR").value(String.valueOf(order.getTotalPrice())))))
                .items(order.getOrderItems().stream()
                        .map(orderItem -> new Item()
                                .name(orderItem.getProduct().getTitle())
                                .unitAmount(new Money().currencyCode("EUR").value(String.valueOf(orderItem.getPricePerProduct())))
                                .quantity(String.valueOf(orderItem.getQuantity())))
                        .collect(Collectors.toList()))
                .shippingDetail(new ShippingDetail().name(new Name().fullName(order.getUsername()))
                        .addressPortable(new AddressPortable().addressLine1("Hristo Botev").addressLine2("26")
                                .adminArea2("Chisinau").adminArea1("Chisinau").postalCode("2001").countryCode("MD")));
        purchaseUnitRequestList.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequestList);
        return orderRequest;
    }
}
