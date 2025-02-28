package at.aau.serg.controllers

import at.aau.serg.models.GameResult
import at.aau.serg.services.GameResultService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/leaderboard")
class LeaderboardController(
    private val gameResultService: GameResultService
) {

    @GetMapping
    fun getLeaderboard(): List<GameResult> =
        gameResultService.getGameResults()
            .sortedWith(
                compareByDescending<GameResult> { it.score } // Nach Score absteigend sortieren
                    .thenBy { it.id } // Falls Score gleich ist, nach ID sortieren (niedrigste zuerst)
            )
}

