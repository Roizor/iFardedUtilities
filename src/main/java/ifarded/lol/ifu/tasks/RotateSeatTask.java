package ifarded.lol.ifu.tasks;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import ifarded.lol.ifu.IFUtilities;

public class RotateSeatTask extends BukkitRunnable {
	private IFUtilities ifu;
	private RotateSeatTask.AlignArmorStand alignArmorStand;

	public RotateSeatTask(IFUtilities ifu) {
		this.ifu = ifu;

		try {
			this.alignArmorStand = new RotateSeatTask.AlignArmorStand() {
				Method method;

				{
					this.method = Entity.class.getMethod("setRotation", Float.TYPE, Float.TYPE);
				}

				public void align(ArmorStand armorStand, float yaw) {
					try {
						this.method.invoke(armorStand, yaw, 0);
					} catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException var4) {
						var4.printStackTrace();
					}

				}
			};
		} catch (SecurityException | NoSuchMethodException var3) {
			this.alignArmorStand = new RotateSeatTask.AlignArmorStand() {
				public void align(ArmorStand armorStand, float yaw) {
					try {
						Object entityArmorstand = armorStand.getClass().getMethod("getHandle").invoke(armorStand);
						Field yawField = entityArmorstand.getClass().getField("yaw");
						yawField.set(entityArmorstand, yaw);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException
							| NoSuchFieldException | NoSuchMethodException var5) {
						var5.printStackTrace();
					}

				}
			};
		}

		this.runTaskTimerAsynchronously(ifu, 0L, 1L);
	}

	public void run() {
		Iterator var2 = this.ifu.getSeats().values().iterator();

		while (var2.hasNext()) {
			ArmorStand armorstand = (ArmorStand) var2.next();
			if (armorstand.getPassenger() != null) {
				this.alignArmorStand.align(armorstand, armorstand.getPassenger().getLocation().getYaw());
			}
		}

	}

	private interface AlignArmorStand {
		void align(ArmorStand var1, float var2);
	}
}