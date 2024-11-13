package com.sparta.ordermanagement.application.service;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.application.domain.order.OrderForUpdate;
import com.sparta.ordermanagement.application.exception.order.InvalidOrderException;
import com.sparta.ordermanagement.application.output.OrderOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderOutputPort orderOutPutPort;
    private final ShopService shopService;
    private final ProductService productService;

    /* User, Product 기능 통합 후 각 id 검증 메서드 추가 필요*/
    public String createOrder(OrderForCreate orderForCreate) {
        /* User ID 기반 검증은 User 기능과 통합 후 사용 - 현재는 생략*/
        /* 유저 권한에 따라 ON/OFF 상태 확인하는 검증 메서드 추가 필요*/

        productService.validateProductUuidAndGetProduct(orderForCreate.productId());

        // OrderService가 ShopService를 의존성 주입하지 않고도 검증할 수 있도록 리팩토링 필요
        shopService.validateShopIdAndGetShop(orderForCreate.shopId());

        return orderOutPutPort.saveOrder(orderForCreate).getOrderUuid();
    }

    /* 업데이트 기능
    * 1. 주문 상태 변경 승인, 완료, 취소 - 취소는 주문 등록 후 5분 이내일 때만 가능
    * 2. 주문상품 변경 - 변경(기존 상품에서 다른 상품으로 변경일 때와 추가일 때 차이 고려), 추가
    * 3. 수량 변경 - 수량 변경에서 주문상품 변경은 해당 상품의 수량을 0으로 처리하는 방법 고려
    * 4. 주문 취소 되었을 때 결제도 취소되도록 설정되어야 함 - 아직 결제 없으니까 추후 생길 때 이 부분 추가
    *  수정할 때 주문 상태의 경우 CUSTOMER는 취소만 가능하게 OWNER부터 모든 상태 변경 가능하도록
    * 주문 상품 변경에 대한 서비스 코드는 OrderProductService 에서 가져와서 쓰기, 이 서비스 코드에서는 주문 상태 변경만 고려*/
    public String updateOrderState(OrderForUpdate orderForUpdate) {

        validateOrderIdAndGetOrder(orderForUpdate.orderId());

        return orderOutPutPort.updateOrderState(orderForUpdate);
    }

    private Order validateOrderIdAndGetOrder(String orderId) {
        return orderOutPutPort.findByOrderId(orderId)
                .orElseThrow(() -> new InvalidOrderException(orderId));
    }

}
