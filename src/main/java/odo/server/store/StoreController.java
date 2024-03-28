package odo.server.store;

import odo.server.domain.OauthMemberRepository;
import odo.server.store.domain.Inven;
import odo.server.store.domain.Item;
import odo.server.store.domain.UserPoint;
import odo.server.store.repository.InventoryRepository;
import odo.server.store.repository.ItemRepository;
import odo.server.store.repository.UserPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class StoreController {
    @Autowired
    private UserPointRepository userPointRepository;
 
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private InventoryRepository inventoryRepository;


    @GetMapping("/store/api/userpoint/{userId}")
    public ResponseEntity<Integer> getUserPoint(@PathVariable Long userId){

        //System.out.println("userId = " + userId);

        int userPoint = userPointRepository.findById(userId)
                .map(UserPoint::getPoint)
                .orElse(-1);

        //System.out.println("userPoint = " + userPoint);

        return userPoint != -1 ? ResponseEntity.ok(userPoint) : ResponseEntity.notFound().build();
    }

    @GetMapping("/store/api/purchase/{userId}")
    public String updatePoint(@PathVariable Long userId, @RequestParam int point){
        System.out.println(" ================================== ");
        System.out.println("userId = " + userId);
        System.out.println("point = " + point);
        System.out.println(" ================================== ");

        UserPoint user = userPointRepository.findById(userId).orElse(null);
        if(user != null){
            user.setPoint(point);
            userPointRepository.save(user);
            return "Point update successfully";
        }else{
            return "ID Not Found";
        }
    }

    @GetMapping("/store/api/itemlog/{itemId}")
    public String updateInven(@PathVariable Long itemId){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found" + itemId));
        item.setCount(item.getCount() + 1);
        itemRepository.save(item);
        return "Inven Update Success!";
    }

    @GetMapping("/store/api/inven/{userId}")
    public String addToInventory(@PathVariable Long userId, @RequestParam Long itemId){
        System.out.println("userId = " + userId);
        System.out.println("itemId = " + itemId);

        UserPoint user = userPointRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found" + userId));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found" + itemId));

        Inven inven = new Inven();
        inven.setUserId(userId);
        inven.setItemId(itemId);
        inventoryRepository.save(inven);
        return "inven update success!";

    }


    @GetMapping("/store/api/invencheck/{userId}")
    public ResponseEntity<List<Long>> InventoryCheck(@PathVariable Long userId){
        List<Inven> userInventory = inventoryRepository.findByUserId(userId);
        if (!userInventory.isEmpty()) {
            // Extract itemIds from the inventory
            List<Long> itemIds = userInventory.stream()
                    .map(Inven::getItemId)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(itemIds);
        } else {
            // User not found or has an empty inventory, return 404
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/store/api/getAllItems")
    public List<Map<String, String>> getAllItems() {
        List<Item> itemList = itemRepository.findAll();

        List<Map<String, String>> itemResponses = new ArrayList<>();

        for(Item item : itemList) {
            Map<String, String> response = new HashMap<>();
            response.put("itemName", item.getItemName());
            response.put("itemInfo", item.getInfo());
            response.put("itemId",Long.toString(item.getId()));
            response.put("itemPrice", String.valueOf(item.getPrice()));
            itemResponses.add(response);
        }

        return itemResponses;
    }

}
