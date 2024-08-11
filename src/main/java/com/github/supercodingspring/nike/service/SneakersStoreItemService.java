package com.github.supercodingspring.nike.service;

import com.github.supercodingspring.nike.repository.Sneaker;
import com.github.supercodingspring.nike.repository.SneakersStoreItemJpaRepository;
import com.github.supercodingspring.nike.service.mapper.SneakerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.github.supercodingspring.nike.web.dto.SneakerDto;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SneakersStoreItemService {
    private final SneakersStoreItemJpaRepository sneakersStoreItemJpaRepository;

    @Cacheable(value = "sneaker", key = "#root.methodName")
    public Page<SneakerDto> findAllItem(Pageable pageable) {
        Page<Sneaker> sneakers = sneakersStoreItemJpaRepository.findAll(pageable);
        return sneakers.map(SneakerMapper.INSTANCE::sneakerToSneakerDto);
    }
}
