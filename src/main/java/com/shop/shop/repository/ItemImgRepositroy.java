package com.shop.shop.repository;

import com.shop.shop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepositroy extends JpaRepository<ItemImg, Long> {
    List<ItemImg> findByItemIdOrderByIdAAsc(Long itemId);
}
