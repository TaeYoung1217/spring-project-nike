package com.github.supercodingspring.nike.service;

import com.github.supercodingspring.nike.repository.*;
import com.github.supercodingspring.nike.service.exceptions.NotFoundException;
import com.github.supercodingspring.nike.service.mapper.SneakerMapper;
import com.github.supercodingspring.nike.web.dto.OrderBody;
import com.github.supercodingspring.nike.web.dto.SneakerInventory;
import com.github.supercodingspring.nike.web.dto.SneakerWithInventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.github.supercodingspring.nike.web.dto.SneakerDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SneakersStoreItemService {
    private final SneakerJpaRepository sneakerJpaRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final UserJpaRepository userJpaRepository;

    //@Cacheable(value = "sneaker", key = "#root.methodName")
    public Page<SneakerDto> findAllItem(Pageable pageable) {
        Page<Sneaker> sneakers = sneakerJpaRepository.findAll(pageable);
        return sneakers.map(SneakerMapper.INSTANCE::sneakerToSneakerDto);
    }

    public Double findPriceById(String id){
        Sneaker sneaker = sneakerJpaRepository.findById(Integer.parseInt(id)).orElseThrow(()->new NotFoundException("model ID인 "+ id +"를 찾을 수 없습니다."));
        return sneaker.getPrice();

    }
    public SneakerWithInventory findItemById(String id) {
        Sneaker sneaker = sneakerJpaRepository.
                findById(Integer.parseInt(id)).orElseThrow(()->new NotFoundException("model ID인 "+ id +"를 찾을 수 없습니다."));

        List<SneakerInventory> sneakerInventoryList = sneaker.getKoreaInventories().stream().map(SneakerInventory::new).toList();

        return new SneakerWithInventory(sneaker.getModelId(),
                sneaker.getSneakerModelTraits().stream().map(sneakerModelTraits -> sneakerModelTraits.getSneakerTraits().getDescriptions()).toList(),
                sneaker.getPrice(),
                sneakerInventoryList);
    }

    public String orderItem(OrderBody orderBody) {
        Double totalPrice = findPriceById(String.valueOf(orderBody.getModelId())) * orderBody.getOrderQuantity();
        Sneaker sneaker = sneakerJpaRepository.findById(orderBody.getModelId()).orElseThrow(()->new NotFoundException("modelId "+orderBody.getModelId()+"를 찾을 수 없습니다."));
        User user = userJpaRepository.findById(orderBody.getUserId()).orElseThrow(()->new NotFoundException("userId "+orderBody.getUserId()+"를 찾을 수 없습니다."));

        Orders orders = new Orders(orderBody, sneaker, user);

        orders.setOrderAt(LocalDateTime.now());
        orders.setTotalPrice(totalPrice);
        orders.setOrderStatus(OrderStatus.ORDER_COMPLETED);

        orderJpaRepository.save(orders);

        return "구매하신 물품 "+totalPrice+"이고 예약완료 되었습니다.";
    }
}
