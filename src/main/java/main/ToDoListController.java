package main;

import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.ToDo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
public class ToDoListController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/todos/")
    public Set list() {
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        HashSet<ToDo> toDos = new HashSet<>();
        for (ToDo toDo : toDoIterable) {
            toDos.add(toDo);
        }
        return toDos;
    }

    @PostMapping("/todos/")
    public Integer add(@RequestBody ToDo todo) {
        ToDo newToDo = toDoRepository.save(todo);
        return newToDo.getId();
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        boolean isPresent = toDoRepository.existsById(id);
        if (isPresent) {
            toDoRepository.deleteById(id);
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/todos/")
    public ResponseEntity deleteAll() {
        if (toDoRepository.count() > 0) {
            toDoRepository.deleteAll();
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<ToDo> toDoOptional = toDoRepository.findById(id);
        if (toDoOptional.isPresent()) {
            return new ResponseEntity(toDoOptional.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity put(@PathVariable int id, @RequestBody ToDo todo) {
        todo.setId(id);
        toDoRepository.save(todo);
        return new ResponseEntity(true, HttpStatus.OK);
    }


}
