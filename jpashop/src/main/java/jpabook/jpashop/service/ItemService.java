package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findItem(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     * 변경 감지 사용하여 영속성 컨텍스트가 자동으로 업데이트
     */
    @Transactional
    public void updateItem(Long id, String name, int price) {
        Item findItem = itemRepository.findOne(id);
        findItem.setName(name);
        findItem.setPrice(price);
    }
}
