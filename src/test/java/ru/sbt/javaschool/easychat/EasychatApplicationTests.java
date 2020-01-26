package ru.sbt.javaschool.easychat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sbt.javaschool.easychat.controller.ChatController;
import ru.sbt.javaschool.easychat.repository.ChatRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class EasychatApplicationTests {

	@Autowired
	ChatController chatController;

	@Autowired
	ChatRepository chatRepository;

	@Test
	public void catsReflectedInRead() {
		chatController.create();
		assertNotNull(chatRepository.findAll());

	}

}
