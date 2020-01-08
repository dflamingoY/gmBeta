package org.xiaoxingqi.gmdoc.core;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

//@GlideModule
public class BaseAppGlideMoudle extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
