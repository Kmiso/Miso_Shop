package com.shop.shop.service;

import com.shop.shop.dto.ItemFormDto;
import com.shop.shop.entity.Item;
import com.shop.shop.entity.ItemImg;
import com.shop.shop.repository.ItemImgRepositroy;
import com.shop.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepositroy itemImgRepositroy;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        // 상품등록
        Item item = itemFormDto.createItme();
        itemRepository.save(item);

        // 이미지 등록
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if (i == 0) {
                itemImg.setRepimgYn("Y");
            } else {
                itemImg.setRepimgYn("N");
                itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
            }
        }
        return item.getId();
    }
}
