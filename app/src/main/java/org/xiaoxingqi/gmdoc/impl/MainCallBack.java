package org.xiaoxingqi.gmdoc.impl;

import org.xiaoxingqi.gmdoc.entity.BaseRespData;
import org.xiaoxingqi.gmdoc.entity.TokenData;
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData;

public interface MainCallBack extends BaseInterceptor {

    void userInfo(UserInfoData data);

    void loginOut(BaseRespData data);

    void token(TokenData data);
}
