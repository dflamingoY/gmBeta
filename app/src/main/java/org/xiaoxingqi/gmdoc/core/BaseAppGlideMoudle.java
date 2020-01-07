package org.xiaoxingqi.gmdoc.core;

import com.bumptech.glide.module.AppGlideModule;

public class BaseAppGlideMoudle extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
