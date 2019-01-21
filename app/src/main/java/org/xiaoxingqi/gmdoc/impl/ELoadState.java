package org.xiaoxingqi.gmdoc.impl;

/**
 * 加载更多数据的状态
 */
public enum ELoadState {
    READY,//准备
    LOADING,//加载中
    EMPTY, //空数据
    GONE,//不可见
    NULLDATA//空数据
}
