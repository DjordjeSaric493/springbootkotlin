package com.example.quizapi.controller
import com.example.quizapi.model.Song
import com.example.quizapi.repository.SongRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.data.jpa.repository.Query

@RestController
@RequestMapping("/api/songs") // Stavi sam sve endpoint-e pod /api/songs
class SongController(private val songRepo: SongRepo) {

    // vraća listu svih pesama.
    @GetMapping
    fun getAllSongs(): List<Song> = songRepo.findAll()

    // vraća jednu nasumičnu pesmu korišćenjem SQL upita.
    @GetMapping("/random")
    fun getRandomSong(): ResponseEntity<Song> {
        val song = songRepo.findRandomSong()
        return if (song != null) {  //ako nije null vrati OK tako i za ostale
            ResponseEntity.ok(song)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // vraća pesmu na osnovu njenog ID-a.
    @GetMapping("/{id}")
    fun getSongById(@PathVariable id: Long): ResponseEntity<Song> {
        val song = songRepo.findById(id).orElse(null)
        return if (song != null) {
            ResponseEntity.ok(song)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // dodat PUT endpoint za ažuriranje trivia poruke na osnovu ID-a.
    @PutMapping("/{id}/trivia")
    fun updateTrivia(@PathVariable id: Long, @RequestBody trivia: String): ResponseEntity<Song> {
        val song = songRepo.findById(id).orElse(null)
        return if (song != null) {
            val updatedSong = song.copy(trivia = trivia)
            ResponseEntity.ok(songRepo.save(updatedSong))
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
