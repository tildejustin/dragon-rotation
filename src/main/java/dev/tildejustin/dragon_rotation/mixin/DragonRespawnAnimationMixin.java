package dev.tildejustin.dragon_rotation.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.DragonRespawnAnimation;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import java.util.Random;

@Mixin(DragonRespawnAnimation.class)
public abstract class DragonRespawnAnimationMixin {
    @Redirect(method = {"createDragon", "Lnet/minecraft/class_2752;method_11817()V"}, at = @At(value = "INVOKE", target = "Ljava/util/Random;nextFloat()F"), require = 1)
    private float printDragonYaw(Random instance) {
        float yaw = instance.nextFloat();
        MinecraftClient.getInstance().player.sendMessage(new LiteralText(String.format("Dragon Yaw: %f", yaw * 360.0F)));
        return yaw;
    }
}
