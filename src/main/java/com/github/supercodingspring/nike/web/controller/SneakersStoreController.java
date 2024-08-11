package com.github.supercodingspring.nike.web.controller;

import com.github.supercodingspring.nike.service.SneakersStoreItemService;
import com.github.supercodingspring.nike.web.dto.OrderBody;
import com.github.supercodingspring.nike.web.dto.SneakerWithInventory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.github.supercodingspring.nike.web.dto.SneakerDto;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "SneakersStoreController", description = "온라인 신발 구매")
public class SneakersStoreController {

    private final SneakersStoreItemService sneakersStoreItemService;

    @Operation(summary = "Nike 모든 상품을 Pageable로 나열")
    @ApiResponse(
            responseCode = "200",
            description = "모든 아이템을 검색했습니다."
    )
    @GetMapping("/user-items/sneakers")
    public Page<SneakerDto> findAllSneakers(Pageable pageable){
        return sneakersStoreItemService.findAllItem(pageable);
    }

    @GetMapping("/user-items/sneakers/{id}")
    public SneakerWithInventory findWithId(@PathVariable String id){
        return sneakersStoreItemService.findItemById(id);
    }

    @PostMapping("/user-order-wish/order")
    public String orderSneakers(
            @Parameter(description = "model_id, user_id, shipping_address, sneaker_size, order_quantity", required = true)
            @RequestBody OrderBody orderBody){
        return sneakersStoreItemService.orderItem(orderBody);
    }

}
