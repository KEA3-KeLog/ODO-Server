package odo.server.Inventory;

import odo.server.dto.ItemInfo;
import odo.server.store.domain.Inven;
import odo.server.store.domain.Item;
import odo.server.store.repository.InventoryRepository;
import odo.server.store.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class InventoryController {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/Inven/api/userInventory/{userId}")
    public List<Map<String, String>> testAPI(@PathVariable Long userId){
        List<Inven> items = inventoryRepository.findByUserId(userId);
        List<Long> itemIds = items.stream().map(Inven::getItemId).collect(Collectors.toList());

        List<Item> itemList = itemRepository.findByIdIn(itemIds);

        List<Map<String, String>> itemResponses = new ArrayList<>();

        for(Item item : itemList){
            Map<String, String> response = new HashMap<>();
            response.put("itemName", item.getItemName());
            response.put("itemInfo", item.getInfo());
            response.put("itemId",Long.toString(item.getId()));
            itemResponses.add(response);
        }

        return itemResponses;
    }

//    @GetMapping("/userInventory/{userId}")
//    public ResponseEntity<Map<String, Object>> getUserInventory(@PathVariable Long userId) {
//        Map<String, Object> response = new HashMap<>();
//
//        // Join을 사용하여 두 테이블을 연결하고 조회한다.
//        List<Object[]> joinedResult = inventoryRepository.joinItemTable(userId);
//
//        List<ItemInfo> itemInfos = new ArrayList<>();
//        for (Object[] result : joinedResult) {
//            String itemName = (String) result[0];
//            int itemPrice = (int) result[1];
//            itemInfos.add(new ItemInfo(itemName, itemPrice));
//        }
//
//        response.put("userId", userId);
//        response.put("items", itemInfos);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
