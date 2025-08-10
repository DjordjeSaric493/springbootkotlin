package com.example.quizapi.config

import com.example.quizapi.model.Song
import com.example.quizapi.repository.SongRepo
import kotlinx.serialization.json.Json
import org.springframework.boot.CommandLineRunner
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val songRepo: SongRepo, //rpo za rad s bazom
    private val resourceLoader: ResourceLoader //čita fajlove iz resursa
) : CommandLineRunner {  //izvršava se pri pokretanju app

    override fun run(vararg args: String?) {
        if (songRepo.count() == 0L) {
            println("Učitavam pesme iz songs.json")

            val resource = resourceLoader.getResource("classpath:songs.json") //čita fajl songs iz resources
            val jsonString = resource.inputStream.bufferedReader().use { it.readText() } //pretvara fajl u string jsonString

            val songs = Json.decodeFromString<List<Song>>(jsonString)  //parsira json u listu, slično ko dart

            songRepo.saveAll(songs)  //ubacuje listu u bazu

            println("Ubacio ${songs.size} pesama u bazu")
        } else {
            println("Pesme su već u bazi")
        }
    }
}
/*
* dataloader služi da se automatski popuni baza podacima
* */