package me.drex.scoreboard_fix.mixin;

import com.mojang.authlib.GameProfile;
import me.drex.scoreboard_fix.ScoreboardFixMod;
import net.minecraft.network.Connection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.GameProfileCache;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(PlayerList.class)
public abstract class PlayerListMixin {

    @Shadow
    @Final
    private MinecraftServer server;

    @Inject(
            method = "placeNewPlayer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/players/GameProfileCache;add(Lcom/mojang/authlib/GameProfile;)V"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void detectPlayerNameChange(Connection connection, ServerPlayer serverPlayer, CallbackInfo ci, GameProfile gameProfile, GameProfileCache gameProfileCache, Optional<GameProfile> optional, String previousName) {
        String updatedName = serverPlayer.getGameProfile().getName();
        if (!updatedName.equalsIgnoreCase(previousName)) {
            ScoreboardFixMod.updateScoreboardEntries(this.server.getScoreboard(), previousName, updatedName);
        }
    }

}
