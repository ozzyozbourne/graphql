package com.github.graphql;

public record ThreadInfo(String name, long id, boolean isVirtual) {

    public static ThreadInfo create(Thread thread){
        return new ThreadInfo(thread.getName(), thread.threadId(), thread.isVirtual());
    }
}
