package ganymedes01.aobd.recipes.modules;

import cpw.mods.fml.common.registry.GameRegistry;
import exnihilo.registries.HammerRegistry;
import exnihilo.registries.SieveRegistry;
import ganymedes01.aobd.lib.CompatType;
import ganymedes01.aobd.ore.Ore;
import ganymedes01.aobd.recipes.RecipesModule;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ExNihilo extends RecipesModule {

	public ExNihilo() {
		super(CompatType.EX_NIHILO, "iron", "gold", "copper", "tin", "lead", "silver", "nickel", "platinum", "aluminum", "osmium");
	}

	@Override
	public void initOre(Ore ore) {
		Block dust = Block.getBlockFromName("exnihilo:dust");
		Block crushedNetherrack = Block.getBlockFromName("exnihilo:exnihilo.gravel_nether");

		ItemStack oreStack = getOreStack("ore", ore);
		ItemStack brokenOreStack = getOreStack("oreBroken", ore);
		ItemStack brokenNetherOreStack = getOreStack("oreNetherBroken", ore);
		ItemStack crushedOreStack = getOreStack("oreCrushed", ore);
		ItemStack powderedOreStack = getOreStack("orePowdered", ore);
		ItemStack gravelOreStack = getOreStack("oreGravel", ore);
		ItemStack gravelNetherOreStack = getOreStack("oreNetherGravel", ore);
		ItemStack sandOreStack = getOreStack("oreSand", ore);
		ItemStack dustOreStack = getOreStack("oreDust", ore);

		// Shaped recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(gravelOreStack, "xx", "xx", 'x', "oreBroken" + ore.name()));
		GameRegistry.addRecipe(new ShapedOreRecipe(gravelNetherOreStack, "xx", "xx", 'x', "oreNetherBroken" + ore.name()));
		GameRegistry.addRecipe(new ShapedOreRecipe(sandOreStack, "xx", "xx", 'x', "oreCrushed" + ore.name()));
		GameRegistry.addRecipe(new ShapedOreRecipe(dustOreStack, "xx", "xx", 'x', "orePowdered" + ore.name()));

		// Smelting ore dust into ingots
		GameRegistry.addSmelting(dustOreStack, getOreStack("ingot", ore), 0.1F);

		// Sieving gravel into broken ore
		SieveRegistry.register(Blocks.gravel, brokenOreStack.getItem(), brokenOreStack.getItemDamage(), (int) (20 / ore.energy(1)));

		// Sieving sand into crushed ore
		SieveRegistry.register(Blocks.sand, crushedOreStack.getItem(), crushedOreStack.getItemDamage(), (int) (20 / ore.energy(1)));

		// Sieving dust into powdered ore
		SieveRegistry.register(dust, powderedOreStack.getItem(), powderedOreStack.getItemDamage(), (int) (20 / ore.energy(1)));

		// Hammering ore into broken ore
		if (oreStack.getItem() instanceof ItemBlock) {
			Block oreBlock = Block.getBlockFromItem(oreStack.getItem());
			HammerRegistry.registerOre(oreBlock, oreStack.getItemDamage(), brokenOreStack.getItem(), brokenOreStack.getItemDamage());
		}

		// Sieving netherrack into broken ore
		SieveRegistry.register(crushedNetherrack, 0, brokenNetherOreStack.getItem(), brokenNetherOreStack.getItemDamage(), 17);
	}
}