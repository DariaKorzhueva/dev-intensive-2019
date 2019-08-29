package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.repositories.ChatRepository
import ru.skillbranch.devintensive.utils.DataGenerator

class MainViewModel : ViewModel() {
    private val chatReposity = ChatRepository
    private val chats = Transformations.map(chatReposity.loadChats()){chats->

        return@map chats.filter { !it.isArchived }.map{it.toChatItem()}.sortedBy { it.id.toInt() }
    }

    fun getChatData(): LiveData<List<ChatItem>> {
        return chats
    }

    fun addToArchive(chatId: String) {
        val chat = chatReposity.find(chatId)
        chat ?: return
        chatReposity.update(chat.copy(isArchived = true))
    }

    fun restoreFromArchive(chatId: String){
        val chat = chatReposity.find(chatId)
        chat ?: return
        chatReposity.update(chat.copy(isArchived = false))
    }
}