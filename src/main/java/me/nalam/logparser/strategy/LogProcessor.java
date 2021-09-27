package me.nalam.logparser.strategy;

public interface LogProcessor {
  Object load(String absFilePath) throws Exception;
}
