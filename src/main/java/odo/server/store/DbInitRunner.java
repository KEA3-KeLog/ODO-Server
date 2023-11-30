package odo.server.store;

// DbInitRunner.java
import odo.server.store.domain.Item;
import odo.server.store.domain.UserPoint;
import odo.server.store.repository.ItemRepository;
import odo.server.store.repository.UserPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInitRunner implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final UserPointRepository userPointRepository;

    @Autowired
    public DbInitRunner(ItemRepository itemRepository, UserPointRepository userPointRepository) {
        this.itemRepository = itemRepository;
        this.userPointRepository = userPointRepository;
    }

    @Override
    public void run(String... args) {
        if(itemRepository.count() == 0 && userPointRepository.count() == 0) {
            // Item 초기값 설정
            Item item1 = new Item("방문 인사말 보이스", 2000, 0 , "이 서비스는 블로그 방문자에게 자동으로 인사말을 전하는 기능을 제공합니다. 방문자가 블로그에 접속하면 인사말이 자동으로 재생되어 환영하는 느낌을 전할 수 있습니다.");
            Item item2 = new Item("가수 아이유의 AI 보이스", 3000, 0,"해당 서비스는 가수 아이유의 목소리를 사용하여 블로그 게시글을 읽어줍니다. 이를 통해 게시글이 더 생동감 있게 들릴 수 있으며, 팬들은 아이유의 목소리를 통해 게시글을 즐길 수 있습니다.");
            Item item3 = new Item("가수 나나의 AI 보이스", 3000, 0,"이 서비스는 배우 나나의 목소리를 활용하여 블로그 게시글을 읽어주는 기능을 제공합니다. 나나의 독특한 목소리로 게시글을 듣는 즐거움을 느낄 수 있습니다.");
            Item item4 = new Item("가수 성시경의 AI 보이스", 3000, 0,"가수 성시경의 목소리를 활용하여 블로그 게시글을 읽어주는 서비스입니다. 성시경의 감미로운 목소리가 게시글을 블로그 방문자에게 특별한 경험으로 전달할 것입니다.");
            Item item5 = new Item("스트릭프리즈", 2000, 0,"포스팅 기록 표에서 전체 기록을 자동으로 체크해주는 서비스입니다. 사용자는 효과적으로 블로그 포스팅 기록을 관리할 수 있습니다.");
            Item item6 = new Item("스트릭 그래프 염색칩 X 5", 400, 0,"이는 포스팅 기록 표에서 5칸을 자동으로 체크하여 사용자에게 활동의 성과를 시각적으로 제공합니다.");
            Item item7 = new Item("스트릭 그래프 염색칩 X 40", 3000, 0,"해당 아이템은 포스팅 기록 표에서 40칸을 자동으로 체크하여 더 많은 활동 성과를 강조하는 역할을 합니다.");
            Item item8 = new Item("[도심의 야경]프로필 배경",10000,0, "이 아이템은 개인 블로그의 프로필 배경으로 사용됩니다. 도시의 야경을 테마로 한 아름다운 배경으로 블로그를 더 세련되게 꾸밀 수 있습니다.");
            itemRepository.saveAll(List.of(item1, item2, item3, item4, item5, item6, item7,item8));

            // User 초기값 설정
            UserPoint user = new UserPoint(10000);
            userPointRepository.save(user);
        }
    }
}
