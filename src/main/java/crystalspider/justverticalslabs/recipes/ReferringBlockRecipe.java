package crystalspider.justverticalslabs.recipes;

import crystalspider.justverticalslabs.JustVerticalSlabsLoader;
import crystalspider.justverticalslabs.utils.VerticalSlabUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * {@link VerticalSlabRecipe} to craft 2 matching adjacent Vertical Slabs into their referring block.
 */
public class ReferringBlockRecipe extends VerticalSlabRecipe {
  /**
   * ID of this recipe.
   */
  public static final String ID = "referring_block_recipe";
  /**
     * {@link ResourceLocation} of this recipe used to uniquely identify it.
     */
  private static final ResourceLocation RESOURCE_LOCATION = VerticalSlabUtils.getResourceLocation(ID);

  public ReferringBlockRecipe() {
    super(2, 1, Items.OAK_PLANKS.getDefaultInstance());
  }

  @Override
  public ItemStack assemble(ItemStack matchedItem) {
    return VerticalSlabUtils.getReferringBlockState(matchedItem).getBlock().asItem().getDefaultInstance();
  }

  /**
   * Returns this {@link #RESOURCE_LOCATION ResourceLocation}.
   */
  @Override
  public ResourceLocation getId() {
    return RESOURCE_LOCATION;
  }

  @Override
  public Serializer getSerializer() {
    return JustVerticalSlabsLoader.REFERRING_BLOCK_RECIPE_SERIALIZER.get();
  }

  @Override 
  protected Integer getMatchIndex(CraftingContainer craftingContainer) {
    boolean correctPattern = true;
    Integer matchIndex = null, containerWidth = craftingContainer.getWidth();
    for (int h = 0; h < containerWidth && correctPattern; h++) {
      for (int w = 0; w < craftingContainer.getHeight() && correctPattern; w++) {
        int index = w + h * containerWidth;
        ItemStack itemStack1 = craftingContainer.getItem(index);
        if (!itemStack1.isEmpty()) {
          if (isVerticalSlab(itemStack1)) {
            ItemStack itemStack2 = craftingContainer.getItem(index + 1);
            if (isVerticalSlab(itemStack2)) {
              if (matchIndex == null && (index + 1) % containerWidth != 0 && verticalSlabsMatch(itemStack1, itemStack2)) {
                matchIndex = index;
              } else {
                matchIndex = null;
                correctPattern = false;
              }
            } else if (matchIndex == null || matchIndex != index - 1) {
              matchIndex = null;
              correctPattern = false;
            }
          } else {
            matchIndex = null;
            correctPattern = false;
          }
        }
      }
    }
    return matchIndex;
  }

  /**
   * Serializer for {@link ReferringBlockRecipe}.
   */
  public static class Serializer extends VerticalSlabRecipe.Serializer<ReferringBlockRecipe> {
    /**
     * ID of this {@link Serializer}.
     */
    public static final String ID = ReferringBlockRecipe.ID + "_serializer";
    /**
     * {@link ResourceLocation} of this {@link Serializer} used to uniquely identify it.
     */
    public static final ResourceLocation RESOURCE_LOCATION = VerticalSlabUtils.getResourceLocation(ID);

    public Serializer() {
      super(ReferringBlockRecipe::new);
    }
  }
}
