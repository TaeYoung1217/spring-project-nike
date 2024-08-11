package com.github.supercodingspring.nike.web.controller;

import com.github.supercodingspring.nike.repository.PaymentType;
import com.github.supercodingspring.nike.service.SneakersStoreItemService;
import com.github.supercodingspring.nike.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
        return sneakersStoreItemService.findAllSneakers(pageable);
    }

    @Operation(summary = "아이디로 상품 상세 검색")
    @ApiResponse(
            responseCode = "200",
            description = "아이템을 상세 검색 하였습니다."
    )
    @GetMapping("/user-items/sneakers/{id}")
    public SneakerWithInventory findSneakersWithId(@PathVariable String id){
        return sneakersStoreItemService.findSneakersWithId(id);
    }

    @Operation(summary = "사용자로부터 요청받아 주문을 실시합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공적으로 주문을 완료하였습니다."
    )
    @PostMapping("/user-order-wish/order")
    public String orderSneakers(
            @Parameter(description = "model_id, user_id, shipping_address, sneaker_size, order_quantity를 입력받아 주문하기", required = true)
            @RequestBody OrderBody orderBody){
        return sneakersStoreItemService.orderSneakers(orderBody);
    }

    @Operation(summary = "사용자로부터 요청받아 상품을 찜합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공적으로 상품을 찜했습니다."
    )
    @PostMapping("/user-order-wish/wish")
    public String wishSneakers(
            @Parameter(description = "model_id, user_id, sneaker_siez를 입력받아 찜하기", required = true)
            @RequestBody WishBody wishBody){
        return sneakersStoreItemService.wishSneakers(wishBody);
    }

    @Operation(summary = "generalUser의 ID를 받아 주문 목록을 출력")
    @ApiResponse(
            responseCode = "200",
            description = "성공적으로 검색했습니다."
    )
    @GetMapping("/user-order-pay/orders")
    public Page<GeneralUserOrder> findGeneralUserOrder(@RequestParam("g-user-id") Integer gUserId, Pageable pageable){
        return sneakersStoreItemService.findGeneralUserOrder(gUserId, pageable);
    }

    @Operation(summary = "클라이언트의 요청을 받아 주문에 대한 결제 요청")
    @ApiResponse(
            responseCode = "200",
            description = "성공적으로 결제하였습니다."
    )
    @PostMapping("/user-order-pay/pays")
    public String payUserOrder(@RequestBody PaymentBody paymentBody){

        return sneakersStoreItemService.payUserOrder(paymentBody);
    }

}
