package com.yangyag.msa.category.command;

@FunctionalInterface
public interface Command<T> {
    T execute();
}
