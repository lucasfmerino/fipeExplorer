package br.com.projects.FipeExplorer.service;

import java.util.List;

public interface IDataConverter {
    <T> T getData(String json, Class<T> projectClass);

    <T> List<T> getList(String json, Class<T> projectClass);
}
