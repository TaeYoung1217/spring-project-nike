package com.github.supercodingspring.nike.service;

import com.github.supercodingspring.nike.repository.*;
import com.github.supercodingspring.nike.service.exceptions.InvalidValueException;
import com.github.supercodingspring.nike.service.exceptions.NotFoundException;
import com.github.supercodingspring.nike.service.mapper.GeneralUserOrderMapper;
import com.github.supercodingspring.nike.service.mapper.SneakerMapper;
import com.github.supercodingspring.nike.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class SneakersStoreItemService {
    private final SneakerJpaRepository sneakerJpaRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final GeneralUserJpaRepository generalUserJpaRepository;
    private final WishJpaRepository wishJpaRepository;
    private final PaymentJpaRepository paymentJpaRepository;

    //@Cacheable(value = "sneaker", key = "#root.methodName")
    public Page<SneakerDto> findAllSneakers(Pageable pageable) {
        Page<Sneaker> sneakers = sneakerJpaRepository.findAll(pageable);
        return sneakers.map(SneakerMapper.INSTANCE::sneakerToSneakerDto);
    }

    public Double findPriceById(String id){
        Sneaker sneaker = sneakerJpaRepository.findById(Integer.parseInt(id)).orElseThrow(()->new NotFoundException("model ID인 "+ id +"를 찾을 수 없습니다."));
        return sneaker.getPrice();

    }
    public SneakerWithInventory findSneakersWithId(String id) {
        Sneaker sneaker = sneakerJpaRepository.
                findById(Integer.parseInt(id)).orElseThrow(()->new NotFoundException("model ID인 "+ id +"를 찾을 수 없습니다."));

        List<SneakerInventory> sneakerInventoryList = sneaker.getKoreaInventories().stream().map(SneakerInventory::new).toList();
        return SneakerWithInventory.builder()
                .modelId(sneaker.getModelId())
                .sneaker_inventories(sneakerInventoryList)
                .price(sneaker.getPrice())
                .model_traits(sneaker.getSneakerModelTraits().stream().map(sneakerModelTraits -> sneakerModelTraits.getSneakerTraits().getDescriptions()).toList())
                .build();
    }

    public String orderSneakers(OrderBody orderBody) {
        Double totalPrice = findPriceById(String.valueOf(orderBody.getModelId())) * orderBody.getOrderQuantity();
        Sneaker sneaker = sneakerJpaRepository.findById(orderBody.getModelId()).orElseThrow(()->new NotFoundException("modelId "+orderBody.getModelId()+"를 찾을 수 없습니다."));
        GeneralUser generalUser = generalUserJpaRepository.findById(orderBody.getUserId()).orElseThrow(()->new NotFoundException("userId "+orderBody.getUserId()+"를 찾을 수 없습니다."));

        if(orderBody.getShippingAddress() == null)
            if(generalUser.getFavoriteShoppingAddress() == null)
                throw new InvalidValueException("유효하지 않은 주문입니다.");
            else orderBody.setShippingAddress(generalUser.getFavoriteShoppingAddress());

        Orders orders = Orders.builder()
                .generalUser(generalUser)
                .sneaker(sneaker)
                .sneakerSize(orderBody.getSneakerSize())
                .orderQuantity(orderBody.getOrderQuantity())
                .totalPrice(totalPrice)
                .shippingAddress(orderBody.getShippingAddress())
                .orderStatus(OrderStatus.ORDER_COMPLETED)
                .orderAt(LocalDateTime.now()).build();


        orderJpaRepository.save(orders);

        return "구매하신 물품 "+totalPrice+"이고 예약완료 되었습니다.";
    }

    public String wishSneakers(WishBody wishBody) {
        Sneaker sneaker = sneakerJpaRepository.findById(wishBody.getModelId()).orElseThrow(()->new NotFoundException("modelId "+wishBody.getModelId()+"를 찾을 수 없습니다."));
        GeneralUser generalUser = generalUserJpaRepository.findById(wishBody.getUserId()).orElseThrow(()->new NotFoundException("userId "+wishBody.getUserId()+"를 찾을 수 없습니다."));

        Wish wish = Wish.builder()
                .sneakerSize(wishBody.getSneakerSize())
                .modelId(sneaker.getModelId())
                .gUserId(generalUser.getGUserId())
                .expectedReplenishmentDate(null)
                .wishAt(LocalDateTime.now())
                .build();

        wishJpaRepository.save(wish);

        return "물품 찜 되었습니다.";
    }

    public Page<GeneralUserOrder> findGeneralUserOrder(Integer gUserId, Pageable pageable) {
        GeneralUser generalUser = generalUserJpaRepository.findById(gUserId).orElseThrow(()->new NotFoundException("userId "+gUserId+"를 찾을 수 없습니다."));
        Page<Orders> ordersList = orderJpaRepository.findAllByGeneralUser_gUserId(generalUser.getGUserId(), pageable);
        return ordersList.map(GeneralUserOrderMapper.INSTANCE::OrderToGeneralUserOrder);
    }

    public String payUserOrder(PaymentBody paymentBody) {
        GeneralUser generalUser = generalUserJpaRepository.findById(paymentBody.getGeneralUserId()).orElseThrow(()->new NotFoundException("userId "+paymentBody.getGeneralUserId()+"를 찾을 수 없습니다."));
        Orders orders = orderJpaRepository.findById(paymentBody.getOrderId()).orElseThrow(()->new NotFoundException("요청하신 주문 "+paymentBody.getOrderId()+"를 찾을 수 없습니다"));

        if(orders.getGeneralUser().getGUserId() == paymentBody.getGeneralUserId() && orders.getOrderId() == paymentBody.getOrderId()){
            if(orders.getOrderStatus() != OrderStatus.ORDER_COMPLETED)
                return "해당 주문은 '주문완료' 상태가 아니어서 결제할 수 없습니다.";

            Payment payment = Payment.builder()
                    .generalUser(generalUser)
                    .orders(orders)
                    .type(Objects.requireNonNull(PaymentType.fromDesc(paymentBody.getType())).getCode())
                    .paymentAt(LocalDateTime.now())
                    .build();

            System.out.println(payment);

            paymentJpaRepository.save(payment);
            orders.setOrderStatus(OrderStatus.PAY_COMPLETED);
            orderJpaRepository.save(orders);
            return "결제 성공하였습니다.";
        }
        else return "결제 정보를 찾을 수 없습니다.";


    }
}
