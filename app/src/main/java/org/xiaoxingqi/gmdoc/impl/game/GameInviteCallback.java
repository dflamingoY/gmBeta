package org.xiaoxingqi.gmdoc.impl.game;

import org.xiaoxingqi.gmdoc.entity.BaseRespData;
import org.xiaoxingqi.gmdoc.entity.game.BaseInvateBean;
import org.xiaoxingqi.gmdoc.entity.game.InvateGameData;

public interface GameInviteCallback {
    void invateUser(InvateGameData data);

    void invating(BaseRespData data, BaseInvateBean bean);
}
