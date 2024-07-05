package com.yangyag.msa.category.command;

public interface Command<T> {
    T execute();
}
