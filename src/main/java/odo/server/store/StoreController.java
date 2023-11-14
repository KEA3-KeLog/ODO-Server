package odo.server.store;

import jakarta.persistence.criteria.CriteriaBuilder;
import odo.server.store.DTO.Inven;
import odo.server.store.DTO.Item;
import odo.server.store.DTO.User;
import odo.server.store.repository.InventoryRepository;
import odo.server.store.repository.ItemRepository;
import odo.server.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/store/api/userpoint/{userId}")
    public ResponseEntity<Integer> getUserPoint(@PathVariable Long userId){

        //System.out.println("userId = " + userId);

        int userPoint = userRepository.findById(userId)
                .map(User::getPoint)
                .orElse(-1);

        //System.out.println("userPoint = " + userPoint);

        return userPoint != -1 ? ResponseEntity.ok(userPoint) : ResponseEntity.notFound().build();
    }

    @GetMapping("/store/api/purchase/{userId}")
    public String updatePoint(@PathVariable Long userId, @RequestParam int point){
        System.out.println("userId = " + userId);
        System.out.println("point = " + point);

        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            user.setPoint(point);
            userRepository.save(user);
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found" + userId));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found" + itemId));
//
//        Inven inven = new Inven();
//        inven.setUser(user);
//        inven.setItem(item);
//        inventoryRepository.save(inven);

        return "inven update success!";

    }



}
