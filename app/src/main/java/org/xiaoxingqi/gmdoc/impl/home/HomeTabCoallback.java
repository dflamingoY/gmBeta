package org.xiaoxingqi.gmdoc.impl.home;

import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData;
import org.xiaoxingqi.gmdoc.impl.IPserent;

public abstract class HomeTabCoallback implements IPserent {

    abstract void otherSuccess(HomeActiveData data);
}
