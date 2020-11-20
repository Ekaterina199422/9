import org.junit.Test
import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun addUser() {
        val userService = UserServiceCore()
        val user2 = User(name = "Katy")
        val user2Id = userService.addUser(user2)

        val expected = user2.name
        val result = userService.findUserById(user2Id).name

        assertEquals(expected, result)
    }

    @Test(expected = UserNotFoundException::class)
    fun addUserShouldThrow() {
        val userService = UserServiceCore()
        val user = User(name = "Katy")
        val userId = userService.addUser(user)
        userService.findUserById(userId + 1).name
    }

    @Test
    fun createMessage() {
        val chatService = ChatServiceCore()
        val userService = UserServiceCore()
        val user1Id = userService.addUser(User(name = "Федя"))
        val user2Id = userService.addUser(User(name = "Katy"))
        val user1 = userService.findUserByName("Федя")
        val user2 = userService.findUserById(user2Id)
        val messageText = "Весьма неплохо!"
        val messageId = chatService.createMessage(user1, user2, messageText)
        val result = chatService.getMessageById(messageId)
        assertEquals(messageText, result.text)
        val expected = "${user1.name}: ${messageText}\n"
        assertEquals(expected, chatService.getMessagesStringByUsers(user1, user2))
    }

    @Test(expected = ChatNotFoundException::class)
    fun getChatByIdShouldThrow() {
        val chatService = ChatServiceCore()
        val userService = UserServiceCore()
        val user1Id = userService.addUser(User(name = "Федя"))
        val user2Id = userService.addUser(User(name = "Katy"))
        val user1 = userService.findUserByName("Katy")
        val user2 = userService.findUserById(user2Id)
        val messageText = "Весьма неплохо!"
        chatService.getChatById(22)
    }

    @Test
    fun getChats() {
        val chatService = ChatServiceCore()
        val userService = UserServiceCore()
        val user1Id = userService.addUser(User(name = "Федя"))
        val user2Id = userService.addUser(User(name = "Katy"))
        val user1 = userService.findUserByName("Федя")
        val user2 = userService.findUserById(user2Id)
        val messageText = "Весьма неплохо!"
        val messageId = chatService.createMessage(user1, user2, messageText)
        assertNotNull(chatService.getChatByUsers(user1, user2))
        assertEquals(chatService.getChatsNumber(), chatService.getUnreadChatsCount())
    }


}