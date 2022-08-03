package main;

import main.model.ToDo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Storage {
    private static HashMap<Integer, ToDo> toDos = new HashMap<>();
    private static Integer id = 0;
    private static final Object monitor = new Object();

    public static Set<ToDo> getAllToDos() {
        return new HashSet<>(toDos.values());
    }

    public static ToDo getToDo(Integer id) {
        return toDos.get(id);
    }

    public static Integer addToDo(ToDo todo) {
        int toDoId;
        synchronized (monitor) {
            toDoId = ++id;
        }
        todo.setId(toDoId);
        toDos.put(toDoId, todo);
        return toDoId;
    }

    public static boolean deleteToDo(Integer id) {
        if (toDos.containsKey(id)) {
            toDos.remove(id);
            return true;
        }
        return false;
    }

    public static boolean deleteAllToDo() {
        if (!toDos.isEmpty()) {
            toDos.clear();
            return true;
        }
    return false;
    }

    public static boolean updateToDo(Integer id, ToDo todo) {
        if (toDos.containsKey(id)) {
            toDos.put(id, todo);
            return true;
        }
        return false;
    }


}
