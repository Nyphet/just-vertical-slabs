package crystalspider.justverticalslabs.model.item;

import java.util.List;
import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;

import crystalspider.justverticalslabs.model.VerticalSlabBakedModel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

/**
 * Simply wraps all methods from {@link VerticalSlabBakedModel} and passes them the right {@link IModelData}.
 * See {@link #handlePerspective(TransformType, PoseStack)}, {@link #getParticleIcon(IModelData)} and {@link #getQuads(BlockState, Direction, Random, IModelData)} for more details.
 */
public class VerticalSlabItemBakedModel implements IDynamicBakedModel {
  /**
   * {@link VerticalSlabBakedModel} instance to wrap.
   */
  private final VerticalSlabBakedModel verticalSlabBakedModel;
  /**
   * Proper {@link IModelData}.
   */
  private final IModelData data;

  public VerticalSlabItemBakedModel(VerticalSlabBakedModel verticalSlabBakedModel, IModelData data) {
    this.verticalSlabBakedModel = verticalSlabBakedModel;
    this.data = data;
  }

  /**
   * See {@link VerticalSlabBakedModel#isGui3d()}.
   */
  @Override
  public boolean isGui3d() {
    return verticalSlabBakedModel.isGui3d();
  }

  /**
   * See {@link VerticalSlabBakedModel#usesBlockLight()}.
   */
  @Override
  public boolean usesBlockLight() {
    return verticalSlabBakedModel.usesBlockLight();
  }

  /**
   * See {@link VerticalSlabBakedModel#isCustomRenderer()}.
   */
  @Override
  public boolean isCustomRenderer() {
    return verticalSlabBakedModel.isCustomRenderer();
  }

  /**
   * See {@link VerticalSlabBakedModel#useAmbientOcclusion()}.
   */
  @Override
  public boolean useAmbientOcclusion() {
    return verticalSlabBakedModel.useAmbientOcclusion();
  }

  /**
   * See {@link VerticalSlabBakedModel#getOverrides()}.
   */
  @Override
  public ItemOverrides getOverrides() {
    return verticalSlabBakedModel.getOverrides();
  }

  /**
   * See {@link VerticalSlabBakedModel#doesHandlePerspectives()}.
   */
  @Override
  public boolean doesHandlePerspectives() {
    return verticalSlabBakedModel.doesHandlePerspectives();
  }

  /**
   * Wraps {@link VerticalSlabBakedModel#handlePerspective(TransformType, PoseStack)} and delegates to it the logic.
   * Note that in order to prevent item rendering errors, this model is returned instead of the model returned by {@link VerticalSlabBakedModel#handlePerspective(TransformType, PoseStack)}.
   * 
   * @param transformType - {@link TransformType}.
   * @param poseStack - {@link PoseStack} to render.
   * @return this model.
   */
  @Override
  public BakedModel handlePerspective(TransformType transformType, PoseStack poseStack) {
    verticalSlabBakedModel.handlePerspective(transformType, poseStack);
    return this;
  }

  /**
   * See {@link VerticalSlabBakedModel#getParticleIcon()}.
   */
  @Override
  public TextureAtlasSprite getParticleIcon() {
    return verticalSlabBakedModel.getParticleIcon();
  }

  /**
   * Wraps and returns {@link VerticalSlabBakedModel#getParticleIcon(IModelData)}, passing to it the proper {@link IModelData} to use to render.
   * Since no BlockEntity is associated to an item, the {@link IModelData} parameter won't contain the correct and necessary data to render the item correctly.
   * For this reason, extraData is ignored and {@link #data} is used instead.
   * 
   * @param extraData - {@link IModelData}.
   * @return {@link VerticalSlabBakedModel#getParticleIcon(IModelData)}.
   */
  @Override
  public TextureAtlasSprite getParticleIcon(IModelData extraData) {
    return verticalSlabBakedModel.getParticleIcon(data);
  }

  /**
   * Wraps and returns {@link VerticalSlabBakedModel#getQuads(BlockState, Direction, Random, IModelData)}, passing to it the proper {@link IModelData} to use to render.
   * Since no BlockEntity is associated to an item, the {@link IModelData} parameter won't contain the correct and necessary data to render the item correctly.
   * For this reason, extraData is ignored and {@link #data} is used instead.
   * 
   * @param state - {@link BlockState} of the block being rendered, null if an item is being rendered.
   * @param side - indicates to which culling {@link Direction face} the {@link BakedQuad baked quads} are associated to.
   *               If null, no culling {@link Direction face} is associated and the {@link BakedQuad baked quads} will always be rendered.
   * @param rand - {@link Random} instance.
   * @param modelData - {@link IModelData}.
   * @return {@link VerticalSlabBakedModel#getQuads(BlockState, Direction, Random, IModelData)}.
   */
  @Override
  public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData) {
    return verticalSlabBakedModel.getQuads(state, side, rand, data);
  }
}
