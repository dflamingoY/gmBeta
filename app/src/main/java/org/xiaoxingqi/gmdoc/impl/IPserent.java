package org.xiaoxingqi.gmdoc.impl;

public interface IPserent<T> {

    void success(T result);

    void error();
}
