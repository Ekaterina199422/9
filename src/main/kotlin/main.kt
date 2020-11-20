fun main() {


    UserService.addUser(User(name = "Гоголь"))
    UserService.addUser(User(name = "Толстой"))
    UserService.addUser(User(name = "Блок"))
    UserService.addUser(User(name = "Шолохов"))
    UserService.addUser(User(name = "Достоевский"))

    val gogoly = UserService.findUserByName("Гоголь")
    val tolstoy = UserService.findUserByName("Толстой")
    val dostoyvskiy = UserService.findUserByName("Достоевский")
    val sholohov = UserService.findUserByName("Шолохов")
    val bloc = UserService.findUser{ user -> user.id == UserService.findUser{it.name == "Блок"}.id}


    ChatService.createMessage(gogoly, tolstoy, "слышал сашу пушкина арестовали!")
    ChatService.createMessage(tolstoy, gogoly, "небось опять стих в вк опубликовал!")
    ChatService.createMessage(gogoly, tolstoy, "да")
    ChatService.createMessage(dostoyvskiy, gogoly, "Я ведь его пердупреждал в вк можно только сказки публиковать! Про политику лучше в Фейсбуке писать")
    ChatService.createMessage(gogoly, dostoyvskiy, "причем тут фесбукю Я вот тут петицию опуликовал, о том что за свое мнение сажать нельзя. Много людей подписалось")
    ChatService.createMessage(sholohov, gogoly, "Я ничего подписывать не буду")
    ChatService.createMessage(bloc, gogoly, "А я подпищу, мне творчетсво александра нравится? Хотя до моего уравня ему далеко. Во послушайте Вечерний час над ресторанами")
    ChatService.createMessage(dostoyvskiy, bloc, "Да подожди со своими рестораними, в каком сизо он сидит?")
    ChatService.createMessage(gogoly, dostoyvskiy, "В бутырьке Есинин помог выяснить, у него хорошие связи")
    ChatService.createMessage(dostoyvskiy, gogoly, "Я там сидел условия неплохие! Кстате поччему у меня ком тормозит?")
    ChatService.createMessage(tolstoy, dostoyvskiy, "перезагрузись или купи новый. А Саше нужно найти адвоката!")
    ChatService.createMessage(dostoyvskiy, tolstoy, "Кони подойдет")


    println("\nВсего чатов: ${ChatService.getChatsNumber()}")
    println("Из них непрочитанных: ${ChatService.getUnreadChatsCount()}")

    ChatService.deleteChat(ChatService.getChatByUsers(gogoly, tolstoy))
    println("\nПосле удаления одного из чатов:")
    println("Всего чатов: ${ChatService.getChatsNumber()}")
    println("Из них непрочитанных: ${ChatService.getUnreadChatsCount()}")

    println("Количество чатов пользователя ${bloc.name}: ${bloc.getChatsList().size} ")

    println("\nСообщения пользователей ${bloc.name} и ${gogoly.name}:\n${ChatService.getMessagesStringByUsers(gogoly, bloc)}")

}
