package rina.turok.bope.mixins;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.client.Minecraft;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;

import java.awt.*;

// Module.
import rina.turok.bope.bopemod.hacks.render.BopePlayerESP;
import rina.turok.bope.bopemod.hacks.render.BopeEntityESP;

// Core.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.draw.TurokGL;

/**
 * @author Rina
 *
 * Created by Rina.
 * 23/04/20.
 *
 */
@Mixin(value = RenderLivingBase.class)
public abstract class BopeMixinRenderLivingBase <T extends EntityLivingBase> extends BopeMixinRender<T> {
	public final Minecraft mc = Minecraft.getMinecraft();

	@Shadow
	protected ModelBase mainModel;

	// Render.
	@Inject(method = "doRender", at = @At("HEAD"))
	private void doRender(T entity, double x, double y, double z, float yaw, float partial_ticks, CallbackInfo callback) {
		if (entity instanceof IMob && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRange").get_value(1)) && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPHostile").get_value(true)) {
			BopeEntityESP.distance_player = mc.player.getDistance(entity);

			if (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRenderEntity").in("Chams")) {
				GlStateManager.pushMatrix();

				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);

				glEnable(GL11.GL_POLYGON_OFFSET_FILL);

				glPolygonOffset(1.0f, -1100000.0f);

				GlStateManager.popMatrix();
			}
		}

		if (entity instanceof EntityAnimal && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRange").get_value(1)) && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPAnimals").get_value(true)) {
			BopeEntityESP.distance_player = mc.player.getDistance(entity);

			if (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRenderEntity").in("Chams")) {
				GlStateManager.pushMatrix();

				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);

				glEnable(GL11.GL_POLYGON_OFFSET_FILL);

				glPolygonOffset(1.0f, -1100000.0f);

				GlStateManager.popMatrix();
			}
		}

//		if (entity instanceof EntityESPCrystal && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRange").get_value(1)) && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPCrystal").get_value(true)) {
//			BopeEntityESP.distance_player = mc.player.getDistance(entity);
//
//			if (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRenderEntity").in("Chams")) {
//				GlStateManager.pushMatrix();
//
//				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
//
//				glEnable(GL11.GL_POLYGON_OFFSET_FILL);
//
//				glPolygonOffset(1.0f, -1100000.0f);
//
//				GlStateManager.popMatrix();
//			}
//		}

//		if (entity instanceof EntityItem && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRange").get_value(1)) && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPItems").get_value(true)) {
//			BopeEntityESP.distance_player = mc.player.getDistance(entity);
//
//			if (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRenderEntity").in("Chams")) {
//				GlStateManager.pushMatrix();
//
//				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
//
//				glEnable(GL11.GL_POLYGON_OFFSET_FILL);
//
//				glPolygonOffset(1.0f, -1100000.0f);
//
//				GlStateManager.popMatrix();
//			}
//		}

		if (entity instanceof EntityPlayer && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPRange").get_value(1))) {
			BopePlayerESP.distance_player = mc.player.getDistance(entity);

			if (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPRenderEntity").in("Chams")) {
				GlStateManager.pushMatrix();

				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);

				glEnable(GL11.GL_POLYGON_OFFSET_FILL);

				glPolygonOffset(1.0f, -1100000.0f);

				GlStateManager.popMatrix();
			}
		}
	}
	
	// Last render.+
	@Inject(method = "doRender", at = @At("RETURN"))
	private void doRenderlast(T entity, double x, double y, double z, float yaw, float partial_ticks, CallbackInfo callback) {
		if (entity instanceof IMob && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRange").get_value(1)) && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPHostile").get_value(true)) {
			BopeEntityESP.distance_player = mc.player.getDistance(entity);

			if (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRenderEntity").in("Chams")) {
				GlStateManager.pushMatrix();

				glDisable(GL11.GL_POLYGON_OFFSET_FILL);
				glPolygonOffset(1.0f, 1100000.0f);
				glEnable(GL11.GL_TEXTURE_2D);

				GlStateManager.popMatrix();
			}
		}

		if (entity instanceof EntityAnimal && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRange").get_value(1)) && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPAnimals").get_value(true)) {
			BopeEntityESP.distance_player = mc.player.getDistance(entity);

			if (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRenderEntity").in("Chams")) {
				GlStateManager.pushMatrix();

				glDisable(GL11.GL_POLYGON_OFFSET_FILL);
				glPolygonOffset(1.0f, 1100000.0f);
				glEnable(GL11.GL_TEXTURE_2D);

				GlStateManager.popMatrix();
			}
		}

//		if (entity instanceof EntityESPCrystal && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRange").get_value(1)) && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPCrystal").get_value(true)) {
//			BopeEntityESP.distance_player = mc.player.getDistance(entity);

//			if (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRenderEntity").in("Chams")) {
//				GlStateManager.pushMatrix();

//				glDisable(GL11.GL_POLYGON_OFFSET_FILL);
//				glPolygonOffset(1.0f, 1100000.0f);
//				glEnable(GL11.GL_TEXTURE_2D);

//				GlStateManager.popMatrix();
//			}
//		}

//		if (entity instanceof EntityItem && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRange").get_value(1)) && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPItems").get_value(true)) {
//			BopeEntityESP.distance_player = mc.player.getDistance(entity);
//
//			if (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRenderEntity").in("Chams")) {
//				GlStateManager.pushMatrix();
//
//				glDisable(GL11.GL_POLYGON_OFFSET_FILL);
//				glPolygonOffset(1.0f, 1100000.0f);
//				glEnable(GL11.GL_TEXTURE_2D);
//
//				GlStateManager.popMatrix();
//			}
//		}

		if (entity instanceof EntityPlayer && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPRange").get_value(1))) {
			BopePlayerESP.distance_player = mc.player.getDistance(entity);

			if (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPRenderEntity").in("Chams")) {
				GlStateManager.pushMatrix();

				glDisable(GL11.GL_POLYGON_OFFSET_FILL);
				glPolygonOffset(1.0f, 1100000.0f);
				glEnable(GL11.GL_TEXTURE_2D);

				GlStateManager.popMatrix();
			}
		}
	}

	/**
	 * @author superblaubeere27
	 */
	@Overwrite
	protected void renderModel(T entitylivingbaseIn, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float scaleFactor) {
		boolean flag  = !entitylivingbaseIn.isInvisible();
		boolean flag1 = !flag && !entitylivingbaseIn.isInvisibleToPlayer(mc.player);
		
		if (flag || flag1) {
			if (!bindEntityTexture(entitylivingbaseIn)) {
				return;
			}
		
			if (flag1) {
				GlStateManager.pushMatrix();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 0.15F);
				GlStateManager.depthMask(false);
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(770, 771);
				GlStateManager.alphaFunc(516, 0.003921569F);
			}

			if (entitylivingbaseIn instanceof IMob && mc.player != null && mc.player.getDistance(entitylivingbaseIn) > Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPDistanceRender").get_value(1) && mc.player.getDistance(entitylivingbaseIn) < (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRange").get_value(1)) && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPHostile").get_value(true)) {
				if (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRenderEntity").in("Outline")) {
					Color n = new Color(190, 190, 190);

					TurokGL.setColor(n);

					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderOne(1.5f);
					
					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderTwo();

					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderThree();
					TurokGL.renderFour();
					TurokGL.setColor(n);

					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderFive();
					TurokGL.setColor(Color.WHITE);
				}
			}

			if (entitylivingbaseIn instanceof EntityAnimal && mc.player != null && mc.player.getDistance(entitylivingbaseIn) > Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPDistanceRender").get_value(1) && mc.player.getDistance(entitylivingbaseIn) < (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRange").get_value(1)) && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_module_manager().get_module_with_tag("EntityESP").is_active() && Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPAnimals").get_value(true)) {
				if (Bope.get_setting_manager().get_setting_with_tag("EntityESP", "EntityESPRenderEntity").in("Outline")) {
					Color n = new Color(190, 190, 190);

					TurokGL.setColor(n);

					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderOne(1.5f);
					
					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderTwo();

					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderThree();
					TurokGL.renderFour();
					TurokGL.setColor(n);

					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderFive();
					TurokGL.setColor(Color.WHITE);
				}
			}
	
			if (entitylivingbaseIn instanceof EntityPlayer && mc.player != null && mc.player.getDistance(entitylivingbaseIn) > Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPDistanceRender").get_value(1) && mc.player.getDistance(entitylivingbaseIn) < (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPRange").get_value(1))) {
				if (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPRenderEntity").in("Outline") && Bope.get_module_manager().get_module_with_tag("PlayerESP").is_active()) {
					Color n;

					if (Bope.get_friend_manager().is_friend(entitylivingbaseIn.getName())) {
						n = new Color(Bope.client_r, Bope.client_g, Bope.client_b);
					} else {
						n = new Color(190, 190, 190);
					}

					TurokGL.setColor(n);

					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderOne(1.5f);
					
					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderTwo();

					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderThree();
					TurokGL.renderFour();
					TurokGL.setColor(n);

					mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

					TurokGL.renderFive();
					TurokGL.setColor(Color.WHITE);
				}
			}

			this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);

			if (flag1) {
				GlStateManager.disableBlend();
				GlStateManager.alphaFunc(516, 0.1F);
				GlStateManager.popMatrix();
				GlStateManager.depthMask(true);
			}
		}
	}
}