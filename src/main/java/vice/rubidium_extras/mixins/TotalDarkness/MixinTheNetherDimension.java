/*******************************************************************************
 * Copyright 2019 grondag
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package vice.rubidium_extras.mixins.TotalDarkness;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import vice.rubidium_extras.config.MagnesiumExtrasConfig;
import vice.rubidium_extras.features.TotalDarkness.Darkness;

@Mixin(DimensionSpecialEffects.NetherEffects.class)
public class MixinTheNetherDimension {

	@Inject(method = "getBrightnessDependentFogColor", at = @At(value = "RETURN"), cancellable = true)
	private void onAdjustSkyColor(CallbackInfoReturnable<Vec3> ci) {
		if (!MagnesiumExtrasConfig.trueDarknessEnabled.get())
			return;

		if (!MagnesiumExtrasConfig.darkNether.get())
			return;

		final double factor = Darkness.darkNetherFog();

		Darkness.getDarkenedFogColor(ci, factor);
	}
}
