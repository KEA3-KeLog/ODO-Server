package odo.server.store;

// DbInitRunner.java
import odo.server.store.DTO.Item;
import odo.server.store.DTO.User;
import odo.server.store.repository.ItemRepository;
import odo.server.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInitRunner implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public DbInitRunner(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if(itemRepository.count() == 0 && userRepository.count() == 0) {
            // Item 초기값 설정
            Item item1 = new Item("방문 인사말 보이스", 2000, 0);
            Item item2 = new Item("가수 아이유의 AI 보이스", 3000, 0);
            Item item3 = new Item("가수 나나의 AI 보이스", 3000, 0);
            Item item4 = new Item("가수 성시경의 AI 보이스", 3000, 0);
            Item item5 = new Item("스트릭프리즈", 2000, 0);
            Item item6 = new Item("스트릭 그래프 염색칩 X 5", 400, 0);
            Item item7 = new Item("스트릭 그래프 염색칩 X 40", 3000, 0);

            itemRepository.saveAll(List.of(item1, item2, item3, item4, item5, item6, item7));

            // User 초기값 설정
            User user = new User(10000);
            userRepository.save(user);
        }
    }
}
