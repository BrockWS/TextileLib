package nerdhub.textilelib.mixin.client;

import nerdhub.textilelib.event.client.render.RenderWorldCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = "ldc=entities", shift = At.Shift.BEFORE), method = "renderCenter")
    private void renderCenter(float deltaTime, long long_1, CallbackInfo ci) {
        this.client.getProfiler().swap("textilelib_render_world");
        RenderWorldCallback.EVENT.invoker().render(this.client.worldRenderer, deltaTime);
    }
}
