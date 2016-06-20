package WayofTime.bloodmagic.livingArmour.upgrade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import WayofTime.bloodmagic.api.Constants;
import WayofTime.bloodmagic.api.livingArmour.ILivingArmour;
import WayofTime.bloodmagic.api.livingArmour.LivingArmourUpgrade;
import WayofTime.bloodmagic.util.ChatUtil;
import WayofTime.bloodmagic.util.helper.TextHelper;

public class LivingArmourUpgradeFireResist extends LivingArmourUpgrade
{
    public static final int[] costs = new int[] { 2, 6, 14, 25, 40 };
    public static final int[] fireCooldownTime = new int[] { 5 * 60 * 20, 5 * 60 * 20, 4 * 60 * 20, 3 * 60 * 20, 2 * 60 * 20 };
    public static final int[] fireResistDuration = new int[] { 30 * 20, 30 * 20, 40 * 20, 50 * 20, 60 * 20 };

    public int fireCooldown = 0;

    public LivingArmourUpgradeFireResist(int level)
    {
        super(level);
    }

    @Override
    public void onTick(World world, EntityPlayer player, ILivingArmour livingArmour)
    {
        if (player.isBurning() && fireCooldown <= 0)
        {

            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, fireResistDuration[this.level]));
            fireCooldown = fireCooldownTime[this.level];

            ChatUtil.sendNoSpam(player, TextHelper.localizeEffect(chatBase + "fireRemove"));

        } else if (fireCooldown > 0)
        {
            fireCooldown--;
        }
    }

    @Override
    public String getUniqueIdentifier()
    {
        return Constants.Mod.MODID + ".upgrade.fireResist";
    }

    @Override
    public int getMaxTier()
    {
        return 5; // Set to here until I can add more upgrades to it.
    }

    @Override
    public int getCostOfUpgrade()
    {
        return costs[this.level];
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        tag.setInteger(Constants.NBT.UPGRADE_FIRE_TIMER, fireCooldown);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        fireCooldown = tag.getInteger(Constants.NBT.UPGRADE_FIRE_TIMER);
    }

    @Override
    public String getUnlocalizedName()
    {
        return tooltipBase + "fireResist";
    }
}