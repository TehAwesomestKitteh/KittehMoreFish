package me.elsiff.morefish.listener

import me.elsiff.morefish.fishing.FishTypeTable
import me.elsiff.morefish.fishing.catcheffect.CatchEffectCollection
import org.bukkit.entity.Item
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent

/**
 * Created by elsiff on 2018-12-24.
 */
class FishingListener(
        private val fishTypes: FishTypeTable,
        private val catchEffects: CatchEffectCollection
) : Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun onPlayerFish(event: PlayerFishEvent) {
        if (event.state == PlayerFishEvent.State.CAUGHT_FISH && event.caught is Item) {
            val fish = fishTypes.pickRandomType().generateFish()
            catchEffects.playAll(event.player, fish)
            (event.caught as Item).itemStack = fish.toItemStack()
        }
    }
}