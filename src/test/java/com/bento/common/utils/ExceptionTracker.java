package com.bento.common.utils;

import java.util.Collection;

public interface ExceptionTracker {

	void addException(Throwable e);

	Collection<Throwable> getExceptions();

	void clearExceptions();
}
