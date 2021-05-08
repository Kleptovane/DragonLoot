package net.dragonloot.entity.render;

import net.dragonloot.DragonLootMain;
import net.dragonloot.entity.DragonTridentEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class DragonTridentEntityRenderer extends EntityRenderer<DragonTridentEntity> {
    public static final Identifier TEXTURE = DragonLootMain.ID("textures/entity/dragon_trident.png");
    private final TridentEntityModel model = new TridentEntityModel();

    public DragonTridentEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    @Override
    public void render(DragonTridentEntity tridentEntity, float f, float g, MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(Vector3f.POSITIVE_Y
                .getDegreesQuaternion(MathHelper.lerp(g, tridentEntity.prevYaw, tridentEntity.yaw) - 90.0F));
        matrixStack.multiply(Vector3f.POSITIVE_Z
                .getDegreesQuaternion(MathHelper.lerp(g, tridentEntity.prevPitch, tridentEntity.pitch) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider,
                this.model.getLayer(this.getTexture(tridentEntity)), false, tridentEntity.isEnchanted());
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        super.render(tridentEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(DragonTridentEntity tridentEntity) {
        return TEXTURE;
    }
}
