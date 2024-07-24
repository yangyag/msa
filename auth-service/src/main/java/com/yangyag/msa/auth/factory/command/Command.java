package com.yangyag.msa.auth.factory.command;

@FunctionalInterface
public interface Command<T> {
    T execute();
}
